// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.init;

import iskallia.vault.world.data.PlayerVaultStatsData;
import iskallia.vault.world.data.SoulShardTraderData;
import net.minecraft.server.MinecraftServer;
import iskallia.vault.util.scheduler.DailyScheduler;

public class ModTasks
{
    public static void initTasks(final DailyScheduler scheduler, final MinecraftServer server) {
        scheduler.scheduleServer(3, () -> SoulShardTraderData.get(server).resetDailyTrades());
        scheduler.scheduleServer(15, () -> SoulShardTraderData.get(server).resetDailyTrades());
        scheduler.scheduleServer(0, () -> PlayerVaultStatsData.get(server).generateRecordRewards(server.overworld()));
    }
}
