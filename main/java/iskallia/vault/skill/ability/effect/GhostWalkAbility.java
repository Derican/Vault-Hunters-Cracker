// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.skill.ability.effect;

import iskallia.vault.skill.ability.config.AbilityConfig;
import javax.annotation.Nullable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.MobEntity;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import iskallia.vault.skill.ability.AbilityNode;
import iskallia.vault.skill.ability.AbilityTree;
import net.minecraft.entity.Entity;
import iskallia.vault.world.data.PlayerAbilitiesData;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import iskallia.vault.init.ModSounds;
import net.minecraft.potion.EffectInstance;
import iskallia.vault.init.ModEffects;
import net.minecraft.entity.player.ServerPlayerEntity;
import iskallia.vault.skill.ability.config.GhostWalkConfig;

public class GhostWalkAbility<C extends GhostWalkConfig> extends AbilityEffect<C>
{
    @Override
    public String getAbilityGroupName() {
        return "Ghost Walk";
    }
    
    @Override
    public boolean onAction(final C config, final ServerPlayerEntity player, final boolean active) {
        if (player.hasEffect(ModEffects.GHOST_WALK)) {
            return false;
        }
        final EffectInstance newEffect = new EffectInstance(config.getEffect(), config.getDurationTicks(), config.getAmplifier(), false, config.getType().showParticles, config.getType().showIcon);
        player.level.playSound((PlayerEntity)player, player.getX(), player.getY(), player.getZ(), ModSounds.GHOST_WALK_SFX, SoundCategory.PLAYERS, 0.2f, 1.0f);
        player.playNotifySound(ModSounds.GHOST_WALK_SFX, SoundCategory.PLAYERS, 0.2f, 1.0f);
        player.addEffect(newEffect);
        return false;
    }
    
    @SubscribeEvent(priority = EventPriority.LOW)
    public void onDamage(final LivingDamageEvent event) {
        final Entity attacker = event.getSource().getEntity();
        if (attacker instanceof ServerPlayerEntity && this.doRemoveWhenDealingDamage()) {
            final ServerPlayerEntity player = (ServerPlayerEntity)attacker;
            final EffectInstance ghostWalk = player.getEffect(ModEffects.GHOST_WALK);
            if (ghostWalk != null) {
                final ServerWorld world = (ServerWorld)player.getCommandSenderWorld();
                final PlayerAbilitiesData data = PlayerAbilitiesData.get(world);
                final AbilityTree abilities = data.getAbilities((PlayerEntity)player);
                final AbilityNode<?, ?> node = abilities.getNodeOf(this);
                if (node.getAbility() == this && node.isLearned()) {
                    player.removeEffect(ModEffects.GHOST_WALK);
                }
            }
        }
    }
    
    @SubscribeEvent(priority = EventPriority.LOW)
    public void onHurt(final LivingHurtEvent event) {
        if (this.isInvulnerable(event.getEntityLiving(), event.getSource())) {
            event.setCanceled(true);
        }
    }
    
    @SubscribeEvent(priority = EventPriority.LOW)
    public void onAttack(final LivingAttackEvent event) {
        if (this.isInvulnerable(event.getEntityLiving(), event.getSource())) {
            event.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public void onTarget(final LivingSetAttackTargetEvent event) {
        if (event.getEntityLiving() instanceof MobEntity && this.isInvulnerable(event.getTarget(), null)) {
            ((MobEntity)event.getEntityLiving()).setTarget((LivingEntity)null);
        }
    }
    
    private boolean isInvulnerable(@Nullable final LivingEntity entity, @Nullable final DamageSource source) {
        if (entity instanceof ServerPlayerEntity) {
            final ServerPlayerEntity player = (ServerPlayerEntity)entity;
            final EffectInstance ghostWalk = player.getEffect(ModEffects.GHOST_WALK);
            if (ghostWalk != null && this.preventsDamage() && (source == null || !source.isBypassInvul())) {
                final ServerWorld world = (ServerWorld)player.getCommandSenderWorld();
                final PlayerAbilitiesData data = PlayerAbilitiesData.get(world);
                final AbilityTree abilities = data.getAbilities((PlayerEntity)player);
                final AbilityNode<?, ?> node = abilities.getNodeOf(this);
                if (node.getAbility() == this && node.isLearned()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    protected boolean preventsDamage() {
        return true;
    }
    
    protected boolean doRemoveWhenDealingDamage() {
        return true;
    }
}
