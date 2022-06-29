
package iskallia.vault.init;

import java.util.function.Supplier;
import net.minecraft.item.Items;
import iskallia.vault.item.gear.applicable.VaultPlateItem;
import iskallia.vault.item.gear.applicable.VaultRepairCoreItem;
import iskallia.vault.item.UnknownEggItem;
import iskallia.vault.world.data.PlayerFavourData;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import iskallia.vault.util.VaultRarity;
import iskallia.vault.Vault;
import net.minecraft.util.IItemProvider;
import iskallia.vault.config.entry.vending.ProductEntry;
import java.util.Random;
import net.minecraft.item.ItemStack;
import com.google.common.collect.ImmutableList;
import java.util.List;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.event.RegistryEvent;
import iskallia.vault.item.FinalVaultKeystoneItem;
import iskallia.vault.item.BurntCrystalItem;
import iskallia.vault.item.VaultCharmUpgrade;
import iskallia.vault.item.FlawedRubyItem;
import iskallia.vault.item.ItemModArmorCrate;
import iskallia.vault.item.ArtisanScrollItem;
import iskallia.vault.item.VoidOrbItem;
import iskallia.vault.item.GatedLootableItem;
import iskallia.vault.item.GodEssenceItem;
import net.minecraft.item.BucketItem;
import iskallia.vault.item.VaultMagnetItem;
import iskallia.vault.item.BasicScavengerItem;
import iskallia.vault.item.VaultPearlItem;
import iskallia.vault.item.InfiniteWaterBucketItem;
import iskallia.vault.item.paxel.VaultPaxelItem;
import iskallia.vault.item.ItemShardPouch;
import iskallia.vault.item.ItemVaultRaffleSeal;
import iskallia.vault.item.ItemVaultCrystalSeal;
import iskallia.vault.item.VaultRuneItem;
import iskallia.vault.item.BasicTooltipItem;
import iskallia.vault.item.VaultInhibitorItem;
import iskallia.vault.item.VaultCatalystItem;
import iskallia.vault.item.WutaxShardItem;
import iskallia.vault.item.consumable.VaultConsumableItem;
import net.minecraft.item.Item;
import iskallia.vault.item.PuzzleRuneItem;
import iskallia.vault.item.gear.IdolItem;
import iskallia.vault.item.gear.EtchingItem;
import iskallia.vault.item.gear.VaultArmorItem;
import iskallia.vault.item.gear.VaultAxeItem;
import iskallia.vault.item.gear.VaultSwordItem;
import iskallia.vault.item.ItemKnowledgeStar;
import iskallia.vault.item.ItemResetFlask;
import iskallia.vault.item.ItemRespecFlask;
import iskallia.vault.item.ItemDrillArrow;
import iskallia.vault.item.LootableItem;
import iskallia.vault.item.VaultStewItem;
import iskallia.vault.item.BasicItem;
import iskallia.vault.item.ItemVaultFruit;
import iskallia.vault.item.ItemSkillShard;
import iskallia.vault.item.ItemSkillOrbFrame;
import iskallia.vault.item.RelicItem;
import iskallia.vault.item.ItemLegendaryTreasure;
import iskallia.vault.item.ItemUnidentifiedArtifact;
import iskallia.vault.item.ItemTraderCore;
import iskallia.vault.item.ItemVaultRune;
import iskallia.vault.item.RelicPartItem;
import iskallia.vault.item.ItemRelicBoosterPack;
import iskallia.vault.item.ItemVaultKey;
import iskallia.vault.item.crystal.VaultCrystalItem;
import iskallia.vault.item.ItemVaultGem;
import iskallia.vault.item.ItemSkillOrb;
import iskallia.vault.item.VaultXPFoodItem;
import net.minecraft.item.ItemGroup;

