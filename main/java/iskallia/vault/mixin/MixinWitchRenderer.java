
package iskallia.vault.mixin;

import iskallia.vault.Vault;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import iskallia.vault.easteregg.Witchskall;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.entity.monster.WitchEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.entity.WitchRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ WitchRenderer.class })
public class MixinWitchRenderer {
    private static final ResourceLocation WITCHSKALL_TEXTURE;

    @Inject(method = { "getEntityTexture" }, at = { @At("HEAD") }, cancellable = true)
    public void getEntityTexture(final WitchEntity entity, final CallbackInfoReturnable<ResourceLocation> ci) {
        if (Witchskall.isWitchskall(entity)) {
            ci.setReturnValue((Object) MixinWitchRenderer.WITCHSKALL_TEXTURE);
        }
    }

    static {
        WITCHSKALL_TEXTURE = Vault.id("textures/entity/witchskall.png");
    }
}
