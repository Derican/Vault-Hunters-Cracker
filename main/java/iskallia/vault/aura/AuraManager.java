// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.aura;

import javax.annotation.Nonnull;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.Collection;
import net.minecraft.entity.Entity;
import java.util.HashSet;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import java.util.Collections;
import net.minecraftforge.event.TickEvent;
import java.util.HashMap;
import java.util.Set;
import net.minecraft.world.World;
import net.minecraft.util.RegistryKey;
import java.util.Map;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class AuraManager
{
    private static final AuraManager INSTANCE;
    private final Map<RegistryKey<World>, Set<ActiveAura>> activeAuras;
    
    private AuraManager() {
        this.activeAuras = new HashMap<RegistryKey<World>, Set<ActiveAura>>();
    }
    
    public static AuraManager getInstance() {
        return AuraManager.INSTANCE;
    }
    
    @SubscribeEvent
    public static void onTick(final TickEvent.WorldTickEvent event) {
        if (event.world.isClientSide() || event.phase != TickEvent.Phase.START) {
            return;
        }
        final Set<ActiveAura> auras = AuraManager.INSTANCE.activeAuras.getOrDefault(event.world.dimension(), Collections.emptySet());
        if (auras.isEmpty()) {
            return;
        }
        auras.removeIf(aura -> !aura.canPersist());
        auras.forEach(ActiveAura::updateFromProvider);
        auras.forEach(aura -> aura.getAura().onTick(event.world, aura));
    }
    
    public void provideAura(final AuraProvider provider) {
        this.activeAuras.computeIfAbsent(provider.getWorld(), key -> new HashSet()).add(new ActiveAura(provider));
    }
    
    @Nonnull
    public Collection<ActiveAura> getAurasAffecting(final Entity entity) {
        final Collection<ActiveAura> worldAuras = this.activeAuras.getOrDefault(entity.getCommandSenderWorld().dimension(), Collections.emptySet());
        if (worldAuras.isEmpty()) {
            return worldAuras;
        }
        return worldAuras.stream().filter(aura -> aura.isAffected(entity)).collect((Collector<? super ActiveAura, ?, Collection<ActiveAura>>)Collectors.toSet());
    }
    
    static {
        INSTANCE = new AuraManager();
    }
}
