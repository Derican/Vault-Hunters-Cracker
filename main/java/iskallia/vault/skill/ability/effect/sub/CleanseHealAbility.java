
package iskallia.vault.skill.ability.effect.sub;

import iskallia.vault.skill.ability.config.CleanseConfig;
import java.util.Iterator;
import net.minecraft.potion.EffectInstance;
import java.util.List;
import net.minecraft.entity.player.ServerPlayerEntity;
import iskallia.vault.skill.ability.config.sub.CleanseHealConfig;
import iskallia.vault.skill.ability.effect.CleanseAbility;

public class CleanseHealAbility extends CleanseAbility<CleanseHealConfig> {
    @Override
    protected void removeEffects(final CleanseHealConfig config, final ServerPlayerEntity player,
            final List<EffectInstance> effects) {
        super.removeEffects(config, player, effects);
        for (final EffectInstance ignored : effects) {
            player.heal(config.getHealthPerEffectRemoved());
        }
    }
}
