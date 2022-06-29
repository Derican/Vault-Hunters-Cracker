
package iskallia.vault.recipe;

import net.minecraft.inventory.IInventory;
import iskallia.vault.init.ModRecipes;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.IItemProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import iskallia.vault.init.ModItems;
import net.minecraft.world.World;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.crafting.SpecialRecipe;

public class MysteryEggRecipe extends SpecialRecipe {
    public MysteryEggRecipe(final ResourceLocation id) {
        super(id);
    }

    public boolean matches(final CraftingInventory inv, final World world) {
        int foundEggs = 0;
        int foundAlex = 0;
        for (int i = 0; i < inv.getContainerSize(); ++i) {
            final ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() == ModItems.ALEXANDRITE_GEM) {
                    ++foundAlex;
                }
                if (stack.getItem() instanceof SpawnEggItem) {
                    ++foundEggs;
                }
            }
        }
        return foundEggs == 4 && foundAlex == 1;
    }

    public ItemStack getCraftingResult(final CraftingInventory inv) {
        return new ItemStack((IItemProvider) ModItems.MYSTERY_EGG, 4);
    }

    public boolean canCraftInDimensions(final int width, final int height) {
        return width * height >= 5;
    }

    public IRecipeSerializer<?> getSerializer() {
        return (IRecipeSerializer<?>) ModRecipes.Serializer.MYSTERY_EGG_RECIPE;
    }
}
