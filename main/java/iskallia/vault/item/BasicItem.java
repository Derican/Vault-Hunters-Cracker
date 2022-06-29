
package iskallia.vault.item;

import java.util.Collection;
import java.util.Arrays;
import net.minecraft.client.util.ITooltipFlag;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.item.Item;

public class BasicItem extends Item {
    ITextComponent[] tooltip;

    public BasicItem(final ResourceLocation id, final Item.Properties properties) {
        super(properties);
        this.setRegistryName(id);
    }

    public BasicItem withTooltip(final ITextComponent... tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public void appendHoverText(final ItemStack stack, @Nullable final World worldIn, final List<ITextComponent> tooltip,
            final ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, (List) tooltip, flagIn);
        if (this.tooltip != null) {
            tooltip.addAll(Arrays.asList(this.tooltip));
        }
    }
}
