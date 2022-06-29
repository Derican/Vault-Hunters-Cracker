// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.network.message;

import iskallia.vault.client.ClientTalentData;
import net.minecraftforge.fml.network.NetworkEvent;
import java.util.function.Supplier;
import java.util.UUID;
import java.util.ArrayList;
import net.minecraft.nbt.INBT;
import java.util.function.Consumer;
import java.util.function.Function;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import iskallia.vault.skill.talent.TalentTree;
import iskallia.vault.skill.talent.TalentNode;
import java.util.List;

public class KnownTalentsMessage
{
    private final List<TalentNode<?>> learnedTalents;
    
    public KnownTalentsMessage(final TalentTree talentTree) {
        this(talentTree.getLearnedNodes());
    }
    
    private KnownTalentsMessage(final List<TalentNode<?>> learnedTalents) {
        this.learnedTalents = learnedTalents;
    }
    
    public List<TalentNode<?>> getLearnedTalents() {
        return this.learnedTalents;
    }
    
    public static void encode(final KnownTalentsMessage message, final PacketBuffer buffer) {
        final CompoundNBT nbt = new CompoundNBT();
        final ListNBT talents = new ListNBT();
        message.learnedTalents.stream().map((Function<? super Object, ?>)TalentNode::serializeNBT).forEach(talents::add);
        nbt.put("LearnedTalents", (INBT)talents);
        buffer.writeNbt(nbt);
    }
    
    public static KnownTalentsMessage decode(final PacketBuffer buffer) {
        final List<TalentNode<?>> abilities = new ArrayList<TalentNode<?>>();
        final CompoundNBT nbt = buffer.readNbt();
        final ListNBT learnedTalents = nbt.getList("LearnedTalents", 10);
        for (int i = 0; i < learnedTalents.size(); ++i) {
            abilities.add(TalentNode.fromNBT(null, learnedTalents.getCompound(i), 2));
        }
        return new KnownTalentsMessage(abilities);
    }
    
    public static void handle(final KnownTalentsMessage message, final Supplier<NetworkEvent.Context> contextSupplier) {
        final NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> ClientTalentData.updateTalents(message));
        context.setPacketHandled(true);
    }
}
