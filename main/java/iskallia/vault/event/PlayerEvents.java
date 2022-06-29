// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.event;

import iskallia.vault.init.ModItems;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import java.util.List;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.util.SoundEvents;
import iskallia.vault.world.data.VaultCharmData;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import iskallia.vault.util.AdvancementHelper;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import iskallia.vault.util.SideOnlyFixer;
import iskallia.vault.item.gear.EtchingItem;
import java.util.Random;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import iskallia.vault.init.ModSounds;
import iskallia.vault.util.VaultRarity;
import iskallia.vault.block.entity.VaultChestTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraftforge.event.world.BlockEvent;
import java.util.Iterator;
import iskallia.vault.skill.set.SetTree;
import iskallia.vault.skill.set.BloodSet;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import iskallia.vault.skill.set.DryadSet;
import net.minecraft.entity.ai.attributes.Attributes;
import iskallia.vault.skill.set.DreamSet;
import iskallia.vault.skill.set.SetNode;
import iskallia.vault.util.PlayerDamageHelper;
import iskallia.vault.skill.set.DragonSet;
import iskallia.vault.skill.set.PlayerSet;
import iskallia.vault.item.gear.VaultGear;
import iskallia.vault.world.data.PlayerSetsData;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.TickEvent;
import iskallia.vault.world.vault.VaultRaid;
import iskallia.vault.world.data.VaultRaidData;
import iskallia.vault.Vault;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.IWorld;
import iskallia.vault.util.EntityHelper;
import net.minecraft.entity.LivingEntity;
import iskallia.vault.attribute.IntegerAttribute;
import iskallia.vault.init.ModAttributes;
import iskallia.vault.world.data.PlayerVaultStatsData;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.entity.Entity;
import iskallia.vault.entity.EternalEntity;
import net.minecraftforge.fml.network.NetworkDirection;
import iskallia.vault.network.message.FighterSizeMessage;
import iskallia.vault.init.ModNetwork;
import iskallia.vault.entity.FighterEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerEvents
{
    @SubscribeEvent
    public static void onStartTracking(final PlayerEvent.StartTracking event) {
        final Entity target = event.getTarget();
        if (target.level.isClientSide) {
            return;
        }
        final ServerPlayerEntity player = (ServerPlayerEntity)event.getPlayer();
        if (target instanceof FighterEntity) {
            ModNetwork.CHANNEL.sendTo((Object)new FighterSizeMessage(target, ((FighterEntity)target).sizeMultiplier), player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
        }
        if (target instanceof EternalEntity) {
            ModNetwork.CHANNEL.sendTo((Object)new FighterSizeMessage(target, ((EternalEntity)target).sizeMultiplier), player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
        }
    }
    
    @SubscribeEvent
    public static void onAttack(final AttackEntityEvent event) {
        final PlayerEntity attacker = event.getPlayer();
        if (attacker.level.isClientSide()) {
            return;
        }
        final int level = PlayerVaultStatsData.get((ServerWorld)attacker.level).getVaultStats(attacker).getVaultLevel();
        final ItemStack stack = attacker.getMainHandItem();
        if (ModAttributes.MIN_VAULT_LEVEL.exists(stack) && level < ModAttributes.MIN_VAULT_LEVEL.get(stack).get().getValue(stack)) {
            event.setCanceled(true);
            return;
        }
        if (event.getTarget() instanceof LivingEntity) {
            final LivingEntity target = (LivingEntity)event.getTarget();
            EntityHelper.getNearby((IWorld)attacker.level, (Vector3i)attacker.blockPosition(), 9.0f, EternalEntity.class).forEach(eternal -> eternal.setTarget(target));
        }
    }
    
    @SubscribeEvent
    public static void onPlayerDamage(final LivingHurtEvent event) {
        final Entity target = event.getEntity();
        if (!(target instanceof ServerPlayerEntity)) {
            return;
        }
        final ServerPlayerEntity player = (ServerPlayerEntity)target;
        if (player.getLevel().dimension() != Vault.VAULT_KEY) {
            return;
        }
        final VaultRaid active = VaultRaidData.get(player.getLevel()).getActiveFor(player);
        if (active != null && active.isFinished()) {
            event.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public static void onPlayerTick2(final TickEvent.PlayerTickEvent event) {
        if (event.player.hasEffect(Effects.FIRE_RESISTANCE)) {
            event.player.clearFire();
        }
        if (event.player.getCommandSenderWorld().isClientSide() || !(event.player instanceof ServerPlayerEntity)) {
            return;
        }
        final ServerPlayerEntity player = (ServerPlayerEntity)event.player;
        for (final EquipmentSlotType slot : EquipmentSlotType.values()) {
            if (slot.getType().equals((Object)EquipmentSlotType.Group.ARMOR)) {
                final ItemStack stack = player.getItemBySlot(slot);
                final int level = PlayerVaultStatsData.get((ServerWorld)event.player.level).getVaultStats((PlayerEntity)player).getVaultLevel();
                if (ModAttributes.MIN_VAULT_LEVEL.exists(stack) && level < ModAttributes.MIN_VAULT_LEVEL.get(stack).get().getValue(stack)) {
                    player.drop(stack.copy(), false, false);
                    stack.setCount(0);
                }
            }
        }
    }
    
    @SubscribeEvent
    public static void onApplyPlayerSets(final TickEvent.PlayerTickEvent event) {
        if (event.player.getCommandSenderWorld().isClientSide() || !(event.player instanceof ServerPlayerEntity)) {
            return;
        }
        final ServerPlayerEntity player = (ServerPlayerEntity)event.player;
        final SetTree sets = PlayerSetsData.get(player.getLevel()).getSets((PlayerEntity)player);
        if (PlayerSet.isActive(VaultGear.Set.DRAGON, (LivingEntity)player) && !PlayerDamageHelper.getMultiplier(player, DragonSet.MULTIPLIER_ID).isPresent()) {
            float multiplier = 1.0f;
            for (final SetNode<?> node : sets.getNodes()) {
                if (!(node.getSet() instanceof DragonSet)) {
                    continue;
                }
                final DragonSet set = (DragonSet)node.getSet();
                multiplier += set.getDamageMultiplier();
            }
            PlayerDamageHelper.applyMultiplier(DragonSet.MULTIPLIER_ID, (ServerPlayerEntity)event.player, multiplier, PlayerDamageHelper.Operation.ADDITIVE_MULTIPLY, false);
        }
        else if (!PlayerSet.isActive(VaultGear.Set.DRAGON, (LivingEntity)player)) {
            PlayerDamageHelper.removeMultiplier(player, DragonSet.MULTIPLIER_ID);
        }
        if (PlayerSet.isActive(VaultGear.Set.DREAM, (LivingEntity)player) && !PlayerDamageHelper.getMultiplier(player, DreamSet.MULTIPLIER_ID).isPresent()) {
            float multiplier = 1.0f;
            for (final SetNode<?> node : sets.getNodes()) {
                if (node.getSet() instanceof DreamSet) {
                    final DreamSet set2 = (DreamSet)node.getSet();
                    multiplier += set2.getIncreasedDamage();
                }
            }
            PlayerDamageHelper.applyMultiplier(DreamSet.MULTIPLIER_ID, (ServerPlayerEntity)event.player, multiplier, PlayerDamageHelper.Operation.ADDITIVE_MULTIPLY, false);
        }
        else if (!PlayerSet.isActive(VaultGear.Set.DREAM, (LivingEntity)player)) {
            PlayerDamageHelper.removeMultiplier(player, DreamSet.MULTIPLIER_ID);
        }
        player.getAttribute(Attributes.MAX_HEALTH).removeModifier(DryadSet.HEALTH_MODIFIER_ID);
        if (PlayerSet.isActive(VaultGear.Set.DRYAD, (LivingEntity)player)) {
            float health = 0.0f;
            for (final SetNode<?> node : sets.getNodes()) {
                if (!(node.getSet() instanceof DryadSet)) {
                    continue;
                }
                final DryadSet set3 = (DryadSet)node.getSet();
                health += set3.getExtraHealth();
            }
            player.getAttribute(Attributes.MAX_HEALTH).addTransientModifier(new AttributeModifier(DryadSet.HEALTH_MODIFIER_ID, "Dryad Bonus Health", (double)health, AttributeModifier.Operation.ADDITION));
        }
        if (PlayerSet.isActive(VaultGear.Set.BLOOD, (LivingEntity)player) && !PlayerDamageHelper.getMultiplier(player, BloodSet.MULTIPLIER_ID).isPresent()) {
            float multiplier = 0.0f;
            for (final SetNode<?> node : sets.getNodes()) {
                if (!(node.getSet() instanceof BloodSet)) {
                    continue;
                }
                final BloodSet set4 = (BloodSet)node.getSet();
                multiplier += set4.getDamageMultiplier();
            }
            PlayerDamageHelper.applyMultiplier(BloodSet.MULTIPLIER_ID, (ServerPlayerEntity)event.player, multiplier, PlayerDamageHelper.Operation.ADDITIVE_MULTIPLY);
        }
        else if (!PlayerSet.isActive(VaultGear.Set.BLOOD, (LivingEntity)player)) {
            PlayerDamageHelper.removeMultiplier(player, BloodSet.MULTIPLIER_ID);
        }
    }
    
    @SubscribeEvent
    public static void onBlockBreak(final BlockEvent.BreakEvent event) {
        if (event.getWorld().isClientSide() || !(event.getWorld() instanceof ServerWorld)) {
            return;
        }
        final TileEntity tile = event.getWorld().getBlockEntity(event.getPos());
        if (tile instanceof LockableLootTileEntity) {
            if (tile instanceof VaultChestTileEntity) {
                ((VaultChestTileEntity)tile).generateChestLoot(event.getPlayer(), true);
            }
            else {
                ((LockableLootTileEntity)tile).unpackLootTable(event.getPlayer());
            }
        }
        if (tile instanceof VaultChestTileEntity) {
            final Random rand = event.getWorld().getRandom();
            final VaultRarity rarity = ((VaultChestTileEntity)tile).getRarity();
            if (rarity == VaultRarity.EPIC) {
                event.getWorld().playSound((PlayerEntity)null, event.getPos(), ModSounds.VAULT_CHEST_EPIC_OPEN, SoundCategory.BLOCKS, 0.5f, rand.nextFloat() * 0.1f + 0.9f);
            }
            else if (rarity == VaultRarity.OMEGA) {
                event.getWorld().playSound((PlayerEntity)null, event.getPos(), ModSounds.VAULT_CHEST_OMEGA_OPEN, SoundCategory.BLOCKS, 0.5f, rand.nextFloat() * 0.1f + 0.9f);
            }
            else if (rarity == VaultRarity.RARE) {
                event.getWorld().playSound((PlayerEntity)null, event.getPos(), ModSounds.VAULT_CHEST_RARE_OPEN, SoundCategory.BLOCKS, 0.5f, rand.nextFloat() * 0.1f + 0.9f);
            }
        }
    }
    
    @SubscribeEvent
    public static void onCraftVaultgear(final PlayerEvent.ItemCraftedEvent event) {
        final PlayerEntity player = event.getPlayer();
        if (player.getCommandSenderWorld().isClientSide()) {
            return;
        }
        final ItemStack crafted = event.getCrafting();
        if (!(crafted.getItem() instanceof VaultGear)) {
            return;
        }
        if (crafted.getItem() instanceof EtchingItem) {
            return;
        }
        final int slot = SideOnlyFixer.getSlotFor(player.inventory, crafted);
        if (slot != -1) {
            ModAttributes.GEAR_CRAFTED_BY.create(player.inventory.getItem(slot), player.getName().getString());
        }
        ModAttributes.GEAR_CRAFTED_BY.create(crafted, player.getName().getString());
    }
    
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onItemTooltip(final ItemTooltipEvent event) {
        if (Minecraft.getInstance().player != null && Minecraft.getInstance().player.isCreative()) {
            return;
        }
        for (int i = 0; i < event.getToolTip().size(); ++i) {
            final ITextComponent txt = event.getToolTip().get(i);
            if (txt.getString().contains("the_vault:idol")) {
                event.getToolTip().set(i, new StringTextComponent("the_vault:idol").setStyle(txt.getStyle()));
            }
        }
    }
    
    @SubscribeEvent
    public static void onPlayerEnterVault(final PlayerEvent.PlayerChangedDimensionEvent event) {
        final PlayerEntity player = event.getPlayer();
        if (event.getTo() == Vault.VAULT_KEY && player instanceof ServerPlayerEntity) {
            final ServerPlayerEntity serverPlayer = (ServerPlayerEntity)player;
            AdvancementHelper.grantCriterion(serverPlayer, Vault.id("main/root"), "entered_vault");
            AdvancementHelper.grantCriterion(serverPlayer, Vault.id("armors/root"), "entered_vault");
        }
    }
    
    @SubscribeEvent
    public static void onVaultCharmUse(final EntityItemPickupEvent event) {
        if (!(event.getPlayer() instanceof ServerPlayerEntity)) {
            return;
        }
        final ServerPlayerEntity player = (ServerPlayerEntity)event.getPlayer();
        final ItemEntity itemEntity = event.getItem();
        final ItemStack stack = itemEntity.getItem();
        if (stack.isEmpty()) {
            return;
        }
        final ServerWorld world = player.getLevel();
        if (world.dimension() != Vault.VAULT_KEY) {
            return;
        }
        if (!hasVaultCharm(player.inventory)) {
            return;
        }
        final List<ResourceLocation> whitelist = VaultCharmData.get(world).getWhitelistedItems(player);
        if (whitelist.contains(stack.getItem().getRegistryName())) {
            event.setCanceled(true);
            itemEntity.remove();
            world.playSound((PlayerEntity)null, player.blockPosition(), SoundEvents.ITEM_PICKUP, SoundCategory.PLAYERS, 0.2f, (world.random.nextFloat() - world.random.nextFloat()) * 1.4f + 2.0f);
        }
    }
    
    private static boolean hasVaultCharm(final PlayerInventory inventory) {
        for (int slot = 0; slot < inventory.getContainerSize(); ++slot) {
            final ItemStack stack = inventory.getItem(slot);
            if (!stack.isEmpty()) {
                if (stack.getItem() == ModItems.VAULT_CHARM) {
                    return true;
                }
            }
        }
        return false;
    }
}
