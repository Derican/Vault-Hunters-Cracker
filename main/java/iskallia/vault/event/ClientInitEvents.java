
package iskallia.vault.event;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import iskallia.vault.init.ModModels;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = { Dist.CLIENT })
public class ClientInitEvents {
    @SubscribeEvent
    public static void onColorHandlerRegister(final ColorHandlerEvent.Item event) {
        ModModels.registerItemColors(event.getItemColors());
    }
}
