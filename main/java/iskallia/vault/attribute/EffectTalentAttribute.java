
package iskallia.vault.attribute;

import java.util.Optional;
import java.util.Collection;
import com.google.gson.annotations.Expose;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.ArrayList;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.CompoundNBT;
import iskallia.vault.skill.talent.type.EffectTalent;
import java.util.List;

public class EffectTalentAttribute extends PooledAttribute<List<EffectTalent>> {
    public EffectTalentAttribute() {
    }

    public EffectTalentAttribute(final VAttribute.Modifier<List<EffectTalent>> modifier) {
        super(modifier);
    }

    @Override
    public void write(final CompoundNBT nbt) {
        if (this.getBaseValue() == null) {
            return;
        }
        final CompoundNBT tag = new CompoundNBT();
        final ListNBT effectsList = new ListNBT();
        ((VAttribute.Instance<List>) this).getBaseValue().forEach(effect -> {
            final CompoundNBT effectTag = new CompoundNBT();
            tag.putString("Id", effect.getEffect().getRegistryName().toString());
            tag.putInt("Amplifier", effect.getAmplifier());
            tag.putString("Type", effect.getType().name);
            tag.putString("Operator", effect.getOperator().name);
            effectsList.add((Object) effectTag);
            return;
        });
        tag.put("EffectTalents", (INBT) effectsList);
        nbt.put("BaseValue", (INBT) tag);
    }

    @Override
    public void read(final CompoundNBT nbt) {
        if (!nbt.contains("BaseValue", 10)) {
            ((VAttribute.Instance<ArrayList<EffectTalent>>) this).setBaseValue(new ArrayList<EffectTalent>());
            return;
        }
        final CompoundNBT tag = nbt.getCompound("BaseValue");
        final ListNBT effectsList = tag.getList("EffectTalents", 10);
        this.setBaseValue(effectsList.stream().map(inbt -> (CompoundNBT) inbt)
                .map(compoundNBT -> new EffectTalent(0, tag.getString("Id"), tag.getInt("Amplifier"),
                        tag.getString("Type"), tag.getString("Operator")))
                .collect((Collector<? super Object, ?, List<EffectTalent>>) Collectors.toList()));
    }

    public static Generator generator() {
        return new Generator();
    }

    public static Generator.Operator of(final Type type) {
        return new Generator.Operator(type);
    }

    public static class Generator extends PooledAttribute.Generator<List<EffectTalent>, Operator> {
        @Override
        public List<EffectTalent> getDefaultValue(final Random random) {
            return new ArrayList<EffectTalent>();
        }

        public static class Operator extends PooledAttribute.Generator.Operator<List<EffectTalent>> {
            @Expose
            protected String type;

            public Operator(final Type type) {
                this.type = type.name();
            }

            public Type getType() {
                return Type.getByName(this.type).orElseThrow(() -> {
                    new IllegalStateException("Unknown type \"" + this.type + "\"");
                    return;
                });
            }

            @Override
            public List<EffectTalent> apply(final List<EffectTalent> value, final List<EffectTalent> modifier) {
                if (this.getType() == Type.SET) {
                    return modifier;
                }
                if (this.getType() == Type.MERGE) {
                    final List<EffectTalent> res = new ArrayList<EffectTalent>(value);
                    res.addAll(modifier);
                    return res;
                }
                return value;
            }
        }
    }

    public enum Type {
        SET,
        MERGE;

        public static Optional<Type> getByName(final String name) {
            for (final Type value : values()) {
                if (value.name().equalsIgnoreCase(name)) {
                    return Optional.of(value);
                }
            }
            return Optional.empty();
        }
    }
}
