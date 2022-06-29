
package iskallia.vault.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import iskallia.vault.skill.set.PlayerSet;
import iskallia.vault.item.gear.VaultGear;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

@Mixin({ ElytraLayer.class })
public abstract class MixinElytraLayer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
    public MixinElytraLayer(final IEntityRenderer<T, M> renderer) {
        super((IEntityRenderer) renderer);
    }

    @Inject(method = { "shouldRender" }, at = { @At("HEAD") }, cancellable = true, remap = false)
    public void shouldRender(final ItemStack stack, final T entity, final CallbackInfoReturnable<Boolean> ci) {
        if (PlayerSet.isActive(VaultGear.Set.DRAGON, entity)) {
            ci.setReturnValue((Object) true);
        }
    }
}
