// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.skill.ability.effect.sub;

import net.minecraft.tileentity.TileEntity;
import iskallia.vault.skill.ability.config.HunterConfig;
import java.util.ArrayList;
import java.awt.Color;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Tuple;
import java.util.List;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import iskallia.vault.skill.ability.config.sub.HunterChestsConfig;
import iskallia.vault.skill.ability.effect.HunterAbility;

public class HunterChestAbility extends HunterAbility<HunterChestsConfig>
{
    @Override
    protected List<Tuple<BlockPos, Color>> selectPositions(final HunterChestsConfig config, final World world, final PlayerEntity player) {
        final List<Tuple<BlockPos, Color>> entityPositions = new ArrayList<Tuple<BlockPos, Color>>();
        final Color c = new Color(config.getColor(), false);
        this.forEachTileEntity(config, world, player, (pos, tile) -> {
            if (config.shouldHighlightTileEntity(tile)) {
                entityPositions.add(new Tuple((Object)pos, (Object)c));
            }
            return;
        });
        return entityPositions;
    }
}
