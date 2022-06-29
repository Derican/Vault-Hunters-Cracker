// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.item.catalyst;

import javax.annotation.Nullable;
import iskallia.vault.config.VaultCrystalCatalystConfig;
import iskallia.vault.init.ModConfigs;
import java.util.Random;
import com.google.gson.annotations.Expose;

public class SingleModifierOutcome
{
    @Expose
    private final ModifierRollType type;
    @Expose
    private final String pool;
    
    public SingleModifierOutcome(final ModifierRollType type, final String pool) {
        this.type = type;
        this.pool = pool;
    }
    
    @Nullable
    public ModifierRollResult resolve(final Random rand) {
        final VaultCrystalCatalystConfig.TaggedPool pool = ModConfigs.VAULT_CRYSTAL_CATALYST.getPool(this.pool);
        if (pool == null) {
            return null;
        }
        if (this.type == ModifierRollType.ADD_SPECIFIC_MODIFIER) {
            return ModifierRollResult.ofModifier(pool.getModifier(rand));
        }
        return ModifierRollResult.ofPool(this.pool);
    }
    
    public ModifierRollType getType() {
        return this.type;
    }
    
    public String getPool() {
        return this.pool;
    }
}
