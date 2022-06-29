
package iskallia.vault.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import iskallia.vault.block.property.HiddenIntegerProperty;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.state.Property;
import java.util.Map;
import net.minecraft.client.gui.overlay.DebugOverlayGui;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ DebugOverlayGui.class })
public class MixinDebugOverlayGui {
    @Inject(method = { "getPropertyString" }, at = { @At("RETURN") }, cancellable = true)
    public void hidePropertyString(final Map.Entry<Property<?>, Comparable<?>> entryIn,
            final CallbackInfoReturnable<String> cir) {
        if (entryIn.getKey() instanceof HiddenIntegerProperty) {
            cir.setReturnValue((Object) (entryIn.getKey().getName() + ": unknown"));
        }
    }
}
