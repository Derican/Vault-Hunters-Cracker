
package iskallia.vault.block.entity;

import net.minecraft.item.ItemStack;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import iskallia.vault.init.ModBlocks;
import iskallia.vault.item.OtherSideData;
import net.minecraft.tileentity.TileEntity;

public class OtherSidePortalTileEntity extends TileEntity {
    private OtherSideData data;

    public OtherSidePortalTileEntity() {
        super((TileEntityType) ModBlocks.OTHER_SIDE_PORTAL_TILE_ENTITY);
    }

    public void sendUpdates() {
        this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 3);
        this.level.updateNeighborsAt(this.worldPosition, this.getBlockState().getBlock());
        this.setChanged();
    }

    public CompoundNBT save(final CompoundNBT compound) {
        if (this.data != null) {
            compound.put("Data", (INBT) this.data.serializeNBT());
        }
        return super.save(compound);
    }

    public void load(final BlockState state, final CompoundNBT nbt) {
        if (nbt.contains("Data", 10)) {
            (this.data = new OtherSideData(null)).deserializeNBT(nbt.getCompound("Data"));
        }
        super.load(state, nbt);
    }

    public OtherSideData getData() {
        return this.data;
    }

    public void setOtherSideData(final OtherSideData data) {
        this.data = data;
        this.setChanged();
    }
}
