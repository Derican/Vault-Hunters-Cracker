// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.block;

import net.minecraft.util.IStringSerializable;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.network.PacketBuffer;
import iskallia.vault.vending.TraderCore;
import iskallia.vault.item.ItemTraderCore;
import iskallia.vault.init.ModItems;
import javax.annotation.Nullable;
import iskallia.vault.container.RenamingContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import iskallia.vault.util.RenameType;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.Hand;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.nbt.INBT;
import iskallia.vault.block.entity.CryoChamberTileEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.IWorld;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateContainer;
import net.minecraft.world.World;
import iskallia.vault.util.MiscUtils;
import net.minecraft.item.BlockItemUseContext;
import iskallia.vault.init.ModBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IItemProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.state.Property;
import net.minecraft.util.Direction;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.block.Block;

public class CryoChamberBlock extends Block
{
    public static final DirectionProperty FACING;
    public static final EnumProperty<DoubleBlockHalf> HALF;
    public static final EnumProperty<ChamberState> CHAMBER_STATE;
    
    public CryoChamberBlock() {
        super(AbstractBlock.Properties.of(Material.METAL, MaterialColor.METAL).strength(5.0f, 3600000.0f).sound(SoundType.METAL).noOcclusion().isRedstoneConductor(CryoChamberBlock::isntSolid).isViewBlocking(CryoChamberBlock::isntSolid));
        this.registerDefaultState((BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue((Property)CryoChamberBlock.FACING, (Comparable)Direction.NORTH)).setValue((Property)CryoChamberBlock.HALF, (Comparable)DoubleBlockHalf.LOWER)).setValue((Property)CryoChamberBlock.CHAMBER_STATE, (Comparable)ChamberState.NONE));
    }
    
    private static boolean isntSolid(final BlockState state, final IBlockReader reader, final BlockPos pos) {
        return false;
    }
    
    public void fillItemCategory(final ItemGroup group, final NonNullList<ItemStack> items) {
        for (final ChamberState state : ChamberState.values()) {
            final ItemStack stack = new ItemStack((IItemProvider)this);
            stack.setDamageValue(state.ordinal());
            items.add((Object)stack);
        }
    }
    
    public boolean hasTileEntity(final BlockState state) {
        return state.getValue((Property)CryoChamberBlock.HALF) == DoubleBlockHalf.LOWER;
    }
    
    public TileEntity createTileEntity(final BlockState state, final IBlockReader world) {
        if (state.getValue((Property)CryoChamberBlock.HALF) != DoubleBlockHalf.LOWER) {
            return null;
        }
        if (state.getValue((Property)CryoChamberBlock.CHAMBER_STATE) == ChamberState.NONE) {
            return ModBlocks.CRYO_CHAMBER_TILE_ENTITY.create();
        }
        return ModBlocks.ANCIENT_CRYO_CHAMBER_TILE_ENTITY.create();
    }
    
    public BlockState getStateForPlacement(final BlockItemUseContext context) {
        final BlockPos pos = context.getClickedPos();
        final World world = context.getLevel();
        if (pos.getY() < 255 && world.getBlockState(pos.above()).canBeReplaced(context)) {
            return (BlockState)((BlockState)((BlockState)this.defaultBlockState().setValue((Property)CryoChamberBlock.FACING, (Comparable)context.getHorizontalDirection())).setValue((Property)CryoChamberBlock.HALF, (Comparable)DoubleBlockHalf.LOWER)).setValue((Property)CryoChamberBlock.CHAMBER_STATE, (Comparable)MiscUtils.getEnumEntry((Class<Comparable<Comparable<Comparable<Comparable<T>>>>>)ChamberState.class, context.getItemInHand().getDamageValue()));
        }
        return null;
    }
    
    protected void createBlockStateDefinition(final StateContainer.Builder<Block, BlockState> builder) {
        builder.add(new Property[] { (Property)CryoChamberBlock.HALF, (Property)CryoChamberBlock.FACING, (Property)CryoChamberBlock.CHAMBER_STATE });
    }
    
    public void playerWillDestroy(final World worldIn, final BlockPos pos, final BlockState state, final PlayerEntity player) {
        if (!worldIn.isClientSide && player.isCreative()) {
            final DoubleBlockHalf half = (DoubleBlockHalf)state.getValue((Property)CryoChamberBlock.HALF);
            if (half == DoubleBlockHalf.UPPER) {
                final BlockPos blockpos = pos.below();
                final BlockState blockstate = worldIn.getBlockState(blockpos);
                if (blockstate.getBlock() == state.getBlock() && blockstate.getValue((Property)CryoChamberBlock.HALF) == DoubleBlockHalf.LOWER) {
                    worldIn.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 35);
                    worldIn.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
                }
            }
        }
        super.playerWillDestroy(worldIn, pos, state, player);
    }
    
    public BlockState updateShape(final BlockState stateIn, final Direction facing, final BlockState facingState, final IWorld worldIn, final BlockPos currentPos, final BlockPos facingPos) {
        final DoubleBlockHalf half = (DoubleBlockHalf)stateIn.getValue((Property)CryoChamberBlock.HALF);
        if (facing.getAxis() == Direction.Axis.Y && half == DoubleBlockHalf.LOWER == (facing == Direction.UP)) {
            return (BlockState)((facingState.is((Block)this) && facingState.getValue((Property)CryoChamberBlock.HALF) != half) ? stateIn.setValue((Property)CryoChamberBlock.FACING, facingState.getValue((Property)CryoChamberBlock.FACING)) : Blocks.AIR.defaultBlockState());
        }
        return (half == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !stateIn.canSurvive((IWorldReader)worldIn, currentPos)) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }
    
    public void setPlacedBy(final World worldIn, final BlockPos pos, final BlockState state, final LivingEntity placer, final ItemStack stack) {
        worldIn.setBlock(pos.above(), (BlockState)state.setValue((Property)CryoChamberBlock.HALF, (Comparable)DoubleBlockHalf.UPPER), 3);
        if (placer != null) {
            final CryoChamberTileEntity te = getCryoChamberTileEntity(worldIn, pos, state);
            te.setOwner(placer.getUUID());
        }
    }
    
    public void onRemove(final BlockState state, final World worldIn, final BlockPos pos, final BlockState newState, final boolean isMoving) {
        if (worldIn.isClientSide) {
            return;
        }
        if (!newState.isAir()) {
            return;
        }
        final CryoChamberTileEntity chamber = getCryoChamberTileEntity(worldIn, pos, state);
        if (chamber == null) {
            return;
        }
        if (state.getValue((Property)CryoChamberBlock.HALF) == DoubleBlockHalf.LOWER) {
            this.dropCryoChamber(worldIn, pos, state, chamber);
        }
        super.onRemove(state, worldIn, pos, newState, isMoving);
    }
    
    private void dropCryoChamber(final World world, final BlockPos pos, final BlockState state, final CryoChamberTileEntity te) {
        final ItemStack chamberStack = new ItemStack((IItemProvider)ModBlocks.CRYO_CHAMBER);
        chamberStack.setDamageValue(((ChamberState)state.getValue((Property)CryoChamberBlock.CHAMBER_STATE)).ordinal());
        final CompoundNBT nbt = chamberStack.getOrCreateTag();
        nbt.put("BlockEntityTag", (INBT)te.serializeNBT());
        chamberStack.setTag(nbt);
        final ItemEntity entity = new ItemEntity(world, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), chamberStack);
        world.addFreshEntity((Entity)entity);
    }
    
    public ActionResultType use(final BlockState state, final World world, final BlockPos pos, final PlayerEntity player, final Hand hand, final BlockRayTraceResult hit) {
        if (world.isClientSide() || !(player instanceof ServerPlayerEntity)) {
            return ActionResultType.SUCCESS;
        }
        final CryoChamberTileEntity chamber = getCryoChamberTileEntity(world, pos, state);
        if (chamber == null) {
            return ActionResultType.SUCCESS;
        }
        if (chamber.getOwner() != null && !chamber.getOwner().equals(player.getUUID())) {
            return ActionResultType.SUCCESS;
        }
        final ItemStack heldStack = player.getItemInHand(hand);
        if (chamber.getEternal() != null) {
            if (!player.isShiftKeyDown()) {
                NetworkHooks.openGui((ServerPlayerEntity)player, (INamedContainerProvider)chamber, buffer -> buffer.writeBlockPos(pos));
                return ActionResultType.SUCCESS;
            }
            if (heldStack.isEmpty()) {
                final CompoundNBT nbt = new CompoundNBT();
                nbt.putInt("RenameType", RenameType.CRYO_CHAMBER.ordinal());
                nbt.put("Data", (INBT)chamber.getRenameNBT());
                NetworkHooks.openGui((ServerPlayerEntity)player, (INamedContainerProvider)new INamedContainerProvider() {
                    public ITextComponent getDisplayName() {
                        return (ITextComponent)new StringTextComponent("Cryo Chamber");
                    }
                    
                    @Nullable
                    public Container createMenu(final int windowId, final PlayerInventory playerInventory, final PlayerEntity playerEntity) {
                        return new RenamingContainer(windowId, nbt);
                    }
                }, buffer -> buffer.writeNbt(nbt));
                return ActionResultType.SUCCESS;
            }
        }
        else if (!((ChamberState)state.getValue((Property)CryoChamberBlock.CHAMBER_STATE)).containsAncient() && heldStack.getItem() == ModItems.TRADER_CORE) {
            final TraderCore coreToInsert = ItemTraderCore.getCoreFromStack(heldStack);
            if (chamber.getOwner() == null) {
                chamber.setOwner(player.getUUID());
            }
            if (chamber.addTraderCore(coreToInsert)) {
                if (!player.isCreative()) {
                    heldStack.shrink(1);
                }
                chamber.sendUpdates();
            }
        }
        return ActionResultType.SUCCESS;
    }
    
    public static BlockPos getCryoChamberPos(final BlockState state, final BlockPos pos) {
        return (state.getValue((Property)CryoChamberBlock.HALF) == DoubleBlockHalf.UPPER) ? pos.below() : pos;
    }
    
    public static CryoChamberTileEntity getCryoChamberTileEntity(final World world, final BlockPos pos, final BlockState state) {
        final BlockPos cryoChamberPos = getCryoChamberPos(state, pos);
        final TileEntity tileEntity = world.getBlockEntity(cryoChamberPos);
        if (!(tileEntity instanceof CryoChamberTileEntity)) {
            return null;
        }
        return (CryoChamberTileEntity)tileEntity;
    }
    
    static {
        FACING = HorizontalBlock.FACING;
        HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
        CHAMBER_STATE = EnumProperty.create("chamber_state", (Class)ChamberState.class);
    }
    
    public enum ChamberState implements IStringSerializable
    {
        NONE("none"), 
        RUSTY("rusty");
        
        private final String name;
        
        private ChamberState(final String name) {
            this.name = name;
        }
        
        public boolean containsAncient() {
            return this == ChamberState.RUSTY;
        }
        
        public String getSerializedName() {
            return this.name;
        }
    }
}
