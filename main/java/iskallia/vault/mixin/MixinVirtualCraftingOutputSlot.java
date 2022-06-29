
package iskallia.vault.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import iskallia.vault.research.ResearchTree;
import net.minecraft.item.ItemStack;
import iskallia.vault.research.Restrictions;
import iskallia.vault.research.StageManager;
import mekanism.common.inventory.container.slot.InventoryContainerSlot;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.entity.player.PlayerEntity;
import mekanism.common.inventory.container.slot.VirtualCraftingOutputSlot;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ VirtualCraftingOutputSlot.class })
public abstract class MixinVirtualCraftingOutputSlot {
    @Inject(method = { "canTakeStack" }, at = { @At("HEAD") }, cancellable = true)
    public void preventRestrictedOutput(final PlayerEntity player, final CallbackInfoReturnable<Boolean> cir) {
        final InventoryContainerSlot thisSlot = (InventoryContainerSlot) this;
        if (!thisSlot.hasItem()) {
            return;
        }
        final ItemStack resultStack = thisSlot.getItem();
        final ResearchTree researchTree = StageManager.getResearchTree(player);
        final String restrictedBy = researchTree.restrictedBy(resultStack.getItem(),
                Restrictions.Type.CRAFTABILITY);
        if (restrictedBy != null) {
            cir.setReturnValue((Object) false);
        }
    }
}
