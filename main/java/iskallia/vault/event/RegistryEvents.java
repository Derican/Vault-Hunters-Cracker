
package iskallia.vault.event;

import iskallia.vault.init.ModAttributes;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.Attribute;
import iskallia.vault.init.ModEffects;
import net.minecraft.potion.Effect;
import iskallia.vault.init.ModLootModifiers;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import iskallia.vault.init.ModRecipes;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.tileentity.TileEntityType;
import iskallia.vault.init.ModEntities;
import net.minecraft.entity.EntityType;
import iskallia.vault.init.ModContainers;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.world.gen.feature.Feature;
import iskallia.vault.init.ModFeatures;
import iskallia.vault.init.ModStructures;
import net.minecraft.world.gen.feature.structure.Structure;
import iskallia.vault.init.ModSounds;
import net.minecraft.util.SoundEvent;
import iskallia.vault.init.ModModels;
import net.minecraftforge.client.event.ModelRegistryEvent;
import iskallia.vault.init.ModAbilities;
import iskallia.vault.util.RelicSet;
import iskallia.vault.init.ModItems;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import iskallia.vault.init.ModBlocks;
import iskallia.vault.init.ModConfigs;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvents {
    @SubscribeEvent
    public static void onBlockRegister(final RegistryEvent.Register<Block> event) {
        ModConfigs.registerCompressionConfigs();
        ModBlocks.registerBlocks(event);
    }

    @SubscribeEvent
    public static void onItemRegister(final RegistryEvent.Register<Item> event) {
        ModConfigs.registerCompressionConfigs();
        ModItems.registerItems(event);
        ModBlocks.registerBlockItems(event);
        RelicSet.register();
        ModAbilities.init();
    }

    @SubscribeEvent
    public static void onModelRegister(final ModelRegistryEvent event) {
        ModModels.setupRenderLayers();
        ModModels.ItemProperty.register();
        ModModels.GearModel.register();
        ModModels.SpecialGearModel.register();
        ModModels.SpecialSwordModel.register();
    }

    @SubscribeEvent
    public static void onSoundRegister(final RegistryEvent.Register<SoundEvent> event) {
        ModSounds.registerSounds(event);
        ModSounds.registerSoundTypes();
    }

    @SubscribeEvent
    public static void onStructureRegister(final RegistryEvent.Register<Structure<?>> event) {
        ModStructures.register(event);
        ModFeatures.registerStructureFeatures();
    }

    @SubscribeEvent
    public static void onFeatureRegister(final RegistryEvent.Register<Feature<?>> event) {
        ModFeatures.registerFeatures(event);
    }

    @SubscribeEvent
    public static void onContainerRegister(final RegistryEvent.Register<ContainerType<?>> event) {
        ModContainers.register(event);
    }

    @SubscribeEvent
    public static void onEntityRegister(final RegistryEvent.Register<EntityType<?>> event) {
        ModEntities.register(event);
    }

    @SubscribeEvent
    public static void onTileEntityRegister(final RegistryEvent.Register<TileEntityType<?>> event) {
        ModBlocks.registerTileEntities(event);
    }

    @SubscribeEvent
    public static void onRecipeRegister(final RegistryEvent.Register<IRecipeSerializer<?>> event) {
        ModRecipes.Serializer.register(event);
    }

    @SubscribeEvent
    public static void registerGlobalLootModifierSerializers(
            final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
        ModLootModifiers.registerGlobalModifiers(event);
    }

    @SubscribeEvent
    public static void onEffectRegister(final RegistryEvent.Register<Effect> event) {
        ModEffects.register(event);
    }

    @SubscribeEvent
    public static void onAttributeRegister(final RegistryEvent.Register<Attribute> event) {
        final Attribute attr = Attributes.MAX_HEALTH;
        if (attr instanceof RangedAttribute) {
            ((RangedAttribute) attr).maxValue = Double.MAX_VALUE;
        }
        ModAttributes.register(event);
    }
}
