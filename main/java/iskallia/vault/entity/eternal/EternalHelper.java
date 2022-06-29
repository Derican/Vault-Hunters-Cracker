
package iskallia.vault.entity.eternal;

import java.util.Iterator;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import java.util.HashMap;
import iskallia.vault.init.ModAttributes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.StringTextComponent;
import iskallia.vault.init.ModEntities;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import java.util.Map;
import iskallia.vault.entity.EternalEntity;
import net.minecraft.world.World;
import java.util.UUID;

public class EternalHelper {
    private static final UUID ETERNAL_SIZE_INCREASE;

    public static EternalEntity spawnEternal(final World world, final EternalDataAccess dataAccess) {
        return spawnEternal(world, dataAccess.getLevel(), dataAccess.isAncient(), dataAccess.getName(),
                dataAccess.getEquipment(), dataAccess.getEntityAttributes());
    }

    private static EternalEntity spawnEternal(final World world, final int level, final boolean isAncient,
            final String name, final Map<EquipmentSlotType, ItemStack> equipment,
            final Map<Attribute, Float> attributes) {
        final EternalEntity eternal = (EternalEntity) ModEntities.ETERNAL.create(world);
        eternal.setCustomName((ITextComponent) new StringTextComponent("[").withStyle(TextFormatting.GREEN)
                .append((ITextComponent) new StringTextComponent(String.valueOf(level))
                        .withStyle(TextFormatting.RED))
                .append(
                        (ITextComponent) new StringTextComponent("] " + name).withStyle(TextFormatting.GREEN)));
        eternal.setSkinName(name);
        equipment.forEach((slot, stack) -> {
            eternal.setItemSlot(slot, stack.copy());
            eternal.setDropChance(slot, 0.0f);
            return;
        });
        attributes.forEach((attribute, value) -> eternal.getAttribute(attribute).setBaseValue((double) value));
        eternal.heal(2.14748365E9f);
        if (isAncient) {
            eternal.getAttribute(ModAttributes.SIZE_SCALE).setBaseValue(1.2000000476837158);
        }
        return eternal;
    }

    public static float getEternalGearModifierAdjustments(final EternalDataAccess dataAccess, final Attribute attribute,
            final float value) {
        return getEternalGearModifierAdjustments(dataAccess.getEquipment(), attribute, value);
    }

    public static float getEternalGearModifierAdjustments(final Map<EquipmentSlotType, ItemStack> equipments,
            final Attribute attribute, float value) {
        final Map<AttributeModifier.Operation, List<AttributeModifier>> modifiers = new HashMap<AttributeModifier.Operation, List<AttributeModifier>>();
        AttributeModifier modifier = null;
        equipments.forEach((slotType, stack) -> {
            if (stack.isEmpty()) {
                return;
            } else {
                stack.getAttributeModifiers(slotType).get((Object) attribute).forEach(modifier -> modifiers
                        .computeIfAbsent(modifier.getOperation(), op -> new ArrayList()).add(modifier));
                return;
            }
        });
        final Iterator<AttributeModifier> iterator = modifiers
                .getOrDefault(AttributeModifier.Operation.ADDITION, Collections.emptyList()).iterator();
        while (iterator.hasNext()) {
            modifier = iterator.next();
            value += (float) modifier.getAmount();
        }
        float val = value;
        for (final AttributeModifier modifier2 : modifiers.getOrDefault(AttributeModifier.Operation.MULTIPLY_BASE,
                Collections.emptyList())) {
            val += (float) (value * modifier2.getAmount());
        }
        for (final AttributeModifier modifier2 : modifiers.getOrDefault(AttributeModifier.Operation.MULTIPLY_TOTAL,
                Collections.emptyList())) {
            val *= (float) modifier2.getAmount();
        }
        return val;
    }

    static {
        ETERNAL_SIZE_INCREASE = UUID.fromString("de6b75be-deb2-4711-8fac-08465031b2c3");
    }
}
