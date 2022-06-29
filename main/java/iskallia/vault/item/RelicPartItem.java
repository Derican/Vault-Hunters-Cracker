
package iskallia.vault.item;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.text.ITextComponent;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemGroup;
import iskallia.vault.util.RelicSet;
import net.minecraft.item.Item;

public class RelicPartItem extends Item {
    protected RelicSet relicSet;

    public RelicPartItem(final ItemGroup group, final ResourceLocation id) {
        super(new Item.Properties().tab(group).stacksTo(64));
        this.setRegistryName(id);
    }

    public RelicSet getRelicSet() {
        return this.relicSet;
    }

    public void setRelicSet(final RelicSet relicSet) {
        this.relicSet = relicSet;
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(final ItemStack stack, @Nullable final World world, final List<ITextComponent> tooltip,
            final ITooltipFlag flag) {
        final StringTextComponent line = new StringTextComponent("Vault Relic - " + this.relicSet.getName());
        line.setStyle(Style.EMPTY.withColor(Color.fromRgb(-3755746)));
        tooltip.add((ITextComponent) new StringTextComponent(""));
        tooltip.add((ITextComponent) line);
        super.appendHoverText(stack, world, (List) tooltip, flag);
    }
}
