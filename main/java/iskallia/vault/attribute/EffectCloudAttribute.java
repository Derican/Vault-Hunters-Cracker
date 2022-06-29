
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
import iskallia.vault.entity.EffectCloudEntity;
import java.util.List;

public class EffectCloudAttribute extends PooledAttribute<List<EffectCloudEntity.Config>> {
    public EffectCloudAttribute() {
    }

    public EffectCloudAttribute(final VAttribute.Modifier<List<EffectCloudEntity.Config>> modifier) {
        super(modifier);
    }

    @Override
    public void write(final CompoundNBT nbt) {
        if (this.getBaseValue() == null) {
            return;
        }
        final CompoundNBT tag = new CompoundNBT();
        final ListNBT effectsList = new ListNBT();
        ((VAttribute.Instance<List>) this).getBaseValue()
                .forEach(effect -> effectsList.add((Object) effect.serializeNBT()));
        tag.put("EffectClouds", (INBT) effectsList);
        nbt.put("BaseValue", (INBT) tag);
    }

    @Override
    public void read(final CompoundNBT nbt) {
        if (!nbt.contains("BaseValue", 10)) {
            ((VAttribute.Instance<ArrayList<EffectCloudEntity.Config>>) this)
                    .setBaseValue(new ArrayList<EffectCloudEntity.Config>());
            return;
        }
        final CompoundNBT tag = nbt.getCompound("BaseValue");
        final ListNBT effectsList = tag.getList("EffectClouds", 10);
        this.setBaseValue(effectsList.stream().map(inbt -> EffectCloudEntity.Config.fromNBT((CompoundNBT) inbt))
                .collect((Collector<? super Object, ?, List<EffectCloudEntity.Config>>) Collectors.toList()));
    }

    public static Generator generator() {
        return new Generator();
    }

    public static Generator.Operator of(final Type type) {
        return new Generator.Operator(type);
    }

    public static class Generator extends PooledAttribute.Generator<List<EffectCloudEntity.Config>, Operator> {
        @Override
        public List<EffectCloudEntity.Config> getDefaultValue(final Random random) {
            return new ArrayList<EffectCloudEntity.Config>();
        }

        public static class Operator extends PooledAttribute.Generator.Operator<List<EffectCloudEntity.Config>> {
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
            public List<EffectCloudEntity.Config> apply(final List<EffectCloudEntity.Config> value,
                    final List<EffectCloudEntity.Config> modifier) {
                if (this.getType() == Type.SET) {
                    return modifier;
                }
                if (this.getType() == Type.MERGE) {
                    final List<EffectCloudEntity.Config> res = new ArrayList<EffectCloudEntity.Config>(value);
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
