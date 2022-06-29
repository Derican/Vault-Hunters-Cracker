// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.skill.ability.config.sub;

import net.minecraft.potion.Effect;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Function;
import net.minecraft.util.ResourceLocation;
import java.util.Collection;
import java.util.ArrayList;
import iskallia.vault.skill.ability.config.AbilityConfig;
import com.google.gson.annotations.Expose;
import java.util.List;
import iskallia.vault.skill.ability.config.CleanseConfig;

public class CleanseEffectConfig extends CleanseConfig
{
    @Expose
    private final List<String> possibleEffects;
    @Expose
    private final int effectAmplifier;
    
    public CleanseEffectConfig(final int learningCost, final Behavior behavior, final int cooldown, final int effectAmplifier, final List<String> possibleEffects) {
        super(learningCost, behavior, cooldown);
        this.possibleEffects = new ArrayList<String>();
        this.effectAmplifier = effectAmplifier;
        this.possibleEffects.addAll(possibleEffects);
    }
    
    public static CleanseEffectConfig ofEffectNames(final int learningCost, final Behavior behavior, final int cooldown, final int effectAmplifier, final List<ResourceLocation> possibleEffects) {
        return new CleanseEffectConfig(learningCost, behavior, cooldown, effectAmplifier, (List<String>)possibleEffects.stream().map((Function<? super Object, ?>)ResourceLocation::toString).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()));
    }
    
    public static CleanseEffectConfig ofEffects(final int learningCost, final Behavior behavior, final int cooldown, final int effectAmplifier, final List<Effect> possibleEffects) {
        return new CleanseEffectConfig(learningCost, behavior, cooldown, effectAmplifier, (List<String>)possibleEffects.stream().map(effect -> effect.getRegistryName().toString()).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()));
    }
    
    public List<String> getPossibleEffects() {
        return this.possibleEffects;
    }
    
    public int getEffectAmplifier() {
        return this.effectAmplifier;
    }
}
