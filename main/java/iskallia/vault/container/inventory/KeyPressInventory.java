
package iskallia.vault.container.inventory;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import iskallia.vault.init.ModConfigs;
import iskallia.vault.container.base.RecipeInventory;

public class KeyPressInventory extends RecipeInventory {
    public static final int KEY_SLOT = 0;
    public static final int CLUSTER_SLOT = 1;

    public KeyPressInventory() {
        super(2);
    }

    @Override
    public boolean recipeFulfilled() {
        final Item keyItem = this.getItem(0).getItem();
        final Item clusterItem = this.getItem(1).getItem();
        return !ModConfigs.KEY_PRESS.getResultFor(keyItem, clusterItem).isEmpty();
    }

    @Override
    public ItemStack resultingItemStack() {
        final Item keyItem = this.getItem(0).getItem();
        final Item clusterItem = this.getItem(1).getItem();
        return ModConfigs.KEY_PRESS.getResultFor(keyItem, clusterItem);
    }
}
