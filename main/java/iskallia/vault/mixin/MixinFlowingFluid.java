
package iskallia.vault.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import iskallia.vault.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FlowingFluid;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ FlowingFluid.class })
public class MixinFlowingFluid {
    @Redirect(method = { "flowInto" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isAir()Z"))
    public boolean flowInto(final BlockState state) {
        return state.isAir() && state.getBlock() != ModBlocks.VAULT_AIR;
    }
}
