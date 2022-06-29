
package iskallia.vault.network.message;

import iskallia.vault.research.StageManager;
import net.minecraftforge.fml.network.NetworkEvent;
import java.util.function.Supplier;
import java.util.Objects;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import java.util.UUID;
import iskallia.vault.research.ResearchTree;

public class ResearchTreeMessage {
    public ResearchTree researchTree;
    public UUID playerUUID;

    public ResearchTreeMessage() {
    }

    public ResearchTreeMessage(final ResearchTree researchTree, final UUID playerUUID) {
        this.researchTree = researchTree;
        this.playerUUID = playerUUID;
    }

    public static void encode(final ResearchTreeMessage message, final PacketBuffer buffer) {
        buffer.writeUUID(message.playerUUID);
        buffer.writeNbt(message.researchTree.serializeNBT());
    }

    public static ResearchTreeMessage decode(final PacketBuffer buffer) {
        final ResearchTreeMessage message = new ResearchTreeMessage();
        (message.researchTree = new ResearchTree(buffer.readUUID()))
                .deserializeNBT(Objects.requireNonNull(buffer.readNbt()));
        return message;
    }

    public static void handle(final ResearchTreeMessage message, final Supplier<NetworkEvent.Context> contextSupplier) {
        final NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> StageManager.RESEARCH_TREE = message.researchTree);
        context.setPacketHandled(true);
    }
}
