// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.config;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.Items;
import com.google.gson.annotations.Expose;
import iskallia.vault.config.entry.vending.ProductEntry;
import iskallia.vault.util.data.WeightedList;

public class MysteryBoxConfig extends Config
{
    @Expose
    public WeightedList<ProductEntry> POOL;
    
    public MysteryBoxConfig() {
        this.POOL = new WeightedList<ProductEntry>();
    }
    
    @Override
    public String getName() {
        return "mystery_box";
    }
    
    @Override
    protected void reset() {
        this.POOL.add(new ProductEntry(Items.APPLE, 8, null), 3);
        this.POOL.add(new ProductEntry(Items.GOLDEN_APPLE, 1, null), 1);
    }
}
