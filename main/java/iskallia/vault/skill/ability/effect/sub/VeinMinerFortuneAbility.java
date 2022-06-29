
package iskallia.vault.skill.ability.effect.sub;

import iskallia.vault.skill.ability.config.VeinMinerConfig;
import iskallia.vault.util.OverlevelEnchantHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import iskallia.vault.skill.ability.config.sub.VeinMinerFortuneConfig;
import iskallia.vault.skill.ability.effect.VeinMinerAbility;

public class VeinMinerFortuneAbility extends VeinMinerAbility<VeinMinerFortuneConfig> {
    @Override
    protected ItemStack getVeinMiningItem(final PlayerEntity player, final VeinMinerFortuneConfig config) {
        final ItemStack stack = super.getVeinMiningItem(player, config).copy();
        return OverlevelEnchantHelper.increaseFortuneBy(stack, config.getAdditionalFortuneLevel());
    }
}
