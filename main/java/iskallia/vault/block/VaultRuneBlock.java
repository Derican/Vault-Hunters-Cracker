
package iskallia.vault.block;

import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.block.Blocks;
import java.util.Random;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import java.util.ArrayList;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import iskallia.vault.init.ModItems;
import iskallia.vault.block.entity.VaultRuneTileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.Hand;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import iskallia.vault.init.ModBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.minecraft.state.StateContainer;
import javax.annotation.Nullable;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.Property;
import net.minecraft.util.Direction;
import net.minecraft.block.BlockState;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import iskallia.vault.world.gen.PortalPlacer;
import net.minecraft.block.Block;

public class VaultRuneBlock extends Block {
    public static final PortalPlacer PORTAL_PLACER;
    public static final DirectionProperty FACING;
    public static final BooleanProperty RUNE_PLACED;

    public VaultRuneBlock() {
        super(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE)
                .strength(Float.MAX_VALUE, Float.MAX_VALUE).noOcclusion());
        this.registerDefaultState((BlockState) ((BlockState) ((BlockState) this.stateDefinition.any())
                .setValue((Property) VaultRuneBlock.FACING, (Comparable) Direction.SOUTH))
                .setValue((Property) VaultRuneBlock.RUNE_PLACED, (Comparable) false));
    }

    @Nullable
    public BlockState getStateForPlacement(final BlockItemUseContext context) {
        return (BlockState) ((BlockState) this.defaultBlockState().setValue((Property) VaultRuneBlock.FACING,
                (Comparable) context.getHorizontalDirection()))
                .setValue((Property) VaultRuneBlock.RUNE_PLACED, (Comparable) false);
    }

    protected void createBlockStateDefinition(final StateContainer.Builder<Block, BlockState> builder) {
        builder.add(new Property[] { (Property) VaultRuneBlock.FACING });
        builder.add(new Property[] { (Property) VaultRuneBlock.RUNE_PLACED });
    }

    public boolean hasTileEntity(final BlockState state) {
        return true;
    }

    @Nullable
    public TileEntity createTileEntity(final BlockState state, final IBlockReader world) {
        return ModBlocks.VAULT_RUNE_TILE_ENTITY.create();
    }

    public ActionResultType use(final BlockState state, final World world, final BlockPos pos,
            final PlayerEntity player, final Hand hand, final BlockRayTraceResult hit) {
        if (world.isClientSide) {
            return super.use(state, world, pos, player, hand, hit);
        }
        final TileEntity tileEntity = world.getBlockEntity(pos);
        if (tileEntity instanceof VaultRuneTileEntity) {
            final VaultRuneTileEntity vaultRuneTE = (VaultRuneTileEntity) tileEntity;
            final String playerNick = player.getDisplayName().getString();
            if (vaultRuneTE.getBelongsTo().equals(playerNick)) {
                final ItemStack heldStack = player.getItemInHand(hand);
                if (heldStack.getItem() == ModItems.VAULT_RUNE) {
                    final BlockState blockState = world.getBlockState(pos);
                    world.setBlock(pos, (BlockState) blockState
                            .setValue((Property) VaultRuneBlock.RUNE_PLACED, (Comparable) true), 3);
                    heldStack.shrink(1);
                    world.playSound((PlayerEntity) null, (double) pos.getX(), (double) pos.getY(),
                            (double) pos.getZ(), SoundEvents.END_PORTAL_FRAME_FILL, SoundCategory.BLOCKS, 1.0f,
                            1.0f);
                    this.createPortalFromRune(world, pos);
                }
            } else {
                final StringTextComponent text = new StringTextComponent(
                        vaultRuneTE.getBelongsTo() + " is responsible with this block.");
                text.setStyle(Style.EMPTY.withColor(Color.fromRgb(-26266)));
                player.displayClientMessage((ITextComponent) text, true);
            }
        }
        return ActionResultType.SUCCESS;
    }

    private void createPortalFromRune(final World world, final BlockPos pos) {
        Direction axis = null;
        for (int i = 1; i < 48; ++i) {
            if (world.getBlockState(pos.offset(i, 0, 0)).getBlock() == ModBlocks.VAULT_RUNE_BLOCK
                    || world.getBlockState(pos.offset(-i, 0, 0)).getBlock() == ModBlocks.VAULT_RUNE_BLOCK) {
                axis = Direction.EAST;
                break;
            }
            if (world.getBlockState(pos.offset(0, 0, i)).getBlock() == ModBlocks.VAULT_RUNE_BLOCK
                    || world.getBlockState(pos.offset(0, 0, -i)).getBlock() == ModBlocks.VAULT_RUNE_BLOCK) {
                axis = Direction.SOUTH;
                break;
            }
        }
        if (axis == null) {
            return;
        }
        BlockPos corner = null;
        for (int j = -48; j <= 0; ++j) {
            if (world.getBlockState(pos.relative(axis, j)).getBlock() == ModBlocks.VAULT_RUNE_BLOCK) {
                corner = pos.relative(axis, j);
                break;
            }
        }
        if (corner == null) {
            return;
        }
        final List<BlockPos> positions = new ArrayList<BlockPos>();
        for (int k = 0; k <= 48; ++k) {
            if (world.getBlockState(corner.relative(axis, k)).getBlock() == ModBlocks.VAULT_RUNE_BLOCK) {
                positions.add(corner.relative(axis, k));
            }
        }
        for (final BlockPos position : positions) {
            final TileEntity tileEntity = world.getBlockEntity(pos);
            if (!(tileEntity instanceof VaultRuneTileEntity)) {
                continue;
            }
            final VaultRuneTileEntity vaultRuneTE = (VaultRuneTileEntity) tileEntity;
            final BlockState state = world.getBlockState(position);
            if (!vaultRuneTE.getBelongsTo().isEmpty()
                    && !(boolean) state.getValue((Property) VaultRuneBlock.RUNE_PLACED)) {
                return;
            }
        }
        final BlockPos low = positions.get(0);
        final BlockPos high = positions.get(positions.size() - 1);
        final BlockPos portalPos = low.above().relative(axis, -1);
        final int width = axis.getAxis().choose(high.getX(), high.getY(),
                high.getZ())
                - axis.getAxis().choose(low.getX(), low.getY(), low.getZ()) + 3;
        VaultRuneBlock.PORTAL_PLACER.place((IWorld) world, portalPos, axis, width, 28);
        if (!world.isClientSide) {
            final ServerWorld sWorld = (ServerWorld) world;
            sWorld.setWeatherParameters(0, 1000000, true, true);
            for (int l = 0; l < 10; ++l) {
                BlockPos pos2 = pos.offset(world.random.nextInt(100) - 50, 0,
                        world.random.nextInt(100) - 50);
                pos2 = world.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING, pos2);
                final LightningBoltEntity lightningboltentity = (LightningBoltEntity) EntityType.LIGHTNING_BOLT
                        .create((World) sWorld);
                lightningboltentity.moveTo(Vector3d.atBottomCenterOf((Vector3i) pos2));
                sWorld.addFreshEntity((Entity) lightningboltentity);
            }
        }
    }

    static {
        PORTAL_PLACER = new PortalPlacer(
                (pos, random,
                        facing) -> (BlockState) ModBlocks.FINAL_VAULT_PORTAL.defaultBlockState().setValue(
                                (Property) VaultPortalBlock.AXIS, (Comparable) facing.getAxis()),
                (pos, random, facing) -> {
                    final Block[] blocks = { Blocks.BLACKSTONE, Blocks.BLACKSTONE, Blocks.POLISHED_BLACKSTONE,
                            Blocks.POLISHED_BLACKSTONE_BRICKS, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS };
                    return blocks[random.nextInt(blocks.length)].defaultBlockState();
                });
        FACING = BlockStateProperties.HORIZONTAL_FACING;
        RUNE_PLACED = BooleanProperty.create("rune_placed");
    }
}
