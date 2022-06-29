
package iskallia.vault.mixin;

import org.spongepowered.asm.mixin.Overwrite;
import net.minecraft.nbt.NBTSizeTracker;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ NBTSizeTracker.class })
public class MixinNBTSizeTracker {
    @Overwrite
    public void accountBits(final long bits) {
    }
}
