// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.world.vault.modifier;

import net.minecraft.util.ResourceLocation;
import com.google.gson.annotations.Expose;

public class CatalystChanceModifier extends TexturedVaultModifier
{
    @Expose
    private final float catalystChanceIncrease;
    
    public CatalystChanceModifier(final String name, final ResourceLocation icon, final float chanceIncrease) {
        super(name, icon);
        this.catalystChanceIncrease = chanceIncrease;
    }
    
    public float getCatalystChanceIncrease() {
        return this.catalystChanceIncrease;
    }
}
