// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.item.gear.model;

import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.LivingEntity;

public class AngelArmorModel
{
    public static class Variant1<T extends LivingEntity> extends VaultGearModel<T>
    {
        public Variant1(final float modelSize, final EquipmentSlotType slotType) {
            super(modelSize, slotType);
            this.texWidth = (this.isLayer2() ? 64 : 128);
            this.texHeight = (this.isLayer2() ? 32 : 128);
            (this.Head = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Head.texOffs(32, 22).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, 1.0f, false);
            final ModelRenderer cube_r1 = new ModelRenderer((Model)this);
            cube_r1.setPos(5.0f, -8.0f, 0.0f);
            this.Head.addChild(cube_r1);
            this.setRotationAngle(cube_r1, 0.0f, 0.0f, 0.2618f);
            cube_r1.texOffs(15, 35).addBox(1.0f, -4.0f, -3.0f, 0.0f, 8.0f, 6.0f, 0.0f, false);
            final ModelRenderer cube_r2 = new ModelRenderer((Model)this);
            cube_r2.setPos(-5.0f, -8.0f, 0.0f);
            this.Head.addChild(cube_r2);
            this.setRotationAngle(cube_r2, 0.0f, 0.0f, -0.2618f);
            cube_r2.texOffs(27, 35).addBox(-1.0f, -4.0f, -3.0f, 0.0f, 8.0f, 6.0f, 0.0f, false);
            final ModelRenderer cube_r3 = new ModelRenderer((Model)this);
            cube_r3.setPos(0.0f, -12.5f, 0.0f);
            this.Head.addChild(cube_r3);
            this.setRotationAngle(cube_r3, 0.1745f, 0.0f, 0.0f);
            cube_r3.texOffs(30, 19).addBox(-3.0f, -0.5f, 3.0f, 6.0f, 1.0f, 1.0f, 0.0f, false);
            cube_r3.texOffs(15, 49).addBox(-3.0f, -0.5f, -4.0f, 6.0f, 1.0f, 1.0f, 0.0f, false);
            cube_r3.texOffs(72, 73).addBox(3.0f, -0.5f, -3.0f, 1.0f, 1.0f, 6.0f, 0.0f, false);
            cube_r3.texOffs(74, 40).addBox(-4.0f, -0.5f, -3.0f, 1.0f, 1.0f, 6.0f, 0.0f, false);
            final ModelRenderer cube_r4 = new ModelRenderer((Model)this);
            cube_r4.setPos(-0.0184f, -4.0f, -5.5f);
            this.Head.addChild(cube_r4);
            this.setRotationAngle(cube_r4, 0.1886f, -0.3864f, -0.0718f);
            cube_r4.texOffs(0, 0).addBox(-0.5429f, -5.0f, -1.3107f, 5.0f, 10.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r5 = new ModelRenderer((Model)this);
            cube_r5.setPos(-0.0184f, -4.0f, -5.5f);
            this.Head.addChild(cube_r5);
            this.setRotationAngle(cube_r5, 0.1886f, 0.3864f, 0.0718f);
            cube_r5.texOffs(32, 74).addBox(-4.4571f, -5.0f, -1.3107f, 5.0f, 10.0f, 1.0f, 0.0f, false);
            (this.Body = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Body.texOffs(22, 58).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 1.01f, false);
            this.Body.texOffs(0, 0).addBox(-6.0f, -2.0f, -7.0f, 12.0f, 6.0f, 13.0f, 0.0f, false);
            final ModelRenderer cube_r6 = new ModelRenderer((Model)this);
            cube_r6.setPos(15.1809f, -4.3373f, 3.9788f);
            this.Body.addChild(cube_r6);
            this.setRotationAngle(cube_r6, -0.6485f, -0.3189f, 0.2333f);
            cube_r6.texOffs(29, 38).addBox(-1.0f, -4.5f, -5.5f, 2.0f, 9.0f, 11.0f, 0.0f, false);
            final ModelRenderer cube_r7 = new ModelRenderer((Model)this);
            cube_r7.setPos(-15.1809f, -4.3373f, 3.9788f);
            this.Body.addChild(cube_r7);
            this.setRotationAngle(cube_r7, -0.6485f, 0.3189f, -0.2333f);
            cube_r7.texOffs(0, 41).addBox(-1.0f, -4.5f, -5.5f, 2.0f, 9.0f, 11.0f, 0.0f, false);
            final ModelRenderer cube_r8 = new ModelRenderer((Model)this);
            cube_r8.setPos(4.0f, -1.7769f, 7.6355f);
            this.Body.addChild(cube_r8);
            this.setRotationAngle(cube_r8, -0.6109f, 0.0f, 0.0f);
            cube_r8.texOffs(44, 38).addBox(-18.0f, -4.5f, -1.0f, 13.0f, 9.0f, 2.0f, 0.0f, false);
            cube_r8.texOffs(56, 13).addBox(-3.0f, -4.5f, -1.0f, 13.0f, 9.0f, 2.0f, 0.0f, false);
            final ModelRenderer cube_r9 = new ModelRenderer((Model)this);
            cube_r9.setPos(0.0f, 7.0f, 4.0f);
            this.Body.addChild(cube_r9);
            this.setRotationAngle(cube_r9, -0.3927f, 0.0f, 0.0f);
            cube_r9.texOffs(71, 49).addBox(-2.0f, -4.0f, -2.0f, 4.0f, 7.0f, 2.0f, 0.0f, false);
            final ModelRenderer cube_r10 = new ModelRenderer((Model)this);
            cube_r10.setPos(2.35f, 5.0f, -5.5f);
            this.Body.addChild(cube_r10);
            this.setRotationAngle(cube_r10, 0.6139f, -0.5198f, -0.3368f);
            cube_r10.texOffs(69, 0).addBox(-3.0f, -3.0f, -0.5f, 7.0f, 10.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r11 = new ModelRenderer((Model)this);
            cube_r11.setPos(-1.9913f, 5.0f, -5.5f);
            this.Body.addChild(cube_r11);
            this.setRotationAngle(cube_r11, 0.6139f, 0.5198f, 0.3368f);
            cube_r11.texOffs(16, 74).addBox(-4.0f, -3.0f, -0.5f, 7.0f, 10.0f, 1.0f, 0.0f, false);
            (this.RightArm = new ModelRenderer((Model)this)).setPos(-5.0f, 2.0f, 0.0f);
            this.RightArm.texOffs(72, 24).addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            final ModelRenderer cube_r12 = new ModelRenderer((Model)this);
            cube_r12.setPos(-5.0f, -2.5f, 0.0f);
            this.RightArm.addChild(cube_r12);
            this.setRotationAngle(cube_r12, 0.0f, 0.0f, -0.7418f);
            cube_r12.texOffs(0, 19).addBox(-6.0f, 4.5f, -5.0f, 10.0f, 1.0f, 10.0f, 0.0f, false);
            cube_r12.texOffs(47, 50).addBox(-4.0f, -0.5f, -4.0f, 8.0f, 5.0f, 8.0f, 0.0f, false);
            (this.LeftArm = new ModelRenderer((Model)this)).setPos(5.0f, 2.0f, 0.0f);
            this.LeftArm.texOffs(62, 63).addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            final ModelRenderer cube_r13 = new ModelRenderer((Model)this);
            cube_r13.setPos(5.0f, -2.5f, 0.0f);
            this.LeftArm.addChild(cube_r13);
            this.setRotationAngle(cube_r13, 0.0f, 0.0f, 0.7418f);
            cube_r13.texOffs(0, 30).addBox(-4.0f, 4.5f, -5.0f, 10.0f, 1.0f, 10.0f, 0.0f, false);
            cube_r13.texOffs(37, 0).addBox(-4.0f, -0.5f, -4.0f, 8.0f, 5.0f, 8.0f, 0.0f, false);
            (this.RightBoot = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightBoot.texOffs(46, 63).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            final ModelRenderer cube_r14 = new ModelRenderer((Model)this);
            cube_r14.setPos(3.05f, 8.0f, 0.5f);
            this.RightBoot.addChild(cube_r14);
            this.setRotationAngle(cube_r14, 0.0f, 0.0f, 0.2618f);
            cube_r14.texOffs(0, 16).addBox(0.75f, -3.0f, -1.5f, 0.0f, 6.0f, 3.0f, 0.0f, false);
            final ModelRenderer cube_r15 = new ModelRenderer((Model)this);
            cube_r15.setPos(-3.2f, 8.0f, 0.5f);
            this.RightBoot.addChild(cube_r15);
            this.setRotationAngle(cube_r15, 0.0f, 0.0f, -0.2618f);
            cube_r15.texOffs(0, 27).addBox(-0.75f, -3.0f, -1.5f, 0.0f, 6.0f, 3.0f, 0.0f, false);
            (this.LeftBoot = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftBoot.texOffs(0, 61).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            final ModelRenderer cube_r16 = new ModelRenderer((Model)this);
            cube_r16.setPos(-3.0f, 8.0f, 0.5f);
            this.LeftBoot.addChild(cube_r16);
            this.setRotationAngle(cube_r16, 0.0f, 0.0f, -0.2618f);
            cube_r16.texOffs(30, 18).addBox(-0.75f, -3.0f, -1.5f, 0.0f, 6.0f, 3.0f, 0.0f, false);
            final ModelRenderer cube_r17 = new ModelRenderer((Model)this);
            cube_r17.setPos(3.0f, 8.0f, 0.5f);
            this.LeftBoot.addChild(cube_r17);
            this.setRotationAngle(cube_r17, 0.0f, 0.0f, 0.2618f);
            cube_r17.texOffs(0, 38).addBox(1.0f, -3.0f, -1.5f, 0.0f, 6.0f, 3.0f, 0.0f, false);
            (this.Belt = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Belt.texOffs(16, 16).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 0.51f, false);
            (this.RightLeg = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightLeg.texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, false);
            (this.LeftLeg = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftLeg.texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, true);
        }
    }
}
