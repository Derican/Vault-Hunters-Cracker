// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.config.entry;

import iskallia.vault.util.MathUtilities;
import com.google.gson.annotations.Expose;

public class RangeEntry
{
    @Expose
    private final int min;
    @Expose
    private final int max;
    
    public RangeEntry(final int min, final int max) {
        this.min = min;
        this.max = max;
    }
    
    public int getRandom() {
        return MathUtilities.getRandomInt(this.min, this.max);
    }
}
