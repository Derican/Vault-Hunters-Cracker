
package iskallia.vault.container.slot;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraft.item.ItemStack;
import java.util.function.BiPredicate;
import net.minecraftforge.items.SlotItemHandler;

public class ConditionalReadSlot extends SlotItemHandler {
    private final BiPredicate<Integer, ItemStack> slotPredicate;

    public ConditionalReadSlot(final IItemHandler inventory, final int index, final int xPosition, final int yPosition,
            final BiPredicate<Integer, ItemStack> slotPredicate) {
        super(inventory, index, xPosition, yPosition);
        this.slotPredicate = slotPredicate;
    }

    public boolean mayPlace(final ItemStack stack) {
        return this.slotPredicate.test(this.getSlotIndex(), stack);
    }

    public boolean mayPickup(final PlayerEntity playerIn) {
        return this.slotPredicate.test(this.getSlotIndex(), this.getItem());
    }
}
