
package iskallia.vault.item;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import iskallia.vault.init.ModConfigs;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.StringTextComponent;
import iskallia.vault.world.vault.gen.VaultRoomNames;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.text.ITextComponent;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;

public class VaultRuneItem extends Item {
    private final String roomName;

    public VaultRuneItem(final ItemGroup group, final ResourceLocation id, final String roomName) {
        super(new Item.Properties().tab(group).stacksTo(8));
        this.roomName = roomName;
        this.setRegistryName(id);
    }

    public String getRoomName() {
        return this.roomName;
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(final ItemStack stack, @Nullable final World world, final List<ITextComponent> tooltip,
            final ITooltipFlag flag) {
        final ITextComponent displayName = VaultRoomNames.getName(this.getRoomName());
        if (displayName == null) {
            return;
        }
        tooltip.add(StringTextComponent.EMPTY);
        tooltip.add((ITextComponent) new StringTextComponent("Combine with a vault crystal to add")
                .withStyle(TextFormatting.GRAY));
        tooltip.add((ITextComponent) new StringTextComponent("a room to the vault: ")
                .withStyle(TextFormatting.GRAY).append(displayName));
        if (ModConfigs.VAULT_RUNE == null) {
            return;
        }
        ModConfigs.VAULT_RUNE.getMinimumLevel(this).ifPresent(minLevel -> {
            tooltip.add(StringTextComponent.EMPTY);
            tooltip.add(new StringTextComponent("Only usable after level ").withStyle(TextFormatting.GRAY)
                    .append((ITextComponent) new StringTextComponent(String.valueOf(minLevel))
                            .withStyle(TextFormatting.AQUA)));
        });
    }
}
