
package iskallia.vault.world.data;

import java.util.function.Supplier;
import net.minecraft.nbt.StringNBT;
import java.util.Iterator;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.CompoundNBT;
import java.util.ArrayList;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import java.util.List;
import java.util.HashMap;
import iskallia.vault.altar.AltarInfusionRecipe;
import java.util.UUID;
import java.util.Map;
import net.minecraft.world.storage.WorldSavedData;

public class PlayerVaultAltarData extends WorldSavedData {
    protected static final String DATA_NAME = "the_vault_PlayerAltarRecipes";
    private Map<UUID, AltarInfusionRecipe> playerMap;
    private HashMap<UUID, List<BlockPos>> playerAltars;

    public PlayerVaultAltarData() {
        super("the_vault_PlayerAltarRecipes");
        this.playerMap = new HashMap<UUID, AltarInfusionRecipe>();
        this.playerAltars = new HashMap<UUID, List<BlockPos>>();
    }

    public PlayerVaultAltarData(final String name) {
        super(name);
        this.playerMap = new HashMap<UUID, AltarInfusionRecipe>();
        this.playerAltars = new HashMap<UUID, List<BlockPos>>();
    }

    public AltarInfusionRecipe getRecipe(final PlayerEntity player) {
        return this.getRecipe(player.getUUID());
    }

    public AltarInfusionRecipe getRecipe(final UUID uuid) {
        return this.playerMap.get(uuid);
    }

    public AltarInfusionRecipe getRecipe(final ServerWorld world, final BlockPos pos, final ServerPlayerEntity player) {
        final AltarInfusionRecipe recipe = this.playerMap.computeIfAbsent(player.getUUID(),
                k -> new AltarInfusionRecipe(world, pos, player));
        this.setDirty();
        return recipe;
    }

    public boolean hasRecipe(final UUID uuid) {
        return this.playerMap.containsKey(uuid);
    }

    public PlayerVaultAltarData addRecipe(final UUID uuid, final AltarInfusionRecipe recipe) {
        this.playerMap.put(uuid, recipe);
        this.setDirty();
        return this;
    }

    public PlayerVaultAltarData removeRecipe(final UUID uuid) {
        this.playerMap.remove(uuid);
        this.setDirty();
        return this;
    }

    public List<BlockPos> getAltars(final UUID uuid) {
        if (uuid == null) {
            return new ArrayList<BlockPos>();
        }
        this.playerAltars.computeIfAbsent(uuid, k -> new ArrayList());
        this.setDirty();
        return this.playerAltars.get(uuid);
    }

    public PlayerVaultAltarData addAltar(final UUID uuid, final BlockPos altarPos) {
        this.getAltars(uuid).add(altarPos);
        this.setDirty();
        return this;
    }

    public PlayerVaultAltarData removeAltar(final UUID uuid, final BlockPos altarPos) {
        this.getAltars(uuid).remove(altarPos);
        this.setDirty();
        return this;
    }

    public void load(final CompoundNBT nbt) {
        final ListNBT playerList = nbt.getList("PlayerEntries", 8);
        final ListNBT recipeList = nbt.getList("AltarRecipeEntries", 10);
        final ListNBT playerBlockPosList = nbt.getList("PlayerBlockPosEntries", 8);
        final ListNBT blockPosList = nbt.getList("BlockPosEntries", 9);
        if (playerList.size() != recipeList.size() || playerBlockPosList.size() != blockPosList.size()) {
            throw new IllegalStateException("Map doesn't have the same amount of keys as values");
        }
        for (int i = 0; i < playerList.size(); ++i) {
            final UUID playerUUID = UUID.fromString(playerList.getString(i));
            this.playerMap.put(playerUUID, AltarInfusionRecipe.deserialize(recipeList.getCompound(i)));
        }
        for (int i = 0; i < playerBlockPosList.size(); ++i) {
            final UUID playerUUID = UUID.fromString(playerBlockPosList.getString(i));
            final List<BlockPos> positions = new ArrayList<BlockPos>();
            for (final INBT compound : blockPosList.getList(i)) {
                final CompoundNBT posTag = (CompoundNBT) compound;
                final BlockPos pos = NBTUtil.readBlockPos(posTag);
                positions.add(pos);
            }
            this.playerAltars.put(playerUUID, positions);
        }
    }

    public CompoundNBT save(final CompoundNBT nbt) {
        final ListNBT playerList = new ListNBT();
        final ListNBT recipeList = new ListNBT();
        final ListNBT playerBlockPosList = new ListNBT();
        final ListNBT blockPosList = new ListNBT();
        this.playerMap.forEach((uuid, recipe) -> {
            playerList.add((Object) StringNBT.valueOf(uuid.toString()));
            recipeList.add((Object) recipe.serialize());
            return;
        });
        this.playerAltars.forEach((uuid, altarPositions) -> {
            playerBlockPosList.add((Object) StringNBT.valueOf(uuid.toString()));
            final ListNBT positions = new ListNBT();
            altarPositions.forEach(pos -> positions.add((Object) NBTUtil.writeBlockPos(pos)));
            blockPosList.add((Object) positions);
            return;
        });
        nbt.put("PlayerEntries", (INBT) playerList);
        nbt.put("AltarRecipeEntries", (INBT) recipeList);
        nbt.put("PlayerBlockPosEntries", (INBT) playerBlockPosList);
        nbt.put("BlockPosEntries", (INBT) blockPosList);
        return nbt;
    }

    public static PlayerVaultAltarData get(final ServerWorld world) {
        return (PlayerVaultAltarData) world.getServer().overworld().getDataStorage()
                .computeIfAbsent((Supplier) PlayerVaultAltarData::new, "the_vault_PlayerAltarRecipes");
    }
}
