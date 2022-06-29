
package iskallia.vault.skill.set;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.INBT;
import java.util.function.Consumer;
import java.util.function.Function;
import net.minecraft.nbt.ListNBT;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import net.minecraft.entity.LivingEntity;
import iskallia.vault.util.NetcodeUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import java.util.Optional;
import iskallia.vault.init.ModConfigs;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public class SetTree implements INBTSerializable<CompoundNBT> {
    private final UUID uuid;
    private final List<SetNode<?>> nodes;

    public SetTree(final UUID uuid) {
        this.nodes = new ArrayList<SetNode<?>>();
        this.uuid = uuid;
    }

    public List<SetNode<?>> getNodes() {
        return this.nodes;
    }

    public SetNode<?> getNodeOf(final SetGroup<?> setGroup) {
        return this.getNodeByName(setGroup.getParentName());
    }

    public SetNode<?> getNodeByName(final String name) {
        final Optional<SetNode<?>> talentWrapped = this.nodes.stream()
                .filter(node -> node.getGroup().getParentName().equals(name)).findFirst();
        if (!talentWrapped.isPresent()) {
            final SetNode<?> talentNode = new SetNode<Object>(ModConfigs.SETS.getByName(name), 0);
            this.nodes.add(talentNode);
            return talentNode;
        }
        return talentWrapped.get();
    }

    public SetTree add(final MinecraftServer server, final SetNode<?>... nodes) {
        for (final SetNode<?> node : nodes) {
            NetcodeUtils.runIfPresent(server, this.uuid, player -> {
                if (node.isActive()) {
                    node.getSet().onAdded((PlayerEntity) player);
                }
                return;
            });
            this.nodes.add(node);
        }
        return this;
    }

    public SetTree tick(final MinecraftServer server) {
        NetcodeUtils.runIfPresent(server, this.uuid, player -> {
            this.nodes.removeIf(node -> node.getLevel() == 0);
            final List<SetNode<?>> toRemove = this.nodes.stream().filter(SetNode::isActive)
                    .filter(setNode -> !setNode.getSet().shouldBeActive((LivingEntity) player))
                    .collect((Collector<? super Object, ?, List<SetNode<?>>>) Collectors.toList());
            toRemove.forEach(setNode -> this.remove(server, setNode));
            ModConfigs.SETS
                    .getAll().stream().filter(setGroup -> this.nodes.stream()
                            .map(setNode -> setNode.getGroup().getName()).noneMatch(s -> s.equals(setGroup.getName())))
                    .forEach(setGroup -> {
                        int i = setGroup.getMaxLevel();
                        while (i > 0) {
                            final PlayerSet set = setGroup.getSet(i);
                            if (set.shouldBeActive((LivingEntity) player)) {
                                this.add(server, new SetNode(setGroup, i));
                                break;
                            } else {
                                --i;
                            }
                        }
                        return;
                    });
            this.nodes.forEach(setNode -> setNode.getSet().onTick((PlayerEntity) player));
            return;
        });
        return this;
    }

    public SetTree remove(final MinecraftServer server, final SetNode<?>... nodes) {
        for (final SetNode<?> node : nodes) {
            NetcodeUtils.runIfPresent(server, this.uuid, player -> {
                if (node.isActive()) {
                    node.getSet().onRemoved((PlayerEntity) player);
                }
                return;
            });
            this.nodes.remove(node);
        }
        return this;
    }

    public CompoundNBT serializeNBT() {
        final CompoundNBT nbt = new CompoundNBT();
        final ListNBT list = new ListNBT();
        this.nodes.stream().map((Function<? super Object, ?>) SetNode::serializeNBT).forEach(list::add);
        nbt.put("Nodes", (INBT) list);
        return nbt;
    }

    public void deserializeNBT(final CompoundNBT nbt) {
        final ListNBT list = nbt.getList("Nodes", 10);
        this.nodes.clear();
        for (int i = 0; i < list.size(); ++i) {
            this.add(null, SetNode.fromNBT(list.getCompound(i), PlayerSet.class));
        }
    }
}
