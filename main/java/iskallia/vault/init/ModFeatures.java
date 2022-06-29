// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.init;

import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.StructureFeature;
import iskallia.vault.Vault;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import iskallia.vault.world.gen.decorator.OverworldOreFeature;
import iskallia.vault.world.gen.decorator.BreadcrumbFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.event.RegistryEvent;
import iskallia.vault.world.gen.structure.FinalVaultBossStructure;
import iskallia.vault.world.gen.structure.FinalVaultLobbyStructure;
import iskallia.vault.world.gen.structure.VaultTroveStructure;
import iskallia.vault.world.gen.structure.RaidChallengeStructure;
import iskallia.vault.world.gen.structure.ArchitectEventStructure;
import iskallia.vault.world.gen.structure.VaultStructure;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import iskallia.vault.world.gen.decorator.FinalVaultBossFeature;
import iskallia.vault.world.gen.decorator.FinalVaultLobbyFeature;
import iskallia.vault.world.gen.decorator.VaultTroveFeature;
import iskallia.vault.world.gen.decorator.RaidChallengeFeature;
import iskallia.vault.world.gen.decorator.ArchitectEventFeature;
import iskallia.vault.world.gen.decorator.VaultFeature;

public class ModFeatures
{
    public static VaultFeature VAULT_FEATURE;
    public static ArchitectEventFeature ARCHITECT_EVENT_FEATURE;
    public static RaidChallengeFeature RAID_CHALLENGE_FEATURE;
    public static VaultTroveFeature VAULT_TROVE_FEATURE;
    public static FinalVaultLobbyFeature FINAL_VAULT_LOBBY_FEATURE;
    public static FinalVaultBossFeature FINAL_VAULT_BOSS_FEATURE;
    public static ConfiguredFeature<?, ?> BREADCRUMB_CHEST;
    public static ConfiguredFeature<?, ?> VAULT_ROCK_ORE;
    
    public static void registerStructureFeatures() {
        ModFeatures.VAULT_FEATURE = register("vault", new VaultFeature(ModStructures.VAULT_STAR, new VaultStructure.Config(() -> VaultStructure.Pools.FINAL_START, 11)));
        ModFeatures.ARCHITECT_EVENT_FEATURE = register("architect_event", new ArchitectEventFeature(ModStructures.ARCHITECT_EVENT, new ArchitectEventStructure.Config(() -> ArchitectEventStructure.Pools.START, 1)));
        ModFeatures.RAID_CHALLENGE_FEATURE = register("raid_challenge", new RaidChallengeFeature(ModStructures.RAID_CHALLENGE, new RaidChallengeStructure.Config(() -> RaidChallengeStructure.Pools.START, 1)));
        ModFeatures.VAULT_TROVE_FEATURE = register("trove", new VaultTroveFeature(ModStructures.VAULT_TROVE, new VaultTroveStructure.Config(() -> VaultTroveStructure.Pools.START, 1)));
        ModFeatures.FINAL_VAULT_LOBBY_FEATURE = register("final_vault_lobby", new FinalVaultLobbyFeature(ModStructures.FINAL_VAULT_LOBBY, new FinalVaultLobbyStructure.Config(() -> FinalVaultLobbyStructure.Pools.START, 1)));
        ModFeatures.FINAL_VAULT_BOSS_FEATURE = register("final_vault_boss", new FinalVaultBossFeature(ModStructures.FINAL_VAULT_BOSS, new FinalVaultBossStructure.Config(() -> FinalVaultBossStructure.Pools.START, 1)));
    }
    
    public static void registerFeatures(final RegistryEvent.Register<Feature<?>> event) {
        BreadcrumbFeature.register(event);
        OverworldOreFeature.register(event);
        ModFeatures.BREADCRUMB_CHEST = register("breadcrumb_chest", (net.minecraft.world.gen.feature.ConfiguredFeature<?, ?>)BreadcrumbFeature.INSTANCE.configured((IFeatureConfig)NoFeatureConfig.INSTANCE));
        ModFeatures.VAULT_ROCK_ORE = register("vault_rock_ore", (net.minecraft.world.gen.feature.ConfiguredFeature<?, ?>)OverworldOreFeature.INSTANCE.configured((IFeatureConfig)new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.VAULT_ROCK_ORE.defaultBlockState(), 1)).decorated(Placement.RANGE.configured((IPlacementConfig)new TopSolidRangeConfig(5, 0, 6))).squared());
    }
    
    private static <FC extends IFeatureConfig, F extends Feature<FC>> ConfiguredFeature<FC, F> register(final String name, final ConfiguredFeature<FC, F> feature) {
        return (ConfiguredFeature<FC, F>)WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_FEATURE, Vault.id(name), (Object)feature);
    }
    
    private static <SF extends StructureFeature<FC, F>, FC extends IFeatureConfig, F extends Structure<FC>> SF register(final String name, final SF feature) {
        return (SF)WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE, Vault.id(name), (Object)feature);
    }
}
