
package iskallia.vault.skill.ability.effect;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import java.util.Random;
import iskallia.vault.skill.ability.config.AbilityConfig;

public abstract class AbilityEffect<C extends AbilityConfig> {
    protected static final Random rand;

    public abstract String getAbilityGroupName();

    public void onAdded(final C config, final PlayerEntity player) {
    }

    public void onRemoved(final C config, final PlayerEntity player) {
    }

    public void onFocus(final C config, final PlayerEntity player) {
    }

    public void onBlur(final C config, final PlayerEntity player) {
    }

    public void onTick(final C config, final PlayerEntity player, final boolean active) {
    }

    public boolean onAction(final C config, final ServerPlayerEntity player, final boolean active) {
        return false;
    }

    static {
        rand = new Random();
    }
}
