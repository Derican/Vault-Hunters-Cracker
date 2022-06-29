
package iskallia.vault.container.slot;

import net.minecraft.item.ItemStack;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;

public class SellSlot extends Slot {
    public SellSlot(final IInventory inventoryIn, final int index, final int xPosition, final int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    public boolean mayPlace(final ItemStack stack) {
        return false;
    }
}
