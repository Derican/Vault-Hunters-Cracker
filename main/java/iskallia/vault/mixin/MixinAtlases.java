// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import iskallia.vault.block.render.ScavengerChestRenderer;
import iskallia.vault.block.render.VaultChestRenderer;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.renderer.model.RenderMaterial;
import java.util.function.Consumer;
import net.minecraft.client.renderer.Atlases;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ Atlases.class })
public class MixinAtlases
{
    @Inject(method = { "collectAllMaterials" }, at = { @At("RETURN") })
    private static void collectAllMaterials(final Consumer<RenderMaterial> materialConsumer, final CallbackInfo ci) {
        materialConsumer.accept(VaultChestRenderer.NORMAL);
        materialConsumer.accept(VaultChestRenderer.TREASURE);
        materialConsumer.accept(VaultChestRenderer.ALTAR);
        materialConsumer.accept(VaultChestRenderer.COOP);
        materialConsumer.accept(VaultChestRenderer.BONUS);
        materialConsumer.accept(ScavengerChestRenderer.MATERIAL);
    }
}
