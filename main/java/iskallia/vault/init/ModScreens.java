
package iskallia.vault.init;

import iskallia.vault.client.gui.overlay.goal.ObeliskGoalOverlay;
import iskallia.vault.client.gui.overlay.VaultGoalBossBarOverlay;
import iskallia.vault.client.gui.overlay.EyesoreBossOverlay;
import iskallia.vault.client.gui.overlay.PlayerDamageOverlay;
import iskallia.vault.client.gui.overlay.PlayerArmorOverlay;
import iskallia.vault.client.gui.overlay.PlayerRageOverlay;
import iskallia.vault.client.gui.overlay.VaultPartyOverlay;
import iskallia.vault.client.gui.overlay.VaultRaidOverlay;
import iskallia.vault.client.gui.overlay.AbilityVignetteOverlay;
import iskallia.vault.client.gui.overlay.AbilitiesOverlay;
import iskallia.vault.client.gui.overlay.VaultBarOverlay;
import net.minecraftforge.common.MinecraftForge;
import iskallia.vault.client.gui.screen.VaultCharmControllerScreen;
import iskallia.vault.client.gui.screen.EtchingTradeScreen;
import iskallia.vault.client.gui.screen.GlobalDifficultyScreen;
import iskallia.vault.client.gui.screen.CryochamberScreen;
import iskallia.vault.client.gui.screen.ShardTradeScreen;
import iskallia.vault.client.gui.screen.ShardPouchScreen;
import iskallia.vault.client.gui.screen.CatalystDecryptionScreen;
import net.minecraft.client.gui.screen.inventory.ChestScreen;
import iskallia.vault.client.gui.screen.TransmogTableScreen;
import iskallia.vault.client.gui.screen.OmegaStatueScreen;
import iskallia.vault.client.gui.screen.KeyPressScreen;
import iskallia.vault.client.gui.screen.RenameScreen;
import iskallia.vault.client.gui.screen.AdvancedVendingMachineScreen;
import iskallia.vault.client.gui.screen.VendingMachineScreen;
import iskallia.vault.client.gui.screen.VaultCrateScreen;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.client.gui.ScreenManager;
import iskallia.vault.client.gui.screen.SkillTreeScreen;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ModScreens {
    public static void register(final FMLClientSetupEvent event) {
        ScreenManager.register((ContainerType) ModContainers.SKILL_TREE_CONTAINER, SkillTreeScreen::new);
        ScreenManager.register((ContainerType) ModContainers.VAULT_CRATE_CONTAINER, VaultCrateScreen::new);
        ScreenManager.register((ContainerType) ModContainers.VENDING_MACHINE_CONTAINER, VendingMachineScreen::new);
        ScreenManager.register((ContainerType) ModContainers.ADVANCED_VENDING_MACHINE_CONTAINER,
                AdvancedVendingMachineScreen::new);
        ScreenManager.register((ContainerType) ModContainers.RENAMING_CONTAINER, RenameScreen::new);
        ScreenManager.register((ContainerType) ModContainers.KEY_PRESS_CONTAINER, KeyPressScreen::new);
        ScreenManager.register((ContainerType) ModContainers.OMEGA_STATUE_CONTAINER, OmegaStatueScreen::new);
        ScreenManager.register((ContainerType) ModContainers.TRANSMOG_TABLE_CONTAINER, TransmogTableScreen::new);
        ScreenManager.register((ContainerType) ModContainers.SCAVENGER_CHEST_CONTAINER, ChestScreen::new);
        ScreenManager.register((ContainerType) ModContainers.CATALYST_DECRYPTION_CONTAINER,
                CatalystDecryptionScreen::new);
        ScreenManager.register((ContainerType) ModContainers.SHARD_POUCH_CONTAINER, ShardPouchScreen::new);
        ScreenManager.register((ContainerType) ModContainers.SHARD_TRADE_CONTAINER, ShardTradeScreen::new);
        ScreenManager.register((ContainerType) ModContainers.CRYOCHAMBER_CONTAINER, CryochamberScreen::new);
        ScreenManager.register((ContainerType) ModContainers.GLOBAL_DIFFICULTY_CONTAINER,
                GlobalDifficultyScreen::new);
        ScreenManager.register((ContainerType) ModContainers.ETCHING_TRADE_CONTAINER, EtchingTradeScreen::new);
        ScreenManager.register((ContainerType) ModContainers.VAULT_CHARM_CONTROLLER_CONTAINER,
                VaultCharmControllerScreen::new);
    }

    public static void registerOverlays() {
        MinecraftForge.EVENT_BUS.register((Object) VaultBarOverlay.class);
        MinecraftForge.EVENT_BUS.register((Object) AbilitiesOverlay.class);
        MinecraftForge.EVENT_BUS.register((Object) AbilityVignetteOverlay.class);
        MinecraftForge.EVENT_BUS.register((Object) VaultRaidOverlay.class);
        MinecraftForge.EVENT_BUS.register((Object) VaultPartyOverlay.class);
        MinecraftForge.EVENT_BUS.register((Object) PlayerRageOverlay.class);
        MinecraftForge.EVENT_BUS.register((Object) PlayerArmorOverlay.class);
        MinecraftForge.EVENT_BUS.register((Object) PlayerDamageOverlay.class);
        MinecraftForge.EVENT_BUS.register((Object) EyesoreBossOverlay.class);
        MinecraftForge.EVENT_BUS.register((Object) VaultGoalBossBarOverlay.class);
        MinecraftForge.EVENT_BUS.register((Object) ObeliskGoalOverlay.class);
    }
}
