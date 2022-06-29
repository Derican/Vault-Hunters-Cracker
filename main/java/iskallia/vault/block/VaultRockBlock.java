
package iskallia.vault.block;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.MathHelper;
import java.util.Random;
import net.minecraft.block.SoundType;
import iskallia.vault.init.ModSounds;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.OreBlock;

public class VaultRockBlock extends OreBlock {
    public VaultRockBlock() {
        super(AbstractBlock.Properties.of(Material.STONE, MaterialColor.DIAMOND)
                .requiresCorrectToolForDrops().lightLevel(state -> 9).strength(3.0f, 3.0f)
                .sound((SoundType) ModSounds.VAULT_GEM));
    }

    protected int xpOnDrop(final Random rand) {
        return MathHelper.nextInt(rand, 3, 7);
    }
}
