
package iskallia.vault.block;

import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.block.BlockState;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.block.Block;

public class BowHatBlock extends Block {
    public static final VoxelShape SHAPE;

    public BowHatBlock() {
        super(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE)
                .strength(1.0f, 3600000.0f).noOcclusion().noCollission());
    }

    public VoxelShape getShape(final BlockState state, final IBlockReader worldIn, final BlockPos pos,
            final ISelectionContext context) {
        return BowHatBlock.SHAPE;
    }

    static {
        SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 6.0, 14.0);
    }
}
