// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.mixin;

import org.spongepowered.asm.mixin.Overwrite;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import net.minecraft.util.math.MathHelper;
import net.minecraft.tileentity.StructureBlockTileEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ StructureBlockTileEntity.class })
public abstract class MixinStructureBlockTileEntity
{
    @Redirect(method = { "read" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;clamp(III)I"))
    private int read(final int num, final int min, final int max) {
        return MathHelper.clamp(num, min * 11, max * 11);
    }
    
    @OnlyIn(Dist.CLIENT)
    @Overwrite
    public double getViewDistance() {
        return 816.0;
    }
}
