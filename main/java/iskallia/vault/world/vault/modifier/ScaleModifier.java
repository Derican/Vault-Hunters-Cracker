
package iskallia.vault.world.vault.modifier;

import net.minecraft.util.ResourceLocation;
import com.google.gson.annotations.Expose;

public class ScaleModifier extends TexturedVaultModifier {
    @Expose
    private final float scale;

    public ScaleModifier(final String name, final ResourceLocation icon, final float scale) {
        super(name, icon);
        this.scale = scale;
    }

    public float getScale() {
        return this.scale;
    }
}
