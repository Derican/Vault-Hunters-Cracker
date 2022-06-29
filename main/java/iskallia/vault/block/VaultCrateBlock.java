
package iskallia.vault.block;

import net.minecraft.state.properties.BlockStateProperties;
import java.util.function.Predicate;
import net.minecraft.entity.LivingEntity;
import javax.annotation.Nullable;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraft.entity.player.ServerPlayerEntity;
import iskallia.vault.container.VaultCrateContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.inventory.container.INamedContainerProvider;
import iskallia.vault.block.entity.VaultCrateTileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.Hand;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.state.StateContainer;
import iskallia.vault.init.ModBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.minecraft.nbt.INBT;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IItemProvider;
import iskallia.vault.Vault;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.state.Property;
import net.minecraft.util.Direction;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraft.state.DirectionProperty;
import net.minecraft.block.Block;

public class VaultCrateBlock extends Block {
    public static final DirectionProperty HORIZONTAL_FACING;
    public static final DirectionProperty FACING;

    public VaultCrateBlock() {
        super(AbstractBlock.Properties.of(Material.METAL, MaterialColor.METAL)
                .strength(2.0f, 3600000.0f).sound(SoundType.METAL).noOcclusion());
        this.registerDefaultState((BlockState) ((BlockState) this.defaultBlockState()
                .setValue((Property) VaultCrateBlock.HORIZONTAL_FACING, (Comparable) Direction.NORTH))
                .setValue((Property) VaultCrateBlock.FACING, (Comparable) Direction.UP));
    }

    public static ItemStack getCrateWithLoot(final VaultCrateBlock crateType, NonNullList<ItemStack> items) {
        if (items.size() > 54) {
            Vault.LOGGER.error("Attempted to get a crate with more than 54 items. Check crate loot table.");
            items = (NonNullList<ItemStack>) NonNullList.of((Object) ItemStack.EMPTY,
                    items.stream().limit(54L).toArray(ItemStack[]::new));
        }
        final ItemStack crate = new ItemStack((IItemProvider) crateType);
        final CompoundNBT nbt = new CompoundNBT();
        ItemStackHelper.saveAllItems(nbt, (NonNullList) items);
        if (!nbt.isEmpty()) {
            crate.addTagElement("BlockEntityTag", (INBT) nbt);
        }
        return crate;
    }

    public boolean hasTileEntity(final BlockState state) {
        return true;
    }

    public TileEntity createTileEntity(final BlockState state, final IBlockReader world) {
        return ModBlocks.VAULT_CRATE_TILE_ENTITY.create();
    }

    protected void createBlockStateDefinition(final StateContainer.Builder<Block, BlockState> builder) {
        builder.add(
                new Property[] { (Property) VaultCrateBlock.HORIZONTAL_FACING, (Property) VaultCrateBlock.FACING });
    }

    public ActionResultType use(final BlockState state, final World world, final BlockPos pos,
            final PlayerEntity player, final Hand handIn, final BlockRayTraceResult hit) {
        if (!world.isClientSide) {
            final TileEntity tileEntity = world.getBlockEntity(pos);
            if (!(tileEntity instanceof VaultCrateTileEntity)) {
                throw new IllegalStateException("Our named container provider is missing!");
            }
            final INamedContainerProvider containerProvider = (INamedContainerProvider) new INamedContainerProvider() {
                public ITextComponent getDisplayName() {
                    return (ITextComponent) ((state.getBlock() == ModBlocks.VAULT_CRATE_ARENA)
                            ? new TranslationTextComponent("container.vault.vault_crate_arena")
                            : ((state.getBlock() == ModBlocks.VAULT_CRATE_SCAVENGER)
                                    ? new TranslationTextComponent("container.vault.vault_crate_scavenger")
                                    : ((state.getBlock() == ModBlocks.VAULT_CRATE_CAKE)
                                            ? new TranslationTextComponent("container.vault.vault_crate_cake")
                                            : new TranslationTextComponent("container.vault.vault_crate"))));
                }

                public Container createMenu(final int i, final PlayerInventory playerInventory,
                        final PlayerEntity playerEntity) {
                    return new VaultCrateContainer(i, world, pos, playerInventory, playerEntity);
                }
            };
            NetworkHooks.openGui((ServerPlayerEntity) player, containerProvider, tileEntity.getBlockPos());
            world.playSound((PlayerEntity) null, player.getX(), player.getY(),
                    player.getZ(), SoundEvents.BARREL_OPEN, SoundCategory.BLOCKS, 1.0f, 1.0f);
        }
        return ActionResultType.SUCCESS;
    }

