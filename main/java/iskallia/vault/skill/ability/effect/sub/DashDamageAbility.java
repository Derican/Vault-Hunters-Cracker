
package iskallia.vault.skill.ability.effect.sub;

import iskallia.vault.skill.ability.config.AbilityConfig;
import iskallia.vault.skill.ability.config.DashConfig;
import java.util.Iterator;
import java.util.List;
import iskallia.vault.util.PlayerDamageHelper;
import iskallia.vault.util.ServerScheduler;
import net.minecraft.util.DamageSource;
import iskallia.vault.event.ActiveFlags;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.IWorld;
import iskallia.vault.util.EntityHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import iskallia.vault.skill.ability.config.sub.DashDamageConfig;
import iskallia.vault.skill.ability.effect.DashAbility;

public class DashDamageAbility extends DashAbility<DashDamageConfig> {
    @Override
    public boolean onAction(final DashDamageConfig config, final ServerPlayerEntity player, final boolean active) {
        if (super.onAction(config, player, active)) {
            final List<LivingEntity> other = EntityHelper.getNearby((IWorld) player.getCommandSenderWorld(),
                    (Vector3i) player.blockPosition(), config.getRadiusOfAttack(), LivingEntity.class);
            other.removeIf(e -> e instanceof PlayerEntity);
            final float atk = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE);
            for (final LivingEntity entity : other) {
                ActiveFlags.IS_AOE_ATTACKING
                        .runIfNotSet(() -> entity.hurt(DamageSource.playerAttack((PlayerEntity) player),
                                atk * config.getAttackDamagePercentPerDash()));
                ServerScheduler.INSTANCE.schedule(0, () -> PlayerDamageHelper.applyMultiplier(player, 0.95f,
                        PlayerDamageHelper.Operation.STACKING_MULTIPLY, true, config.getCooldown()));
            }
            return true;
        }
        return false;
    }
}
