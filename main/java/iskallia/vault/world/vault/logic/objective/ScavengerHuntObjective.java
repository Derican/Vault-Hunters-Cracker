
package iskallia.vault.world.vault.logic.objective;

import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.IContainerListener;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.util.math.MathHelper;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.entity.item.ItemEntity;
import iskallia.vault.block.VaultCrateBlock;
import net.minecraft.util.text.IFormattableTextComponent;
import iskallia.vault.util.MiscUtils;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.Style;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.LootContext;
import iskallia.vault.world.vault.player.VaultRunner;
import java.util.Optional;
import iskallia.vault.world.vault.player.VaultPlayer;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SSetSlotPacket;
import iskallia.vault.container.ScavengerChestContainer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fml.network.NetworkDirection;
import iskallia.vault.init.ModNetwork;
import iskallia.vault.network.message.VaultGoalMessage;
import iskallia.vault.util.PlayerFilter;
import iskallia.vault.config.LootTablesConfig;
import net.minecraft.loot.LootTable;
import java.util.function.Function;
import javax.annotation.Nonnull;
import iskallia.vault.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import iskallia.vault.world.vault.VaultRaid;
import javax.annotation.Nullable;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import iskallia.vault.item.BasicScavengerItem;
import java.util.UUID;
import java.util.Iterator;
import net.minecraft.item.Item;
import iskallia.vault.config.ScavengerHuntConfig;
import java.util.function.Predicate;
import java.util.Collections;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.inventory.IInventory;
import java.util.ArrayList;
import iskallia.vault.world.vault.logic.task.VaultTask;
import iskallia.vault.init.ModConfigs;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import java.util.List;

public class ScavengerHuntObjective extends VaultObjective {
    public static final int INVENTORY_SIZE = 45;
    private final ChestWatcher chestWatcher;
    private final Inventory inventoryMirror;
    private final List<ItemSubmission> submissions;
    private int requiredSubmissions;
    private NonNullList<ItemStack> chestInventory;

    public ScavengerHuntObjective(final ResourceLocation id) {
        this(id, ModConfigs.SCAVENGER_HUNT.getTotalRequiredItems());
    }

    private ScavengerHuntObjective(final ResourceLocation id, final int requiredSubmissions) {
        super(id, VaultTask.EMPTY, VaultTask.EMPTY);
        this.chestWatcher = new ChestWatcher();
        this.inventoryMirror = new Inventory();
        this.submissions = new ArrayList<ItemSubmission>();
        this.chestInventory = (NonNullList<ItemStack>) NonNullList.withSize(45, (Object) ItemStack.EMPTY);
        this.requiredSubmissions = requiredSubmissions;
    }

    public IInventory getScavengerChestInventory() {
        return (IInventory) this.inventoryMirror;
    }

    public ChestWatcher getChestWatcher() {
        return this.chestWatcher;
    }

    private Stream<ItemSubmission> getActiveSubmissionsFilter() {
        return this.getAllSubmissions().stream().filter(submission -> !submission.isFinished());
    }

    public List<ItemSubmission> getActiveSubmissions() {
        return this.getActiveSubmissionsFilter()
                .collect((Collector<? super ItemSubmission, ?, List<ItemSubmission>>) Collectors.toList());
    }

    public List<ItemSubmission> getAllSubmissions() {
        return Collections.unmodifiableList((List<? extends ItemSubmission>) this.submissions);
    }

    public Predicate<ScavengerHuntConfig.ItemEntry> getGenerationDropFilter() {
        final List<ItemSubmission> submissions = this.getActiveSubmissions();
        return entry -> {
            final Item generatedItem = entry.getItem();
            submissions.iterator();
            final Iterator iterator;
            while (iterator.hasNext()) {
                final ItemSubmission submission = iterator.next();
                if (generatedItem.equals(submission.getRequiredItem())) {
                    return true;
                }
            }
            return false;
        };
    }

