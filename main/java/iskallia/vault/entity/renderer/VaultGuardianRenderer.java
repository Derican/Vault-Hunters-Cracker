// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.entity.renderer;

import iskallia.vault.Vault;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.entity.MobEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.entity.PiglinRenderer;

public class VaultGuardianRenderer extends PiglinRenderer
{
    public static final ResourceLocation TEXTURE;
    
    public VaultGuardianRenderer(final EntityRendererManager renderManager) {
        super(renderManager, false);
    }
    
    protected void preRenderCallback(final MobEntity entity, final MatrixStack matrixStack, final float partialTickTime) {
        super.scale((LivingEntity)entity, matrixStack, partialTickTime);
        matrixStack.scale(1.5f, 1.5f, 1.5f);
    }
    
    public ResourceLocation getTextureLocation(final MobEntity entity) {
        return VaultGuardianRenderer.TEXTURE;
    }
    
    static {
        TEXTURE = Vault.id("textures/entity/vault_guardian.png");
    }
}
