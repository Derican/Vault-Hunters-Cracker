
package iskallia.vault.skill.ability.config;

import iskallia.vault.init.ModEffects;
import com.google.gson.annotations.Expose;

public class GhostWalkConfig extends EffectConfig {
    @Expose
    private final int durationTicks;

    public GhostWalkConfig(final int cost, final int level, final int durationTicks) {
        super(cost, ModEffects.GHOST_WALK, level, Type.ICON_ONLY, Behavior.RELEASE_TO_PERFORM);
        this.durationTicks = durationTicks;
    }

    public int getDurationTicks() {
        return this.durationTicks;
    }
}
