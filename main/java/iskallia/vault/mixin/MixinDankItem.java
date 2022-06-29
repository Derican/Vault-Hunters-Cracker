// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.mixin;

import net.minecraft.item.ItemUseContext;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import tfar.dankstorage.utils.Utils;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.util.Hand;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import tfar.dankstorage.item.DankItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ DankItem.class })
public class MixinDankItem
{
    @Inject(method = { "onItemRightClick" }, cancellable = true, at = { @At(value = "INVOKE", shift = At.Shift.BEFORE, target = "net/minecraft/item/ItemStack.copy()Lnet/minecraft/item/ItemStack;") })
    public void onItemRightClick(final World world, final PlayerEntity player, final Hand hand, final CallbackInfoReturnable<ActionResult> result) {
        final ItemStack bag = player.getItemInHand(hand);
        if (Utils.isConstruction(bag)) {
            final ItemStack selectedItem = Utils.getItemStackInSelectedSlot(bag);
            final String registryName = selectedItem.getItem().getRegistryName().toString();
            if (registryName.equalsIgnoreCase("quark:pickarang") || registryName.equalsIgnoreCase("quark:flamerang")) {
                result.setReturnValue((Object)new ActionResult(ActionResultType.FAIL, (Object)player.getItemInHand(hand)));
                result.cancel();
            }
        }
    }
    
    @Inject(method = { "onItemUse" }, cancellable = true, at = { @At(value = "INVOKE", target = "Ltfar/dankstorage/utils/Utils;getHandler(Lnet/minecraft/item/ItemStack;)Ltfar/dankstorage/inventory/PortableDankHandler;") })
    public void onItemUse(final ItemUseContext ctx, final CallbackInfoReturnable<ActionResultType> cir) {
        final ItemStack bag = ctx.getItemInHand();
        if (Utils.isConstruction(bag)) {
            final ItemStack selectedItem = Utils.getItemStackInSelectedSlot(bag);
            final String registryName = selectedItem.getItem().getRegistryName().toString();
            if (registryName.equalsIgnoreCase("quark:pickarang") || registryName.equalsIgnoreCase("quark:flamerang")) {
                cir.setReturnValue((Object)ActionResultType.FAIL);
                cir.cancel();
            }
        }
    }
}
