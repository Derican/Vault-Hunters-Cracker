// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.util.ActionResultType;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.SpawnEggItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ SpawnEggItem.class })
public class MixinSpawnEggItem
{
    @Inject(method = { "onItemUse" }, at = { @At(value = "INVOKE", target = "Lnet/minecraft/world/spawner/AbstractSpawner;setEntityType(Lnet/minecraft/entity/EntityType;)V", shift = At.Shift.BEFORE) }, cancellable = true)
    public void onItemUse(final ItemUseContext context, final CallbackInfoReturnable<ActionResultType> cir) {
        if (context.getPlayer() != null && !context.getPlayer().isCreative()) {
            cir.setReturnValue((Object)ActionResultType.PASS);
        }
    }
}
