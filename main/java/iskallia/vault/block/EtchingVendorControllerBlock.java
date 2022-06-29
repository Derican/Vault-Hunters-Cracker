
package iskallia.vault.block;

import javax.annotation.Nullable;
import iskallia.vault.init.ModBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.block.BlockRenderType;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.shapes.EntitySelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.block.BlockState;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.ContainerBlock;

public class EtchingVendorControllerBlock extends ContainerBlock {
    public EtchingVendorControllerBlock() {
        super(AbstractBlock.Properties.copy((AbstractBlock) Blocks.BARRIER).noCollission()
                .isRedstoneConductor(EtchingVendorControllerBlock::nonSolid)
                .isViewBlocking(EtchingVendorControllerBlock::nonSolid));
    }

    private static boolean nonSolid(final BlockState state, final IBlockReader reader, final BlockPos pos) {
        return false;
    }

    public boolean propagatesSkylightDown(final BlockState state, final IBlockReader reader, final BlockPos pos) {
        return true;
    }

    public VoxelShape getShape(final BlockState state, final IBlockReader worldIn, final BlockPos pos,
            final ISelectionContext context) {
        if (context instanceof EntitySelectionContext) {
            final Entity e = context.getEntity();
            if (e instanceof PlayerEntity && ((PlayerEntity) e).isCreative()) {
                return VoxelShapes.block();
            }
        }
        return VoxelShapes.empty();
    }

    public BlockRenderType getRenderShape(final BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    @OnlyIn(Dist.CLIENT)
    public float getShadeBrightness(final BlockState state, final IBlockReader worldIn, final BlockPos pos) {
        return 1.0f;
    }

    @Nullable
    public TileEntity newBlockEntity(final IBlockReader world) {
        return ModBlocks.ETCHING_CONTROLLER_TILE_ENTITY.create();
    }
}
