
package iskallia.vault.attribute;

import java.util.Random;
import net.minecraft.nbt.CompoundNBT;

public class LongAttribute extends NumberAttribute<Long> {
    public LongAttribute() {
    }

    public LongAttribute(final VAttribute.Modifier<Long> modifier) {
        super(modifier);
    }

    @Override
    public void write(final CompoundNBT nbt) {
        nbt.putLong("BaseValue", (long) this.getBaseValue());
    }

    @Override
    public void read(final CompoundNBT nbt) {
        this.setBaseValue(nbt.getLong("BaseValue"));
    }

    public static Generator generator() {
        return new Generator();
    }

    public static Generator.Operator of(final Type type) {
        return new Generator.Operator(type);
    }

    public static class Generator extends NumberAttribute.Generator<Long, Operator> {
        @Override
        public Long getDefaultValue(final Random random) {
            return 0L;
        }

        public static Operator of(final Type type) {
            return new Operator(type);
        }

        public static class Operator extends NumberAttribute.Generator.Operator<Long> {
            public Operator(final Type type) {
                super(type);
            }

            @Override
            public Long apply(final Long value, final Long modifier) {
                if (this.getType() == Type.SET) {
                    return modifier;
                }
                if (this.getType() == Type.ADD) {
                    return value + modifier;
                }
                if (this.getType() == Type.MULTIPLY) {
                    return value * modifier;
                }
                return value;
            }
        }
    }
}
