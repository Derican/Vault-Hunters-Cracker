// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.item.gear.specials;

import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.inventory.EquipmentSlotType;
import iskallia.vault.item.gear.model.VaultGearModel;
import net.minecraft.entity.LivingEntity;

public class CreateArmorModel<T extends LivingEntity> extends VaultGearModel<T>
{
    public CreateArmorModel(final float modelSize, final EquipmentSlotType slotType) {
        super(modelSize, slotType);
        this.texWidth = (this.isLayer2() ? 64 : 128);
        this.texHeight = (this.isLayer2() ? 64 : 128);
        (this.Head = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
        this.Head.texOffs(0, 0).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, 1.0f, false);
        this.Head.texOffs(56, 39).addBox(-5.0f, -6.0f, -6.0f, 4.0f, 4.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(24, 19).addBox(-6.0f, -7.0f, -5.5f, 2.0f, 2.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(56, 34).addBox(1.0f, -6.0f, -6.0f, 4.0f, 4.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(0, 16).addBox(1.5f, -5.5f, -7.0f, 3.0f, 3.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(56, 29).addBox(-2.0f, -10.0f, -5.5f, 4.0f, 4.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(0, 20).addBox(-1.0f, -6.0f, -5.75f, 2.0f, 3.0f, 1.0f, 0.0f, false);
        (this.Body = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
        this.Body.texOffs(32, 29).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 1.01f, false);
        this.Body.texOffs(14, 55).addBox(-3.0f, 4.0f, -4.0f, 6.0f, 4.0f, 1.0f, 0.0f, false);
        this.Body.texOffs(58, 2).addBox(-2.0f, 8.0f, -4.0f, 4.0f, 1.0f, 1.0f, 0.0f, false);
        this.Body.texOffs(58, 0).addBox(-2.0f, 3.0f, -4.0f, 4.0f, 1.0f, 1.0f, 0.0f, false);
        this.Body.texOffs(16, 39).addBox(-0.5f, 5.5f, -4.5f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Body.texOffs(4, 28).addBox(-0.5f, 4.0f, -4.25f, 1.0f, 2.0f, 1.0f, 0.0f, false);
        this.Body.texOffs(24, 0).addBox(-4.0f, 5.0f, 3.0f, 8.0f, 4.0f, 1.0f, 0.0f, false);
        this.Body.texOffs(24, 17).addBox(-3.0f, 9.0f, 3.0f, 6.0f, 1.0f, 1.0f, 0.0f, false);
        this.Body.texOffs(24, 5).addBox(-3.0f, 4.0f, 3.0f, 6.0f, 1.0f, 1.0f, 0.0f, false);
        this.Body.texOffs(50, 13).addBox(-2.0f, 10.0f, 3.0f, 4.0f, 1.0f, 1.0f, 0.0f, false);
        this.Body.texOffs(37, 18).addBox(-2.0f, 3.0f, 3.0f, 4.0f, 1.0f, 1.0f, 0.0f, false);
        this.Body.texOffs(12, 39).addBox(-1.5f, 2.0f, 2.75f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Body.texOffs(24, 22).addBox(-1.5f, 11.0f, 2.75f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Body.texOffs(0, 39).addBox(0.5f, 2.0f, 2.75f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Body.texOffs(28, 22).addBox(0.5f, 11.0f, 2.75f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Body.texOffs(0, 34).addBox(4.0f, 5.5f, 2.75f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Body.texOffs(3, 33).addBox(4.0f, 7.25f, 2.75f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Body.texOffs(28, 32).addBox(-5.0f, 7.25f, 2.75f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Body.texOffs(24, 32).addBox(-5.0f, 5.5f, 2.75f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Body.texOffs(28, 1).addBox(-0.5f, 6.5f, 3.5f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        final ModelRenderer cube_r1 = new ModelRenderer((Model)this);
        cube_r1.setPos(3.0f, 2.5f, 5.25f);
        this.Body.addChild(cube_r1);
        this.setRotationAngle(cube_r1, 0.0f, 0.0f, 0.7854f);
        cube_r1.texOffs(24, 34).addBox(0.55f, 9.05f, -2.5f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        cube_r1.texOffs(3, 31).addBox(4.65f, 4.75f, -2.5f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        cube_r1.texOffs(0, 32).addBox(-3.6f, 5.0f, -2.5f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        cube_r1.texOffs(28, 34).addBox(0.65f, 0.75f, -2.5f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        final ModelRenderer cube_r2 = new ModelRenderer((Model)this);
        cube_r2.setPos(0.0f, 7.5f, -3.75f);
        this.Body.addChild(cube_r2);
        this.setRotationAngle(cube_r2, 0.0f, 0.0f, -0.7854f);
        cube_r2.texOffs(0, 28).addBox(0.5f, -1.25f, -0.5f, 1.0f, 3.0f, 1.0f, 0.0f, false);
        (this.RightArm = new ModelRenderer((Model)this)).setPos(-5.0f, 2.0f, 0.0f);
        this.RightArm.texOffs(48, 45).addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
        this.RightArm.texOffs(24, 29).addBox(-6.0f, -3.0f, 1.0f, 5.0f, 2.0f, 1.0f, 0.0f, false);
        this.RightArm.texOffs(37, 6).addBox(-6.0f, -8.0f, 1.0f, 5.0f, 1.0f, 1.0f, 0.0f, false);
        this.RightArm.texOffs(42, 0).addBox(-7.0f, -7.0f, 1.0f, 7.0f, 5.0f, 1.0f, 0.0f, false);
        this.RightArm.texOffs(50, 24).addBox(-5.0f, -7.0f, -3.0f, 4.0f, 1.0f, 1.0f, 0.0f, false);
        this.RightArm.texOffs(50, 26).addBox(-5.0f, -2.0f, -3.0f, 4.0f, 1.0f, 1.0f, 0.0f, false);
        this.RightArm.texOffs(0, 55).addBox(-6.0f, -6.0f, -3.0f, 6.0f, 4.0f, 1.0f, 0.0f, false);
        this.RightArm.texOffs(46, 17).addBox(-7.0f, -5.0f, -3.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.RightArm.texOffs(32, 45).addBox(-4.5f, -8.0f, -3.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.RightArm.texOffs(12, 41).addBox(-2.75f, -9.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.RightArm.texOffs(16, 41).addBox(-5.25f, -9.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.RightArm.texOffs(0, 41).addBox(-8.0f, -6.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.RightArm.texOffs(28, 39).addBox(-8.0f, -4.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.RightArm.texOffs(28, 41).addBox(-2.5f, -8.0f, -3.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.RightArm.texOffs(44, 45).addBox(-7.0f, -3.0f, -3.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        final ModelRenderer cube_r3 = new ModelRenderer((Model)this);
        cube_r3.setPos(-3.5f, -1.5f, 0.0f);
        this.RightArm.addChild(cube_r3);
        this.setRotationAngle(cube_r3, 0.0f, 0.0f, -0.4363f);
        cube_r3.texOffs(24, 20).addBox(-4.5f, 1.25f, -4.0f, 9.0f, 1.0f, 8.0f, 0.0f, false);
        cube_r3.texOffs(0, 28).addBox(-3.5f, -1.75f, -4.0f, 8.0f, 3.0f, 8.0f, 0.0f, false);
        (this.LeftArm = new ModelRenderer((Model)this)).setPos(5.0f, 2.0f, 0.0f);
        this.LeftArm.texOffs(32, 45).addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
        final ModelRenderer cube_r4 = new ModelRenderer((Model)this);
        cube_r4.setPos(1.5782f, -1.1195f, 0.0f);
        this.LeftArm.addChild(cube_r4);
        this.setRotationAngle(cube_r4, 0.0f, 0.0f, 0.4363f);
        cube_r4.texOffs(24, 8).addBox(-4.25f, 0.0f, -4.0f, 9.0f, 1.0f, 8.0f, 0.0f, false);
        cube_r4.texOffs(0, 16).addBox(-4.25f, -4.0f, -4.0f, 8.0f, 4.0f, 8.0f, 0.0f, false);
        (this.RightBoot = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
        this.RightBoot.texOffs(16, 39).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
        this.RightBoot.texOffs(0, 4).addBox(-4.0f, 11.0f, -2.0f, 1.0f, 1.0f, 3.0f, 0.0f, false);
        this.RightBoot.texOffs(50, 17).addBox(-5.0f, 9.0f, -1.0f, 2.0f, 2.0f, 5.0f, 0.0f, false);
        (this.LeftBoot = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
        this.LeftBoot.texOffs(0, 0).addBox(3.2f, 11.0f, -2.0f, 1.0f, 1.0f, 3.0f, 0.0f, false);
        this.LeftBoot.texOffs(50, 6).addBox(3.2f, 9.0f, -1.0f, 2.0f, 2.0f, 5.0f, 0.0f, false);
        this.LeftBoot.texOffs(0, 39).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
        (this.Belt = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
        this.Belt.texOffs(0, 0).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 0.51f, false);
        this.Belt.texOffs(20, 0).addBox(4.5f, 10.0f, -2.0f, 1.0f, 1.0f, 2.0f, 0.0f, false);
        this.Belt.texOffs(0, 0).addBox(4.5f, 11.0f, -1.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
        this.Belt.texOffs(12, 16).addBox(4.5f, 7.0f, -2.0f, 1.0f, 2.0f, 2.0f, 0.0f, false);
        this.Belt.texOffs(0, 16).addBox(4.5f, 9.0f, -1.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Belt.texOffs(24, 0).addBox(4.0f, 9.0f, 0.0f, 2.0f, 6.0f, 3.0f, 0.0f, false);
        (this.RightLeg = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
        this.RightLeg.texOffs(16, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, false);
        (this.LeftLeg = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
        this.LeftLeg.texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, false);
    }
}
