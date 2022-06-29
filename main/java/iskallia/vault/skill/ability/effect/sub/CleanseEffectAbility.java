// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.skill.ability.effect.sub;

import net.minecraft.potion.Effect;
import iskallia.vault.skill.ability.config.CleanseConfig;
import java.util.Iterator;
import net.minecraft.entity.player.PlayerEntity;
import iskallia.vault.skill.talent.type.EffectTalent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.potion.EffectInstance;
import java.util.List;
import net.minecraft.entity.player.ServerPlayerEntity;
import iskallia.vault.skill.ability.config.sub.CleanseEffectConfig;
import iskallia.vault.skill.ability.effect.CleanseAbility;

public class CleanseEffectAbility extends CleanseAbility<CleanseEffectConfig>
{
    @Override
    protected void removeEffects(final CleanseEffectConfig config, final ServerPlayerEntity player, final List<EffectInstance> effects) {
        super.removeEffects(config, player, effects);
        final List<String> addEffects = config.getPossibleEffects();
        if (!addEffects.isEmpty()) {
            for (final EffectInstance ignored : effects) {
                final String effectStr = addEffects.get(CleanseEffectAbility.rand.nextInt(addEffects.size()));
                Registry.MOB_EFFECT.getOptional(new ResourceLocation(effectStr)).ifPresent(effect -> {
                    final EffectTalent.CombinedEffects grantedEffects = EffectTalent.getEffectData((PlayerEntity)player, player.getLevel(), effect);
                    if (grantedEffects.getDisplayEffect() != null && grantedEffects.getAmplifier() >= 0) {
                        final EffectTalent.Type type = grantedEffects.getDisplayEffect().getType();
                        new EffectInstance(effect, 600, grantedEffects.getAmplifier() + config.getEffectAmplifier() + 1, false, type.showParticles, type.showIcon);
                    }
                    else {
                        player.addEffect(new EffectInstance(effect, 600, config.getEffectAmplifier(), false, false, true));
                    }
                });
            }
        }
    }
}
