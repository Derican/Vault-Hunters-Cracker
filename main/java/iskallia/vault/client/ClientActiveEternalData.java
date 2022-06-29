
package iskallia.vault.client;

import java.util.LinkedHashSet;
import java.util.Iterator;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.HashSet;
import iskallia.vault.network.message.ActiveEternalMessage;
import java.util.Collections;
import iskallia.vault.entity.eternal.ActiveEternalData;
import java.util.Set;

public class ClientActiveEternalData {
    private static final Set<ActiveEternalData.ActiveEternal> activeEternals;

    public static Set<ActiveEternalData.ActiveEternal> getActiveEternals() {
        return Collections.unmodifiableSet(
                (Set<? extends ActiveEternalData.ActiveEternal>) ClientActiveEternalData.activeEternals);
    }

    public static void receive(final ActiveEternalMessage message) {
        final Set<ActiveEternalData.ActiveEternal> updatedEternals = message.getActiveEternals();
        final Set<ActiveEternalData.ActiveEternal> processed = new HashSet<ActiveEternalData.ActiveEternal>();
        ClientActiveEternalData.activeEternals.removeIf(activeEternal -> {
            ActiveEternalData.ActiveEternal updated = null;
            updatedEternals.iterator();
            final Iterator iterator;
            while (iterator.hasNext()) {
                final ActiveEternalData.ActiveEternal eternal = iterator.next();
                if (eternal.equals(activeEternal)) {
                    updated = eternal;
                    break;
                }
            }
            if (updated == null) {
                return true;
            } else {
                activeEternal.updateFrom(updated);
                processed.add(updated);
                return false;
            }
        });
        updatedEternals.removeIf(processed::contains);
        ClientActiveEternalData.activeEternals.addAll(updatedEternals);
    }

    public static void clearClientCache() {
        ClientActiveEternalData.activeEternals.clear();
    }

    static {
        activeEternals = new LinkedHashSet<ActiveEternalData.ActiveEternal>();
    }
}
