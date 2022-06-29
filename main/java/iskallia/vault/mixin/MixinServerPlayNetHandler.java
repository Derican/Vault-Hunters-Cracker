
package iskallia.vault.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.network.play.client.CPlayerPacket;
import net.minecraft.network.play.ServerPlayNetHandler;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ ServerPlayNetHandler.class })
public class MixinServerPlayNetHandler {
    private boolean doesOwnerCheck;

    public MixinServerPlayNetHandler() {
        this.doesOwnerCheck = false;
    }

    @Inject(method = { "processPlayer" }, at = {
            @At(value = "INVOKE", target = "Lnet/minecraft/network/play/ServerPlayNetHandler;isSingleplayerOwner()Z", shift = At.Shift.BEFORE) })
    public void onSpeedCheck(final CPlayerPacket packetIn, final CallbackInfo ci) {
        this.doesOwnerCheck = true;
    }

    @Inject(method = { "isSingleplayerOwner" }, at = { @At("HEAD") }, cancellable = true)
    public void isOwnerCheck(final CallbackInfoReturnable<Boolean> cir) {
        if (this.doesOwnerCheck) {
            cir.setReturnValue((Object) true);
        }
        this.doesOwnerCheck = false;
    }
}
