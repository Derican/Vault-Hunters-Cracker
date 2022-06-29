
package iskallia.vault.item.gear;

import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import java.util.List;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.NonNullList;
import net.minecraft.item.ItemGroup;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attribute;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import javax.annotation.Nullable;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.AxeItem;

public class VaultAxeItem extends AxeItem implements VaultGear<VaultAxeItem> {
    public VaultAxeItem(final ResourceLocation id, final Item.Properties builder) {
        super((IItemTier) Tier.INSTANCE, 0.0f, -2.4f, builder);
        this.setRegistryName(id);
    }

    public int getModelsFor(final Rarity rarity) {
        return 9;
    }

    @Nullable
    public EquipmentSlotType getIntendedSlot() {
        return EquipmentSlotType.MAINHAND;
    }

    public float getDestroySpeed(final ItemStack stack, final BlockState state) {
        final float destroySpeed = super.getDestroySpeed(stack, state);
        return (destroySpeed > 0.0f) ? destroySpeed : 6.0f;
    }

    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(final EquipmentSlotType slot,
            final ItemStack stack) {
        return this.getAttributeModifiers(this, slot, stack,
                (Multimap<Attribute, AttributeModifier>) super.getAttributeModifiers(slot, stack));
    }

    public void fillItemCategory(final ItemGroup group, final NonNullList<ItemStack> items) {
        if (this.allowdedIn(group)) {
            this.fillItemGroup(items);
        }
    }

    public boolean isRepairable(final ItemStack stack) {
        return false;
    }

    public boolean isDamageable(final ItemStack stack) {
        return this.isDamageable(this, stack);
    }

    public int getMaxDamage(final ItemStack stack) {
        return this.getMaxDamage(this, stack, super.getMaxDamage(stack));
    }

    public ITextComponent getName(final ItemStack itemStack) {
        return this.getDisplayName(this, itemStack, super.getName(itemStack));
    }

    public ActionResult<ItemStack> use(final World world, final PlayerEntity player, final Hand hand) {
        return this.onItemRightClick(this, world, player, hand,
                (ActionResult<ItemStack>) super.use(world, player, hand));
    }

    public void inventoryTick(final ItemStack stack, final World world, final Entity entity, final int itemSlot,
            final boolean isSelected) {
        super.inventoryTick(stack, world, entity, itemSlot, isSelected);
        this.splitStack(this, stack, world, entity);
        if (entity instanceof ServerPlayerEntity) {
            this.inventoryTick(this, stack, world, (ServerPlayerEntity) entity, itemSlot, isSelected);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(final ItemStack stack, final World world, final List<ITextComponent> tooltip,
            final ITooltipFlag flag) {
        super.appendHoverText(stack, world, (List) tooltip, flag);
        this.addInformation(this, stack, tooltip, Screen.hasShiftDown());
    }

    public boolean canElytraFly(final ItemStack stack, final LivingEntity entity) {
        return this.canElytraFly(this, stack, entity);
    }

    public boolean elytraFlightTick(final ItemStack stack, final LivingEntity entity, final int flightTicks) {
        return this.elytraFlightTick(this, stack, entity, flightTicks);
    }
}
