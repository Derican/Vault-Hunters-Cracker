
package iskallia.vault.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import iskallia.vault.research.ResearchTree;
import iskallia.vault.research.Restrictions;
import iskallia.vault.research.StageManager;
import net.minecraft.inventory.container.CraftingResultSlot;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.container.Slot;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ Slot.class })
public abstract class MixinSlot {
    @Shadow
    public abstract ItemStack getItem();

    @Shadow
    public abstract boolean hasItem();

    @Inject(method = { "canTakeStack" }, at = { @At("HEAD") }, cancellable = true)
    public void preventRestrictedTake(final PlayerEntity player, final CallbackInfoReturnable<Boolean> cir) {
        final Slot thisSlot = (Slot) this;
        if (!(thisSlot instanceof CraftingResultSlot)) {
            return;
        }
        if (!this.hasItem()) {
            return;
        }
        final ItemStack resultStack = this.getItem();
        final ResearchTree researchTree = StageManager.getResearchTree(player);
        final String restrictedBy = researchTree.restrictedBy(resultStack.getItem(),
                Restrictions.Type.CRAFTABILITY);
        if (restrictedBy != null) {
            cir.setReturnValue((Object) false);
        }
    }
}
