// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.mixin;

import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import net.minecraft.network.login.server.SCustomPayloadLoginPacket;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ SCustomPayloadLoginPacket.class })
public class MixinSCustomPayloadLoginPacket
{
    @ModifyConstant(method = { "readPacketData" }, constant = { @Constant(intValue = 1048576) }, require = 1)
    public int adjustMaxPayloadSize(final int maxPayloadSize) {
        return Integer.MAX_VALUE;
    }
}
