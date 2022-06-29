// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.config;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.item.Item;
import java.util.Random;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.IItemProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import iskallia.vault.config.entry.SingleItemEntry;
import java.util.List;

public class LegendaryTreasureRareConfig extends Config
{
    @Expose
    public List<SingleItemEntry> ITEMS;
    
    public LegendaryTreasureRareConfig() {
        this.ITEMS = new ArrayList<SingleItemEntry>();
    }
    
    @Override
    public String getName() {
        return "legendary_treasure_rare";
    }
    
    @Override
    protected void reset() {
        final ItemStack fancyApple = new ItemStack((IItemProvider)Items.APPLE);
        fancyApple.setHoverName((ITextComponent)new StringTextComponent("Fancy Apple"));
        this.ITEMS.add(new SingleItemEntry(fancyApple));
        final ItemStack sword = new ItemStack((IItemProvider)Items.GOLDEN_SWORD);
        sword.enchant(Enchantments.SHARPNESS, 5);
        this.ITEMS.add(new SingleItemEntry(sword));
    }
    
    public ItemStack getRandom() {
        final Random rand = new Random();
        ItemStack stack = ItemStack.EMPTY;
        final SingleItemEntry singleItemEntry = this.ITEMS.get(rand.nextInt(this.ITEMS.size()));
        try {
            final Item item = (Item)ForgeRegistries.ITEMS.getValue(new ResourceLocation(singleItemEntry.ITEM));
            stack = new ItemStack((IItemProvider)item);
            final CompoundNBT nbt = JsonToNBT.parseTag(singleItemEntry.NBT);
            stack.setTag(nbt);
        }
        catch (final Exception e) {
            e.printStackTrace();
        }
        return stack;
    }
}
