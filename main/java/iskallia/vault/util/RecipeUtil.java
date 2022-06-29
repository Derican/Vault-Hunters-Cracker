// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.util;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import org.apache.commons.lang3.ObjectUtils;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.inventory.Inventory;
import javax.annotation.Nonnull;
import net.minecraft.util.IItemProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Tuple;
import java.util.Optional;
import net.minecraft.block.BlockState;
import net.minecraft.world.World;

public class RecipeUtil
{
    @Nonnull
    public static Optional<Tuple<ItemStack, Float>> findSmeltingResult(final World world, final BlockState input) {
        final ItemStack stack = new ItemStack((IItemProvider)input.getBlock());
        if (stack.isEmpty()) {
            return Optional.empty();
        }
        return findSmeltingResult(world, stack);
    }
    
    @Nonnull
    public static Optional<Tuple<ItemStack, Float>> findSmeltingResult(final World world, final ItemStack input) {
        final RecipeManager mgr = world.getRecipeManager();
        final IInventory inv = (IInventory)new Inventory(new ItemStack[] { input });
        final Optional<IRecipe<IInventory>> optRecipe = (Optional<IRecipe<IInventory>>)ObjectUtils.firstNonNull((Object[])new Optional[] { mgr.getRecipeFor(IRecipeType.SMELTING, inv, world), mgr.getRecipeFor(IRecipeType.CAMPFIRE_COOKING, inv, world), mgr.getRecipeFor(IRecipeType.SMOKING, inv, world), Optional.empty() });
        return optRecipe.map(recipe -> {
            final ItemStack smeltResult = recipe.assemble(inv).copy();
            float exp = 0.0f;
            if (recipe instanceof AbstractCookingRecipe) {
                exp = ((AbstractCookingRecipe)recipe).getExperience();
            }
            return new Tuple((Object)smeltResult, (Object)exp);
        });
    }
}
