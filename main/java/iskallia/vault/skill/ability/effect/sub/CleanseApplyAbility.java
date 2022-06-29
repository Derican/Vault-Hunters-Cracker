
package iskallia.vault.skill.ability.effect.sub;

import iskallia.vault.skill.ability.config.CleanseConfig;
import java.util.Iterator;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.IWorld;
import iskallia.vault.util.EntityHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import java.util.List;
import net.minecraft.entity.player.ServerPlayerEntity;
import iskallia.vault.skill.ability.config.sub.CleanseApplyConfig;
import iskallia.vault.skill.ability.effect.CleanseAbility;

public class CleanseApplyAbility extends CleanseAbility<CleanseApplyConfig> {
    @Override
    protected void removeEffects(final CleanseApplyConfig config, final ServerPlayerEntity player,
            final List<EffectInstance> effects) {
        super.removeEffects(config, player, effects);
        for (final EffectInstance effect : effects) {
            final List<LivingEntity> other = EntityHelper.getNearby((IWorld) player.getCommandSenderWorld(),
                    (Vector3i) player.blockPosition(), (float) config.getApplyRadius(), LivingEntity.class);
            LivingEntity e = null;
            other.removeIf(e -> e instanceof PlayerEntity);
            if (!other.isEmpty()) {
                e = other.get(CleanseApplyAbility.rand.nextInt(other.size()));
                e.addEffect(effect);
            }
        }
    }
}
