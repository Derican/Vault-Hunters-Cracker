
package iskallia.vault.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.IFormattableTextComponent;
import iskallia.vault.init.ModAttributes;
import iskallia.vault.attribute.EnumAttribute;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.Overwrite;
import iskallia.vault.config.DurabilityConfig;
import java.util.Iterator;
import iskallia.vault.skill.talent.TalentTree;
import net.minecraft.advancements.CriteriaTriggers;
import iskallia.vault.init.ModConfigs;
import net.minecraft.item.ArmorItem;
import iskallia.vault.skill.talent.type.UnbreakableTalent;
import net.minecraft.entity.player.PlayerEntity;
import iskallia.vault.world.data.PlayerTalentsData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import iskallia.vault.skill.set.PlayerSet;
import iskallia.vault.item.gear.VaultGear;
import iskallia.vault.item.gear.VaultArmorItem;
import javax.annotation.Nullable;
import net.minecraft.entity.player.ServerPlayerEntity;
import java.util.Random;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = { ItemStack.class }, priority = 1001)
public abstract class MixinItemStack {
    @Shadow
    public abstract boolean isDamageableItem();

    @Shadow
    public abstract int getDamageValue();

    @Shadow
    public abstract void setDamageValue(final int p0);

    @Shadow
    public abstract int getMaxDamage();

    @Shadow
    public abstract ItemStack copy();

    @Shadow
    public abstract Item getItem();

    @Overwrite
    public boolean hurt(int damage, final Random rand, @Nullable final ServerPlayerEntity damager) {
        if (!this.isDamageableItem()) {
            return false;
        }
        if (damager != null && this.getItem() instanceof VaultArmorItem
                && PlayerSet.isActive(VaultGear.Set.ZOD, (LivingEntity) damager)) {
            return false;
        }
        if (damage > 0) {
            int unbreakingLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.UNBREAKING, (ItemStack) this);
            if (damager != null) {
                final TalentTree abilities = PlayerTalentsData.get(damager.getLevel())
                        .getTalents((PlayerEntity) damager);
                for (final UnbreakableTalent talent : abilities.getTalents(UnbreakableTalent.class)) {
                    unbreakingLevel += (int) talent.getExtraUnbreaking();
                }
            }
            int damageNegation = 0;
            final boolean isArmor = ((ItemStack) this).getItem() instanceof ArmorItem;
            final DurabilityConfig cfg = ModConfigs.DURBILITY;
            final float chance = isArmor ? cfg.getArmorDurabilityIgnoreChance(unbreakingLevel)
                    : cfg.getDurabilityIgnoreChance(unbreakingLevel);
            for (int k = 0; unbreakingLevel > 0 && k < damage; ++k) {
                if (rand.nextFloat() < chance) {
                    ++damageNegation;
                }
            }
            damage -= damageNegation;
            if (damage <= 0) {
                return false;
            }
        }
        if (damager != null && damage != 0) {
            CriteriaTriggers.ITEM_DURABILITY_CHANGED.trigger(damager, (ItemStack) this, this.getDamageValue() + damage);
        }
        final int absDamage = this.getDamageValue() + damage;
        this.setDamageValue(absDamage);
        return absDamage >= this.getMaxDamage();
    }

    @Inject(method = { "getDisplayName" }, at = { @At("RETURN") }, cancellable = true)
    public void useGearRarity(final CallbackInfoReturnable<ITextComponent> ci) {
        if (!(this.getItem() instanceof VaultGear)) {
            return;
        }
        final ItemStack itemStack = this.copy();
        final VaultGear.State state = ModAttributes.GEAR_STATE.getOrDefault(itemStack, VaultGear.State.UNIDENTIFIED)
                .getValue(itemStack);
        final VaultGear.Rarity rarity = ModAttributes.GEAR_RARITY.getOrDefault(itemStack, VaultGear.Rarity.COMMON)
                .getValue(itemStack);
        if (state == VaultGear.State.UNIDENTIFIED) {
            return;
        }
        final IFormattableTextComponent returnValue = (IFormattableTextComponent) ci.getReturnValue();
        final Style style = returnValue.getStyle().withColor(rarity.getColor());
        ci.setReturnValue((Object) returnValue.setStyle(style));
    }
}
