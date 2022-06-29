// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.init;

import net.minecraftforge.registries.IForgeRegistryEntry;
import iskallia.vault.Vault;
import net.minecraft.item.crafting.IRecipe;
import java.util.function.Function;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import iskallia.vault.recipe.MysteryEggRecipe;
import iskallia.vault.recipe.ShapelessCopyNbtRecipe;
import iskallia.vault.recipe.NonRaffleCrystalShapedRecipe;
import iskallia.vault.recipe.RelicSetRecipe;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import java.lang.reflect.Field;
import net.minecraft.util.IItemProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraft.potion.PotionBrewing;

public class ModRecipes
{
    public static void initialize() {
        PotionBrewing.CONTAINER_MIXES.removeIf(o -> {
            final Field f = ObfuscationReflectionHelper.findField((Class)o.getClass(), "ingredient");
            try {
                final Ingredient i = (Ingredient)f.get(o);
                if (i.test(new ItemStack((IItemProvider)Items.DRAGON_BREATH))) {
                    return true;
                }
            }
            catch (final Exception ex) {}
            return false;
        });
    }
    
    public static class Serializer
    {
        public static SpecialRecipeSerializer<RelicSetRecipe> CRAFTING_SPECIAL_RELIC_SET;
        public static NonRaffleCrystalShapedRecipe.Serializer NON_RAFFLE_CRYSTAL_SHAPED;
        public static ShapelessCopyNbtRecipe.Serializer COPY_NBT_SHAPELESS;
        public static SpecialRecipeSerializer<MysteryEggRecipe> MYSTERY_EGG_RECIPE;
        
        public static void register(final RegistryEvent.Register<IRecipeSerializer<?>> event) {
            Serializer.CRAFTING_SPECIAL_RELIC_SET = (SpecialRecipeSerializer<RelicSetRecipe>)register(event, "crafting_special_relic_set", new SpecialRecipeSerializer((Function)RelicSetRecipe::new));
            Serializer.NON_RAFFLE_CRYSTAL_SHAPED = register(event, "non_raffle_crystal_shaped", new NonRaffleCrystalShapedRecipe.Serializer());
            Serializer.COPY_NBT_SHAPELESS = register(event, "crafting_shapeless_copy_nbt", new ShapelessCopyNbtRecipe.Serializer());
            Serializer.MYSTERY_EGG_RECIPE = (SpecialRecipeSerializer<MysteryEggRecipe>)register(event, "mystery_egg", new SpecialRecipeSerializer((Function)MysteryEggRecipe::new));
        }
        
        private static <S extends IRecipeSerializer<T>, T extends IRecipe<?>> S register(final RegistryEvent.Register<IRecipeSerializer<?>> event, final String name, final S serializer) {
            serializer.setRegistryName(Vault.id(name));
            event.getRegistry().register((IForgeRegistryEntry)serializer);
            return serializer;
        }
    }
}
