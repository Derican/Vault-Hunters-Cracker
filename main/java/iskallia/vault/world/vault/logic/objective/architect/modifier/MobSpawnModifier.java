// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.world.vault.logic.objective.architect.modifier;

import javax.annotation.Nullable;
import iskallia.vault.world.vault.logic.objective.architect.processor.VaultSpawnerSpawningPostProcessor;
import iskallia.vault.world.vault.logic.objective.architect.processor.VaultPieceProcessor;
import iskallia.vault.world.vault.VaultRaid;
import iskallia.vault.world.vault.logic.objective.architect.ArchitectObjective;
import com.google.gson.annotations.Expose;

public class MobSpawnModifier extends VoteModifier
{
    @Expose
    private final int blocksPerSpawn;
    
    public MobSpawnModifier(final String name, final String description, final int voteLockDurationChangeSeconds, final int blocksPerSpawn) {
        super(name, description, voteLockDurationChangeSeconds);
        this.blocksPerSpawn = blocksPerSpawn;
    }
    
    @Nullable
    @Override
    public VaultPieceProcessor getPostProcessor(final ArchitectObjective objective, final VaultRaid vault) {
        return new VaultSpawnerSpawningPostProcessor(this.blocksPerSpawn);
    }
}
