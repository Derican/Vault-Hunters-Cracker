
package iskallia.vault.world.vault.modifier;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import iskallia.vault.world.data.PhoenixModifierSnapshotData;
import java.util.Random;
import net.minecraft.world.server.ServerWorld;
import iskallia.vault.world.vault.player.VaultPlayer;
import iskallia.vault.world.vault.VaultRaid;
import net.minecraft.util.ResourceLocation;
import com.google.gson.annotations.Expose;

public class InventoryRestoreModifier extends TexturedVaultModifier {
    @Expose
    private final boolean preventsArtifact;

    public InventoryRestoreModifier(final String name, final ResourceLocation icon, final boolean preventsArtifact) {
        super(name, icon);
        this.preventsArtifact = preventsArtifact;
    }

    public boolean preventsArtifact() {
        return this.preventsArtifact;
    }

    @Override
    public void apply(final VaultRaid vault, final VaultPlayer player, final ServerWorld world, final Random random) {
        player.runIfPresent(world.getServer(), sPlayer -> {
            final PhoenixModifierSnapshotData snapshotData = PhoenixModifierSnapshotData.get(world);
            if (snapshotData.hasSnapshot((PlayerEntity) sPlayer)) {
                snapshotData.removeSnapshot((PlayerEntity) sPlayer);
            }
            snapshotData.createSnapshot((PlayerEntity) sPlayer);
        });
    }

    @Override
    public void remove(final VaultRaid vault, final VaultPlayer player, final ServerWorld world, final Random random) {
        final PhoenixModifierSnapshotData snapshotData = PhoenixModifierSnapshotData.get(world);
        if (snapshotData.hasSnapshot(player.getPlayerId())) {
            snapshotData.removeSnapshot(player.getPlayerId());
        }
    }
}
