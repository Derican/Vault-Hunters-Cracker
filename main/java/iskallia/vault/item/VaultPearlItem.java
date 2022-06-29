// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.stats.Stats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import iskallia.vault.item.entity.VaultPearlEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import iskallia.vault.init.ModConfigs;
import net.minecraft.item.ItemStack;
import iskallia.vault.init.ModItems;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.EnderPearlItem;

public class VaultPearlItem extends EnderPearlItem
{
    public VaultPearlItem(final ResourceLocation id) {
        super(new Item.Properties().stacksTo(1).tab(ModItems.VAULT_MOD_GROUP).durability(0));
        this.setRegistryName(id);
    }
    
    public boolean showDurabilityBar(final ItemStack stack) {
        return stack.getDamageValue() > 0;
    }
    
    public void setDamage(final ItemStack stack, final int damage) {
        final int currentDamage = this.getDamage(stack);
        if (damage <= currentDamage) {
            return;
        }
        super.setDamage(stack, damage);
    }
    
    public double getDurabilityForDisplay(final ItemStack stack) {
        return stack.getDamageValue() / (double)this.getMaxDamage(stack);
    }
    
    public int getMaxDamage(final ItemStack stack) {
        if (ModConfigs.VAULT_UTILITIES != null) {
            return ModConfigs.VAULT_UTILITIES.getVaultPearlMaxUses();
        }
        return 0;
    }
    
    public boolean canBeDepleted() {
        return true;
    }
    
    public ActionResult<ItemStack> use(final World world, final PlayerEntity player, final Hand hand) {
        if (hand != Hand.MAIN_HAND) {
            return (ActionResult<ItemStack>)ActionResult.pass((Object)player.getItemInHand(hand));
        }
        final ItemStack stack = player.getItemInHand(hand);
        world.playSound((PlayerEntity)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 0.5f, 0.4f / (VaultPearlItem.random.nextFloat() * 0.4f + 0.8f));
        player.getCooldowns().addCooldown((Item)this, 20);
        if (!world.isClientSide) {
            final VaultPearlEntity pearl = new VaultPearlEntity(world, (LivingEntity)player);
            pearl.setItem(stack);
            pearl.shootFromRotation((Entity)player, player.xRot, player.yRot, 0.0f, 1.5f, 1.0f);
            world.addFreshEntity((Entity)pearl);
            stack.hurtAndBreak(1, (LivingEntity)player, e -> e.broadcastBreakEvent(hand));
        }
        player.awardStat(Stats.ITEM_USED.get((Object)this));
        return (ActionResult<ItemStack>)ActionResult.sidedSuccess((Object)stack, world.isClientSide());
    }
    
    public boolean isEnchantable(final ItemStack stack) {
        return false;
    }
    
    public boolean canApplyAtEnchantingTable(final ItemStack stack, final Enchantment enchantment) {
        return false;
    }
    
    public boolean isBookEnchantable(final ItemStack stack, final ItemStack book) {
        return false;
    }
}
