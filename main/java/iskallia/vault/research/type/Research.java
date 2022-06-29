
package iskallia.vault.research.type;

import net.minecraft.entity.EntityType;
import net.minecraft.block.Block;
import iskallia.vault.research.Restrictions;
import net.minecraft.item.Item;
import com.google.gson.annotations.Expose;

public abstract class Research {
    @Expose
    protected String name;
    @Expose
    protected int cost;
    @Expose
    protected boolean usesKnowledge;
    @Expose
    protected String gatedBy;

    public Research(final String name, final int cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return this.name;
    }

    public int getCost() {
        return this.cost;
    }

    public boolean isGated() {
        return this.gatedBy != null;
    }

    public String gatedBy() {
        return this.gatedBy;
    }

    public boolean usesKnowledge() {
        return this.usesKnowledge;
    }

    public abstract boolean restricts(final Item p0, final Restrictions.Type p1);

    public abstract boolean restricts(final Block p0, final Restrictions.Type p1);

    public abstract boolean restricts(final EntityType<?> p0, final Restrictions.Type p1);
}
