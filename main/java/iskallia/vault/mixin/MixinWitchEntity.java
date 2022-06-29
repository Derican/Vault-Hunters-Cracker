// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.mixin;

import iskallia.vault.init.ModSounds;
import net.minecraft.util.SoundEvent;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataSerializers;
import iskallia.vault.easteregg.Witchskall;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.entity.monster.WitchEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ WitchEntity.class })
public abstract class MixinWitchEntity
{
    @Inject(method = { "registerData" }, at = { @At("TAIL") })
    protected void registerData(final CallbackInfo ci) {
        final WitchEntity thiz = (WitchEntity)this;
        if (Witchskall.WITCHSKALL_TICKS == null) {
            Witchskall.WITCHSKALL_TICKS = (DataParameter<Integer>)EntityDataManager.defineId((Class)WitchEntity.class, DataSerializers.INT);
        }
        thiz.getEntityData().define((DataParameter)Witchskall.WITCHSKALL_TICKS, (Object)(-1));
        if (Witchskall.IS_WITCHSKALL == null) {
            Witchskall.IS_WITCHSKALL = (DataParameter<Boolean>)EntityDataManager.defineId((Class)WitchEntity.class, DataSerializers.BOOLEAN);
        }
        thiz.getEntityData().define((DataParameter)Witchskall.IS_WITCHSKALL, (Object)false);
    }
    
    @Inject(method = { "getAmbientSound" }, at = { @At("HEAD") }, cancellable = true)
    protected void getAmbientSound(final CallbackInfoReturnable<SoundEvent> ci) {
        final WitchEntity thiz = (WitchEntity)this;
        if (Witchskall.isWitchskall(thiz)) {
            ci.setReturnValue((Object)ModSounds.WITCHSKALL_IDLE);
        }
    }
}
