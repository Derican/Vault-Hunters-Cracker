// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ ItemStack.class })
public class MixinItemStackClient
{
    @Redirect(method = { "getTooltip" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;hasDisplayName()Z", ordinal = 0))
    public boolean doDisplayNameItalic(final ItemStack stack) {
        return false;
    }
}
