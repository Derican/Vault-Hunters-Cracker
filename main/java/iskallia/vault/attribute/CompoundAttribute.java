
package iskallia.vault.attribute;

import net.minecraft.nbt.INBT;
import java.util.function.Supplier;
import java.util.function.Function;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public class CompoundAttribute<T extends INBTSerializable<CompoundNBT>> extends VAttribute.Instance<T> {
    protected Function<CompoundNBT, T> read;

    public CompoundAttribute(final Function<CompoundNBT, T> read) {
        this.read = read;
    }

    public static <T extends INBTSerializable<CompoundNBT>> CompoundAttribute<T> of(final Supplier<T> supplier) {
        return new CompoundAttribute<T>(nbt -> {
            final T value = supplier.get();
            value.deserializeNBT((INBT) nbt);
            return value;
        });
    }

    @Override
    public void write(final CompoundNBT nbt) {
        if (this.getBaseValue() != null) {
            nbt.put("BaseValue", this.getBaseValue().serializeNBT());
        }
    }

    @Override
    public void read(final CompoundNBT nbt) {
        if (nbt.contains("BaseValue", 10)) {
            this.setBaseValue(this.read.apply(nbt.getCompound("BaseValue")));
        }
    }
}
