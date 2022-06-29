
package iskallia.vault.attribute;

import java.util.Random;
import net.minecraft.nbt.CompoundNBT;

public class DoubleAttribute extends NumberAttribute<Double> {
    public DoubleAttribute() {
    }

    public DoubleAttribute(final VAttribute.Modifier<Double> modifier) {
        super(modifier);
    }

    @Override
    public void write(final CompoundNBT nbt) {
        nbt.putDouble("BaseValue", (double) this.getBaseValue());
    }

    @Override
    public void read(final CompoundNBT nbt) {
        this.setBaseValue(nbt.getDouble("BaseValue"));
    }

    public static Generator generator() {
        return new Generator();
    }

    public static Generator.Operator of(final Type type) {
        return new Generator.Operator(type);
    }

    public static class Generator extends NumberAttribute.Generator<Double, Operator> {
        @Override
        public Double getDefaultValue(final Random random) {
            return 0.0;
        }

        public static class Operator extends NumberAttribute.Generator.Operator<Double> {
            public Operator(final Type type) {
                super(type);
            }

            @Override
            public Double apply(final Double value, final Double modifier) {
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
