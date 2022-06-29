// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.util.text.IFormattableTextComponent;
import iskallia.vault.init.ModConfigs;
import iskallia.vault.util.OverlevelEnchantHelper;
import net.minecraft.item.Items;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ Item.class })
public class MixinItem
{
    @Inject(method = { "getDisplayName" }, cancellable = true, at = { @At("RETURN") })
    public void appendOverlevelPrefix(final ItemStack stack, final CallbackInfoReturnable<ITextComponent> info) {
        if (stack.getItem() == Items.ENCHANTED_BOOK) {
            final int overLevels = OverlevelEnchantHelper.getOverlevels(stack);
            if (overLevels != -1) {
                final IFormattableTextComponent formatted = ModConfigs.OVERLEVEL_ENCHANT.format((ITextComponent)info.getReturnValue(), overLevels);
                if (formatted != null) {
                    info.setReturnValue((Object)formatted);
                    info.cancel();
                }
            }
        }
    }
}
