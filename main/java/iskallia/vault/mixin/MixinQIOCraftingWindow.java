// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import iskallia.vault.research.ResearchTree;
import net.minecraft.item.ItemStack;
import iskallia.vault.research.Restrictions;
import iskallia.vault.research.StageManager;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import mekanism.common.inventory.container.slot.MainInventorySlot;
import mekanism.common.inventory.container.slot.HotBarSlot;
import java.util.List;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import mekanism.common.inventory.slot.CraftingWindowOutputInventorySlot;
import mekanism.common.content.qio.QIOCraftingWindow;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ QIOCraftingWindow.class })
public class MixinQIOCraftingWindow
{
    @Shadow
    @Final
    private CraftingWindowOutputInventorySlot outputSlot;
    
    @Inject(method = { "performCraft(Lnet/minecraft/entity/player/PlayerEntity;Ljava/util/List;Ljava/util/List;)V" }, at = { @At(value = "INVOKE", target = "Lmekanism/common/content/qio/IQIOCraftingWindowHolder;getHolderWorld()Lnet/minecraft/world/World;") }, cancellable = true, remap = false)
    public void preventShiftCrafting(final PlayerEntity player, final List<HotBarSlot> hotBarSlots, final List<MainInventorySlot> mainInventorySlots, final CallbackInfo ci) {
        final ItemStack resultStack = this.outputSlot.getStack().copy();
        final ResearchTree researchTree = StageManager.getResearchTree(player);
        final String restrictedBy = researchTree.restrictedBy(resultStack.getItem(), Restrictions.Type.CRAFTABILITY);
        if (restrictedBy != null) {
            ci.cancel();
        }
    }
    
    @Inject(method = { "performCraft(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;I)Lnet/minecraft/item/ItemStack;" }, at = { @At("HEAD") }, cancellable = true, remap = false)
    public void preventCrafting(final PlayerEntity player, final ItemStack result, final int amountCrafted, final CallbackInfoReturnable<ItemStack> cir) {
        if (result.isEmpty()) {
            return;
        }
        final ResearchTree researchTree = StageManager.getResearchTree(player);
        final String restrictedBy = researchTree.restrictedBy(result.getItem(), Restrictions.Type.CRAFTABILITY);
        if (restrictedBy != null) {
            cir.setReturnValue((Object)ItemStack.EMPTY);
        }
    }
}
