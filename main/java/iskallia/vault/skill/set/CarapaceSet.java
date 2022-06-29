
package iskallia.vault.skill.set;

import iskallia.vault.item.gear.VaultGear;
import com.google.gson.annotations.Expose;

public class CarapaceSet extends PlayerSet {
    @Expose
    private final float absorptionPercent;

    public CarapaceSet(final float absorptionPercent) {
        super(VaultGear.Set.CARAPACE);
        this.absorptionPercent = absorptionPercent;
    }

    public float getAbsorptionPercent() {
        return this.absorptionPercent;
    }
}
