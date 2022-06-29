// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.client.vault.goal;

import javax.annotation.Nullable;
import iskallia.vault.client.gui.overlay.goal.BossBarOverlay;
import net.minecraft.nbt.CompoundNBT;
import iskallia.vault.network.message.VaultGoalMessage;

public class CakeHuntData extends VaultGoalData
{
    private int totalCakes;
    private int foundCakes;
    
    @Override
    public void receive(final VaultGoalMessage pkt) {
        final CompoundNBT tag = pkt.payload;
        this.totalCakes = tag.getInt("total");
        this.foundCakes = tag.getInt("found");
    }
    
    @Nullable
    @Override
    public BossBarOverlay getBossBarOverlay() {
        return null;
    }
    
    public float getCompletePercent() {
        return this.foundCakes / (float)this.totalCakes;
    }
    
    public int getTotalCakes() {
        return this.totalCakes;
    }
    
    public int getFoundCakes() {
        return this.foundCakes;
    }
}
