
package iskallia.vault.world.vault.logic.objective.architect.processor;

import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.LivingEntity;
import iskallia.vault.world.vault.logic.VaultSpawner;
import iskallia.vault.util.MiscUtils;
import net.minecraft.util.math.AxisAlignedBB;
import iskallia.vault.world.vault.gen.piece.VaultObelisk;
import net.minecraft.util.Direction;
import iskallia.vault.world.vault.gen.piece.VaultPiece;
import net.minecraft.world.server.ServerWorld;
import iskallia.vault.world.vault.VaultRaid;

public class VaultSpawnerSpawningPostProcessor extends VaultPieceProcessor {
    private final int blocksPerSpawn;

    public VaultSpawnerSpawningPostProcessor(final int blocksPerSpawn) {
        this.blocksPerSpawn = blocksPerSpawn;
    }

    @Override
    public void postProcess(final VaultRaid vault, final ServerWorld world, final VaultPiece piece,
            final Direction generatedDirection) {
        if (piece instanceof VaultObelisk) {
            return;
        }
        vault.getProperties().getBase(VaultRaid.LEVEL).ifPresent(vaultLevel -> {
            final AxisAlignedBB box = AxisAlignedBB.of(piece.getBoundingBox());
            final float size = (float) ((box.maxX - box.minX)
                    * (box.maxY - box.minY) * (box.maxZ - box.minZ));
            float runs = size / this.blocksPerSpawn;
            while (runs > 0.0f && (runs >= 1.0f || VaultSpawnerSpawningPostProcessor.rand.nextFloat() < runs)) {
                --runs;
                BlockPos pos;
                for (LivingEntity spawned = null; spawned == null; spawned = VaultSpawner.spawnMob(vault, world,
                        vaultLevel, pos.getX(), pos.getY(), pos.getZ(),
                        VaultSpawnerSpawningPostProcessor.rand)) {
                    pos = MiscUtils.getRandomPos(box, VaultSpawnerSpawningPostProcessor.rand);
                }
            }
        });
    }
}
