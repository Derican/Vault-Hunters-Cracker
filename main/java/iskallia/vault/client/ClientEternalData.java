
package iskallia.vault.client;

import java.util.HashMap;
import iskallia.vault.network.message.EternalSyncMessage;
import java.util.ArrayList;
import javax.annotation.Nullable;
import java.util.Iterator;
import iskallia.vault.entity.eternal.EternalDataSnapshot;
import java.util.List;
import java.util.UUID;
import java.util.Map;

public class ClientEternalData {
    private static Map<UUID, List<EternalDataSnapshot>> eternalSnapshots;

    @Nullable
    public static EternalDataSnapshot getSnapshot(final UUID eternalId) {
        for (final UUID playerId : ClientEternalData.eternalSnapshots.keySet()) {
            final List<EternalDataSnapshot> snapshots = ClientEternalData.eternalSnapshots.get(playerId);
            for (final EternalDataSnapshot snapshot : snapshots) {
                if (snapshot.getId().equals(eternalId)) {
                    return snapshot;
                }
            }
        }
        return null;
    }

    public static List<EternalDataSnapshot> getPlayerEternals(final UUID playerId) {
        return ClientEternalData.eternalSnapshots.getOrDefault(playerId, new ArrayList<EternalDataSnapshot>());
    }

    public static void receiveUpdate(final EternalSyncMessage pkt) {
        ClientEternalData.eternalSnapshots = pkt.getEternalData();
    }

    static {
        ClientEternalData.eternalSnapshots = new HashMap<UUID, List<EternalDataSnapshot>>();
    }
}
