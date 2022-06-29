// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.skill.set;

import iskallia.vault.item.gear.VaultGear;
import com.google.gson.annotations.Expose;

public class AssassinSet extends PlayerSet
{
    @Expose
    private float increasedFatalStrikeChance;
    
    public AssassinSet(final float increasedFatalStrikeChance) {
        super(VaultGear.Set.ASSASSIN);
        this.increasedFatalStrikeChance = increasedFatalStrikeChance;
    }
    
    public float getIncreasedFatalStrikeChance() {
        return this.increasedFatalStrikeChance;
    }
}
