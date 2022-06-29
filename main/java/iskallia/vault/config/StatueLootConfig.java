// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.config;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.item.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.IItemProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import iskallia.vault.util.StatueType;
import iskallia.vault.config.entry.StatueDecay;
import iskallia.vault.config.entry.SingleItemEntry;
import iskallia.vault.util.data.WeightedList;
import java.util.HashMap;
import com.google.gson.annotations.Expose;

public class StatueLootConfig extends Config
{
    @Expose
    private int MAX_ACCELERATION_CHIPS;
    @Expose
    private HashMap<Integer, Integer> INTERVAL_DECREASE_PER_CHIP;
    @Expose
    private WeightedList<SingleItemEntry> GIFT_NORMAL_STATUE_LOOT;
    @Expose
    private int GIFT_NORMAL_STATUE_INTERVAL;
    @Expose
    private StatueDecay GIFT_NORMAL_DECAY;
    @Expose
    private WeightedList<SingleItemEntry> GIFT_MEGA_STATUE_LOOT;
    @Expose
    private int GIFT_MEGA_STATUE_INTERVAL;
    @Expose
    private StatueDecay GIFT_MEGA_DECAY;
    @Expose
    private WeightedList<SingleItemEntry> VAULT_BOSS_STATUE_LOOT;
    @Expose
    private int VAULT_BOSS_STATUE_INTERVAL;
    @Expose
    private StatueDecay VAULT_BOSS_DECAY;
    @Expose
    private WeightedList<SingleItemEntry> OMEGA_STATUE_LOOT;
    @Expose
    private int OMEGA_STATUE_INTERVAL;
    
    public StatueLootConfig() {
        this.INTERVAL_DECREASE_PER_CHIP = new HashMap<Integer, Integer>();
    }
    
    @Override
    public String getName() {
        return "statue_loot";
    }
    
    public int getDecay(final StatueType type) {
        switch (type) {
            case GIFT_NORMAL: {
                return this.GIFT_NORMAL_DECAY.getDecay();
            }
            case GIFT_MEGA: {
                return this.GIFT_MEGA_DECAY.getDecay();
            }
            case VAULT_BOSS: {
                return this.VAULT_BOSS_DECAY.getDecay();
            }
            default: {
                return -1;
            }
        }
    }
    
    @Override
    protected void reset() {
        this.MAX_ACCELERATION_CHIPS = 4;
        this.INTERVAL_DECREASE_PER_CHIP.put(1, 50);
        this.INTERVAL_DECREASE_PER_CHIP.put(2, 100);
        this.INTERVAL_DECREASE_PER_CHIP.put(3, 200);
        this.INTERVAL_DECREASE_PER_CHIP.put(4, 500);
        this.GIFT_NORMAL_STATUE_LOOT = new WeightedList<SingleItemEntry>();
        final ItemStack fancyApple = new ItemStack((IItemProvider)Items.APPLE);
        fancyApple.setHoverName((ITextComponent)new StringTextComponent("Fancy Apple"));
        this.GIFT_NORMAL_STATUE_LOOT.add(new WeightedList.Entry<SingleItemEntry>(new SingleItemEntry(fancyApple), 1));
        ItemStack sword = new ItemStack((IItemProvider)Items.WOODEN_SWORD);
        sword.enchant(Enchantments.SHARPNESS, 10);
        this.GIFT_NORMAL_STATUE_LOOT.add(new WeightedList.Entry<SingleItemEntry>(new SingleItemEntry(sword), 1));
        this.GIFT_NORMAL_STATUE_INTERVAL = 500;
        this.GIFT_NORMAL_DECAY = new StatueDecay(100, 1000);
        this.GIFT_MEGA_STATUE_LOOT = new WeightedList<SingleItemEntry>();
        final ItemStack fancierApple = new ItemStack((IItemProvider)Items.GOLDEN_APPLE);
        fancierApple.setHoverName((ITextComponent)new StringTextComponent("Fancier Apple"));
        this.GIFT_MEGA_STATUE_LOOT.add(new WeightedList.Entry<SingleItemEntry>(new SingleItemEntry(fancierApple), 1));
        sword = new ItemStack((IItemProvider)Items.DIAMOND_SWORD);
        sword.enchant(Enchantments.SHARPNESS, 10);
        this.GIFT_MEGA_STATUE_LOOT.add(new WeightedList.Entry<SingleItemEntry>(new SingleItemEntry(sword), 1));
        this.GIFT_MEGA_STATUE_INTERVAL = 1000;
        this.GIFT_MEGA_DECAY = new StatueDecay(100, 1000);
        this.VAULT_BOSS_STATUE_LOOT = new WeightedList<SingleItemEntry>();
        final ItemStack fanciestApple = new ItemStack((IItemProvider)Items.ENCHANTED_GOLDEN_APPLE);
        fanciestApple.setHoverName((ITextComponent)new StringTextComponent("Fanciest Apple"));
        this.VAULT_BOSS_STATUE_LOOT.add(new WeightedList.Entry<SingleItemEntry>(new SingleItemEntry(fanciestApple), 1));
        sword = new ItemStack((IItemProvider)Items.NETHERITE_SWORD);
        sword.enchant(Enchantments.SHARPNESS, 10);
        this.VAULT_BOSS_STATUE_LOOT.add(new WeightedList.Entry<SingleItemEntry>(new SingleItemEntry(sword), 1));
        this.VAULT_BOSS_STATUE_INTERVAL = 500;
        this.VAULT_BOSS_DECAY = new StatueDecay(100, 1000);
        (this.OMEGA_STATUE_LOOT = new WeightedList<SingleItemEntry>()).add(new WeightedList.Entry<SingleItemEntry>(new SingleItemEntry((IItemProvider)Blocks.STONE), 1));
        this.OMEGA_STATUE_LOOT.add(new WeightedList.Entry<SingleItemEntry>(new SingleItemEntry((IItemProvider)Blocks.COBBLESTONE), 1));
        this.OMEGA_STATUE_LOOT.add(new WeightedList.Entry<SingleItemEntry>(new SingleItemEntry((IItemProvider)Blocks.DIORITE), 1));
        this.OMEGA_STATUE_LOOT.add(new WeightedList.Entry<SingleItemEntry>(new SingleItemEntry((IItemProvider)Blocks.ANDESITE), 1));
        this.OMEGA_STATUE_LOOT.add(new WeightedList.Entry<SingleItemEntry>(new SingleItemEntry((IItemProvider)Blocks.OAK_LOG), 1));
        this.OMEGA_STATUE_INTERVAL = 1000;
    }
    
