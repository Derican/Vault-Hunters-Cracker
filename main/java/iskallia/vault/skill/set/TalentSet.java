// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.skill.set;

import net.minecraft.entity.player.PlayerEntity;
import iskallia.vault.item.gear.VaultGear;
import com.google.gson.annotations.Expose;
import iskallia.vault.skill.talent.type.PlayerTalent;

public abstract class TalentSet<T extends PlayerTalent> extends PlayerSet
{
    @Expose
    private final T child;
    
    public TalentSet(final VaultGear.Set set, final T child) {
        super(set);
        this.child = child;
    }
    
    public T getChild() {
        return this.child;
    }
    
    @Override
    public void onAdded(final PlayerEntity player) {
        this.child.onAdded(player);
    }
    
    @Override
    public void onRemoved(final PlayerEntity player) {
        this.child.onRemoved(player);
    }
}
