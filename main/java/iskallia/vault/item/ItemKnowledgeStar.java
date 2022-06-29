// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.item;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.client.util.ITooltipFlag;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.stats.Stats;
import net.minecraft.entity.player.ServerPlayerEntity;
import iskallia.vault.world.data.PlayerVaultStatsData;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import iskallia.vault.Vault;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;

public class ItemKnowledgeStar extends Item
{
    public ItemKnowledgeStar(final ItemGroup group) {
        super(new Item.Properties().tab(group).stacksTo(64));
        this.setRegistryName(Vault.id("knowledge_star"));
    }
    
    public ActionResult<ItemStack> use(final World world, final PlayerEntity player, final Hand hand) {
        final ItemStack heldItemStack = player.getItemInHand(hand);
        world.playSound((PlayerEntity)null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_LEVELUP, SoundCategory.NEUTRAL, 0.5f, 0.4f / (ItemKnowledgeStar.random.nextFloat() * 0.4f + 0.8f));
        if (!world.isClientSide) {
            final PlayerVaultStatsData statsData = PlayerVaultStatsData.get((ServerWorld)world);
            statsData.addKnowledgePoints((ServerPlayerEntity)player, 1);
        }
        player.awardStat(Stats.ITEM_USED.get((Object)this));
        if (!player.abilities.instabuild) {
            heldItemStack.shrink(1);
        }
        return (ActionResult<ItemStack>)ActionResult.sidedSuccess((Object)heldItemStack, world.isClientSide());
    }
    
    public ITextComponent getName(final ItemStack stack) {
        return (ITextComponent)((IFormattableTextComponent)super.getName(stack)).setStyle(Style.EMPTY.withColor(Color.fromRgb(4249521)));
    }
    
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(final ItemStack stack, @Nullable final World worldIn, final List<ITextComponent> tooltip, final ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, (List)tooltip, flagIn);
    }
}
