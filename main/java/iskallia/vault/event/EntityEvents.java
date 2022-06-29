// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.event;

import java.util.UUID;
import iskallia.vault.block.VaultDoorBlock;
import net.minecraftforge.event.entity.living.LivingDestroyBlockEvent;
import iskallia.vault.init.ModSounds;
import net.minecraft.util.SoundEvents;
import iskallia.vault.world.vault.modifier.CurseOnHitModifier;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraft.util.math.MathHelper;
import iskallia.vault.skill.talent.TalentNode;
import iskallia.vault.skill.talent.type.SoulShardTalent;
import iskallia.vault.world.data.PlayerTalentsData;
import iskallia.vault.item.ItemVaultRaffleSeal;
import net.minecraft.util.IItemProvider;
import iskallia.vault.init.ModItems;
import iskallia.vault.config.ScavengerHuntConfig;
import java.util.Optional;
import iskallia.vault.item.BasicScavengerItem;
import net.minecraft.entity.EntityType;
import iskallia.vault.init.ModConfigs;
import iskallia.vault.world.vault.logic.objective.ScavengerHuntObjective;
import iskallia.vault.entity.TreasureGoblinEntity;
import iskallia.vault.entity.VaultGuardianEntity;
import net.minecraft.inventory.IInventory;
import iskallia.vault.util.MiscUtils;
import iskallia.vault.item.ItemShardPouch;
import net.minecraft.entity.item.ItemEntity;
import java.util.Collection;
import iskallia.vault.world.vault.logic.objective.VaultObjective;
import iskallia.vault.entity.AggressiveCowEntity;
import iskallia.vault.entity.VaultFighterEntity;
import iskallia.vault.Vault;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import iskallia.vault.world.vault.VaultRaid;
import iskallia.vault.world.vault.modifier.DurabilityDamageModifier;
import iskallia.vault.util.PlayerFilter;
import iskallia.vault.world.vault.influence.VaultAttributeInfluence;
import iskallia.vault.world.data.VaultRaidData;
import iskallia.vault.item.gear.IdolItem;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import java.util.Comparator;
import iskallia.vault.entity.EternalEntity;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.IWorld;
import iskallia.vault.util.EntityHelper;
import net.minecraft.entity.MobEntity;
import iskallia.vault.attribute.IntegerAttribute;
import java.util.Iterator;
import net.minecraft.world.World;
import net.minecraft.entity.CreatureAttribute;
import iskallia.vault.attribute.FloatAttribute;
import iskallia.vault.item.gear.VaultGearHelper;
import iskallia.vault.attribute.VAttribute;
import java.util.ArrayList;
import iskallia.vault.attribute.EffectCloudAttribute;
import iskallia.vault.entity.EffectCloudEntity;
import iskallia.vault.item.gear.VaultGear;
import iskallia.vault.init.ModAttributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import java.util.function.Consumer;
import iskallia.vault.skill.talent.type.EffectTalent;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import iskallia.vault.block.VaultChestBlock;
import net.minecraftforge.common.ForgeMod;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MerchantOffer;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import net.minecraft.potion.Potions;
import net.minecraft.potion.PotionUtils;
import net.minecraft.item.TippedArrowItem;
import net.minecraft.item.ShieldItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import java.util.List;
import net.minecraftforge.event.village.VillagerTradesEvent;
import java.util.Random;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EntityEvents
{
    private static final Random rand;
    
    @SubscribeEvent
    public static void onTradesLoad(final VillagerTradesEvent event) {
        for (final List<VillagerTrades.ITrade> trades : event.getTrades().values()) {
            trades.removeIf(trade -> {
                try {
                    final MerchantOffer offer = trade.getOffer((Entity)null, EntityEvents.rand);
                    final ItemStack output = offer.assemble();
                    final Item outItem = output.getItem();
                    if (outItem instanceof ShieldItem) {
                        return true;
                    }
                    else if (outItem instanceof TippedArrowItem && PotionUtils.getPotion(output).equals(Potions.REGENERATION)) {
                        return true;
                    }
                }
                catch (final Exception ex) {}
                return false;
            });
        }
    }
    
    @SubscribeEvent
    public static void onBlockBreak(final BlockEvent.BreakEvent event) {
        final PlayerEntity player = event.getPlayer();
        final ModifiableAttributeInstance reachAttr = player.getAttribute((Attribute)ForgeMod.REACH_DISTANCE.get());
        if (reachAttr == null) {
            return;
        }
        final BlockPos pos = event.getPos();
        final BlockState state = player.getCommandSenderWorld().getBlockState(pos);
        if (!(state.getBlock() instanceof VaultChestBlock)) {
            return;
        }
        final double reach = reachAttr.getValue();
        if (player.distanceToSqr(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5) >= reach * reach) {
            event.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public static void onEffectImmune(final LivingEvent.LivingUpdateEvent event) {
        if (event.getEntity().getCommandSenderWorld().isClientSide()) {
            return;
        }
        if (!(event.getEntity() instanceof LivingEntity)) {
            return;
        }
        final LivingEntity livingEntity = (LivingEntity)event.getEntity();
        EffectTalent.getImmunities(livingEntity).forEach(livingEntity::removeEffect);
    }
    
    @SubscribeEvent
    public static void onPlayerFallDamage(final LivingHurtEvent event) {
        if (event.getEntity().level.isClientSide) {
            return;
        }
        if (!(event.getEntity() instanceof PlayerEntity)) {
            return;
        }
        if (event.getSource() != DamageSource.FALL) {
            return;
        }
        final ServerPlayerEntity player = (ServerPlayerEntity)event.getEntity();
        float totalReduction = 0.0f;
        for (final EquipmentSlotType slot : EquipmentSlotType.values()) {
            final ItemStack stack = player.getItemBySlot(slot);
            totalReduction += ModAttributes.FEATHER_FEET.get(stack).map(attribute -> attribute.getValue(stack)).orElse(0.0f);
        }
        event.setAmount(event.getAmount() * (1.0f - Math.min(totalReduction, 1.0f)));
        if (event.getAmount() <= 1.0E-4) {
            event.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public static void onPlayerMobHit(final LivingHurtEvent event) {
        final World world = event.getEntity().getCommandSenderWorld();
        if (world.isClientSide()) {
            return;
        }
        if (!(event.getSource().getEntity() instanceof LivingEntity)) {
            return;
        }
        final LivingEntity attacked = event.getEntityLiving();
        final LivingEntity attacker = (LivingEntity)event.getSource().getEntity();
        final boolean doEffectClouds = !ActiveFlags.IS_AOE_ATTACKING.isSet() && !ActiveFlags.IS_DOT_ATTACKING.isSet() && !ActiveFlags.IS_REFLECT_ATTACKING.isSet();
        if (doEffectClouds) {
            for (final EquipmentSlotType slot : EquipmentSlotType.values()) {
                final ItemStack stack = attacker.getItemBySlot(slot);
                if (!(stack.getItem() instanceof VaultGear) || ((VaultGear)stack.getItem()).isIntendedForSlot(slot)) {
                    final List<EffectCloudEntity.Config> configs = ModAttributes.EFFECT_CLOUD.getOrDefault(stack, new ArrayList<EffectCloudEntity.Config>()).getValue(stack);
                    for (final EffectCloudEntity.Config config : configs) {
                        if (world.random.nextFloat() >= config.getChance()) {
                            continue;
                        }
                        final EffectCloudEntity cloud = EffectCloudEntity.fromConfig(attacked.level, attacker, attacked.getX(), attacked.getY(), attacked.getZ(), config);
                        world.addFreshEntity((Entity)cloud);
                    }
                }
            }
        }
        float incDamage = VaultGearHelper.getAttributeValueOnGearSumFloat(attacker, ModAttributes.DAMAGE_INCREASE, ModAttributes.DAMAGE_INCREASE_2);
        final CreatureAttribute creatureType = attacked.getMobType();
        if (creatureType == CreatureAttribute.UNDEAD) {
            incDamage += VaultGearHelper.getAttributeValueOnGearSumFloat(attacker, ModAttributes.DAMAGE_UNDEAD);
        }
        else if (creatureType == CreatureAttribute.ARTHROPOD) {
            incDamage += VaultGearHelper.getAttributeValueOnGearSumFloat(attacker, ModAttributes.DAMAGE_SPIDERS);
        }
        else if (creatureType == CreatureAttribute.ILLAGER) {
            incDamage += VaultGearHelper.getAttributeValueOnGearSumFloat(attacker, ModAttributes.DAMAGE_ILLAGERS);
        }
        event.setAmount(event.getAmount() * (1.0f + incDamage));
    }
    
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onPlayerMobHitAfter(final LivingHurtEvent event) {
        final World world = event.getEntity().getCommandSenderWorld();
        if (world.isClientSide()) {
            return;
        }
        if (!(event.getSource().getEntity() instanceof LivingEntity)) {
            return;
        }
        final LivingEntity attacked = event.getEntityLiving();
        final LivingEntity attacker = (LivingEntity)event.getSource().getEntity();
        if (ActiveFlags.IS_DOT_ATTACKING.isSet() || ActiveFlags.IS_REFLECT_ATTACKING.isSet()) {
            return;
        }
        boolean mayChainAttack = true;
        if (attacker instanceof PlayerEntity) {
            mayChainAttack = !PlayerActiveFlags.isSet((PlayerEntity)attacker, PlayerActiveFlags.Flag.CHAINING_AOE);
        }
        if (mayChainAttack) {
            final int additionalChains = VaultGearHelper.getAttributeValueOnGearSumInt(attacker, ModAttributes.ON_HIT_CHAIN);
            if (additionalChains > 0) {
                ActiveFlags.IS_AOE_ATTACKING.runIfNotSet(() -> {
                    final List<MobEntity> nearby = EntityHelper.getNearby((IWorld)world, (Vector3i)attacked.blockPosition(), 5.0f, MobEntity.class);
                    nearby.remove(attacked);
                    nearby.remove(attacker);
                    nearby.removeIf(mob -> (attacker instanceof EternalEntity || attacker instanceof PlayerEntity) && mob instanceof EternalEntity);
                    if (!nearby.isEmpty()) {
                        nearby.sort((Comparator)Comparator.comparing(e -> e.distanceToSqr((Entity)attacked)));
                        final List nearby2 = nearby.subList(0, Math.min(additionalChains, nearby.size()));
                        float multiplier = 0.5f;
                        nearby2.iterator();
                        final Iterator iterator;
                        while (iterator.hasNext()) {
                            final MobEntity me = iterator.next();
                            me.hurt(event.getSource(), event.getAmount() * multiplier);
                            multiplier *= 0.5f;
                        }
                    }
                    return;
                });
                if (attacker instanceof PlayerEntity) {
                    PlayerActiveFlags.set((PlayerEntity)attacker, PlayerActiveFlags.Flag.CHAINING_AOE, 2);
                }
            }
        }
        boolean mayAoeAttack = true;
        if (attacker instanceof PlayerEntity) {
            mayAoeAttack = !PlayerActiveFlags.isSet((PlayerEntity)attacker, PlayerActiveFlags.Flag.ATTACK_AOE);
        }
        if (mayAoeAttack) {
            final int blockAoE = VaultGearHelper.getAttributeValueOnGearSumInt(attacker, ModAttributes.ON_HIT_AOE);
            if (blockAoE > 0) {
                ActiveFlags.IS_AOE_ATTACKING.runIfNotSet(() -> {
                    final List<MobEntity> nearby3 = EntityHelper.getNearby((IWorld)world, (Vector3i)attacked.blockPosition(), (float)blockAoE, MobEntity.class);
                    nearby3.remove(attacked);
                    nearby3.remove(attacker);
                    nearby3.removeIf(mob -> (attacker instanceof EternalEntity || attacker instanceof PlayerEntity) && mob instanceof EternalEntity);
                    if (!nearby3.isEmpty()) {
                        nearby3.iterator();
                        final Iterator iterator2;
                        while (iterator2.hasNext()) {
                            final MobEntity me2 = iterator2.next();
                            me2.hurt(event.getSource(), event.getAmount() * 0.6f);
                        }
                    }
                    return;
                });
            }
            if (attacker instanceof PlayerEntity) {
                PlayerActiveFlags.set((PlayerEntity)attacker, PlayerActiveFlags.Flag.ATTACK_AOE, 2);
            }
        }
        final float stunChance = VaultGearHelper.getAttributeValueOnGearSumFloat(attacker, ModAttributes.ON_HIT_STUN);
        if (EntityEvents.rand.nextFloat() < stunChance) {
            attacked.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 40, 9));
            attacked.addEffect(new EffectInstance(Effects.DIG_SLOWDOWN, 40, 9));
        }
    }
    
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onDamageTotem(final LivingHurtEvent event) {
        final World world = event.getEntity().getCommandSenderWorld();
        if (world.isClientSide() || !(world instanceof ServerWorld)) {
            return;
        }
        if (!(event.getEntityLiving() instanceof PlayerEntity)) {
            return;
        }
        if (event.getSource().isBypassArmor()) {
            return;
        }
        final ServerWorld sWorld = (ServerWorld)world;
        final ItemStack offHand = event.getEntityLiving().getOffhandItem();
        if (!(offHand.getItem() instanceof IdolItem)) {
            return;
        }
        final PlayerEntity player = (PlayerEntity)event.getEntityLiving();
        float damage = Math.max(1.0f, event.getAmount() / 5.0f);
        final VaultRaid vault = VaultRaidData.get(sWorld).getAt(sWorld, player.blockPosition());
        if (vault != null) {
            for (final VaultAttributeInfluence influence : vault.getInfluences().getInfluences(VaultAttributeInfluence.class)) {
                if (influence.getType() == VaultAttributeInfluence.Type.DURABILITY_DAMAGE && !influence.isMultiplicative()) {
                    damage += influence.getValue();
                }
            }
            for (final DurabilityDamageModifier modifier : vault.getActiveModifiersFor(PlayerFilter.of(player), DurabilityDamageModifier.class)) {
                damage *= modifier.getDurabilityDamageTakenMultiplier();
            }
            for (final VaultAttributeInfluence influence : vault.getInfluences().getInfluences(VaultAttributeInfluence.class)) {
                if (influence.getType() == VaultAttributeInfluence.Type.DURABILITY_DAMAGE && influence.isMultiplicative()) {
                    damage += influence.getValue();
                }
            }
        }
        offHand.hurtAndBreak((int)damage, event.getEntityLiving(), entity -> entity.broadcastBreakEvent(EquipmentSlotType.OFFHAND));
    }
    
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onEntityDrops(final LivingDropsEvent event) {
        final World world = event.getEntity().level;
        if (world.isClientSide() || !(world instanceof ServerWorld)) {
            return;
        }
        if (world.dimension() != Vault.VAULT_KEY) {
            return;
        }
        final Entity entity = event.getEntity();
        if (shouldDropDefaultInVault(entity)) {
            return;
        }
        final BlockPos pos = entity.blockPosition();
        final ServerWorld sWorld = (ServerWorld)world;
        final VaultRaid vault = VaultRaidData.get(sWorld).getAt(sWorld, pos);
        if (vault == null) {
            event.setCanceled(true);
            return;
        }
        final DamageSource killingSrc = event.getSource();
        if (!(entity instanceof VaultFighterEntity) && !(entity instanceof AggressiveCowEntity)) {
            event.getDrops().clear();
        }
        if (vault.getActiveObjectives().stream().anyMatch(VaultObjective::preventsNormalMonsterDrops)) {
            event.setCanceled(true);
            return;
        }
        boolean addedDrops = entity instanceof AggressiveCowEntity;
        addedDrops |= addScavengerDrops(world, entity, vault, event.getDrops());
        addedDrops |= addSubFighterDrops(world, entity, vault, event.getDrops());
        Entity killerEntity = killingSrc.getEntity();
        if (killerEntity instanceof EternalEntity) {
            killerEntity = ((EternalEntity)killerEntity).getOwner().right().orElse(null);
        }
        if (killerEntity instanceof ServerPlayerEntity) {
            final ServerPlayerEntity killer = (ServerPlayerEntity)killerEntity;
            if (MiscUtils.inventoryContains((IInventory)killer.inventory, stack -> stack.getItem() instanceof ItemShardPouch) && vault.getActiveObjectives().stream().noneMatch(objective -> objective.shouldPauseTimer(sWorld.getServer(), vault))) {
                addedDrops |= addShardDrops(world, entity, killer, vault, event.getDrops());
            }
        }
        if (!addedDrops) {
            event.setCanceled(true);
        }
    }
    
    private static boolean shouldDropDefaultInVault(final Entity entity) {
        return entity instanceof VaultGuardianEntity || entity instanceof TreasureGoblinEntity;
    }
    
    private static boolean addScavengerDrops(final World world, final Entity killed, final VaultRaid vault, final Collection<ItemEntity> drops) {
        final Optional<ScavengerHuntObjective> objectiveOpt = vault.getActiveObjective(ScavengerHuntObjective.class);
        if (!objectiveOpt.isPresent()) {
            return false;
        }
        final ScavengerHuntObjective objective = objectiveOpt.get();
        final List<ScavengerHuntConfig.ItemEntry> specialDrops = ModConfigs.SCAVENGER_HUNT.generateMobDropLoot(objective.getGenerationDropFilter(), (EntityType<?>)killed.getType());
        return !specialDrops.isEmpty() && vault.getProperties().getBase(VaultRaid.IDENTIFIER).map(identifier -> {
            specialDrops.forEach(entry -> {
                final ItemStack stack = entry.createItemStack();
                if (stack.isEmpty()) {
                    return;
                }
                else {
                    BasicScavengerItem.setVaultIdentifier(stack, identifier);
                    final ItemEntity itemEntity = new ItemEntity(world, killed.getX(), killed.getY(), killed.getZ(), stack);
                    itemEntity.setDefaultPickUpDelay();
                    drops.add(itemEntity);
                    return;
                }
            });
            return true;
        }).orElse(false);
    }
    
    private static boolean addSubFighterDrops(final World world, final Entity killed, final VaultRaid vault, final Collection<ItemEntity> drops) {
        if (!(killed instanceof VaultFighterEntity)) {
            return false;
        }
        final int level = vault.getProperties().getBase(VaultRaid.LEVEL).orElse(0);
        final float shardChance = ModConfigs.LOOT_TABLES.getForLevel(level).getSubFighterRaffleChance();
        if (EntityEvents.rand.nextFloat() >= shardChance) {
            return false;
        }
        final String name = killed.getPersistentData().getString("VaultPlayerName");
        if (name.isEmpty()) {
            return false;
        }
        final ItemStack raffleSeal = new ItemStack((IItemProvider)ModItems.CRYSTAL_SEAL_RAFFLE);
        ItemVaultRaffleSeal.setPlayerName(raffleSeal, name);
        final ItemEntity itemEntity = new ItemEntity(world, killed.getX(), killed.getY(), killed.getZ(), raffleSeal);
        itemEntity.setDefaultPickUpDelay();
        drops.add(itemEntity);
        return true;
    }
    
    private static boolean addShardDrops(final World world, final Entity killed, final ServerPlayerEntity killer, final VaultRaid vault, final Collection<ItemEntity> drops) {
        final List<TalentNode<SoulShardTalent>> shardNodes = PlayerTalentsData.get(killer.getLevel()).getTalents((PlayerEntity)killer).getLearnedNodes(SoulShardTalent.class);
        if (shardNodes.isEmpty()) {
            return false;
        }
        for (final TalentNode<SoulShardTalent> node : shardNodes) {
            if (!node.isLearned()) {
                return false;
            }
        }
        int shardCount = ModConfigs.SOUL_SHARD.getRandomShards((EntityType<?>)killed.getType());
        for (final VaultAttributeInfluence influence : vault.getInfluences().getInfluences(VaultAttributeInfluence.class)) {
            if (influence.getType() == VaultAttributeInfluence.Type.SOUL_SHARD_DROPS && !influence.isMultiplicative()) {
                shardCount += (int)influence.getValue();
            }
        }
        if (shardCount <= 0) {
            return false;
        }
        float additionalSoulShardChance = 0.0f;
        for (final TalentNode<SoulShardTalent> node2 : shardNodes) {
            additionalSoulShardChance += node2.getTalent().getAdditionalSoulShardChance();
        }
        final float shShardCount = shardCount * (1.0f + additionalSoulShardChance);
        shardCount = MathHelper.floor(shShardCount);
        final float decimal = shShardCount - shardCount;
        if (EntityEvents.rand.nextFloat() < decimal) {
            ++shardCount;
        }
        for (final VaultAttributeInfluence influence2 : vault.getInfluences().getInfluences(VaultAttributeInfluence.class)) {
            if (influence2.getType() == VaultAttributeInfluence.Type.SOUL_SHARD_DROPS && influence2.isMultiplicative()) {
                shardCount *= (int)influence2.getValue();
            }
        }
        final ItemStack shards = new ItemStack((IItemProvider)ModItems.SOUL_SHARD, shardCount);
        final ItemEntity itemEntity = new ItemEntity(world, killed.getX(), killed.getY(), killed.getZ(), shards);
        itemEntity.setDefaultPickUpDelay();
        drops.add(itemEntity);
        return true;
    }
    
    @SubscribeEvent
    public static void onEntitySpawn(final LivingSpawnEvent.CheckSpawn event) {
        if (event.getEntity().getCommandSenderWorld().dimension() == Vault.VAULT_KEY && !event.isSpawner()) {
            event.setResult(Event.Result.DENY);
        }
    }
    
    @SubscribeEvent
    public static void onDamageArmorHit(final LivingDamageEvent event) {
        final LivingEntity damaged = event.getEntityLiving();
        if (!(damaged instanceof PlayerEntity) || damaged.getCommandSenderWorld().isClientSide()) {
            return;
        }
        final PlayerEntity player = (PlayerEntity)damaged;
        final Entity trueSrc = event.getSource().getEntity();
        if (trueSrc instanceof LivingEntity) {
            double chance = ((LivingEntity)trueSrc).getAttributeValue(ModAttributes.BREAK_ARMOR_CHANCE);
            while (chance > 0.0) {
                if (EntityEvents.rand.nextFloat() > chance) {
                    break;
                }
                --chance;
                player.inventory.hurtArmor(event.getSource(), 4.0f);
            }
        }
    }
    
    @SubscribeEvent
    public static void onCurseOnHit(final LivingDamageEvent event) {
        if (!(event.getSource().getEntity() instanceof LivingEntity)) {
            return;
        }
        final LivingEntity damaged = event.getEntityLiving();
        if (!(damaged instanceof ServerPlayerEntity)) {
            return;
        }
        final ServerPlayerEntity sPlayer = (ServerPlayerEntity)damaged;
        final ServerWorld sWorld = sPlayer.getLevel();
        final VaultRaid vault = VaultRaidData.get(sWorld).getAt(sWorld, sPlayer.blockPosition());
        if (vault != null) {
            vault.getActiveModifiersFor(PlayerFilter.any(), CurseOnHitModifier.class).forEach(modifier -> modifier.applyCurse(sPlayer));
        }
    }
    
    @SubscribeEvent
    public static void onVaultGuardianDamage(final LivingDamageEvent event) {
        final LivingEntity entityLiving = event.getEntityLiving();
        if (entityLiving.level.isClientSide) {
            return;
        }
        if (entityLiving instanceof VaultGuardianEntity) {
            final Entity trueSource = event.getSource().getEntity();
            if (trueSource instanceof LivingEntity) {
                final LivingEntity attacker = (LivingEntity)trueSource;
                attacker.hurt(DamageSource.thorns((Entity)entityLiving), event.getAmount() * 0.2f);
            }
        }
    }
    
    @SubscribeEvent
    public static void onLivingHurtCrit(final LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof LivingEntity)) {
            return;
        }
        final LivingEntity source = (LivingEntity)event.getSource().getEntity();
        if (source.level.isClientSide) {
            return;
        }
        if (source.getAttributes().hasAttribute(ModAttributes.CRIT_CHANCE)) {
            final double chance = source.getAttributeValue(ModAttributes.CRIT_CHANCE);
            if (source.getAttributes().hasAttribute(ModAttributes.CRIT_MULTIPLIER)) {
                final double multiplier = source.getAttributeValue(ModAttributes.CRIT_MULTIPLIER);
                if (source.level.random.nextDouble() < chance) {
                    source.level.playSound((PlayerEntity)null, source.getX(), source.getY(), source.getZ(), SoundEvents.PLAYER_ATTACK_CRIT, source.getSoundSource(), 1.0f, 1.0f);
                    event.setAmount((float)(event.getAmount() * multiplier));
                }
            }
        }
    }
    
    @SubscribeEvent
    public static void onLivingHurtTp(final LivingHurtEvent event) {
        if (event.getEntityLiving().level.isClientSide) {
            return;
        }
        final boolean direct = event.getSource().getDirectEntity() == event.getSource().getEntity();
        if (direct && event.getEntityLiving().getAttributes().hasAttribute(ModAttributes.TP_CHANCE)) {
            final double chance = event.getEntityLiving().getAttributeValue(ModAttributes.TP_CHANCE);
            if (event.getEntityLiving().getAttributes().hasAttribute(ModAttributes.TP_RANGE)) {
                final double range = event.getEntityLiving().getAttributeValue(ModAttributes.TP_RANGE);
                if (event.getEntityLiving().level.random.nextDouble() < chance) {
                    for (int i = 0; i < 64; ++i) {
                        if (teleportRandomly(event.getEntityLiving(), range)) {
                            event.getEntityLiving().level.playSound((PlayerEntity)null, event.getEntityLiving().xo, event.getEntityLiving().yo, event.getEntityLiving().zo, ModSounds.BOSS_TP_SFX, event.getEntityLiving().getSoundSource(), 1.0f, 1.0f);
                            event.setCanceled(true);
                            return;
                        }
                    }
                }
            }
        }
        else if (!direct && event.getEntityLiving().getAttributes().hasAttribute(ModAttributes.TP_INDIRECT_CHANCE)) {
            final double chance = event.getEntityLiving().getAttributeValue(ModAttributes.TP_INDIRECT_CHANCE);
            if (event.getEntityLiving().getAttributes().hasAttribute(ModAttributes.TP_RANGE)) {
                final double range = event.getEntityLiving().getAttributeValue(ModAttributes.TP_RANGE);
                if (event.getEntityLiving().level.random.nextDouble() < chance) {
                    for (int i = 0; i < 64; ++i) {
                        if (teleportRandomly(event.getEntityLiving(), range)) {
                            event.getEntityLiving().level.playSound((PlayerEntity)null, event.getEntityLiving().xo, event.getEntityLiving().yo, event.getEntityLiving().zo, ModSounds.BOSS_TP_SFX, event.getEntityLiving().getSoundSource(), 1.0f, 1.0f);
                            event.setCanceled(true);
                            return;
                        }
                    }
                }
            }
        }
    }
    
    private static boolean teleportRandomly(final LivingEntity entity, final double range) {
        if (!entity.level.isClientSide() && entity.isAlive()) {
            final double d0 = entity.getX() + (entity.level.random.nextDouble() - 0.5) * (range * 2.0);
            final double d2 = entity.getY() + (entity.level.random.nextInt((int)(range * 2.0)) - range);
            final double d3 = entity.getZ() + (entity.level.random.nextDouble() - 0.5) * (range * 2.0);
            return entity.randomTeleport(d0, d2, d3, true);
        }
        return false;
    }
    
    @SubscribeEvent
    public static void onEntityDestroy(final LivingDestroyBlockEvent event) {
        if (event.getState().getBlock() instanceof VaultDoorBlock) {
            event.setCanceled(true);
        }
    }
    
    static {
        rand = new Random();
    }
}
