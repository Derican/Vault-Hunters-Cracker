// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.world.vault.modifier;

import net.minecraft.util.ResourceLocation;
import com.google.gson.annotations.Expose;

public class DurabilityDamageModifier extends TexturedVaultModifier
{
    @Expose
    protected float durabilityDamageTakenMultiplier;
    
    public DurabilityDamageModifier(final String name, final ResourceLocation icon, final float durabilityDamageTakenMultiplier) {
        super(name, icon);
        this.durabilityDamageTakenMultiplier = durabilityDamageTakenMultiplier;
    }
    
    public float getDurabilityDamageTakenMultiplier() {
        return this.durabilityDamageTakenMultiplier;
    }
}
