// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.skill.ability.effect;

import iskallia.vault.skill.ability.config.AbilityConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import iskallia.vault.init.ModSounds;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import net.minecraft.potion.EffectInstance;
import java.util.List;
import net.minecraft.entity.player.ServerPlayerEntity;
import iskallia.vault.skill.ability.config.CleanseConfig;

public class CleanseAbility<C extends CleanseConfig> extends AbilityEffect<C>
{
    @Override
    public String getAbilityGroupName() {
        return "Cleanse";
    }
    
    @Override
    public boolean onAction(final C config, final ServerPlayerEntity player, final boolean active) {
        final List<EffectInstance> effects = player.getActiveEffects().stream().filter(effect -> !effect.getEffect().isBeneficial()).collect((Collector<? super Object, ?, List<EffectInstance>>)Collectors.toList());
        this.removeEffects(config, player, effects);
        player.level.playSound((PlayerEntity)player, player.getX(), player.getY(), player.getZ(), ModSounds.CLEANSE_SFX, SoundCategory.PLAYERS, 0.2f, 1.0f);
        player.playNotifySound(ModSounds.CLEANSE_SFX, SoundCategory.PLAYERS, 0.2f, 1.0f);
        return true;
    }
    
    protected void removeEffects(final C config, final ServerPlayerEntity player, final List<EffectInstance> effects) {
        effects.forEach(effect -> player.removeEffect(effect.getEffect()));
    }
}
