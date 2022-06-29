// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.item.gear.specials;

import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.inventory.EquipmentSlotType;
import iskallia.vault.item.gear.model.VaultGearModel;
import net.minecraft.entity.LivingEntity;

public class TrashArmorModel<T extends LivingEntity> extends VaultGearModel<T>
{
    public TrashArmorModel(final float modelSize, final EquipmentSlotType slotType) {
        super(modelSize, slotType);
        this.texWidth = (this.isLayer2() ? 32 : 128);
        this.texHeight = (this.isLayer2() ? 32 : 128);
        (this.Head = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
        this.Head.texOffs(0, 15).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, 1.0f, false);
        this.Head.texOffs(36, 49).addBox(-4.0f, -14.0f, -6.0f, 8.0f, 15.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(18, 48).addBox(-4.0f, -14.0f, 5.0f, 8.0f, 15.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(0, 15).addBox(-5.0f, -14.0f, 4.0f, 1.0f, 5.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(8, 5).addBox(4.0f, -14.0f, 4.0f, 1.0f, 5.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(4, 5).addBox(4.0f, -14.0f, -5.0f, 1.0f, 5.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(0, 5).addBox(-5.0f, -14.0f, -5.0f, 1.0f, 5.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(0, 0).addBox(-4.0f, -15.0f, -7.0f, 8.0f, 1.0f, 14.0f, 0.0f, false);
        this.Head.texOffs(24, 17).addBox(-4.0f, -14.0f, -7.0f, 8.0f, 1.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(24, 15).addBox(-4.0f, -14.0f, 6.0f, 8.0f, 1.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(36, 36).addBox(-5.0f, -15.0f, -6.0f, 1.0f, 1.0f, 12.0f, 0.0f, false);
        this.Head.texOffs(30, 0).addBox(4.0f, -15.0f, -6.0f, 1.0f, 1.0f, 12.0f, 0.0f, false);
        this.Head.texOffs(0, 0).addBox(-0.5f, -17.0f, -2.0f, 1.0f, 1.0f, 4.0f, 0.0f, false);
        this.Head.texOffs(4, 15).addBox(-0.5f, -16.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(8, 11).addBox(-0.5f, -16.0f, -2.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(50, 29).addBox(-6.0f, -15.0f, -5.0f, 1.0f, 1.0f, 10.0f, 0.0f, false);
        this.Head.texOffs(4, 11).addBox(-6.0f, -14.0f, 4.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(10, 3).addBox(-5.0f, -14.0f, -6.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(6, 2).addBox(4.0f, -14.0f, -6.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(0, 0).addBox(4.0f, -14.0f, 5.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(9, 1).addBox(-6.0f, -14.0f, -5.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(6, 0).addBox(5.0f, -14.0f, -5.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(0, 2).addBox(5.0f, -14.0f, 4.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(0, 11).addBox(-5.0f, -14.0f, 5.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(44, 0).addBox(5.0f, -15.0f, -5.0f, 1.0f, 1.0f, 10.0f, 0.0f, false);
        this.Head.texOffs(62, 40).addBox(-7.0f, -15.0f, -4.0f, 1.0f, 1.0f, 8.0f, 0.0f, false);
        this.Head.texOffs(62, 30).addBox(6.0f, -15.0f, -4.0f, 1.0f, 1.0f, 8.0f, 0.0f, false);
        this.Head.texOffs(62, 21).addBox(6.0f, -14.0f, -4.0f, 1.0f, 1.0f, 8.0f, 0.0f, false);
        this.Head.texOffs(56, 0).addBox(-7.0f, -14.0f, -4.0f, 1.0f, 1.0f, 8.0f, 0.0f, false);
        final ModelRenderer cube_r1 = new ModelRenderer((Model)this);
        cube_r1.setPos(0.0f, 0.0f, 0.0f);
        this.Head.addChild(cube_r1);
        this.setRotationAngle(cube_r1, 0.0f, 1.5708f, 0.0f);
        cube_r1.texOffs(0, 48).addBox(-4.0f, -14.0f, -6.0f, 8.0f, 15.0f, 1.0f, 0.0f, false);
        cube_r1.texOffs(48, 13).addBox(-4.0f, -14.0f, 5.0f, 8.0f, 15.0f, 1.0f, 0.0f, false);
        (this.Body = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
        this.Body.texOffs(24, 32).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 1.01f, false);
        (this.RightArm = new ModelRenderer((Model)this)).setPos(-5.0f, 2.0f, 0.0f);
        this.RightArm.texOffs(32, 65).addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
        this.RightArm.texOffs(0, 31).addBox(-5.0f, -5.0f, -4.0f, 4.0f, 9.0f, 8.0f, 0.0f, false);
        (this.LeftArm = new ModelRenderer((Model)this)).setPos(5.0f, 2.0f, 0.0f);
        this.LeftArm.texOffs(16, 64).addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
        (this.RightBoot = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
        this.RightBoot.texOffs(0, 64).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
        this.RightBoot.texOffs(24, 23).addBox(-4.0f, 5.0f, -4.0f, 8.0f, 1.0f, 8.0f, 0.0f, false);
        (this.LeftBoot = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
        this.LeftBoot.texOffs(24, 23).addBox(-3.8f, 5.0f, -4.0f, 8.0f, 1.0f, 8.0f, 0.0f, false);
        this.LeftBoot.texOffs(54, 49).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
        (this.Belt = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
        this.Belt.texOffs(0, 0).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 0.51f, false);
        this.Belt.texOffs(12, 16).addBox(-3.0f, 10.0f, 2.0f, 3.0f, 3.0f, 1.0f, 0.0f, false);
        this.Belt.texOffs(19, 19).addBox(-1.0f, 13.0f, 2.0f, 1.0f, 6.0f, 1.0f, 0.0f, false);
        this.Belt.texOffs(0, 0).addBox(-2.5f, 13.0f, 2.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
        (this.RightLeg = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
        this.RightLeg.texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, false);
        (this.LeftLeg = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
        this.LeftLeg.texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, true);
    }
}
