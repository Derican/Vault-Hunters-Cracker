
package iskallia.vault.event;

import iskallia.vault.init.ModModels;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import iskallia.vault.init.ModGameRules;
import iskallia.vault.init.ModRecipes;
import iskallia.vault.init.ModNetwork;
import iskallia.vault.init.ModConfigs;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import iskallia.vault.client.util.ShaderUtil;
import iskallia.vault.init.ModBlocks;
import net.minecraftforge.common.MinecraftForge;
import iskallia.vault.init.ModEntities;
import iskallia.vault.init.ModKeybinds;
import iskallia.vault.init.ModScreens;
import iskallia.vault.Vault;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SetupEvents {
    @SubscribeEvent
    public static void setupClient(final FMLClientSetupEvent event) {
        Vault.LOGGER.info("setupClient()");
        ModScreens.register(event);
        ModScreens.registerOverlays();
        ModKeybinds.register(event);
        ModEntities.Renderers.register(event);
        MinecraftForge.EVENT_BUS.register((Object) InputEvents.class);
        ModBlocks.registerTileEntityRenderers();
        event.enqueueWork(ShaderUtil::initShaders);
    }

    @SubscribeEvent
    public static void setupCommon(final FMLCommonSetupEvent event) {
        Vault.LOGGER.info("setupCommon()");
        ModConfigs.register();
        ModNetwork.initialize();
        ModRecipes.initialize();
        ModGameRules.initialize();
    }

    @SubscribeEvent
    public static void setupDedicatedServer(final FMLDedicatedServerSetupEvent event) {
        Vault.LOGGER.info("setupDedicatedServer()");
        ModModels.SpecialGearModel.register();
        ModModels.GearModel.register();
        ModModels.SpecialSwordModel.register();
    }
}
