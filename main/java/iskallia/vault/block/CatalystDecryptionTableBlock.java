// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.block;

import net.minecraft.block.HorizontalBlock;
import net.minecraft.network.PacketBuffer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraftforge.items.IItemHandler;
import javax.annotation.Nullable;
import iskallia.vault.init.ModBlocks;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.pathfinding.PathType;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraft.entity.player.ServerPlayerEntity;
import iskallia.vault.block.entity.CatalystDecryptionTableTileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.Hand;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.Direction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.block.LecternBlock;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.state.Property;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;
import net.minecraft.state.DirectionProperty;
import net.minecraft.block.Block;

public class CatalystDecryptionTableBlock extends Block
{
    public static final DirectionProperty FACING;
    
    public CatalystDecryptionTableBlock() {
        super(AbstractBlock.Properties.of(Material.STONE).strength(1.5f, 6.0f).noOcclusion());
    }
    
    public BlockState getStateForPlacement(final BlockItemUseContext context) {
        return (BlockState)this.defaultBlockState().setValue((Property)CatalystDecryptionTableBlock.FACING, (Comparable)context.getHorizontalDirection().getOpposite());
    }
    
    public VoxelShape getOcclusionShape(final BlockState state, final IBlockReader worldIn, final BlockPos pos) {
        return LecternBlock.SHAPE_COMMON;
    }
    
    public VoxelShape getCollisionShape(final BlockState state, final IBlockReader worldIn, final BlockPos pos, final ISelectionContext context) {
        return LecternBlock.SHAPE_COLLISION;
    }
    
    public VoxelShape getShape(final BlockState state, final IBlockReader worldIn, final BlockPos pos, final ISelectionContext context) {
        switch ((Direction)state.getValue((Property)CatalystDecryptionTableBlock.FACING)) {
            case NORTH: {
                return LecternBlock.SHAPE_NORTH;
            }
            case SOUTH: {
                return LecternBlock.SHAPE_SOUTH;
            }
            case EAST: {
                return LecternBlock.SHAPE_EAST;
            }
            case WEST: {
                return LecternBlock.SHAPE_WEST;
            }
            default: {
                return LecternBlock.SHAPE_COMMON;
            }
        }
    }
    
    public ActionResultType use(final BlockState state, final World world, final BlockPos pos, final PlayerEntity player, final Hand hand, final BlockRayTraceResult hit) {
        if (world.isClientSide()) {
            return ActionResultType.SUCCESS;
        }
        final TileEntity te = world.getBlockEntity(pos);
        if (!(te instanceof CatalystDecryptionTableTileEntity)) {
            return ActionResultType.SUCCESS;
        }
        if (!(player instanceof ServerPlayerEntity)) {
            return ActionResultType.SUCCESS;
        }
        NetworkHooks.openGui((ServerPlayerEntity)player, (INamedContainerProvider)te, buffer -> buffer.writeBlockPos(pos));
        return ActionResultType.SUCCESS;
    }
    
    public void onRemove(final BlockState state, final World worldIn, final BlockPos pos, final BlockState newState, final boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            final TileEntity tileentity = worldIn.getBlockEntity(pos);
            if (tileentity instanceof CatalystDecryptionTableTileEntity) {
                tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
                    for (int i = 0; i < handler.getSlots(); ++i) {
                        InventoryHelper.dropItemStack(worldIn, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), handler.getStackInSlot(i));
                    }
                });
            }
        }
        super.onRemove(state, worldIn, pos, newState, isMoving);
    }
    
    public boolean useShapeForLightOcclusion(final BlockState state) {
        return true;
    }
    
    public boolean isPathfindable(final BlockState state, final IBlockReader worldIn, final BlockPos pos, final PathType type) {
        return false;
    }
    
    public BlockState rotate(final BlockState state, final Rotation rot) {
        return (BlockState)state.setValue((Property)CatalystDecryptionTableBlock.FACING, (Comparable)rot.rotate((Direction)state.getValue((Property)CatalystDecryptionTableBlock.FACING)));
    }
    
    public BlockState mirror(final BlockState state, final Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation((Direction)state.getValue((Property)CatalystDecryptionTableBlock.FACING)));
    }
    
    protected void createBlockStateDefinition(final StateContainer.Builder<Block, BlockState> builder) {
        builder.add(new Property[] { (Property)CatalystDecryptionTableBlock.FACING });
    }
    
    public boolean hasTileEntity(final BlockState state) {
        return true;
    }
    
    @Nullable
    public TileEntity createTileEntity(final BlockState state, final IBlockReader world) {
        return ModBlocks.CATALYST_DECRYPTION_TABLE_TILE_ENTITY.create();
    }
    
    static {
        FACING = HorizontalBlock.FACING;
    }
}
