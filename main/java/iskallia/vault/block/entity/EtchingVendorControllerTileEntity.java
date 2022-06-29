
package iskallia.vault.block.entity;

import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import java.util.Iterator;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.block.BlockState;
import javax.annotation.Nullable;
import net.minecraft.item.ItemStack;
import iskallia.vault.config.EtchingConfig;
import iskallia.vault.item.gear.VaultGear;
import iskallia.vault.util.MathUtilities;
import iskallia.vault.item.gear.EtchingItem;
import iskallia.vault.init.ModConfigs;
import java.util.Random;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.SpawnReason;
import iskallia.vault.init.ModEntities;
import iskallia.vault.entity.EtchingVendorEntity;
import net.minecraft.entity.Entity;
import iskallia.vault.Vault;
import net.minecraft.world.server.ServerWorld;
import java.util.ArrayList;
import net.minecraft.tileentity.TileEntityType;
import iskallia.vault.init.ModBlocks;
import java.util.List;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class EtchingVendorControllerTileEntity extends TileEntity implements ITickableTileEntity {
    private int monitoredEntityId;
    private final List<EtchingTrade> trades;

    public EtchingVendorControllerTileEntity() {
        super((TileEntityType) ModBlocks.ETCHING_CONTROLLER_TILE_ENTITY);
        this.monitoredEntityId = -1;
        this.trades = new ArrayList<EtchingTrade>();
    }

    public void tick() {
        if (this.getLevel().isClientSide() || !(this.getLevel() instanceof ServerWorld)) {
            return;
        }
        if (this.getLevel().dimension() != Vault.VAULT_KEY) {
            return;
        }
        if (this.trades.isEmpty()) {
            this.generateTrades();
            this.sendUpdates();
        }
        Entity monitoredEntity;
        if (this.monitoredEntityId == -1) {
            monitoredEntity = this.createVendor();
        } else if ((monitoredEntity = this.level.getEntity(this.monitoredEntityId)) == null) {
            monitoredEntity = this.createVendor();
        }
        monitoredEntity.setPos(this.worldPosition.getX() + 0.5,
                (double) this.worldPosition.getY(), this.worldPosition.getZ() + 0.5);
    }

    private Entity createVendor() {
        final ServerWorld sWorld = (ServerWorld) this.getLevel();
        final EtchingVendorEntity vendor = (EtchingVendorEntity) ModEntities.ETCHING_VENDOR.create(sWorld,
                (CompoundNBT) null, (ITextComponent) null, (PlayerEntity) null, this.getBlockPos(),
                SpawnReason.STRUCTURE, false, false);
        vendor.setVendorPos(this.getBlockPos());
        sWorld.addFreshEntity((Entity) vendor);
        this.monitoredEntityId = vendor.getId();
        return (Entity) vendor;
    }

    private void generateTrades() {
        final Random r = new Random();
        for (int i = 0; i < 3; ++i) {
            final VaultGear.Set etchingSet = ModConfigs.ETCHING.getRandomSet();
            final EtchingConfig.Etching etching = ModConfigs.ETCHING.getFor(etchingSet);
            final ItemStack etchingStack = EtchingItem.createEtchingStack(etchingSet);
            final int amount = MathUtilities.getRandomInt(etching.minValue, etching.maxValue + 1);
            this.trades.add(new EtchingTrade(etchingStack, amount, false));
        }
    }

    public int getMonitoredEntityId() {
        return this.monitoredEntityId;
    }

    public void setMonitoredEntityId(final int id) {
        if (this.monitoredEntityId == -1) {
            this.monitoredEntityId = id;
        }
    }

    @Nullable
    public EtchingTrade getTrade(final int id) {
        if (id < 0 || id >= this.trades.size()) {
            return null;
        }
        return this.trades.get(id);
    }

    public void load(final BlockState state, final CompoundNBT nbt) {
        super.load(state, nbt);
        final ListNBT trades = nbt.getList("trades", 10);
        for (int i = 0; i < trades.size(); ++i) {
            final CompoundNBT tradeTag = trades.getCompound(i);
            this.trades.add(EtchingTrade.deserialize(tradeTag));
        }
    }

    public CompoundNBT save(final CompoundNBT compound) {
        final CompoundNBT tag = super.save(compound);
        final ListNBT trades = new ListNBT();
        for (final EtchingTrade trade : this.trades) {
            trades.add((Object) trade.serialize());
        }
        compound.put("trades", (INBT) trades);
        return tag;
    }

    public CompoundNBT getUpdateTag() {
        return this.save(new CompoundNBT());
    }

    public void handleUpdateTag(final BlockState state, final CompoundNBT tag) {
        this.load(state, tag);
    }

    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.worldPosition, 1, this.getUpdateTag());
    }

    public void onDataPacket(final NetworkManager net, final SUpdateTileEntityPacket pkt) {
        final CompoundNBT tag = pkt.getTag();
        this.handleUpdateTag(this.getBlockState(), tag);
    }

    public void sendUpdates() {
        this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 11);
        this.level.updateNeighborsAt(this.worldPosition, this.getBlockState().getBlock());
        this.setChanged();
    }

    public static class EtchingTrade {
        private final ItemStack soldEtching;
        private final int requiredPlatinum;
        private boolean sold;

        public EtchingTrade(final ItemStack soldEtching, final int requiredPlatinum, final boolean sold) {
            this.soldEtching = soldEtching;
            this.requiredPlatinum = requiredPlatinum;
            this.sold = sold;
        }

        public ItemStack getSoldEtching() {
            return this.soldEtching;
        }

        public int getRequiredPlatinum() {
            return this.requiredPlatinum;
        }

        public void setSold(final boolean sold) {
            this.sold = sold;
        }

        public boolean isSold() {
            return this.sold;
        }

        public CompoundNBT serialize() {
            final CompoundNBT nbt = new CompoundNBT();
            nbt.put("stack", (INBT) this.soldEtching.serializeNBT());
            nbt.putInt("amount", this.requiredPlatinum);
            nbt.putBoolean("sold", this.sold);
            return nbt;
        }

        public static EtchingTrade deserialize(final CompoundNBT nbt) {
            final ItemStack stack = ItemStack.of(nbt.getCompound("stack"));
            final int amount = nbt.getInt("amount");
            final boolean sold = nbt.getBoolean("sold");
            return new EtchingTrade(stack, amount, sold);
        }
    }
}
