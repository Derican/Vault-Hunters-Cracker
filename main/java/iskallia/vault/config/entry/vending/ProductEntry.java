// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.config.entry.vending;

import net.minecraft.util.IItemProvider;
import iskallia.vault.vending.Product;
import net.minecraft.nbt.JsonToNBT;
import iskallia.vault.util.MathUtilities;
import net.minecraftforge.registries.ForgeRegistries;
import java.util.Objects;
import net.minecraft.util.ResourceLocation;
import javax.annotation.Nullable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.Item;
import com.google.gson.annotations.Expose;

public class ProductEntry
{
    @Expose
    protected String id;
    @Expose
    protected String nbt;
    @Expose
    protected int amountMin;
    @Expose
    protected int amountMax;
    
    public ProductEntry() {
    }
    
    public ProductEntry(final Item item) {
        this(item, 1, null);
    }
    
    public ProductEntry(final ItemStack stack) {
        this(stack.getItem(), stack.getCount(), stack.getTag());
    }
    
    public ProductEntry(final Item item, final int amount, @Nullable final CompoundNBT nbt) {
        this(item, amount, amount, nbt);
    }
    
    public ProductEntry(final Item item, final int amountMin, final int amountMax, @Nullable final CompoundNBT nbt) {
        this.id = Objects.requireNonNull(item.getRegistryName()).toString();
        this.nbt = ((nbt == null) ? null : nbt.toString());
        this.amountMin = amountMin;
        this.amountMax = amountMax;
    }
    
    public Item getItem() {
        return (Item)ForgeRegistries.ITEMS.getValue(new ResourceLocation(this.id));
    }
    
    public int generateAmount() {
        return MathUtilities.getRandomInt(this.amountMin, this.amountMax);
    }
    
    public CompoundNBT getNBT() {
        if (this.nbt == null) {
            return null;
        }
        try {
            return JsonToNBT.parseTag(this.nbt);
        }
        catch (final Exception e) {
            return null;
        }
    }
    
    public Product toProduct() {
        return new Product(this.getItem(), this.generateAmount(), this.getNBT());
    }
    
    public ItemStack generateItemStack() {
        final ItemStack itemStack = new ItemStack((IItemProvider)this.getItem(), this.generateAmount());
        itemStack.setTag(this.getNBT());
        return itemStack;
    }
}
