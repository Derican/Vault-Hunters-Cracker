// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.block;

import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.network.PacketBuffer;
import net.minecraft.state.StateContainer;
import javax.annotation.Nullable;
import iskallia.vault.init.ModBlocks;
import net.minecraft.tileentity.TileEntity;
import com.google.common.collect.Lists;
import net.minecraft.item.ItemStack;
import java.util.List;
import net.minecraft.loot.LootContext;
import iskallia.vault.world.vault.VaultRaid;
import iskallia.vault.world.vault.logic.objective.architect.VotingSession;
import java.util.function.Function;
import iskallia.vault.world.vault.logic.objective.architect.ArchitectObjective;
import iskallia.vault.world.data.VaultRaidData;
import net.minecraftforge.fml.network.PacketDistributor;
import iskallia.vault.init.ModNetwork;
import iskallia.vault.network.message.EffectMessage;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.Hand;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import iskallia.vault.util.VoxelUtils;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.state.Property;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.state.EnumProperty;
import java.util.Random;
import net.minecraft.block.Block;

public class StabilizerBlock extends Block
{
    private static final Random rand;
    public static final EnumProperty<DoubleBlockHalf> HALF;
    private static final VoxelShape SHAPE_TOP;
    private static final VoxelShape SHAPE_BOTTOM;
    
    public StabilizerBlock() {
        super(AbstractBlock.Properties.of(Material.GLASS).sound(SoundType.GLASS).strength(-1.0f, 3600000.0f).noOcclusion().noDrops());
        this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue((Property)StabilizerBlock.HALF, (Comparable)DoubleBlockHalf.LOWER));
    }
    
    private static VoxelShape makeShape() {
        final VoxelShape m1 = Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0);
        final VoxelShape m2 = Block.box(2.0, 2.0, 2.0, 14.0, 29.0, 14.0);
        return VoxelUtils.combineAll(IBooleanFunction.OR, m1, m2);
    }
    
    public VoxelShape getShape(final BlockState state, final IBlockReader worldIn, final BlockPos pos, final ISelectionContext context) {
        if (state.getValue((Property)StabilizerBlock.HALF) == DoubleBlockHalf.UPPER) {
            return StabilizerBlock.SHAPE_TOP;
        }
        return StabilizerBlock.SHAPE_BOTTOM;
    }
    
    public ActionResultType use(final BlockState state, final World world, final BlockPos pos, final PlayerEntity player, final Hand hand, final BlockRayTraceResult hit) {
        if (state.getValue((Property)StabilizerBlock.HALF) != DoubleBlockHalf.UPPER) {
            if (!world.isClientSide() && world instanceof ServerWorld && hand == Hand.MAIN_HAND) {
                if (this.startPoll((ServerWorld)world, pos)) {
                    return ActionResultType.SUCCESS;
                }
                this.spawnNoVoteParticles(world, pos);
            }
            return ActionResultType.SUCCESS;
        }
        final BlockState downState = world.getBlockState(pos.below());
        if (!(downState.getBlock() instanceof StabilizerBlock)) {
            return ActionResultType.SUCCESS;
        }
        return this.use(downState, world, pos.below(), player, hand, hit);
    }
    
    private void spawnNoVoteParticles(final World world, final BlockPos pos) {
        for (int i = 0; i < 40; ++i) {
            final Vector3d particlePos = new Vector3d(pos.getX() - 0.5 + StabilizerBlock.rand.nextFloat() * 2.0f, (double)(pos.getY() + StabilizerBlock.rand.nextFloat() * 8.0f), pos.getZ() - 0.5 + StabilizerBlock.rand.nextFloat() * 2.0f);
            final EffectMessage pkt = new EffectMessage(EffectMessage.Type.COLORED_FIREWORK, particlePos).addData(buf -> buf.writeInt(10027008));
            ModNetwork.CHANNEL.send(PacketDistributor.ALL.noArg(), (Object)pkt);
        }
    }
    
    private boolean startPoll(final ServerWorld world, final BlockPos pos) {
        final VaultRaid vault = VaultRaidData.get(world).getAt(world, pos);
        return vault != null && (vault.getActiveObjective(ArchitectObjective.class).map((Function<? super ArchitectObjective, ?>)ArchitectObjective::getActiveSession).map((Function<? super Object, ?>)VotingSession::getStabilizerPos).map(stabilizer -> stabilizer.equals((Object)pos)).orElse(false) || vault.getActiveObjective(ArchitectObjective.class).map(objective -> objective.createVotingSession(world, pos)).orElse(false));
    }
    
    public void onRemove(final BlockState state, final World world, final BlockPos pos, final BlockState newState, final boolean isMoving) {
        super.onRemove(state, world, pos, newState, isMoving);
        if (!state.is(newState.getBlock())) {
            if (state.getValue((Property)StabilizerBlock.HALF) == DoubleBlockHalf.UPPER) {
                final BlockState otherState = world.getBlockState(pos.below());
                if (otherState.is(state.getBlock())) {
                    world.removeBlock(pos.below(), isMoving);
                }
            }
            else {
                final BlockState otherState = world.getBlockState(pos.above());
                if (otherState.is(state.getBlock())) {
                    world.removeBlock(pos.above(), isMoving);
                }
            }
        }
    }
    
    public List<ItemStack> getDrops(final BlockState state, final LootContext.Builder builder) {
        return Lists.newArrayList();
    }
    
    public boolean hasTileEntity(final BlockState state) {
        return state.getValue((Property)StabilizerBlock.HALF) == DoubleBlockHalf.LOWER;
    }
    
    @Nullable
    public TileEntity createTileEntity(final BlockState state, final IBlockReader world) {
        if (this.hasTileEntity(state)) {
            return ModBlocks.STABILIZER_TILE_ENTITY.create();
        }
        return null;
    }
    
    protected void createBlockStateDefinition(final StateContainer.Builder<Block, BlockState> builder) {
        builder.add(new Property[] { (Property)StabilizerBlock.HALF });
    }
    
    static {
        rand = new Random();
        HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
        SHAPE_TOP = makeShape().move(0.0, -1.0, 0.0);
        SHAPE_BOTTOM = makeShape();
    }
}
