// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.skill.talent;

import iskallia.vault.skill.talent.type.AttributeTalent;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import net.minecraft.entity.ai.attributes.Attribute;
import java.util.stream.IntStream;
import java.util.function.IntUnaryOperator;
import iskallia.vault.skill.talent.type.EffectTalent;
import net.minecraft.potion.Effect;
import com.google.common.collect.HashBiMap;
import iskallia.vault.util.RomanNumber;
import com.google.common.collect.BiMap;
import com.google.gson.annotations.Expose;
import iskallia.vault.skill.talent.type.PlayerTalent;

public class TalentGroup<T extends PlayerTalent>
{
    @Expose
    private final String name;
    @Expose
    private final T[] levels;
    private BiMap<String, T> registry;
    
    public TalentGroup(final String name, final T... levels) {
        this.name = name;
        this.levels = levels;
    }
    
    public int getMaxLevel() {
        return this.levels.length;
    }
    
    public String getParentName() {
        return this.name;
    }
    
    public String getName(final int level) {
        if (level == 0) {
            return this.name + " " + RomanNumber.toRoman(0);
        }
        return (String)this.getRegistry().inverse().get(this.getTalent(level));
    }
    
    public T getTalent(final int level) {
        if (level < 0) {
            return this.levels[0];
        }
        if (level >= this.getMaxLevel()) {
            return this.levels[this.getMaxLevel() - 1];
        }
        return this.levels[level - 1];
    }
    
    public int learningCost() {
        return this.levels[0].getCost();
    }
    
    public int cost(final int level) {
        if (level > this.getMaxLevel()) {
            return -1;
        }
        return this.levels[level - 1].getCost();
    }
    
    public BiMap<String, T> getRegistry() {
        if (this.registry == null) {
            this.registry = (BiMap<String, T>)HashBiMap.create(this.getMaxLevel());
            if (this.getMaxLevel() == 1) {
                this.registry.put((Object)this.getParentName(), (Object)this.levels[0]);
            }
            else if (this.getMaxLevel() > 1) {
                for (int i = 0; i < this.getMaxLevel(); ++i) {
                    this.registry.put((Object)(this.getParentName() + " " + RomanNumber.toRoman(i + 1)), this.getTalent(i + 1));
                }
            }
        }
        return this.registry;
    }
    
    public static TalentGroup<EffectTalent> ofEffect(final String name, final Effect effect, final EffectTalent.Type type, final int maxLevel, final IntUnaryOperator cost, final EffectTalent.Operator operator) {
        final EffectTalent[] talents = IntStream.range(0, maxLevel).mapToObj(i -> new EffectTalent(cost.applyAsInt(i + 1), effect, i, type, operator)).toArray(EffectTalent[]::new);
        return new TalentGroup<EffectTalent>(name, talents);
    }
    
    public static TalentGroup<AttributeTalent> ofAttribute(final String name, final Attribute attribute, final String modifierName, final int maxLevel, final IntUnaryOperator cost, final IntToDoubleFunction amount, final IntFunction<AttributeModifier.Operation> operation) {
        final AttributeTalent[] talents = IntStream.range(0, maxLevel).mapToObj(i -> {
            new(iskallia.vault.skill.talent.type.AttributeTalent.class)();
            cost.applyAsInt(i + 1);
            new AttributeTalent.Modifier(modifierName + " " + RomanNumber.toRoman(i + 1), amount.applyAsDouble(i + 1), operation.apply(i + 1));
            final AttributeTalent.Modifier modifier;
            final Object cost2;
            new AttributeTalent((int)cost2, attribute, modifier);
            return;
        }).toArray(AttributeTalent[]::new);
        return new TalentGroup<AttributeTalent>(name, talents);
    }
    
    public static <T extends PlayerTalent> TalentGroup<T> of(final String name, final int maxLevel, final IntFunction<T> supplier) {
        final PlayerTalent[] talents = IntStream.range(0, maxLevel).mapToObj((IntFunction<?>)supplier).toArray(PlayerTalent[]::new);
        return new TalentGroup<T>(name, (T[])talents);
    }
}
