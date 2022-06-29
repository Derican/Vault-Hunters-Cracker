// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.world.vault.logic;

import javax.annotation.Nullable;
import java.util.Iterator;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.World;
import iskallia.vault.init.ModEntities;
import net.minecraft.entity.Entity;
import iskallia.vault.entity.EntityScaler;
import iskallia.vault.entity.EternalEntity;
import iskallia.vault.entity.EtchingVendorEntity;
import net.minecraft.entity.monster.SilverfishEntity;
import iskallia.vault.entity.AggressiveCowEntity;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.entity.LivingEntity;
import iskallia.vault.world.vault.VaultRaid;
import java.util.UUID;

public class VaultCowOverrides
{
    private static final UUID DAMAGE_NERF_MULTIPLIER;
    public static boolean forceSpecialVault;
    public static final String ENTITY_TAG = "replaced_entity";
    
    public static void setupVault(final VaultRaid vault) {
        vault.getEvents().add(VaultRaid.REPLACE_WITH_COW);
    }
    
    @Nullable
    public static AggressiveCowEntity replaceVaultEntity(final VaultRaid vault, final LivingEntity spawned, final ServerWorld world) {
        if (spawned instanceof SilverfishEntity || spawned instanceof EtchingVendorEntity || spawned instanceof EternalEntity) {
            spawned.addTag("replaced_entity");
            return null;
        }
        EntityScaler.scaleVaultEntity(vault, (Entity)spawned);
        final AggressiveCowEntity override = (AggressiveCowEntity)ModEntities.AGGRESSIVE_COW.create((World)world);
        final AttributeModifierManager mgr = override.getAttributes();
        for (final Attribute attr : ForgeRegistries.ATTRIBUTES) {
            if (spawned.getAttributes().hasAttribute(attr) && mgr.hasAttribute(attr)) {
                override.getAttribute(attr).setBaseValue(spawned.getAttributeValue(attr));
            }
        }
        mgr.getInstance(Attributes.ATTACK_DAMAGE).addPermanentModifier(new AttributeModifier(VaultCowOverrides.DAMAGE_NERF_MULTIPLIER, "Scaling Damage Reduction", 0.4, AttributeModifier.Operation.MULTIPLY_TOTAL));
        if (spawned instanceof MobEntity) {
            for (final EquipmentSlotType slot : EquipmentSlotType.values()) {
                final ItemStack has = override.getItemBySlot(slot);
                if (!has.isEmpty()) {
                    spawned.setItemSlot(slot, has.copy());
                }
                else {
                    spawned.setItemSlot(slot, ItemStack.EMPTY);
                }
            }
        }
        override.addTag("replaced_entity");
        return override;
    }
    
    static {
        DAMAGE_NERF_MULTIPLIER = UUID.fromString("384df991-f603-344c-a090-3693adfa984a");
        VaultCowOverrides.forceSpecialVault = false;
    }
}
