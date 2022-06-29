
package iskallia.vault.attribute;

import java.util.function.Supplier;
import net.minecraft.util.ResourceLocation;

public abstract class VModifier<T, I extends Instance<T>> extends VAttribute<T, I> {
    public VModifier(final ResourceLocation id, final Supplier<I> instance) {
        super(id, instance);
    }

    @Override
    protected String getTagKey() {
        return "Modifiers";
    }

    public abstract T apply(final Instance<T> p0, final T p1);
}
