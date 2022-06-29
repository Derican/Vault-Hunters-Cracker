// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.item;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.text.ITextComponent;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.world.World;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.Item;

public class WutaxShardItem extends Item
{
    public WutaxShardItem(final ResourceLocation id, final Item.Properties properties) {
        super(properties);
        this.setRegistryName(id);
    }
    
    public Rarity getRarity(final ItemStack stack) {
        return Rarity.RARE;
    }
    
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(final ItemStack stack, @Nullable final World worldIn, final List<ITextComponent> tooltip, final ITooltipFlag flagIn) {
        tooltip.add(StringTextComponent.EMPTY);
        tooltip.add((ITextComponent)new StringTextComponent("Reduces the level requirement of").withStyle(TextFormatting.GRAY));
        tooltip.add((ITextComponent)new StringTextComponent("any vault gear by 1 when combined").withStyle(TextFormatting.GRAY));
        tooltip.add((ITextComponent)new StringTextComponent("in an anvil with a vault gear item.").withStyle(TextFormatting.GRAY));
        super.appendHoverText(stack, worldIn, (List)tooltip, flagIn);
    }
}
