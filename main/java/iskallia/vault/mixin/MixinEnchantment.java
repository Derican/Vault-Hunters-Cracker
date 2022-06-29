
package iskallia.vault.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import iskallia.vault.item.gear.VaultGear;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.item.ItemStack;
import net.minecraft.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ Enchantment.class })
public abstract class MixinEnchantment {
    @Inject(method = { "canApply" }, at = { @At("HEAD") }, cancellable = true)
    private void canApply(final ItemStack stack, final CallbackInfoReturnable<Boolean> ci) {
        if (stack.getItem() instanceof VaultGear
                && !((VaultGear) stack.getItem()).canApply(stack, (Enchantment) this)) {
            ci.setReturnValue((Object) false);
        }
    }
}
