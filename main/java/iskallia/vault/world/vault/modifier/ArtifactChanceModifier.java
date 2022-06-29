
package iskallia.vault.world.vault.modifier;

import net.minecraft.util.ResourceLocation;
import com.google.gson.annotations.Expose;

public class ArtifactChanceModifier extends TexturedVaultModifier {
    @Expose
    private final float artifactChanceIncrease;

    public ArtifactChanceModifier(final String name, final ResourceLocation icon, final float chanceIncrease) {
        super(name, icon);
        this.artifactChanceIncrease = chanceIncrease;
    }

    public float getArtifactChanceIncrease() {
        return this.artifactChanceIncrease;
    }
}
