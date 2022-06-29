
package iskallia.vault.skill.set;

import iskallia.vault.item.gear.VaultGear;
import com.google.gson.annotations.Expose;

public class TreasureSet extends PlayerSet {
    @Expose
    private float increasedChestRarity;

    public TreasureSet(final float increasedChestRarity) {
        super(VaultGear.Set.TREASURE);
        this.increasedChestRarity = increasedChestRarity;
    }

    public float getIncreasedChestRarity() {
        return this.increasedChestRarity;
    }
}
