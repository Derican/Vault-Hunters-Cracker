// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.block;

import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.block.BlockState;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.Block;

public class VaultBedrockBlock extends Block
{
    public VaultBedrockBlock() {
        super(AbstractBlock.Properties.of(Material.STONE).strength(-1.0f, 3600000.0f).noDrops().isValidSpawn((a, b, c, d) -> false));
    }
}
