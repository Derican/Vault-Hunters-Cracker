// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.config;

import iskallia.vault.init.ModItems;
import java.util.Iterator;
import java.util.Optional;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.item.Item;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import iskallia.vault.util.data.WeightedList;

public class VaultRuneConfig extends Config
{
    @Expose
    private final WeightedList<String> runeWeights;
    @Expose
    private final List<RuneLevel> runeLevels;
    
    public VaultRuneConfig() {
        this.runeWeights = new WeightedList<String>();
        this.runeLevels = new ArrayList<RuneLevel>();
    }
    
    public Item getRandomRune() {
        return (Item)ForgeRegistries.ITEMS.getValue(new ResourceLocation((String)this.runeWeights.getRandom(VaultRuneConfig.rand)));
    }
    
    public Optional<Integer> getMinimumLevel(final Item item) {
        final String itemRegistryName = item.getRegistryName().toString();
        for (final RuneLevel runeLevel : this.runeLevels) {
            if (runeLevel.item.equals(itemRegistryName)) {
                return Optional.of(runeLevel.minLevel);
            }
        }
        return Optional.empty();
    }
    
    @Override
    public String getName() {
        return "vault_rune";
    }
    
    @Override
    protected void reset() {
        this.runeWeights.clear();
        this.runeWeights.add(ModItems.VAULT_RUNE_MINE.getRegistryName().toString(), 1);
        this.runeWeights.add(ModItems.VAULT_RUNE_PUZZLE.getRegistryName().toString(), 1);
        this.runeWeights.add(ModItems.VAULT_RUNE_DIGSITE.getRegistryName().toString(), 1);
        this.runeWeights.add(ModItems.VAULT_RUNE_CRYSTAL.getRegistryName().toString(), 1);
        this.runeWeights.add(ModItems.VAULT_RUNE_VIEWER.getRegistryName().toString(), 1);
        this.runeWeights.add(ModItems.VAULT_RUNE_VENDOR.getRegistryName().toString(), 1);
        this.runeWeights.add(ModItems.VAULT_RUNE_XMARK.getRegistryName().toString(), 1);
        this.runeLevels.clear();
        this.runeLevels.add(new RuneLevel(ModItems.VAULT_RUNE_VENDOR.getRegistryName().toString(), 250));
    }
    
    public static class RuneLevel
    {
        @Expose
        private String item;
        @Expose
        private int minLevel;
        
        public RuneLevel(final String item, final int minLevel) {
            this.item = item;
            this.minLevel = minLevel;
        }
        
        public String getItem() {
            return this.item;
        }
        
        public int getMinLevel() {
            return this.minLevel;
        }
    }
}
