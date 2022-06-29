
package iskallia.vault.world.vault.event;

import java.util.WeakHashMap;
import iskallia.vault.Vault;
import net.minecraftforge.fml.common.thread.SidedThreadGroups;
import net.minecraftforge.eventbus.api.Event;
import iskallia.vault.world.vault.VaultRaid;
import java.util.Map;

public class VaultListener {
    public static final Map<VaultRaid, Void> REGISTRY;

    public static void listen(final VaultRaid vault) {
        VaultListener.REGISTRY.put(vault, null);
    }

    public static synchronized <T extends Event> void onEvent(final T event) {
        if (Thread.currentThread().getThreadGroup() != SidedThreadGroups.SERVER) {
            return;
        }
        try {
            VaultListener.REGISTRY.keySet().removeIf(VaultRaid::isFinished);
            VaultListener.REGISTRY.keySet().forEach(vault -> vault.getEvents().forEach(listener -> {
                if (event.getClass().isAssignableFrom(listener.getType())) {
                    listener.accept(vault, event);
                }
            }));
        } catch (final Exception e) {
            Vault.LOGGER.error("Upsie, you know what causes this but are lazy to fix it :(");
            e.printStackTrace();
        }
    }

    static {
        REGISTRY = new WeakHashMap<VaultRaid, Void>();
    }
}
