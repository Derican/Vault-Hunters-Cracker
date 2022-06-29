
package iskallia.vault.world.vault.logic.objective.architect.processor;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import iskallia.vault.entity.FloatingItemEntity;
import net.minecraft.world.IBlockReader;
import iskallia.vault.util.MiscUtils;
import net.minecraft.util.math.AxisAlignedBB;
import iskallia.vault.world.vault.gen.piece.VaultObelisk;
import net.minecraft.util.Direction;
import iskallia.vault.world.vault.gen.piece.VaultPiece;
import net.minecraft.world.server.ServerWorld;
import iskallia.vault.world.vault.VaultRaid;
import net.minecraft.item.ItemStack;
import iskallia.vault.util.data.WeightedList;

public class FloatingItemPostProcessor extends VaultPieceProcessor {
    private final int blocksPerSpawn;
    private final WeightedList<ItemStack> itemList;

    public FloatingItemPostProcessor(final int blocksPerSpawn, final WeightedList<ItemStack> itemList) {
        this.blocksPerSpawn = blocksPerSpawn;
        this.itemList = itemList;
    }

    @Override
    public void postProcess(final VaultRaid vault, final ServerWorld world, final VaultPiece piece,
            final Direction generatedDirection) {
        if (piece instanceof VaultObelisk) {
            return;
        }
        final AxisAlignedBB box = AxisAlignedBB.of(piece.getBoundingBox());
        final float size = (float) ((box.maxX - box.minX) * (box.maxY - box.minY)
                * (box.maxZ - box.minZ));
        float runs = size / this.blocksPerSpawn;
        while (runs > 0.0f && (runs >= 1.0f || FloatingItemPostProcessor.rand.nextFloat() < runs)) {
            --runs;
            boolean placed = false;
            while (!placed) {
                final BlockPos pos = MiscUtils.getRandomPos(box, FloatingItemPostProcessor.rand);
                final BlockState state = world.getBlockState(pos);
                if (state.isAir((IBlockReader) world, pos)) {
                    placed = true;
                    final ItemStack stack = this.itemList.getRandom(FloatingItemPostProcessor.rand);
                    if (stack == null) {
                        continue;
                    }
                    final FloatingItemEntity itemEntity = FloatingItemEntity.create((World) world, pos,
                            stack.copy());
                    world.addFreshEntity((Entity) itemEntity);
                }
            }
        }
    }
}
