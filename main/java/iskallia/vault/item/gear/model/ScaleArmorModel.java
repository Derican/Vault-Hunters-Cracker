// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.item.gear.model;

import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.LivingEntity;

public class ScaleArmorModel
{
    public static class Variant1<T extends LivingEntity> extends VaultGearModel<T>
    {
        public Variant1(final float modelSize, final EquipmentSlotType slotType) {
            super(modelSize, slotType);
            this.texWidth = (this.isLayer2() ? 64 : 64);
            this.texHeight = (this.isLayer2() ? 32 : 64);
            (this.Head = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Head.texOffs(0, 14).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, 1.0f, false);
            final ModelRenderer cube_r1 = new ModelRenderer((Model)this);
            cube_r1.setPos(-7.3333f, -8.1667f, -0.5f);
            this.Head.addChild(cube_r1);
            this.setRotationAngle(cube_r1, 0.829f, 0.0f, 0.0f);
            cube_r1.texOffs(0, 0).addBox(14.8333f, -3.8333f, -1.75f, 1.0f, 3.0f, 2.0f, 0.0f, false);
            cube_r1.texOffs(0, 14).addBox(-1.1667f, -3.8333f, -1.75f, 1.0f, 3.0f, 2.0f, 0.0f, false);
            cube_r1.texOffs(24, 15).addBox(14.3333f, -0.8333f, -2.25f, 2.0f, 3.0f, 3.0f, 0.0f, false);
            cube_r1.texOffs(42, 0).addBox(-1.6667f, -0.8333f, -2.25f, 2.0f, 3.0f, 3.0f, 0.0f, false);
            cube_r1.texOffs(30, 0).addBox(12.3333f, 0.1667f, -2.75f, 2.0f, 3.0f, 4.0f, 0.0f, false);
            cube_r1.texOffs(32, 18).addBox(0.3333f, 0.1667f, -2.75f, 2.0f, 3.0f, 4.0f, 0.0f, false);
            (this.Body = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Body.texOffs(28, 26).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 1.01f, false);
            this.Body.texOffs(0, 0).addBox(-5.5f, 0.0f, -4.0f, 11.0f, 6.0f, 8.0f, 0.0f, false);
            (this.RightArm = new ModelRenderer((Model)this)).setPos(-5.0f, 2.0f, 0.0f);
            this.RightArm.texOffs(48, 38).addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            this.RightArm.texOffs(31, 7).addBox(-5.25f, -4.25f, -3.5f, 6.0f, 4.0f, 7.0f, 0.0f, false);
            this.RightArm.texOffs(4, 0).addBox(-2.5f, -2.75f, 2.75f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.RightArm.texOffs(0, 5).addBox(-4.5f, -2.75f, 2.75f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.RightArm.texOffs(4, 5).addBox(-4.5f, -2.75f, -3.75f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.RightArm.texOffs(4, 14).addBox(-2.5f, -2.75f, -3.75f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.RightArm.texOffs(0, 19).addBox(-5.5f, -2.75f, -3.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.RightArm.texOffs(4, 19).addBox(-5.5f, -2.75f, -0.5f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.RightArm.texOffs(0, 30).addBox(-5.5f, -2.75f, 2.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            (this.LeftArm = new ModelRenderer((Model)this)).setPos(5.0f, 2.0f, 0.0f);
            this.LeftArm.texOffs(32, 42).addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            this.LeftArm.texOffs(0, 30).addBox(-0.75f, -4.25f, -3.5f, 6.0f, 4.0f, 7.0f, 0.0f, false);
            this.LeftArm.texOffs(19, 32).addBox(4.5f, -2.75f, 2.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.LeftArm.texOffs(0, 32).addBox(4.5f, -2.75f, -0.5f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.LeftArm.texOffs(3, 31).addBox(4.5f, -2.75f, -3.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.LeftArm.texOffs(23, 30).addBox(3.5f, -2.75f, -3.75f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.LeftArm.texOffs(19, 30).addBox(1.5f, -2.75f, -3.75f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.LeftArm.texOffs(30, 2).addBox(1.5f, -2.75f, 2.75f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.LeftArm.texOffs(30, 0).addBox(3.5f, -2.75f, 2.75f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            (this.RightBoot = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightBoot.texOffs(16, 41).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            (this.LeftBoot = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftBoot.texOffs(0, 41).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
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
            this.Head.texOffs(0, 15).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, 1.0f, false);
            this.Head.texOffs(0, 0).addBox(-1.0f, -11.0f, -2.0f, 2.0f, 2.0f, 2.0f, 0.0f, false);
            this.Head.texOffs(0, 4).addBox(-0.5f, -15.0f, -1.5f, 1.0f, 4.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r1 = new ModelRenderer((Model)this);
            cube_r1.setPos(1.8321f, -9.2935f, -0.35f);
            this.Head.addChild(cube_r1);
            this.setRotationAngle(cube_r1, 0.0f, 0.0f, 0.2618f);
            cube_r1.texOffs(35, 6).addBox(-2.0f, -0.5f, -4.5f, 5.0f, 2.0f, 9.0f, 0.0f, false);
            final ModelRenderer cube_r2 = new ModelRenderer((Model)this);
            cube_r2.setPos(-1.8675f, -7.7152f, 0.0f);
            this.Head.addChild(cube_r2);
            this.setRotationAngle(cube_r2, 0.0f, 0.0f, -0.2618f);
            cube_r2.texOffs(0, 44).addBox(-2.5f, -2.0f, -4.85f, 5.0f, 2.0f, 9.0f, 0.0f, false);
            (this.Body = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Body.texOffs(30, 36).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 1.01f, false);
            this.Body.texOffs(0, 0).addBox(-6.0f, 1.0f, -5.0f, 12.0f, 5.0f, 10.0f, 0.0f, false);
            this.Body.texOffs(34, 0).addBox(-4.0f, 6.0f, -4.0f, 8.0f, 3.0f, 1.0f, 0.0f, false);
            this.Body.texOffs(24, 17).addBox(-4.0f, 6.0f, 3.0f, 8.0f, 3.0f, 1.0f, 0.0f, false);
            this.Body.texOffs(54, 9).addBox(-3.0f, 9.0f, -3.5f, 6.0f, 3.0f, 1.0f, 0.0f, false);
            this.Body.texOffs(63, 18).addBox(-3.0f, 9.0f, 2.5f, 6.0f, 3.0f, 1.0f, 0.0f, false);
            (this.RightArm = new ModelRenderer((Model)this)).setPos(-5.0f, 2.0f, 0.0f);
            this.RightArm.texOffs(40, 56).addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            this.RightArm.texOffs(0, 31).addBox(-6.0f, -5.0f, -4.0f, 7.0f, 5.0f, 8.0f, 0.0f, false);
            this.RightArm.texOffs(47, 45).addBox(-5.0f, -1.0f, -3.5f, 5.0f, 4.0f, 7.0f, 0.0f, false);
            final ModelRenderer cube_r3 = new ModelRenderer((Model)this);
            cube_r3.setPos(-4.2495f, -5.2887f, 0.0f);
            this.RightArm.addChild(cube_r3);
            this.setRotationAngle(cube_r3, 0.0f, 0.0f, -0.4363f);
            cube_r3.texOffs(63, 9).addBox(-1.7f, -0.5f, -3.9f, 3.0f, 2.0f, 7.0f, 0.0f, false);
            final ModelRenderer cube_r4 = new ModelRenderer((Model)this);
            cube_r4.setPos(-1.5f, -5.5f, 0.0f);
            this.RightArm.addChild(cube_r4);
            this.setRotationAngle(cube_r4, 0.0f, 0.0f, 0.4363f);
            cube_r4.texOffs(9, 64).addBox(-1.5f, -0.5f, -3.9f, 3.0f, 2.0f, 7.0f, 0.0f, false);
            (this.LeftArm = new ModelRenderer((Model)this)).setPos(5.0f, 2.0f, 0.0f);
            this.LeftArm.texOffs(0, 55).addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            this.LeftArm.texOffs(24, 23).addBox(-1.0f, -5.0f, -4.0f, 7.0f, 5.0f, 8.0f, 0.0f, false);
            this.LeftArm.texOffs(46, 17).addBox(0.0f, -1.0f, -3.5f, 5.0f, 4.0f, 7.0f, 0.0f, false);
            final ModelRenderer cube_r5 = new ModelRenderer((Model)this);
            cube_r5.setPos(4.6f, -5.85f, 0.0f);
            this.LeftArm.addChild(cube_r5);
            this.setRotationAngle(cube_r5, -3.1416f, 0.0f, 2.7053f);
            cube_r5.texOffs(56, 56).addBox(1.5f, -1.5f, -3.9f, 3.0f, 2.0f, 7.0f, 0.0f, false);
            final ModelRenderer cube_r6 = new ModelRenderer((Model)this);
            cube_r6.setPos(1.9727f, -5.2161f, 0.0f);
            this.LeftArm.addChild(cube_r6);
            this.setRotationAngle(cube_r6, 3.1416f, 0.0f, -2.7053f);
            cube_r6.texOffs(54, 0).addBox(-3.7f, -1.5f, -3.9f, 3.0f, 2.0f, 7.0f, 0.0f, false);
            (this.RightBoot = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightBoot.texOffs(54, 28).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            (this.LeftBoot = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftBoot.texOffs(24, 52).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            (this.Belt = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Belt.texOffs(16, 16).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 0.51f, false);
            (this.RightLeg = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightLeg.texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, false);
            (this.LeftLeg = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftLeg.texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, true);
        }
    }
    
    public static class Variant3<T extends LivingEntity> extends VaultGearModel<T>
    {
        public Variant3(final float modelSize, final EquipmentSlotType slotType) {
            super(modelSize, slotType);
            this.texWidth = (this.isLayer2() ? 64 : 64);
            this.texHeight = (this.isLayer2() ? 32 : 64);
            (this.Head = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Head.texOffs(0, 0).addBox(-4.0f, -7.0f, -4.0f, 8.0f, 7.0f, 8.0f, 1.0f, false);
            this.Head.texOffs(0, 15).addBox(-4.0f, -9.0f, -4.0f, 8.0f, 1.0f, 8.0f, 0.0f, false);
            final ModelRenderer cube_r1 = new ModelRenderer((Model)this);
            cube_r1.setPos(-0.1327f, -6.906f, -5.7145f);
            this.Head.addChild(cube_r1);
            this.setRotationAngle(cube_r1, -0.5074f, -0.5162f, 0.2679f);
            cube_r1.texOffs(36, 23).addBox(-1.273f, -1.0f, -1.6649f, 6.0f, 2.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r2 = new ModelRenderer((Model)this);
            cube_r2.setPos(-0.0912f, -1.0f, -6.1377f);
            this.Head.addChild(cube_r2);
            this.setRotationAngle(cube_r2, 0.3596f, -0.5462f, -0.1929f);
            cube_r2.texOffs(0, 40).addBox(-1.2073f, -4.0f, -1.6417f, 6.0f, 6.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r3 = new ModelRenderer((Model)this);
            cube_r3.setPos(-0.1327f, -6.906f, -5.7145f);
            this.Head.addChild(cube_r3);
            this.setRotationAngle(cube_r3, -0.5175f, 0.5467f, -0.2877f);
            cube_r3.texOffs(24, 0).addBox(-4.5957f, -1.0f, -1.7112f, 5.0f, 2.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r4 = new ModelRenderer((Model)this);
            cube_r4.setPos(-0.0912f, -1.0f, -6.1377f);
            this.Head.addChild(cube_r4);
            this.setRotationAngle(cube_r4, 0.3674f, 0.5788f, 0.2075f);
            cube_r4.texOffs(14, 40).addBox(-4.5301f, -4.0f, -1.7344f, 5.0f, 6.0f, 1.0f, 0.0f, false);
            (this.Body = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Body.texOffs(0, 24).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 1.01f, false);
            final ModelRenderer cube_r5 = new ModelRenderer((Model)this);
            cube_r5.setPos(-0.0133f, 4.0f, -3.5f);
            this.Body.addChild(cube_r5);
            this.setRotationAngle(cube_r5, 0.2934f, -0.4623f, -0.1339f);
            cube_r5.texOffs(14, 40).addBox(-0.7378f, -3.0f, -1.4173f, 5.0f, 6.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r6 = new ModelRenderer((Model)this);
            cube_r6.setPos(-0.0133f, 4.0f, -3.5f);
            this.Body.addChild(cube_r6);
            this.setRotationAngle(cube_r6, 0.2934f, 0.4623f, 0.1339f);
            cube_r6.texOffs(14, 40).addBox(-4.2622f, -3.0f, -1.4173f, 5.0f, 6.0f, 1.0f, 0.0f, false);
            (this.RightArm = new ModelRenderer((Model)this)).setPos(-5.0f, 2.0f, 0.0f);
            this.RightArm.texOffs(32, 0).addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            this.RightArm.texOffs(24, 16).addBox(-4.0f, -4.0f, -3.0f, 5.0f, 1.0f, 6.0f, 0.0f, false);
            final ModelRenderer cube_r7 = new ModelRenderer((Model)this);
            cube_r7.setPos(-2.5f, -0.5f, -3.5f);
            this.RightArm.addChild(cube_r7);
            this.setRotationAngle(cube_r7, -0.3927f, 0.0f, 0.0f);
            cube_r7.texOffs(40, 16).addBox(-1.5f, -2.5f, -0.5f, 4.0f, 5.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r8 = new ModelRenderer((Model)this);
            cube_r8.setPos(-2.0f, -0.5f, 3.5f);
            this.RightArm.addChild(cube_r8);
            this.setRotationAngle(cube_r8, 0.3491f, 0.0f, 0.0f);
            cube_r8.texOffs(40, 16).addBox(-2.0f, -2.5f, -0.5f, 4.0f, 5.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r9 = new ModelRenderer((Model)this);
            cube_r9.setPos(-4.5f, -0.5f, 0.0f);
            this.RightArm.addChild(cube_r9);
            this.setRotationAngle(cube_r9, 0.0f, 0.0f, 0.3491f);
            cube_r9.texOffs(34, 34).addBox(-0.5f, -2.5f, -3.0f, 1.0f, 5.0f, 6.0f, 0.0f, false);
            (this.LeftArm = new ModelRenderer((Model)this)).setPos(5.0f, 2.0f, 0.0f);
            this.LeftArm.texOffs(24, 16).addBox(-1.0f, -4.0f, -3.0f, 5.0f, 1.0f, 6.0f, 0.0f, false);
            this.LeftArm.texOffs(32, 0).addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, true);
            final ModelRenderer cube_r10 = new ModelRenderer((Model)this);
            cube_r10.setPos(4.5f, -0.5f, 0.0f);
            this.LeftArm.addChild(cube_r10);
            this.setRotationAngle(cube_r10, 0.0f, 0.0f, -0.3491f);
            cube_r10.texOffs(34, 34).addBox(-0.5f, -2.5f, -3.0f, 1.0f, 5.0f, 6.0f, 0.0f, false);
            final ModelRenderer cube_r11 = new ModelRenderer((Model)this);
            cube_r11.setPos(2.0f, -0.5f, 3.5f);
            this.LeftArm.addChild(cube_r11);
            this.setRotationAngle(cube_r11, 0.3491f, 0.0f, 0.0f);
            cube_r11.texOffs(40, 16).addBox(-2.0f, -2.5f, -0.5f, 4.0f, 5.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r12 = new ModelRenderer((Model)this);
            cube_r12.setPos(1.5f, -0.5f, -3.5f);
            this.LeftArm.addChild(cube_r12);
            this.setRotationAngle(cube_r12, -0.3927f, 0.0f, 0.0f);
            cube_r12.texOffs(40, 16).addBox(-1.5f, -2.5f, -0.5f, 4.0f, 5.0f, 1.0f, 0.0f, false);
            (this.RightBoot = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightBoot.texOffs(24, 24).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            (this.LeftBoot = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftBoot.texOffs(24, 24).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, true);
            (this.Belt = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Belt.texOffs(16, 16).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 0.51f, false);
            (this.RightLeg = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightLeg.texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, false);
            (this.LeftLeg = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftLeg.texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, true);
        }
    }
    
    public static class Variant4<T extends LivingEntity> extends VaultGearModel<T>
    {
        public Variant4(final float modelSize, final EquipmentSlotType slotType) {
            super(modelSize, slotType);
            this.texWidth = (this.isLayer2() ? 64 : 128);
            this.texHeight = (this.isLayer2() ? 32 : 128);
            (this.Head = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Head.texOffs(0, 15).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, 1.0f, false);
            this.Head.texOffs(0, 0).addBox(-6.0f, -10.0f, -8.0f, 12.0f, 1.0f, 14.0f, 0.0f, false);
            this.Head.texOffs(20, 19).addBox(-6.0f, -9.0f, -8.0f, 1.0f, 4.0f, 12.0f, 0.0f, false);
            this.Head.texOffs(20, 19).addBox(5.0f, -9.0f, -8.0f, 1.0f, 4.0f, 12.0f, 0.0f, false);
            this.Head.texOffs(24, 35).addBox(-6.0f, -10.0f, -9.0f, 12.0f, 5.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(24, 15).addBox(4.0f, -5.0f, -9.0f, 2.0f, 2.0f, 2.0f, 0.0f, false);
            this.Head.texOffs(24, 15).addBox(-6.0f, -5.0f, -9.0f, 2.0f, 2.0f, 2.0f, 0.0f, false);
            this.Head.texOffs(24, 15).addBox(-5.0f, -2.0f, -7.0f, 2.0f, 2.0f, 2.0f, 0.0f, false);
            this.Head.texOffs(24, 15).addBox(3.0f, -2.0f, -7.0f, 2.0f, 2.0f, 2.0f, 0.0f, false);
            this.Head.texOffs(0, 0).addBox(-6.0f, -5.0f, -5.0f, 1.0f, 6.0f, 5.0f, 0.0f, false);
            this.Head.texOffs(0, 0).addBox(5.0f, -5.0f, -5.0f, 1.0f, 6.0f, 5.0f, 0.0f, false);
            this.Head.texOffs(38, 0).addBox(-6.0f, 0.0f, -7.0f, 12.0f, 1.0f, 2.0f, 0.0f, false);
            final ModelRenderer cube_r1 = new ModelRenderer((Model)this);
            cube_r1.setPos(-6.0f, -10.5f, -0.5f);
            this.Head.addChild(cube_r1);
            this.setRotationAngle(cube_r1, -0.9163f, 0.0f, -0.0436f);
            cube_r1.texOffs(0, 11).addBox(-0.2f, -5.5f, -0.5f, 0.0f, 8.0f, 4.0f, 0.0f, false);
            final ModelRenderer cube_r2 = new ModelRenderer((Model)this);
            cube_r2.setPos(6.1f, -10.5f, -0.5f);
            this.Head.addChild(cube_r2);
            this.setRotationAngle(cube_r2, -0.9163f, 0.0f, 0.0436f);
            cube_r2.texOffs(0, 11).addBox(0.1f, -5.5f, -0.5f, 0.0f, 8.0f, 4.0f, 0.0f, false);
            (this.Body = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Body.texOffs(0, 31).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 1.01f, false);
            final ModelRenderer cube_r3 = new ModelRenderer((Model)this);
            cube_r3.setPos(0.0f, 3.5f, -3.5f);
            this.Body.addChild(cube_r3);
            this.setRotationAngle(cube_r3, 0.48f, 0.0f, 0.0f);
            cube_r3.texOffs(38, 3).addBox(-5.0f, -2.5f, -0.5f, 10.0f, 5.0f, 1.0f, 0.0f, false);
            (this.RightArm = new ModelRenderer((Model)this)).setPos(-5.0f, 2.0f, 0.0f);
            this.RightArm.texOffs(40, 41).addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            final ModelRenderer cube_r4 = new ModelRenderer((Model)this);
            cube_r4.setPos(-3.0f, -1.0f, 0.0f);
            this.RightArm.addChild(cube_r4);
            this.setRotationAngle(cube_r4, 0.0f, 0.0f, 0.9599f);
            cube_r4.texOffs(34, 15).addBox(-3.0f, -3.0f, -4.0f, 4.0f, 6.0f, 8.0f, 0.0f, false);
            (this.LeftArm = new ModelRenderer((Model)this)).setPos(5.0f, 2.0f, 0.0f);
            this.LeftArm.texOffs(40, 41).addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, true);
            final ModelRenderer cube_r5 = new ModelRenderer((Model)this);
            cube_r5.setPos(3.0f, -1.0f, 0.0f);
            this.LeftArm.addChild(cube_r5);
            this.setRotationAngle(cube_r5, 0.0f, 0.0f, -0.9599f);
            cube_r5.texOffs(34, 15).addBox(-1.0f, -3.0f, -4.0f, 4.0f, 6.0f, 8.0f, 0.0f, false);
            (this.RightBoot = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightBoot.texOffs(24, 41).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            (this.LeftBoot = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftBoot.texOffs(24, 41).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, true);
            (this.Belt = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Belt.texOffs(16, 16).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 0.51f, false);
            (this.RightLeg = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightLeg.texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, false);
            (this.LeftLeg = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftLeg.texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, true);
        }
    }
}