    public ItemStack randomLoot(final StatueType type) {
        switch (type) {
            case GIFT_NORMAL: {
                return this.getItem(this.GIFT_NORMAL_STATUE_LOOT.getRandom(new Random()));
            }
            case GIFT_MEGA: {
                return this.getItem(this.GIFT_MEGA_STATUE_LOOT.getRandom(new Random()));
            }
            case VAULT_BOSS: {
                return this.getItem(this.VAULT_BOSS_STATUE_LOOT.getRandom(new Random()));
            }
            case OMEGA:
            case OMEGA_VARIANT: {
                return this.getItem(this.OMEGA_STATUE_LOOT.getRandom(new Random()));
            }
            default: {
                throw new InternalError("Unknown Statue variant: " + type);
            }
        }
    }
    
    public List<ItemStack> getOmegaOptions() {
        final List<ItemStack> options = new ArrayList<ItemStack>();
        final WeightedList<SingleItemEntry> entries = this.OMEGA_STATUE_LOOT;
        for (int i = 0; i < 5; ++i) {
            final SingleItemEntry entry = entries.getRandom(new Random());
            entries.remove(entry);
            options.add(this.getItem(entry));
        }
        return options;
    }
    
    public int getInterval(final StatueType type) {
        switch (type) {
            case GIFT_NORMAL: {
                return this.GIFT_NORMAL_STATUE_INTERVAL;
            }
            case GIFT_MEGA: {
                return this.GIFT_MEGA_STATUE_INTERVAL;
            }
            case VAULT_BOSS: {
                return this.VAULT_BOSS_STATUE_INTERVAL;
            }
            case OMEGA:
            case OMEGA_VARIANT: {
                return this.OMEGA_STATUE_INTERVAL;
            }
            default: {
                throw new IllegalArgumentException("Unknown Statue variant: " + type);
            }
        }
    }
    
    public int getMaxAccelerationChips() {
        return this.MAX_ACCELERATION_CHIPS;
    }
    
    private ItemStack getItem(final SingleItemEntry entry) {
        ItemStack stack = ItemStack.EMPTY;
        try {
            final Item item = (Item)ForgeRegistries.ITEMS.getValue(new ResourceLocation(entry.ITEM));
            stack = new ItemStack((IItemProvider)item);
            if (entry.NBT != null) {
                final CompoundNBT nbt = JsonToNBT.parseTag(entry.NBT);
                stack.setTag(nbt);
            }
        }
        catch (final Exception e) {
            e.printStackTrace();
        }
        return stack;
    }
    
    public int getIntervalDecrease(final int chipCount) {
        return this.INTERVAL_DECREASE_PER_CHIP.get(chipCount);
    }
}
