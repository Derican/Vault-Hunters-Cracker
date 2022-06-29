// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.world.vault.logic.objective.ancient;

import java.util.Collections;
import iskallia.vault.util.NameProviderPublic;
import iskallia.vault.world.data.EternalsData;
import java.util.List;
import java.util.UUID;
import net.minecraft.server.MinecraftServer;

public class AncientEternalArchive
{
    public static List<String> getAncients(final MinecraftServer server, final UUID playerId) {
        final EternalsData.EternalGroup playerEternals = EternalsData.get(server).getEternals(playerId);
        final List<String> ancients = NameProviderPublic.getVHSMPAssociates();
        Collections.shuffle(ancients);
        ancients.removeIf(ancientRef -> playerEternals.containsOriginalEternal(ancientRef, true));
        return ancients;
    }
}
