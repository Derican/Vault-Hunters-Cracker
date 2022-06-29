// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.skill.ability.effect.sub;

import iskallia.vault.skill.ability.config.VeinMinerConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import iskallia.vault.skill.ability.config.sub.VeinMinerDurabilityConfig;
import iskallia.vault.skill.ability.effect.VeinMinerAbility;

public class VeinMinerDurabilityAbility extends VeinMinerAbility<VeinMinerDurabilityConfig>
{
    @Override
    public void damageMiningItem(final ItemStack heldItem, final PlayerEntity player, final VeinMinerDurabilityConfig config) {
        if (VeinMinerDurabilityAbility.rand.nextFloat() >= config.getNoDurabilityUsageChance()) {
            return;
        }
        super.damageMiningItem(heldItem, player, config);
    }
}
