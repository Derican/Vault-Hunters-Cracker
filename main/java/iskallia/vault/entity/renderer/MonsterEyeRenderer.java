
package iskallia.vault.entity.renderer;

import iskallia.vault.Vault;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.entity.SlimeRenderer;

public class MonsterEyeRenderer extends SlimeRenderer {
    public static final ResourceLocation TEXTURE;

    public MonsterEyeRenderer(final EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    protected void scale(final SlimeEntity entitylivingbase, final MatrixStack matrixStack,
            final float partialTickTime) {
        super.scale(entitylivingbase, matrixStack, partialTickTime);
        matrixStack.scale(2.0f, 2.0f, 2.0f);
    }

    public ResourceLocation getTextureLocation(final SlimeEntity entity) {
        return MonsterEyeRenderer.TEXTURE;
    }

    static {
        TEXTURE = Vault.id("textures/entity/monster_eye.png");
    }
}
