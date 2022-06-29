// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import iskallia.vault.Vault;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.block.BlockState;
import net.minecraft.block.FireBlock;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ FireBlock.class })
public class MixinFireBlock
{
    @Inject(method = { "tick" }, at = { @At("HEAD") }, cancellable = true)
    public void onFireTick(final BlockState state, final ServerWorld world, final BlockPos pos, final Random rand, final CallbackInfo ci) {
        if (world.dimension() == Vault.VAULT_KEY) {
            ci.cancel();
        }
    }
}
