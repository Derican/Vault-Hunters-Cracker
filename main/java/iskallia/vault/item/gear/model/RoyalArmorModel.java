// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.item.gear.model;

import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.LivingEntity;

public class RoyalArmorModel
{
    public static class Variant1<T extends LivingEntity> extends VaultGearModel<T>
    {
        public Variant1(final float modelSize, final EquipmentSlotType slotType) {
            super(modelSize, slotType);
            this.texWidth = (this.isLayer2() ? 64 : 128);
            this.texHeight = (this.isLayer2() ? 32 : 128);
            (this.Head = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Head.texOffs(0, 17).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, 1.0f, false);
            this.Head.texOffs(40, 50).addBox(-3.0f, -6.25f, -5.0f, 6.0f, 2.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(28, 52).addBox(-2.0f, -7.25f, -5.0f, 4.0f, 1.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(28, 50).addBox(-2.0f, -7.25f, 4.0f, 4.0f, 1.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(0, 17).addBox(-1.0f, -8.25f, -5.0f, 2.0f, 1.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(0, 6).addBox(-1.0f, -8.25f, 4.0f, 2.0f, 1.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(36, 0).addBox(-3.0f, -6.25f, 4.0f, 6.0f, 2.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(32, 50).addBox(-5.0f, -6.25f, -3.0f, 1.0f, 2.0f, 6.0f, 0.0f, false);
            this.Head.texOffs(28, 0).addBox(4.0f, -6.25f, -3.0f, 1.0f, 2.0f, 6.0f, 0.0f, false);
            this.Head.texOffs(28, 3).addBox(-4.0f, -6.25f, -4.0f, 1.0f, 2.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(28, 0).addBox(-4.0f, -6.25f, 3.0f, 1.0f, 2.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(24, 20).addBox(3.0f, -6.25f, 3.0f, 1.0f, 2.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(24, 17).addBox(3.0f, -6.25f, -4.0f, 1.0f, 2.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(60, 32).addBox(-5.0f, -7.25f, -2.0f, 1.0f, 1.0f, 4.0f, 0.0f, false);
            this.Head.texOffs(0, 3).addBox(-5.0f, -8.25f, -1.0f, 1.0f, 1.0f, 2.0f, 0.0f, false);
            this.Head.texOffs(38, 4).addBox(4.0f, -7.25f, -2.0f, 1.0f, 1.0f, 4.0f, 0.0f, false);
            this.Head.texOffs(0, 0).addBox(4.0f, -8.25f, -1.0f, 1.0f, 1.0f, 2.0f, 0.0f, false);
            this.Head.texOffs(27, 45).addBox(-2.2f, -5.75f, -5.25f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(27, 41).addBox(-2.2f, -5.75f, 4.15f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(24, 44).addBox(1.2f, -5.75f, -5.25f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(24, 40).addBox(1.2f, -5.75f, 4.15f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(4, 22).addBox(-0.4f, -6.75f, -5.25f, 1.0f, 2.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(0, 19).addBox(-0.4f, -6.75f, 4.15f, 1.0f, 2.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(0, 22).addBox(4.4f, -6.75f, -0.55f, 1.0f, 2.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(44, 5).addBox(4.4f, -5.75f, -2.15f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(44, 3).addBox(4.4f, -5.75f, 1.15f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(27, 43).addBox(-5.2f, -5.75f, 1.15f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(24, 42).addBox(-5.2f, -5.75f, -2.35f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(4, 19).addBox(-5.3f, -6.75f, -0.55f, 1.0f, 2.0f, 1.0f, 0.0f, false);
            (this.Body = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Body.texOffs(0, 33).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 1.01f, false);
            this.Body.texOffs(0, 0).addBox(-5.0f, 1.0f, -4.0f, 10.0f, 9.0f, 8.0f, 0.0f, false);
            this.Body.texOffs(24, 40).addBox(-4.0f, 9.25f, -3.5f, 8.0f, 3.0f, 7.0f, 0.0f, false);
            final ModelRenderer cube_r1 = new ModelRenderer((Model)this);
            cube_r1.setPos(0.048f, 5.4f, -4.3293f);
            this.Body.addChild(cube_r1);
            this.setRotationAngle(cube_r1, 0.2752f, 0.473f, 0.1279f);
            cube_r1.texOffs(32, 58).addBox(-4.2417f, -4.0f, -1.4261f, 5.0f, 8.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r2 = new ModelRenderer((Model)this);
            cube_r2.setPos(-2.0f, 5.3f, 4.55f);
            this.Body.addChild(cube_r2);
            this.setRotationAngle(cube_r2, -0.3001f, -0.504f, 0.1483f);
            cube_r2.texOffs(44, 61).addBox(-2.5f, -4.0f, -0.5f, 5.0f, 8.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r3 = new ModelRenderer((Model)this);
            cube_r3.setPos(0.048f, 5.4f, -4.3293f);
            this.Body.addChild(cube_r3);
            this.setRotationAngle(cube_r3, 0.2752f, -0.473f, -0.1279f);
            cube_r3.texOffs(44, 24).addBox(-0.7583f, -4.0f, -1.4261f, 5.0f, 8.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r4 = new ModelRenderer((Model)this);
            cube_r4.setPos(1.8301f, 5.3f, 4.55f);
            this.Body.addChild(cube_r4);
            this.setRotationAngle(cube_r4, -0.3001f, 0.504f, -0.1483f);
            cube_r4.texOffs(56, 61).addBox(-2.5f, -4.0f, -0.5f, 5.0f, 8.0f, 1.0f, 0.0f, false);
            (this.RightArm = new ModelRenderer((Model)this)).setPos(-5.0f, 2.0f, 0.0f);
            this.RightArm.texOffs(56, 16).addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            this.RightArm.texOffs(36, 3).addBox(-6.25f, 2.75f, 2.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.RightArm.texOffs(0, 35).addBox(-6.25f, 2.75f, 0.25f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.RightArm.texOffs(20, 35).addBox(-6.25f, 2.75f, -3.25f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.RightArm.texOffs(20, 33).addBox(-6.25f, 2.75f, -1.5f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r5 = new ModelRenderer((Model)this);
            cube_r5.setPos(-3.0f, -0.5f, 0.0f);
            this.RightArm.addChild(cube_r5);
            this.setRotationAngle(cube_r5, 0.0f, 0.0f, 0.6981f);
            cube_r5.texOffs(47, 33).addBox(-2.65f, -1.5f, -3.5f, 3.0f, 7.0f, 7.0f, 0.0f, false);
            cube_r5.texOffs(24, 25).addBox(-3.0f, -3.5f, -4.1f, 6.0f, 7.0f, 8.0f, 0.0f, false);
            (this.LeftArm = new ModelRenderer((Model)this)).setPos(5.0f, 2.0f, 0.0f);
            this.LeftArm.texOffs(16, 50).addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            this.LeftArm.texOffs(4, 0).addBox(5.25f, 2.75f, 0.35f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.LeftArm.texOffs(24, 23).addBox(5.25f, 2.75f, 2.15f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.LeftArm.texOffs(4, 3).addBox(5.25f, 2.75f, -1.35f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.LeftArm.texOffs(0, 33).addBox(5.25f, 2.75f, -3.25f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r6 = new ModelRenderer((Model)this);
            cube_r6.setPos(3.0f, -0.5f, 0.0f);
            this.LeftArm.addChild(cube_r6);
            this.setRotationAngle(cube_r6, 0.0f, 0.0f, -0.6981f);
            cube_r6.texOffs(47, 47).addBox(-0.45f, -1.5f, -3.5f, 3.0f, 7.0f, 7.0f, 0.0f, false);
            cube_r6.texOffs(28, 9).addBox(-3.0f, -3.5f, -4.2f, 6.0f, 7.0f, 8.0f, 0.0f, false);
            (this.RightBoot = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightBoot.texOffs(0, 49).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            (this.LeftBoot = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftBoot.texOffs(48, 0).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            (this.Belt = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Belt.texOffs(16, 16).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 0.51f, false);
            (this.RightLeg = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightLeg.texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, false);
            (this.LeftLeg = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftLeg.texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, true);
        }
    }
    
    public static class Variant2<T extends LivingEntity> extends VaultGearModel<T>
    {
        public Variant2(final float modelSize, final EquipmentSlotType slotType) {
            super(modelSize, slotType);
            this.texWidth = (this.isLayer2() ? 64 : 128);
            this.texHeight = (this.isLayer2() ? 32 : 128);
            (this.Head = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Head.texOffs(2, 95).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, 1.0f, false);
            this.Head.texOffs(26, 95).addBox(-5.0f, -9.0f, -6.0f, 10.0f, 4.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(36, 74).addBox(-3.0f, -8.0f, -7.0f, 6.0f, 3.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(26, 100).addBox(-5.0f, -1.0f, -6.0f, 10.0f, 2.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(36, 78).addBox(-3.0f, -1.0f, -6.5f, 6.0f, 2.0f, 0.1f, 0.0f, false);
            this.Head.texOffs(2, 74).addBox(-6.0f, -10.0f, -4.0f, 12.0f, 11.0f, 10.0f, 0.0f, false);
            this.Head.texOffs(26, 103).addBox(-5.0f, -11.0f, -3.0f, 10.0f, 1.0f, 8.0f, 0.0f, false);
            final ModelRenderer cube_r8 = new ModelRenderer((Model)this);
            cube_r8.setPos(1.6f, -0.7527f, -5.7789f);
            this.Head.addChild(cube_r8);
            this.setRotationAngle(cube_r8, 0.7418f, -1.5708f, 0.0f);
            cube_r8.texOffs(2, 80).addBox(-0.5f, -0.75f, -0.75f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r9 = new ModelRenderer((Model)this);
            cube_r9.setPos(-2.4f, -0.7527f, -5.7789f);
            this.Head.addChild(cube_r9);
            this.setRotationAngle(cube_r9, 0.7418f, -1.5708f, 0.0f);
            cube_r9.texOffs(2, 80).addBox(-0.5f, -0.75f, -0.75f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r10 = new ModelRenderer((Model)this);
            cube_r10.setPos(-1.15f, -0.5027f, -5.7789f);
            this.Head.addChild(cube_r10);
            this.setRotationAngle(cube_r10, 0.7418f, -1.5708f, 0.0f);
            cube_r10.texOffs(2, 80).addBox(-0.5f, -0.7654f, -0.3968f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r11 = new ModelRenderer((Model)this);
            cube_r11.setPos(0.35f, -0.7527f, -5.7789f);
            this.Head.addChild(cube_r11);
            this.setRotationAngle(cube_r11, 0.7418f, -1.5708f, 0.0f);
            cube_r11.texOffs(2, 80).addBox(-0.5f, -0.9189f, -0.9343f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r12 = new ModelRenderer((Model)this);
            cube_r12.setPos(1.6f, -5.2527f, -5.7789f);
            this.Head.addChild(cube_r12);
            this.setRotationAngle(cube_r12, 0.7418f, -1.5708f, 0.0f);
            cube_r12.texOffs(2, 80).addBox(-0.5f, -0.5657f, -0.9189f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r13 = new ModelRenderer((Model)this);
            cube_r13.setPos(1.6f, -5.2527f, -5.7789f);
            this.Head.addChild(cube_r13);
            this.setRotationAngle(cube_r13, 0.7418f, -1.5708f, 0.0f);
            cube_r13.texOffs(2, 80).addBox(-0.5f, 2.1368f, 2.0301f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r14 = new ModelRenderer((Model)this);
            cube_r14.setPos(1.6f, -5.2527f, -5.7789f);
            this.Head.addChild(cube_r14);
            this.setRotationAngle(cube_r14, 0.7418f, -1.5708f, 0.0f);
            cube_r14.texOffs(2, 80).addBox(-0.5f, 0.0945f, 0.1716f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r15 = new ModelRenderer((Model)this);
            cube_r15.setPos(1.6f, -5.2527f, -5.7789f);
            this.Head.addChild(cube_r15);
            this.setRotationAngle(cube_r15, 0.7418f, -1.5708f, 0.0f);
            cube_r15.texOffs(2, 80).addBox(-0.5f, 1.1079f, 1.2774f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            (this.Body = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Body.texOffs(0, 33).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 1.01f, false);
            this.Body.texOffs(0, 0).addBox(-5.0f, 1.0f, -4.0f, 10.0f, 9.0f, 8.0f, 0.0f, false);
            this.Body.texOffs(24, 40).addBox(-4.0f, 9.25f, -3.5f, 8.0f, 3.0f, 7.0f, 0.0f, false);
            final ModelRenderer cube_r16 = new ModelRenderer((Model)this);
            cube_r16.setPos(0.048f, 5.4f, -4.3293f);
            this.Body.addChild(cube_r16);
            this.setRotationAngle(cube_r16, 0.2752f, 0.473f, 0.1279f);
            cube_r16.texOffs(32, 58).addBox(-4.2417f, -4.0f, -1.4261f, 5.0f, 8.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r17 = new ModelRenderer((Model)this);
            cube_r17.setPos(-2.0f, 5.3f, 4.55f);
            this.Body.addChild(cube_r17);
            this.setRotationAngle(cube_r17, -0.3001f, -0.504f, 0.1483f);
            cube_r17.texOffs(44, 61).addBox(-2.5f, -4.0f, -0.5f, 5.0f, 8.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r18 = new ModelRenderer((Model)this);
            cube_r18.setPos(0.048f, 5.4f, -4.3293f);
            this.Body.addChild(cube_r18);
            this.setRotationAngle(cube_r18, 0.2752f, -0.473f, -0.1279f);
            cube_r18.texOffs(44, 24).addBox(-0.7583f, -4.0f, -1.4261f, 5.0f, 8.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r19 = new ModelRenderer((Model)this);
            cube_r19.setPos(1.8301f, 5.3f, 4.55f);
            this.Body.addChild(cube_r19);
            this.setRotationAngle(cube_r19, -0.3001f, 0.504f, -0.1483f);
            cube_r19.texOffs(56, 61).addBox(-2.5f, -4.0f, -0.5f, 5.0f, 8.0f, 1.0f, 0.0f, false);
            (this.RightArm = new ModelRenderer((Model)this)).setPos(-5.0f, 2.0f, 0.0f);
            this.RightArm.texOffs(56, 16).addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            this.RightArm.texOffs(36, 3).addBox(-6.25f, 2.75f, 2.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.RightArm.texOffs(0, 35).addBox(-6.25f, 2.75f, 0.25f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.RightArm.texOffs(20, 35).addBox(-6.25f, 2.75f, -3.25f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.RightArm.texOffs(20, 33).addBox(-6.25f, 2.75f, -1.5f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r20 = new ModelRenderer((Model)this);
            cube_r20.setPos(-3.0f, -0.5f, 0.0f);
            this.RightArm.addChild(cube_r20);
            this.setRotationAngle(cube_r20, 0.0f, 0.0f, 0.6981f);
            cube_r20.texOffs(47, 33).addBox(-2.65f, -1.5f, -3.5f, 3.0f, 7.0f, 7.0f, 0.0f, false);
            cube_r20.texOffs(24, 25).addBox(-3.0f, -3.5f, -4.1f, 6.0f, 7.0f, 8.0f, 0.0f, false);
            (this.LeftArm = new ModelRenderer((Model)this)).setPos(5.0f, 2.0f, 0.0f);
            this.LeftArm.texOffs(16, 50).addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            this.LeftArm.texOffs(4, 0).addBox(5.25f, 2.75f, 0.35f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.LeftArm.texOffs(24, 23).addBox(5.25f, 2.75f, 2.15f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.LeftArm.texOffs(4, 3).addBox(5.25f, 2.75f, -1.35f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.LeftArm.texOffs(0, 33).addBox(5.25f, 2.75f, -3.25f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r21 = new ModelRenderer((Model)this);
            cube_r21.setPos(3.0f, -0.5f, 0.0f);
            this.LeftArm.addChild(cube_r21);
            this.setRotationAngle(cube_r21, 0.0f, 0.0f, -0.6981f);
            cube_r21.texOffs(47, 47).addBox(-0.45f, -1.5f, -3.5f, 3.0f, 7.0f, 7.0f, 0.0f, false);
            cube_r21.texOffs(28, 9).addBox(-3.0f, -3.5f, -4.2f, 6.0f, 7.0f, 8.0f, 0.0f, false);
            (this.RightBoot = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightBoot.texOffs(0, 49).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            (this.LeftBoot = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftBoot.texOffs(48, 0).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            (this.Belt = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Belt.texOffs(16, 16).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 0.51f, false);
            (this.RightLeg = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightLeg.texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, false);
            (this.LeftLeg = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftLeg.texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, true);
        }
    }
}
