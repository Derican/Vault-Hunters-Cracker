// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.util.calc;

import iskallia.vault.skill.talent.type.ThornsDamageTalent;
import net.minecraft.item.ItemStack;
import iskallia.vault.init.ModAttributes;
import iskallia.vault.attribute.FloatAttribute;
import iskallia.vault.item.gear.VaultGear;
import net.minecraft.inventory.EquipmentSlotType;
import iskallia.vault.skill.set.SetTree;
import java.util.Iterator;
import iskallia.vault.skill.talent.TalentTree;
import net.minecraft.entity.LivingEntity;
import iskallia.vault.skill.set.PorcupineSet;
import iskallia.vault.skill.set.SetNode;
import iskallia.vault.world.data.PlayerSetsData;
import iskallia.vault.skill.talent.type.ThornsChanceTalent;
import iskallia.vault.skill.talent.type.ThornsTalent;
import net.minecraft.entity.player.PlayerEntity;
import iskallia.vault.world.data.PlayerTalentsData;
import net.minecraft.entity.player.ServerPlayerEntity;

public class ThornsHelper
{
    public static float getPlayerThornsChance(final ServerPlayerEntity player) {
        float chance = 0.0f;
        final TalentTree tree = PlayerTalentsData.get(player.getLevel()).getTalents((PlayerEntity)player);
        for (final ThornsTalent talent : tree.getTalents(ThornsTalent.class)) {
            chance += talent.getThornsChance();
        }
        for (final ThornsChanceTalent talent2 : tree.getTalents(ThornsChanceTalent.class)) {
            chance += talent2.getAdditionalThornsChance();
        }
        final SetTree sets = PlayerSetsData.get(player.getLevel()).getSets((PlayerEntity)player);
        for (final SetNode<?> node : sets.getNodes()) {
            if (!(node.getSet() instanceof PorcupineSet)) {
                continue;
            }
            final PorcupineSet set = (PorcupineSet)node.getSet();
            chance += set.getAdditionalThornsChance();
        }
        chance += getThornsChance((LivingEntity)player);
        return chance;
    }
    
    public static float getThornsChance(final LivingEntity entity) {
        float chance = 0.0f;
        for (final EquipmentSlotType slot : EquipmentSlotType.values()) {
            final ItemStack stack = entity.getItemBySlot(slot);
            if (!(stack.getItem() instanceof VaultGear) || ((VaultGear)stack.getItem()).isIntendedForSlot(slot)) {
                chance += ModAttributes.THORNS_CHANCE.getOrDefault(stack, 0.0f).getValue(stack);
            }
        }
        return chance;
    }
    
    public static float getPlayerThornsDamage(final ServerPlayerEntity player) {
        float additionalMultiplier = 0.0f;
        final TalentTree tree = PlayerTalentsData.get(player.getLevel()).getTalents((PlayerEntity)player);
        for (final ThornsTalent talent : tree.getTalents(ThornsTalent.class)) {
            additionalMultiplier += talent.getThornsDamage();
        }
        for (final ThornsDamageTalent talent2 : tree.getTalents(ThornsDamageTalent.class)) {
            additionalMultiplier += talent2.getAdditionalThornsDamage();
        }
        final SetTree sets = PlayerSetsData.get(player.getLevel()).getSets((PlayerEntity)player);
        for (final SetNode<?> node : sets.getNodes()) {
            if (!(node.getSet() instanceof PorcupineSet)) {
                continue;
            }
            final PorcupineSet set = (PorcupineSet)node.getSet();
            additionalMultiplier += set.getAdditionalThornsDamage();
        }
        additionalMultiplier += getThornsDamage((LivingEntity)player);
        return additionalMultiplier;
    }
    
    public static float getThornsDamage(final LivingEntity entity) {
        float additionalMultiplier = 0.0f;
        for (final EquipmentSlotType slot : EquipmentSlotType.values()) {
            final ItemStack stack = entity.getItemBySlot(slot);
            if (!(stack.getItem() instanceof VaultGear) || ((VaultGear)stack.getItem()).isIntendedForSlot(slot)) {
                additionalMultiplier += ModAttributes.THORNS_DAMAGE.getOrDefault(stack, 0.0f).getValue(stack);
            }
        }
        return additionalMultiplier;
    }
}
