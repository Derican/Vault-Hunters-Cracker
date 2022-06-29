// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.dump;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Map;
import java.util.UUID;
import com.google.gson.GsonBuilder;
import iskallia.vault.skill.ability.AbilityNode;
import iskallia.vault.skill.talent.TalentNode;
import net.minecraft.item.ItemStack;
import iskallia.vault.config.EternalAuraConfig;
import iskallia.vault.research.ResearchTree;
import iskallia.vault.skill.talent.TalentTree;
import iskallia.vault.skill.ability.AbilityTree;
import net.minecraft.util.ResourceLocation;
import java.util.Iterator;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import iskallia.vault.skill.PlayerVaultStats;
import net.minecraft.world.server.ServerWorld;
import iskallia.vault.init.ModConfigs;
import iskallia.vault.entity.eternal.EternalData;
import iskallia.vault.world.data.EternalsData;
import iskallia.vault.world.data.PlayerFavourData;
import iskallia.vault.util.calc.PlayerStatisticsCollector;
import java.util.Collection;
import iskallia.vault.world.data.PlayerResearchesData;
import iskallia.vault.world.data.PlayerTalentsData;
import iskallia.vault.world.data.PlayerAbilitiesData;
import java.util.Arrays;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.LivingEntity;
import iskallia.vault.util.calc.ThornsHelper;
import iskallia.vault.util.calc.FatalStrikeHelper;
import iskallia.vault.skill.ability.AbilityGroup;
import iskallia.vault.util.calc.CooldownHelper;
import iskallia.vault.util.calc.ResistanceHelper;
import iskallia.vault.util.calc.ParryHelper;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.entity.player.PlayerEntity;
import iskallia.vault.world.data.PlayerVaultStatsData;
import iskallia.vault.Vault;
import net.minecraft.entity.player.ServerPlayerEntity;
import com.google.gson.Gson;

public class PlayerSnapshotDump
{
    private static final Gson GSON;
    
    public static String createAndSerializeSnapshot(final ServerPlayerEntity sPlayer) {
        return PlayerSnapshotDump.GSON.toJson((Object)createSnapshot(sPlayer));
    }
    
    public static PlayerSnapshot createSnapshot(final ServerPlayerEntity sPlayer) {
        final PlayerSnapshot snapshot = new PlayerSnapshot(sPlayer);
        final ServerWorld sWorld = sPlayer.getLevel();
        snapshot.inVault = (sWorld.dimension() == Vault.VAULT_KEY);
        final PlayerVaultStats stats = PlayerVaultStatsData.get(sWorld).getVaultStats((PlayerEntity)sPlayer);
        snapshot.vaultLevel = stats.getVaultLevel();
        snapshot.levelPercent = stats.getExp() / (float)stats.getTnl();
        final AttributeModifierManager mgr = sPlayer.getAttributes();
        for (final Attribute attribute : ForgeRegistries.ATTRIBUTES) {
            if (mgr.hasAttribute(attribute)) {
                final ResourceLocation attrId = attribute.getRegistryName();
                snapshot.attributes.put((attrId == null) ? attribute.getDescriptionId() : attrId.toString(), mgr.getValue(attribute));
            }
        }
        snapshot.parry = ParryHelper.getPlayerParryChance(sPlayer);
        snapshot.resistance = ResistanceHelper.getPlayerResistancePercent(sPlayer);
        snapshot.cooldownReduction = CooldownHelper.getCooldownMultiplier(sPlayer, null);
        snapshot.fatalStrikeChance = FatalStrikeHelper.getPlayerFatalStrikeChance(sPlayer);
        snapshot.fatalStrikeDamage = FatalStrikeHelper.getPlayerFatalStrikeDamage(sPlayer);
        snapshot.thornsChance = ThornsHelper.getThornsChance((LivingEntity)sPlayer);
        snapshot.thornsDamage = ThornsHelper.getThornsDamage((LivingEntity)sPlayer);
        Arrays.stream(EquipmentSlotType.values()).forEach(slotType -> {
            final ItemStack stack = sPlayer.getItemBySlot(slotType);
            if (!stack.isEmpty()) {
                snapshot.equipment.put(slotType.name(), new SerializableItemStack(stack));
            }
            return;
        });
        final AbilityTree abilities = PlayerAbilitiesData.get(sWorld).getAbilities((PlayerEntity)sPlayer);
        abilities.getLearnedNodes().forEach(node -> {
            if (node.getSpecialization() != null) {
                snapshot.abilities.put(node.getGroup().getParentName() + ": " + node.getSpecializationName(), node.getLevel());
            }
            else {
                snapshot.abilities.put(node.getGroup().getParentName(), node.getLevel());
            }
            return;
        });
        final TalentTree talents = PlayerTalentsData.get(sWorld).getTalents((PlayerEntity)sPlayer);
        talents.getLearnedNodes().forEach(node -> snapshot.talents.put(node.getGroup().getParentName(), node.getLevel()));
        final ResearchTree researches = PlayerResearchesData.get(sWorld).getResearches((PlayerEntity)sPlayer);
        snapshot.researches.addAll(researches.getResearchesDone());
        final PlayerStatisticsCollector.VaultRunsSnapshot vaultRunsSnapshot = PlayerStatisticsCollector.VaultRunsSnapshot.ofPlayer(sPlayer);
        snapshot.vaultRuns = vaultRunsSnapshot.vaultRuns;
        snapshot.vaultWins = vaultRunsSnapshot.bossKills;
        snapshot.vaultDeaths = vaultRunsSnapshot.deaths;
        snapshot.artifactCount = vaultRunsSnapshot.artifacts;
        snapshot.powerLevel = stats.getTotalSpentSkillPoints() + stats.getUnspentSkillPts();
        final PlayerFavourData favourData = PlayerFavourData.get(sWorld);
        for (final PlayerFavourData.VaultGodType type : PlayerFavourData.VaultGodType.values()) {
            snapshot.favors.put(type.getName(), favourData.getFavour(sPlayer.getUUID(), type));
        }
        final EternalsData.EternalGroup group = EternalsData.get(sWorld).getEternals((PlayerEntity)sPlayer);
        for (final EternalData eternal : group.getEternals()) {
            String auraName = null;
            if (eternal.getAbilityName() != null) {
                final EternalAuraConfig.AuraConfig cfg = ModConfigs.ETERNAL_AURAS.getByName(eternal.getAbilityName());
                if (cfg != null) {
                    auraName = cfg.getDisplayName();
                }
            }
            final EternalInformation eternalSnapshot = new EternalInformation(eternal.getName(), eternal.getLevel(), eternal.isAncient(), auraName);
            eternal.getEquipment().forEach((slot, stack) -> {
                if (!stack.isEmpty()) {
                    eternalSnapshot.equipment.put(slot.name(), new SerializableItemStack(stack));
                }
                return;
            });
            snapshot.eternals.add(eternalSnapshot);
        }
        return snapshot;
    }
    
