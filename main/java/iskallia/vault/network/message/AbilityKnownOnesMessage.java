
package iskallia.vault.network.message;

import iskallia.vault.client.ClientAbilityData;
import net.minecraftforge.fml.network.NetworkEvent;
import java.util.function.Supplier;
import java.util.ArrayList;
import net.minecraft.nbt.INBT;
import java.util.function.Consumer;
import java.util.function.Function;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import iskallia.vault.skill.ability.AbilityTree;
import iskallia.vault.skill.ability.AbilityNode;
import java.util.List;

public class AbilityKnownOnesMessage {
    private final List<AbilityNode<?, ?>> learnedAbilities;

    public AbilityKnownOnesMessage(final AbilityTree abilityTree) {
        this(abilityTree.getLearnedNodes());
    }

    private AbilityKnownOnesMessage(final List<AbilityNode<?, ?>> learnedAbilities) {
        this.learnedAbilities = learnedAbilities;
    }

    public List<AbilityNode<?, ?>> getLearnedAbilities() {
        return this.learnedAbilities;
    }

    public static void encode(final AbilityKnownOnesMessage message, final PacketBuffer buffer) {
        final CompoundNBT nbt = new CompoundNBT();
        final ListNBT abilities = new ListNBT();
        message.learnedAbilities.stream().map((Function<? super Object, ?>) AbilityNode::serializeNBT)
                .forEach(abilities::add);
        nbt.put("LearnedAbilities", (INBT) abilities);
        buffer.writeNbt(nbt);
    }

    public static AbilityKnownOnesMessage decode(final PacketBuffer buffer) {
        final ArrayList<AbilityNode<?, ?>> abilities = new ArrayList<AbilityNode<?, ?>>();
        final CompoundNBT nbt = buffer.readNbt();
        final ListNBT learnedAbilities = nbt.getList("LearnedAbilities", 10);
        for (int i = 0; i < learnedAbilities.size(); ++i) {
            abilities.add(AbilityNode.fromNBT(learnedAbilities.getCompound(i)));
        }
        return new AbilityKnownOnesMessage(abilities);
    }

    public static void handle(final AbilityKnownOnesMessage message,
            final Supplier<NetworkEvent.Context> contextSupplier) {
        final NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> ClientAbilityData.updateAbilities(message));
        context.setPacketHandled(true);
    }
}
