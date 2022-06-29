// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import net.minecraft.entity.player.PlayerEntity;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.Comparator;
import net.minecraft.entity.Entity;
import java.util.HashMap;
import iskallia.vault.Vault;
import net.minecraft.client.Minecraft;
import java.util.Collection;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.gui.ClientBossInfo;
import java.util.UUID;
import java.util.Map;
import net.minecraft.client.gui.overlay.BossOverlayGui;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ BossOverlayGui.class })
public abstract class MixinBossOverlayGui
{
    @Shadow
    @Final
    private Map<UUID, ClientBossInfo> events;
    
    @Redirect(method = { "render" }, at = @At(value = "INVOKE", target = "Ljava/util/Map;values()Ljava/util/Collection;"))
    private Collection<ClientBossInfo> thing(final Map<UUID, ClientBossInfo> map) {
        if (Minecraft.getInstance().level.dimension() != Vault.VAULT_KEY) {
            return this.events.values();
        }
        final Map<UUID, Entity> entities = new HashMap<UUID, Entity>();
        Minecraft.getInstance().level.entitiesForRendering().forEach(entity -> entities.put(entity.getUUID(), entity));
        return this.events.entrySet().stream().sorted(Comparator.comparingDouble(o -> {
            final PlayerEntity player = (PlayerEntity)Minecraft.getInstance().player;
            final Entity entity2 = entities.get(o.getKey());
            if (entity2 == null) {
                return 2.147483647E9;
            }
            else if (player.getName().getString().equals(entity2.getCustomName().getString())) {
                return -2.147483648E9;
            }
            else {
                return (double)player.distanceTo(entity2);
            }
        })).map((Function<? super Object, ?>)Map.Entry::getValue).collect((Collector<? super Object, ?, Collection<ClientBossInfo>>)Collectors.toList());
    }
}
