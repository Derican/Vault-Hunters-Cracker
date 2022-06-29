// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import iskallia.vault.init.ModAttributes;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.client.renderer.entity.LivingRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ LivingRenderer.class })
public class MixinLivingRenderer
{
    @Inject(method = { "render" }, at = { @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/matrix/MatrixStack;push()V", ordinal = 0) })
    public void render(final LivingEntity entity, final float entityYaw, final float partialTicks, final MatrixStack matrixStack, final IRenderTypeBuffer buffer, final int packedLight, final CallbackInfo ci) {
        final ModifiableAttributeInstance attribute = entity.getAttribute(ModAttributes.SIZE_SCALE);
        if (attribute == null) {
            return;
        }
        final float scale = (float)attribute.getValue();
        matrixStack.scale(scale, scale, scale);
    }
}
