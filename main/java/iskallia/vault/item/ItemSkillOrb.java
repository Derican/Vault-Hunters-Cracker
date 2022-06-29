// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.item;

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

public class ItemSkillOrb extends Item
{
    public ItemSkillOrb(final ItemGroup group) {
        super(new Item.Properties().tab(group).stacksTo(64));
        this.setRegistryName(Vault.id("skill_orb"));
    }
    
    public ActionResult<ItemStack> use(final World world, final PlayerEntity player, final Hand hand) {
        final ItemStack heldItemStack = player.getItemInHand(hand);
        world.playSound((PlayerEntity)null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_LEVELUP, SoundCategory.NEUTRAL, 0.5f, 0.4f / (ItemSkillOrb.random.nextFloat() * 0.4f + 0.8f));
        if (!world.isClientSide) {
            final PlayerVaultStatsData statsData = PlayerVaultStatsData.get((ServerWorld)world);
            statsData.addSkillPoint((ServerPlayerEntity)player, 1);
        }
        player.awardStat(Stats.ITEM_USED.get((Object)this));
        if (!player.abilities.instabuild) {
            heldItemStack.shrink(1);
        }
        return (ActionResult<ItemStack>)ActionResult.sidedSuccess((Object)heldItemStack, world.isClientSide());
    }
}
