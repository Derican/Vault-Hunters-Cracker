
package iskallia.vault.skill.ability.effect.sub;

import iskallia.vault.skill.ability.config.SummonEternalConfig;
import iskallia.vault.world.data.EternalsData;
import iskallia.vault.skill.ability.config.sub.SummonEternalCountConfig;
import iskallia.vault.skill.ability.effect.SummonEternalAbility;

public class SummonEternalCountAbility extends SummonEternalAbility<SummonEternalCountConfig> {
    @Override
    protected int getEternalCount(final EternalsData.EternalGroup eternals, final SummonEternalCountConfig config) {
        return super.getEternalCount(eternals, config) + config.getAdditionalCount();
    }
}
