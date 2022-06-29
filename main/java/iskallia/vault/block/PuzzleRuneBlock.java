
package iskallia.vault.block;

import iskallia.vault.item.ItemRelicBoosterPack;
import iskallia.vault.init.ModBlocks;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import java.util.Locale;
import net.minecraft.util.IStringSerializable;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.world.IBlockReader;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import iskallia.vault.item.PuzzleRuneItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.Hand;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.state.StateContainer;
import javax.annotation.Nullable;
import iskallia.vault.init.ModAttributes;
import iskallia.vault.attribute.EnumAttribute;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.Property;
import net.minecraft.util.Direction;
import net.minecraft.block.BlockState;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.block.Block;

public class PuzzleRuneBlock extends Block {
    public static final DirectionProperty FACING;
    public static final EnumProperty<Color> COLOR;
    public static final BooleanProperty RUNE_PLACED;

    public PuzzleRuneBlock() {
        super(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE)
                .strength(-1.0f, 3600000.0f).noOcclusion().noDrops());
        this.registerDefaultState((BlockState) ((BlockState) ((BlockState) ((BlockState) this.stateDefinition.any())
                .setValue((Property) PuzzleRuneBlock.FACING, (Comparable) Direction.SOUTH))
                .setValue((Property) PuzzleRuneBlock.COLOR, (Comparable) Color.YELLOW))
                .setValue((Property) PuzzleRuneBlock.RUNE_PLACED, (Comparable) false));
    }

    public void fillItemCategory(final ItemGroup group, final NonNullList<ItemStack> items) {
    }

    @Nullable
    public BlockState getStateForPlacement(final BlockItemUseContext context) {
        return (BlockState) ((BlockState) ((BlockState) this.defaultBlockState()
                .setValue((Property) PuzzleRuneBlock.FACING, (Comparable) context.getHorizontalDirection()))
                .setValue((Property) PuzzleRuneBlock.COLOR,
                        (Comparable) ModAttributes.PUZZLE_COLOR.getOrDefault(context.getItemInHand(), Color.YELLOW)
                                .getValue(context.getItemInHand())))
                .setValue((Property) PuzzleRuneBlock.RUNE_PLACED, (Comparable) false);
    }

    protected void createBlockStateDefinition(final StateContainer.Builder<Block, BlockState> builder) {
        builder.add(new Property[] { (Property) PuzzleRuneBlock.FACING })
                .add(new Property[] { (Property) PuzzleRuneBlock.COLOR })
                .add(new Property[] { (Property) PuzzleRuneBlock.RUNE_PLACED });
    }

    public ActionResultType use(final BlockState state, final World world, final BlockPos pos,
            final PlayerEntity player, final Hand hand, final BlockRayTraceResult hit) {
        if (!world.isClientSide) {
            final ItemStack heldStack = player.getItemInHand(hand);
            if (heldStack.getItem() instanceof PuzzleRuneItem && this.isValidKey(heldStack, state)) {
                heldStack.shrink(1);
                final BlockState blockState = world.getBlockState(pos);
                world.setBlock(pos, (BlockState) blockState.setValue((Property) PuzzleRuneBlock.RUNE_PLACED,
                        (Comparable) true), 3);
                world.playSound((PlayerEntity) null, (double) pos.getX(), (double) pos.getY(),
                        (double) pos.getZ(), SoundEvents.END_PORTAL_FRAME_FILL, SoundCategory.BLOCKS, 1.0f, 1.0f);
            }
        }
        return super.use(state, world, pos, player, hand, hit);
    }

    public int getSignal(final BlockState state, final IBlockReader blockAccess, final BlockPos pos,
            final Direction side) {
        return state.getValue((Property) PuzzleRuneBlock.RUNE_PLACED) ? 15 : 0;
    }

    private boolean isValidKey(final ItemStack stack, final BlockState state) {
        return !(boolean) state.getValue((Property) PuzzleRuneBlock.RUNE_PLACED)
                && ModAttributes.PUZZLE_COLOR.get(stack).map(attribute -> attribute.getValue(stack))
                        .filter(value -> value == state.getValue((Property) PuzzleRuneBlock.COLOR)).isPresent();
    }

    static {
        FACING = BlockStateProperties.HORIZONTAL_FACING;
        COLOR = EnumProperty.create("color", (Class) Color.class);
        RUNE_PLACED = BooleanProperty.create("rune_placed");
    }

    public enum Color implements IStringSerializable {
        YELLOW,
        PINK,
        GREEN,
        BLUE;

        public Color next() {
            return values()[(this.ordinal() + 1) % values().length];
        }

        public String getSerializedName() {
            return this.name().toLowerCase(Locale.ENGLISH);
        }
    }

    public static class Item extends BlockItem {
        public Item(final Block block, final net.minecraft.item.Item.Properties properties) {
            super(block, properties);
        }

        public ActionResultType onItemUseFirst(final ItemStack stack, final ItemUseContext context) {
            final PlayerEntity player = context.getPlayer();
            final World world = context.getLevel();
            if (player != null && player.isCreative() && !world.isClientSide
                    && world.getBlockState(context.getClickedPos()).getBlockState()
                            .getBlock() == ModBlocks.PUZZLE_RUNE_BLOCK) {
                ModAttributes.PUZZLE_COLOR.create(stack,
                        ModAttributes.PUZZLE_COLOR.getOrCreate(stack, Color.YELLOW).getValue(stack).next());
                ItemRelicBoosterPack.successEffects(world, player.position());
                return ActionResultType.SUCCESS;
            }
            return super.onItemUseFirst(stack, context);
        }
    }
}
