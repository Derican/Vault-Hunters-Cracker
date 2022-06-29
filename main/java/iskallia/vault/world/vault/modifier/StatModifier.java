// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.world.vault.modifier;

import net.minecraft.util.ResourceLocation;
import com.google.gson.annotations.Expose;

public class StatModifier extends TexturedVaultModifier
{
    @Expose
    protected Statistic stat;
    @Expose
    protected float multiplier;
    
    public StatModifier(final String name, final ResourceLocation icon, final Statistic stat, final float multiplier) {
        super(name, icon);
        this.stat = stat;
        this.multiplier = multiplier;
    }
    
    public Statistic getStat() {
        return this.stat;
    }
    
    public float getMultiplier() {
        return this.multiplier;
    }
    
    public enum Statistic
    {
        PARRY, 
        RESISTANCE, 
        COOLDOWN_REDUCTION;
    }
}
