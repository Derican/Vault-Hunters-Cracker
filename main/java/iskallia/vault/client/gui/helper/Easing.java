// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.client.gui.helper;

import java.util.function.Function;

public enum Easing
{
    CONSTANT_ONE(x -> 1.0f), 
    LINEAR_IN(x -> x), 
    LINEAR_OUT(x -> 1.0f - x), 
    EASE_IN_OUT_SINE(x -> -((float)Math.cos(3.141592653589793 * x) - 1.0f) / 2.0f), 
    EXPO_OUT(x -> (x == 1.0f) ? 1.0f : (1.0f - (float)Math.pow(2.0, -10.0f * x))), 
    EASE_OUT_BOUNCE(x -> {
        final float n6 = 7.5625f;
        final float d1 = 2.75f;
        if (x < 1.0f / d1) {
            return Float.valueOf(n6 * x * x);
        }
        else if (x < 2.0f / d1) {
            x -= 1.5f / d1;
            final float n8;
            final Float n9;
            return Float.valueOf(n8 * n9 * x + 0.75f);
        }
        else if (x < 2.5 / d1) {
            x -= 2.25f / d1;
            final float n10;
            final Float n11;
            return Float.valueOf(n10 * n11 * x + 0.9375f);
        }
        else {
            x -= 2.625f / d1;
            final float n12;
            final Float n13;
            return Float.valueOf(n12 * n13 * x + 0.984375f);
        }
    });
    
    Function<Float, Float> function;
    
    private Easing(final Function<Float, Float> function) {
        this.function = function;
    }
    
    public Function<Float, Float> getFunction() {
        return this.function;
    }
    
    public float calc(final float time) {
        return this.function.apply(time);
    }
}
