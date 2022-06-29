// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.block;

import net.minecraft.util.IStringSerializable;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.scoreboard.ScoreCriteria;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import javax.annotation.Nullable;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.state.Property;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraft.state.EnumProperty;
import net.minecraft.block.Block;

public class MazeBlock extends Block
{
    public static final EnumProperty<MazeColor> COLOR;
    
    public MazeBlock() {
        super(AbstractBlock.Properties.of(Material.METAL, MaterialColor.METAL).strength(-1.0f, 3600000.0f).sound(SoundType.METAL));
        this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue((Property)MazeBlock.COLOR, (Comparable)MazeColor.RED));
    }
    
    protected void createBlockStateDefinition(final StateContainer.Builder<Block, BlockState> builder) {
        builder.add(new Property[] { (Property)MazeBlock.COLOR });
    }
    
    @Nullable
    public BlockState getStateForPlacement(final BlockItemUseContext context) {
        return super.getStateForPlacement(context);
    }
    
    public void stepOn(final World worldIn, final BlockPos pos, final Entity entityIn) {
        if (worldIn.isClientSide) {
            return;
        }
        if (!(entityIn instanceof PlayerEntity)) {
            return;
        }
        final PlayerEntity player = (PlayerEntity)entityIn;
        final Scoreboard scoreboard = worldIn.getScoreboard();
        if (scoreboard.getObjective("Color") == null) {
            scoreboard.addObjective("Color", ScoreCriteria.DUMMY, (ITextComponent)new StringTextComponent("Color"), ScoreCriteria.RenderType.INTEGER);
        }
        final ScoreObjective colorObjective = scoreboard.getObjective("Color");
        assert colorObjective != null;
        final Score colorScore = worldIn.getScoreboard().getOrCreatePlayerScore(player.getDisplayName().getString(), colorObjective);
        final MazeColor playerColor = MazeColor.values()[colorScore.getScore()];
        final MazeColor blockColor = (MazeColor)worldIn.getBlockState(pos).getValue((Property)MazeBlock.COLOR);
        if (playerColor != blockColor) {
            final BlockPos nextPosition = player.blockPosition().relative(player.getDirection(), 1);
            colorScore.setScore((playerColor == MazeColor.RED) ? MazeColor.BLUE.ordinal() : MazeColor.RED.ordinal());
            player.teleportTo(nextPosition.getX() + 0.5, (double)nextPosition.getY(), nextPosition.getZ() + 0.5);
        }
        super.stepOn(worldIn, pos, entityIn);
    }
    
    static {
        COLOR = EnumProperty.create("color", (Class)MazeColor.class);
    }
    
    public enum MazeColor implements IStringSerializable
    {
        RED("red"), 
        BLUE("blue");
        
        private String name;
        
        private MazeColor(final String name) {
            this.name = name;
        }
        
        public String getSerializedName() {
            return this.name;
        }
    }
}
