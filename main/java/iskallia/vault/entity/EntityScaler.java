
package iskallia.vault.entity;

import java.util.function.Function;
import java.util.function.BiFunction;
import net.minecraft.entity.MobEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import java.util.UUID;
import net.minecraft.entity.ai.attributes.Attributes;
import iskallia.vault.world.vault.logic.objective.architect.ArchitectSummonAndKillBossesObjective;
import iskallia.vault.config.VaultMobsConfig;
import iskallia.vault.init.ModConfigs;
import net.minecraft.world.World;
import net.minecraft.entity.LivingEntity;
import java.util.Random;
import iskallia.vault.world.data.GlobalDifficultyData;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.Entity;
import iskallia.vault.world.vault.VaultUtils;
import net.minecraftforge.event.entity.EntityEvent;
import iskallia.vault.world.vault.VaultRaid;

public class EntityScaler {
    private static final String SCALED_TAG = "vault_scaled";

    public static void scaleVaultEntity(final VaultRaid vault, final EntityEvent event) {
        if (!VaultUtils.inVault(vault, event.getEntity())) {
            return;
        }
        scaleVaultEntity(vault, event.getEntity());
    }

    public static void scaleVaultEntity(final VaultRaid vault, final Entity entity) {
        if (!(entity instanceof MonsterEntity) || entity instanceof EternalEntity || isScaled(entity)) {
            return;
        }
        final World world = entity.getCommandSenderWorld();
        if (!(world instanceof ServerWorld)) {
            return;
        }
        final ServerWorld sWorld = (ServerWorld) world;
        final MonsterEntity livingEntity = (MonsterEntity) entity;
        final GlobalDifficultyData.Difficulty difficulty = GlobalDifficultyData.get(sWorld).getVaultDifficulty();
        vault.getProperties().getBase(VaultRaid.LEVEL)
                .ifPresent(level -> setScaledEquipment((LivingEntity) livingEntity, vault, difficulty, level,
                        new Random(), Type.MOB));
        setScaled((Entity) livingEntity);
        livingEntity.setPersistenceRequired();
    }

    public static boolean isScaled(final Entity entity) {
        return entity.getTags().contains("vault_scaled");
    }

    public static void setScaled(final Entity entity) {
        entity.addTag("vault_scaled");
    }

    public static void setScaledEquipment(final LivingEntity entity, final VaultRaid vault,
            final GlobalDifficultyData.Difficulty vaultDifficulty, final int level, final Random random,
            final Type type) {
        final VaultMobsConfig.Level overrides = ModConfigs.VAULT_MOBS.getForLevel(level);
        if (!isScaled((Entity) entity)) {
            VaultMobsConfig.Mob.scale(entity, vault, vaultDifficulty);
        }
        vault.getActiveObjective(ArchitectSummonAndKillBossesObjective.class).ifPresent(objective -> {
            if (entity.getAttributes().hasAttribute(Attributes.MAX_HEALTH)) {
                UUID randomId;
                do {
                    randomId = UUID.randomUUID();
                } while (entity.getAttributes().hasModifier(Attributes.MAX_HEALTH, randomId));
                entity.getAttribute(Attributes.MAX_HEALTH)
                        .addPermanentModifier(new AttributeModifier(randomId, "Final Architect Health",
                                (double) objective.getCombinedMobHealthMultiplier(),
                                AttributeModifier.Operation.MULTIPLY_BASE));
            }
            entity.heal(1000000.0f);
            return;
        });
        for (final EquipmentSlotType slot : EquipmentSlotType.values()) {
            if (slot.getType() != EquipmentSlotType.Group.HAND || entity.getItemBySlot(slot).isEmpty()) {
                final ItemStack loot = type.loot.apply(overrides, slot);
                for (int i = 0; i < type.trials.apply(overrides); ++i) {
                    EnchantmentHelper.enchantItem(random, loot,
                            EnchantmentHelper.getEnchantmentCost(random, (int) type.level.apply(overrides), 15, loot), true);
                }
                entity.setItemSlot(slot, loot);
                if (entity instanceof MobEntity) {
                    ((MobEntity) entity).setDropChance(slot, 0.0f);
                }
            }
        }
    }

    public enum Type {
        MOB(VaultMobsConfig.Level::getForMob, level -> level.MOB_MISC.ENCH_TRIALS, level -> level.MOB_MISC.ENCH_LEVEL),
        BOSS(VaultMobsConfig.Level::getForBoss, level -> level.BOSS_MISC.ENCH_TRIALS,
                level -> level.BOSS_MISC.ENCH_LEVEL),
        RAFFLE_BOSS(VaultMobsConfig.Level::getForRaffle, level -> level.RAFFLE_BOSS_MISC.ENCH_TRIALS,
                level -> level.RAFFLE_BOSS_MISC.ENCH_LEVEL);

        private final BiFunction<VaultMobsConfig.Level, EquipmentSlotType, ItemStack> loot;
        private final Function<VaultMobsConfig.Level, Integer> trials;
        private final Function<VaultMobsConfig.Level, Integer> level;

        private Type(final BiFunction<VaultMobsConfig.Level, EquipmentSlotType, ItemStack> loot,
                final Function<VaultMobsConfig.Level, Integer> trials,
                final Function<VaultMobsConfig.Level, Integer> level) {
            this.loot = loot;
            this.trials = trials;
            this.level = level;
        }
    }
}
