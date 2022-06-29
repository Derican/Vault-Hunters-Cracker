
package iskallia.vault;

import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.server.ServerWorld;
import iskallia.vault.world.data.GlobalDifficultyData;
import iskallia.vault.init.ModConfigs;
import iskallia.vault.world.data.SoulShardTraderData;
import iskallia.vault.world.data.EternalsData;
import iskallia.vault.world.data.PlayerTalentsData;
import iskallia.vault.world.data.PlayerAbilitiesData;
import iskallia.vault.world.data.PlayerResearchesData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import iskallia.vault.init.ModFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraft.command.CommandSource;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import iskallia.vault.init.ModPotions;
import iskallia.vault.init.ModFluids;
import iskallia.vault.init.ModParticles;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import iskallia.vault.integration.IntegrationDankStorage;
import net.minecraftforge.fml.ModList;
import iskallia.vault.init.ModCommands;
import iskallia.vault.dump.VaultDataDump;
import iskallia.vault.util.scheduler.DailyScheduler;
import iskallia.vault.world.data.PlayerVaultStatsData;
import iskallia.vault.world.gen.structure.VaultJigsawHelper;
import iskallia.vault.util.ServerScheduler;
import iskallia.vault.world.vault.event.VaultListener;
import java.util.function.Consumer;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.common.MinecraftForge;
import net.minecraft.world.World;
import net.minecraft.util.RegistryKey;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.Mod;

@Mod("the_vault")
public class Vault {
    public static final String MOD_ID = "the_vault";
    public static final Logger LOGGER;
    public static RegistryKey<World> VAULT_KEY;
    public static RegistryKey<World> OTHER_SIDE_KEY;

    public Vault() {
        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, (Consumer) this::onCommandRegister);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, (Consumer) this::onBiomeLoad);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, (Consumer) this::onBiomeLoadPost);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, (Consumer) this::onPlayerLoggedIn);
        MinecraftForge.EVENT_BUS.addListener((Consumer) VaultListener::onEvent);
        MinecraftForge.EVENT_BUS.addListener((Consumer) ServerScheduler.INSTANCE::onServerTick);
        MinecraftForge.EVENT_BUS.addListener((Consumer) VaultJigsawHelper::preloadVaultRooms);
        MinecraftForge.EVENT_BUS.addListener((Consumer) PlayerVaultStatsData::onStartup);
        MinecraftForge.EVENT_BUS.addListener((Consumer) DailyScheduler::start);
        MinecraftForge.EVENT_BUS.addListener((Consumer) DailyScheduler::stop);
        MinecraftForge.EVENT_BUS.addListener((Consumer) VaultDataDump::onStart);
        this.registerDeferredRegistries();
        ModCommands.registerArgumentTypes();
        if (ModList.get().isLoaded("dankstorage")) {
            MinecraftForge.EVENT_BUS.register((Object) IntegrationDankStorage.class);
        }
    }

    public void registerDeferredRegistries() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModParticles.REGISTRY.register(modEventBus);
        ModFluids.REGISTRY.register(modEventBus);
        ModPotions.REGISTRY.register(modEventBus);
    }

    public void onCommandRegister(final RegisterCommandsEvent event) {
        ModCommands.registerCommands((CommandDispatcher<CommandSource>) event.getDispatcher(), event.getEnvironment());
    }

    public void onBiomeLoad(final BiomeLoadingEvent event) {
        event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                (ConfiguredFeature) ModFeatures.VAULT_ROCK_ORE);
    }

    public void onBiomeLoadPost(final BiomeLoadingEvent event) {
        if (event.getName().equals((Object) id("spoopy"))) {
            for (final GenerationStage.Decoration stage : GenerationStage.Decoration.values()) {
                event.getGeneration().getFeatures(stage).clear();
            }
            event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION,
                    (ConfiguredFeature) ModFeatures.BREADCRUMB_CHEST);
        }
    }

    public void onPlayerLoggedIn(final PlayerEvent.PlayerLoggedInEvent event) {
        final ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        final ServerWorld serverWorld = player.getLevel();
        final MinecraftServer server = player.getServer();
        PlayerVaultStatsData.get(serverWorld).getVaultStats((PlayerEntity) player).sync(server);
        PlayerResearchesData.get(serverWorld).getResearches((PlayerEntity) player).sync(server);
        PlayerAbilitiesData.get(serverWorld).getAbilities((PlayerEntity) player).sync(server);
        PlayerTalentsData.get(serverWorld).getTalents((PlayerEntity) player).sync(server);
        EternalsData.get(serverWorld).syncTo(player);
        SoulShardTraderData.get(serverWorld).syncTo(player);
        ModConfigs.SOUL_SHARD.syncTo(player);
        GlobalDifficultyData.get(serverWorld).openDifficultySelection(player);
    }

    public static String sId(final String name) {
        return "the_vault:" + name;
    }

    public static ResourceLocation id(final String name) {
        return new ResourceLocation("the_vault", name);
    }

    static {
        LOGGER = LogManager.getLogger();
        Vault.VAULT_KEY = (RegistryKey<World>) RegistryKey.create(Registry.DIMENSION_REGISTRY, id("vault"));
        Vault.OTHER_SIDE_KEY = (RegistryKey<World>) RegistryKey.create(Registry.DIMENSION_REGISTRY,
                id("the_other_side"));
    }
}
