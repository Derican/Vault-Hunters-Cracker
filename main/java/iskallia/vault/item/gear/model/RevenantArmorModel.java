// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.item.gear.model;

import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.LivingEntity;

public class RevenantArmorModel
{
    public static class Variant1<T extends LivingEntity> extends VaultGearModel<T>
    {
        public Variant1(final float modelSize, final EquipmentSlotType slotType) {
            super(modelSize, slotType);
            this.texWidth = (this.isLayer2() ? 64 : 128);
            this.texHeight = (this.isLayer2() ? 64 : 128);
            (this.Head = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Head.texOffs(0, 0).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, 1.0f, false);
            this.Head.texOffs(8, 63).addBox(-1.0f, -6.0f, -6.0f, 2.0f, 5.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(62, 49).addBox(-2.0f, -12.0f, -6.0f, 4.0f, 6.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r1 = new ModelRenderer((Model)this);
            cube_r1.setPos(0.0f, -11.0f, 0.0f);
            this.Head.addChild(cube_r1);
            this.setRotationAngle(cube_r1, 0.4363f, 0.0f, 0.0f);
            cube_r1.texOffs(52, 61).addBox(-7.0f, -8.0f, -3.0f, 1.0f, 3.0f, 2.0f, 0.0f, false);
            cube_r1.texOffs(44, 61).addBox(-8.0f, -5.0f, -3.0f, 2.0f, 7.0f, 2.0f, 0.0f, false);
            cube_r1.texOffs(24, 0).addBox(-9.0f, 2.0f, -3.0f, 3.0f, 2.0f, 2.0f, 0.0f, false);
            cube_r1.texOffs(20, 16).addBox(-8.0f, 4.0f, -3.0f, 3.0f, 2.0f, 2.0f, 0.0f, false);
            cube_r1.texOffs(12, 67).addBox(6.0f, -8.0f, -3.0f, 1.0f, 3.0f, 2.0f, 0.0f, false);
            cube_r1.texOffs(0, 63).addBox(6.0f, -5.0f, -3.0f, 2.0f, 7.0f, 2.0f, 0.0f, false);
            cube_r1.texOffs(40, 27).addBox(6.0f, 2.0f, -3.0f, 3.0f, 2.0f, 2.0f, 0.0f, false);
            cube_r1.texOffs(44, 11).addBox(5.0f, 4.0f, -3.0f, 3.0f, 2.0f, 2.0f, 0.0f, false);
            (this.Body = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Body.texOffs(0, 16).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 1.01f, false);
            this.Body.texOffs(60, 0).addBox(-2.0f, 5.0f, -4.0f, 4.0f, 7.0f, 1.0f, 0.0f, false);
            this.Body.texOffs(46, 15).addBox(-3.0f, 5.0f, -3.5f, 2.0f, 5.0f, 1.0f, 0.0f, false);
            this.Body.texOffs(0, 0).addBox(1.0f, 5.0f, -3.5f, 2.0f, 5.0f, 1.0f, 0.0f, false);
            this.Body.texOffs(0, 32).addBox(-3.0f, 5.0f, 3.0f, 6.0f, 4.0f, 2.0f, 0.0f, false);
            final ModelRenderer cube_r2 = new ModelRenderer((Model)this);
            cube_r2.setPos(0.0f, 11.0f, 3.5f);
            this.Body.addChild(cube_r2);
            this.setRotationAngle(cube_r2, -0.5236f, 0.0f, 0.0f);
            cube_r2.texOffs(16, 47).addBox(-2.0f, -2.0f, -1.5f, 4.0f, 4.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r3 = new ModelRenderer((Model)this);
            cube_r3.setPos(0.0f, 3.0f, 4.5f);
            this.Body.addChild(cube_r3);
            this.setRotationAngle(cube_r3, 0.6109f, 0.0f, 0.0f);
            cube_r3.texOffs(0, 38).addBox(-4.0f, -2.0f, -3.5f, 8.0f, 4.0f, 5.0f, 0.0f, false);
            final ModelRenderer cube_r4 = new ModelRenderer((Model)this);
            cube_r4.setPos(0.0f, 5.5f, -2.0f);
            this.Body.addChild(cube_r4);
            this.setRotationAngle(cube_r4, 0.48f, 0.0f, 0.0f);
            cube_r4.texOffs(32, 0).addBox(-5.0f, -4.5f, -2.0f, 10.0f, 7.0f, 4.0f, 0.0f, false);
            (this.RightArm = new ModelRenderer((Model)this)).setPos(-5.0f, 2.0f, 0.0f);
            this.RightArm.texOffs(16, 52).addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            this.RightArm.texOffs(26, 38).addBox(-5.0f, 5.0f, -3.5f, 3.0f, 7.0f, 7.0f, 0.0f, false);
            final ModelRenderer cube_r5 = new ModelRenderer((Model)this);
            cube_r5.setPos(-7.4444f, -1.5f, 0.0f);
            this.RightArm.addChild(cube_r5);
            this.setRotationAngle(cube_r5, 0.0f, 0.0f, -0.5236f);
            cube_r5.texOffs(58, 44).addBox(2.4444f, 0.4f, 3.0f, 5.0f, 4.0f, 1.0f, 0.0f, false);
            cube_r5.texOffs(68, 13).addBox(-3.5556f, 2.4f, 3.0f, 2.0f, 2.0f, 1.0f, 0.0f, false);
            cube_r5.texOffs(40, 31).addBox(-5.5556f, 2.4f, 3.0f, 2.0f, 1.0f, 1.0f, 0.0f, false);
            cube_r5.texOffs(52, 66).addBox(-1.5556f, 1.4f, 3.0f, 4.0f, 4.0f, 1.0f, 0.0f, false);
            cube_r5.texOffs(26, 43).addBox(-5.5556f, 2.4f, -4.0f, 2.0f, 1.0f, 1.0f, 0.0f, false);
            cube_r5.texOffs(68, 16).addBox(-3.5556f, 2.4f, -4.0f, 2.0f, 2.0f, 1.0f, 0.0f, false);
            cube_r5.texOffs(62, 66).addBox(-1.5556f, 1.4f, -4.0f, 4.0f, 4.0f, 1.0f, 0.0f, false);
            cube_r5.texOffs(32, 60).addBox(2.4444f, 0.4f, -4.0f, 5.0f, 4.0f, 1.0f, 0.0f, false);
            cube_r5.texOffs(58, 57).addBox(-1.5556f, -1.6f, -2.0f, 7.0f, 5.0f, 4.0f, 0.0f, false);
            cube_r5.texOffs(18, 27).addBox(-0.5556f, -0.6f, -3.0f, 8.0f, 5.0f, 6.0f, 0.0f, false);
            (this.LeftArm = new ModelRenderer((Model)this)).setPos(5.0f, 2.0f, 0.0f);
            this.LeftArm.texOffs(52, 11).addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            this.LeftArm.texOffs(39, 31).addBox(2.0f, 5.0f, -3.5f, 3.0f, 7.0f, 7.0f, 0.0f, false);
            final ModelRenderer cube_r6 = new ModelRenderer((Model)this);
            cube_r6.setPos(7.3333f, -1.5f, 0.0f);
            this.LeftArm.addChild(cube_r6);
            this.setRotationAngle(cube_r6, 0.0f, 0.0f, 0.5236f);
            cube_r6.texOffs(52, 27).addBox(-5.5833f, -1.5f, -2.0f, 7.0f, 5.0f, 4.0f, 0.0f, false);
            cube_r6.texOffs(24, 16).addBox(-7.5833f, -0.5f, -3.0f, 8.0f, 5.0f, 6.0f, 0.0f, false);
            cube_r6.texOffs(32, 11).addBox(-7.5833f, 0.5f, -4.0f, 5.0f, 4.0f, 1.0f, 0.0f, false);
            cube_r6.texOffs(21, 38).addBox(-7.5833f, 0.5f, 3.0f, 5.0f, 4.0f, 1.0f, 0.0f, false);
            cube_r6.texOffs(64, 8).addBox(-2.5833f, 1.5f, 3.0f, 4.0f, 4.0f, 1.0f, 0.0f, false);
            cube_r6.texOffs(32, 65).addBox(-2.5833f, 1.5f, -4.0f, 4.0f, 4.0f, 1.0f, 0.0f, false);
            cube_r6.texOffs(24, 4).addBox(1.4167f, 2.5f, -4.0f, 2.0f, 2.0f, 1.0f, 0.0f, false);
            cube_r6.texOffs(0, 6).addBox(3.4167f, 2.5f, -4.0f, 2.0f, 1.0f, 1.0f, 0.0f, false);
            cube_r6.texOffs(50, 27).addBox(1.4167f, 2.5f, 3.0f, 2.0f, 2.0f, 1.0f, 0.0f, false);
            cube_r6.texOffs(24, 20).addBox(3.4167f, 2.5f, 3.0f, 2.0f, 1.0f, 1.0f, 0.0f, false);
            (this.RightBoot = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightBoot.texOffs(0, 47).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            this.RightBoot.texOffs(59, 36).addBox(-2.0f, 6.0f, -4.0f, 4.0f, 7.0f, 1.0f, 0.0f, false);
            (this.LeftBoot = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftBoot.texOffs(46, 45).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            this.LeftBoot.texOffs(32, 52).addBox(-1.8f, 6.0f, -4.0f, 4.0f, 7.0f, 1.0f, 0.0f, false);
            (this.Belt = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Belt.texOffs(0, 8).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 0.51f, false);
            this.Belt.texOffs(24, 8).addBox(-1.0f, 10.0f, -4.0f, 2.0f, 6.0f, 1.0f, 0.0f, false);
            this.Belt.texOffs(26, 3).addBox(-2.0f, 11.0f, -4.0f, 1.0f, 2.0f, 1.0f, 0.0f, false);
            this.Belt.texOffs(26, 0).addBox(1.0f, 11.0f, -4.0f, 1.0f, 2.0f, 1.0f, 0.0f, false);
            this.Belt.texOffs(34, 7).addBox(-3.0f, 9.0f, -4.0f, 1.0f, 6.0f, 1.0f, 0.0f, false);
            this.Belt.texOffs(24, 15).addBox(-4.0f, 10.0f, -4.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            this.Belt.texOffs(0, 24).addBox(-6.25f, 10.0f, 1.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            this.Belt.texOffs(32, 0).addBox(-5.25f, 9.0f, 1.0f, 1.0f, 6.0f, 1.0f, 0.0f, false);
            this.Belt.texOffs(16, 31).addBox(-5.25f, 9.0f, -2.0f, 1.0f, 6.0f, 1.0f, 0.0f, false);
            this.Belt.texOffs(20, 8).addBox(-6.25f, 10.0f, -2.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            this.Belt.texOffs(0, 0).addBox(5.25f, 10.0f, -2.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            this.Belt.texOffs(0, 8).addBox(5.25f, 10.0f, 1.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            this.Belt.texOffs(16, 24).addBox(4.25f, 9.0f, -2.0f, 1.0f, 6.0f, 1.0f, 0.0f, false);
            this.Belt.texOffs(30, 8).addBox(4.25f, 9.0f, 1.0f, 1.0f, 6.0f, 1.0f, 0.0f, false);
            this.Belt.texOffs(12, 24).addBox(3.0f, 10.0f, -4.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            this.Belt.texOffs(32, 15).addBox(2.0f, 9.0f, -4.0f, 1.0f, 6.0f, 1.0f, 0.0f, false);
            this.Belt.texOffs(0, 0).addBox(-5.0f, 11.0f, -3.0f, 10.0f, 2.0f, 6.0f, 0.0f, false);
            (this.RightLeg = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightLeg.texOffs(0, 24).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, false);
            (this.LeftLeg = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftLeg.texOffs(20, 20).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, false);
        }
    }
    
    public static class Variant2<T extends LivingEntity> extends VaultGearModel<T>
    {
        public Variant2(final float modelSize, final EquipmentSlotType slotType) {
            super(modelSize, slotType);
            this.texWidth = (this.isLayer2() ? 64 : 128);
            this.texHeight = (this.isLayer2() ? 64 : 128);
            (this.Head = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Head.texOffs(0, 28).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, 1.0f, false);
            this.Head.texOffs(0, 28).addBox(-1.0f, -6.0f, -6.0f, 2.0f, 5.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(62, 42).addBox(-2.0f, -12.0f, -6.0f, 4.0f, 6.0f, 1.0f, 0.0f, false);
            (this.Body = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Body.texOffs(32, 34).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 1.01f, false);
            this.Body.texOffs(32, 59).addBox(-2.0f, 5.0f, -4.0f, 4.0f, 7.0f, 1.0f, 0.0f, false);
            this.Body.texOffs(0, 14).addBox(-3.0f, 5.0f, -3.5f, 2.0f, 5.0f, 1.0f, 0.0f, false);
            this.Body.texOffs(0, 0).addBox(1.0f, 5.0f, -3.5f, 2.0f, 5.0f, 1.0f, 0.0f, false);
            this.Body.texOffs(28, 0).addBox(-3.0f, 5.0f, 3.0f, 6.0f, 4.0f, 2.0f, 0.0f, false);
            final ModelRenderer cube_r1 = new ModelRenderer((Model)this);
            cube_r1.setPos(0.0f, 11.0f, 3.5f);
            this.Body.addChild(cube_r1);
            this.setRotationAngle(cube_r1, -0.5236f, 0.0f, 0.0f);
            cube_r1.texOffs(61, 64).addBox(-2.0f, -2.0f, -1.5f, 4.0f, 4.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r2 = new ModelRenderer((Model)this);
            cube_r2.setPos(0.0f, 3.0f, 4.5f);
            this.Body.addChild(cube_r2);
            this.setRotationAngle(cube_r2, 0.6109f, 0.0f, 0.0f);
            cube_r2.texOffs(23, 50).addBox(-4.0f, -2.0f, -3.5f, 8.0f, 4.0f, 5.0f, 0.0f, false);
            final ModelRenderer cube_r3 = new ModelRenderer((Model)this);
            cube_r3.setPos(0.0f, 5.5f, -2.0f);
            this.Body.addChild(cube_r3);
            this.setRotationAngle(cube_r3, 0.48f, 0.0f, 0.0f);
            cube_r3.texOffs(0, 44).addBox(-5.0f, -4.5f, -2.0f, 10.0f, 7.0f, 4.0f, 0.0f, false);
            (this.RightArm = new ModelRenderer((Model)this)).setPos(-5.0f, 2.0f, 0.0f);
            this.RightArm.texOffs(60, 14).addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            this.RightArm.texOffs(49, 43).addBox(-5.0f, 5.0f, -3.5f, 3.0f, 7.0f, 7.0f, 0.0f, false);
            final ModelRenderer cube_r4 = new ModelRenderer((Model)this);
            cube_r4.setPos(-7.4444f, -1.5f, 0.0f);
            this.RightArm.addChild(cube_r4);
            this.setRotationAngle(cube_r4, 0.0f, 0.0f, -0.5236f);
            cube_r4.texOffs(30, 22).addBox(-3.5556f, -2.6f, -3.25f, 9.0f, 6.0f, 6.0f, 0.0f, false);
            cube_r4.texOffs(0, 14).addBox(-2.5556f, -1.6f, -4.25f, 10.0f, 6.0f, 8.0f, 0.0f, false);
            (this.LeftArm = new ModelRenderer((Model)this)).setPos(5.0f, 2.0f, 0.0f);
            this.LeftArm.texOffs(16, 59).addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            this.LeftArm.texOffs(54, 0).addBox(2.0f, 5.0f, -3.5f, 3.0f, 7.0f, 7.0f, 0.0f, false);
            final ModelRenderer cube_r5 = new ModelRenderer((Model)this);
            cube_r5.setPos(7.3333f, -1.5f, 0.0f);
            this.LeftArm.addChild(cube_r5);
            this.setRotationAngle(cube_r5, 0.0f, 0.0f, 0.5236f);
            cube_r5.texOffs(30, 8).addBox(-5.5833f, -2.5f, -3.25f, 9.0f, 6.0f, 6.0f, 0.0f, false);
            cube_r5.texOffs(0, 0).addBox(-7.5833f, -1.5f, -4.25f, 10.0f, 6.0f, 8.0f, 0.0f, false);
            (this.RightBoot = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightBoot.texOffs(45, 57).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            this.RightBoot.texOffs(56, 34).addBox(-2.0f, 6.0f, -4.0f, 4.0f, 7.0f, 1.0f, 0.0f, false);
            (this.LeftBoot = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftBoot.texOffs(0, 55).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            this.LeftBoot.texOffs(44, 0).addBox(-1.8f, 6.0f, -4.0f, 4.0f, 7.0f, 1.0f, 0.0f, false);
            (this.Belt = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Belt.texOffs(0, 8).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 0.51f, false);
            this.Belt.texOffs(24, 8).addBox(-1.0f, 10.0f, -4.0f, 2.0f, 6.0f, 1.0f, 0.0f, false);
            this.Belt.texOffs(19, 0).addBox(-2.0f, 9.0f, -4.0f, 1.0f, 7.0f, 1.0f, 0.0f, false);
            this.Belt.texOffs(20, 0).addBox(1.0f, 9.0f, -4.0f, 1.0f, 7.0f, 1.0f, 0.0f, false);
            this.Belt.texOffs(34, 7).addBox(-3.0f, 9.0f, -4.0f, 1.0f, 6.0f, 1.0f, 0.0f, false);
            this.Belt.texOffs(24, 15).addBox(-4.0f, 10.0f, -4.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            this.Belt.texOffs(0, 24).addBox(-6.25f, 10.0f, 1.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            this.Belt.texOffs(32, 0).addBox(-5.25f, 9.0f, 1.0f, 1.0f, 6.0f, 1.0f, 0.0f, false);
            this.Belt.texOffs(16, 31).addBox(-5.25f, 9.0f, -2.0f, 1.0f, 6.0f, 1.0f, 0.0f, false);
            this.Belt.texOffs(20, 8).addBox(-6.25f, 10.0f, -2.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            this.Belt.texOffs(0, 0).addBox(5.25f, 10.0f, -2.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            this.Belt.texOffs(0, 8).addBox(5.25f, 10.0f, 1.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            this.Belt.texOffs(16, 24).addBox(4.25f, 9.0f, -2.0f, 1.0f, 6.0f, 1.0f, 0.0f, false);
            this.Belt.texOffs(30, 8).addBox(4.25f, 9.0f, 1.0f, 1.0f, 6.0f, 1.0f, 0.0f, false);
            this.Belt.texOffs(12, 24).addBox(3.0f, 10.0f, -4.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            this.Belt.texOffs(32, 15).addBox(2.0f, 9.0f, -4.0f, 1.0f, 6.0f, 1.0f, 0.0f, false);
            this.Belt.texOffs(0, 0).addBox(-5.0f, 11.0f, -3.0f, 10.0f, 2.0f, 6.0f, 0.0f, false);
            (this.RightLeg = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightLeg.texOffs(0, 24).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, false);
            (this.LeftLeg = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftLeg.texOffs(20, 20).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, false);
        }
    }
}
