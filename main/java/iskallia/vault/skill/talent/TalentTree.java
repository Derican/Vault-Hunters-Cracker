// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.skill.talent;

import net.minecraft.entity.player.ServerPlayerEntity;
import iskallia.vault.world.data.PlayerVaultStatsData;
import net.minecraftforge.fml.common.thread.SidedThreadGroups;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.LogicalSidedProvider;
import javax.annotation.Nullable;
import net.minecraft.nbt.INBT;
import java.util.function.Consumer;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fml.network.NetworkDirection;
import iskallia.vault.network.message.KnownTalentsMessage;
import iskallia.vault.init.ModNetwork;
import iskallia.vault.util.NetcodeUtils;
import net.minecraft.entity.player.PlayerEntity;
import java.util.Optional;
import javax.annotation.Nonnull;
import java.util.function.Function;
import java.util.Collection;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import net.minecraft.server.MinecraftServer;
import iskallia.vault.skill.talent.type.PlayerTalent;
import iskallia.vault.init.ModConfigs;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TalentTree
{
    public static final int CURRENT_VERSION = 2;
    private final UUID uuid;
    private final List<TalentNode<?>> nodes;
    private int version;
    
    public TalentTree(final UUID uuid) {
        this.nodes = new ArrayList<TalentNode<?>>();
        this.uuid = uuid;
        this.add(null, (TalentNode<?>[])ModConfigs.TALENTS.getAll().stream().map(talentGroup -> new TalentNode(talentGroup, 0)).toArray(TalentNode[]::new));
    }
    
    public List<TalentNode<?>> getNodes() {
        return this.nodes;
    }
    
    public List<TalentNode<?>> getLearnedNodes() {
        return this.getNodes().stream().filter(TalentNode::isLearned).collect((Collector<? super Object, ?, List<TalentNode<?>>>)Collectors.toList());
    }
    
    public <T extends PlayerTalent> List<TalentNode<T>> getLearnedNodes(final Class<T> talentGroupType) {
        return this.getNodes().stream().filter(TalentNode::isLearned).filter(talentNode -> talentGroupType.isAssignableFrom(talentNode.getTalent().getClass())).map(node -> node).collect((Collector<? super Object, ?, List<TalentNode<T>>>)Collectors.toList());
    }
    
    public boolean hasLearnedNode(final TalentGroup<?> talentGroup) {
        return this.getLearnedNodes().stream().anyMatch(node -> node.getGroup().getParentName().equals(talentGroup.getParentName()));
    }
    
    public <T extends PlayerTalent> Collection<T> getTalents(final Class<T> talentType) {
        return this.getNodes().stream().filter(TalentNode::isLearned).map((Function<? super Object, ?>)TalentNode::getTalent).filter(talent -> talentType.isAssignableFrom(talent.getClass())).map(talent -> talent).collect((Collector<? super Object, ?, Collection<T>>)Collectors.toList());
    }
    
    @Nonnull
    public <T extends PlayerTalent> TalentNode<T> getNodeOf(final TalentGroup<T> talentGroup) {
        return this.getNodeByName(talentGroup.getParentName());
    }
    
    @Nonnull
    public <T extends PlayerTalent> TalentNode<T> getNodeByName(final String name) {
        final Optional<TalentNode<T>> talentWrapped = this.nodes.stream().filter(node -> node.getGroup().getParentName().equals(name)).map(node -> node).findFirst();
        if (!talentWrapped.isPresent()) {
            final TalentNode<?> talentNode = new TalentNode<Object>(ModConfigs.TALENTS.getByName(name), 0);
            this.nodes.add(talentNode);
            return (TalentNode<T>)talentNode;
        }
        return talentWrapped.get();
    }
    
    public TalentTree upgradeTalent(final MinecraftServer server, final TalentNode<?> talentNode) {
        this.remove(server, talentNode);
        final TalentNode<?> upgradedTalentNode = new TalentNode<Object>(talentNode.getGroup(), talentNode.getLevel() + 1);
        this.add(server, upgradedTalentNode);
        return this;
    }
    
    public TalentTree downgradeTalent(final MinecraftServer server, final TalentNode<?> talentNode) {
        this.remove(server, talentNode);
        final int targetLevel = talentNode.getLevel() - 1;
        final TalentNode<?> upgradedTalentNode = new TalentNode<Object>(talentNode.getGroup(), Math.max(targetLevel, 0));
        this.add(server, upgradedTalentNode);
        return this;
    }
    
    public TalentTree add(final MinecraftServer server, final TalentNode<?>... nodes) {
        for (final TalentNode<?> node : nodes) {
            NetcodeUtils.runIfPresent(server, this.uuid, player -> {
                if (node.isLearned()) {
                    node.getTalent().onAdded((PlayerEntity)player);
                }
                return;
            });
            this.nodes.add(node);
        }
        return this;
    }
    
    public TalentTree tick(final MinecraftServer server) {
        NetcodeUtils.runIfPresent(server, this.uuid, player -> this.nodes.stream().filter(TalentNode::isLearned).forEach(node -> node.getTalent().tick((PlayerEntity)player)));
        return this;
    }
    
    public TalentTree remove(final MinecraftServer server, final TalentNode<?>... nodes) {
        for (final TalentNode<?> node : nodes) {
            NetcodeUtils.runIfPresent(server, this.uuid, player -> {
                if (node.isLearned()) {
                    node.getTalent().onRemoved((PlayerEntity)player);
                }
                return;
            });
            this.nodes.remove(node);
        }
        return this;
    }
    
    public void sync(final MinecraftServer server) {
        this.syncTree(server);
    }
    
    public void syncTree(final MinecraftServer server) {
        NetcodeUtils.runIfPresent(server, this.uuid, player -> ModNetwork.CHANNEL.sendTo((Object)new KnownTalentsMessage(this), player.connection.connection, NetworkDirection.PLAY_TO_CLIENT));
    }
    
    public CompoundNBT serializeNBT() {
        final CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("version", this.version);
        final ListNBT list = new ListNBT();
        this.nodes.stream().map((Function<? super Object, ?>)TalentNode::serializeNBT).forEach(list::add);
        nbt.put("Nodes", (INBT)list);
        return nbt;
    }
    
    public void deserialize(final CompoundNBT nbt, final boolean migrateData) {
        final int currentVersion = nbt.contains("version", 3) ? nbt.getInt("version") : 0;
        final ListNBT list = nbt.getList("Nodes", 10);
        this.nodes.clear();
        for (int i = 0; i < list.size(); ++i) {
            final TalentNode<?> talent = TalentNode.fromNBT(this.uuid, list.getCompound(i), migrateData ? currentVersion : 2);
            if (talent != null) {
                this.add(null, talent);
            }
        }
        this.version = 2;
    }
    
    @Nullable
    static TalentNode<?> migrate(@Nullable final UUID playerId, final String talentName, int talentLevel, final int vFrom) {
        final TalentGroup<?> group = ModConfigs.TALENTS.getTalent(talentName).orElse(null);
        if (vFrom >= 2) {
            return (group == null) ? null : new TalentNode<Object>(group, talentLevel);
        }
        int refundedCost = 0;
        final MinecraftServer srv = (MinecraftServer)LogicalSidedProvider.INSTANCE.get(LogicalSide.SERVER);
        if (vFrom <= 0 && (talentName.equalsIgnoreCase("Commander") || talentName.equalsIgnoreCase("Ward") || talentName.equalsIgnoreCase("Barbaric") || talentName.equalsIgnoreCase("Frenzy") || talentName.equalsIgnoreCase("Glass Cannon"))) {
            refundedCost += getRefundAmount(talentLevel, new int[] { 3, 5, 6, 7, 9 });
            talentLevel = 0;
        }
        if (vFrom <= 1 && talentName.equalsIgnoreCase("Soul Hunter")) {
            refundedCost += getRefundAmount(talentLevel, new int[] { 1, 2, 3, 5 });
            talentLevel = 0;
        }
        if (vFrom <= 2) {}
        if (playerId != null && srv != null && Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER) {
            PlayerVaultStatsData.get(srv).addSkillPointNoSync(playerId, refundedCost);
        }
        return (group == null) ? null : new TalentNode<Object>(group, talentLevel);
    }
    
    private static int getRefundAmount(final int currentLevel, final int[] levelCost) {
        int totalRefund = 0;
        for (int i = 0; i < Math.min(currentLevel, levelCost.length); ++i) {
            totalRefund += levelCost[i];
        }
        return totalRefund;
    }
}
