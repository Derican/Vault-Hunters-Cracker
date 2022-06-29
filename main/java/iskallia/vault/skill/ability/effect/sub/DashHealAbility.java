// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.skill.ability.effect.sub;

import iskallia.vault.skill.ability.config.AbilityConfig;
import iskallia.vault.skill.ability.config.DashConfig;
import net.minecraft.entity.player.ServerPlayerEntity;
import iskallia.vault.skill.ability.config.sub.DashHealConfig;
import iskallia.vault.skill.ability.effect.DashAbility;

public class DashHealAbility extends DashAbility<DashHealConfig>
{
    @Override
    public boolean onAction(final DashHealConfig config, final ServerPlayerEntity player, final boolean active) {
        if (super.onAction(config, player, active)) {
            player.heal(config.getHealPerDash());
            return true;
        }
        return false;
    }
}
