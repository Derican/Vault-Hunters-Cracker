// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.mixin;

import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import net.minecraft.network.login.client.CCustomPayloadLoginPacket;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ CCustomPayloadLoginPacket.class })
public class MixinCCustomPayloadLoginPacket
{
    @ModifyConstant(method = { "readPacketData" }, constant = { @Constant(intValue = 1048576) }, require = 1)
    public int adjustMaxPayloadSize(final int maxPayloadSize) {
        return Integer.MAX_VALUE;
    }
}
