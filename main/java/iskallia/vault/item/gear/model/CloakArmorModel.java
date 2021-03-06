// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.item.gear.model;

import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.LivingEntity;

public class CloakArmorModel
{
    public static class Variant1<T extends LivingEntity> extends VaultGearModel<T>
    {
        public Variant1(final float modelSize, final EquipmentSlotType slotType) {
            super(modelSize, slotType);
            this.texWidth = (this.isLayer2() ? 32 : 128);
            this.texHeight = (this.isLayer2() ? 32 : 128);
            (this.Head = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Head.texOffs(0, 14).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, 1.0f, false);
            this.Head.texOffs(0, 19).addBox(-4.0f, 0.0f, -7.0f, 2.0f, 1.0f, 2.0f, 0.0f, false);
            this.Head.texOffs(0, 19).addBox(2.0f, 0.0f, -7.0f, 2.0f, 1.0f, 2.0f, 0.0f, false);
            this.Head.texOffs(24, 19).addBox(-5.0f, -1.0f, -7.0f, 1.0f, 1.0f, 2.0f, 0.0f, false);
            this.Head.texOffs(24, 19).addBox(4.0f, -1.0f, -7.0f, 1.0f, 1.0f, 2.0f, 0.0f, false);
            this.Head.texOffs(16, 46).addBox(-6.0f, -6.0f, -6.0f, 1.0f, 5.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(16, 46).addBox(5.0f, -6.0f, -6.0f, 1.0f, 5.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(0, 14).addBox(-5.0f, -9.0f, -7.0f, 1.0f, 3.0f, 2.0f, 0.0f, false);
            this.Head.texOffs(0, 14).addBox(4.0f, -9.0f, -7.0f, 1.0f, 3.0f, 2.0f, 0.0f, false);
            this.Head.texOffs(36, 33).addBox(-4.0f, -10.0f, -7.0f, 3.0f, 1.0f, 2.0f, 0.0f, false);
            this.Head.texOffs(24, 15).addBox(-1.5f, -11.0f, -8.0f, 3.0f, 1.0f, 3.0f, 0.0f, false);
            this.Head.texOffs(36, 33).addBox(1.0f, -10.0f, -7.0f, 3.0f, 1.0f, 2.0f, 0.0f, false);
            this.Head.texOffs(24, 33).addBox(5.0f, -6.0f, -5.0f, 1.0f, 5.0f, 10.0f, 0.0f, false);
            this.Head.texOffs(24, 33).addBox(-6.0f, -6.0f, -5.0f, 1.0f, 5.0f, 10.0f, 0.0f, false);
            this.Head.texOffs(26, 4).addBox(-4.0f, -10.0f, -5.0f, 3.0f, 1.0f, 10.0f, 0.0f, false);
            this.Head.texOffs(26, 4).addBox(1.0f, -10.0f, -5.0f, 3.0f, 1.0f, 10.0f, 0.0f, false);
            this.Head.texOffs(20, 20).addBox(-1.5f, -11.0f, -5.0f, 3.0f, 1.0f, 12.0f, 0.0f, false);
            this.Head.texOffs(36, 38).addBox(-3.5f, -11.0f, -5.0f, 2.0f, 1.0f, 10.0f, 0.0f, false);
            this.Head.texOffs(36, 38).addBox(1.5f, -11.0f, -5.0f, 2.0f, 1.0f, 10.0f, 0.0f, false);
            this.Head.texOffs(32, 49).addBox(-4.0f, -10.0f, 5.0f, 8.0f, 9.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(50, 50).addBox(-3.0f, -10.0f, 6.0f, 6.0f, 6.0f, 2.0f, 0.0f, false);
            this.Head.texOffs(29, 0).addBox(-2.0f, -10.0f, 8.0f, 4.0f, 3.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(33, 15).addBox(-2.0f, -10.0f, 9.0f, 4.0f, 2.0f, 1.0f, 0.0f, false);
            (this.Body = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Body.texOffs(0, 30).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 1.01f, false);
            this.Body.texOffs(0, 0).addBox(-5.5f, 0.0f, -3.6f, 11.0f, 7.0f, 7.0f, 0.0f, false);
            this.Body.texOffs(36, 36).addBox(-5.0f, 12.0f, -3.5f, 10.0f, 1.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r1 = new ModelRenderer((Model)this);
            cube_r1.setPos(-5.5f, 17.0f, 0.0f);
            this.Body.addChild(cube_r1);
            this.setRotationAngle(cube_r1, 0.0f, 0.0f, 0.4363f);
            cube_r1.texOffs(38, 15).addBox(-1.5f, -5.0f, -4.0f, 1.0f, 10.0f, 7.0f, 0.0f, false);
            final ModelRenderer cube_r2 = new ModelRenderer((Model)this);
            cube_r2.setPos(5.5f, 17.0f, -0.5f);
            this.Body.addChild(cube_r2);
            this.setRotationAngle(cube_r2, 0.0f, 0.0f, -0.4363f);
            cube_r2.texOffs(38, 15).addBox(0.5f, -5.0f, -3.5f, 1.0f, 10.0f, 7.0f, 0.0f, false);
            final ModelRenderer cube_r3 = new ModelRenderer((Model)this);
            cube_r3.setPos(0.0f, 17.0f, 3.5f);
            this.Body.addChild(cube_r3);
            this.setRotationAngle(cube_r3, 0.4363f, 0.0f, 0.0f);
            cube_r3.texOffs(42, 0).addBox(-5.0f, -5.0f, 0.5f, 10.0f, 10.0f, 1.0f, 0.0f, false);
            (this.RightArm = new ModelRenderer((Model)this)).setPos(-5.0f, 2.0f, 0.0f);
            this.RightArm.texOffs(0, 0).addBox(-3.0f, 8.0f, 2.75f, 2.0f, 4.0f, 1.0f, 0.0f, false);
            this.RightArm.texOffs(29, 4).addBox(-2.5f, 12.0f, 2.75f, 1.0f, 2.0f, 1.0f, 0.0f, false);
            this.RightArm.texOffs(24, 33).addBox(-4.0f, 4.0f, 3.0f, 4.0f, 4.0f, 1.0f, 0.0f, false);
            this.RightArm.texOffs(16, 48).addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            (this.LeftArm = new ModelRenderer((Model)this)).setPos(5.0f, 2.0f, 0.0f);
            this.LeftArm.texOffs(24, 33).addBox(0.0f, 4.0f, 3.0f, 4.0f, 4.0f, 1.0f, 0.0f, false);
            this.LeftArm.texOffs(16, 48).addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, true);
            this.LeftArm.texOffs(0, 0).addBox(1.0f, 8.0f, 2.75f, 2.0f, 4.0f, 1.0f, 0.0f, false);
            this.LeftArm.texOffs(29, 4).addBox(1.5f, 12.0f, 2.75f, 1.0f, 2.0f, 1.0f, 0.0f, false);
            (this.RightBoot = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightBoot.texOffs(0, 46).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            (this.LeftBoot = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftBoot.texOffs(0, 46).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, true);
            (this.Belt = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Belt.texOffs(0, 0).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 0.51f, false);
            (this.RightLeg = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightLeg.texOffs(16, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, false);
            (this.LeftLeg = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftLeg.texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, false);
        }
    }
    
    public static class Variant2<T extends LivingEntity> extends VaultGearModel<T>
    {
        public Variant2(final float modelSize, final EquipmentSlotType slotType) {
            super(modelSize, slotType);
            this.texWidth = (this.isLayer2() ? 32 : 128);
            this.texHeight = (this.isLayer2() ? 32 : 128);
            (this.Head = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Head.texOffs(36, 28).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, 1.0f, false);
            this.Head.texOffs(0, 0).addBox(-8.0f, -6.5f, -9.0f, 16.0f, 1.0f, 18.0f, 0.0f, false);
            this.Head.texOffs(0, 19).addBox(-5.5f, -12.5f, -5.5f, 11.0f, 6.0f, 11.0f, 0.0f, false);
            final ModelRenderer cube_r1 = new ModelRenderer((Model)this);
            cube_r1.setPos(0.0f, -19.9218f, 5.1644f);
            this.Head.addChild(cube_r1);
            this.setRotationAngle(cube_r1, -1.0036f, 0.0f, 0.0f);
            cube_r1.texOffs(0, 19).addBox(-1.0f, -0.8f, 0.0f, 2.0f, 2.0f, 2.0f, 0.0f, false);
            final ModelRenderer cube_r2 = new ModelRenderer((Model)this);
            cube_r2.setPos(0.0f, -20.0f, 2.25f);
            this.Head.addChild(cube_r2);
            this.setRotationAngle(cube_r2, -0.5672f, 0.0f, 0.0f);
            cube_r2.texOffs(42, 57).addBox(-2.5f, -0.5f, -0.5f, 5.0f, 5.0f, 5.0f, 0.0f, false);
            final ModelRenderer cube_r3 = new ModelRenderer((Model)this);
            cube_r3.setPos(0.0f, -15.0f, 0.25f);
            this.Head.addChild(cube_r3);
            this.setRotationAngle(cube_r3, -0.1745f, 0.0f, 0.0f);
            cube_r3.texOffs(40, 44).addBox(-4.0f, -1.5f, -3.25f, 8.0f, 5.0f, 8.0f, 0.0f, false);
            (this.Body = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Body.texOffs(50, 0).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 1.01f, false);
            this.Body.texOffs(0, 36).addBox(-6.0f, 8.0f, -4.0f, 12.0f, 12.0f, 8.0f, 0.0f, false);
            (this.RightArm = new ModelRenderer((Model)this)).setPos(-5.0f, 2.0f, 0.0f);
            this.RightArm.texOffs(26, 56).addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            this.RightArm.texOffs(0, 56).addBox(-5.0f, -4.0f, -4.0f, 5.0f, 5.0f, 8.0f, 0.0f, false);
            (this.LeftArm = new ModelRenderer((Model)this)).setPos(5.0f, 2.0f, 0.0f);
            this.LeftArm.texOffs(26, 56).addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, true);
            this.LeftArm.texOffs(0, 56).addBox(0.0f, -4.0f, -4.0f, 5.0f, 5.0f, 8.0f, 0.0f, false);
            (this.RightBoot = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightBoot.texOffs(0, 0).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            (this.LeftBoot = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftBoot.texOffs(0, 0).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, true);
            (this.Belt = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Belt.texOffs(0, 0).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 0.51f, false);
            (this.RightLeg = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightLeg.texOffs(16, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, false);
            (this.LeftLeg = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftLeg.texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, false);
        }
    }
    
    public static class Variant3<T extends LivingEntity> extends VaultGearModel<T>
    {
        public Variant3(final float modelSize, final EquipmentSlotType slotType) {
            super(modelSize, slotType);
            this.texWidth = (this.isLayer2() ? 32 : 128);
            this.texHeight = (this.isLayer2() ? 32 : 128);
            (this.Head = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Head.texOffs(0, 0).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, 1.0f, false);
            this.Head.texOffs(35, 30).addBox(-5.5f, -6.0f, -5.5f, 11.0f, 4.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(35, 25).addBox(-5.5f, -6.0f, 4.5f, 11.0f, 4.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(29, 7).addBox(4.5f, -6.0f, -4.5f, 1.0f, 4.0f, 9.0f, 0.0f, false);
            this.Head.texOffs(24, 26).addBox(-5.5f, -6.0f, -4.5f, 1.0f, 4.0f, 9.0f, 0.0f, false);
            this.Head.texOffs(36, 20).addBox(-2.0f, -5.5f, -6.0f, 4.0f, 3.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(0, 16).addBox(-1.0f, -5.0f, -6.25f, 2.0f, 2.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(0, 26).addBox(-3.0f, -5.0f, -6.0f, 1.0f, 2.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(0, 19).addBox(2.0f, -5.0f, -6.0f, 1.0f, 2.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(24, 3).addBox(-2.0f, -6.0f, 5.5f, 4.0f, 4.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r1 = new ModelRenderer((Model)this);
            cube_r1.setPos(1.0f, 0.5f, 5.75f);
            this.Head.addChild(cube_r1);
            this.setRotationAngle(cube_r1, 0.0f, 0.0f, -0.5236f);
            cube_r1.texOffs(0, 0).addBox(1.0f, -3.5f, 0.0f, 2.0f, 7.0f, 0.0f, 0.0f, false);
            final ModelRenderer cube_r2 = new ModelRenderer((Model)this);
            cube_r2.setPos(-1.0f, 0.5f, 5.75f);
            this.Head.addChild(cube_r2);
            this.setRotationAngle(cube_r2, 0.0f, 0.0f, 0.5236f);
            cube_r2.texOffs(4, 0).addBox(-3.0f, -3.5f, 0.0f, 2.0f, 7.0f, 0.0f, 0.0f, false);
            (this.Body = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Body.texOffs(0, 26).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 1.01f, false);
            this.Body.texOffs(0, 16).addBox(-5.5f, 10.0f, -3.5f, 11.0f, 3.0f, 7.0f, 0.0f, false);
            this.Body.texOffs(24, 0).addBox(-2.5f, 8.0f, -3.25f, 5.0f, 2.0f, 1.0f, 0.0f, false);
            this.Body.texOffs(20, 26).addBox(-2.5f, 8.0f, 2.25f, 5.0f, 2.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r3 = new ModelRenderer((Model)this);
            cube_r3.setPos(0.0f, 16.0f, 3.5f);
            this.Body.addChild(cube_r3);
            this.setRotationAngle(cube_r3, 0.1309f, 0.0f, 0.0f);
            cube_r3.texOffs(52, 35).addBox(-3.0f, -4.5f, -0.25f, 6.0f, 10.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r4 = new ModelRenderer((Model)this);
            cube_r4.setPos(0.0f, 16.3986f, -4.4074f);
            this.Body.addChild(cube_r4);
            this.setRotationAngle(cube_r4, -0.1745f, 0.0f, 0.0f);
            cube_r4.texOffs(52, 46).addBox(-3.0f, -5.0f, 0.0f, 6.0f, 10.0f, 1.0f, 0.0f, false);
            (this.RightArm = new ModelRenderer((Model)this)).setPos(-5.0f, 2.0f, 0.0f);
            this.RightArm.texOffs(0, 42).addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            (this.LeftArm = new ModelRenderer((Model)this)).setPos(5.0f, 2.0f, 0.0f);
            this.LeftArm.texOffs(40, 0).addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            (this.RightBoot = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightBoot.texOffs(36, 39).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            (this.LeftBoot = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftBoot.texOffs(20, 39).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            (this.Belt = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Belt.texOffs(0, 0).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 0.51f, false);
            (this.RightLeg = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightLeg.texOffs(16, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, false);
            (this.LeftLeg = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftLeg.texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, false);
        }
    }
    
    public static class Variant4<T extends LivingEntity> extends VaultGearModel<T>
    {
        public Variant4(final float modelSize, final EquipmentSlotType slotType) {
            super(modelSize, slotType);
            this.texWidth = (this.isLayer2() ? 32 : 128);
            this.texHeight = (this.isLayer2() ? 32 : 128);
            (this.Head = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Head.texOffs(0, 27).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, 1.0f, false);
            final ModelRenderer cube_r1 = new ModelRenderer((Model)this);
            cube_r1.setPos(-2.8378f, -9.7364f, 0.7885f);
            this.Head.addChild(cube_r1);
            this.setRotationAngle(cube_r1, -0.5672f, 0.0f, 0.0f);
            cube_r1.texOffs(10, 0).addBox(-4.4122f, -7.1054f, -1.654f, 0.0f, 5.0f, 1.0f, 0.0f, false);
            cube_r1.texOffs(6, 0).addBox(-4.4122f, -9.1054f, 3.346f, 0.0f, 3.0f, 1.0f, 0.0f, false);
            cube_r1.texOffs(0, 26).addBox(-4.4122f, -9.1054f, 2.346f, 0.0f, 5.0f, 1.0f, 0.0f, false);
            cube_r1.texOffs(8, 0).addBox(-4.4122f, -9.1054f, 1.346f, 0.0f, 6.0f, 1.0f, 0.0f, false);
            cube_r1.texOffs(48, 14).addBox(-4.4122f, -9.1054f, 0.346f, 0.0f, 9.0f, 1.0f, 0.0f, false);
            cube_r1.texOffs(46, 14).addBox(-4.4122f, -8.1054f, -0.654f, 0.0f, 10.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r2 = new ModelRenderer((Model)this);
            cube_r2.setPos(-2.8378f, -9.7364f, 0.7885f);
            this.Head.addChild(cube_r2);
            this.setRotationAngle(cube_r2, 0.1745f, 0.0f, 0.0f);
            cube_r2.texOffs(2, 5).addBox(-4.1622f, -1.7636f, -1.0385f, 1.0f, 1.0f, 4.0f, 0.0f, false);
            cube_r2.texOffs(0, 59).addBox(-4.1622f, -0.7636f, -3.0385f, 1.0f, 1.0f, 7.0f, 0.0f, false);
            cube_r2.texOffs(41, 42).addBox(-4.1622f, 0.2364f, -7.0385f, 1.0f, 2.0f, 12.0f, 0.0f, false);
            cube_r2.texOffs(40, 0).addBox(-1.1622f, -2.7636f, -5.0385f, 8.0f, 1.0f, 8.0f, 0.0f, false);
            cube_r2.texOffs(0, 13).addBox(-2.1622f, -1.7636f, -6.0385f, 10.0f, 4.0f, 10.0f, 0.0f, false);
            cube_r2.texOffs(60, 34).addBox(-1.1622f, 2.2364f, -11.0385f, 8.0f, 1.0f, 1.0f, 0.0f, false);
            cube_r2.texOffs(53, 32).addBox(-2.1622f, 2.2364f, -10.0385f, 10.0f, 1.0f, 1.0f, 0.0f, false);
            cube_r2.texOffs(53, 30).addBox(-2.1622f, 2.2364f, 5.9615f, 10.0f, 1.0f, 1.0f, 0.0f, false);
            cube_r2.texOffs(53, 28).addBox(-3.1622f, 2.2364f, -9.0385f, 12.0f, 1.0f, 1.0f, 0.0f, false);
            cube_r2.texOffs(30, 13).addBox(-4.1622f, 2.2364f, 4.9615f, 14.0f, 1.0f, 1.0f, 0.0f, false);
            cube_r2.texOffs(40, 9).addBox(-4.1622f, 2.2364f, -8.0385f, 14.0f, 1.0f, 1.0f, 0.0f, false);
            cube_r2.texOffs(0, 0).addBox(-4.1622f, 2.2364f, -7.0385f, 14.0f, 1.0f, 12.0f, 0.0f, false);
            final ModelRenderer cube_r3 = new ModelRenderer((Model)this);
            cube_r3.setPos(-2.8378f, -9.7364f, 0.7885f);
            this.Head.addChild(cube_r3);
            this.setRotationAngle(cube_r3, 0.1129f, 0.1334f, -0.8651f);
            cube_r3.texOffs(28, 15).addBox(3.8444f, 8.6166f, -7.0385f, 3.0f, 1.0f, 12.0f, 0.0f, false);
            (this.Body = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Body.texOffs(0, 43).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 1.01f, false);
            this.Body.texOffs(0, 17).addBox(-2.0f, 1.0f, -3.6f, 4.0f, 2.0f, 1.0f, 0.0f, false);
            this.Body.texOffs(24, 28).addBox(-3.0f, 3.0f, -4.0f, 6.0f, 4.0f, 1.0f, 0.0f, false);
            this.Body.texOffs(0, 10).addBox(-2.0f, 7.0f, -4.0f, 4.0f, 1.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r4 = new ModelRenderer((Model)this);
            cube_r4.setPos(0.0f, -2.75f, 4.4f);
            this.Body.addChild(cube_r4);
            this.setRotationAngle(cube_r4, 0.0f, 0.0f, -0.6981f);
            cube_r4.texOffs(8, 7).addBox(-7.0f, -2.25f, -0.4f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            cube_r4.texOffs(8, 7).addBox(-7.0f, -4.25f, -0.4f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            cube_r4.texOffs(8, 7).addBox(-2.0f, -4.25f, -0.4f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            cube_r4.texOffs(8, 7).addBox(-2.0f, -2.25f, -0.4f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            cube_r4.texOffs(0, 13).addBox(-6.0f, -4.25f, -0.4f, 4.0f, 3.0f, 1.0f, 0.0f, false);
            cube_r4.texOffs(0, 0).addBox(-5.0f, -3.25f, -1.4f, 2.0f, 8.0f, 1.0f, 0.0f, false);
            cube_r4.texOffs(56, 56).addBox(-7.0f, 4.75f, -1.4f, 6.0f, 8.0f, 2.0f, 0.0f, false);
            final ModelRenderer cube_r4_r1 = new ModelRenderer((Model)this);
            cube_r4_r1.setPos(0.0f, 0.0f, 0.0f);
            cube_r4.addChild(cube_r4_r1);
            this.setRotationAngle(cube_r4_r1, 0.0f, 1.5708f, 0.0f);
            cube_r4_r1.texOffs(17, 60).addBox(-0.75f, -3.25f, -5.0f, 0.1f, 15.0f, 2.0f, 0.0f, false);
            (this.RightArm = new ModelRenderer((Model)this)).setPos(-5.0f, 2.0f, 0.0f);
            this.RightArm.texOffs(40, 56).addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            this.RightArm.texOffs(25, 41).addBox(-4.5f, 2.0f, -3.5f, 7.0f, 6.0f, 7.0f, 0.0f, false);
            (this.LeftArm = new ModelRenderer((Model)this)).setPos(5.0f, 2.0f, 0.0f);
            this.LeftArm.texOffs(56, 37).addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            this.LeftArm.texOffs(32, 28).addBox(-2.5f, 2.0f, -3.5f, 7.0f, 6.0f, 7.0f, 0.0f, false);
            (this.RightBoot = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightBoot.texOffs(56, 11).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            (this.LeftBoot = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftBoot.texOffs(24, 54).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            (this.Belt = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Belt.texOffs(0, 0).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 0.51f, false);
            (this.RightLeg = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightLeg.texOffs(16, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, false);
            (this.LeftLeg = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftLeg.texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, false);
        }
    }
}
