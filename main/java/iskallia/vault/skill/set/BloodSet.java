// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.skill.set;

import iskallia.vault.item.gear.VaultGear;
import com.google.gson.annotations.Expose;

public class BloodSet extends PlayerSet
{
    public static int MULTIPLIER_ID;
    @Expose
    private float damageMultiplier;
    
    public BloodSet(final float damageMultiplier) {
        super(VaultGear.Set.BLOOD);
        this.damageMultiplier = damageMultiplier;
    }
    
    public float getDamageMultiplier() {
        return this.damageMultiplier;
    }
    
    static {
        BloodSet.MULTIPLIER_ID = -2;
    }
}
