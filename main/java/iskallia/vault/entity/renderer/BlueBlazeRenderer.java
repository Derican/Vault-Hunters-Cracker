
package iskallia.vault.entity.renderer;

import iskallia.vault.Vault;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.entity.BlazeRenderer;

public class BlueBlazeRenderer extends BlazeRenderer {
    public static final ResourceLocation TEXTURE;

    public BlueBlazeRenderer(final EntityRendererManager renderManager) {
        super(renderManager);
    }

    protected void preRenderCallback(final BlazeEntity entitylivingbase, final MatrixStack matrixStack,
            final float partialTickTime) {
        super.scale((LivingEntity) entitylivingbase, matrixStack, partialTickTime);
        matrixStack.scale(2.0f, 2.0f, 2.0f);
    }

    public ResourceLocation getTextureLocation(final BlazeEntity entity) {
        return BlueBlazeRenderer.TEXTURE;
    }

    static {
        TEXTURE = Vault.id("textures/entity/blue_blaze.png");
    }
}
