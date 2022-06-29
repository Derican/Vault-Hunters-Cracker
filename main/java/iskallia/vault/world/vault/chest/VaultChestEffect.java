// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.world.vault.chest;

import net.minecraft.world.server.ServerWorld;
import iskallia.vault.world.vault.player.VaultPlayer;
import iskallia.vault.world.vault.VaultRaid;
import com.google.gson.annotations.Expose;

public abstract class VaultChestEffect
{
    @Expose
    private final String name;
    
    public VaultChestEffect(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public boolean isTrapEffect() {
        return true;
    }
    
    public abstract void apply(final VaultRaid p0, final VaultPlayer p1, final ServerWorld p2);
}
