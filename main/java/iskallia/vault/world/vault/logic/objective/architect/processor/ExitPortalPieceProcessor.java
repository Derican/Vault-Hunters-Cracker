
package iskallia.vault.world.vault.logic.objective.architect.processor;

import net.minecraft.state.Property;
import iskallia.vault.block.VaultPortalBlock;
import iskallia.vault.init.ModBlocks;
import iskallia.vault.util.MiscUtils;
import net.minecraft.block.Blocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import java.util.Random;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import iskallia.vault.world.vault.gen.piece.VaultRoom;
import net.minecraft.util.Direction;
import iskallia.vault.world.vault.gen.piece.VaultPiece;
import net.minecraft.world.server.ServerWorld;
import iskallia.vault.world.vault.VaultRaid;
import iskallia.vault.world.vault.logic.objective.architect.ArchitectObjective;
import iskallia.vault.world.gen.PortalPlacer;

public class ExitPortalPieceProcessor extends VaultPieceProcessor {
    private static final PortalPlacer EXIT_PORTAL_PLACER;
    private final ArchitectObjective objective;

    public ExitPortalPieceProcessor(final ArchitectObjective objective) {
        this.objective = objective;
    }

    @Override
    public void postProcess(final VaultRaid vault, final ServerWorld world, final VaultPiece piece,
            final Direction generatedDirection) {
        if (!(piece instanceof VaultRoom)) {
            return;
        }
        final Direction portalDir = generatedDirection.getClockWise();
        final VaultRoom room = (VaultRoom) piece;
        final BlockPos at = new BlockPos(room.getCenter()).relative(portalDir, -1);
        this.objective
                .buildPortal(ExitPortalPieceProcessor.EXIT_PORTAL_PLACER.place((IWorld) world, at, portalDir, 3, 5));
    }

    static {
        EXIT_PORTAL_PLACER = new PortalPlacer(
                (pos, random,
                        facing) -> (BlockState) ModBlocks.VAULT_PORTAL.defaultBlockState().setValue(
                                (Property) VaultPortalBlock.AXIS, (Comparable) facing.getAxis()),
                (pos, random, facing) -> MiscUtils
                        .eitherOf(random, Blocks.ANDESITE, Blocks.POLISHED_ANDESITE, Blocks.POLISHED_ANDESITE)
                        .defaultBlockState());
    }
}
