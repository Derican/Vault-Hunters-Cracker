// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.world.vault.modifier;

import net.minecraft.util.ResourceLocation;
import com.google.gson.annotations.Expose;

public abstract class TexturedVaultModifier extends VaultModifier
{
    @Expose
    private final String icon;
    
    public TexturedVaultModifier(final String name, final ResourceLocation icon) {
        super(name);
        this.icon = icon.toString();
    }
    
    public ResourceLocation getIcon() {
        return new ResourceLocation(this.icon);
    }
}