public class ModItems {
    public static ItemGroup VAULT_MOD_GROUP;
    public static ItemGroup SCAVENGER_GROUP;
    public static VaultXPFoodItem VAULT_BURGER;
    public static VaultXPFoodItem OOZING_PIZZA;
    public static VaultXPFoodItem VAULT_COOKIE;
    public static ItemSkillOrb SKILL_ORB;
    public static ItemVaultGem VAULT_ROCK;
    public static ItemVaultGem ALEXANDRITE_GEM;
    public static ItemVaultGem BENITOITE_GEM;
    public static ItemVaultGem LARIMAR_GEM;
    public static ItemVaultGem BLACK_OPAL_GEM;
    public static ItemVaultGem PAINITE_GEM;
    public static ItemVaultGem ISKALLIUM_GEM;
    public static ItemVaultGem GORGINITE_GEM;
    public static ItemVaultGem SPARKLETINE_GEM;
    public static ItemVaultGem WUTODIE_GEM;
    public static ItemVaultGem ASHIUM_GEM;
    public static ItemVaultGem BOMIGNITE_GEM;
    public static ItemVaultGem FUNSOIDE_GEM;
    public static ItemVaultGem TUBIUM_GEM;
    public static ItemVaultGem UPALINE_GEM;
    public static ItemVaultGem PUFFIUM_GEM;
    public static ItemVaultGem ECHO_GEM;
    public static ItemVaultGem POG;
    public static ItemVaultGem ECHO_POG;
    public static VaultCrystalItem VAULT_CRYSTAL;
    public static ItemVaultKey ISKALLIUM_KEY;
    public static ItemVaultKey GORGINITE_KEY;
    public static ItemVaultKey SPARKLETINE_KEY;
    public static ItemVaultKey ASHIUM_KEY;
    public static ItemVaultKey BOMIGNITE_KEY;
    public static ItemVaultKey FUNSOIDE_KEY;
    public static ItemVaultKey TUBIUM_KEY;
    public static ItemVaultKey UPALINE_KEY;
    public static ItemVaultKey PUFFIUM_KEY;
    public static ItemRelicBoosterPack RELIC_BOOSTER_PACK;
    public static RelicPartItem DRAGON_HEAD_RELIC;
    public static RelicPartItem DRAGON_TAIL_RELIC;
    public static RelicPartItem DRAGON_FOOT_RELIC;
    public static RelicPartItem DRAGON_CHEST_RELIC;
    public static RelicPartItem DRAGON_BREATH_RELIC;
    public static RelicPartItem MINERS_DELIGHT_RELIC;
    public static RelicPartItem MINERS_LIGHT_RELIC;
    public static RelicPartItem PICKAXE_HANDLE_RELIC;
    public static RelicPartItem PICKAXE_HEAD_RELIC;
    public static RelicPartItem PICKAXE_TOOL_RELIC;
    public static RelicPartItem SWORD_BLADE_RELIC;
    public static RelicPartItem SWORD_HANDLE_RELIC;
    public static RelicPartItem SWORD_STICK_RELIC;
    public static RelicPartItem WARRIORS_ARMOUR_RELIC;
    public static RelicPartItem WARRIORS_CHARM_RELIC;
    public static RelicPartItem DIAMOND_ESSENCE_RELIC;
    public static RelicPartItem GOLD_ESSENCE_RELIC;
    public static RelicPartItem MYSTIC_GEM_ESSENCE_RELIC;
    public static RelicPartItem NETHERITE_ESSENCE_RELIC;
    public static RelicPartItem PLATINUM_ESSENCE_RELIC;
    public static RelicPartItem TWITCH_EMOTE1_RELIC;
    public static RelicPartItem TWITCH_EMOTE2_RELIC;
    public static RelicPartItem TWITCH_EMOTE3_RELIC;
    public static RelicPartItem TWITCH_EMOTE4_RELIC;
    public static RelicPartItem TWITCH_EMOTE5_RELIC;
    public static RelicPartItem CUPCAKE_BLUE_RELIC;
    public static RelicPartItem CUPCAKE_LIME_RELIC;
    public static RelicPartItem CUPCAKE_PINK_RELIC;
    public static RelicPartItem CUPCAKE_PURPLE_RELIC;
    public static RelicPartItem CUPCAKE_RED_RELIC;
    public static RelicPartItem AIR_RELIC;
    public static RelicPartItem SPIRIT_RELIC;
    public static RelicPartItem FIRE_RELIC;
    public static RelicPartItem EARTH_RELIC;
    public static RelicPartItem WATER_RELIC;
    public static ItemVaultRune VAULT_RUNE;
    public static ItemTraderCore TRADER_CORE;
    public static ItemUnidentifiedArtifact UNIDENTIFIED_ARTIFACT;
    public static ItemLegendaryTreasure LEGENDARY_TREASURE_NORMAL;
    public static ItemLegendaryTreasure LEGENDARY_TREASURE_RARE;
    public static ItemLegendaryTreasure LEGENDARY_TREASURE_EPIC;
    public static ItemLegendaryTreasure LEGENDARY_TREASURE_OMEGA;
    public static RelicItem VAULT_RELIC;
    public static ItemSkillOrbFrame SKILL_ORB_FRAME;
    public static ItemSkillShard SKILL_SHARD;
    public static ItemVaultFruit.BitterLemon BITTER_LEMON;
    public static ItemVaultFruit.SourOrange SOUR_ORANGE;
    public static ItemVaultFruit.MysticPear MYSTIC_PEAR;
    public static BasicItem KEY_PIECE;
    public static BasicItem KEY_MOULD;
    public static BasicItem BLANK_KEY;
    public static BasicItem NETHERITE_CLUSTER;
    public static BasicItem ISKALLIUM_CLUSTER;
    public static BasicItem GORGINITE_CLUSTER;
    public static BasicItem SPARKLETINE_CLUSTER;
    public static BasicItem ASHIUM_CLUSTER;
    public static BasicItem BOMIGNITE_CLUSTER;
    public static BasicItem FUNSOIDE_CLUSTER;
    public static BasicItem TUBIUM_CLUSTER;
    public static BasicItem UPALINE_CLUSTER;
    public static BasicItem PUFFIUM_CLUSTER;
    public static RelicPartItem TWOLF999_HEAD_RELIC;
    public static RelicPartItem TWOLF999_COMBAT_VEST_RELIC;
    public static RelicPartItem TWOLF999_COMBAT_LEGGINGS_RELIC;
    public static RelicPartItem TWOLF999_COMBAT_GLOVES_RELIC;
    public static RelicPartItem TWOLF999_COMBAT_BOOTS_RELIC;
    public static RelicPartItem ARMOR_OF_FORBEARANCE_RELIC;
    public static RelicPartItem HEART_OF_THE_VOID_RELIC;
    public static RelicPartItem NEMESIS_THWARTER_RELIC;
    public static RelicPartItem REVERENCE_EDGE_RELIC;
    public static RelicPartItem WINGS_OF_EQUITY_RELIC;
    public static VaultStewItem VAULT_STEW_MYSTERY;
    public static VaultStewItem VAULT_STEW_NORMAL;
    public static VaultStewItem VAULT_STEW_RARE;
    public static VaultStewItem VAULT_STEW_EPIC;
    public static VaultStewItem VAULT_STEW_OMEGA;
    public static BasicItem POISONOUS_MUSHROOM;
    public static BasicItem VAULT_GOLD;
    public static BasicItem VAULT_DIAMOND;
    public static BasicItem SKILL_ESSENCE;
    public static LootableItem UNIDENTIFIED_RELIC;
    public static ItemVaultFruit.SweetKiwi SWEET_KIWI;
    public static BasicItem HUNTER_EYE;
    public static BasicItem BURGER_PATTY;
    public static BasicItem BURGER_BUN;
    public static BasicItem MATURE_CHEDDAR;
    public static BasicItem MYSTIC_TOMATO;
    public static BasicItem VAULT_SCRAP;
    public static BasicItem VAULT_INGOT;
    public static BasicItem VAULT_PLATINUM;
    public static LootableItem MYSTERY_BOX;
    public static BasicItem DRILL_ARROW_PART;
    public static ItemDrillArrow DRILL_ARROW;
    public static ItemRespecFlask RESPEC_FLASK;
    public static ItemResetFlask RESET_FLASK;
    public static LootableItem MYSTERY_EGG;
    public static LootableItem MYSTERY_HOSTILE_EGG;
    public static BasicItem ACCELERATION_CHIP;
    public static LootableItem PANDORAS_BOX;
    public static BasicItem ISKALLIUM_CHUNK;
    public static BasicItem GORGINITE_CHUNK;
    public static BasicItem SPARKLETINE_CHUNK;
    public static BasicItem ASHIUM_CHUNK;
    public static BasicItem BOMIGNITE_CHUNK;
    public static BasicItem FUNSOIDE_CHUNK;
    public static BasicItem TUBIUM_CHUNK;
    public static BasicItem UPALINE_CHUNK;
    public static BasicItem PUFFIUM_CHUNK;
    public static BasicItem OMEGA_POG;
    public static BasicItem ETERNAL_SOUL;
    public static BasicItem SPARK;
    public static BasicItem STAR_SHARD;
    public static BasicItem STAR_CORE;
    public static BasicItem STAR_ESSENCE;
    public static ItemKnowledgeStar KNOWLEDGE_STAR;
    public static VaultSwordItem SWORD;
    public static VaultAxeItem AXE;
    public static VaultArmorItem HELMET;
    public static VaultArmorItem CHESTPLATE;
    public static VaultArmorItem LEGGINGS;
    public static VaultArmorItem BOOTS;
    public static EtchingItem ETCHING;
    public static BasicItem ETCHING_FRAGMENT;
    public static IdolItem IDOL_BENEVOLENT;
    public static IdolItem IDOL_OMNISCIENT;
    public static IdolItem IDOL_TIMEKEEPER;
    public static IdolItem IDOL_MALEVOLENCE;
    public static PuzzleRuneItem PUZZLE_RUNE;
    public static BasicItem INFUSED_ETERNAL_SOUL;
    public static Item UNKNOWN_EGG;
    public static VaultConsumableItem CANDY_BAR;
    public static VaultConsumableItem POWER_BAR;
    public static VaultConsumableItem JADE_APPLE;
    public static VaultConsumableItem COBALT_APPLE;
    public static VaultConsumableItem PIXIE_APPLE;
    public static BasicItem VAULT_APPLE;
    public static VaultConsumableItem LUCKY_APPLE;
    public static VaultConsumableItem TREASURE_APPLE;
    public static VaultConsumableItem POWER_APPLE;
    public static VaultConsumableItem GHOST_APPLE;
    public static VaultConsumableItem GOLEM_APPLE;
    public static VaultConsumableItem SWEET_APPLE;
    public static VaultConsumableItem HEARTY_APPLE;
    public static BasicItem PERFECT_ALEXANDRITE;
    public static BasicItem PERFECT_PAINITE;
    public static BasicItem PERFECT_BENITOITE;
    public static BasicItem PERFECT_LARIMAR;
    public static BasicItem PERFECT_BLACK_OPAL;
    public static BasicItem PERFECT_ECHO_GEM;
    public static BasicItem PERFECT_WUTODIE;
    public static BasicItem VAULTERITE_INGOT;
    public static BasicItem RED_VAULT_ESSENCE;
    public static BasicItem VAULT_DUST;
    public static BasicItem VAULT_NUGGET;
    public static BasicItem VAULT_BRONZE;
    public static BasicItem VAULT_SILVER;
    public static BasicItem MAGNETITE;
    public static BasicItem MAGNET_CORE_WEAK;
    public static BasicItem MAGNET_CORE_STRONG;
    public static BasicItem MAGNET_CORE_OMEGA;
    public static BasicItem VAULT_ESSENCE;
    public static BasicItem REPAIR_CORE;
    public static BasicItem REPAIR_CORE_T2;
    public static BasicItem REPAIR_CORE_T3;
    public static BasicItem VAULT_PLATING;
    public static BasicItem VAULT_PLATING_T2;
    public static BasicItem VAULT_PLATING_T3;
    public static WutaxShardItem WUTAX_SHARD;
    public static BasicItem WUTAX_CRYSTAL;
    public static VaultCatalystItem VAULT_CATALYST;
    public static VaultInhibitorItem VAULT_INHIBITOR;
    public static BasicTooltipItem PAINITE_STAR;
    public static VaultRuneItem VAULT_RUNE_MINE;
    public static VaultRuneItem VAULT_RUNE_PUZZLE;
    public static VaultRuneItem VAULT_RUNE_DIGSITE;
    public static VaultRuneItem VAULT_RUNE_CRYSTAL;
    public static VaultRuneItem VAULT_RUNE_VIEWER;
    public static VaultRuneItem VAULT_RUNE_VENDOR;
    public static VaultRuneItem VAULT_RUNE_XMARK;
    public static BasicItem VAULT_CATALYST_FRAGMENT;
    public static BasicItem VAULT_SAND;
    public static BasicItem SOUL_FLAME;
    public static BasicItem VAULT_GEAR;
    public static ItemVaultCrystalSeal CRYSTAL_SEAL_EXECUTIONER;
    public static ItemVaultCrystalSeal CRYSTAL_SEAL_HUNTER;
    public static ItemVaultCrystalSeal CRYSTAL_SEAL_ARCHITECT;
    public static ItemVaultCrystalSeal CRYSTAL_SEAL_ANCIENTS;
    public static ItemVaultCrystalSeal CRYSTAL_SEAL_RAID;
    public static ItemVaultRaffleSeal CRYSTAL_SEAL_RAFFLE;
    public static BasicTooltipItem GEAR_CHARM;
    public static BasicTooltipItem GEAR_CHARM_T3;
    public static BasicTooltipItem IDENTIFICATION_TOME;
    public static BasicItem BANISHED_SOUL;
    public static BasicItem UNKNOWN_ITEM;
    public static BasicItem SOUL_SHARD;
    public static ItemShardPouch SHARD_POUCH;
    public static VaultPaxelItem VAULT_PAXEL;
    public static BasicItem PAXEL_CHARM;
    public static InfiniteWaterBucketItem INFINITE_WATER_BUCKET;
    public static VaultPearlItem VAULT_PEARL;
    public static BasicScavengerItem SCAVENGER_CREEPER_EYE;
    public static BasicScavengerItem SCAVENGER_CREEPER_FOOT;
    public static BasicScavengerItem SCAVENGER_CREEPER_FUSE;
    public static BasicScavengerItem SCAVENGER_CREEPER_TNT;
    public static BasicScavengerItem SCAVENGER_CREEPER_VIAL;
    public static BasicScavengerItem SCAVENGER_CREEPER_CHARM;
    public static BasicScavengerItem SCAVENGER_DROWNED_BARNACLE;
    public static BasicScavengerItem SCAVENGER_DROWNED_EYE;
    public static BasicScavengerItem SCAVENGER_DROWNED_HIDE;
    public static BasicScavengerItem SCAVENGER_DROWNED_VIAL;
    public static BasicScavengerItem SCAVENGER_DROWNED_CHARM;
    public static BasicScavengerItem SCAVENGER_SKELETON_SHARD;
    public static BasicScavengerItem SCAVENGER_SKELETON_EYE;
    public static BasicScavengerItem SCAVENGER_SKELETON_RIBCAGE;
    public static BasicScavengerItem SCAVENGER_SKELETON_SKULL;
    public static BasicScavengerItem SCAVENGER_SKELETON_WISHBONE;
    public static BasicScavengerItem SCAVENGER_SKELETON_VIAL;
    public static BasicScavengerItem SCAVENGER_SKELETON_CHARM;
    public static BasicScavengerItem SCAVENGER_SPIDER_FANGS;
    public static BasicScavengerItem SCAVENGER_SPIDER_LEG;
    public static BasicScavengerItem SCAVENGER_SPIDER_WEBBING;
    public static BasicScavengerItem SCAVENGER_SPIDER_CURSED_CHARM;
    public static BasicScavengerItem SCAVENGER_SPIDER_VIAL;
    public static BasicScavengerItem SCAVENGER_SPIDER_CHARM;
    public static BasicScavengerItem SCAVENGER_ZOMBIE_BRAIN;
    public static BasicScavengerItem SCAVENGER_ZOMBIE_ARM;
    public static BasicScavengerItem SCAVENGER_ZOMBIE_EAR;
    public static BasicScavengerItem SCAVENGER_ZOMBIE_EYE;
    public static BasicScavengerItem SCAVENGER_ZOMBIE_HIDE;
    public static BasicScavengerItem SCAVENGER_ZOMBIE_NOSE;
    public static BasicScavengerItem SCAVENGER_ZOMBIE_VIAL;
    public static BasicScavengerItem SCAVENGER_TREASURE_BANGLE_BLUE;
    public static BasicScavengerItem SCAVENGER_TREASURE_BANGLE_PINK;
    public static BasicScavengerItem SCAVENGER_TREASURE_BANGLE_GREEN;
    public static BasicScavengerItem SCAVENGER_TREASURE_EARRINGS;
    public static BasicScavengerItem SCAVENGER_TREASURE_GOBLET;
    public static BasicScavengerItem SCAVENGER_TREASURE_SACK;
    public static BasicScavengerItem SCAVENGER_TREASURE_SCROLL_RED;
    public static BasicScavengerItem SCAVENGER_TREASURE_SCROLL_BLUE;
    public static BasicScavengerItem SCAVENGER_SCRAP_BROKEN_POTTERY;
    public static BasicScavengerItem SCAVENGER_SCRAP_CRACKED_PEARL;
    public static BasicScavengerItem SCAVENGER_SCRAP_CRACKED_SCRIPT;
    public static BasicScavengerItem SCAVENGER_SCRAP_EMPTY_JAR;
    public static BasicScavengerItem SCAVENGER_SCRAP_OLD_BOOK;
    public static BasicScavengerItem SCAVENGER_SCRAP_POTTERY_SHARD;
    public static BasicScavengerItem SCAVENGER_SCRAP_POULTICE_JAR;
    public static BasicScavengerItem SCAVENGER_SCRAP_PRESERVES_JAR;
    public static BasicScavengerItem SCAVENGER_SCRAP_RIPPED_PAGE;
    public static BasicScavengerItem SCAVENGER_SCRAP_SADDLE_BAG;
    public static BasicScavengerItem SCAVENGER_SCRAP_SPICE_JAR;
    public static BasicScavengerItem SCAVENGER_SCRAP_WIZARD_WAND;
    public static VaultMagnetItem VAULT_MAGNET_WEAK;
    public static VaultMagnetItem VAULT_MAGNET_STRONG;
    public static VaultMagnetItem VAULT_MAGNET_OMEGA;
    public static final BucketItem VOID_LIQUID_BUCKET;
    public static final GodEssenceItem TENOS_ESSENCE;
    public static final GodEssenceItem VELARA_ESSENCE;
    public static final GodEssenceItem WENDARR_ESSENCE;
    public static final GodEssenceItem IDONA_ESSENCE;
    public static GatedLootableItem MOD_BOX;
    public static LootableItem UNIDENTIFIED_TREASURE_KEY;
    public static VoidOrbItem VOID_ORB;
    public static BasicItem CRYSTAL_BURGER;
    public static BasicItem FULL_PIZZA;
    public static BasicItem LIFE_SCROLL;
    public static BasicItem AURA_SCROLL;
    public static ArtisanScrollItem ARTISAN_SCROLL;
    public static BasicTooltipItem FABRICATION_JEWEL;
    public static ItemModArmorCrate ARMOR_CRATE_HELLCOW;
    public static ItemModArmorCrate ARMOR_CRATE_BOTANIA;
    public static ItemModArmorCrate ARMOR_CRATE_CREATE;
    public static ItemModArmorCrate ARMOR_CRATE_DANK;
    public static ItemModArmorCrate ARMOR_CRATE_FLUX;
    public static ItemModArmorCrate ARMOR_CRATE_IMMERSIVE_ENGINEERING;
    public static ItemModArmorCrate ARMOR_CRATE_MEKA;
    public static ItemModArmorCrate ARMOR_CRATE_POWAH;
    public static ItemModArmorCrate ARMOR_CRATE_THERMAL;
    public static ItemModArmorCrate ARMOR_CRATE_TRASH;
    public static ItemModArmorCrate ARMOR_CRATE_VILLAGER;
    public static ItemModArmorCrate ARMOR_CRATE_AUTOMATIC;
    public static ItemModArmorCrate ARMOR_CRATE_FAIRY;
    public static ItemModArmorCrate ARMOR_CRATE_BUILDING;
    public static ItemModArmorCrate ARMOR_CRATE_ZOMBIE;
    public static ItemModArmorCrate ARMOR_CRATE_XNET;
    public static ItemModArmorCrate ARMOR_CRATE_TEST_DUMMY;
    public static ItemModArmorCrate ARMOR_CRATE_INDUSTRIAL_FOREGOING;
    public static ItemModArmorCrate ARMOR_CRATE_CAKE;
    public static FlawedRubyItem FLAWED_RUBY;
    public static BasicItem ARTIFACT_FRAGMENT;
    public static BasicTooltipItem VAULT_CHARM;
    public static VaultCharmUpgrade CHARM_UPGRADE_TIER_1;
    public static VaultCharmUpgrade CHARM_UPGRADE_TIER_2;
    public static VaultCharmUpgrade CHARM_UPGRADE_TIER_3;
    public static VaultCharmUpgrade CHARM_UPGRADE_TIER_4;
    public static BurntCrystalItem BURNT_CRYSTAL;
    public static FinalVaultKeystoneItem KEYSTONE_IDONA;
    public static FinalVaultKeystoneItem KEYSTONE_TENOS;
    public static FinalVaultKeystoneItem KEYSTONE_VELARA;
    public static FinalVaultKeystoneItem KEYSTONE_WENDARR;

