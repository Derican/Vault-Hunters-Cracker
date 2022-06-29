
package iskallia.vault.skill.ability.effect.sub;

import net.minecraft.tileentity.TileEntity;
import iskallia.vault.skill.ability.config.HunterConfig;
import java.awt.Color;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Tuple;
import java.util.List;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import iskallia.vault.init.ModEntities;
import net.minecraft.entity.LivingEntity;
import java.util.function.Predicate;
import iskallia.vault.skill.ability.config.sub.HunterObjectiveConfig;
import iskallia.vault.skill.ability.effect.HunterAbility;

public class HunterObjectiveAbility extends HunterAbility<HunterObjectiveConfig> {
    @Override
    protected Predicate<LivingEntity> getEntityFilter() {
        return e -> e.isAlive() && !e.isSpectator() && e.getType().equals(ModEntities.TREASURE_GOBLIN);
    }

    @Override
    protected List<Tuple<BlockPos, Color>> selectPositions(final HunterObjectiveConfig config, final World world,
            final PlayerEntity player) {
        final List<Tuple<BlockPos, Color>> entityPositions = super.selectPositions(config, world, player);
        final Color c = new Color(config.getColor(), false);
        this.forEachTileEntity(config, world, player, (pos, tile) -> {
            if (config.shouldHighlightTileEntity(tile)) {
                entityPositions.add(new Tuple((Object) pos, (Object) c));
            }
            return;
        });
        return entityPositions;
    }
}