    static {
        GSON = new GsonBuilder().setPrettyPrinting().create();
    }
    
    public static class PlayerSnapshot
    {
        protected final UUID playerUUID;
        protected final String playerNickname;
        protected final long timestamp;
        protected int vaultRuns;
        protected int vaultWins;
        protected int vaultDeaths;
        protected int artifactCount;
        protected boolean inVault;
        protected int powerLevel;
        protected int vaultLevel;
        protected float levelPercent;
        protected Map<String, Double> attributes;
        protected Map<String, Integer> favors;
        protected float parry;
        protected float resistance;
        protected float cooldownReduction;
        protected float fatalStrikeChance;
        protected float fatalStrikeDamage;
        protected float thornsChance;
        protected float thornsDamage;
        protected Map<String, SerializableItemStack> equipment;
        protected Map<String, Integer> abilities;
        protected Map<String, Integer> talents;
        protected Set<String> researches;
        protected Set<EternalInformation> eternals;
        
        public PlayerSnapshot(final ServerPlayerEntity playerEntity) {
            this.inVault = false;
            this.powerLevel = 0;
            this.vaultLevel = 0;
            this.levelPercent = 0.0f;
            this.attributes = new LinkedHashMap<String, Double>();
            this.favors = new LinkedHashMap<String, Integer>();
            this.equipment = new LinkedHashMap<String, SerializableItemStack>();
            this.abilities = new LinkedHashMap<String, Integer>();
            this.talents = new LinkedHashMap<String, Integer>();
            this.researches = new LinkedHashSet<String>();
            this.eternals = new LinkedHashSet<EternalInformation>();
            this.playerUUID = playerEntity.getUUID();
            this.playerNickname = playerEntity.getName().getString();
            this.timestamp = Instant.now().getEpochSecond();
        }
    }
    
    public static class EternalInformation
    {
        private final String name;
        private final int level;
        private final boolean isAncient;
        private final String auraName;
        private Map<String, SerializableItemStack> equipment;
        
        public EternalInformation(final String name, final int level, final boolean isAncient, final String auraName) {
            this.equipment = new LinkedHashMap<String, SerializableItemStack>();
            this.name = name;
            this.level = level;
            this.isAncient = isAncient;
            this.auraName = auraName;
        }
    }
    
    public static class SerializableItemStack
    {
        private final String itemKey;
        private final int count;
        private final String nbt;
        
        private SerializableItemStack(final ItemStack stack) {
            this.itemKey = stack.getItem().getRegistryName().toString();
            this.count = stack.getCount();
            if (stack.hasTag()) {
                this.nbt = stack.getTag().toString();
            }
            else {
                this.nbt = null;
            }
        }
    }
}
