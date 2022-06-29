
package iskallia.vault.skill.ability.effect.sub;

import net.minecraft.tags.BlockTags;
import iskallia.vault.Vault;
import net.minecraft.block.Block;
import net.minecraft.world.server.ServerWorld;
import iskallia.vault.skill.ability.config.sub.VeinMinerVoidConfig;
import iskallia.vault.skill.ability.effect.VeinMinerAbility;

public class VeinMinerVoidAbility extends VeinMinerAbility<VeinMinerVoidConfig> {
    @Override
    public boolean shouldVoid(final ServerWorld world, final Block targetBlock) {
        return world.dimension() == Vault.VAULT_KEY
                && !targetBlock.is(BlockTags.getAllTags().getTagOrEmpty(Vault.id("voidmine_exclusions")));
    }
}
