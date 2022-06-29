
package iskallia.vault.world.vault.logic.objective.architect.modifier;

import javax.annotation.Nullable;
import iskallia.vault.world.vault.logic.objective.architect.processor.BlockPlacementPostProcessor;
import iskallia.vault.world.vault.logic.objective.architect.processor.VaultPieceProcessor;
import iskallia.vault.world.vault.VaultRaid;
import iskallia.vault.world.vault.logic.objective.architect.ArchitectObjective;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;
import com.google.gson.annotations.Expose;

public class BlockPlacementModifier extends VoteModifier {
    @Expose
    private final String block;
    @Expose
    private final int blocksPerSpawn;

    public BlockPlacementModifier(final String name, final String description, final int voteLockDurationChangeSeconds,
            final Block block, final int blocksPerSpawn) {
        super(name, description, voteLockDurationChangeSeconds);
        this.block = block.getRegistryName().toString();
        this.blocksPerSpawn = blocksPerSpawn;
    }

    public BlockState getBlock() {
        return Registry.BLOCK.getOptional(new ResourceLocation(this.block)).orElse(Blocks.AIR)
                .defaultBlockState();
    }

    public int getBlocksPerSpawn() {
        return this.blocksPerSpawn;
    }

    @Nullable
    @Override
    public VaultPieceProcessor getPostProcessor(final ArchitectObjective objective, final VaultRaid vault) {
        return new BlockPlacementPostProcessor(this.getBlock(), this.getBlocksPerSpawn());
    }
}
