// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.config;

import java.util.Collection;
import javax.annotation.Nullable;
import javax.annotation.Nonnull;
import java.util.Iterator;
import iskallia.vault.world.vault.VaultRaid;
import iskallia.vault.util.data.RandomListAccess;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.block.Block;
import iskallia.vault.world.vault.modifier.LootableModifier;
import iskallia.vault.util.PlayerFilter;
import iskallia.vault.world.data.VaultRaidData;
import net.minecraft.block.BlockState;
import java.util.UUID;
import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import java.util.LinkedHashMap;
import java.util.Map;
import iskallia.vault.util.data.WeightedList;
import com.google.gson.annotations.Expose;

public class VaultLootablesConfig extends Config
{
    @Expose
    public Lootable ORE;
    @Expose
    public Lootable DOOR;
    @Expose
    public Lootable RICHITY;
    @Expose
    public Lootable RESOURCE;
    @Expose
    public Lootable MISC;
    @Expose
    public Lootable VAULT_CHEST;
    @Expose
    public Lootable VAULT_TREASURE;
    
    public VaultLootablesConfig() {
        this.ORE = Lootable.defaultConfig();
        this.DOOR = Lootable.defaultConfig();
        this.RICHITY = Lootable.defaultConfig();
        this.RESOURCE = Lootable.defaultConfig();
        this.MISC = Lootable.defaultConfig();
        this.VAULT_CHEST = Lootable.defaultConfig();
        this.VAULT_TREASURE = Lootable.defaultConfig();
    }
    
    @Override
    public String getName() {
        return "vault_lootables";
    }
    
    @Override
    protected void reset() {
    }
    
    public static class Lootable
    {
        @Expose
        private WeightedList<String> DEFAULT;
        @Expose
        private Map<String, WeightedList<String>> OVERRIDES;
        
        public Lootable() {
            this.DEFAULT = new WeightedList<String>();
            this.OVERRIDES = new LinkedHashMap<String, WeightedList<String>>();
        }
        
        @Nonnull
        public BlockState get(final ServerWorld world, final BlockPos pos, final Random random, final String poolName, final UUID playerUUID) {
            RandomListAccess<String> pool = this.getPool(playerUUID);
            final VaultRaid vault = VaultRaidData.get(world).getAt(world, pos);
            if (vault != null) {
                for (final LootableModifier modifier : vault.getActiveModifiersFor(PlayerFilter.of(playerUUID), LootableModifier.class)) {
                    pool = modifier.adjustLootWeighting(poolName, pool);
                }
            }
            return Registry.BLOCK.getOptional(new ResourceLocation((String)pool.getRandom(random))).orElse(Blocks.AIR).defaultBlockState();
        }
        
        public WeightedList<String> getPool(@Nullable final UUID playerUUID) {
            final WeightedList<String> pool = new WeightedList<String>();
            if (playerUUID != null) {
                pool.addAll((Collection<?>)this.OVERRIDES.getOrDefault(playerUUID.toString(), new WeightedList<String>()));
            }
            this.DEFAULT.forEach(entry -> {
                if (!pool.containsValue(entry.value)) {
                    pool.add(entry);
                }
                return;
            });
            return pool;
        }
        
        public static Lootable defaultConfig() {
            final Lootable lootable = new Lootable();
            lootable.DEFAULT.add(Blocks.AIR.getRegistryName().toString(), 1);
            lootable.OVERRIDES.computeIfAbsent("cc821d6c-a2f4-4307-955d-8b30c2fc505d", key -> new WeightedList()).add(Blocks.STONE.getRegistryName().toString(), 1);
            return lootable;
        }
    }
}
