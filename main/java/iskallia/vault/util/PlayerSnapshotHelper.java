// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.util;

import net.minecraftforge.fml.loading.FMLPaths;
import java.io.IOException;
import iskallia.vault.Vault;
import iskallia.vault.dump.PlayerSnapshotDump;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import iskallia.vault.init.ModConfigs;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlayerSnapshotHelper
{
    @SubscribeEvent
    public static void onSave(final PlayerEvent.SaveToFile event) {
        if (!(event.getPlayer() instanceof ServerPlayerEntity)) {
            return;
        }
        if (!ModConfigs.VAULT_GENERAL.SAVE_PLAYER_SNAPSHOTS) {
            return;
        }
        savePlayerSnapshot((ServerPlayerEntity)event.getPlayer());
    }
    
    private static void savePlayerSnapshot(final ServerPlayerEntity sPlayer) {
        savePlayerSnapshot(sPlayer, getSnapshotDirectory());
    }
    
    private static void savePlayerSnapshot(final ServerPlayerEntity sPlayer, final File directory) {
        final String uuidStr = sPlayer.getUUID().toString();
        final File playerFile = new File(directory, uuidStr + ".json");
        if (!playerFile.exists()) {
            playerFile.delete();
        }
        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(playerFile))) {
            writer.write(PlayerSnapshotDump.createAndSerializeSnapshot(sPlayer));
        }
        catch (final IOException exc) {
            Vault.LOGGER.error("Error writing player snapshot file: " + sPlayer.getName().getString());
            exc.printStackTrace();
        }
    }
    
    private static File getSnapshotDirectory() {
        final File snapshotDirectory = FMLPaths.GAMEDIR.get().resolve("playerSnapshots").toFile();
        if (!snapshotDirectory.exists()) {
            snapshotDirectory.mkdirs();
        }
        return snapshotDirectory;
    }
}
