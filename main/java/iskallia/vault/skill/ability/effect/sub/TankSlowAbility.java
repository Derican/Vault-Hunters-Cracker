
package iskallia.vault.skill.ability.effect.sub;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import java.util.Iterator;
import java.util.List;
import iskallia.vault.skill.ability.AbilityNode;
import iskallia.vault.skill.ability.AbilityTree;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import iskallia.vault.entity.EternalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.IWorld;
import iskallia.vault.util.EntityHelper;
import net.minecraft.entity.LivingEntity;
import iskallia.vault.world.data.PlayerAbilitiesData;
import net.minecraft.world.server.ServerWorld;
import iskallia.vault.init.ModEffects;
import net.minecraftforge.event.TickEvent;
import iskallia.vault.skill.ability.config.sub.TankSlowConfig;
import iskallia.vault.skill.ability.effect.TankAbility;

public class TankSlowAbility extends TankAbility<TankSlowConfig> {
    @SubscribeEvent
    public void onWorldTick(final TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            return;
        }
        final EffectInstance tank = event.player.getEffect(ModEffects.TANK);
        if (tank == null) {
            return;
        }
        if (event.player.getCommandSenderWorld() instanceof ServerWorld) {
            final ServerWorld sWorld = (ServerWorld) event.player.getCommandSenderWorld();
            if (sWorld.getServer().getTickCount() % 20 != 0) {
                return;
            }
            final AbilityTree abilities = PlayerAbilitiesData.get(sWorld).getAbilities(event.player);
            final AbilityNode<?, ?> tankNode = abilities.getNodeByName("Tank");
            if (tankNode.getAbility() == this && tankNode.isLearned()) {
                final TankSlowConfig config = (TankSlowConfig) tankNode.getAbilityConfig();
                final List<LivingEntity> entities = EntityHelper.getNearby((IWorld) sWorld,
                        (Vector3i) event.player.blockPosition(), config.getSlowAreaRadius(), LivingEntity.class);
                entities.removeIf(e -> e instanceof PlayerEntity || e instanceof EternalEntity);
                for (final LivingEntity entity : entities) {
                    entity.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 80, config.getSlownessAmplifier(),
                            false, false, false));
                }
            }
        }
    }
}
