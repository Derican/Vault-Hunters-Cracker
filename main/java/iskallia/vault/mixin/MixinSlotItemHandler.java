// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import tfar.dankstorage.item.DankItem;
import tfar.dankstorage.inventory.DankSlot;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ SlotItemHandler.class })
public class MixinSlotItemHandler
{
    @Inject(method = { "isItemValid" }, at = { @At("HEAD") }, cancellable = true)
    public void itemValid(final ItemStack stack, final CallbackInfoReturnable<Boolean> cir) {
        final SlotItemHandler itemHandler = (SlotItemHandler)this;
        if (itemHandler instanceof DankSlot && stack.getItem() instanceof DankItem) {
            cir.setReturnValue((Object)false);
        }
    }
}
