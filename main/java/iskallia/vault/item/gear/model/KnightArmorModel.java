// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.item.gear.model;

import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.LivingEntity;

public class KnightArmorModel
{
    public static class Variant1<T extends LivingEntity> extends VaultGearModel<T>
    {
        public Variant1(final float modelSize, final EquipmentSlotType slotType) {
            super(modelSize, slotType);
            this.texWidth = (this.isLayer2() ? 64 : 64);
            this.texHeight = (this.isLayer2() ? 32 : 64);
            (this.Head = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Head.texOffs(0, 0).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, 1.0f, false);
            this.Head.texOffs(0, 0).addBox(5.0f, -5.0f, -5.0f, 1.0f, 3.0f, 3.0f, 0.0f, false);
            this.Head.texOffs(0, 0).addBox(-6.0f, -5.0f, -5.0f, 1.0f, 3.0f, 3.0f, 0.0f, false);
            final ModelRenderer cube_r1 = new ModelRenderer((Model)this);
            cube_r1.setPos(4.5161f, 0.0f, 0.1884f);
            this.Head.addChild(cube_r1);
            this.setRotationAngle(cube_r1, 0.0f, -0.6545f, 0.0f);
            cube_r1.texOffs(40, 9).addBox(-9.0438f, -9.0f, -4.2832f, 6.0f, 5.0f, 1.0f, 0.0f, false);
            cube_r1.texOffs(36, 23).addBox(-9.0438f, -3.0f, -4.2832f, 6.0f, 4.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r2 = new ModelRenderer((Model)this);
            cube_r2.setPos(-5.0f, 0.0f, -3.0f);
            this.Head.addChild(cube_r2);
            this.setRotationAngle(cube_r2, 0.0f, 0.6545f, 0.0f);
            cube_r2.texOffs(40, 9).addBox(1.1637f, -9.0f, -1.4569f, 6.0f, 5.0f, 1.0f, 0.0f, false);
            cube_r2.texOffs(36, 23).addBox(1.1637f, -3.0f, -1.4569f, 6.0f, 4.0f, 1.0f, 0.0f, false);
            (this.Body = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Body.texOffs(28, 28).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 1.01f, false);
            this.Body.texOffs(32, 44).addBox(-4.0f, 2.0f, 3.0f, 8.0f, 8.0f, 1.0f, 0.0f, false);
            this.Body.texOffs(0, 16).addBox(-5.5f, 11.0f, -3.5f, 11.0f, 2.0f, 7.0f, 0.0f, false);
            final ModelRenderer cube_r3 = new ModelRenderer((Model)this);
            cube_r3.setPos(5.0f, 13.0f, 0.0f);
            this.Body.addChild(cube_r3);
            this.setRotationAngle(cube_r3, 0.0f, 0.0f, -1.8326f);
            cube_r3.texOffs(18, 0).addBox(-7.8378f, 0.6276f, -3.0f, 8.0f, 0.0f, 6.0f, 0.0f, false);
            final ModelRenderer cube_r4 = new ModelRenderer((Model)this);
            cube_r4.setPos(-4.0f, 13.0f, 0.0f);
            this.Body.addChild(cube_r4);
            this.setRotationAngle(cube_r4, 0.0f, 0.0f, -1.2217f);
            cube_r4.texOffs(18, 0).addBox(-8.0f, -1.1f, -3.0f, 8.0f, 0.0f, 6.0f, 0.0f, false);
            final ModelRenderer cube_r5 = new ModelRenderer((Model)this);
            cube_r5.setPos(0.0f, 0.0f, 0.0f);
            this.Body.addChild(cube_r5);
            this.setRotationAngle(cube_r5, 0.1745f, 0.0f, 0.0f);
            cube_r5.texOffs(32, 44).addBox(-4.0f, 1.0f, -6.0f, 8.0f, 8.0f, 1.0f, 0.0f, false);
            (this.RightArm = new ModelRenderer((Model)this)).setPos(-5.0f, 2.0f, 0.0f);
            this.RightArm.texOffs(16, 40).addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            this.RightArm.texOffs(0, 25).addBox(-5.0f, -4.0f, -4.0f, 6.0f, 6.0f, 8.0f, 0.0f, false);
            this.RightArm.texOffs(32, 0).addBox(-5.0f, 2.0f, -4.0f, 3.0f, 1.0f, 8.0f, 0.0f, false);
            final ModelRenderer cube_r6 = new ModelRenderer((Model)this);
            cube_r6.setPos(-4.0f, 4.5f, 0.0f);
            this.RightArm.addChild(cube_r6);
            this.setRotationAngle(cube_r6, 0.0f, 0.0f, 0.4363f);
            cube_r6.texOffs(29, 9).addBox(-2.0f, -4.5f, -3.5f, 2.0f, 7.0f, 7.0f, 0.0f, false);
            cube_r6.texOffs(29, 9).addBox(-4.0f, -6.5f, -3.5f, 2.0f, 7.0f, 7.0f, 0.0f, false);
            (this.LeftArm = new ModelRenderer((Model)this)).setPos(5.0f, 2.0f, 0.0f);
            this.LeftArm.texOffs(0, 25).addBox(-1.0f, -4.0f, -4.0f, 6.0f, 6.0f, 8.0f, 0.0f, false);
            this.LeftArm.texOffs(32, 0).addBox(2.0f, 2.0f, -4.0f, 3.0f, 1.0f, 8.0f, 0.0f, false);
            this.LeftArm.texOffs(16, 40).addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, true);
            final ModelRenderer cube_r7 = new ModelRenderer((Model)this);
            cube_r7.setPos(4.0f, 4.5f, 0.0f);
            this.LeftArm.addChild(cube_r7);
            this.setRotationAngle(cube_r7, 0.0f, 0.0f, -0.4363f);
            cube_r7.texOffs(29, 9).addBox(0.0f, -4.5f, -3.5f, 2.0f, 7.0f, 7.0f, 0.0f, false);
            cube_r7.texOffs(29, 9).addBox(2.0f, -6.5f, -3.5f, 2.0f, 7.0f, 7.0f, 0.0f, false);
            (this.RightBoot = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightBoot.texOffs(0, 39).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            (this.LeftBoot = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftBoot.texOffs(0, 39).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, true);
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
            this.texWidth = (this.isLayer2() ? 64 : 64);
            this.texHeight = (this.isLayer2() ? 32 : 64);
            (this.Head = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Head.texOffs(0, 0).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, 1.0f, false);
            final ModelRenderer cube_r1 = new ModelRenderer((Model)this);
            cube_r1.setPos(0.0f, 0.0f, 0.0f);
            this.Head.addChild(cube_r1);
            this.setRotationAngle(cube_r1, 0.2182f, 0.0f, 0.0f);
            cube_r1.texOffs(0, 25).addBox(-1.5f, -13.0f, 5.0f, 3.0f, 3.0f, 1.0f, 0.0f, false);
            cube_r1.texOffs(32, 50).addBox(-1.5f, -16.0f, 5.0f, 3.0f, 3.0f, 2.0f, 0.0f, false);
            cube_r1.texOffs(47, 18).addBox(-1.5f, -18.0f, 2.0f, 3.0f, 4.0f, 3.0f, 0.0f, false);
            cube_r1.texOffs(0, 0).addBox(-2.5f, -16.0f, 0.0f, 1.0f, 5.0f, 2.0f, 0.0f, false);
            cube_r1.texOffs(0, 0).addBox(1.5f, -16.0f, 0.0f, 1.0f, 5.0f, 2.0f, 0.0f, false);
            cube_r1.texOffs(47, 9).addBox(-1.5f, -17.0f, -1.0f, 3.0f, 6.0f, 3.0f, 0.0f, false);
            cube_r1.texOffs(0, 16).addBox(-0.5f, -14.0f, 0.0f, 1.0f, 6.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r2 = new ModelRenderer((Model)this);
            cube_r2.setPos(4.5161f, 0.0f, 0.1884f);
            this.Head.addChild(cube_r2);
            this.setRotationAngle(cube_r2, 0.0f, -0.6545f, 0.0f);
            cube_r2.texOffs(46, 0).addBox(-9.0438f, -9.0f, -4.2832f, 6.0f, 5.0f, 1.0f, 0.0f, false);
            cube_r2.texOffs(49, 49).addBox(-9.0438f, -3.0f, -4.2832f, 6.0f, 4.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r3 = new ModelRenderer((Model)this);
            cube_r3.setPos(-5.0f, 0.0f, -3.0f);
            this.Head.addChild(cube_r3);
            this.setRotationAngle(cube_r3, 0.0f, 0.6545f, 0.0f);
            cube_r3.texOffs(46, 0).addBox(1.1637f, -9.0f, -1.4569f, 6.0f, 5.0f, 1.0f, 0.0f, false);
            cube_r3.texOffs(49, 49).addBox(1.1637f, -3.0f, -1.4569f, 6.0f, 4.0f, 1.0f, 0.0f, false);
            (this.Body = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Body.texOffs(28, 25).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 1.01f, false);
            this.Body.texOffs(32, 41).addBox(-4.0f, 2.0f, 3.0f, 8.0f, 8.0f, 1.0f, 0.0f, false);
            this.Body.texOffs(0, 16).addBox(-5.5f, 11.0f, -3.5f, 11.0f, 2.0f, 7.0f, 0.0f, false);
            final ModelRenderer cube_r4 = new ModelRenderer((Model)this);
            cube_r4.setPos(5.0f, 13.0f, 0.0f);
            this.Body.addChild(cube_r4);
            this.setRotationAngle(cube_r4, 0.0f, 0.0f, -1.8326f);
            cube_r4.texOffs(18, 0).addBox(-7.8378f, 0.4276f, -3.0f, 8.0f, 0.0f, 6.0f, 0.0f, false);
            final ModelRenderer cube_r5 = new ModelRenderer((Model)this);
            cube_r5.setPos(-4.0f, 13.0f, 0.0f);
            this.Body.addChild(cube_r5);
            this.setRotationAngle(cube_r5, 0.0f, 0.0f, -1.2217f);
            cube_r5.texOffs(18, 0).addBox(-8.0f, -1.3f, -3.0f, 8.0f, 0.0f, 6.0f, 0.0f, false);
            final ModelRenderer cube_r6 = new ModelRenderer((Model)this);
            cube_r6.setPos(0.0f, 6.0f, -3.5f);
            this.Body.addChild(cube_r6);
            this.setRotationAngle(cube_r6, 0.3054f, 0.0f, 0.0f);
            cube_r6.texOffs(32, 41).addBox(-4.0f, -4.0f, -0.5f, 8.0f, 8.0f, 1.0f, 0.0f, false);
            (this.RightArm = new ModelRenderer((Model)this)).setPos(-5.0f, 2.0f, 0.0f);
            this.RightArm.texOffs(16, 39).addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            this.RightArm.texOffs(0, 25).addBox(-5.0f, -4.0f, -4.0f, 6.0f, 6.0f, 8.0f, 0.0f, false);
            this.RightArm.texOffs(32, 0).addBox(-5.0f, 2.0f, -4.0f, 3.0f, 1.0f, 8.0f, 0.0f, false);
            final ModelRenderer cube_r7 = new ModelRenderer((Model)this);
            cube_r7.setPos(-4.0f, 8.5f, 0.0f);
            this.RightArm.addChild(cube_r7);
            this.setRotationAngle(cube_r7, 0.0f, 0.0f, -0.7418f);
            cube_r7.texOffs(29, 9).addBox(0.0f, -7.5f, -3.5f, 2.0f, 7.0f, 7.0f, 0.0f, false);
            final ModelRenderer cube_r8 = new ModelRenderer((Model)this);
            cube_r8.setPos(-4.0f, 4.5f, 0.0f);
            this.RightArm.addChild(cube_r8);
            this.setRotationAngle(cube_r8, 0.0f, 0.0f, -0.6545f);
            cube_r8.texOffs(29, 9).addBox(-1.0f, -6.5f, -3.5f, 2.0f, 7.0f, 7.0f, 0.0f, false);
            (this.LeftArm = new ModelRenderer((Model)this)).setPos(5.0f, 2.0f, 0.0f);
            this.LeftArm.texOffs(0, 25).addBox(-1.0f, -4.0f, -4.0f, 6.0f, 6.0f, 8.0f, 0.0f, false);
            this.LeftArm.texOffs(32, 0).addBox(2.0f, 2.0f, -4.0f, 3.0f, 1.0f, 8.0f, 0.0f, false);
            this.LeftArm.texOffs(16, 39).addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, true);
            final ModelRenderer cube_r9 = new ModelRenderer((Model)this);
            cube_r9.setPos(4.0f, 4.5f, 0.0f);
            this.LeftArm.addChild(cube_r9);
            this.setRotationAngle(cube_r9, 0.0f, 0.0f, 0.6109f);
            cube_r9.texOffs(29, 9).addBox(-1.0f, -6.5f, -3.5f, 2.0f, 7.0f, 7.0f, 0.0f, false);
            final ModelRenderer cube_r10 = new ModelRenderer((Model)this);
            cube_r10.setPos(4.0f, 8.5f, 0.0f);
            this.LeftArm.addChild(cube_r10);
            this.setRotationAngle(cube_r10, -0.0174f, -0.0013f, 0.7727f);
            cube_r10.texOffs(29, 9).addBox(-2.0f, -7.5f, -3.5f, 2.0f, 7.0f, 7.0f, 0.0f, false);
            (this.RightBoot = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightBoot.texOffs(0, 39).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            (this.LeftBoot = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftBoot.texOffs(0, 39).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, true);
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
            this.texWidth = (this.isLayer2() ? 64 : 128);
            this.texHeight = (this.isLayer2() ? 32 : 128);
            (this.Head = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Head.texOffs(26, 6).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, 1.0f, false);
            this.Head.texOffs(0, 14).addBox(5.0f, -5.0f, -5.0f, 1.0f, 3.0f, 3.0f, 0.0f, false);
            this.Head.texOffs(0, 0).addBox(-6.0f, -5.0f, -5.0f, 1.0f, 3.0f, 3.0f, 0.0f, false);
            final ModelRenderer cube_r1 = new ModelRenderer((Model)this);
            cube_r1.setPos(4.5161f, 0.0f, 0.1884f);
            this.Head.addChild(cube_r1);
            this.setRotationAngle(cube_r1, 0.0f, -0.6545f, 0.0f);
            cube_r1.texOffs(26, 0).addBox(-9.0438f, -3.3f, -4.2832f, 6.0f, 5.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r2 = new ModelRenderer((Model)this);
            cube_r2.setPos(-0.1275f, -5.95f, -6.3931f);
            this.Head.addChild(cube_r2);
            this.setRotationAngle(cube_r2, -0.5807f, -0.5704f, 0.3405f);
            cube_r2.texOffs(57, 48).addBox(-1.3533f, -2.05f, -1.8886f, 6.0f, 4.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r3 = new ModelRenderer((Model)this);
            cube_r3.setPos(-5.0f, 0.0f, -3.0f);
            this.Head.addChild(cube_r3);
            this.setRotationAngle(cube_r3, 0.0f, 0.6545f, 0.0f);
            cube_r3.texOffs(40, 0).addBox(1.1637f, -3.3f, -1.4569f, 6.0f, 5.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r4 = new ModelRenderer((Model)this);
            cube_r4.setPos(-0.1275f, -5.95f, -6.3931f);
            this.Head.addChild(cube_r4);
            this.setRotationAngle(cube_r4, -0.5807f, 0.5704f, -0.3405f);
            cube_r4.texOffs(72, 38).addBox(-4.7675f, -1.95f, -1.7312f, 6.0f, 4.0f, 1.0f, 0.0f, false);
            (this.Body = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Body.texOffs(0, 28).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 1.01f, false);
            this.Body.texOffs(58, 16).addBox(-4.0f, 1.0f, 3.0f, 8.0f, 9.0f, 2.0f, 0.0f, false);
            this.Body.texOffs(62, 30).addBox(-3.0f, 2.0f, 5.0f, 6.0f, 7.0f, 1.0f, 0.0f, false);
            this.Body.texOffs(27, 22).addBox(-5.5f, 11.0f, -3.5f, 11.0f, 2.0f, 7.0f, 0.0f, false);
            final ModelRenderer cube_r5 = new ModelRenderer((Model)this);
            cube_r5.setPos(0.0f, 6.2822f, -2.9637f);
            this.Body.addChild(cube_r5);
            this.setRotationAngle(cube_r5, 0.1745f, 0.0f, 0.0f);
            cube_r5.texOffs(0, 28).addBox(4.0f, -0.5f, -2.5f, 1.0f, 2.0f, 1.0f, 0.0f, false);
            cube_r5.texOffs(20, 28).addBox(4.0f, -3.5f, -2.5f, 1.0f, 2.0f, 1.0f, 0.0f, false);
            cube_r5.texOffs(24, 30).addBox(-5.0f, -2.0f, -2.5f, 1.0f, 2.0f, 1.0f, 0.0f, false);
            cube_r5.texOffs(42, 31).addBox(-4.0f, -4.0f, -2.5f, 8.0f, 6.0f, 1.0f, 0.0f, false);
            cube_r5.texOffs(48, 38).addBox(-5.0f, -5.0f, -1.5f, 10.0f, 8.0f, 2.0f, 0.0f, false);
            (this.RightArm = new ModelRenderer((Model)this)).setPos(-5.0f, 2.0f, 0.0f);
            this.RightArm.texOffs(60, 62).addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            final ModelRenderer cube_r6 = new ModelRenderer((Model)this);
            cube_r6.setPos(0.0f, 0.0f, 0.0f);
            this.RightArm.addChild(cube_r6);
            this.setRotationAngle(cube_r6, 0.0f, 0.0f, 0.2618f);
            cube_r6.texOffs(9, 68).addBox(-7.0f, -1.0f, -3.5f, 2.0f, 5.0f, 7.0f, 0.0f, false);
            cube_r6.texOffs(0, 44).addBox(-3.0f, 1.0f, -3.5f, 2.0f, 8.0f, 7.0f, 0.0f, false);
            cube_r6.texOffs(46, 51).addBox(-5.0f, -1.0f, -3.5f, 2.0f, 8.0f, 7.0f, 0.0f, false);
            final ModelRenderer cube_r7 = new ModelRenderer((Model)this);
            cube_r7.setPos(0.0f, 0.0f, 0.0f);
            this.RightArm.addChild(cube_r7);
            this.setRotationAngle(cube_r7, 0.0f, 0.0f, 0.6545f);
            cube_r7.texOffs(16, 40).addBox(-7.0f, 2.0f, -4.0f, 5.0f, 1.0f, 8.0f, 0.0f, false);
            cube_r7.texOffs(0, 14).addBox(-8.0f, -4.0f, -4.0f, 9.0f, 6.0f, 8.0f, 0.0f, false);
            (this.LeftArm = new ModelRenderer((Model)this)).setPos(5.0f, 2.0f, 0.0f);
            this.LeftArm.texOffs(32, 62).addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            final ModelRenderer cube_r8 = new ModelRenderer((Model)this);
            cube_r8.setPos(2.75f, 0.75f, 0.0f);
            this.LeftArm.addChild(cube_r8);
            this.setRotationAngle(cube_r8, 0.0f, 0.0f, -0.6545f);
            cube_r8.texOffs(24, 31).addBox(0.25f, -0.75f, -4.0f, 5.0f, 1.0f, 8.0f, 0.0f, false);
            cube_r8.texOffs(0, 0).addBox(-2.75f, -6.75f, -4.0f, 9.0f, 6.0f, 8.0f, 0.0f, false);
            final ModelRenderer cube_r9 = new ModelRenderer((Model)this);
            cube_r9.setPos(4.0f, 1.5f, 0.0f);
            this.LeftArm.addChild(cube_r9);
            this.setRotationAngle(cube_r9, 0.0f, 0.0f, -0.2618f);
            cube_r9.texOffs(35, 42).addBox(-3.0f, -2.5f, -3.5f, 2.0f, 9.0f, 7.0f, 0.0f, false);
            cube_r9.texOffs(64, 48).addBox(1.0f, -3.5f, -3.5f, 2.0f, 5.0f, 7.0f, 0.0f, false);
            cube_r9.texOffs(18, 51).addBox(-1.0f, -3.5f, -3.5f, 2.0f, 8.0f, 7.0f, 0.0f, false);
            (this.RightBoot = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightBoot.texOffs(0, 59).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            (this.LeftBoot = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftBoot.texOffs(58, 0).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            (this.Belt = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Belt.texOffs(16, 16).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 0.51f, false);
            (this.RightLeg = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightLeg.texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, false);
            (this.LeftLeg = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftLeg.texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, true);
        }
    }
}
