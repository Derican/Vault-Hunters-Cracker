
package iskallia.vault.world.data;

import java.util.function.Supplier;
import net.minecraft.nbt.StringNBT;
import iskallia.vault.util.nbt.NBTHelper;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.IntFunction;
import java.util.stream.IntStream;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.item.Item;
import net.minecraft.inventory.IInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.server.ServerWorld;
import iskallia.vault.item.RelicPartItem;
import net.minecraft.item.ItemStack;
import iskallia.vault.init.ModBlocks;
import net.minecraftforge.event.entity.player.PlayerEvent;
import iskallia.vault.util.RelicSet;
import iskallia.vault.init.ModConfigs;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;
import java.util.Map;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.storage.WorldSavedData;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class VaultSetsData extends WorldSavedData {
    protected static final String DATA_NAME = "the_vault_VaultSets";
    private Map<UUID, Set<String>> playerData;

    public VaultSetsData() {
        super("the_vault_VaultSets");
        this.playerData = new HashMap<UUID, Set<String>>();
    }

    public VaultSetsData(final String name) {
        super(name);
        this.playerData = new HashMap<UUID, Set<String>>();
    }

    public Set<String> getCraftedSets(final UUID playerId) {
        return this.playerData.computeIfAbsent(playerId, uuid -> new HashSet());
    }

    public int getExtraTime(final UUID playerId) {
        return this.getCraftedSets(playerId).size() * ModConfigs.VAULT_RELICS.getExtraTickPerSet();
    }

    public boolean markSetAsCrafted(final UUID playerId, final RelicSet relicSet) {
        final Set<String> craftedSets = this.getCraftedSets(playerId);
        this.setDirty();
        return craftedSets.add(relicSet.getId().toString());
    }

    @SubscribeEvent
    public static void onCrafted(final PlayerEvent.ItemCraftedEvent event) {
        final PlayerEntity player = event.getPlayer();
        if (player.level.isClientSide) {
            return;
        }
        final IInventory craftingMatrix = event.getInventory();
        final ItemStack craftedItemstack = event.getCrafting();
        if (craftedItemstack.getItem() != ModBlocks.RELIC_STATUE_BLOCK_ITEM) {
            return;
        }
        for (int i = 0; i < craftingMatrix.getContainerSize(); ++i) {
            final ItemStack stackInSlot = craftingMatrix.getItem(i);
            if (stackInSlot != ItemStack.EMPTY) {
                final Item item = stackInSlot.getItem();
                if (item instanceof RelicPartItem) {
                    final RelicPartItem relicPart = (RelicPartItem) item;
                    final VaultSetsData vaultSetsData = get((ServerWorld) player.level);
                    vaultSetsData.markSetAsCrafted(player.getUUID(), relicPart.getRelicSet());
                    break;
                }
            }
        }
    }

    public void load(final CompoundNBT nbt) {
        this.playerData = NBTHelper.readMap(nbt, "Sets", ListNBT.class,
                list -> IntStream.range(0, list.size()).mapToObj((IntFunction<?>) list::getString)
                        .collect((Collector<? super Object, ?, Set<? super Object>>) Collectors.toSet()));
    }

    public CompoundNBT save(final CompoundNBT compound) {
        NBTHelper.writeMap(compound, "Sets", this.playerData, ListNBT.class, strings -> {
            final ListNBT list = new ListNBT();
            strings.forEach(s -> list.add((Object) StringNBT.valueOf(s)));
            return list;
        });
        return compound;
    }

    public static VaultSetsData get(final ServerWorld world) {
        return (VaultSetsData) world.getServer().overworld().getDataStorage()
                .computeIfAbsent((Supplier) VaultSetsData::new, "the_vault_VaultSets");
    }
}
