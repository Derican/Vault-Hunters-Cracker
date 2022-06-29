// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.skill.ability.effect.sub;

import iskallia.vault.skill.ability.config.AbilityConfig;
import iskallia.vault.skill.ability.config.MegaJumpConfig;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.IWorld;
import iskallia.vault.util.EntityHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import iskallia.vault.skill.ability.config.sub.MegaJumpKnockbackConfig;
import iskallia.vault.skill.ability.effect.MegaJumpAbility;

public class MegaJumpKnockbackAbility extends MegaJumpAbility<MegaJumpKnockbackConfig>
{
    @Override
    public boolean onAction(final MegaJumpKnockbackConfig config, final ServerPlayerEntity player, final boolean active) {
        if (super.onAction(config, player, active)) {
            final List<LivingEntity> entities = EntityHelper.getNearby((IWorld)player.getCommandSenderWorld(), (Vector3i)player.blockPosition(), config.getRadius(), LivingEntity.class);
            entities.removeIf(e -> e instanceof PlayerEntity);
            for (final LivingEntity entity : entities) {
                double xDiff = player.getX() - entity.getX();
                double zDiff = player.getZ() - entity.getZ();
                if (xDiff * xDiff + zDiff * zDiff < 1.0E-4) {
                    xDiff = (Math.random() - Math.random()) * 0.01;
                    zDiff = (Math.random() - Math.random()) * 0.01;
                }
                entity.knockback(0.4f * config.getKnockbackStrengthMultiplier(), xDiff, zDiff);
            }
            return true;
        }
        return false;
    }
}
