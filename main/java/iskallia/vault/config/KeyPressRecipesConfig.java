
package iskallia.vault.config;

import iskallia.vault.config.entry.ItemEntry;
import iskallia.vault.config.entry.SingleItemEntry;
import iskallia.vault.init.ModItems;
import java.util.ArrayList;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.util.IItemProvider;
import iskallia.vault.Vault;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.item.ItemStack;
import java.util.Iterator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.Item;
import com.google.gson.annotations.Expose;
import java.util.List;

public class KeyPressRecipesConfig extends Config {
    @Expose
    private List<Recipe> RECIPES;

    @Override
    public String getName() {
        return "key_press_recipes";
    }

    public List<Recipe> getRecipes() {
        return this.RECIPES;
    }

    public Recipe getRecipeFor(final Item keyItem, final Item clusterItem) {
        final ResourceLocation keyID = keyItem.getRegistryName();
        final ResourceLocation clusterID = clusterItem.getRegistryName();
        if (keyID == null || clusterID == null) {
            return null;
        }
        for (final Recipe recipe : this.getRecipes()) {
            if (recipe.KEY_ITEM.ITEM.equals(keyID.toString())
                    && recipe.CLUSTER_ITEM.ITEM.equals(clusterID.toString())) {
                return recipe;
            }
        }
        return null;
    }

    public ItemStack getResultFor(final Item keyItem, final Item clusterItem) {
        final Recipe recipe = this.getRecipeFor(keyItem, clusterItem);
        if (recipe == null) {
            return ItemStack.EMPTY;
        }
        final ResourceLocation resultID = new ResourceLocation(recipe.RESULT_ITEM.ITEM);
        final Item item = (Item) ForgeRegistries.ITEMS.getValue(resultID);
        if (item == null) {
            Vault.LOGGER.warn("Invalid Key Press recipe result -> {}", (Object) recipe.RESULT_ITEM.ITEM);
            return ItemStack.EMPTY;
        }
        try {
            final ItemStack resultStack = new ItemStack((IItemProvider) item, recipe.RESULT_ITEM.AMOUNT);
            if (recipe.RESULT_ITEM.NBT != null && !recipe.RESULT_ITEM.NBT.isEmpty()) {
                resultStack.setTag(JsonToNBT.parseTag(recipe.RESULT_ITEM.NBT));
            }
            return resultStack;
        } catch (final CommandSyntaxException e) {
            Vault.LOGGER.warn("Malformed NBT at Key Press recipe result -> {} NBT: {}",
                    (Object) recipe.RESULT_ITEM.ITEM, (Object) recipe.RESULT_ITEM.NBT);
            return ItemStack.EMPTY;
        }
    }

    @Override
    protected void reset() {
        this.RECIPES = new ArrayList<Recipe>();
        final Recipe recipe = new Recipe();
        recipe.KEY_ITEM = new SingleItemEntry((IItemProvider) ModItems.BLANK_KEY);
        recipe.CLUSTER_ITEM = new SingleItemEntry((IItemProvider) ModItems.SPARKLETINE_CLUSTER);
        recipe.RESULT_ITEM = new ItemEntry(new ItemStack((IItemProvider) ModItems.SPARKLETINE_KEY));
        this.RECIPES.add(recipe);
    }

    public static class Recipe {
        @Expose
        private SingleItemEntry KEY_ITEM;
        @Expose
        private SingleItemEntry CLUSTER_ITEM;
        @Expose
        private ItemEntry RESULT_ITEM;
    }
}
