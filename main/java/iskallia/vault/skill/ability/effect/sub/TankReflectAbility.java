// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.skill.ability.effect.sub;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import iskallia.vault.skill.ability.AbilityNode;
import iskallia.vault.skill.ability.AbilityTree;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.LivingEntity;
import iskallia.vault.world.data.PlayerAbilitiesData;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.entity.player.PlayerEntity;
import iskallia.vault.init.ModEffects;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import iskallia.vault.skill.ability.config.sub.TankReflectConfig;
import iskallia.vault.skill.ability.effect.TankAbility;

public class TankReflectAbility extends TankAbility<TankReflectConfig>
{
    @SubscribeEvent
    @Override
    public void onDamage(final LivingDamageEvent event) {
        final EffectInstance tank = event.getEntityLiving().getEffect(ModEffects.TANK);
        if (tank == null) {
            return;
        }
        if (event.getEntityLiving() instanceof PlayerEntity) {
            final PlayerEntity player = (PlayerEntity)event.getEntityLiving();
            if (!player.getCommandSenderWorld().isClientSide() && player.getCommandSenderWorld() instanceof ServerWorld) {
                final ServerWorld world = (ServerWorld)player.getCommandSenderWorld();
                final AbilityTree abilities = PlayerAbilitiesData.get(world).getAbilities(player);
                final AbilityNode<?, ?> tankNode = abilities.getNodeByName("Tank");
                if (tankNode.getAbility() == this && tankNode.isLearned()) {
                    final TankReflectConfig config = (TankReflectConfig)tankNode.getAbilityConfig();
                    final Entity attacker = event.getSource().getEntity();
                    if (attacker instanceof LivingEntity && ((LivingEntity)attacker).getHealth() > 0.0f && TankReflectAbility.rand.nextFloat() < config.getDamageReflectChance()) {
                        final float damage = event.getAmount() * config.getDamageReflectPercent();
                        attacker.hurt(DamageSource.thorns((Entity)player), damage);
                    }
                }
            }
        }
    }
}
