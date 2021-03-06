// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.world.vault.builder;

import iskallia.vault.world.vault.player.VaultPlayerType;
import iskallia.vault.world.vault.logic.objective.VaultObjective;
import iskallia.vault.world.vault.logic.VaultLogic;
import iskallia.vault.world.vault.VaultRaid;
import iskallia.vault.item.crystal.CrystalData;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.server.ServerWorld;

public class RaidCommandVaultBuilder extends VaultRaidBuilder
{
    private RaidCommandVaultBuilder() {
    }
    
    public static RaidCommandVaultBuilder get() {
        return new RaidCommandVaultBuilder();
    }
    
    @Override
    public VaultRaid.Builder initializeBuilder(final ServerWorld world, final ServerPlayerEntity player, final CrystalData crystal) {
        return VaultRaid.builder(VaultLogic.CLASSIC, 0, VaultRaid.SUMMON_AND_KILL_BOSS.get()).setInitializer(this.getDefaultInitializer()).addEvents(this.getDefaultEvents()).addPlayer(VaultPlayerType.RUNNER, player);
    }
}
