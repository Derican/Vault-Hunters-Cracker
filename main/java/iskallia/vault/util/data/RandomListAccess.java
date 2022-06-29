
package iskallia.vault.util.data;

import java.util.function.BiConsumer;
import java.util.Optional;
import javax.annotation.Nullable;
import java.util.Random;

public interface RandomListAccess<T> {
    @Nullable
    T getRandom(final Random p0);

    default Optional<T> getOptionalRandom(final Random random) {
        return Optional.ofNullable(this.getRandom(random));
    }

    void forEach(final BiConsumer<T, Number> p0);

    boolean removeEntry(final T p0);

    @Nullable
    default T removeRandom(final Random random) {
        final T element = this.getRandom(random);
        if (element != null) {
            this.removeEntry(element);
            return element;
        }
        return null;
    }
}
