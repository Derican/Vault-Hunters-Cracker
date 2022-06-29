
package iskallia.vault.recipe;

import net.minecraft.inventory.IInventory;
import iskallia.vault.init.ModRecipes;
import net.minecraft.item.crafting.IRecipeSerializer;
import java.util.Optional;
import java.util.Comparator;
import iskallia.vault.block.item.RelicStatueBlockItem;
import net.minecraft.item.ItemStack;
import java.util.Set;
import iskallia.vault.util.RelicSet;
import iskallia.vault.item.RelicPartItem;
import java.util.HashSet;
import net.minecraft.world.World;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.crafting.SpecialRecipe;

public class UnidentifiedGearRecipe extends SpecialRecipe {
    public UnidentifiedGearRecipe(final ResourceLocation id) {
        super(id);
    }

    public boolean matches(final CraftingInventory inv, final World world) {
        RelicSet set = null;
        final Set<RelicPartItem> parts = new HashSet<RelicPartItem>();
        for (int i = 0; i < inv.getContainerSize(); ++i) {
            final ItemStack stack = inv.getItem(i);
            if (stack.getItem() instanceof RelicPartItem) {
                if (set != null && ((RelicPartItem) stack.getItem()).getRelicSet() != set) {
                    return false;
                }
                set = ((RelicPartItem) stack.getItem()).getRelicSet();
                parts.add((RelicPartItem) stack.getItem());
            } else if (!stack.isEmpty()) {
                return false;
            }
        }
        return set != null && parts.size() == set.getItemSet().size();
    }

    public ItemStack getCraftingResult(final CraftingInventory inv) {
        for (int i = 0; i < inv.getContainerSize(); ++i) {
            final ItemStack stack = inv.getItem(i);
            if (stack.getItem() instanceof RelicPartItem) {
                final RelicSet set = ((RelicPartItem) stack.getItem()).getRelicSet();
                return RelicStatueBlockItem.withRelicSet(set);
            }
        }
        return ItemStack.EMPTY;
    }

    public boolean canCraftInDimensions(final int width, final int height) {
        final Optional<RelicSet> min = RelicSet.getAll().stream()
                .min(Comparator.comparingInt(o -> o.getItemSet().size()));
        return min.isPresent() && width * height >= min.get().getItemSet().size();
    }

    public IRecipeSerializer<?> getSerializer() {
        return (IRecipeSerializer<?>) ModRecipes.Serializer.CRAFTING_SPECIAL_RELIC_SET;
    }
}
