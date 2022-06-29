// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.container;

import net.minecraft.item.ItemStack;
import iskallia.vault.container.slot.RecipeOutputSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import iskallia.vault.container.base.RecipeInventory;
import net.minecraft.inventory.container.ContainerType;
import iskallia.vault.container.inventory.KeyPressInventory;
import iskallia.vault.init.ModContainers;
import net.minecraft.entity.player.PlayerEntity;
import iskallia.vault.container.base.RecipeContainer;

public class KeyPressContainer extends RecipeContainer
{
    public KeyPressContainer(final int windowId, final PlayerEntity player) {
        super(ModContainers.KEY_PRESS_CONTAINER, windowId, new KeyPressInventory(), player);
    }
    
    @Override
    protected void addInternalInventorySlots() {
        this.addSlot(new Slot((IInventory)this.internalInventory, 0, 27, 47));
        this.addSlot(new Slot((IInventory)this.internalInventory, 1, 76, 47));
        this.addSlot((Slot)new RecipeOutputSlot(this.internalInventory, this.internalInventory.outputSlotIndex(), 134, 47) {
            public ItemStack onTake(final PlayerEntity player, final ItemStack stack) {
                final ItemStack itemStack = super.onTake(player, stack);
                if (!player.level.isClientSide && !itemStack.isEmpty()) {
                    player.level.levelEvent(1030, player.blockPosition(), 0);
                }
                return itemStack;
            }
        });
    }
    
    public boolean stillValid(final PlayerEntity player) {
        return true;
    }
    
    @Override
    public void onResultPicked(final PlayerEntity player, final int index) {
        player.level.levelEvent(1030, player.blockPosition(), 0);
    }
}
