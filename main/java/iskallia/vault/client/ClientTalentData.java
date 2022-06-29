
package iskallia.vault.client;

import java.util.ArrayList;
import iskallia.vault.network.message.KnownTalentsMessage;
import java.util.Iterator;
import javax.annotation.Nullable;
import iskallia.vault.skill.talent.type.PlayerTalent;
import iskallia.vault.skill.talent.TalentGroup;
import javax.annotation.Nonnull;
import java.util.Collections;
import iskallia.vault.skill.talent.TalentNode;
import java.util.List;

public class ClientTalentData {
    private static List<TalentNode<?>> learnedTalents;

    @Nonnull
    public static List<TalentNode<?>> getLearnedTalentNodes() {
        return Collections.unmodifiableList((List<? extends TalentNode<?>>) ClientTalentData.learnedTalents);
    }

    @Nullable
    public static <T extends PlayerTalent> TalentNode<T> getLearnedTalentNode(final TalentGroup<T> talent) {
        return getLearnedTalentNode(talent.getParentName());
    }

    @Nullable
    public static <T extends PlayerTalent> TalentNode<T> getLearnedTalentNode(final String talentName) {
        for (final TalentNode<?> node : getLearnedTalentNodes()) {
            if (node.getGroup().getParentName().equals(talentName)) {
                return (TalentNode<T>) node;
            }
        }
        return null;
    }

    public static void updateTalents(final KnownTalentsMessage pkt) {
        ClientTalentData.learnedTalents = pkt.getLearnedTalents();
    }

    static {
        ClientTalentData.learnedTalents = new ArrayList<TalentNode<?>>();
    }
}
