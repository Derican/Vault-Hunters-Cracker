// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.util.calc;

import iskallia.vault.attribute.FloatAttribute;
import iskallia.vault.init.ModAttributes;
import iskallia.vault.item.gear.VaultGear;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import java.util.function.Function;
import iskallia.vault.init.ModEffects;
import iskallia.vault.skill.set.SetTree;
import iskallia.vault.world.vault.VaultRaid;
import java.util.Iterator;
import iskallia.vault.skill.set.DreamSet;
import iskallia.vault.skill.set.GolemSet;
import iskallia.vault.skill.set.SetNode;
import iskallia.vault.world.data.PlayerSetsData;
import net.minecraft.world.server.ServerWorld;
import iskallia.vault.world.vault.modifier.StatModifier;
import iskallia.vault.util.PlayerFilter;
import iskallia.vault.world.vault.influence.VaultAttributeInfluence;
import iskallia.vault.world.data.VaultRaidData;
import iskallia.vault.aura.type.ResistanceAuraConfig;
import iskallia.vault.aura.ActiveAura;
import net.minecraft.entity.Entity;
import iskallia.vault.aura.AuraManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;

public class ResistanceHelper
{
    public static float getPlayerResistancePercent(final ServerPlayerEntity player) {
        return MathHelper.clamp(getPlayerResistancePercentUnlimited(player), 0.0f, AttributeLimitHelper.getResistanceLimit((PlayerEntity)player));
    }
    
    public static float getPlayerResistancePercentUnlimited(final ServerPlayerEntity player) {
        float resistancePercent = 0.0f;
        resistancePercent += getResistancePercent((LivingEntity)player);
        for (final ActiveAura aura : AuraManager.getInstance().getAurasAffecting((Entity)player)) {
            if (aura.getAura() instanceof ResistanceAuraConfig) {
                resistancePercent += ((ResistanceAuraConfig)aura.getAura()).getAdditionalResistance();
            }
        }
        final VaultRaid vault = VaultRaidData.get(player.getLevel()).getActiveFor(player);
        if (vault != null) {
            for (final VaultAttributeInfluence influence : vault.getInfluences().getInfluences(VaultAttributeInfluence.class)) {
                if (influence.getType() == VaultAttributeInfluence.Type.RESISTANCE && !influence.isMultiplicative()) {
                    resistancePercent += influence.getValue();
                }
            }
            for (final StatModifier modifier : vault.getActiveModifiersFor(PlayerFilter.of((PlayerEntity)player), StatModifier.class)) {
                if (modifier.getStat() == StatModifier.Statistic.RESISTANCE) {
                    resistancePercent *= modifier.getMultiplier();
                }
            }
            for (final VaultAttributeInfluence influence : vault.getInfluences().getInfluences(VaultAttributeInfluence.class)) {
                if (influence.getType() == VaultAttributeInfluence.Type.PARRY && influence.isMultiplicative()) {
                    resistancePercent += influence.getValue();
                }
            }
        }
        final SetTree sets = PlayerSetsData.get((ServerWorld)player.level).getSets((PlayerEntity)player);
        for (final SetNode<?> node : sets.getNodes()) {
            if (node.getSet() instanceof GolemSet) {
                final GolemSet set = (GolemSet)node.getSet();
                resistancePercent += set.getBonusResistance();
            }
            if (node.getSet() instanceof DreamSet) {
                final DreamSet set2 = (DreamSet)node.getSet();
                resistancePercent += set2.getIncreasedResistance();
            }
        }
        return resistancePercent;
    }
    
    public static float getResistancePercent(final LivingEntity entity) {
        float resistancePercent = 0.0f;
        resistancePercent += getGearResistanceChance(entity::getItemBySlot);
        if (entity.hasEffect(ModEffects.RESISTANCE)) {
            resistancePercent += (entity.getEffect(ModEffects.RESISTANCE).getAmplifier() + 1) / 100.0f;
        }
        return resistancePercent;
    }
    
    public static float getGearResistanceChance(final Function<EquipmentSlotType, ItemStack> gearProvider) {
        float resistancePercent = 0.0f;
        for (final EquipmentSlotType slot : EquipmentSlotType.values()) {
            final ItemStack stack = gearProvider.apply(slot);
            if (!(stack.getItem() instanceof VaultGear) || ((VaultGear)stack.getItem()).isIntendedForSlot(slot)) {
                resistancePercent += ModAttributes.EXTRA_RESISTANCE.get(stack).map(attribute -> attribute.getValue(stack)).orElse(0.0f);
                resistancePercent += ModAttributes.ADD_EXTRA_RESISTANCE.get(stack).map(attribute -> attribute.getValue(stack)).orElse(0.0f);
            }
        }
        return MathHelper.clamp(resistancePercent, 0.0f, 1.0f);
    }
}
