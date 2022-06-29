// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.world.data;

import java.util.Iterator;
import net.minecraft.entity.player.PlayerEntity;
import iskallia.vault.client.ClientPartyData;
import java.util.Collection;
import iskallia.vault.util.MiscUtils;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import net.minecraft.nbt.StringNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.nbt.ListNBT;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.network.NetworkDirection;
import iskallia.vault.network.message.PartyMembersMessage;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.LogicalSidedProvider;
import net.minecraftforge.event.TickEvent;
import java.util.Optional;
import java.util.UUID;
import iskallia.vault.network.message.PartyStatusMessage;
import net.minecraftforge.fml.network.PacketDistributor;
import iskallia.vault.init.ModNetwork;
import java.util.function.Supplier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.CompoundNBT;
import iskallia.vault.nbt.VListNBT;
import java.util.Random;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.storage.WorldSavedData;

@Mod.EventBusSubscriber
public class VaultPartyData extends WorldSavedData
{
    private static final Random rand;
    protected static final String DATA_NAME = "the_vault_VaultParty";
    protected VListNBT<Party, CompoundNBT> activeParties;
    
    public VaultPartyData() {
        this("the_vault_VaultParty");
    }
    
    public VaultPartyData(final String name) {
        super(name);
        this.activeParties = VListNBT.of(Party::new);
    }
    
    public void load(final CompoundNBT nbt) {
        this.activeParties.deserializeNBT(nbt.getList("ActiveParties", 10));
    }
    
    public CompoundNBT save(final CompoundNBT nbt) {
        nbt.put("ActiveParties", (INBT)this.activeParties.serializeNBT());
        return nbt;
    }
    
    public static VaultPartyData get(final ServerWorld world) {
        return get(world.getServer());
    }
    
    public static VaultPartyData get(final MinecraftServer server) {
        return (VaultPartyData)server.overworld().getDataStorage().computeIfAbsent((Supplier)VaultPartyData::new, "the_vault_VaultParty");
    }
    
    public static void broadcastPartyData(final ServerWorld world) {
        final VaultPartyData data = get(world);
        ModNetwork.CHANNEL.send(PacketDistributor.ALL.noArg(), (Object)new PartyStatusMessage(data.activeParties.serializeNBT()));
    }
    
    public Optional<Party> getParty(final UUID playerId) {
        return this.activeParties.stream().filter(party -> party.hasMember(playerId)).findFirst();
    }
    
    public boolean createParty(final UUID playerId) {
        if (this.getParty(playerId).isPresent()) {
            return false;
        }
        final Party newParty = new Party();
        newParty.addMember(playerId);
        this.activeParties.add(newParty);
        return true;
    }
    
    public boolean disbandParty(final UUID playerId) {
        final Optional<Party> party = this.getParty(playerId);
        if (!party.isPresent()) {
            return false;
        }
        this.activeParties.remove(party.get());
        return true;
    }
    
    @SubscribeEvent
    public static void onServerTick(final TickEvent.ServerTickEvent event) {
        final MinecraftServer serverInstance = (MinecraftServer)LogicalSidedProvider.INSTANCE.get(LogicalSide.SERVER);
        if (event.phase != TickEvent.Phase.END) {
            return;
        }
        if (serverInstance.getTickCount() % 20 == 0) {
            final VaultPartyData vaultPartyData = get(serverInstance);
            vaultPartyData.activeParties.forEach(party -> {
                final ListNBT partyMembers = party.toClientMemberList();
                final PartyMembersMessage pkt = new PartyMembersMessage(partyMembers);
                party.members.forEach(uuid -> {
                    final ServerPlayerEntity player = serverInstance.getPlayerList().getPlayer(uuid);
                    if (player != null) {
                        ModNetwork.CHANNEL.sendTo((Object)pkt, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
                    }
                });
            });
        }
    }
    
    static {
        rand = new Random();
    }
    
    public static class Party implements INBTSerializable<CompoundNBT>
    {
        private UUID leader;
        private final VListNBT<UUID, StringNBT> members;
        private final VListNBT<UUID, StringNBT> invites;
        
        public Party() {
            this.leader = null;
            this.members = VListNBT.ofUUID();
            this.invites = VListNBT.ofUUID();
        }
        
        public List<UUID> getMembers() {
            return Collections.unmodifiableList((List<? extends UUID>)this.members);
        }
        
        @Nullable
        public UUID getLeader() {
            return this.leader;
        }
        
        public boolean addMember(final UUID member) {
            if (this.members.isEmpty()) {
                this.leader = member;
            }
            return this.members.add(member);
        }
        
        public boolean invite(final UUID member) {
            if (this.invites.contains(member)) {
                return false;
            }
            this.invites.add(member);
            return true;
        }
        
        public boolean remove(final UUID member) {
            final boolean removed = this.members.remove(member);
            if (removed && member.equals(this.leader)) {
                this.leader = MiscUtils.getRandomEntry((Collection<UUID>)this.members, VaultPartyData.rand);
            }
            return removed;
        }
        
        public boolean confirmInvite(final UUID member) {
            if (this.invites.contains(member) && this.invites.remove(member)) {
                this.members.add(member);
                return true;
            }
            return false;
        }
        
        public boolean hasMember(final UUID member) {
            return this.members.contains(member);
        }
        
        public ListNBT toClientMemberList() {
            final MinecraftServer serverInstance = (MinecraftServer)LogicalSidedProvider.INSTANCE.get(LogicalSide.SERVER);
            final ListNBT partyMembers = new ListNBT();
            for (final UUID uuid : this.members) {
                final ServerPlayerEntity player = serverInstance.getPlayerList().getPlayer(uuid);
                if (player == null) {
                    continue;
                }
                final ClientPartyData.PartyMember partyMember = new ClientPartyData.PartyMember((PlayerEntity)player);
                partyMembers.add((Object)partyMember.serializeNBT());
            }
            return partyMembers;
        }
        
        public CompoundNBT serializeNBT() {
            final CompoundNBT nbt = new CompoundNBT();
            if (this.leader != null) {
                nbt.putUUID("leader", this.leader);
            }
            nbt.put("Members", (INBT)this.members.serializeNBT());
            nbt.put("Invites", (INBT)this.invites.serializeNBT());
            return nbt;
        }
        
        public void deserializeNBT(final CompoundNBT nbt) {
            this.leader = null;
            if (nbt.hasUUID("leader")) {
                this.leader = nbt.getUUID("leader");
            }
            this.members.deserializeNBT(nbt.getList("Members", 8));
            this.invites.deserializeNBT(nbt.getList("Invites", 8));
        }
    }
}
