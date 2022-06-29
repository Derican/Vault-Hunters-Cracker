// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.mixin;

import net.minecraft.inventory.EquipmentSlotType;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.item.ArmorMaterial;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ ArmorMaterial.class })
public class MixinArmorMaterial
{
    @Inject(method = { "getToughness" }, at = { @At("HEAD") }, cancellable = true)
    public void getToughness(final CallbackInfoReturnable<Float> ci) {
        final ArmorMaterial material = (ArmorMaterial)this;
        if (material == ArmorMaterial.LEATHER || material == ArmorMaterial.CHAIN || material == ArmorMaterial.GOLD || material == ArmorMaterial.IRON || material == ArmorMaterial.DIAMOND || material == ArmorMaterial.NETHERITE) {
            ci.setReturnValue((Object)0.0f);
        }
    }
    
    @Inject(method = { "getKnockbackResistance" }, at = { @At("HEAD") }, cancellable = true)
    public void getKockbackResistance(final CallbackInfoReturnable<Float> ci) {
        final ArmorMaterial material = (ArmorMaterial)this;
        if (material == ArmorMaterial.LEATHER || material == ArmorMaterial.CHAIN || material == ArmorMaterial.GOLD || material == ArmorMaterial.IRON || material == ArmorMaterial.DIAMOND || material == ArmorMaterial.NETHERITE) {
            ci.setReturnValue((Object)0.0f);
        }
    }
    
    @Inject(method = { "getDamageReductionAmount" }, at = { @At("HEAD") }, cancellable = true)
    public void getDamageReductionAmount(final EquipmentSlotType slot, final CallbackInfoReturnable<Integer> ci) {
        Label_0549: {
            switch ((ArmorMaterial)this) {
                case LEATHER: {
                    switch (slot) {
                        case HEAD: {
                            ci.setReturnValue((Object)1);
                            break;
                        }
                        case CHEST: {
                            ci.setReturnValue((Object)1);
                            break;
                        }
                        case LEGS: {
                            ci.setReturnValue((Object)1);
                            break;
                        }
                        case FEET: {
                            ci.setReturnValue((Object)1);
                            break;
                        }
                    }
                    break;
                }
                case CHAIN: {
                    switch (slot) {
                        case HEAD: {
                            ci.setReturnValue((Object)1);
                            break;
                        }
                        case CHEST: {
                            ci.setReturnValue((Object)2);
                            break;
                        }
                        case LEGS: {
                            ci.setReturnValue((Object)2);
                            break;
                        }
                        case FEET: {
                            ci.setReturnValue((Object)1);
                            break;
                        }
                    }
                    break;
                }
                case GOLD: {
                    switch (slot) {
                        case HEAD: {
                            ci.setReturnValue((Object)2);
                            break;
                        }
                        case CHEST: {
                            ci.setReturnValue((Object)2);
                            break;
                        }
                        case LEGS: {
                            ci.setReturnValue((Object)2);
                            break;
                        }
                        case FEET: {
                            ci.setReturnValue((Object)1);
                            break;
                        }
                    }
                    break;
                }
                case IRON: {
                    switch (slot) {
                        case HEAD: {
                            ci.setReturnValue((Object)2);
                            break;
                        }
                        case CHEST: {
                            ci.setReturnValue((Object)2);
                            break;
                        }
                        case LEGS: {
                            ci.setReturnValue((Object)2);
                            break;
                        }
                        case FEET: {
                            ci.setReturnValue((Object)2);
                            break;
                        }
                    }
                    break;
                }
                case DIAMOND: {
                    switch (slot) {
                        case HEAD: {
                            ci.setReturnValue((Object)2);
                            break;
                        }
                        case CHEST: {
                            ci.setReturnValue((Object)3);
                            break;
                        }
                        case LEGS: {
                            ci.setReturnValue((Object)3);
                            break;
                        }
                        case FEET: {
                            ci.setReturnValue((Object)2);
                            break;
                        }
                    }
                    break;
                }
                case NETHERITE: {
                    switch (slot) {
                        case HEAD: {
                            ci.setReturnValue((Object)3);
                            break Label_0549;
                        }
                        case CHEST: {
                            ci.setReturnValue((Object)4);
                            break Label_0549;
                        }
                        case LEGS: {
                            ci.setReturnValue((Object)4);
                            break Label_0549;
                        }
                        case FEET: {
                            ci.setReturnValue((Object)3);
                            break Label_0549;
                        }
                    }
                    break;
                }
            }
        }
    }
}
