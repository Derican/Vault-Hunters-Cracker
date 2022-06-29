
package iskallia.vault.util.calc;

import iskallia.vault.skill.talent.TalentTree;
import iskallia.vault.client.ClientTalentData;
import iskallia.vault.world.data.PlayerTalentsData;
import net.minecraft.entity.player.ServerPlayerEntity;
import java.util.Optional;
import iskallia.vault.skill.talent.type.PlayerTalent;
import iskallia.vault.skill.talent.TalentGroup;
import iskallia.vault.skill.set.GolemSet;
import iskallia.vault.skill.talent.type.ResistanceTalent;
import java.util.Iterator;
import iskallia.vault.skill.set.SetTree;
import iskallia.vault.skill.set.NinjaSet;
import iskallia.vault.skill.set.SetNode;
import iskallia.vault.world.data.PlayerSetsData;
import net.minecraft.world.server.ServerWorld;
import iskallia.vault.skill.talent.type.ParryTalent;
import java.util.function.Function;
import iskallia.vault.skill.talent.TalentNode;
import iskallia.vault.init.ModConfigs;
import net.minecraft.entity.player.PlayerEntity;

public class AttributeLimitHelper {
    public static float getCooldownReductionLimit(final PlayerEntity player) {
        return 0.8f;
    }

    public static float getParryLimit(final PlayerEntity player) {
        float limit = 0.4f;
        limit += getTalent(player, ModConfigs.TALENTS.PARRY)
                .map((Function<? super TalentNode<ParryTalent>, ?>) TalentNode::getTalent)
                .map((Function<? super Object, ? extends Float>) ParryTalent::getAdditionalParryLimit).orElse(0.0f);
        final SetTree sets = PlayerSetsData.get((ServerWorld) player.level).getSets(player);
        for (final SetNode<?> node : sets.getNodes()) {
            if (!(node.getSet() instanceof NinjaSet)) {
                continue;
            }
            final NinjaSet set = (NinjaSet) node.getSet();
            limit += set.getBonusParryCap();
        }
        return limit;
    }

    public static float getResistanceLimit(final PlayerEntity player) {
        float limit = 0.3f;
        limit += getTalent(player, ModConfigs.TALENTS.RESISTANCE)
                .map((Function<? super TalentNode<ResistanceTalent>, ?>) TalentNode::getTalent)
                .map((Function<? super Object, ? extends Float>) ResistanceTalent::getAdditionalResistanceLimit)
                .orElse(0.0f);
        final SetTree sets = PlayerSetsData.get((ServerWorld) player.level).getSets(player);
        for (final SetNode<?> node : sets.getNodes()) {
            if (!(node.getSet() instanceof GolemSet)) {
                continue;
            }
            final GolemSet set = (GolemSet) node.getSet();
            limit += set.getBonusResistanceCap();
        }
        return limit;
    }

    private static <T extends PlayerTalent> Optional<TalentNode<T>> getTalent(final PlayerEntity player,
            final TalentGroup<T> talentGroup) {
        if (player instanceof ServerPlayerEntity) {
            final TalentTree talents = PlayerTalentsData.get(((ServerPlayerEntity) player).getLevel())
                    .getTalents(player);
            return Optional.of(talents.getNodeOf(talentGroup));
        }
        return Optional.ofNullable(ClientTalentData.getLearnedTalentNode(talentGroup));
    }
}
