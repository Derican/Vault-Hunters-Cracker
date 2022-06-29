// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.item.gear.specials;

import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.inventory.EquipmentSlotType;
import iskallia.vault.item.gear.model.VaultGearModel;
import net.minecraft.entity.LivingEntity;

public class ThermalArmorModel<T extends LivingEntity> extends VaultGearModel<T>
{
    public ThermalArmorModel(final float modelSize, final EquipmentSlotType slotType) {
        super(modelSize, slotType);
        this.texWidth = (this.isLayer2() ? 64 : 128);
        this.texHeight = (this.isLayer2() ? 32 : 128);
        (this.Head = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
        this.Head.texOffs(64, 49).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, 1.0f, false);
        this.Head.texOffs(76, 19).addBox(-3.0f, -10.0f, -3.0f, 6.0f, 1.0f, 6.0f, 0.0f, false);
        this.Head.texOffs(88, 47).addBox(-5.0f, -9.0f, -6.0f, 10.0f, 4.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(18, 69).addBox(-5.0f, -9.0f, 5.0f, 10.0f, 5.0f, 1.0f, 0.0f, false);
        (this.Body = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
        this.Body.texOffs(16, 92).addBox(1.5f, -7.0f, 7.0f, 4.0f, 1.0f, 4.0f, 0.0f, false);
        this.Body.texOffs(88, 52).addBox(-5.5f, -7.0f, 7.0f, 4.0f, 1.0f, 4.0f, 0.0f, false);
        this.Body.texOffs(24, 76).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 1.01f, false);
        this.Body.texOffs(32, 12).addBox(-5.0f, -4.0f, -6.0f, 10.0f, 9.0f, 12.0f, 0.0f, false);
        this.Body.texOffs(64, 19).addBox(-4.0f, 5.0f, -4.0f, 8.0f, 3.0f, 1.0f, 0.0f, false);
        this.Body.texOffs(32, 92).addBox(-3.0f, 5.0f, 3.0f, 6.0f, 3.0f, 2.0f, 0.0f, false);
        this.Body.texOffs(70, 27).addBox(-6.5f, -6.0f, 6.0f, 6.0f, 14.0f, 6.0f, 0.0f, false);
        this.Body.texOffs(0, 69).addBox(0.5f, -6.0f, 6.0f, 6.0f, 14.0f, 6.0f, 0.0f, false);
        this.Body.texOffs(6, 11).addBox(2.5f, 7.75f, 8.25f, 2.0f, 1.0f, 2.0f, 0.0f, false);
        this.Body.texOffs(30, 33).addBox(3.0f, 8.75f, 8.75f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Body.texOffs(68, 78).addBox(3.0f, 9.75f, 2.75f, 1.0f, 1.0f, 7.0f, 0.0f, false);
        this.Body.texOffs(60, 40).addBox(-4.0f, 9.75f, 2.75f, 1.0f, 1.0f, 7.0f, 0.0f, false);
        this.Body.texOffs(30, 12).addBox(-4.0f, 8.75f, 8.75f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Body.texOffs(0, 10).addBox(-4.5f, 7.75f, 8.25f, 2.0f, 1.0f, 2.0f, 0.0f, false);
        final ModelRenderer cube_r1 = new ModelRenderer((Model)this);
        cube_r1.setPos(0.0f, 8.5f, 3.5f);
        this.Body.addChild(cube_r1);
        this.setRotationAngle(cube_r1, -0.3927f, 0.0f, 0.0f);
        cube_r1.texOffs(34, 0).addBox(-1.0f, -1.5f, -0.5f, 2.0f, 5.0f, 1.0f, 0.0f, false);
        final ModelRenderer cube_r2 = new ModelRenderer((Model)this);
        cube_r2.setPos(0.0f, 7.6455f, -3.1695f);
        this.Body.addChild(cube_r2);
        this.setRotationAngle(cube_r2, 0.3491f, 0.0f, 0.0f);
        cube_r2.texOffs(0, 0).addBox(-2.0f, -3.5f, -1.5f, 4.0f, 7.0f, 3.0f, 0.0f, false);
        (this.RightArm = new ModelRenderer((Model)this)).setPos(-5.0f, 2.0f, 0.0f);
        this.RightArm.texOffs(0, 89).addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
        this.RightArm.texOffs(0, 24).addBox(-8.0f, -8.0f, -7.0f, 8.0f, 10.0f, 14.0f, 0.0f, false);
        this.RightArm.texOffs(0, 48).addBox(-8.0f, 4.0f, -6.0f, 8.0f, 9.0f, 12.0f, 0.0f, false);
        this.RightArm.texOffs(76, 78).addBox(-6.0f, -9.0f, -4.0f, 6.0f, 1.0f, 8.0f, 0.0f, false);
        this.RightArm.texOffs(88, 0).addBox(-5.0f, -10.0f, -3.0f, 5.0f, 1.0f, 6.0f, 0.0f, false);
        this.RightArm.texOffs(62, 66).addBox(-7.0f, 2.0f, -5.0f, 6.0f, 2.0f, 10.0f, 0.0f, false);
        this.RightArm.texOffs(30, 35).addBox(-9.0f, -1.0f, 2.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.RightArm.texOffs(30, 0).addBox(-10.0f, -1.0f, 2.0f, 1.0f, 9.0f, 1.0f, 0.0f, false);
        this.RightArm.texOffs(33, 34).addBox(-9.0f, 7.0f, 2.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.RightArm.texOffs(34, 8).addBox(-9.0f, 7.0f, -3.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.RightArm.texOffs(8, 24).addBox(-10.0f, -1.0f, -3.0f, 1.0f, 9.0f, 1.0f, 0.0f, false);
        this.RightArm.texOffs(34, 12).addBox(-9.0f, -1.0f, -3.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        (this.LeftArm = new ModelRenderer((Model)this)).setPos(5.0f, 2.0f, 0.0f);
        this.LeftArm.texOffs(30, 0).addBox(1.0f, 2.0f, -5.0f, 6.0f, 2.0f, 10.0f, 0.0f, false);
        this.LeftArm.texOffs(32, 36).addBox(0.0f, 4.0f, -6.0f, 8.0f, 9.0f, 12.0f, 0.0f, false);
        this.LeftArm.texOffs(48, 78).addBox(0.0f, -9.0f, -4.0f, 6.0f, 1.0f, 8.0f, 0.0f, false);
        this.LeftArm.texOffs(0, 0).addBox(0.0f, -8.0f, -7.0f, 8.0f, 10.0f, 14.0f, 0.0f, false);
        this.LeftArm.texOffs(80, 87).addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
        this.LeftArm.texOffs(84, 65).addBox(0.0f, -10.0f, -3.0f, 5.0f, 1.0f, 6.0f, 0.0f, false);
        this.LeftArm.texOffs(8, 34).addBox(8.0f, -1.0f, -3.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.LeftArm.texOffs(34, 6).addBox(8.0f, 7.0f, -3.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.LeftArm.texOffs(4, 24).addBox(9.0f, -1.0f, -3.0f, 1.0f, 9.0f, 1.0f, 0.0f, false);
        this.LeftArm.texOffs(0, 34).addBox(8.0f, -1.0f, 2.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.LeftArm.texOffs(0, 24).addBox(9.0f, -1.0f, 2.0f, 1.0f, 9.0f, 1.0f, 0.0f, false);
        this.LeftArm.texOffs(4, 34).addBox(8.0f, 7.0f, 2.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        (this.RightBoot = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
        this.RightBoot.texOffs(64, 87).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
        this.RightBoot.texOffs(64, 0).addBox(-4.0f, 2.0f, -4.0f, 8.0f, 11.0f, 8.0f, 0.0f, false);
        this.RightBoot.texOffs(52, 0).addBox(-3.95f, 8.0f, -6.0f, 8.0f, 5.0f, 2.0f, 0.0f, false);
        (this.LeftBoot = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
        this.LeftBoot.texOffs(48, 87).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
        this.LeftBoot.texOffs(40, 57).addBox(-3.8f, 2.0f, -4.0f, 8.0f, 11.0f, 8.0f, 0.0f, false);
        this.LeftBoot.texOffs(88, 26).addBox(-3.75f, 8.0f, -6.0f, 8.0f, 5.0f, 2.0f, 0.0f, false);
        (this.Belt = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
        this.Belt.texOffs(16, 16).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 0.51f, false);
        (this.RightLeg = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
        this.RightLeg.texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, false);
        (this.LeftLeg = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
        this.LeftLeg.texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, true);
    }
}
