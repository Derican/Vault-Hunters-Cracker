// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.world.vault.influence;

import net.minecraft.nbt.INBT;
import java.util.Random;
import net.minecraft.world.server.ServerWorld;
import iskallia.vault.world.vault.player.VaultPlayer;
import iskallia.vault.world.vault.VaultRaid;
import net.minecraft.util.ResourceLocation;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public class VaultInfluence implements INBTSerializable<CompoundNBT>
{
    private final ResourceLocation key;
    
    public VaultInfluence(final ResourceLocation key) {
        this.key = key;
    }
    
    public final ResourceLocation getKey() {
        return this.key;
    }
    
    public void apply(final VaultRaid vault, final VaultPlayer player, final ServerWorld world, final Random random) {
    }
    
    public void remove(final VaultRaid vault, final VaultPlayer player, final ServerWorld world, final Random random) {
    }
    
    public void tick(final VaultRaid vault, final VaultPlayer player, final ServerWorld world) {
    }
    
    public CompoundNBT serializeNBT() {
        return new CompoundNBT();
    }
    
    public void deserializeNBT(final CompoundNBT tag) {
    }
}
