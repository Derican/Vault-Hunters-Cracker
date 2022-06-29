// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.effect;

import net.minecraft.util.ResourceLocation;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effect;

public class BasicEffect extends Effect
{
    public BasicEffect(final EffectType typeIn, final int liquidColorIn, final ResourceLocation id) {
        super(typeIn, liquidColorIn);
        this.setRegistryName(id);
    }
    
    public boolean isDurationEffectTick(final int duration, final int amplifier) {
        return true;
    }
}
