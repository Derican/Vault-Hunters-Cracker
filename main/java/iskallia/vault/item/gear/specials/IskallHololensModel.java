// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.item.gear.specials;

import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.inventory.EquipmentSlotType;
import iskallia.vault.item.gear.model.VaultGearModel;
import net.minecraft.entity.LivingEntity;

public class IskallHololensModel<T extends LivingEntity> extends VaultGearModel<T>
{
    public IskallHololensModel(final float modelSize, final EquipmentSlotType slotType) {
        super(modelSize, slotType);
        this.texWidth = 32;
        this.texHeight = 32;
        (this.Head = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
        this.Head.texOffs(0, 0).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, 1.0f, false);
    }
}
