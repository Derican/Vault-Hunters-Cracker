
package iskallia.vault.skill.ability.config.sub;

import iskallia.vault.skill.ability.config.AbilityConfig;
import com.google.gson.annotations.Expose;
import iskallia.vault.skill.ability.config.CleanseConfig;

public class CleanseHealConfig extends CleanseConfig {
    @Expose
    private final float healthPerEffectRemoved;

    public CleanseHealConfig(final int learningCost, final Behavior behavior, final int cooldown,
            final float healthPerEffectRemoved) {
        super(learningCost, behavior, cooldown);
        this.healthPerEffectRemoved = healthPerEffectRemoved;
    }

    public float getHealthPerEffectRemoved() {
        return this.healthPerEffectRemoved;
    }
}
