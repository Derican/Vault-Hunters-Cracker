// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.config;

import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import java.util.stream.StreamSupport;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Item;
import net.minecraft.entity.EntityType;
import com.google.gson.annotations.Expose;
import iskallia.vault.config.entry.vending.ProductEntry;
import iskallia.vault.util.data.WeightedList;

public class MysteryHostileEggConfig extends Config
{
    @Expose
    public WeightedList<ProductEntry> POOL;
    
    public MysteryHostileEggConfig() {
        this.POOL = new WeightedList<ProductEntry>();
    }
    
    @Override
    public String getName() {
        return "mystery_hostile_egg";
    }
    
    @Override
    protected void reset() {
        this.POOL.add(new ProductEntry(this.getEgg((EntityType<?>)EntityType.ZOMBIE)), 3);
        this.POOL.add(new ProductEntry(this.getEgg((EntityType<?>)EntityType.SKELETON)), 1);
    }
    
    private Item getEgg(final EntityType<?> type) {
        return StreamSupport.stream(SpawnEggItem.eggs().spliterator(), false).filter(eggItem -> type.equals(eggItem.getType((CompoundNBT)null))).findAny().map(eggItem -> eggItem).orElse(Items.AIR);
    }
}
