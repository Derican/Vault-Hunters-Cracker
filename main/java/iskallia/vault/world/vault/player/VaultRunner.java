
package iskallia.vault.world.vault.player;

import iskallia.vault.Vault;
import net.minecraft.entity.player.ServerPlayerEntity;
import iskallia.vault.network.message.VaultOverlayMessage;
import iskallia.vault.world.data.PlayerVaultStatsData;
import iskallia.vault.world.vault.logic.objective.VaultObjective;
import iskallia.vault.world.vault.logic.objective.SummonAndKillBossObjective;
import iskallia.vault.world.vault.time.VaultTimer;
import net.minecraft.world.server.ServerWorld;
import iskallia.vault.world.vault.VaultRaid;
import java.util.UUID;
import net.minecraft.util.ResourceLocation;

public class VaultRunner extends VaultPlayer {
    public static final ResourceLocation ID;

    public VaultRunner() {
    }

    public VaultRunner(final UUID playerId) {
        this(VaultRunner.ID, playerId);
    }

    public VaultRunner(final ResourceLocation id, final UUID playerId) {
        super(id, playerId);
    }

    @Override
    public void tickTimer(final VaultRaid vault, final ServerWorld world, final VaultTimer timer) {
        timer.tick();
        this.runIfPresent(world.getServer(), player -> {
            this.addedExtensions.clear();
            this.appliedExtensions.clear();
        });
    }

    @Override
    public void tickObjectiveUpdates(final VaultRaid vault, final ServerWorld world) {
        this.runIfPresent(world.getServer(), player -> {
            boolean earlyKill = false;
            if (vault.hasActiveObjective(this, SummonAndKillBossObjective.class)) {
                final boolean isRaffle = vault.getProperties().getBase(VaultRaid.IS_RAFFLE).orElse(false);
                if (isRaffle) {
                    final PlayerVaultStatsData.PlayerRecordEntry fastestVault = PlayerVaultStatsData.get(world)
                            .getFastestVaultTime();
                    earlyKill = (this.timer.getRunTime() < fastestVault.getTickCount());
                }
            }
            final boolean showTimer = this.getProperties().getBaseOrDefault(VaultRaid.SHOW_TIMER, true);
            this.sendIfPresent(world.getServer(),
                    VaultOverlayMessage.forVault(showTimer ? this.timer.getTimeLeft() : 0, earlyKill, showTimer));
        });
    }

    static {
        ID = Vault.id("runner");
    }
}
