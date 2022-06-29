
package iskallia.vault.item.gear.specials;

import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.inventory.EquipmentSlotType;
import iskallia.vault.item.gear.model.VaultGearModel;
import net.minecraft.entity.LivingEntity;

public class FairyArmorModel<T extends LivingEntity> extends VaultGearModel<T> {
    public FairyArmorModel(final float modelSize, final EquipmentSlotType slotType) {
        super(modelSize, slotType);
        this.texWidth = (this.isLayer2() ? 128 : 128);
        this.texHeight = (this.isLayer2() ? 128 : 128);
        (this.Head = new ModelRenderer((Model) this)).setPos(0.0f, 0.0f, 0.0f);
        this.Head.texOffs(22, 29).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, 1.0f, false);
        this.Head.texOffs(39, 24).addBox(-4.0f, -6.0f, -6.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(39, 22).addBox(-1.0f, -8.0f, -6.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(16, 39).addBox(2.0f, -6.5f, -6.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(12, 39).addBox(5.0f, -6.5f, -4.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(8, 39).addBox(5.0f, -7.5f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(4, 39).addBox(5.0f, -5.5f, 3.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(8, 37).addBox(3.0f, -5.5f, 5.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(4, 37).addBox(0.0f, -7.5f, 5.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(0, 37).addBox(-3.25f, -6.0f, 5.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(0, 39).addBox(-6.0f, -5.5f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(16, 37).addBox(-6.0f, -7.5f, -3.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(12, 37).addBox(-6.0f, -7.5f, 3.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Head.texOffs(0, 0).addBox(-11.0f, -6.0f, -11.0f, 22.0f, 0.0f, 21.0f, 0.0f, false);
        (this.Body = new ModelRenderer((Model) this)).setPos(0.0f, 0.0f, 0.0f);
        this.Body.texOffs(0, 41).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 1.01f, false);
        this.Body.texOffs(36, 27).addBox(-4.0f, 5.0f, -4.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Body.texOffs(36, 25).addBox(-1.0f, 3.0f, -4.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Body.texOffs(36, 21).addBox(1.0f, 5.0f, -4.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Body.texOffs(33, 26).addBox(3.0f, 3.0f, -4.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Body.texOffs(36, 23).addBox(-3.0f, 2.0f, -4.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Body.texOffs(30, 23).addBox(3.0f, 4.0f, 3.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Body.texOffs(30, 25).addBox(0.0f, 5.0f, 3.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Body.texOffs(30, 27).addBox(1.0f, 2.0f, 3.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Body.texOffs(33, 22).addBox(-3.0f, 2.0f, 3.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Body.texOffs(33, 24).addBox(-4.0f, 5.0f, 3.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        final ModelRenderer cube_r1 = new ModelRenderer((Model) this);
        cube_r1.setPos(-2.0f, 2.1321f, 6.4042f);
        this.Body.addChild(cube_r1);
        this.setRotationAngle(cube_r1, 0.982f, -0.1733f, -0.2528f);
        cube_r1.texOffs(0, 6).addBox(0.0f, -4.0f, -7.5f, 0.0f, 8.0f, 15.0f, 0.0f, false);
        final ModelRenderer cube_r2 = new ModelRenderer((Model) this);
        cube_r2.setPos(2.0f, 2.1321f, 6.4042f);
        this.Body.addChild(cube_r2);
        this.setRotationAngle(cube_r2, 0.982f, 0.1733f, 0.2528f);
        cube_r2.texOffs(0, 14).addBox(0.0f, -4.0f, -7.5f, 0.0f, 8.0f, 15.0f, 0.0f, false);
        (this.RightArm = new ModelRenderer((Model) this)).setPos(-5.0f, 2.0f, 0.0f);
        this.RightArm.texOffs(46, 21).addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
        this.RightArm.texOffs(16, 11).addBox(-3.0f, 3.0f, -4.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.RightArm.texOffs(16, 9).addBox(-1.0f, 6.0f, -4.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.RightArm.texOffs(16, 7).addBox(-3.0f, 3.0f, 3.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.RightArm.texOffs(8, 16).addBox(-1.0f, 6.0f, 3.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.RightArm.texOffs(15, 19).addBox(-5.0f, 3.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.RightArm.texOffs(12, 18).addBox(-5.0f, 7.0f, -2.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        (this.LeftArm = new ModelRenderer((Model) this)).setPos(5.0f, 2.0f, 0.0f);
        this.LeftArm.texOffs(15, 17).addBox(4.0f, 3.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.LeftArm.texOffs(0, 18).addBox(1.0f, 7.0f, 3.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.LeftArm.texOffs(15, 15).addBox(0.0f, 4.0f, 3.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.LeftArm.texOffs(0, 2).addBox(0.0f, 4.0f, -4.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.LeftArm.texOffs(12, 2).addBox(1.0f, 7.0f, -4.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.LeftArm.texOffs(16, 3).addBox(4.0f, 7.0f, -2.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.LeftArm.texOffs(40, 45).addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
        (this.RightBoot = new ModelRenderer((Model) this)).setPos(-1.9f, 12.0f, 0.0f);
        this.RightBoot.texOffs(24, 45).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
        (this.LeftBoot = new ModelRenderer((Model) this)).setPos(1.9f, 12.0f, 0.0f);
        this.LeftBoot.texOffs(0, 0).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
        (this.Belt = new ModelRenderer((Model) this)).setPos(0.0f, 0.0f, 0.0f);
        this.Belt.texOffs(0, 0).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 0.51f, false);
        this.Belt.texOffs(0, 16).addBox(-5.0f, 10.0f, -3.0f, 10.0f, 1.0f, 6.0f, 0.0f, false);
        final ModelRenderer cube_l2_r1 = new ModelRenderer((Model) this);
        cube_l2_r1.setPos(-5.5f, 12.5f, 3.5f);
        this.Belt.addChild(cube_l2_r1);
        this.setRotationAngle(cube_l2_r1, -0.4971f, -0.1719f, -0.3053f);
        cube_l2_r1.texOffs(42, 38).addBox(-2.5f, 1.5f, -2.5f, 5.0f, 1.0f, 5.0f, 0.0f, false);
        cube_l2_r1.texOffs(15, 43).addBox(-2.5f, -0.5f, -2.5f, 5.0f, 1.0f, 5.0f, 0.0f, false);
        final ModelRenderer cube_l2_r2 = new ModelRenderer((Model) this);
        cube_l2_r2.setPos(-5.5f, 12.5f, -3.5f);
        this.Belt.addChild(cube_l2_r2);
        this.setRotationAngle(cube_l2_r2, 0.3491f, 0.0f, -0.2618f);
        cube_l2_r2.texOffs(0, 39).addBox(-2.5f, 1.5f, -2.5f, 5.0f, 1.0f, 5.0f, 0.0f, false);
        cube_l2_r2.texOffs(30, 44).addBox(-2.5f, -0.5f, -2.5f, 5.0f, 1.0f, 5.0f, 0.0f, false);
        final ModelRenderer cube_l2_r3 = new ModelRenderer((Model) this);
        cube_l2_r3.setPos(5.5f, 12.5f, -3.5f);
        this.Belt.addChild(cube_l2_r3);
        this.setRotationAngle(cube_l2_r3, 0.4232f, -0.1096f, 0.2382f);
        cube_l2_r3.texOffs(27, 37).addBox(-2.5f, 1.5f, -2.5f, 5.0f, 1.0f, 5.0f, 0.0f, false);
        cube_l2_r3.texOffs(0, 45).addBox(-2.5f, -0.5f, -2.5f, 5.0f, 1.0f, 5.0f, 0.0f, false);
        final ModelRenderer cube_l2_r4 = new ModelRenderer((Model) this);
        cube_l2_r4.setPos(5.5f, 12.5f, 3.5f);
        this.Belt.addChild(cube_l2_r4);
        this.setRotationAngle(cube_l2_r4, -0.4363f, 0.0f, 0.3927f);
        cube_l2_r4.texOffs(32, 31).addBox(-2.5f, 1.5f, -2.5f, 5.0f, 1.0f, 5.0f, 0.0f, false);
        cube_l2_r4.texOffs(45, 45).addBox(-2.5f, -0.5f, -2.5f, 5.0f, 1.0f, 5.0f, 0.0f, false);
        final ModelRenderer cube_l2_r5 = new ModelRenderer((Model) this);
        cube_l2_r5.setPos(0.0f, 15.0341f, 3.2412f);
        this.Belt.addChild(cube_l2_r5);
        this.setRotationAngle(cube_l2_r5, 0.5236f, 0.0f, 0.0f);
        cube_l2_r5.texOffs(24, 0).addBox(-5.0f, -4.0f, 0.25f, 10.0f, 8.0f, 1.0f, 0.0f, false);
        final ModelRenderer cube_l2_r6 = new ModelRenderer((Model) this);
        cube_l2_r6.setPos(0.0f, 13.0f, 3.5f);
        this.Belt.addChild(cube_l2_r6);
        this.setRotationAngle(cube_l2_r6, 0.7418f, 0.0f, 0.0f);
        cube_l2_r6.texOffs(32, 17).addBox(-5.0f, -3.0f, 0.2f, 10.0f, 6.0f, 1.0f, 0.0f, false);
        final ModelRenderer cube_l2_r7 = new ModelRenderer((Model) this);
        cube_l2_r7.setPos(0.0f, 16.0f, -3.5f);
        this.Belt.addChild(cube_l2_r7);
        this.setRotationAngle(cube_l2_r7, -0.4363f, 0.0f, 0.0f);
        cube_l2_r7.texOffs(26, 9).addBox(-5.0f, -4.0f, -1.5f, 10.0f, 7.0f, 1.0f, 0.0f, false);
        final ModelRenderer cube_l2_r8 = new ModelRenderer((Model) this);
        cube_l2_r8.setPos(0.0f, 13.0f, -3.5f);
        this.Belt.addChild(cube_l2_r8);
        this.setRotationAngle(cube_l2_r8, -0.7418f, 0.0f, 0.0f);
        cube_l2_r8.texOffs(32, 24).addBox(-5.0f, -3.0f, -1.25f, 10.0f, 6.0f, 1.0f, 0.0f, false);
        final ModelRenderer cube_l2_r9 = new ModelRenderer((Model) this);
        cube_l2_r9.setPos(5.5f, 16.0f, 0.0f);
        this.Belt.addChild(cube_l2_r9);
        this.setRotationAngle(cube_l2_r9, 0.0f, 0.0f, -0.6109f);
        cube_l2_r9.texOffs(14, 49).addBox(0.5f, -4.0f, -3.0f, 1.0f, 6.0f, 6.0f, 0.0f, false);
        final ModelRenderer cube_l2_r10 = new ModelRenderer((Model) this);
        cube_l2_r10.setPos(-5.5f, 16.0f, 0.0f);
        this.Belt.addChild(cube_l2_r10);
        this.setRotationAngle(cube_l2_r10, 0.0f, 0.0f, 0.6981f);
        cube_l2_r10.texOffs(28, 50).addBox(-2.0f, -3.5f, -3.0f, 1.0f, 6.0f, 6.0f, 0.0f, false);
        final ModelRenderer cube_l2_r11 = new ModelRenderer((Model) this);
        cube_l2_r11.setPos(-5.5f, 13.0f, 0.0f);
        this.Belt.addChild(cube_l2_r11);
        this.setRotationAngle(cube_l2_r11, 0.0f, 0.0f, 0.8727f);
        cube_l2_r11.texOffs(0, 51).addBox(-1.5f, -3.0f, -3.0f, 1.0f, 6.0f, 6.0f, 0.0f, false);
        final ModelRenderer cube_l2_r12 = new ModelRenderer((Model) this);
        cube_l2_r12.setPos(5.5f, 13.0f, 0.0f);
        this.Belt.addChild(cube_l2_r12);
        this.setRotationAngle(cube_l2_r12, 0.0f, 0.0f, -0.7854f);
        cube_l2_r12.texOffs(48, 0).addBox(0.5f, -3.0f, -3.0f, 1.0f, 6.0f, 6.0f, 0.0f, false);
        (this.RightLeg = new ModelRenderer((Model) this)).setPos(-1.9f, 12.0f, 0.0f);
        this.RightLeg.texOffs(16, 23).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, false);
        (this.LeftLeg = new ModelRenderer((Model) this)).setPos(1.9f, 12.0f, 0.0f);
        this.LeftLeg.texOffs(0, 23).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, false);
    }
}