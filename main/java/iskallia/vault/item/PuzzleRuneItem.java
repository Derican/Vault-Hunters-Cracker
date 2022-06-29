
package iskallia.vault.item;

import net.minecraft.world.World;
import net.minecraft.entity.player.PlayerEntity;
import iskallia.vault.attribute.EnumAttribute;
import iskallia.vault.block.PuzzleRuneBlock;
import iskallia.vault.init.ModAttributes;
import iskallia.vault.init.ModBlocks;
import net.minecraft.util.ActionResultType;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class PuzzleRuneItem extends BasicItem {
    public PuzzleRuneItem(final ResourceLocation id, final Item.Properties properties) {
        super(id, properties);
    }

    public ActionResultType onItemUseFirst(final ItemStack stack, final ItemUseContext context) {
        final PlayerEntity player = context.getPlayer();
        final World world = context.getLevel();
        if (player != null && player.isCreative() && !world.isClientSide
                && world.getBlockState(context.getClickedPos()).getBlockState()
                        .getBlock() != ModBlocks.PUZZLE_RUNE_BLOCK) {
            ModAttributes.PUZZLE_COLOR.create(stack,
                    ModAttributes.PUZZLE_COLOR.getOrCreate(stack, PuzzleRuneBlock.Color.YELLOW).getValue(stack).next());
            ItemRelicBoosterPack.successEffects(world, player.position());
            return ActionResultType.SUCCESS;
        }
        return super.onItemUseFirst(stack, context);
    }
}
