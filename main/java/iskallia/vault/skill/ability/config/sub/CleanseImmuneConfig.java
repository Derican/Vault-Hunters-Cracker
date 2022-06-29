
package iskallia.vault.skill.ability.config.sub;

import iskallia.vault.skill.ability.config.AbilityConfig;
import com.google.gson.annotations.Expose;
import iskallia.vault.skill.ability.config.CleanseConfig;

public class CleanseImmuneConfig extends CleanseConfig {
    @Expose
    private final int immunityDuration;

    public CleanseImmuneConfig(final int learningCost, final Behavior behavior, final int cooldown,
            final int duration) {
        super(learningCost, behavior, cooldown);
        this.immunityDuration = duration;
    }

    public int getImmunityDuration() {
        return this.immunityDuration;
    }
}
