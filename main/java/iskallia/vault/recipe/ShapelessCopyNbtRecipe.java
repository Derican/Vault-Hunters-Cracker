// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.recipe;

import net.minecraft.item.crafting.IRecipe;
import java.util.Iterator;
import net.minecraft.network.PacketBuffer;
import com.google.gson.JsonArray;
import net.minecraft.item.crafting.ShapedRecipe;
import com.google.gson.JsonParseException;
import net.minecraft.util.JSONUtils;
import com.google.gson.JsonObject;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.crafting.ShapelessRecipe;

public class ShapelessCopyNbtRecipe extends ShapelessRecipe
{
    public ShapelessCopyNbtRecipe(final ResourceLocation idIn, final String groupIn, final ItemStack recipeOutputIn, final NonNullList<Ingredient> recipeItemsIn) {
        super(idIn, groupIn, recipeOutputIn, (NonNullList)recipeItemsIn);
    }
    
    public ItemStack assemble(final CraftingInventory inv) {
        ItemStack input = ItemStack.EMPTY;
        for (int i = 0; i < inv.getContainerSize(); ++i) {
            final ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty()) {
                input = stack;
                break;
            }
        }
        if (input.isEmpty()) {
            return this.getResultItem();
        }
        final ItemStack out = this.getResultItem();
        out.setTag(input.getTag());
        return out;
    }
    
    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<ShapelessCopyNbtRecipe>
    {
        public ShapelessCopyNbtRecipe read(final ResourceLocation recipeId, final JsonObject json) {
            final String s = JSONUtils.getAsString(json, "group", "");
            final NonNullList<Ingredient> nonnulllist = readIngredients(JSONUtils.getAsJsonArray(json, "ingredients"));
            if (nonnulllist.isEmpty()) {
                throw new JsonParseException("No ingredients for shapeless recipe");
            }
            if (nonnulllist.size() > 1) {
                throw new JsonParseException("Too many ingredients for blank nbt copy recipe. The max is 1");
            }
            final ItemStack itemstack = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "result"));
            return new ShapelessCopyNbtRecipe(recipeId, s, itemstack, nonnulllist);
        }
        
        private static NonNullList<Ingredient> readIngredients(final JsonArray ingredientArray) {
            final NonNullList<Ingredient> nonnulllist = (NonNullList<Ingredient>)NonNullList.create();
            for (int i = 0; i < ingredientArray.size(); ++i) {
                final Ingredient ingredient = Ingredient.fromJson(ingredientArray.get(i));
                if (!ingredient.isEmpty()) {
                    nonnulllist.add((Object)ingredient);
                }
            }
            return nonnulllist;
        }
        
        public ShapelessCopyNbtRecipe read(final ResourceLocation recipeId, final PacketBuffer buffer) {
            final String s = buffer.readUtf(32767);
            final int i = buffer.readVarInt();
            final NonNullList<Ingredient> nonnulllist = (NonNullList<Ingredient>)NonNullList.withSize(i, (Object)Ingredient.EMPTY);
            for (int j = 0; j < nonnulllist.size(); ++j) {
                nonnulllist.set(j, (Object)Ingredient.fromNetwork(buffer));
            }
            final ItemStack itemstack = buffer.readItem();
            return new ShapelessCopyNbtRecipe(recipeId, s, itemstack, nonnulllist);
        }
        
        public void write(final PacketBuffer buffer, final ShapelessCopyNbtRecipe recipe) {
            buffer.writeUtf(recipe.getGroup());
            buffer.writeVarInt(recipe.getIngredients().size());
            for (final Ingredient ingredient : recipe.getIngredients()) {
                ingredient.toNetwork(buffer);
            }
            buffer.writeItem(recipe.getResultItem());
        }
    }
}
