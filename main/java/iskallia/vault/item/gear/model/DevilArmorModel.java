
package iskallia.vault.item.gear.model;

import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.LivingEntity;

public class DevilArmorModel {
    public static class Variant1<T extends LivingEntity> extends VaultGearModel<T> {
        public Variant1(final float modelSize, final EquipmentSlotType slotType) {
            super(modelSize, slotType);
            this.texWidth = (this.isLayer2() ? 64 : 128);
            this.texHeight = (this.isLayer2() ? 32 : 128);
            (this.Head = new ModelRenderer((Model) this)).setPos(0.0f, 0.0f, 0.0f);
            this.Head.texOffs(32, 22).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, 1.0f, false);
            this.Head.texOffs(0, 61).addBox(-8.0f, -15.0f, -3.0f, 3.0f, 8.0f, 6.0f, 0.0f, false);
            this.Head.texOffs(0, 61).addBox(5.0f, -15.0f, -3.0f, 3.0f, 8.0f, 6.0f, 0.0f, false);
            this.Head.texOffs(0, 0).addBox(5.0f, -23.0f, -2.0f, 2.0f, 8.0f, 4.0f, 0.0f, false);
            this.Head.texOffs(0, 0).addBox(-7.0f, -23.0f, -2.0f, 2.0f, 8.0f, 4.0f, 0.0f, false);
            this.Head.texOffs(15, 41).addBox(-2.0f, -25.0f, -2.0f, 4.0f, 4.0f, 4.0f, 0.0f, false);
            final ModelRenderer cube_r1 = new ModelRenderer((Model) this);
            cube_r1.setPos(-0.0184f, -7.0f, -5.5f);
            this.Head.addChild(cube_r1);
            this.setRotationAngle(cube_r1, -0.4674f, -0.3542f, 0.1733f);
            cube_r1.texOffs(74, 40).addBox(-0.5429f, -5.0f, -1.3107f, 5.0f, 10.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r2 = new ModelRenderer((Model) this);
            cube_r2.setPos(-0.0184f, -7.0f, -5.5f);
            this.Head.addChild(cube_r2);
            this.setRotationAngle(cube_r2, -0.4674f, 0.3542f, -0.1733f);
            cube_r2.texOffs(0, 75).addBox(-4.4571f, -5.0f, -1.3107f, 5.0f, 10.0f, 1.0f, 0.0f, false);
            (this.Body = new ModelRenderer((Model) this)).setPos(0.0f, 0.0f, 0.0f);
            this.Body.texOffs(22, 58).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 1.01f, false);
            this.Body.texOffs(0, 0).addBox(-6.0f, -2.0f, -7.0f, 12.0f, 6.0f, 13.0f, 0.0f, false);
            final ModelRenderer cube_r3 = new ModelRenderer((Model) this);
            cube_r3.setPos(11.3404f, 0.6929f, 6.8071f);
            this.Body.addChild(cube_r3);
            this.setRotationAngle(cube_r3, 1.113f, -0.6783f, -0.3741f);
            cube_r3.texOffs(29, 38).addBox(1.694f, -4.5f, -9.0318f, 2.0f, 9.0f, 11.0f, 0.0f, false);
            final ModelRenderer cube_r4 = new ModelRenderer((Model) this);
            cube_r4.setPos(-11.3404f, 0.9429f, 7.8071f);
            this.Body.addChild(cube_r4);
            this.setRotationAngle(cube_r4, 1.1569f, 0.7276f, 0.3912f);
            cube_r4.texOffs(0, 41).addBox(-3.694f, -4.5f, -9.0318f, 2.0f, 9.0f, 11.0f, 0.0f, false);
            final ModelRenderer cube_r5 = new ModelRenderer((Model) this);
            cube_r5.setPos(-11.3404f, 0.9429f, 7.8071f);
            this.Body.addChild(cube_r5);
            this.setRotationAngle(cube_r5, 0.9096f, 0.523f, -0.0252f);
            cube_r5.texOffs(44, 38).addBox(-2.6596f, -4.5f, 1.232f, 13.0f, 9.0f, 2.0f, 0.0f, false);
            final ModelRenderer cube_r6 = new ModelRenderer((Model) this);
            cube_r6.setPos(11.3404f, 0.6929f, 6.8071f);
            this.Body.addChild(cube_r6);
            this.setRotationAngle(cube_r6, 0.8967f, -0.4645f, 0.02f);
            cube_r6.texOffs(56, 13).addBox(-10.3404f, -4.5f, 1.232f, 13.0f, 9.0f, 2.0f, 0.0f, false);
            final ModelRenderer cube_r7 = new ModelRenderer((Model) this);
            cube_r7.setPos(0.0f, 7.0f, 4.0f);
            this.Body.addChild(cube_r7);
            this.setRotationAngle(cube_r7, -0.3927f, 0.0f, 0.0f);
            cube_r7.texOffs(76, 77).addBox(-2.0f, -4.0f, -2.0f, 4.0f, 7.0f, 2.0f, 0.0f, false);
            final ModelRenderer cube_r8 = new ModelRenderer((Model) this);
            cube_r8.setPos(2.35f, 5.0f, -5.5f);
            this.Body.addChild(cube_r8);
            this.setRotationAngle(cube_r8, 0.9855f, -0.9275f, -0.879f);
            cube_r8.texOffs(69, 0).addBox(-3.0f, -3.0f, -2.5f, 7.0f, 10.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r9 = new ModelRenderer((Model) this);
            cube_r9.setPos(-1.9913f, 5.0f, -5.5f);
            this.Body.addChild(cube_r9);
            this.setRotationAngle(cube_r9, 0.8213f, 0.8189f, 0.6654f);
            cube_r9.texOffs(30, 74).addBox(-4.0f, -3.0f, -2.5f, 7.0f, 10.0f, 1.0f, 0.0f, false);
            (this.RightArm = new ModelRenderer((Model) this)).setPos(-5.0f, 2.0f, 0.0f);
            this.RightArm.texOffs(14, 74).addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            final ModelRenderer cube_r10 = new ModelRenderer((Model) this);
            cube_r10.setPos(-3.0041f, 0.4183f, 0.0f);
            this.RightArm.addChild(cube_r10);
            this.setRotationAngle(cube_r10, 0.0f, 0.0f, 0.3927f);
            cube_r10.texOffs(0, 19).addBox(-8.5f, -3.0f, -5.0f, 10.0f, 1.0f, 10.0f, 0.0f, false);
            cube_r10.texOffs(47, 50).addBox(-6.5f, -8.0f, -4.0f, 8.0f, 5.0f, 8.0f, 0.0f, false);
            (this.LeftArm = new ModelRenderer((Model) this)).setPos(5.0f, 2.0f, 0.0f);
            this.LeftArm.texOffs(72, 24).addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            final ModelRenderer cube_r11 = new ModelRenderer((Model) this);
            cube_r11.setPos(3.0041f, 0.4183f, 0.0f);
            this.LeftArm.addChild(cube_r11);
            this.setRotationAngle(cube_r11, 0.0f, 0.0f, -0.3927f);
            cube_r11.texOffs(0, 30).addBox(-1.5f, -3.0f, -5.0f, 10.0f, 1.0f, 10.0f, 0.0f, false);
            cube_r11.texOffs(37, 0).addBox(-1.5f, -8.0f, -4.0f, 8.0f, 5.0f, 8.0f, 0.0f, false);
            (this.RightBoot = new ModelRenderer((Model) this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightBoot.texOffs(62, 63).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            (this.LeftBoot = new ModelRenderer((Model) this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftBoot.texOffs(46, 63).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            (this.Belt = new ModelRenderer((Model) this)).setPos(0.0f, 0.0f, 0.0f);
            this.Belt.texOffs(16, 16).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 0.51f, false);
            (this.RightLeg = new ModelRenderer((Model) this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightLeg.texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, false);
            (this.LeftLeg = new ModelRenderer((Model) this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftLeg.texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, true);
        }
    }
}
