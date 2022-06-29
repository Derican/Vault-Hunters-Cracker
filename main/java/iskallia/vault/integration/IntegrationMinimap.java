// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.integration;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.world.World;
import iskallia.vault.Vault;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.TickEvent;
import java.util.function.Supplier;
import net.minecraftforge.fml.ModList;
import java.lang.reflect.Modifier;
import java.lang.reflect.Field;
import java.util.function.Consumer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber({ Dist.CLIENT })
public class IntegrationMinimap
{
    private static boolean initialized;
    private static Consumer<Integer> setZoomSetting;
    private static Consumer<Boolean> setShowItems;
    
    public static void overrideItemCheck() {
    }
    
    private static void makeAccessible(final Field f) throws Exception {
        if (Modifier.isFinal(f.getModifiers())) {
            final Field modifiersField = Field.class.getDeclaredField("modifiers");
            if (!modifiersField.isAccessible()) {
                modifiersField.setAccessible(true);
            }
            modifiersField.setInt(f, f.getModifiers() & 0xFFFFFFEF);
        }
    }
    
    private static void initialize() {
        if (IntegrationMinimap.initialized) {
            return;
        }
        try {
            setupConfigAccessors();
        }
        catch (final Exception exc) {
            exc.printStackTrace();
            return;
        }
        IntegrationMinimap.initialized = true;
    }
    
    private static void setupConfigAccessors() throws Exception {
        final Object minimapInstance = ModList.get().getModObjectById("xaerominimap").orElseThrow(IllegalStateException::new);
        final Object minimapSettings = minimapInstance.getClass().getMethod("getSettings", (Class<?>[])new Class[0]).invoke(minimapInstance, new Object[0]);
        final Class<?> modSettingsClass = Class.forName("xaero.common.settings.ModSettings");
        final Field fModSettingsZoom = modSettingsClass.getDeclaredField("zoom");
        fModSettingsZoom.setAccessible(true);
        (IntegrationMinimap.setZoomSetting = (val -> {
            try {
                fModSettingsZoom.setInt(minimapSettings, val);
            }
            catch (final IllegalAccessException e) {
                e.printStackTrace();
            }
            return;
        })).accept(fModSettingsZoom.getInt(minimapSettings));
        final Field fModSettingsShowItems = modSettingsClass.getDeclaredField("showItems");
        fModSettingsShowItems.setAccessible(true);
        (IntegrationMinimap.setShowItems = (val -> {
            try {
                fModSettingsShowItems.setBoolean(minimapSettings, val);
            }
            catch (final IllegalAccessException e2) {
                e2.printStackTrace();
            }
        })).accept(fModSettingsShowItems.getBoolean(minimapSettings));
    }
    
    @SubscribeEvent
    public static void onClientTick(final TickEvent.ClientTickEvent event) {
        if (!ModList.get().isLoaded("xaerominimap")) {
            return;
        }
        if (!IntegrationMinimap.initialized) {
            initialize();
            return;
        }
        if (event.phase == TickEvent.Phase.END) {
            return;
        }
        final World world = (World)Minecraft.getInstance().level;
        if (world == null || world.dimension() != Vault.VAULT_KEY) {
            return;
        }
        IntegrationMinimap.setZoomSetting.accept(4);
        IntegrationMinimap.setShowItems.accept(false);
    }
    
    static {
        IntegrationMinimap.initialized = false;
    }
}
