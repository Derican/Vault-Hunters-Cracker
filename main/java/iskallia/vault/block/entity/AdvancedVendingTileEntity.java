
package iskallia.vault.block.entity;

import iskallia.vault.util.SkinProfile;
import iskallia.vault.init.ModBlocks;

public class AdvancedVendingTileEntity extends VendingMachineTileEntity {
    public AdvancedVendingTileEntity() {
        super(ModBlocks.ADVANCED_VENDING_MACHINE_TILE_ENTITY);
        this.skin = new SkinProfile();
    }

    public void updateSkin(final String name) {
        this.skin.updateSkin(name);
    }
}