    public static void registerItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = (IForgeRegistry<Item>) event.getRegistry();
        registry.register((IForgeRegistryEntry) ModItems.VAULT_BURGER);
        registry.register((IForgeRegistryEntry) ModItems.OOZING_PIZZA);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_COOKIE);
        registry.register((IForgeRegistryEntry) ModItems.SKILL_ORB);
        registry.register((IForgeRegistryEntry) ModItems.ALEXANDRITE_GEM);
        registry.register((IForgeRegistryEntry) ModItems.BENITOITE_GEM);
        registry.register((IForgeRegistryEntry) ModItems.LARIMAR_GEM);
        registry.register((IForgeRegistryEntry) ModItems.BLACK_OPAL_GEM);
        registry.register((IForgeRegistryEntry) ModItems.PAINITE_GEM);
        registry.register((IForgeRegistryEntry) ModItems.ISKALLIUM_GEM);
        registry.register((IForgeRegistryEntry) ModItems.GORGINITE_GEM);
        registry.register((IForgeRegistryEntry) ModItems.SPARKLETINE_GEM);
        registry.register((IForgeRegistryEntry) ModItems.ASHIUM_GEM);
        registry.register((IForgeRegistryEntry) ModItems.BOMIGNITE_GEM);
        registry.register((IForgeRegistryEntry) ModItems.FUNSOIDE_GEM);
        registry.register((IForgeRegistryEntry) ModItems.TUBIUM_GEM);
        registry.register((IForgeRegistryEntry) ModItems.WUTODIE_GEM);
        registry.register((IForgeRegistryEntry) ModItems.UPALINE_GEM);
        registry.register((IForgeRegistryEntry) ModItems.PUFFIUM_GEM);
        registry.register((IForgeRegistryEntry) ModItems.ECHO_GEM);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_ROCK);
        registry.register((IForgeRegistryEntry) ModItems.POG);
        registry.register((IForgeRegistryEntry) ModItems.ECHO_POG);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_CRYSTAL);
        registry.register((IForgeRegistryEntry) ModItems.ISKALLIUM_KEY);
        registry.register((IForgeRegistryEntry) ModItems.GORGINITE_KEY);
        registry.register((IForgeRegistryEntry) ModItems.SPARKLETINE_KEY);
        registry.register((IForgeRegistryEntry) ModItems.ASHIUM_KEY);
        registry.register((IForgeRegistryEntry) ModItems.BOMIGNITE_KEY);
        registry.register((IForgeRegistryEntry) ModItems.FUNSOIDE_KEY);
        registry.register((IForgeRegistryEntry) ModItems.TUBIUM_KEY);
        registry.register((IForgeRegistryEntry) ModItems.UPALINE_KEY);
        registry.register((IForgeRegistryEntry) ModItems.PUFFIUM_KEY);
        registry.register((IForgeRegistryEntry) ModItems.RELIC_BOOSTER_PACK);
        registry.register((IForgeRegistryEntry) ModItems.DRAGON_HEAD_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.DRAGON_TAIL_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.DRAGON_FOOT_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.DRAGON_CHEST_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.DRAGON_BREATH_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.MINERS_DELIGHT_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.MINERS_LIGHT_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.PICKAXE_HANDLE_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.PICKAXE_HEAD_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.PICKAXE_TOOL_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.SWORD_BLADE_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.SWORD_HANDLE_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.SWORD_STICK_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.WARRIORS_ARMOUR_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.WARRIORS_CHARM_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.DIAMOND_ESSENCE_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.GOLD_ESSENCE_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.MYSTIC_GEM_ESSENCE_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.NETHERITE_ESSENCE_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.PLATINUM_ESSENCE_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.CUPCAKE_BLUE_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.CUPCAKE_LIME_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.CUPCAKE_PINK_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.CUPCAKE_PURPLE_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.CUPCAKE_RED_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.AIR_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.SPIRIT_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.FIRE_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.EARTH_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.WATER_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.TWOLF999_HEAD_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.TWOLF999_COMBAT_VEST_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.TWOLF999_COMBAT_LEGGINGS_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.TWOLF999_COMBAT_GLOVES_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.TWOLF999_COMBAT_BOOTS_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.ARMOR_OF_FORBEARANCE_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.HEART_OF_THE_VOID_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.NEMESIS_THWARTER_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.REVERENCE_EDGE_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.WINGS_OF_EQUITY_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.TWITCH_EMOTE1_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.TWITCH_EMOTE2_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.TWITCH_EMOTE3_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.TWITCH_EMOTE4_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.TWITCH_EMOTE5_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_RUNE);
        registry.register((IForgeRegistryEntry) ModItems.TRADER_CORE);
        registry.register((IForgeRegistryEntry) ModItems.LEGENDARY_TREASURE_NORMAL);
        registry.register((IForgeRegistryEntry) ModItems.LEGENDARY_TREASURE_RARE);
        registry.register((IForgeRegistryEntry) ModItems.LEGENDARY_TREASURE_EPIC);
        registry.register((IForgeRegistryEntry) ModItems.LEGENDARY_TREASURE_OMEGA);
        registry.register((IForgeRegistryEntry) ModItems.UNIDENTIFIED_ARTIFACT);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.SKILL_ORB_FRAME);
        registry.register((IForgeRegistryEntry) ModItems.SKILL_SHARD);
        registry.register((IForgeRegistryEntry) ModItems.BITTER_LEMON);
        registry.register((IForgeRegistryEntry) ModItems.SOUR_ORANGE);
        registry.register((IForgeRegistryEntry) ModItems.MYSTIC_PEAR);
        registry.register((IForgeRegistryEntry) ModItems.KEY_PIECE);
        registry.register((IForgeRegistryEntry) ModItems.KEY_MOULD);
        registry.register((IForgeRegistryEntry) ModItems.BLANK_KEY);
        registry.register((IForgeRegistryEntry) ModItems.NETHERITE_CLUSTER);
        registry.register((IForgeRegistryEntry) ModItems.ISKALLIUM_CLUSTER);
        registry.register((IForgeRegistryEntry) ModItems.GORGINITE_CLUSTER);
        registry.register((IForgeRegistryEntry) ModItems.SPARKLETINE_CLUSTER);
        registry.register((IForgeRegistryEntry) ModItems.ASHIUM_CLUSTER);
        registry.register((IForgeRegistryEntry) ModItems.BOMIGNITE_CLUSTER);
        registry.register((IForgeRegistryEntry) ModItems.FUNSOIDE_CLUSTER);
        registry.register((IForgeRegistryEntry) ModItems.TUBIUM_CLUSTER);
        registry.register((IForgeRegistryEntry) ModItems.UPALINE_CLUSTER);
        registry.register((IForgeRegistryEntry) ModItems.PUFFIUM_CLUSTER);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_STEW_MYSTERY);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_STEW_NORMAL);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_STEW_RARE);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_STEW_EPIC);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_STEW_OMEGA);
        registry.register((IForgeRegistryEntry) ModItems.POISONOUS_MUSHROOM);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_GOLD);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_DIAMOND);
        registry.register((IForgeRegistryEntry) ModItems.SKILL_ESSENCE);
        registry.register((IForgeRegistryEntry) ModItems.UNIDENTIFIED_RELIC);
        registry.register((IForgeRegistryEntry) ModItems.SWEET_KIWI);
        registry.register((IForgeRegistryEntry) ModItems.HUNTER_EYE);
        registry.register((IForgeRegistryEntry) ModItems.BURGER_PATTY);
        registry.register((IForgeRegistryEntry) ModItems.BURGER_BUN);
        registry.register((IForgeRegistryEntry) ModItems.MATURE_CHEDDAR);
        registry.register((IForgeRegistryEntry) ModItems.MYSTIC_TOMATO);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_SCRAP);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_INGOT);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_PLATINUM);
        registry.register((IForgeRegistryEntry) ModItems.MYSTERY_BOX);
        registry.register((IForgeRegistryEntry) ModItems.DRILL_ARROW_PART);
        registry.register((IForgeRegistryEntry) ModItems.DRILL_ARROW);
        registry.register((IForgeRegistryEntry) ModItems.RESPEC_FLASK);
        registry.register((IForgeRegistryEntry) ModItems.RESET_FLASK);
        registry.register((IForgeRegistryEntry) ModItems.MYSTERY_EGG);
        registry.register((IForgeRegistryEntry) ModItems.MYSTERY_HOSTILE_EGG);
        registry.register((IForgeRegistryEntry) ModItems.ACCELERATION_CHIP);
        registry.register((IForgeRegistryEntry) ModItems.PANDORAS_BOX);
        registry.register((IForgeRegistryEntry) ModItems.ISKALLIUM_CHUNK);
        registry.register((IForgeRegistryEntry) ModItems.GORGINITE_CHUNK);
        registry.register((IForgeRegistryEntry) ModItems.SPARKLETINE_CHUNK);
        registry.register((IForgeRegistryEntry) ModItems.ASHIUM_CHUNK);
        registry.register((IForgeRegistryEntry) ModItems.BOMIGNITE_CHUNK);
        registry.register((IForgeRegistryEntry) ModItems.FUNSOIDE_CHUNK);
        registry.register((IForgeRegistryEntry) ModItems.TUBIUM_CHUNK);
        registry.register((IForgeRegistryEntry) ModItems.UPALINE_CHUNK);
        registry.register((IForgeRegistryEntry) ModItems.PUFFIUM_CHUNK);
        registry.register((IForgeRegistryEntry) ModItems.OMEGA_POG);
        registry.register((IForgeRegistryEntry) ModItems.ETERNAL_SOUL);
        registry.register((IForgeRegistryEntry) ModItems.SPARK);
        registry.register((IForgeRegistryEntry) ModItems.STAR_SHARD);
        registry.register((IForgeRegistryEntry) ModItems.STAR_CORE);
        registry.register((IForgeRegistryEntry) ModItems.STAR_ESSENCE);
        registry.register((IForgeRegistryEntry) ModItems.KNOWLEDGE_STAR);
        registry.register((IForgeRegistryEntry) ModItems.SWORD);
        registry.register((IForgeRegistryEntry) ModItems.AXE);
        registry.register((IForgeRegistryEntry) ModItems.HELMET);
        registry.register((IForgeRegistryEntry) ModItems.CHESTPLATE);
        registry.register((IForgeRegistryEntry) ModItems.LEGGINGS);
        registry.register((IForgeRegistryEntry) ModItems.BOOTS);
        registry.register((IForgeRegistryEntry) ModItems.ETCHING);
        registry.register((IForgeRegistryEntry) ModItems.ETCHING_FRAGMENT);
        registry.register((IForgeRegistryEntry) ModItems.IDOL_BENEVOLENT);
        registry.register((IForgeRegistryEntry) ModItems.IDOL_OMNISCIENT);
        registry.register((IForgeRegistryEntry) ModItems.IDOL_TIMEKEEPER);
        registry.register((IForgeRegistryEntry) ModItems.IDOL_MALEVOLENCE);
        registry.register((IForgeRegistryEntry) ModItems.PUZZLE_RUNE);
        registry.register((IForgeRegistryEntry) ModItems.INFUSED_ETERNAL_SOUL);
        registry.register((IForgeRegistryEntry) ModItems.UNKNOWN_EGG);
        registry.register((IForgeRegistryEntry) ModItems.CANDY_BAR);
        registry.register((IForgeRegistryEntry) ModItems.POWER_BAR);
        registry.register((IForgeRegistryEntry) ModItems.JADE_APPLE);
        registry.register((IForgeRegistryEntry) ModItems.COBALT_APPLE);
        registry.register((IForgeRegistryEntry) ModItems.PIXIE_APPLE);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_APPLE);
        registry.register((IForgeRegistryEntry) ModItems.LUCKY_APPLE);
        registry.register((IForgeRegistryEntry) ModItems.TREASURE_APPLE);
        registry.register((IForgeRegistryEntry) ModItems.POWER_APPLE);
        registry.register((IForgeRegistryEntry) ModItems.GHOST_APPLE);
        registry.register((IForgeRegistryEntry) ModItems.GOLEM_APPLE);
        registry.register((IForgeRegistryEntry) ModItems.SWEET_APPLE);
        registry.register((IForgeRegistryEntry) ModItems.HEARTY_APPLE);
        registry.register((IForgeRegistryEntry) ModItems.PERFECT_ALEXANDRITE);
        registry.register((IForgeRegistryEntry) ModItems.PERFECT_PAINITE);
        registry.register((IForgeRegistryEntry) ModItems.PERFECT_BENITOITE);
        registry.register((IForgeRegistryEntry) ModItems.PERFECT_LARIMAR);
        registry.register((IForgeRegistryEntry) ModItems.PERFECT_BLACK_OPAL);
        registry.register((IForgeRegistryEntry) ModItems.PERFECT_ECHO_GEM);
        registry.register((IForgeRegistryEntry) ModItems.PERFECT_WUTODIE);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_DUST);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_NUGGET);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_BRONZE);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_SILVER);
        registry.register((IForgeRegistryEntry) ModItems.MAGNETITE);
        registry.register((IForgeRegistryEntry) ModItems.MAGNET_CORE_WEAK);
        registry.register((IForgeRegistryEntry) ModItems.MAGNET_CORE_STRONG);
        registry.register((IForgeRegistryEntry) ModItems.MAGNET_CORE_OMEGA);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_ESSENCE);
        registry.register((IForgeRegistryEntry) ModItems.REPAIR_CORE);
        registry.register((IForgeRegistryEntry) ModItems.REPAIR_CORE_T2);
        registry.register((IForgeRegistryEntry) ModItems.REPAIR_CORE_T3);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_PLATING);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_PLATING_T2);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_PLATING_T3);
        registry.register((IForgeRegistryEntry) ModItems.WUTAX_SHARD);
        registry.register((IForgeRegistryEntry) ModItems.WUTAX_CRYSTAL);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_CATALYST);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_INHIBITOR);
        registry.register((IForgeRegistryEntry) ModItems.PAINITE_STAR);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_RUNE_MINE);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_RUNE_PUZZLE);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_RUNE_DIGSITE);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_RUNE_CRYSTAL);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_RUNE_VIEWER);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_RUNE_VENDOR);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_RUNE_XMARK);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_CATALYST_FRAGMENT);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_SAND);
        registry.register((IForgeRegistryEntry) ModItems.SOUL_FLAME);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_GEAR);
        registry.register((IForgeRegistryEntry) ModItems.CRYSTAL_SEAL_EXECUTIONER);
        registry.register((IForgeRegistryEntry) ModItems.CRYSTAL_SEAL_HUNTER);
        registry.register((IForgeRegistryEntry) ModItems.CRYSTAL_SEAL_ARCHITECT);
        registry.register((IForgeRegistryEntry) ModItems.CRYSTAL_SEAL_ANCIENTS);
        registry.register((IForgeRegistryEntry) ModItems.CRYSTAL_SEAL_RAID);
        registry.register((IForgeRegistryEntry) ModItems.CRYSTAL_SEAL_RAFFLE);
        registry.register((IForgeRegistryEntry) ModItems.GEAR_CHARM);
        registry.register((IForgeRegistryEntry) ModItems.GEAR_CHARM_T3);
        registry.register((IForgeRegistryEntry) ModItems.IDENTIFICATION_TOME);
        registry.register((IForgeRegistryEntry) ModItems.BANISHED_SOUL);
        registry.register((IForgeRegistryEntry) ModItems.UNKNOWN_ITEM);
        registry.register((IForgeRegistryEntry) ModItems.SOUL_SHARD);
        registry.register((IForgeRegistryEntry) ModItems.SHARD_POUCH);
        registry.register((IForgeRegistryEntry) ModItems.PAXEL_CHARM);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_PAXEL);
        registry.register((IForgeRegistryEntry) ModItems.INFINITE_WATER_BUCKET);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_PEARL);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_MAGNET_WEAK);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_MAGNET_STRONG);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_MAGNET_OMEGA);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_CREEPER_EYE);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_CREEPER_FOOT);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_CREEPER_FUSE);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_CREEPER_TNT);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_CREEPER_VIAL);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_CREEPER_CHARM);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_DROWNED_BARNACLE);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_DROWNED_EYE);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_DROWNED_HIDE);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_DROWNED_VIAL);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_DROWNED_CHARM);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_SKELETON_SHARD);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_SKELETON_EYE);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_SKELETON_RIBCAGE);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_SKELETON_SKULL);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_SKELETON_WISHBONE);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_SKELETON_VIAL);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_SKELETON_CHARM);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_SPIDER_FANGS);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_SPIDER_LEG);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_SPIDER_WEBBING);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_SPIDER_CURSED_CHARM);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_SPIDER_VIAL);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_SPIDER_CHARM);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_ZOMBIE_BRAIN);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_ZOMBIE_ARM);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_ZOMBIE_EAR);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_ZOMBIE_EYE);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_ZOMBIE_HIDE);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_ZOMBIE_NOSE);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_ZOMBIE_VIAL);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_TREASURE_BANGLE_BLUE);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_TREASURE_BANGLE_PINK);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_TREASURE_BANGLE_GREEN);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_TREASURE_EARRINGS);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_TREASURE_GOBLET);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_TREASURE_SACK);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_TREASURE_SCROLL_RED);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_TREASURE_SCROLL_BLUE);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_SCRAP_BROKEN_POTTERY);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_SCRAP_CRACKED_PEARL);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_SCRAP_CRACKED_SCRIPT);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_SCRAP_EMPTY_JAR);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_SCRAP_OLD_BOOK);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_SCRAP_POTTERY_SHARD);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_SCRAP_POULTICE_JAR);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_SCRAP_PRESERVES_JAR);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_SCRAP_RIPPED_PAGE);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_SCRAP_SADDLE_BAG);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_SCRAP_SPICE_JAR);
        registry.register((IForgeRegistryEntry) ModItems.SCAVENGER_SCRAP_WIZARD_WAND);
        registry.register((IForgeRegistryEntry) ModItems.VOID_LIQUID_BUCKET);
        registry.register((IForgeRegistryEntry) ModItems.MOD_BOX);
        registry.register((IForgeRegistryEntry) ModItems.TENOS_ESSENCE);
        registry.register((IForgeRegistryEntry) ModItems.VELARA_ESSENCE);
        registry.register((IForgeRegistryEntry) ModItems.WENDARR_ESSENCE);
        registry.register((IForgeRegistryEntry) ModItems.IDONA_ESSENCE);
        registry.register((IForgeRegistryEntry) ModItems.VAULTERITE_INGOT);
        registry.register((IForgeRegistryEntry) ModItems.RED_VAULT_ESSENCE);
        registry.register((IForgeRegistryEntry) ModItems.UNIDENTIFIED_TREASURE_KEY);
        registry.register((IForgeRegistryEntry) ModItems.VOID_ORB);
        registry.register((IForgeRegistryEntry) ModItems.CRYSTAL_BURGER);
        registry.register((IForgeRegistryEntry) ModItems.FULL_PIZZA);
        registry.register((IForgeRegistryEntry) ModItems.LIFE_SCROLL);
        registry.register((IForgeRegistryEntry) ModItems.AURA_SCROLL);
        registry.register((IForgeRegistryEntry) ModItems.ARTISAN_SCROLL);
        registry.register((IForgeRegistryEntry) ModItems.FABRICATION_JEWEL);
        registry.register((IForgeRegistryEntry) ModItems.FLAWED_RUBY);
        registry.register((IForgeRegistryEntry) ModItems.ARMOR_CRATE_HELLCOW);
        registry.register((IForgeRegistryEntry) ModItems.ARMOR_CRATE_BOTANIA);
        registry.register((IForgeRegistryEntry) ModItems.ARMOR_CRATE_CREATE);
        registry.register((IForgeRegistryEntry) ModItems.ARMOR_CRATE_DANK);
        registry.register((IForgeRegistryEntry) ModItems.ARMOR_CRATE_FLUX);
        registry.register((IForgeRegistryEntry) ModItems.ARMOR_CRATE_IMMERSIVE_ENGINEERING);
        registry.register((IForgeRegistryEntry) ModItems.ARMOR_CRATE_MEKA);
        registry.register((IForgeRegistryEntry) ModItems.ARMOR_CRATE_POWAH);
        registry.register((IForgeRegistryEntry) ModItems.ARMOR_CRATE_THERMAL);
        registry.register((IForgeRegistryEntry) ModItems.ARMOR_CRATE_TRASH);
        registry.register((IForgeRegistryEntry) ModItems.ARMOR_CRATE_VILLAGER);
        registry.register((IForgeRegistryEntry) ModItems.ARMOR_CRATE_AUTOMATIC);
        registry.register((IForgeRegistryEntry) ModItems.ARMOR_CRATE_FAIRY);
        registry.register((IForgeRegistryEntry) ModItems.ARMOR_CRATE_BUILDING);
        registry.register((IForgeRegistryEntry) ModItems.ARMOR_CRATE_ZOMBIE);
        registry.register((IForgeRegistryEntry) ModItems.ARMOR_CRATE_XNET);
        registry.register((IForgeRegistryEntry) ModItems.ARMOR_CRATE_TEST_DUMMY);
        registry.register((IForgeRegistryEntry) ModItems.ARMOR_CRATE_INDUSTRIAL_FOREGOING);
        registry.register((IForgeRegistryEntry) ModItems.ARMOR_CRATE_CAKE);
        registry.register((IForgeRegistryEntry) ModItems.ARTIFACT_FRAGMENT);
        registry.register((IForgeRegistryEntry) ModItems.VAULT_CHARM);
        registry.register((IForgeRegistryEntry) ModItems.CHARM_UPGRADE_TIER_1);
        registry.register((IForgeRegistryEntry) ModItems.CHARM_UPGRADE_TIER_2);
        registry.register((IForgeRegistryEntry) ModItems.CHARM_UPGRADE_TIER_3);
        registry.register((IForgeRegistryEntry) ModItems.CHARM_UPGRADE_TIER_4);
    }

    static {
        ModItems.VAULT_MOD_GROUP = new ItemGroup("the_vault") {
            public ItemStack makeIcon() {
                return new ItemStack((IItemProvider) ModItems.VAULT_BURGER);
            }

            public boolean hasSearchBar() {
                return true;
            }
        };
        ModItems.SCAVENGER_GROUP = new ItemGroup("the_vault.scavenger") {
            public ItemStack makeIcon() {
                return new ItemStack((IItemProvider) ModBlocks.SCAVENGER_CHEST);
            }

            public boolean hasSearchBar() {
                return true;
            }
        };
        ModItems.VAULT_BURGER = new VaultXPFoodItem.Percent(Vault.id("vault_burger"),
                () -> ModConfigs.VAULT_ITEMS.VAULT_BURGER.minExpPercent,
                () -> ModConfigs.VAULT_ITEMS.VAULT_BURGER.maxExpPercent,
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.OOZING_PIZZA = new VaultXPFoodItem.Percent(Vault.id("oozing_pizza"),
                () -> ModConfigs.VAULT_ITEMS.VAULT_PIZZA.minExpPercent,
                () -> ModConfigs.VAULT_ITEMS.VAULT_PIZZA.maxExpPercent,
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.VAULT_COOKIE = new VaultXPFoodItem.Flat(Vault.id("vault_cookie"),
                () -> ModConfigs.VAULT_ITEMS.VAULT_COOKIE.minExp, () -> ModConfigs.VAULT_ITEMS.VAULT_COOKIE.maxExp,
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP), 25);
        ModItems.SKILL_ORB = new ItemSkillOrb(ModItems.VAULT_MOD_GROUP);
        ModItems.VAULT_ROCK = new ItemVaultGem(ModItems.VAULT_MOD_GROUP, Vault.id("vault_rock"));
        ModItems.ALEXANDRITE_GEM = new ItemVaultGem(ModItems.VAULT_MOD_GROUP, Vault.id("gem_alexandrite"));
        ModItems.BENITOITE_GEM = new ItemVaultGem(ModItems.VAULT_MOD_GROUP, Vault.id("gem_benitoite"));
        ModItems.LARIMAR_GEM = new ItemVaultGem(ModItems.VAULT_MOD_GROUP, Vault.id("gem_larimar"));
        ModItems.BLACK_OPAL_GEM = new ItemVaultGem(ModItems.VAULT_MOD_GROUP, Vault.id("gem_black_opal"));
        ModItems.PAINITE_GEM = new ItemVaultGem(ModItems.VAULT_MOD_GROUP, Vault.id("gem_painite"));
        ModItems.ISKALLIUM_GEM = new ItemVaultGem(ModItems.VAULT_MOD_GROUP, Vault.id("gem_iskallium"));
        ModItems.GORGINITE_GEM = new ItemVaultGem(ModItems.VAULT_MOD_GROUP, Vault.id("gem_gorginite"));
        ModItems.SPARKLETINE_GEM = new ItemVaultGem(ModItems.VAULT_MOD_GROUP, Vault.id("gem_sparkletine"));
        ModItems.WUTODIE_GEM = new ItemVaultGem(ModItems.VAULT_MOD_GROUP, Vault.id("gem_wutodie"));
        ModItems.ASHIUM_GEM = new ItemVaultGem(ModItems.VAULT_MOD_GROUP, Vault.id("gem_ashium"));
        ModItems.BOMIGNITE_GEM = new ItemVaultGem(ModItems.VAULT_MOD_GROUP, Vault.id("gem_bomignite"));
        ModItems.FUNSOIDE_GEM = new ItemVaultGem(ModItems.VAULT_MOD_GROUP, Vault.id("gem_funsoide"));
        ModItems.TUBIUM_GEM = new ItemVaultGem(ModItems.VAULT_MOD_GROUP, Vault.id("gem_tubium"));
        ModItems.UPALINE_GEM = new ItemVaultGem(ModItems.VAULT_MOD_GROUP, Vault.id("gem_upaline"));
        ModItems.PUFFIUM_GEM = new ItemVaultGem(ModItems.VAULT_MOD_GROUP, Vault.id("gem_puffium"));
        ModItems.ECHO_GEM = new ItemVaultGem(ModItems.VAULT_MOD_GROUP, Vault.id("gem_echo"));
        ModItems.POG = new ItemVaultGem(ModItems.VAULT_MOD_GROUP, Vault.id("gem_pog"));
        ModItems.ECHO_POG = new ItemVaultGem(ModItems.VAULT_MOD_GROUP, Vault.id("echo_pog"));
        ModItems.VAULT_CRYSTAL = new VaultCrystalItem(ModItems.VAULT_MOD_GROUP, Vault.id("vault_crystal"));
        ModItems.ISKALLIUM_KEY = new ItemVaultKey(ModItems.VAULT_MOD_GROUP, Vault.id("key_iskallium"));
        ModItems.GORGINITE_KEY = new ItemVaultKey(ModItems.VAULT_MOD_GROUP, Vault.id("key_gorginite"));
        ModItems.SPARKLETINE_KEY = new ItemVaultKey(ModItems.VAULT_MOD_GROUP, Vault.id("key_sparkletine"));
        ModItems.ASHIUM_KEY = new ItemVaultKey(ModItems.VAULT_MOD_GROUP, Vault.id("key_ashium"));
        ModItems.BOMIGNITE_KEY = new ItemVaultKey(ModItems.VAULT_MOD_GROUP, Vault.id("key_bomignite"));
        ModItems.FUNSOIDE_KEY = new ItemVaultKey(ModItems.VAULT_MOD_GROUP, Vault.id("key_funsoide"));
        ModItems.TUBIUM_KEY = new ItemVaultKey(ModItems.VAULT_MOD_GROUP, Vault.id("key_tubium"));
        ModItems.UPALINE_KEY = new ItemVaultKey(ModItems.VAULT_MOD_GROUP, Vault.id("key_upaline"));
        ModItems.PUFFIUM_KEY = new ItemVaultKey(ModItems.VAULT_MOD_GROUP, Vault.id("key_puffium"));
        ModItems.RELIC_BOOSTER_PACK = new ItemRelicBoosterPack(ModItems.VAULT_MOD_GROUP,
                Vault.id("relic_booster_pack"));
        ModItems.DRAGON_HEAD_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_dragon_head"));
        ModItems.DRAGON_TAIL_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_dragon_tail"));
        ModItems.DRAGON_FOOT_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_dragon_foot"));
        ModItems.DRAGON_CHEST_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_dragon_chest"));
        ModItems.DRAGON_BREATH_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_dragon_breath"));
        ModItems.MINERS_DELIGHT_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_miners_delight"));
        ModItems.MINERS_LIGHT_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_miners_light"));
        ModItems.PICKAXE_HANDLE_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_pickaxe_handle"));
        ModItems.PICKAXE_HEAD_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_pickaxe_head"));
        ModItems.PICKAXE_TOOL_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_pickaxe_tool"));
        ModItems.SWORD_BLADE_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_sword_blade"));
        ModItems.SWORD_HANDLE_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_sword_handle"));
        ModItems.SWORD_STICK_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_sword_stick"));
        ModItems.WARRIORS_ARMOUR_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_warriors_armour"));
        ModItems.WARRIORS_CHARM_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_warriors_charm"));
        ModItems.DIAMOND_ESSENCE_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_diamond_essence"));
        ModItems.GOLD_ESSENCE_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_gold_essence"));
        ModItems.MYSTIC_GEM_ESSENCE_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP,
                Vault.id("relic_mystic_gem_essence"));
        ModItems.NETHERITE_ESSENCE_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP,
                Vault.id("relic_netherite_essence"));
        ModItems.PLATINUM_ESSENCE_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP,
                Vault.id("relic_platinum_essence"));
        ModItems.TWITCH_EMOTE1_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_twitch_emote1"));
        ModItems.TWITCH_EMOTE2_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_twitch_emote2"));
        ModItems.TWITCH_EMOTE3_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_twitch_emote3"));
        ModItems.TWITCH_EMOTE4_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_twitch_emote4"));
        ModItems.TWITCH_EMOTE5_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_twitch_emote5"));
        ModItems.CUPCAKE_BLUE_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_cupcake_blue"));
        ModItems.CUPCAKE_LIME_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_cupcake_lime"));
        ModItems.CUPCAKE_PINK_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_cupcake_pink"));
        ModItems.CUPCAKE_PURPLE_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_cupcake_purple"));
        ModItems.CUPCAKE_RED_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_cupcake_red"));
        ModItems.AIR_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_air"));
        ModItems.SPIRIT_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_spirit"));
        ModItems.FIRE_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_fire"));
        ModItems.EARTH_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_earth"));
        ModItems.WATER_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_water"));
        ModItems.VAULT_RUNE = new ItemVaultRune(ModItems.VAULT_MOD_GROUP, Vault.id("vault_rune"));
        ModItems.TRADER_CORE = new ItemTraderCore(ModItems.VAULT_MOD_GROUP, Vault.id("trader_core"));
        ModItems.UNIDENTIFIED_ARTIFACT = new ItemUnidentifiedArtifact(ModItems.VAULT_MOD_GROUP,
                Vault.id("unidentified_artifact"));
        ModItems.LEGENDARY_TREASURE_NORMAL = new ItemLegendaryTreasure(ModItems.VAULT_MOD_GROUP,
                Vault.id("legendary_treasure_normal"), VaultRarity.COMMON);
        ModItems.LEGENDARY_TREASURE_RARE = new ItemLegendaryTreasure(ModItems.VAULT_MOD_GROUP,
                Vault.id("legendary_treasure_rare"), VaultRarity.RARE);
        ModItems.LEGENDARY_TREASURE_EPIC = new ItemLegendaryTreasure(ModItems.VAULT_MOD_GROUP,
                Vault.id("legendary_treasure_epic"), VaultRarity.EPIC);
        ModItems.LEGENDARY_TREASURE_OMEGA = new ItemLegendaryTreasure(ModItems.VAULT_MOD_GROUP,
                Vault.id("legendary_treasure_omega"), VaultRarity.OMEGA);
        ModItems.VAULT_RELIC = new RelicItem(ModItems.VAULT_MOD_GROUP, Vault.id("vault_relic"));
        ModItems.SKILL_ORB_FRAME = new ItemSkillOrbFrame(ModItems.VAULT_MOD_GROUP, Vault.id("orb_frame"));
        ModItems.SKILL_SHARD = new ItemSkillShard(ModItems.VAULT_MOD_GROUP, Vault.id("skill_shard"));
        ModItems.BITTER_LEMON = new ItemVaultFruit.BitterLemon(ModItems.VAULT_MOD_GROUP, Vault.id("bitter_lemon"), 600);
        ModItems.SOUR_ORANGE = new ItemVaultFruit.SourOrange(ModItems.VAULT_MOD_GROUP, Vault.id("sour_orange"), 1200);
        ModItems.MYSTIC_PEAR = new ItemVaultFruit.MysticPear(ModItems.VAULT_MOD_GROUP, Vault.id("mystic_pear"), 6000);
        ModItems.KEY_PIECE = new BasicItem(Vault.id("key_piece"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.KEY_MOULD = new BasicItem(Vault.id("key_mould"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.BLANK_KEY = new BasicItem(Vault.id("blank_key"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.NETHERITE_CLUSTER = new BasicItem(Vault.id("cluster_netherite"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.ISKALLIUM_CLUSTER = new BasicItem(Vault.id("cluster_iskallium"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.GORGINITE_CLUSTER = new BasicItem(Vault.id("cluster_gorginite"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.SPARKLETINE_CLUSTER = new BasicItem(Vault.id("cluster_sparkletine"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.ASHIUM_CLUSTER = new BasicItem(Vault.id("cluster_ashium"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.BOMIGNITE_CLUSTER = new BasicItem(Vault.id("cluster_bomignite"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.FUNSOIDE_CLUSTER = new BasicItem(Vault.id("cluster_funsoide"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.TUBIUM_CLUSTER = new BasicItem(Vault.id("cluster_tubium"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.UPALINE_CLUSTER = new BasicItem(Vault.id("cluster_upaline"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.PUFFIUM_CLUSTER = new BasicItem(Vault.id("cluster_puffium"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.TWOLF999_HEAD_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_twolf999_head"));
        ModItems.TWOLF999_COMBAT_VEST_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP,
                Vault.id("relic_twolf999_combat_vest"));
        ModItems.TWOLF999_COMBAT_LEGGINGS_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP,
                Vault.id("relic_twolf999_combat_leggings"));
        ModItems.TWOLF999_COMBAT_GLOVES_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP,
                Vault.id("relic_twolf999_combat_gloves"));
        ModItems.TWOLF999_COMBAT_BOOTS_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP,
                Vault.id("relic_twolf999_combat_boots"));
        ModItems.ARMOR_OF_FORBEARANCE_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP,
                Vault.id("relic_armor_of_forbearance"));
        ModItems.HEART_OF_THE_VOID_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP,
                Vault.id("relic_heart_of_the_void"));
        ModItems.NEMESIS_THWARTER_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP,
                Vault.id("relic_nemesis_thwarter"));
        ModItems.REVERENCE_EDGE_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_reverence_edge"));
        ModItems.WINGS_OF_EQUITY_RELIC = new RelicPartItem(ModItems.VAULT_MOD_GROUP, Vault.id("relic_wings_of_equity"));
        ModItems.VAULT_STEW_MYSTERY = new VaultStewItem(Vault.id("vault_stew_mystery"), VaultStewItem.Rarity.MYSTERY,
                new Item.Properties().food(VaultStewItem.FOOD).stacksTo(1)
                        .tab(ModItems.VAULT_MOD_GROUP));
        ModItems.VAULT_STEW_NORMAL = new VaultStewItem(Vault.id("vault_stew_normal"), VaultStewItem.Rarity.NORMAL,
                new Item.Properties().food(VaultStewItem.FOOD).stacksTo(1)
                        .tab(ModItems.VAULT_MOD_GROUP));
        ModItems.VAULT_STEW_RARE = new VaultStewItem(Vault.id("vault_stew_rare"), VaultStewItem.Rarity.RARE,
                new Item.Properties().food(VaultStewItem.FOOD).stacksTo(1)
                        .tab(ModItems.VAULT_MOD_GROUP));
        ModItems.VAULT_STEW_EPIC = new VaultStewItem(Vault.id("vault_stew_epic"), VaultStewItem.Rarity.EPIC,
                new Item.Properties().food(VaultStewItem.FOOD).stacksTo(1)
                        .tab(ModItems.VAULT_MOD_GROUP));
        ModItems.VAULT_STEW_OMEGA = new VaultStewItem(Vault.id("vault_stew_omega"), VaultStewItem.Rarity.OMEGA,
                new Item.Properties().food(VaultStewItem.FOOD).stacksTo(1)
                        .tab(ModItems.VAULT_MOD_GROUP));
        ModItems.POISONOUS_MUSHROOM = new BasicItem(Vault.id("poisonous_mushroom"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.VAULT_GOLD = new BasicItem(Vault.id("vault_gold"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.VAULT_DIAMOND = new BasicItem(Vault.id("vault_diamond"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.SKILL_ESSENCE = new BasicItem(Vault.id("skill_essence"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.UNIDENTIFIED_RELIC = new LootableItem(Vault.id("unidentified_relic"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP),
                () -> new ItemStack((IItemProvider) ModConfigs.VAULT_RELICS.getRandomPart()));
        ModItems.SWEET_KIWI = new ItemVaultFruit.SweetKiwi(ModItems.VAULT_MOD_GROUP, Vault.id("sweet_kiwi"), 100);
        ModItems.HUNTER_EYE = new BasicItem(Vault.id("hunter_eye"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.BURGER_PATTY = new BasicItem(Vault.id("burger_patty"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.BURGER_BUN = new BasicItem(Vault.id("burger_bun"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.MATURE_CHEDDAR = new BasicItem(Vault.id("mature_cheddar"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.MYSTIC_TOMATO = new BasicItem(Vault.id("mystic_tomato"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.VAULT_SCRAP = new BasicTooltipItem(Vault.id("vault_scrap"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP),
                new ITextComponent[] {
                        (ITextComponent) new StringTextComponent("The scrap of smelted down vault gear.")
                                .withStyle(TextFormatting.GRAY),
                        (ITextComponent) new StringTextComponent("Can be used to craft a repair core.")
                                .withStyle(TextFormatting.GRAY) });
        ModItems.VAULT_INGOT = new BasicItem(Vault.id("vault_ingot"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.VAULT_PLATINUM = new BasicItem(Vault.id("vault_platinum"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.MYSTERY_BOX = new LootableItem(Vault.id("mystery_box"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP),
                () -> ModConfigs.MYSTERY_BOX.POOL.getRandom(new Random()).generateItemStack());
        ModItems.DRILL_ARROW_PART = new BasicItem(Vault.id("drill_arrow_part"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(8));
        ModItems.DRILL_ARROW = new ItemDrillArrow(ModItems.VAULT_MOD_GROUP, Vault.id("drill_arrow"));
        ModItems.RESPEC_FLASK = new ItemRespecFlask(ModItems.VAULT_MOD_GROUP, Vault.id("respec_flask"));
        ModItems.RESET_FLASK = new ItemResetFlask(ModItems.VAULT_MOD_GROUP, Vault.id("reset_flask"));
        ModItems.MYSTERY_EGG = new LootableItem(Vault.id("mystery_egg"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP),
                () -> ModConfigs.MYSTERY_EGG.POOL.getRandom(new Random()).generateItemStack());
        ModItems.MYSTERY_HOSTILE_EGG = new LootableItem(Vault.id("mystery_hostile_egg"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP),
                () -> ModConfigs.MYSTERY_HOSTILE_EGG.POOL.getRandom(new Random()).generateItemStack());
        ModItems.ACCELERATION_CHIP = new BasicTooltipItem(Vault.id("acceleration_chip"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP),
                new ITextComponent[] {
                        (ITextComponent) new StringTextComponent(
                                "Right click to install in an omega statue to accelerate")
                                .withStyle(TextFormatting.GRAY),
                        (ITextComponent) new StringTextComponent(
                                "the speed of production, each chip lowers the wait time by 5 seconds")
                                .withStyle(TextFormatting.GRAY) });
        ModItems.PANDORAS_BOX = new LootableItem(Vault.id("pandoras_box"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP),
                () -> ModConfigs.PANDORAS_BOX.POOL.getRandom(new Random()).generateItemStack());
        ModItems.ISKALLIUM_CHUNK = new BasicItem(Vault.id("chunk_iskallium"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.GORGINITE_CHUNK = new BasicItem(Vault.id("chunk_gorginite"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.SPARKLETINE_CHUNK = new BasicItem(Vault.id("chunk_sparkletine"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.ASHIUM_CHUNK = new BasicItem(Vault.id("chunk_ashium"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.BOMIGNITE_CHUNK = new BasicItem(Vault.id("chunk_bomignite"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.FUNSOIDE_CHUNK = new BasicItem(Vault.id("chunk_funsoide"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.TUBIUM_CHUNK = new BasicItem(Vault.id("chunk_tubium"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.UPALINE_CHUNK = new BasicItem(Vault.id("chunk_upaline"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.PUFFIUM_CHUNK = new BasicItem(Vault.id("chunk_puffium"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.OMEGA_POG = new BasicItem(Vault.id("omega_pog"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.ETERNAL_SOUL = new BasicItem(Vault.id("eternal_soul"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.SPARK = new BasicItem(Vault.id("spark"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.STAR_SHARD = new BasicItem(Vault.id("star_shard"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.STAR_CORE = new BasicItem(Vault.id("star_core"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.STAR_ESSENCE = new BasicItem(Vault.id("star_essence"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.KNOWLEDGE_STAR = new ItemKnowledgeStar(ModItems.VAULT_MOD_GROUP);
        ModItems.SWORD = new VaultSwordItem(Vault.id("sword"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(1));
        ModItems.AXE = new VaultAxeItem(Vault.id("axe"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(1));
        ModItems.HELMET = new VaultArmorItem(Vault.id("helmet"), EquipmentSlotType.HEAD,
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(1));
        ModItems.CHESTPLATE = new VaultArmorItem(Vault.id("chestplate"), EquipmentSlotType.CHEST,
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(1));
        ModItems.LEGGINGS = new VaultArmorItem(Vault.id("leggings"), EquipmentSlotType.LEGS,
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(1));
        ModItems.BOOTS = new VaultArmorItem(Vault.id("boots"), EquipmentSlotType.FEET,
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(1));
        ModItems.ETCHING = new EtchingItem(Vault.id("etching"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(1));
        ModItems.ETCHING_FRAGMENT = new BasicItem(Vault.id("etching_fragment"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.IDOL_BENEVOLENT = new IdolItem(Vault.id("idol_benevolent"), PlayerFavourData.VaultGodType.BENEVOLENT,
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(1));
        ModItems.IDOL_OMNISCIENT = new IdolItem(Vault.id("idol_omniscient"), PlayerFavourData.VaultGodType.OMNISCIENT,
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(1));
        ModItems.IDOL_TIMEKEEPER = new IdolItem(Vault.id("idol_timekeeper"), PlayerFavourData.VaultGodType.TIMEKEEPER,
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(1));
        ModItems.IDOL_MALEVOLENCE = new IdolItem(Vault.id("idol_malevolence"),
                PlayerFavourData.VaultGodType.MALEVOLENCE,
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(1));
        ModItems.PUZZLE_RUNE = new PuzzleRuneItem(Vault.id("puzzle_rune"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(1));
        ModItems.INFUSED_ETERNAL_SOUL = new BasicItem(Vault.id("infused_eternal_soul"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.UNKNOWN_EGG = new UnknownEggItem(Vault.id("unknown_egg"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.CANDY_BAR = new VaultConsumableItem(Vault.id("candy_bar"));
        ModItems.POWER_BAR = new VaultConsumableItem(Vault.id("power_bar"));
        ModItems.JADE_APPLE = new VaultConsumableItem(Vault.id("jade_apple"));
        ModItems.COBALT_APPLE = new VaultConsumableItem(Vault.id("cobalt_apple"));
        ModItems.PIXIE_APPLE = new VaultConsumableItem(Vault.id("pixie_apple"));
        ModItems.VAULT_APPLE = new BasicItem(Vault.id("vault_apple"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.LUCKY_APPLE = new VaultConsumableItem(Vault.id("lucky_apple"));
        ModItems.TREASURE_APPLE = new VaultConsumableItem(Vault.id("treasure_apple"));
        ModItems.POWER_APPLE = new VaultConsumableItem(Vault.id("power_apple"));
        ModItems.GHOST_APPLE = new VaultConsumableItem(Vault.id("ghost_apple"));
        ModItems.GOLEM_APPLE = new VaultConsumableItem(Vault.id("golem_apple"));
        ModItems.SWEET_APPLE = new VaultConsumableItem(Vault.id("sweet_apple"));
        ModItems.HEARTY_APPLE = new VaultConsumableItem(Vault.id("hearty_apple"));
        ModItems.PERFECT_ALEXANDRITE = new BasicItem(Vault.id("perfect_alexandrite"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.PERFECT_PAINITE = new BasicItem(Vault.id("perfect_painite"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.PERFECT_BENITOITE = new BasicItem(Vault.id("perfect_benitoite"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.PERFECT_LARIMAR = new BasicItem(Vault.id("perfect_larimar"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.PERFECT_BLACK_OPAL = new BasicItem(Vault.id("perfect_black_opal"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.PERFECT_ECHO_GEM = new BasicItem(Vault.id("perfect_echo_gem"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.PERFECT_WUTODIE = new BasicItem(Vault.id("perfect_wutodie"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.VAULTERITE_INGOT = new BasicItem(Vault.id("vaulterite_ingot"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.RED_VAULT_ESSENCE = new BasicItem(Vault.id("red_vault_essence"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.VAULT_DUST = new BasicItem(Vault.id("vault_dust"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.VAULT_NUGGET = new BasicItem(Vault.id("vault_nugget"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.VAULT_BRONZE = new BasicItem(Vault.id("vault_bronze"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.VAULT_SILVER = new BasicItem(Vault.id("vault_silver"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.MAGNETITE = new BasicTooltipItem(Vault.id("magnetite"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP),
                new ITextComponent[] {
                        (ITextComponent) new StringTextComponent("Can be used to repair vault magnets,")
                                .withStyle(TextFormatting.GRAY),
                        (ITextComponent) new StringTextComponent("or craft new ones.")
                                .withStyle(TextFormatting.GRAY) });
        ModItems.MAGNET_CORE_WEAK = new BasicItem(Vault.id("magnet_core_weak"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.MAGNET_CORE_STRONG = new BasicItem(Vault.id("magnet_core_strong"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.MAGNET_CORE_OMEGA = new BasicItem(Vault.id("magnet_core_omega"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.VAULT_ESSENCE = new BasicTooltipItem(Vault.id("vault_essence"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP),
                new ITextComponent[] {
                        (ITextComponent) new StringTextComponent("A rare essence of the vaults.")
                                .withStyle(TextFormatting.GRAY),
                        (ITextComponent) new StringTextComponent(
                                "Used as an ingredient in several crafting recipes, including regeneration potions.")
                                .withStyle(TextFormatting.GRAY) });
        ModItems.REPAIR_CORE = new VaultRepairCoreItem(Vault.id("repair_core"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP), 0,
                new ITextComponent[] {
                        (ITextComponent) new StringTextComponent("Can be used to fully repair any vault gear,")
                                .withStyle(TextFormatting.GRAY),
                        (ITextComponent) new StringTextComponent("as long as it has repair slots remaining.")
                                .withStyle(TextFormatting.GRAY) });
        ModItems.REPAIR_CORE_T2 = new VaultRepairCoreItem(Vault.id("repair_core_t2"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP), 1,
                new ITextComponent[] {
                        (ITextComponent) new StringTextComponent("Can be used to fully repair any vault gear,")
                                .withStyle(TextFormatting.GRAY),
                        (ITextComponent) new StringTextComponent("as long as it has repair slots remaining.")
                                .withStyle(TextFormatting.GRAY) });
        ModItems.REPAIR_CORE_T3 = new VaultRepairCoreItem(Vault.id("repair_core_t3"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP), 2,
                new ITextComponent[] {
                        (ITextComponent) new StringTextComponent("Can be used to fully repair any vault gear,")
                                .withStyle(TextFormatting.GRAY),
                        (ITextComponent) new StringTextComponent("as long as it has repair slots remaining.")
                                .withStyle(TextFormatting.GRAY) });
        ModItems.VAULT_PLATING = new VaultPlateItem(Vault.id("vault_plating"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP), 0,
                new ITextComponent[] {
                        (ITextComponent) new StringTextComponent(
                                "Permanently adds 50 max durability to any vault gear.")
                                .withStyle(TextFormatting.GRAY),
                        (ITextComponent) new StringTextComponent(
                                "Maximum 20 plates can be attached to any one vault gear.")
                                .withStyle(TextFormatting.GRAY) });
        ModItems.VAULT_PLATING_T2 = new VaultPlateItem(Vault.id("vault_plating_t2"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP), 1,
                new ITextComponent[] {
                        (ITextComponent) new StringTextComponent(
                                "Permanently adds 50 max durability to any vault gear.")
                                .withStyle(TextFormatting.GRAY),
                        (ITextComponent) new StringTextComponent(
                                "Maximum 20 plates can be attached to any one vault gear.")
                                .withStyle(TextFormatting.GRAY) });
        ModItems.VAULT_PLATING_T3 = new VaultPlateItem(Vault.id("vault_plating_t3"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP), 2,
                new ITextComponent[] {
                        (ITextComponent) new StringTextComponent(
                                "Permanently adds 50 max durability to any vault gear.")
                                .withStyle(TextFormatting.GRAY),
                        (ITextComponent) new StringTextComponent(
                                "Maximum 20 plates can be attached to any one vault gear.")
                                .withStyle(TextFormatting.GRAY) });
        ModItems.WUTAX_SHARD = new WutaxShardItem(Vault.id("wutax_shard"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.WUTAX_CRYSTAL = new BasicItem(Vault.id("wutax_crystal"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.VAULT_CATALYST = new VaultCatalystItem(ModItems.VAULT_MOD_GROUP, Vault.id("vault_catalyst"));
        ModItems.VAULT_INHIBITOR = new VaultInhibitorItem(ModItems.VAULT_MOD_GROUP, Vault.id("vault_inhibitor"));
        ModItems.PAINITE_STAR = new BasicTooltipItem(Vault.id("painite_star"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(4),
                new ITextComponent[] {
                        (ITextComponent) new StringTextComponent("Reroll the vault catalyst combination results")
                                .withStyle(TextFormatting.GRAY) });
        ModItems.VAULT_RUNE_MINE = new VaultRuneItem(ModItems.VAULT_MOD_GROUP, Vault.id("vault_rune_mineshaft"),
                "mineshaft");
        ModItems.VAULT_RUNE_PUZZLE = new VaultRuneItem(ModItems.VAULT_MOD_GROUP, Vault.id("vault_rune_puzzle_cube"),
                "puzzle_cube");
        ModItems.VAULT_RUNE_DIGSITE = new VaultRuneItem(ModItems.VAULT_MOD_GROUP, Vault.id("vault_rune_digsite"),
                "digsite");
        ModItems.VAULT_RUNE_CRYSTAL = new VaultRuneItem(ModItems.VAULT_MOD_GROUP, Vault.id("vault_rune_crystal_caves"),
                "crystal_caves");
        ModItems.VAULT_RUNE_VIEWER = new VaultRuneItem(ModItems.VAULT_MOD_GROUP, Vault.id("vault_rune_viewer"),
                "viewer");
        ModItems.VAULT_RUNE_VENDOR = new VaultRuneItem(ModItems.VAULT_MOD_GROUP, Vault.id("vault_rune_vendor"),
                "vendor");
        ModItems.VAULT_RUNE_XMARK = new VaultRuneItem(ModItems.VAULT_MOD_GROUP, Vault.id("vault_rune_xmark"), "x_spot");
        ModItems.VAULT_CATALYST_FRAGMENT = new BasicTooltipItem(Vault.id("vault_catalyst_fragment"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP),
                new ITextComponent[] {
                        (ITextComponent) new StringTextComponent("Can be used to craft very powerful Catalysts,")
                                .withStyle(TextFormatting.GRAY),
                        (ITextComponent) new StringTextComponent("which can modify a vault crystal.")
                                .withStyle(TextFormatting.GRAY) });
        ModItems.VAULT_SAND = new BasicItem(Vault.id("vault_sand"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP));
        ModItems.SOUL_FLAME = new BasicItem(Vault.id("soul_flame"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(3));
        ModItems.VAULT_GEAR = new BasicItem(Vault.id("vault_gear"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(4));
        ModItems.CRYSTAL_SEAL_EXECUTIONER = new ItemVaultCrystalSeal(Vault.id("crystal_seal_executioner"),
                Vault.id("summon_and_kill_boss"));
        ModItems.CRYSTAL_SEAL_HUNTER = new ItemVaultCrystalSeal(Vault.id("crystal_seal_hunter"),
                Vault.id("scavenger_hunt"));
        ModItems.CRYSTAL_SEAL_ARCHITECT = new ItemVaultCrystalSeal(Vault.id("crystal_seal_architect"),
                Vault.id("architect"));
        ModItems.CRYSTAL_SEAL_ANCIENTS = new ItemVaultCrystalSeal(Vault.id("crystal_seal_ancients"),
                Vault.id("ancients"));
        ModItems.CRYSTAL_SEAL_RAID = new ItemVaultCrystalSeal(Vault.id("crystal_seal_raid"),
                Vault.id("raid_challenge"));
        ModItems.CRYSTAL_SEAL_RAFFLE = new ItemVaultRaffleSeal(Vault.id("crystal_seal_raffle"));
        ModItems.GEAR_CHARM = new BasicTooltipItem(Vault.id("gear_charm"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP),
                new ITextComponent[] {
                        (ITextComponent) new StringTextComponent("Combine with an unidentified vault gear in an anvil")
                                .withStyle(TextFormatting.GRAY),
                        (ITextComponent) new StringTextComponent("to increase its tier to Tier 2.")
                                .withStyle(TextFormatting.GRAY),
                        (ITextComponent) new StringTextComponent("Requires level: ").withStyle(TextFormatting.GRAY)
                                .append((ITextComponent) new StringTextComponent("100")
                                        .withStyle(TextFormatting.AQUA)) });
        ModItems.GEAR_CHARM_T3 = new BasicTooltipItem(Vault.id("gear_charm_tier_3"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP),
                new ITextComponent[] {
                        (ITextComponent) new StringTextComponent("Combine with an unidentified vault gear in an anvil")
                                .withStyle(TextFormatting.GRAY),
                        (ITextComponent) new StringTextComponent("to increase its tier to Tier 3.")
                                .withStyle(TextFormatting.GRAY),
                        (ITextComponent) new StringTextComponent("Requires level: ").withStyle(TextFormatting.GRAY)
                                .append((ITextComponent) new StringTextComponent("200")
                                        .withStyle(TextFormatting.AQUA)) });
        ModItems.IDENTIFICATION_TOME = new BasicTooltipItem(Vault.id("identification_tome"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(1),
                new ITextComponent[] {
                        (ITextComponent) new StringTextComponent("Hold in the off hand to instantly")
                                .withStyle(TextFormatting.GRAY),
                        (ITextComponent) new StringTextComponent("identify vault gear.")
                                .withStyle(TextFormatting.GRAY) });
        ModItems.BANISHED_SOUL = new BasicTooltipItem(Vault.id("banished_soul"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(1),
                new ITextComponent[] {
                        (ITextComponent) new StringTextComponent("Adds 3000 Durability to a Vault idol.")
                                .withStyle(TextFormatting.GRAY),
                        (ITextComponent) new StringTextComponent("Has a 1 in 3 chance to break the idol when applying.")
                                .withStyle(TextFormatting.GRAY) });
        ModItems.UNKNOWN_ITEM = new BasicItem(Vault.id("unknown_item"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(1));
        ModItems.SOUL_SHARD = new BasicItem(Vault.id("soul_shard"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(64));
        ModItems.SHARD_POUCH = new ItemShardPouch(Vault.id("shard_pouch"));
        ModItems.VAULT_PAXEL = new VaultPaxelItem(Vault.id("vault_paxel"));
        ModItems.PAXEL_CHARM = new BasicTooltipItem(Vault.id("paxel_charm"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(1),
                new ITextComponent[] {
                        (ITextComponent) new StringTextComponent("Combine with a paxel in anvil to enhance it.")
                                .withStyle(TextFormatting.AQUA),
                        (ITextComponent) new StringTextComponent("A paxel can have only one enhancement.")
                                .withStyle(TextFormatting.GRAY) });
        ModItems.INFINITE_WATER_BUCKET = new InfiniteWaterBucketItem(Vault.id("infinite_water_bucket"));
        ModItems.VAULT_PEARL = new VaultPearlItem(Vault.id("vault_pearl"));
        ModItems.SCAVENGER_CREEPER_EYE = new BasicScavengerItem("creeper_eye");
        ModItems.SCAVENGER_CREEPER_FOOT = new BasicScavengerItem("creeper_foot");
        ModItems.SCAVENGER_CREEPER_FUSE = new BasicScavengerItem("creeper_fuse");
        ModItems.SCAVENGER_CREEPER_TNT = new BasicScavengerItem("creeper_tnt");
        ModItems.SCAVENGER_CREEPER_VIAL = new BasicScavengerItem("creeper_vial");
        ModItems.SCAVENGER_CREEPER_CHARM = new BasicScavengerItem("creeper_soul_charm");
        ModItems.SCAVENGER_DROWNED_BARNACLE = new BasicScavengerItem("drowned_barnacle");
        ModItems.SCAVENGER_DROWNED_EYE = new BasicScavengerItem("drowned_eye");
        ModItems.SCAVENGER_DROWNED_HIDE = new BasicScavengerItem("drowned_hide");
        ModItems.SCAVENGER_DROWNED_VIAL = new BasicScavengerItem("drowned_vial");
        ModItems.SCAVENGER_DROWNED_CHARM = new BasicScavengerItem("drowned_soul_charm");
        ModItems.SCAVENGER_SKELETON_SHARD = new BasicScavengerItem("skeleton_bone_shard");
        ModItems.SCAVENGER_SKELETON_EYE = new BasicScavengerItem("skeleton_milky_eye");
        ModItems.SCAVENGER_SKELETON_RIBCAGE = new BasicScavengerItem("skeleton_ribcage");
        ModItems.SCAVENGER_SKELETON_SKULL = new BasicScavengerItem("skeleton_skull");
        ModItems.SCAVENGER_SKELETON_WISHBONE = new BasicScavengerItem("skeleton_wishbone");
        ModItems.SCAVENGER_SKELETON_VIAL = new BasicScavengerItem("skeleton_milky_vial");
        ModItems.SCAVENGER_SKELETON_CHARM = new BasicScavengerItem("skeleton_soul_charm");
        ModItems.SCAVENGER_SPIDER_FANGS = new BasicScavengerItem("spider_fangs");
        ModItems.SCAVENGER_SPIDER_LEG = new BasicScavengerItem("spider_leg");
        ModItems.SCAVENGER_SPIDER_WEBBING = new BasicScavengerItem("spider_webbing_spool");
        ModItems.SCAVENGER_SPIDER_CURSED_CHARM = new BasicScavengerItem("spider_cursed_charm");
        ModItems.SCAVENGER_SPIDER_VIAL = new BasicScavengerItem("spider_vial");
        ModItems.SCAVENGER_SPIDER_CHARM = new BasicScavengerItem("spider_soul_charm");
        ModItems.SCAVENGER_ZOMBIE_BRAIN = new BasicScavengerItem("zombie_brain");
        ModItems.SCAVENGER_ZOMBIE_ARM = new BasicScavengerItem("zombie_arm");
        ModItems.SCAVENGER_ZOMBIE_EAR = new BasicScavengerItem("zombie_ear");
        ModItems.SCAVENGER_ZOMBIE_EYE = new BasicScavengerItem("zombie_eye");
        ModItems.SCAVENGER_ZOMBIE_HIDE = new BasicScavengerItem("zombie_hide");
        ModItems.SCAVENGER_ZOMBIE_NOSE = new BasicScavengerItem("zombie_nose");
        ModItems.SCAVENGER_ZOMBIE_VIAL = new BasicScavengerItem("zombie_blood_vial");
        ModItems.SCAVENGER_TREASURE_BANGLE_BLUE = new BasicScavengerItem("blue_bangle");
        ModItems.SCAVENGER_TREASURE_BANGLE_PINK = new BasicScavengerItem("pink_bangle");
        ModItems.SCAVENGER_TREASURE_BANGLE_GREEN = new BasicScavengerItem("green_bangle");
        ModItems.SCAVENGER_TREASURE_EARRINGS = new BasicScavengerItem("earrings");
        ModItems.SCAVENGER_TREASURE_GOBLET = new BasicScavengerItem("goblet");
        ModItems.SCAVENGER_TREASURE_SACK = new BasicScavengerItem("sack");
        ModItems.SCAVENGER_TREASURE_SCROLL_RED = new BasicScavengerItem("red_scroll");
        ModItems.SCAVENGER_TREASURE_SCROLL_BLUE = new BasicScavengerItem("blue_scroll");
        ModItems.SCAVENGER_SCRAP_BROKEN_POTTERY = new BasicScavengerItem("broken_pottery");
        ModItems.SCAVENGER_SCRAP_CRACKED_PEARL = new BasicScavengerItem("cracked_pearl");
        ModItems.SCAVENGER_SCRAP_CRACKED_SCRIPT = new BasicScavengerItem("cracked_script");
        ModItems.SCAVENGER_SCRAP_EMPTY_JAR = new BasicScavengerItem("empty_jar");
        ModItems.SCAVENGER_SCRAP_OLD_BOOK = new BasicScavengerItem("old_book");
        ModItems.SCAVENGER_SCRAP_POTTERY_SHARD = new BasicScavengerItem("pottery_shard");
        ModItems.SCAVENGER_SCRAP_POULTICE_JAR = new BasicScavengerItem("poultice_jar");
        ModItems.SCAVENGER_SCRAP_PRESERVES_JAR = new BasicScavengerItem("preserves_jar");
        ModItems.SCAVENGER_SCRAP_RIPPED_PAGE = new BasicScavengerItem("ripped_page");
        ModItems.SCAVENGER_SCRAP_SADDLE_BAG = new BasicScavengerItem("saddle_bag");
        ModItems.SCAVENGER_SCRAP_SPICE_JAR = new BasicScavengerItem("spice_jar");
        ModItems.SCAVENGER_SCRAP_WIZARD_WAND = new BasicScavengerItem("wizard_wand");
        ModItems.VAULT_MAGNET_WEAK = new VaultMagnetItem(Vault.id("vault_magnet_weak"),
                VaultMagnetItem.MagnetType.WEAK);
        ModItems.VAULT_MAGNET_STRONG = new VaultMagnetItem(Vault.id("vault_magnet_strong"),
                VaultMagnetItem.MagnetType.STRONG);
        ModItems.VAULT_MAGNET_OMEGA = new VaultMagnetItem(Vault.id("vault_magnet_omega"),
                VaultMagnetItem.MagnetType.OMEGA);
        VOID_LIQUID_BUCKET = (BucketItem) new BucketItem((Supplier) ModFluids.VOID_LIQUID, new Item.Properties()
                .craftRemainder(Items.BUCKET).stacksTo(1).tab(ModItems.VAULT_MOD_GROUP))
                .setRegistryName(Vault.id("void_liquid_bucket"));
        TENOS_ESSENCE = (GodEssenceItem) new GodEssenceItem(Vault.id("god_essence_tenos"),
                PlayerFavourData.VaultGodType.OMNISCIENT,
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(1))
                .withTooltip((ITextComponent) new StringTextComponent(
                        "The eyes are the first to aquire new knowledge when experiencing")
                        .withStyle(TextFormatting.ITALIC));
        VELARA_ESSENCE = (GodEssenceItem) new GodEssenceItem(Vault.id("god_essence_velara"),
                PlayerFavourData.VaultGodType.BENEVOLENT,
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(1))
                .withTooltip((ITextComponent) new StringTextComponent(
                        "Life might start blind, but is always seen by the eyes of others")
                        .withStyle(TextFormatting.ITALIC));
        WENDARR_ESSENCE = (GodEssenceItem) new GodEssenceItem(Vault.id("god_essence_wendarr"),
                PlayerFavourData.VaultGodType.TIMEKEEPER,
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(1))
                .withTooltip((ITextComponent) new StringTextComponent("Time can only be perceived by the eyes of a god")
                        .withStyle(TextFormatting.ITALIC));
        IDONA_ESSENCE = (GodEssenceItem) new GodEssenceItem(Vault.id("god_essence_idona"),
                PlayerFavourData.VaultGodType.MALEVOLENCE,
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(1))
                .withTooltip((ITextComponent) new StringTextComponent(
                        "Sacrifices are something that please the eyes of the gods")
                        .withStyle(TextFormatting.ITALIC));
        ModItems.MOD_BOX = new GatedLootableItem(Vault.id("mod_box"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP),
                new ITextComponent[] { (ITextComponent) new StringTextComponent(
                        "Contains a random modded item from any of your unlocked mods") });
        ModItems.UNIDENTIFIED_TREASURE_KEY = new LootableItem(Vault.id("unidentified_treasure_key"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP),
                () -> ModConfigs.UNIDENTIFIED_TREASURE_KEY.getRandomKey(new Random()));
        ModItems.VOID_ORB = new VoidOrbItem(Vault.id("void_orb"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(1));
        ModItems.CRYSTAL_BURGER = new BasicTooltipItem(Vault.id("crystal_burger"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP),
                new ITextComponent[] { (ITextComponent) new StringTextComponent("Eternal Exp food")
                        .withStyle(TextFormatting.GOLD) });
        ModItems.FULL_PIZZA = new BasicTooltipItem(Vault.id("full_pizza"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP),
                new ITextComponent[] { (ITextComponent) new StringTextComponent("Eternal Exp food")
                        .withStyle(TextFormatting.GOLD) });
        ModItems.LIFE_SCROLL = new BasicTooltipItem(Vault.id("life_scroll"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP),
                new ITextComponent[] { (ITextComponent) new StringTextComponent("Revives a unalived eternal")
                        .withStyle(TextFormatting.GRAY) });
        ModItems.AURA_SCROLL = new BasicTooltipItem(Vault.id("aura_scroll"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP),
                new ITextComponent[] { (ITextComponent) new StringTextComponent("Rerolls an eternal's available auras")
                        .withStyle(TextFormatting.GRAY) });
        ModItems.ARTISAN_SCROLL = new ArtisanScrollItem(Vault.id("artisan_scroll"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(1));
        ModItems.FABRICATION_JEWEL = new BasicTooltipItem(Vault.id("fabrication_jewel"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP),
                new ITextComponent[] { (ITextComponent) new StringTextComponent("Requires Talent: ")
                        .withStyle(TextFormatting.GRAY)
                        .append((ITextComponent) new StringTextComponent("Artisan")
                                .withStyle(TextFormatting.AQUA)) });
        ModItems.ARMOR_CRATE_HELLCOW = new ItemModArmorCrate(Vault.id("armor_crate_hellcow"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(64),
                () -> ImmutableList.of((Object) ModModels.SpecialGearModel.HELLCOW_SET));
        ModItems.ARMOR_CRATE_BOTANIA = new ItemModArmorCrate(Vault.id("armor_crate_botania"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(64),
                () -> ImmutableList.of((Object) ModModels.SpecialGearModel.BOTANIA_SET));
        ModItems.ARMOR_CRATE_CREATE = new ItemModArmorCrate(Vault.id("armor_crate_create"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(64),
                () -> ImmutableList.of((Object) ModModels.SpecialGearModel.CREATE_SET));
        ModItems.ARMOR_CRATE_DANK = new ItemModArmorCrate(Vault.id("armor_crate_dank"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(64),
                () -> ImmutableList.of((Object) ModModels.SpecialGearModel.DANK_SET));
        ModItems.ARMOR_CRATE_FLUX = new ItemModArmorCrate(Vault.id("armor_crate_flux"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(64),
                () -> ImmutableList.of((Object) ModModels.SpecialGearModel.FLUX_SET));
        ModItems.ARMOR_CRATE_IMMERSIVE_ENGINEERING = new ItemModArmorCrate(Vault.id("armor_crate_ie"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(64),
                () -> ImmutableList.of((Object) ModModels.SpecialGearModel.IMMERSIVE_ENGINEERING_SET));
        ModItems.ARMOR_CRATE_MEKA = new ItemModArmorCrate(Vault.id("armor_crate_meka"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(64),
                () -> ImmutableList.of((Object) ModModels.SpecialGearModel.MEKA_SET_DARK,
                        (Object) ModModels.SpecialGearModel.MEKA_SET_LIGHT));
        ModItems.ARMOR_CRATE_POWAH = new ItemModArmorCrate(Vault.id("armor_crate_powah"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(64),
                () -> ImmutableList.of((Object) ModModels.SpecialGearModel.POWAH_SET));
        ModItems.ARMOR_CRATE_THERMAL = new ItemModArmorCrate(Vault.id("armor_crate_thermal"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(64),
                () -> ImmutableList.of((Object) ModModels.SpecialGearModel.THERMAL_SET));
        ModItems.ARMOR_CRATE_TRASH = new ItemModArmorCrate(Vault.id("armor_crate_trash"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(64),
                () -> ImmutableList.of((Object) ModModels.SpecialGearModel.TRASH_SET));
        ModItems.ARMOR_CRATE_VILLAGER = new ItemModArmorCrate(Vault.id("armor_crate_villager"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(64),
                () -> ImmutableList.of((Object) ModModels.SpecialGearModel.VILLAGER_SET));
        ModItems.ARMOR_CRATE_AUTOMATIC = new ItemModArmorCrate(Vault.id("armor_crate_automatic"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(64),
                () -> ImmutableList.of((Object) ModModels.SpecialGearModel.AUTOMATIC_SET));
        ModItems.ARMOR_CRATE_FAIRY = new ItemModArmorCrate(Vault.id("armor_crate_fairy"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(64),
                () -> ImmutableList.of((Object) ModModels.SpecialGearModel.FAIRY_SET));
        ModItems.ARMOR_CRATE_BUILDING = new ItemModArmorCrate(Vault.id("armor_crate_building"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(64),
                () -> ImmutableList.of((Object) ModModels.SpecialGearModel.BUILDING_SET));
        ModItems.ARMOR_CRATE_ZOMBIE = new ItemModArmorCrate(Vault.id("armor_crate_zombie"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(64),
                () -> ImmutableList.of((Object) ModModels.SpecialGearModel.ZOMBIE_SET));
        ModItems.ARMOR_CRATE_XNET = new ItemModArmorCrate(Vault.id("armor_crate_xnet"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(64),
                () -> ImmutableList.of((Object) ModModels.SpecialGearModel.XNET_SET));
        ModItems.ARMOR_CRATE_TEST_DUMMY = new ItemModArmorCrate(Vault.id("armor_crate_test_dummy"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(64),
                () -> ImmutableList.of((Object) ModModels.SpecialGearModel.TEST_DUMMY_SET));
        ModItems.ARMOR_CRATE_INDUSTRIAL_FOREGOING = new ItemModArmorCrate(Vault.id("armor_crate_if"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(64),
                () -> ImmutableList.of((Object) ModModels.SpecialGearModel.INDUSTRIAL_FOREGOING_SET));
        ModItems.ARMOR_CRATE_CAKE = new ItemModArmorCrate(Vault.id("armor_crate_cake"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(64),
                () -> ImmutableList.of((Object) ModModels.SpecialGearModel.CAKE_SET));
        ModItems.FLAWED_RUBY = new FlawedRubyItem(Vault.id("flawed_ruby"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP),
                new ITextComponent[] {
                        (ITextComponent) new StringTextComponent("Combine with a gear piece in an anvil")
                                .withStyle(TextFormatting.GRAY),
                        (ITextComponent) new StringTextComponent("to add additional modifier(s) with a")
                                .withStyle(TextFormatting.GRAY),
                        (ITextComponent) new StringTextComponent("chance that the gear will break.")
                                .withStyle(TextFormatting.GRAY),
                        (ITextComponent) new StringTextComponent(" ").withStyle(TextFormatting.GRAY),
                        (ITextComponent) new StringTextComponent("Requires Talent: ")
                                .withStyle(TextFormatting.GRAY)
                                .append((ITextComponent) new StringTextComponent("Artisan ")
                                        .withStyle(TextFormatting.AQUA))
                                .append((ITextComponent) new StringTextComponent("or ")
                                        .withStyle(TextFormatting.GRAY))
                                .append((ITextComponent) new StringTextComponent("Treasure Hunter")
                                        .withStyle(TextFormatting.AQUA)) });
        ModItems.ARTIFACT_FRAGMENT = new BasicItem(Vault.id("artifact_fragment"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(3).fireResistant());
        ModItems.VAULT_CHARM = new BasicTooltipItem(Vault.id("vault_charm"),
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(1),
                new ITextComponent[] {
                        (ITextComponent) new StringTextComponent("When this charm is in your inventory")
                                .withStyle(TextFormatting.GRAY),
                        (ITextComponent) new StringTextComponent("it will automatically void any item")
                                .withStyle(TextFormatting.GRAY),
                        (ITextComponent) new StringTextComponent("whitelisted in the Vault Controller")
                                .withStyle(TextFormatting.GRAY),
                        (ITextComponent) new StringTextComponent("on pickup in a Vault.")
                                .withStyle(TextFormatting.GRAY) });
        ModItems.CHARM_UPGRADE_TIER_1 = new VaultCharmUpgrade(Vault.id("charm_upgrade_tier_1"),
                VaultCharmUpgrade.Tier.ONE,
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(1).fireResistant());
        ModItems.CHARM_UPGRADE_TIER_2 = new VaultCharmUpgrade(Vault.id("charm_upgrade_tier_2"),
                VaultCharmUpgrade.Tier.TWO,
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(1).fireResistant());
        ModItems.CHARM_UPGRADE_TIER_3 = new VaultCharmUpgrade(Vault.id("charm_upgrade_tier_3"),
                VaultCharmUpgrade.Tier.THREE,
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(1).fireResistant());
        ModItems.CHARM_UPGRADE_TIER_4 = new VaultCharmUpgrade(Vault.id("charm_upgrade_tier_4"),
                VaultCharmUpgrade.Tier.FOUR,
                new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(1).fireResistant());
        ModItems.BURNT_CRYSTAL = new BurntCrystalItem(ModItems.VAULT_MOD_GROUP, Vault.id("burnt_crystal"));
        ModItems.KEYSTONE_IDONA = new FinalVaultKeystoneItem(Vault.id("final_keystone_idona"),
                PlayerFavourData.VaultGodType.MALEVOLENCE);
        ModItems.KEYSTONE_TENOS = new FinalVaultKeystoneItem(Vault.id("final_keystone_tenos"),
                PlayerFavourData.VaultGodType.OMNISCIENT);
        ModItems.KEYSTONE_VELARA = new FinalVaultKeystoneItem(Vault.id("final_keystone_velara"),
                PlayerFavourData.VaultGodType.BENEVOLENT);
        ModItems.KEYSTONE_WENDARR = new FinalVaultKeystoneItem(Vault.id("final_keystone_wendarr"),
                PlayerFavourData.VaultGodType.TIMEKEEPER);
    }
}