// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.skill.ability.effect.sub;

import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.entity.LivingEntity;
import iskallia.vault.skill.ability.AbilityNode;
import iskallia.vault.skill.ability.AbilityTree;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import iskallia.vault.util.DamageOverTimeHelper;
import iskallia.vault.Vault;
import iskallia.vault.world.data.PlayerAbilitiesData;
import iskallia.vault.init.ModEffects;
import net.minecraft.world.server.ServerWorld;
import iskallia.vault.event.ActiveFlags;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import iskallia.vault.skill.ability.config.sub.RampageDotConfig;
import iskallia.vault.skill.ability.effect.RampageAbility;

public class RampageDotAbility extends RampageAbility<RampageDotConfig>
{
    @SubscribeEvent
    public void onLivingDamage(final LivingDamageEvent event) {
        if (event.getSource() instanceof PlayerDamageOverTimeSource) {
            return;
        }
        if (event.getEntity().getCommandSenderWorld().isClientSide()) {
            return;
        }
        if (!(event.getSource().getEntity() instanceof PlayerEntity)) {
            return;
        }
        if (ActiveFlags.IS_REFLECT_ATTACKING.isSet()) {
            return;
        }
        final PlayerEntity player = (PlayerEntity)event.getSource().getEntity();
        if (!(player.getCommandSenderWorld() instanceof ServerWorld)) {
            return;
        }
        final ServerWorld world = (ServerWorld)player.getCommandSenderWorld();
        final EffectInstance rampage = player.getEffect(ModEffects.RAMPAGE);
        if (rampage == null) {
            return;
        }
        final AbilityTree abilities = PlayerAbilitiesData.get(world).getAbilities(player);
        final AbilityNode<?, ?> node = abilities.getNodeByName("Rampage");
        if (node.getAbility() instanceof RampageDotAbility && node.isLearned()) {
            final LivingEntity targetEntity = event.getEntityLiving();
            final RampageDotConfig cfg = (RampageDotConfig)node.getAbilityConfig();
            if (cfg == null) {
                Vault.LOGGER.warn("RampageDotConfig was null when trying to apply Dot.");
                return;
            }
            DamageOverTimeHelper.invalidateAll(targetEntity);
            DamageOverTimeHelper.applyDamageOverTime(targetEntity, (DamageSource)PlayerDamageOverTimeSource.causeDoTDamage(player), event.getAmount(), cfg.getDotSecondDuration());
            event.setAmount(0.0f);
        }
    }
    
    public static class PlayerDamageOverTimeSource extends EntityDamageSource
    {
        private PlayerDamageOverTimeSource(@Nullable final Entity damageSource) {
            super("player", damageSource);
        }
        
        public static PlayerDamageOverTimeSource causeDoTDamage(final PlayerEntity player) {
            return new PlayerDamageOverTimeSource((Entity)player);
        }
    }
}
