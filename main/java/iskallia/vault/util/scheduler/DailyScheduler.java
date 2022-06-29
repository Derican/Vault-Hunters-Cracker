// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.util.scheduler;

import iskallia.vault.Vault;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;
import java.util.concurrent.TimeUnit;
import java.time.temporal.Temporal;
import java.time.Duration;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.LogicalSidedProvider;
import net.minecraft.server.MinecraftServer;
import java.time.chrono.ChronoZonedDateTime;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import iskallia.vault.init.ModTasks;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class DailyScheduler
{
    private static DailyScheduler scheduler;
    private final ScheduledExecutorService executorService;
    
    private DailyScheduler() {
        this.executorService = Executors.newScheduledThreadPool(1);
    }
    
    public static void start(final FMLServerStartingEvent event) {
        ModTasks.initTasks(DailyScheduler.scheduler = new DailyScheduler(), event.getServer());
    }
    
    public void scheduleServer(final int hour, final Runnable task) {
        this.scheduleServer(hour, 0, 0, task);
    }
    
    public void scheduleServer(final int hour, final int minute, final int second, final Runnable task) {
        if (DailyScheduler.scheduler == null) {
            throw new IllegalStateException("Startup not finished, Scheduler not running!");
        }
        final ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
        ZonedDateTime nextRun = now.withHour(hour).withMinute(minute).withSecond(second);
        if (now.compareTo((ChronoZonedDateTime<?>)nextRun) > 0) {
            nextRun = nextRun.plusDays(1L);
        }
        DailyScheduler.scheduler.executorService.scheduleAtFixedRate(() -> {
            final MinecraftServer srv = (MinecraftServer)LogicalSidedProvider.INSTANCE.get(LogicalSide.SERVER);
            srv.execute(task);
        }, Duration.between(now, nextRun).getSeconds(), TimeUnit.DAYS.toSeconds(1L), TimeUnit.SECONDS);
    }
    
    public static void stop(final FMLServerStoppingEvent event) {
        DailyScheduler.scheduler.executorService.shutdown();
        try {
            DailyScheduler.scheduler.executorService.awaitTermination(1L, TimeUnit.SECONDS);
        }
        catch (final InterruptedException ex) {
            Vault.LOGGER.error((Object)ex);
        }
        DailyScheduler.scheduler = null;
    }
}
