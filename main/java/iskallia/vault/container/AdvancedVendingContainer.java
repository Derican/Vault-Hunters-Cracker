
package iskallia.vault.container;

import net.minecraft.inventory.container.ClickType;
import iskallia.vault.util.EntityHelper;
import iskallia.vault.item.ItemTraderCore;
import net.minecraft.item.Item;
import java.util.List;
import iskallia.vault.vending.TraderCore;
import net.minecraft.block.BlockState;
import iskallia.vault.container.slot.SellSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import iskallia.vault.block.AdvancedVendingBlock;
import net.minecraft.inventory.container.ContainerType;
import iskallia.vault.init.ModContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.entity.player.PlayerInventory;
import iskallia.vault.container.inventory.AdvancedVendingInventory;
import iskallia.vault.block.entity.AdvancedVendingTileEntity;
import net.minecraft.inventory.container.Container;

public class AdvancedVendingContainer extends Container {
    protected AdvancedVendingTileEntity tileEntity;
    protected AdvancedVendingInventory vendingInventory;
    protected PlayerInventory playerInventory;

    public AdvancedVendingContainer(final int windowId, final World world, final BlockPos pos,
            final PlayerInventory playerInventory, final PlayerEntity player) {
        super((ContainerType) ModContainers.ADVANCED_VENDING_MACHINE_CONTAINER, windowId);
        final BlockState blockState = world.getBlockState(pos);
        this.tileEntity = AdvancedVendingBlock.getAdvancedVendingMachineTile(world, pos, blockState);
        this.playerInventory = playerInventory;
        this.vendingInventory = new AdvancedVendingInventory();
        this.addSlot((Slot) new Slot(this.vendingInventory, 0, 210, 43) {
            public void setChanged() {
                super.setChanged();
                AdvancedVendingContainer.this.vendingInventory.updateRecipe();
            }

            public void onQuickCraft(final ItemStack oldStackIn, final ItemStack newStackIn) {
                super.onQuickCraft(oldStackIn, newStackIn);
                AdvancedVendingContainer.this.vendingInventory.updateRecipe();
            }
        });
        this.addSlot((Slot) new SellSlot((IInventory) this.vendingInventory, 2, 268, 43));
        for (int i1 = 0; i1 < 3; ++i1) {
            for (int k1 = 0; k1 < 9; ++k1) {
                this.addSlot(new Slot((IInventory) playerInventory, k1 + i1 * 9 + 9, 167 + k1 * 18, 86 + i1 * 18));
            }
        }
        for (int j1 = 0; j1 < 9; ++j1) {
            this.addSlot(new Slot((IInventory) playerInventory, j1, 167 + j1 * 18, 144));
        }
    }

    public AdvancedVendingTileEntity getTileEntity() {
        return this.tileEntity;
    }

    public TraderCore getSelectedTrade() {
        return this.vendingInventory.getSelectedCore();
    }

    public void selectTrade(final int index) {
        final List<TraderCore> cores = this.tileEntity.getCores();
        if (index < 0 || index >= cores.size()) {
            return;
        }
        final TraderCore traderCore = cores.get(index);
        this.vendingInventory.updateSelectedCore(this.tileEntity, traderCore);
        this.vendingInventory.updateRecipe();
        this.getTileEntity().updateSkin(traderCore.getName());
        if (this.vendingInventory.getItem(0) != ItemStack.EMPTY) {
            final ItemStack buyStack = this.vendingInventory.removeItemNoUpdate(0);
            this.playerInventory.add(buyStack);
        }
        if (traderCore.getTrade().getTradesLeft() <= 0) {
            return;
        }
        final int slot = this.slotForItem(traderCore.getTrade().getBuy().getItem());
        if (slot != -1) {
            final ItemStack buyStack2 = this.playerInventory.removeItemNoUpdate(slot);
            this.vendingInventory.setItem(0, buyStack2);
        }
    }

    private int slotForItem(final Item item) {
        for (int i = 0; i < this.playerInventory.getContainerSize(); ++i) {
            if (this.playerInventory.getItem(i).getItem() == item) {
                return i;
            }
        }
        return -1;
    }

    public boolean stillValid(final PlayerEntity player) {
        return true;
    }

    public void deselectTrades() {
        if (this.vendingInventory.getItem(0) != ItemStack.EMPTY) {
            final ItemStack buyStack = this.vendingInventory.removeItemNoUpdate(0);
            this.playerInventory.add(buyStack);
        }
        this.vendingInventory.updateSelectedCore(this.tileEntity, null);
    }

    public void ejectCore(final int index) {
        final List<TraderCore> cores = this.tileEntity.getCores();
        if (index < 0 || index >= cores.size()) {
            return;
        }
        this.deselectTrades();
        final TraderCore ejectedCore = this.tileEntity.getCores().remove(index);
        final ItemStack itemStack = ItemTraderCore.getStackFromCore(ejectedCore);
        this.playerInventory.player.drop(itemStack, false, true);
    }

    public ItemStack quickMoveStack(final PlayerEntity playerIn, final int index) {
        return ItemStack.EMPTY;
    }

    public void removed(final PlayerEntity player) {
        super.removed(player);
        final ItemStack buy = this.vendingInventory.getItem(0);
        if (!buy.isEmpty()) {
            EntityHelper.giveItem(player, buy);
        }
    }

    public ItemStack clicked(final int slotId, final int dragType, final ClickType clickType,
            final PlayerEntity player) {
        if (clickType == ClickType.SWAP && slotId == 1) {
            return ItemStack.EMPTY;
        }
        return super.clicked(slotId, dragType, clickType, player);
    }
}
