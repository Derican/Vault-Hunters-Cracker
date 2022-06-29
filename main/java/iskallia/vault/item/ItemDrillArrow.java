
package iskallia.vault.item;

import javax.annotation.Nonnull;
import iskallia.vault.util.MiscUtils;
import net.minecraft.entity.player.PlayerEntity;
import iskallia.vault.entity.DrillArrowEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.text.ITextComponent;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.world.World;
import net.minecraft.util.IItemProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ArrowItem;

public class ItemDrillArrow extends ArrowItem {
    public ItemDrillArrow(final ItemGroup group, final ResourceLocation id) {
        super(new Item.Properties().tab(group).stacksTo(63));
        this.setRegistryName(id);
    }

    public void fillItemCategory(final ItemGroup group, final NonNullList<ItemStack> items) {
        if (this.allowdedIn(group)) {
            for (final ArrowTier tier : ArrowTier.values()) {
                final ItemStack stack = new ItemStack((IItemProvider) this);
                setArrowTier(stack, tier);
                items.add((Object) stack);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(final ItemStack stack, @Nullable final World worldIn, final List<ITextComponent> tooltip,
            final ITooltipFlag flagIn) {
        tooltip.add((ITextComponent) new TranslationTextComponent(getArrowTier(stack).getName())
                .withStyle(TextFormatting.GOLD));
    }

    public AbstractArrowEntity createArrow(final World world, final ItemStack stack, final LivingEntity shooter) {
        return (AbstractArrowEntity) new DrillArrowEntity(world, shooter)
                .setMaxBreakCount(getArrowTier(stack).getBreakCount());
    }

    public boolean isInfinite(final ItemStack stack, final ItemStack bow, final PlayerEntity player) {
        return false;
    }

    @Nonnull
    public static ArrowTier getArrowTier(final ItemStack stack) {
        if (!(stack.getItem() instanceof ItemDrillArrow)) {
            return ArrowTier.NORMAL;
        }
        final int tierOrd = stack.getOrCreateTag().getInt("tier");
        return MiscUtils.getEnumEntry(ArrowTier.class, tierOrd);
    }

    public static void setArrowTier(final ItemStack stack, @Nonnull final ArrowTier tier) {
        if (!(stack.getItem() instanceof ItemDrillArrow)) {
            return;
        }
        stack.getOrCreateTag().putInt("tier", tier.ordinal());
    }

    public enum ArrowTier {
        NORMAL(400),
        RARE(700),
        EPIC(1000);

        private final int breakCount;

        private ArrowTier(final int breakCount) {
            this.breakCount = breakCount;
        }

        public int getBreakCount() {
            return this.breakCount;
        }

        public String getName() {
            return "item.the_vault.drill_arrow." + this.name().toLowerCase();
        }
    }
}
