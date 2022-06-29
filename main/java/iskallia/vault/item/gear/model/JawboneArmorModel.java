// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.item.gear.model;

import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.LivingEntity;

public class JawboneArmorModel
{
    public static class Variant1<T extends LivingEntity> extends VaultGearModel<T>
    {
        public Variant1(final float modelSize, final EquipmentSlotType slotType) {
            super(modelSize, slotType);
            this.texWidth = (this.isLayer2() ? 64 : 128);
            this.texHeight = (this.isLayer2() ? 64 : 128);
            (this.Head = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Head.texOffs(0, 15).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, 1.0f, false);
            this.Head.texOffs(48, 26).addBox(-5.0f, -4.0f, -7.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(32, 49).addBox(-2.0f, -3.0f, -7.0f, 1.0f, 2.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(46, 10).addBox(4.0f, -4.0f, -7.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(7, 0).addBox(1.0f, -3.0f, -7.0f, 1.0f, 2.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r1 = new ModelRenderer((Model)this);
            cube_r1.setPos(0.0f, -2.0f, -1.5f);
            this.Head.addChild(cube_r1);
            this.setRotationAngle(cube_r1, 0.48f, 0.0f, 0.0f);
            cube_r1.texOffs(0, 0).addBox(-6.0f, -2.0f, -5.5f, 12.0f, 4.0f, 11.0f, 0.0f, false);
            final ModelRenderer cube_r2 = new ModelRenderer((Model)this);
            cube_r2.setPos(2.0f, 6.5f, 7.5f);
            this.Head.addChild(cube_r2);
            this.setRotationAngle(cube_r2, 0.8727f, 0.0f, 0.0f);
            cube_r2.texOffs(56, 35).addBox(-1.0f, -10.5f, 4.5f, 2.0f, 7.0f, 3.0f, 0.0f, false);
            cube_r2.texOffs(0, 0).addBox(-5.0f, -10.5f, 4.5f, 2.0f, 7.0f, 3.0f, 0.0f, false);
            (this.Body = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Body.texOffs(32, 33).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 1.01f, false);
            this.Body.texOffs(24, 15).addBox(-4.0f, 1.0f, -4.0f, 8.0f, 7.0f, 1.0f, 0.0f, false);
            this.Body.texOffs(24, 33).addBox(-2.0f, 8.0f, -4.0f, 4.0f, 3.0f, 1.0f, 0.0f, false);
            this.Body.texOffs(56, 45).addBox(-2.0f, 8.0f, 3.0f, 4.0f, 3.0f, 2.0f, 0.0f, false);
            this.Body.texOffs(12, 41).addBox(-1.0f, 11.0f, 3.0f, 2.0f, 3.0f, 1.0f, 0.0f, false);
            this.Body.texOffs(35, 0).addBox(-4.0f, 1.0f, 3.0f, 8.0f, 7.0f, 3.0f, 0.0f, false);
            final ModelRenderer cube_r3 = new ModelRenderer((Model)this);
            cube_r3.setPos(-2.0f, 6.5f, 7.5f);
            this.Body.addChild(cube_r3);
            this.setRotationAngle(cube_r3, 0.8727f, 0.0f, 0.0f);
            cube_r3.texOffs(0, 57).addBox(-1.0f, -3.5f, -1.5f, 2.0f, 7.0f, 3.0f, 0.0f, false);
            cube_r3.texOffs(57, 0).addBox(3.0f, -3.5f, -1.5f, 2.0f, 7.0f, 3.0f, 0.0f, false);
            (this.RightArm = new ModelRenderer((Model)this)).setPos(-5.0f, 2.0f, 0.0f);
            this.RightArm.texOffs(32, 49).addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            this.RightArm.texOffs(42, 19).addBox(-5.0f, -2.0f, 2.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            this.RightArm.texOffs(42, 15).addBox(-5.0f, -2.0f, -3.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r4 = new ModelRenderer((Model)this);
            cube_r4.setPos(-3.5f, 13.5f, 0.0f);
            this.RightArm.addChild(cube_r4);
            this.setRotationAngle(cube_r4, 0.0f, 0.0f, -0.48f);
            cube_r4.texOffs(0, 31).addBox(-0.25f, -3.5f, -1.0f, 1.0f, 5.0f, 2.0f, 0.0f, false);
            final ModelRenderer cube_r5 = new ModelRenderer((Model)this);
            cube_r5.setPos(-4.0f, 8.5f, 0.0f);
            this.RightArm.addChild(cube_r5);
            this.setRotationAngle(cube_r5, 0.0f, 0.0f, 0.3054f);
            cube_r5.texOffs(56, 26).addBox(-1.0f, -2.5f, -2.0f, 2.0f, 5.0f, 4.0f, 0.0f, false);
            final ModelRenderer cube_r6 = new ModelRenderer((Model)this);
            cube_r6.setPos(-1.5f, 0.0f, 0.0f);
            this.RightArm.addChild(cube_r6);
            this.setRotationAngle(cube_r6, 0.0f, 0.0f, -0.3927f);
            cube_r6.texOffs(0, 31).addBox(-4.5f, -1.0f, -4.0f, 8.0f, 2.0f, 8.0f, 0.0f, false);
            (this.LeftArm = new ModelRenderer((Model)this)).setPos(5.0f, 2.0f, 0.0f);
            this.LeftArm.texOffs(28, 41).addBox(4.0f, -2.0f, -3.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            this.LeftArm.texOffs(0, 41).addBox(4.0f, -2.0f, 2.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            this.LeftArm.texOffs(48, 10).addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            final ModelRenderer cube_r7 = new ModelRenderer((Model)this);
            cube_r7.setPos(4.13f, 10.4988f, 0.0f);
            this.LeftArm.addChild(cube_r7);
            this.setRotationAngle(cube_r7, 3.1416f, 0.0f, 2.8362f);
            cube_r7.texOffs(48, 49).addBox(-1.725f, -4.3672f, -2.0f, 2.0f, 5.0f, 4.0f, 0.0f, false);
            final ModelRenderer cube_r8 = new ModelRenderer((Model)this);
            cube_r8.setPos(4.13f, 10.4988f, 0.0f);
            this.LeftArm.addChild(cube_r8);
            this.setRotationAngle(cube_r8, -3.1416f, 0.0f, -2.6616f);
            cube_r8.texOffs(0, 15).addBox(-1.3076f, -0.667f, -1.0f, 1.0f, 5.0f, 2.0f, 0.0f, false);
            final ModelRenderer cube_r9 = new ModelRenderer((Model)this);
            cube_r9.setPos(1.5f, 0.0f, 0.0f);
            this.LeftArm.addChild(cube_r9);
            this.setRotationAngle(cube_r9, 0.0f, 0.0f, 0.3927f);
            cube_r9.texOffs(24, 23).addBox(-3.5f, -1.0f, -4.0f, 8.0f, 2.0f, 8.0f, 0.0f, false);
            (this.RightBoot = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightBoot.texOffs(16, 41).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            this.RightBoot.texOffs(20, 57).addBox(-2.0f, 8.0f, -4.0f, 4.0f, 5.0f, 1.0f, 0.0f, false);
            (this.LeftBoot = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftBoot.texOffs(10, 57).addBox(-1.8f, 8.0f, -4.0f, 4.0f, 5.0f, 1.0f, 0.0f, false);
            this.LeftBoot.texOffs(0, 41).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            (this.Belt = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Belt.texOffs(0, 0).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 0.51f, false);
            this.Belt.texOffs(22, 17).addBox(-2.0f, 10.0f, -5.0f, 4.0f, 3.0f, 2.0f, 0.0f, false);
            final ModelRenderer cube_r1_l2 = new ModelRenderer((Model)this);
            cube_r1_l2.setPos(-3.5f, 12.5f, 0.0f);
            this.Belt.addChild(cube_r1_l2);
            this.setRotationAngle(cube_r1_l2, 0.0f, 0.0f, 0.3927f);
            cube_r1_l2.texOffs(0, 16).addBox(3.5f, -3.5f, -4.0f, 7.0f, 1.0f, 8.0f, 0.0f, false);
            final ModelRenderer cube_r2_l2 = new ModelRenderer((Model)this);
            cube_r2_l2.setPos(-3.5f, 12.5f, 0.0f);
            this.Belt.addChild(cube_r2_l2);
            this.setRotationAngle(cube_r2_l2, 0.0f, 0.0f, -0.3927f);
            cube_r2_l2.texOffs(22, 8).addBox(-3.5f, -0.5f, -4.0f, 7.0f, 1.0f, 8.0f, 0.0f, false);
            (this.RightLeg = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightLeg.texOffs(16, 25).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, false);
            (this.LeftLeg = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftLeg.texOffs(0, 25).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, false);
        }
    }
}
