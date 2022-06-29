// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.skill.set;

import java.util.Optional;
import iskallia.vault.attribute.EnumAttribute;
import iskallia.vault.init.ModAttributes;
import java.util.Arrays;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import java.util.function.BiPredicate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import iskallia.vault.item.gear.VaultGear;
import com.google.gson.annotations.Expose;

public class PlayerSet
{
    @Expose
    private String set;
    
    public PlayerSet(final VaultGear.Set set) {
        this.set = set.name();
    }
    
    public VaultGear.Set getSet() {
        return VaultGear.Set.valueOf(this.set);
    }
    
    public boolean shouldBeActive(final LivingEntity player) {
        return isActive(this.getSet(), player);
    }
    
    public void onAdded(final PlayerEntity player) {
    }
    
    public void onTick(final PlayerEntity player) {
    }
    
    public void onRemoved(final PlayerEntity player) {
    }
    
    public static boolean allMatch(final LivingEntity player, final BiPredicate<EquipmentSlotType, ItemStack> predicate, final EquipmentSlotType... slots) {
        return Arrays.stream(slots).allMatch(slot -> predicate.test(slot, player.getItemBySlot(slot)));
    }
    
    public static boolean allMatch(final LivingEntity player, final BiPredicate<EquipmentSlotType, ItemStack> predicate) {
        return allMatch(player, predicate, EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET);
    }
    
    public static boolean isActive(final VaultGear.Set set, final LivingEntity player) {
        return allMatch(player, (slot, stack) -> {
            final Optional<EnumAttribute<VaultGear.Set>> attribute = ModAttributes.GEAR_SET.get(stack);
            return attribute.isPresent() && attribute.get().getValue(stack) == set;
        });
    }
}
