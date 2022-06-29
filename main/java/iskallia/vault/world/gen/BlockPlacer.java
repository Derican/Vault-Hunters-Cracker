
package iskallia.vault.world.gen;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import java.util.Random;
import net.minecraft.util.math.BlockPos;

@FunctionalInterface
public interface BlockPlacer {
    BlockState getState(final BlockPos p0, final Random p1, final Direction p2);
}
