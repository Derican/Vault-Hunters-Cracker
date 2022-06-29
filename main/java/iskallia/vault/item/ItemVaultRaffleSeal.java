
package iskallia.vault.item;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.text.ITextComponent;
import java.util.List;
import javax.annotation.Nullable;
import iskallia.vault.util.NameProviderPublic;
import iskallia.vault.util.MiscUtils;
import net.minecraft.util.IItemProvider;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import iskallia.vault.init.ModItems;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.Item;

public class ItemVaultRaffleSeal extends Item {
    public ItemVaultRaffleSeal(final ResourceLocation id) {
        super(new Item.Properties().tab(ModItems.VAULT_MOD_GROUP).stacksTo(1));
        this.setRegistryName(id);
    }

    public static void setPlayerName(final ItemStack stack, final String name) {
        stack.getOrCreateTag().putString("PlayerName", name);
    }

    public static String getPlayerName(final ItemStack stack) {
        return stack.getOrCreateTag().getString("PlayerName");
    }

    public void inventoryTick(final ItemStack stack, final World world, final Entity entity, final int itemSlot,
            final boolean isSelected) {
        if (!(world instanceof ServerWorld) || !(entity instanceof ServerPlayerEntity)) {
            return;
        }
        final ServerWorld sWorld = (ServerWorld) world;
        final ServerPlayerEntity player = (ServerPlayerEntity) entity;
        if (stack.getCount() > 1) {
            while (stack.getCount() > 1) {
                stack.shrink(1);
                final ItemStack gearPiece = new ItemStack((IItemProvider) this);
                MiscUtils.giveItem(player, gearPiece);
            }
        }
        final String raffleName = getPlayerName(stack);
        if (raffleName.isEmpty()) {
            setPlayerName(stack, NameProviderPublic.getRandomName());
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(final ItemStack stack, @Nullable final World world, final List<ITextComponent> tooltip,
            final ITooltipFlag flag) {
        tooltip.add((ITextComponent) new StringTextComponent("Turns a crystal into a ")
                .withStyle(TextFormatting.GRAY)
                .append((ITextComponent) new StringTextComponent("Raffle").withStyle(TextFormatting.GOLD))
                .append(
                        (ITextComponent) new StringTextComponent(" crystal.").withStyle(TextFormatting.GRAY)));
        final String raffleName = getPlayerName(stack);
        if (!raffleName.isEmpty()) {
            tooltip.add((ITextComponent) new StringTextComponent("Player Boss: ").withStyle(TextFormatting.GRAY)
                    .append(
                            (ITextComponent) new StringTextComponent(raffleName).withStyle(TextFormatting.GREEN)));
        } else {
            tooltip.add((ITextComponent) new StringTextComponent("Player Boss: ").withStyle(TextFormatting.GRAY)
                    .append(
                            (ITextComponent) new StringTextComponent("???").withStyle(TextFormatting.GREEN)));
        }
    }
}
