// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.world.vault.logic.objective.architect.processor;

import java.util.function.Function;
import net.minecraft.block.BlockState;
import iskallia.vault.block.VaultLootableBlock;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import iskallia.vault.world.vault.gen.piece.VaultObelisk;
import net.minecraft.util.Direction;
import iskallia.vault.world.vault.gen.piece.VaultPiece;
import net.minecraft.world.server.ServerWorld;
import iskallia.vault.world.vault.VaultRaid;
import iskallia.vault.world.vault.logic.objective.architect.ArchitectObjective;

public class BossSpawnPieceProcessor extends VaultPieceProcessor
{
    private final ArchitectObjective objective;
    
    public BossSpawnPieceProcessor(final ArchitectObjective objective) {
        this.objective = objective;
    }
    
    @Override
    public void postProcess(final VaultRaid vault, final ServerWorld world, final VaultPiece piece, final Direction generatedDirection) {
        if (!(piece instanceof VaultObelisk)) {
            return;
        }
        final BlockPos stabilizerPos = BlockPos.betweenClosedStream(piece.getBoundingBox()).map(pos -> new Tuple((Object)pos, (Object)world.getBlockState(pos))).filter(tpl -> ((BlockState)tpl.getB()).getBlock() instanceof VaultLootableBlock && ((VaultLootableBlock)((BlockState)tpl.getB()).getBlock()).getType() == VaultLootableBlock.Type.VAULT_OBJECTIVE).findFirst().map((Function<? super Object, ? extends BlockPos>)Tuple::getA).orElse(null);
        if (stabilizerPos != null && world.removeBlock(stabilizerPos, false)) {
            this.objective.spawnBoss(vault, world, stabilizerPos);
        }
    }
}
