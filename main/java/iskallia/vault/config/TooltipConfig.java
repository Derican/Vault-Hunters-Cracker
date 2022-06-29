// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.config;

import iskallia.vault.init.ModItems;
import java.util.function.Function;
import java.util.Optional;
import net.minecraft.item.Item;
import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import java.util.List;

public class TooltipConfig extends Config
{
    @Expose
    private final List<TooltipEntry> tooltips;
    
    public TooltipConfig() {
        this.tooltips = new ArrayList<TooltipEntry>();
    }
    
    public Optional<String> getTooltipString(final Item item) {
        final String itemRegistryName = item.getRegistryName().toString();
        return this.tooltips.stream().filter(entry -> entry.item.equals(itemRegistryName)).map((Function<? super Object, ? extends String>)TooltipEntry::getValue).findFirst();
    }
    
    @Override
    public String getName() {
        return "tooltip";
    }
    
    @Override
    protected void reset() {
        this.tooltips.clear();
        this.tooltips.add(new TooltipEntry(ModItems.POISONOUS_MUSHROOM.getRegistryName().toString(), "Rare - Crafting ingredient for Mystery Stews and Burgers"));
    }
    
    public static class TooltipEntry
    {
        @Expose
        private String item;
        @Expose
        private String value;
        
        public TooltipEntry(final String item, final String value) {
            this.item = item;
            this.value = value;
        }
        
        public String getItem() {
            return this.item;
        }
        
        public String getValue() {
            return this.value;
        }
    }
}
