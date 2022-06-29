
package iskallia.vault.block.entity;

import net.minecraft.util.NonNullList;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import iskallia.vault.init.ModBlocks;

public class VaultTreasureChestTileEntity extends VaultChestTileEntity {
    public VaultTreasureChestTileEntity() {
        super(ModBlocks.VAULT_TREASURE_CHEST_TILE_ENTITY);
        this.setItems(NonNullList.withSize(54, (Object) ItemStack.EMPTY));
    }

    public int getContainerSize() {
        return 54;
    }
}
