
package iskallia.vault.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import iskallia.vault.entity.VaultBoss;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ EnchantmentHelper.class })
public class MixinEnchantmentHelper {
    @Inject(method = { "getDepthStriderModifier" }, at = { @At("RETURN") }, cancellable = true)
    private static void modifyBossDepthStrider(final LivingEntity entityIn, final CallbackInfoReturnable<Integer> cir) {
        if (entityIn instanceof VaultBoss) {
            cir.setReturnValue((Object) 3);
        }
    }
}
