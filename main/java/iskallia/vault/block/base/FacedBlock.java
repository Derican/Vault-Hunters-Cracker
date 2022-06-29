// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.block.base;

import net.minecraft.block.HorizontalBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.pathfinding.PathType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Mirror;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import javax.annotation.Nonnull;
import net.minecraft.state.Property;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.block.AbstractBlock;
import net.minecraft.state.DirectionProperty;
import net.minecraft.block.Block;

public abstract class FacedBlock extends Block
{
    public static final DirectionProperty FACING;
    
    public FacedBlock(final AbstractBlock.Properties properties) {
        super(properties);
    }
    
    @Nonnull
    public BlockState getStateForPlacement(final BlockItemUseContext context) {
        return (BlockState)this.defaultBlockState().setValue((Property)FacedBlock.FACING, (Comparable)context.getHorizontalDirection());
    }
    
    public BlockState rotate(final BlockState state, final Rotation rot) {
        return (BlockState)state.setValue((Property)FacedBlock.FACING, (Comparable)rot.rotate((Direction)state.getValue((Property)FacedBlock.FACING)));
    }
    
    public BlockState mirror(final BlockState state, final Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation((Direction)state.getValue((Property)FacedBlock.FACING)));
    }
    
    protected void createBlockStateDefinition(final StateContainer.Builder<Block, BlockState> builder) {
        builder.add(new Property[] { (Property)FacedBlock.FACING });
    }
    
    public boolean isPathfindable(final BlockState state, final IBlockReader worldIn, final BlockPos pos, final PathType type) {
        return false;
    }
    
    @OnlyIn(Dist.CLIENT)
    public int getDustColor(final BlockState state, final IBlockReader reader, final BlockPos pos) {
        return state.getMapColor(reader, pos).col;
    }
    
    static {
        FACING = HorizontalBlock.FACING;
    }
}
