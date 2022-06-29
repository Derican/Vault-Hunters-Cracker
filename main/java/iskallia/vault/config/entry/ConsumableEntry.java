// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.config.entry;

import java.util.ArrayList;
import iskallia.vault.item.consumable.ConsumableType;
import java.util.List;
import com.google.gson.annotations.Expose;

public class ConsumableEntry
{
    @Expose
    private String itemId;
    @Expose
    private boolean absorption;
    @Expose
    private float absorptionAmount;
    @Expose
    private List<ConsumableEffect> effects;
    @Expose
    private String type;
    
    public ConsumableEntry(final String itemId, final boolean absorption, final float absorptionAmount, final ConsumableType type) {
        this.effects = new ArrayList<ConsumableEffect>();
        this.type = "";
        this.itemId = itemId;
        this.absorption = absorption;
        this.absorptionAmount = absorptionAmount;
        this.type = type.toString();
    }
    
    public ConsumableEntry addEffect(final ConsumableEffect entry) {
        this.effects.add(entry);
        return this;
    }
    
    public String getItemId() {
        return this.itemId;
    }
    
    public boolean shouldAddAbsorption() {
        return this.absorption;
    }
    
    public float getAbsorptionAmount() {
        return this.absorptionAmount;
    }
    
    public List<ConsumableEffect> getEffects() {
        return this.effects;
    }
    
    public ConsumableType getType() {
        return ConsumableType.fromString(this.type);
    }
    
    public boolean isPowerup() {
        return ConsumableType.fromString(this.type) == ConsumableType.POWERUP;
    }
}
