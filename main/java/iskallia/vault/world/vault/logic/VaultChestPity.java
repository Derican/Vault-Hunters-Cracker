// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.world.vault.logic;

import net.minecraft.nbt.INBT;
import java.util.Iterator;
import iskallia.vault.init.ModConfigs;
import java.util.Random;
import net.minecraft.entity.player.PlayerEntity;
import iskallia.vault.util.data.WeightedDoubleList;
import net.minecraft.world.server.ServerWorld;
import iskallia.vault.world.vault.player.VaultPlayer;
import iskallia.vault.world.vault.VaultRaid;
import java.util.HashMap;
import java.util.UUID;
import java.util.Map;
import iskallia.vault.world.vault.logic.task.IVaultTask;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public class VaultChestPity implements INBTSerializable<CompoundNBT>, IVaultTask
{
    private final Map<UUID, Integer> ticksElapsed;
    
    public VaultChestPity() {
        this.ticksElapsed = new HashMap<UUID, Integer>();
    }
    
    public void execute(final VaultRaid vault, final VaultPlayer player, final ServerWorld world) {
        final int ticks = this.ticksElapsed.getOrDefault(player.getPlayerId(), 0);
        this.ticksElapsed.put(player.getPlayerId(), ticks + 1);
    }
    
    public String getRandomChestRarity(final WeightedDoubleList<String> chestWeights, final PlayerEntity player, final Random rand) {
        final int ticks = this.getTicksElapsed(player);
        final String rarity = ModConfigs.VAULT_CHEST_META.getPityAdjustedRarity(chestWeights, ticks).getRandom(rand);
        this.resetTicks(player);
        return rarity;
    }
    
    public int getTicksElapsed(final PlayerEntity player) {
        return this.ticksElapsed.getOrDefault(player.getUUID(), 0);
    }
    
    public void resetTicks(final PlayerEntity player) {
        this.ticksElapsed.put(player.getUUID(), 0);
    }
    
    public CompoundNBT serializeNBT() {
        final CompoundNBT nbt = new CompoundNBT();
        this.ticksElapsed.forEach((uuid, ticks) -> nbt.putInt(uuid.toString(), (int)ticks));
        return nbt;
    }
    
    public void deserializeNBT(final CompoundNBT nbt) {
        this.ticksElapsed.clear();
        for (final String key : nbt.getAllKeys()) {
            UUID playerUUID;
            try {
                playerUUID = UUID.fromString(key);
            }
            catch (final IllegalArgumentException exc) {
                continue;
            }
            this.ticksElapsed.put(playerUUID, nbt.getInt(key));
        }
    }
}
