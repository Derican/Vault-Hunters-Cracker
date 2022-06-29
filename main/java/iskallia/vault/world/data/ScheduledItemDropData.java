
package iskallia.vault.world.data;

import java.util.function.Supplier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import java.util.Iterator;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.event.TickEvent;
import java.util.ArrayList;
import net.minecraft.entity.player.PlayerEntity;
import java.util.HashMap;
import net.minecraft.item.ItemStack;
import java.util.List;
import java.util.UUID;
import java.util.Map;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.storage.WorldSavedData;

@Mod.EventBusSubscriber
public class ScheduledItemDropData extends WorldSavedData {
    protected static final String DATA_NAME = "the_vault_ScheduledItemDrops";
    private final Map<UUID, List<ItemStack>> scheduledItems;

    public ScheduledItemDropData() {
        super("the_vault_ScheduledItemDrops");
        this.scheduledItems = new HashMap<UUID, List<ItemStack>>();
    }

    public void addDrop(final PlayerEntity player, final ItemStack toDrop) {
        this.addDrop(player.getUUID(), toDrop);
    }

    public void addDrop(final UUID playerUUID, final ItemStack toDrop) {
        this.scheduledItems.computeIfAbsent(playerUUID, key -> new ArrayList()).add(toDrop.copy());
        this.setDirty();
    }

    @SubscribeEvent
    public static void onPlayerTick(final TickEvent.PlayerTickEvent event) {
        if (event.side == LogicalSide.SERVER && event.phase == TickEvent.Phase.END) {
            final ServerPlayerEntity player = (ServerPlayerEntity) event.player;
            final ScheduledItemDropData data = get(player.getLevel());
            if (data.scheduledItems.isEmpty()) {
                return;
            }
            if (data.scheduledItems.containsKey(player.getUUID())) {
                final List<ItemStack> drops = data.scheduledItems.get(player.getUUID());
                while (!drops.isEmpty() && player.inventory.getFreeSlot() != -1) {
                    final ItemStack drop = drops.get(0);
                    if (!player.inventory.add(drop)) {
                        break;
                    }
                    drops.remove(0);
                    data.setDirty();
                }
                if (drops.isEmpty()) {
                    data.scheduledItems.remove(player.getUUID());
                    data.setDirty();
                }
            }
        }
    }

    public void load(final CompoundNBT tag) {
        this.scheduledItems.clear();
        final CompoundNBT savTag = tag.getCompound("drops");
        for (final String key : savTag.getAllKeys()) {
            UUID playerUUID;
            try {
                playerUUID = UUID.fromString(key);
            } catch (final IllegalArgumentException exc) {
                continue;
            }
            final List<ItemStack> drops = new ArrayList<ItemStack>();
            final ListNBT dropsList = savTag.getList(key, 10);
            for (int i = 0; i < dropsList.size(); ++i) {
                drops.add(ItemStack.of(dropsList.getCompound(i)));
            }
            this.scheduledItems.put(playerUUID, drops);
        }
    }

    public CompoundNBT save(final CompoundNBT tag) {
        final CompoundNBT savTag = new CompoundNBT();
        this.scheduledItems.forEach((uuid, drops) -> {
            final ListNBT dropsList = new ListNBT();
            drops.forEach(stack -> dropsList.add((Object) stack.serializeNBT()));
            savTag.put(uuid.toString(), (INBT) dropsList);
            return;
        });
        tag.put("drops", (INBT) savTag);
        return tag;
    }

    public static ScheduledItemDropData get(final ServerWorld world) {
        return get(world.getServer());
    }

    public static ScheduledItemDropData get(final MinecraftServer srv) {
        return (ScheduledItemDropData) srv.overworld().getDataStorage()
                .computeIfAbsent((Supplier) ScheduledItemDropData::new, "the_vault_ScheduledItemDrops");
    }
}
