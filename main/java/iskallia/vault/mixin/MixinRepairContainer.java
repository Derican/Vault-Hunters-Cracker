
package iskallia.vault.mixin;

import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import net.minecraft.inventory.container.RepairContainer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ RepairContainer.class })
public class MixinRepairContainer {
    @ModifyConstant(method = { "updateRepairOutput" }, constant = { @Constant(intValue = 40, ordinal = 2) })
    private int overrideMaxRepairLevel(final int oldValue) {
        return Integer.MAX_VALUE;
    }
}
