
package iskallia.vault.network.message;

import iskallia.vault.client.ClientPartyData;
import net.minecraftforge.fml.network.NetworkEvent;
import java.util.function.Supplier;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.nbt.ListNBT;

public class PartyStatusMessage {
    private final ListNBT serializedParties;

    public PartyStatusMessage(final ListNBT serializedParties) {
        this.serializedParties = serializedParties;
    }

    public static void encode(final PartyStatusMessage message, final PacketBuffer buffer) {
        final CompoundNBT tag = new CompoundNBT();
        tag.put("list", (INBT) message.serializedParties);
        buffer.writeNbt(tag);
    }

    public static PartyStatusMessage decode(final PacketBuffer buffer) {
        return new PartyStatusMessage(buffer.readNbt().getList("list", 10));
    }

    public static void handle(final PartyStatusMessage message, final Supplier<NetworkEvent.Context> contextSupplier) {
        final NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> ClientPartyData.receivePartyUpdate(message.serializedParties));
        context.setPacketHandled(true);
    }
}
