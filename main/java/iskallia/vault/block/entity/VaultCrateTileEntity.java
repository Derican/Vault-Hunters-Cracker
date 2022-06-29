
package iskallia.vault.block.entity;

import net.minecraftforge.items.CapabilityItemHandler;
import javax.annotation.Nullable;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import iskallia.vault.block.VaultCrateBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ShulkerBoxBlock;
import javax.annotation.Nonnull;
import net.minecraft.item.ItemStack;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import iskallia.vault.init.ModBlocks;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraft.tileentity.TileEntity;

public class VaultCrateTileEntity extends TileEntity {
    private ItemStackHandler itemHandler;
    private LazyOptional<IItemHandler> handler;

    public VaultCrateTileEntity() {
        super((TileEntityType) ModBlocks.VAULT_CRATE_TILE_ENTITY);
        this.itemHandler = this.createHandler();
        this.handler = (LazyOptional<IItemHandler>) LazyOptional.of(() -> this.itemHandler);
    }

    public void sendUpdates() {
        this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 3);
        this.level.updateNeighborsAt(this.worldPosition, this.getBlockState().getBlock());
        this.setChanged();
    }

    public CompoundNBT save(final CompoundNBT compound) {
        compound.put("inv", (INBT) this.itemHandler.serializeNBT());
        return super.save(compound);
    }

    public void load(final BlockState state, final CompoundNBT nbt) {
        nbt.getCompound("inv").remove("Size");
        this.itemHandler.deserializeNBT(nbt.getCompound("inv"));
        super.load(state, nbt);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(54) {
            protected void onContentsChanged(final int slot) {
                VaultCrateTileEntity.this.sendUpdates();
            }

            public boolean isItemValid(final int slot, @Nonnull final ItemStack stack) {
                return !(Block.byItem(stack.getItem()) instanceof ShulkerBoxBlock)
                        && !(Block.byItem(stack.getItem()) instanceof VaultCrateBlock);
            }

            @Nonnull
            public ItemStack insertItem(final int slot, @Nonnull final ItemStack stack, final boolean simulate) {
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Nonnull
    public <T> LazyOptional<T> getCapability(@Nonnull final Capability<T> cap, @Nullable final Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return (LazyOptional<T>) this.handler.cast();
        }
        return (LazyOptional<T>) super.getCapability((Capability) cap, side);
    }

    public CompoundNBT saveToNbt() {
        return this.itemHandler.serializeNBT();
    }

    public void loadFromNBT(final CompoundNBT nbt) {
        this.itemHandler.deserializeNBT(nbt);
    }
}
