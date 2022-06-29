
package iskallia.vault.config;

import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import iskallia.vault.item.paxel.enhancement.PaxelEnhancements;
import iskallia.vault.item.paxel.enhancement.PaxelEnhancement;
import java.util.Random;
import com.google.gson.annotations.Expose;
import iskallia.vault.util.data.WeightedList;

public class PaxelEnhancementConfig extends Config {
    @Expose
    private WeightedList<String> ENHANCEMENT_WEIGHTS;

    @Override
    public String getName() {
        return "paxel_enhancement";
    }

    @Nullable
    public PaxelEnhancement getRandomEnhancement(final Random random) {
        final String enhancementSid = this.ENHANCEMENT_WEIGHTS.getRandom(random);
        if (enhancementSid == null) {
            return null;
        }
        return PaxelEnhancements.REGISTRY.get(new ResourceLocation(enhancementSid));
    }

    @Override
    protected void reset() {
        this.ENHANCEMENT_WEIGHTS = new WeightedList<String>();
        PaxelEnhancements.REGISTRY.keySet().forEach(enhancementId -> {
            final String enhancementSid = enhancementId.toString();
            this.ENHANCEMENT_WEIGHTS.add(enhancementSid, 1);
        });
    }
}
