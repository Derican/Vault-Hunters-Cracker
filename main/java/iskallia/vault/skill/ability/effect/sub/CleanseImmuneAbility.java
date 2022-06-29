// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.skill.ability.effect.sub;

import net.minecraft.potion.Effect;
import iskallia.vault.skill.ability.config.CleanseConfig;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import iskallia.vault.skill.ability.AbilityNode;
import iskallia.vault.skill.ability.AbilityTree;
import net.minecraftforge.eventbus.api.Event;
import iskallia.vault.world.data.PlayerAbilitiesData;
import net.minecraftforge.event.entity.living.PotionEvent;
import iskallia.vault.init.ModEffects;
import net.minecraft.entity.player.PlayerEntity;
import java.util.Collections;
import iskallia.vault.world.data.PlayerImmunityData;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.potion.EffectInstance;
import java.util.List;
import net.minecraft.entity.player.ServerPlayerEntity;
import iskallia.vault.skill.ability.config.sub.CleanseImmuneConfig;
import iskallia.vault.skill.ability.effect.CleanseAbility;

public class CleanseImmuneAbility extends CleanseAbility<CleanseImmuneConfig>
{
    @Override
    protected void removeEffects(final CleanseImmuneConfig config, final ServerPlayerEntity player, final List<EffectInstance> effects) {
        if (effects.isEmpty()) {
            return;
        }
        if (!(player.getCommandSenderWorld() instanceof ServerWorld)) {
            return;
        }
        final ServerWorld world = (ServerWorld)player.getCommandSenderWorld();
        final PlayerImmunityData data = PlayerImmunityData.get(world);
        Collections.shuffle(effects);
        data.addEffect((PlayerEntity)player, effects.get(0));
        effects.forEach(effect -> player.removeEffect(effect.getEffect()));
        final EffectInstance activeEffect = player.getEffect(ModEffects.IMMUNITY);
        final EffectInstance newEffect = new EffectInstance(ModEffects.IMMUNITY, config.getImmunityDuration(), 0, false, false, true);
        if (activeEffect == null) {
            player.addEffect(newEffect);
        }
    }
    
    @SubscribeEvent
    public void onEffect(final PotionEvent.PotionApplicableEvent event) {
        if (!(event.getEntityLiving() instanceof PlayerEntity)) {
            return;
        }
        final PlayerEntity player = (PlayerEntity)event.getEntityLiving();
        final EffectInstance immunity = player.getEffect(ModEffects.IMMUNITY);
        if (immunity == null) {
            return;
        }
        final EffectInstance effectInstance = event.getPotionEffect();
        if (effectInstance.getEffect().isBeneficial()) {
            return;
        }
        if (!(player.getCommandSenderWorld() instanceof ServerWorld)) {
            return;
        }
        final ServerWorld world = (ServerWorld)player.getCommandSenderWorld();
        final PlayerAbilitiesData data = PlayerAbilitiesData.get(world);
        final AbilityTree abilities = data.getAbilities(player);
        final AbilityNode<?, ?> node = abilities.getNodeByName("Cleanse");
        if (node.getAbility() == this && node.isLearned()) {
            final PlayerImmunityData immunityData = PlayerImmunityData.get(world);
            if (immunityData.getEffects(player.getUUID()).stream().anyMatch(effect -> effect.equals(effectInstance.getEffect()))) {
                event.setResult(Event.Result.DENY);
            }
        }
    }
    
    @SubscribeEvent
    public void onImmunityRemoved(final PotionEvent.PotionExpiryEvent event) {
        if (!(event.getEntityLiving() instanceof PlayerEntity)) {
            return;
        }
        final PlayerEntity player = (PlayerEntity)event.getEntityLiving();
        if (!(player.getCommandSenderWorld() instanceof ServerWorld)) {
            return;
        }
        final ServerWorld world = (ServerWorld)player.getCommandSenderWorld();
        if (event.getPotionEffect() == null || event.getPotionEffect().getEffect() != ModEffects.IMMUNITY) {
            return;
        }
        PlayerImmunityData.get(world).removeEffects(player);
    }
}