    public boolean trySubmitItem(final UUID vaultIdentifier, final ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }
        if (!vaultIdentifier.equals(BasicScavengerItem.getVaultIdentifier(stack))) {
            return false;
        }
        final Item providedItem = stack.getItem();
        final boolean addedItem = this.getActiveSubmissionsFilter()
                .filter(submission -> providedItem.equals(submission.requiredItem)).findFirst().map(submission -> {
                    final int add = Math.min(stack.getCount(),
                            submission.requiredAmount - submission.currentAmount);
                    submission.currentAmount += add;
                    stack.shrink(add);
                    return true;
                }).orElse(false);
        if (this.getAllSubmissions().stream().filter(ItemSubmission::isFinished).count() >= this.requiredSubmissions) {
            this.setCompleted();
        }
        return addedItem;
    }

    @Override
    public void setObjectiveTargetCount(final int amount) {
        this.requiredSubmissions = amount;
    }

    @Nullable
    @Override
    public ITextComponent getObjectiveTargetDescription(final int amount) {
        return (ITextComponent) new StringTextComponent("Total required Item Types: ").append(
                (ITextComponent) new StringTextComponent(String.valueOf(amount)).withStyle(TextFormatting.GREEN));
    }

    @Nonnull
    @Override
    public BlockState getObjectiveRelevantBlock(final VaultRaid vault, final ServerWorld world, final BlockPos pos) {
        return ModBlocks.SCAVENGER_CHEST.defaultBlockState();
    }

    @Nullable
    @Override
    public LootTable getRewardLootTable(final VaultRaid vault,
            final Function<ResourceLocation, LootTable> tblResolver) {
        final int level = vault.getProperties().getBase(VaultRaid.LEVEL).orElse(0);
        final LootTablesConfig.Level config = ModConfigs.LOOT_TABLES.getForLevel(level);
        return (config != null) ? tblResolver.apply(config.getScavengerCrate()) : LootTable.EMPTY;
    }

    @Override
    public ITextComponent getObjectiveDisplayName() {
        return (ITextComponent) new StringTextComponent("Scavenger Hunt").withStyle(TextFormatting.GREEN);
    }

    @Override
    public ITextComponent getVaultName() {
        return (ITextComponent) new StringTextComponent("Scavenger Vault");
    }

    @Override
    public void tick(final VaultRaid vault, final PlayerFilter filter, final ServerWorld world) {
        super.tick(vault, filter, world);
        final MinecraftServer srv = world.getServer();
        vault.getPlayers().stream().filter(vPlayer -> filter.test(vPlayer.getPlayerId()))
                .forEach(vPlayer -> vPlayer.runIfPresent(srv, playerEntity -> {
                    final VaultGoalMessage pkt = VaultGoalMessage.scavengerHunt(this.getActiveSubmissions());
                    ModNetwork.CHANNEL.sendTo((Object) pkt, playerEntity.connection.connection,
                            NetworkDirection.PLAY_TO_CLIENT);
                }));
        if (this.isCompleted()) {
            return;
        }
        final long activeSubmissions = this.getActiveSubmissionsFilter().count();
        if (world.getGameTime() % 20L == 0L) {
            final boolean addedAnyItem = vault.getProperties().getBase(VaultRaid.IDENTIFIER).map(identifier -> {
                boolean addedItem = false;
                final NonNullList<ItemStack> inventory = this.chestInventory;
                for (int slot = 0; slot < inventory.size(); ++slot) {
                    final ItemStack stack = (ItemStack) inventory.get(slot);
                    if (!stack.isEmpty()) {
                        if (this.trySubmitItem(identifier, stack)) {
                            this.chestInventory.set(slot, (Object) stack);
                            this.updateOpenContainers(srv, vault, slot, stack);
                            addedItem = true;
                        }
                    }
                }
                return Boolean.valueOf(addedItem);
            }).orElse(false);
            if (activeSubmissions > this.getActiveSubmissionsFilter().count()) {
                vault.getPlayers()
                        .forEach(vPlayer -> vPlayer.runIfPresent(srv,
                                sPlayer -> world.playSound((PlayerEntity) null, sPlayer.blockPosition(),
                                        SoundEvents.PLAYER_LEVELUP, SoundCategory.BLOCKS, 1.0f, 1.0f)));
            } else if (addedAnyItem) {
                vault.getPlayers()
                        .forEach(vPlayer -> vPlayer.runIfPresent(srv,
                                sPlayer -> world.playSound((PlayerEntity) null, sPlayer.blockPosition(),
                                        SoundEvents.NOTE_BLOCK_BELL, SoundCategory.PLAYERS, 1.1f, 1.4f)));
            }
        }
        if (this.isCompleted()) {
            this.spawnRewards(world, vault);
        }
        if (this.getAllSubmissions().size() < this.requiredSubmissions) {
            final ItemSubmission newEntry = this.getNewEntry(vault);
            if (newEntry != null) {
                this.submissions.add(newEntry);
            }
        }
    }

    private void updateOpenContainers(final MinecraftServer srv, final VaultRaid vault, final int slot,
            final ItemStack stack) {
        vault.getPlayers().forEach(vPlayer -> vPlayer.runIfPresent(srv, sPlayer -> {
            if (sPlayer.containerMenu instanceof ScavengerChestContainer) {
                sPlayer.containerMenu.setItem(slot, stack);
                sPlayer.connection
                        .send((IPacket) new SSetSlotPacket(sPlayer.containerMenu.containerId, slot, stack));
            }
        }));
    }

    @Override
    public void complete(final VaultRaid vault, final VaultPlayer player, final ServerWorld world) {
        super.complete(vault, player, world);
        player.sendIfPresent(world.getServer(), VaultGoalMessage.clear());
    }

    @Override
    public void complete(final VaultRaid vault, final ServerWorld world) {
        super.complete(vault, world);
        vault.getPlayers().forEach(player -> player.sendIfPresent(world.getServer(), VaultGoalMessage.clear()));
    }

    public void spawnRewards(final ServerWorld world, final VaultRaid vault) {
        final VaultPlayer rewardPlayer = vault.getProperties().getBase(VaultRaid.HOST)
                .flatMap((Function<? super UUID, ? extends Optional<? extends VaultPlayer>>) vault::getPlayer)
                .filter(vPlayer -> vPlayer instanceof VaultRunner).orElseGet(() -> vault.getPlayers().stream()
                        .filter(vPlayer -> vPlayer instanceof VaultRunner).findAny().orElse(null));
        if (rewardPlayer == null) {
            return;
        }
        rewardPlayer.runIfPresent(world.getServer(), sPlayer -> {
            final BlockPos pos = sPlayer.blockPosition();
            final LootContext.Builder builder = new LootContext.Builder(world).withRandom(world.random)
                    .withParameter(LootParameters.THIS_ENTITY, (Object) sPlayer)
                    .withParameter(LootParameters.ORIGIN, (Object) Vector3d.atCenterOf((Vector3i) pos))
                    .withLuck(sPlayer.getLuck());
            final LootContext ctx = builder.create(LootParameterSets.CHEST);
            this.dropRewardCrate(world, vault, pos, ctx);
            for (int i = 1; i < vault.getPlayers().size(); ++i) {
                if (ScavengerHuntObjective.rand.nextFloat() < 0.5f) {
                    this.dropRewardCrate(world, vault, pos, ctx);
                }
            }
            final IFormattableTextComponent msgContainer = new StringTextComponent("")
                    .withStyle(TextFormatting.WHITE);
            final IFormattableTextComponent playerName = sPlayer.getDisplayName().copy();
            playerName.setStyle(Style.EMPTY.withColor(Color.fromRgb(9974168)));
            MiscUtils.broadcast((ITextComponent) msgContainer.append((ITextComponent) playerName)
                    .append(" finished a Scavenger Hunt!"));
        });
    }

    private void dropRewardCrate(final ServerWorld world, final VaultRaid vault, final BlockPos pos,
            final LootContext context) {
        final NonNullList<ItemStack> stacks = this.createLoot(world, vault, context);
        final ItemStack crate = VaultCrateBlock.getCrateWithLoot(ModBlocks.VAULT_CRATE_SCAVENGER, stacks);
        final ItemEntity item = new ItemEntity((World) world, (double) pos.getX(),
                (double) pos.getY(), (double) pos.getZ(), crate);
        item.setDefaultPickUpDelay();
        world.addFreshEntity((Entity) item);
        this.crates.add(new Crate((List<ItemStack>) stacks));
    }

    @Nullable
    private ItemSubmission getNewEntry(final VaultRaid vault) {
        final List<Item> currentItems = this.submissions.stream().map(submission -> submission.requiredItem)
                .collect((Collector<? super Object, ?, List<Item>>) Collectors.toList());
        final int players = vault.getPlayers().size();
        final int level = vault.getProperties().getBase(VaultRaid.LEVEL).orElse(0);
        float multiplier = 1.0f + (players - 1) * 0.5f;
        final ScavengerHuntConfig.ItemEntry newEntry = ModConfigs.SCAVENGER_HUNT
                .getRandomRequiredItem(currentItems::contains);
        if (newEntry == null) {
            return null;
        }
        final ScavengerHuntConfig.SourceType sourceType = ModConfigs.SCAVENGER_HUNT
                .getRequirementSource(newEntry.createItemStack());
        switch (sourceType) {
            case MOB: {
                multiplier *= 1.0f + level / 100.0f;
                break;
            }
            case CHEST: {
                multiplier *= 1.0f + level / 100.0f / 1.5f;
                break;
            }
        }
        return fromConfigEntry(newEntry, multiplier);
    }

    @Override
    public CompoundNBT serializeNBT() {
        final CompoundNBT tag = super.serializeNBT();
        final ListNBT list = new ListNBT();
        for (final ItemSubmission submission : this.submissions) {
            list.add((Object) submission.serialize());
        }
        tag.put("submissions", (INBT) list);
        tag.putInt("requiredSubmissions", this.requiredSubmissions);
        final ListNBT inventoryList = new ListNBT();
        for (int slot = 0; slot < this.chestInventory.size(); ++slot) {
            final ItemStack stack = (ItemStack) this.chestInventory.get(slot);
            if (!stack.isEmpty()) {
                final CompoundNBT itemTag = new CompoundNBT();
                itemTag.putInt("slot", slot);
                itemTag.put("item", (INBT) stack.serializeNBT());
                inventoryList.add((Object) itemTag);
            }
        }
        tag.put("inventory", (INBT) inventoryList);
        return tag;
    }

    @Override
    public void deserializeNBT(final CompoundNBT tag) {
        super.deserializeNBT(tag);
        this.submissions.clear();
        final ListNBT list = tag.getList("submissions", 10);
        for (int index = 0; index < list.size(); ++index) {
            this.submissions.add(ItemSubmission.deserialize(list.getCompound(index)));
        }
        this.requiredSubmissions = tag.getInt("requiredSubmissions");
        this.chestInventory = (NonNullList<ItemStack>) NonNullList.withSize(45, (Object) ItemStack.EMPTY);
        final ListNBT inventoryList = tag.getList("inventory", 10);
        for (int i = 0; i < inventoryList.size(); ++i) {
            final CompoundNBT itemTag = inventoryList.getCompound(i);
            final int slot = itemTag.getInt("slot");
            final ItemStack stack = ItemStack.of(itemTag.getCompound("item"));
            this.chestInventory.set(slot, (Object) stack);
        }
    }

    public static class ItemSubmission {
        private final Item requiredItem;
        private final int requiredAmount;
        private int currentAmount;

        public ItemSubmission(final Item requiredItem, final int requiredAmount) {
            this.currentAmount = 0;
            this.requiredItem = requiredItem;
            this.requiredAmount = requiredAmount;
        }

        private static ItemSubmission fromConfigEntry(final ScavengerHuntConfig.ItemEntry entry,
                final float multiplyAmount) {
            return new ItemSubmission(entry.getItem(),
                    MathHelper.ceil(entry.getRandomAmount() * multiplyAmount));
        }

        public boolean isFinished() {
            return this.currentAmount >= this.requiredAmount;
        }

        public Item getRequiredItem() {
            return this.requiredItem;
        }

        public int getRequiredAmount() {
            return this.requiredAmount;
        }

        public int getCurrentAmount() {
            return this.currentAmount;
        }

        public CompoundNBT serialize() {
            final CompoundNBT tag = new CompoundNBT();
            tag.putString("item", this.requiredItem.getRegistryName().toString());
            tag.putInt("required", this.requiredAmount);
            tag.putInt("current", this.currentAmount);
            return tag;
        }

        public static ItemSubmission deserialize(final CompoundNBT tag) {
            final Item requiredItem = (Item) ForgeRegistries.ITEMS
                    .getValue(new ResourceLocation(tag.getString("item")));
            final int requiredAmount = tag.getInt("required");
            final int currentAmount = tag.getInt("current");
            final ItemSubmission submitted = new ItemSubmission(requiredItem, requiredAmount);
            submitted.currentAmount = currentAmount;
            return submitted;
        }
    }

    public class ChestWatcher implements IContainerListener {
        public void refreshContainer(final Container container, final NonNullList<ItemStack> items) {
        }

        public void slotChanged(final Container container, final int slotId, final ItemStack stack) {
            if (slotId >= 0 && slotId < 45) {
                ScavengerHuntObjective.this.chestInventory.set(slotId, (Object) stack);
            }
        }

        public void setContainerData(final Container containerIn, final int varToUpdate, final int newValue) {
        }
    }

    private class Inventory implements IInventory {
        public int getContainerSize() {
            return ScavengerHuntObjective.this.chestInventory.size();
        }

        public boolean isEmpty() {
            return ScavengerHuntObjective.this.chestInventory.isEmpty();
        }

        public ItemStack getItem(final int index) {
            return (ItemStack) ScavengerHuntObjective.this.chestInventory.get(index);
        }

        public ItemStack removeItem(final int index, final int count) {
            return ItemStackHelper.removeItem((List) ScavengerHuntObjective.this.chestInventory, index, count);
        }

        public ItemStack removeItemNoUpdate(final int index) {
            final ItemStack existing = this.getItem(index);
            this.setItem(index, ItemStack.EMPTY);
            return existing;
        }

        public void setItem(final int index, final ItemStack stack) {
            ScavengerHuntObjective.this.chestInventory.set(index, (Object) stack);
        }

        public void setChanged() {
        }

        public boolean stillValid(final PlayerEntity player) {
            return true;
        }

        public void clearContent() {
            ScavengerHuntObjective.this.chestInventory.clear();
        }
    }
}
