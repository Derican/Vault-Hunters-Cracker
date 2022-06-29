
package iskallia.vault.network.message.base;

import java.util.function.Consumer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.nbt.CompoundNBT;

public class OpcodeMessage<OPC extends Enum<OPC>> {
    public OPC opcode;
    public CompoundNBT payload;

    public void encodeSelf(final OpcodeMessage<OPC> message, final PacketBuffer buffer) {
        buffer.writeEnum((Enum) message.opcode);
        buffer.writeNbt(message.payload);
    }

    public void decodeSelf(final PacketBuffer buffer, final Class<OPC> enumClass) {
        this.opcode = (OPC) buffer.readEnum((Class) enumClass);
        this.payload = buffer.readNbt();
    }

    public static <O extends Enum<O>, T extends OpcodeMessage<O>> T composeMessage(final T message, final O opcode,
            final Consumer<CompoundNBT> payloadSerializer) {
        message.opcode = (OPC) opcode;
        final CompoundNBT payload = new CompoundNBT();
        payloadSerializer.accept(payload);
        message.payload = payload;
        return message;
    }
}
