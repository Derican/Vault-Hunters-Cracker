
package iskallia.vault.config.entry;

import iskallia.vault.util.MathUtilities;
import com.google.gson.annotations.Expose;

public class StatueDecay {
    @Expose
    private int MIN;
    @Expose
    private int MAX;
    public static final StatueDecay NONE;

    public StatueDecay(final int min, final int max) {
        this.MIN = min;
        this.MAX = max;
    }

    public int getDecay() {
        return MathUtilities.getRandomInt(this.MIN, this.MAX);
    }

    static {
        NONE = new StatueDecay(-1, -1);
    }
}
