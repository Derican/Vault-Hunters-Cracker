
package iskallia.vault.block;

import net.minecraft.block.Block;
import javax.annotation.Nullable;
import iskallia.vault.init.ModBlocks;
import net.minecraft.tileentity.TileEntity;
import iskallia.vault.world.data.PlayerVaultStatsData;
import iskallia.vault.util.WeekKey;
import iskallia.vault.block.entity.TrophyStatueTileEntity;
import net.minecraft.nbt.CompoundNBT;
import iskallia.vault.block.entity.LootStatueTileEntity;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.block.BlockState;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import iskallia.vault.util.StatueType;
import net.minecraft.util.math.shapes.VoxelShape;

public class TrophyBlock extends LootStatueBlock {
    public static final VoxelShape SHAPE;

    public TrophyBlock() {
        super(StatueType.TROPHY,
                AbstractBlock.Properties.of(Material.METAL, MaterialColor.GOLD)
                        .strength(5.0f, 3600000.0f).noOcclusion().noCollission());
    }

    @Override
    public VoxelShape getShape(final BlockState state, final IBlockReader worldIn, final BlockPos pos,
            final ISelectionContext context) {
        return TrophyBlock.SHAPE;
    }

    @Override
    protected void setStatueTileData(final LootStatueTileEntity lootStatue, final CompoundNBT blockEntityTag) {
        super.setStatueTileData(lootStatue, blockEntityTag);
        if (lootStatue instanceof TrophyStatueTileEntity) {
            final TrophyStatueTileEntity trophyStatue = (TrophyStatueTileEntity) lootStatue;
            final WeekKey week = WeekKey.deserialize(blockEntityTag.getCompound("trophyWeek"));
            final PlayerVaultStatsData.PlayerRecordEntry recordEntry = PlayerVaultStatsData.PlayerRecordEntry
                    .deserialize(blockEntityTag.getCompound("recordEntry"));
            trophyStatue.setWeek(week);
            trophyStatue.setRecordEntry(recordEntry);
        }
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(final BlockState state, final IBlockReader world) {
        return ModBlocks.TROPHY_STATUE_TILE_ENTITY.create();
    }

    static {
        SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 16.0, 14.0);
    }
}
