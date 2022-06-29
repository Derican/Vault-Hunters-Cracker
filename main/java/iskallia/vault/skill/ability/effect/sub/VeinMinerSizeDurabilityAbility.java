// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.skill.ability.effect.sub;

import iskallia.vault.skill.ability.config.VeinMinerConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import iskallia.vault.skill.ability.config.sub.VeinMinerSizeDurabilityConfig;
import iskallia.vault.skill.ability.effect.VeinMinerAbility;

public class VeinMinerSizeDurabilityAbility extends VeinMinerAbility<VeinMinerSizeDurabilityConfig>
{
    @Override
    public void damageMiningItem(final ItemStack heldItem, final PlayerEntity player, final VeinMinerSizeDurabilityConfig config) {
        super.damageMiningItem(heldItem, player, config);
        if (VeinMinerSizeDurabilityAbility.rand.nextFloat() < config.getDoubleDurabilityCostChance()) {
            super.damageMiningItem(heldItem, player, config);
        }
    }
}
