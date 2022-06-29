
package iskallia.vault.entity.renderer;

import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import iskallia.vault.entity.EffectCloudEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;

public class EffectCloudRenderer extends EntityRenderer<EffectCloudEntity> {
    public EffectCloudRenderer(final EntityRendererManager manager) {
        super(manager);
    }

    public ResourceLocation getEntityTexture(final EffectCloudEntity entity) {
        return AtlasTexture.LOCATION_BLOCKS;
    }
}