    public void playerWillDestroy(final World world, final BlockPos pos, final BlockState state,
            final PlayerEntity player) {
        super.playerWillDestroy(world, pos, state, player);
        final VaultCrateBlock block = this.getBlockVariant();
        final TileEntity tileentity = world.getBlockEntity(pos);
        if (tileentity instanceof VaultCrateTileEntity) {
            final VaultCrateTileEntity crate = (VaultCrateTileEntity) tileentity;
            final ItemStack itemstack = new ItemStack((IItemProvider) block);
            final CompoundNBT compoundnbt = crate.saveToNbt();
            if (!compoundnbt.isEmpty()) {
                itemstack.addTagElement("BlockEntityTag", (INBT) compoundnbt);
            }
            final ItemEntity itementity = new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 0.5,
                    pos.getZ() + 0.5, itemstack);
            itementity.setDefaultPickUpDelay();
            world.addFreshEntity((Entity) itementity);
        }
    }

    @Nullable
    public BlockState getStateForPlacement(final BlockItemUseContext context) {
        final Direction placeDir = context.getNearestLookingDirection().getOpposite();
        Direction horizontalDir = Direction.NORTH;
        if (placeDir.getAxis().isVertical()) {
            for (final Direction direction : context.getNearestLookingDirections()) {
                if (direction.getAxis().isHorizontal()) {
                    horizontalDir = direction;
                    break;
                }
            }
        }
        return (BlockState) ((BlockState) this.defaultBlockState().setValue((Property) VaultCrateBlock.FACING,
                (Comparable) placeDir))
                .setValue((Property) VaultCrateBlock.HORIZONTAL_FACING, (Comparable) horizontalDir);
    }

    private VaultCrateBlock getBlockVariant() {
        if (this.getBlock() == ModBlocks.VAULT_CRATE) {
            return ModBlocks.VAULT_CRATE;
        }
        if (this.getBlock() == ModBlocks.VAULT_CRATE_SCAVENGER) {
            return ModBlocks.VAULT_CRATE_SCAVENGER;
        }
        if (this.getBlock() == ModBlocks.VAULT_CRATE_CAKE) {
            return ModBlocks.VAULT_CRATE_CAKE;
        }
        return ModBlocks.VAULT_CRATE_ARENA;
    }

    public void setPlacedBy(final World worldIn, final BlockPos pos, final BlockState state,
            @Nullable final LivingEntity placer, final ItemStack stack) {
        if (worldIn.isClientSide()) {
            return;
        }
        final CompoundNBT tag = stack.getTagElement("BlockEntityTag");
        if (tag == null) {
            return;
        }
        final VaultCrateTileEntity crate = this.getCrateTileEntity(worldIn, pos);
        if (crate == null) {
            return;
        }
        crate.loadFromNBT(tag);
        super.setPlacedBy(worldIn, pos, state, placer, stack);
    }

    private VaultCrateTileEntity getCrateTileEntity(final World worldIn, final BlockPos pos) {
        final TileEntity te = worldIn.getBlockEntity(pos);
        if (!(te instanceof VaultCrateTileEntity)) {
            return null;
        }
        return (VaultCrateTileEntity) worldIn.getBlockEntity(pos);
    }

    static {
        HORIZONTAL_FACING = DirectionProperty.create("horizontal_facing",
                (Predicate) Direction.Plane.HORIZONTAL);
        FACING = BlockStateProperties.FACING;
    }
}
