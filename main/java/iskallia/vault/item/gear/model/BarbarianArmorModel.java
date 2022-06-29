// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.item.gear.model;

import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.LivingEntity;

public class BarbarianArmorModel
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
            cube_r1.setPos(8.25f, -7.0f, -1.5f);
            this.Head.addChild(cube_r1);
            this.setRotationAngle(cube_r1, -1.0908f, 0.0f, 0.0f);
            cube_r1.texOffs(0, 0).addBox(-1.25f, -1.0f, -3.5f, 1.0f, 2.0f, 2.0f, 0.0f, false);
            cube_r1.texOffs(0, 4).addBox(-0.25f, -1.0f, -7.5f, 1.0f, 2.0f, 2.0f, 0.0f, false);
            cube_r1.texOffs(28, 0).addBox(-0.25f, -1.0f, -5.5f, 2.0f, 2.0f, 4.0f, 0.0f, false);
            cube_r1.texOffs(36, 0).addBox(-3.25f, -1.0f, -1.5f, 5.0f, 2.0f, 2.0f, 0.0f, false);
            final ModelRenderer cube_r2 = new ModelRenderer((Model)this);
            cube_r2.setPos(-8.25f, -7.0f, -1.5f);
            this.Head.addChild(cube_r2);
            this.setRotationAngle(cube_r2, -1.1781f, 0.0f, 0.0f);
            cube_r2.texOffs(0, 14).addBox(0.25f, -1.0f, -3.5f, 1.0f, 2.0f, 2.0f, 0.0f, false);
            cube_r2.texOffs(0, 18).addBox(-0.75f, -1.0f, -7.5f, 1.0f, 2.0f, 2.0f, 0.0f, false);
            cube_r2.texOffs(16, 30).addBox(-1.75f, -1.0f, -5.5f, 2.0f, 2.0f, 4.0f, 0.0f, false);
            cube_r2.texOffs(36, 6).addBox(-1.75f, -1.0f, -1.5f, 5.0f, 2.0f, 2.0f, 0.0f, false);
            final ModelRenderer cube_r3 = new ModelRenderer((Model)this);
            cube_r3.setPos(2.1367f, -4.5f, -5.5f);
            this.Head.addChild(cube_r3);
            this.setRotationAngle(cube_r3, 0.0f, -0.3927f, 0.0f);
            cube_r3.texOffs(0, 46).addBox(-2.5f, -2.5f, -0.5f, 5.0f, 5.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r4 = new ModelRenderer((Model)this);
            cube_r4.setPos(-2.1f, -4.5f, -5.5f);
            this.Head.addChild(cube_r4);
            this.setRotationAngle(cube_r4, 0.0f, 0.3927f, 0.0f);
            cube_r4.texOffs(48, 10).addBox(-2.5f, -2.5f, -0.5f, 5.0f, 5.0f, 1.0f, 0.0f, false);
            (this.Body = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Body.texOffs(28, 26).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 1.01f, false);
            (this.RightArm = new ModelRenderer((Model)this)).setPos(-5.0f, 2.0f, 0.0f);
            this.RightArm.texOffs(32, 42).addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            final ModelRenderer cube_r5 = new ModelRenderer((Model)this);
            cube_r5.setPos(0.0f, 0.0f, 0.0f);
            this.RightArm.addChild(cube_r5);
            this.setRotationAngle(cube_r5, 0.0f, 0.0f, -0.6981f);
            cube_r5.texOffs(0, 0).addBox(-7.0f, -6.0f, -4.0f, 10.0f, 6.0f, 8.0f, 0.0f, false);
            (this.LeftArm = new ModelRenderer((Model)this)).setPos(5.0f, 2.0f, 0.0f);
            this.LeftArm.texOffs(16, 38).addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            (this.RightBoot = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightBoot.texOffs(32, 10).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            (this.LeftBoot = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftBoot.texOffs(0, 30).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
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
            this.Head.texOffs(0, 17).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, 1.0f, false);
            final ModelRenderer cube_r1 = new ModelRenderer((Model)this);
            cube_r1.setPos(0.0f, -13.4726f, -11.8976f);
            this.Head.addChild(cube_r1);
            this.setRotationAngle(cube_r1, 1.0472f, 0.0f, 0.0f);
            cube_r1.texOffs(52, 62).addBox(-1.0f, -0.2f, -1.4f, 2.0f, 1.0f, 2.0f, 0.0f, false);
            final ModelRenderer cube_r2 = new ModelRenderer((Model)this);
            cube_r2.setPos(0.0f, -12.4726f, -10.3976f);
            this.Head.addChild(cube_r2);
            this.setRotationAngle(cube_r2, 1.5708f, 0.0f, 0.0f);
            cube_r2.texOffs(28, 62).addBox(-1.5f, -3.0f, -4.0f, 3.0f, 2.0f, 4.0f, 0.0f, false);
            final ModelRenderer cube_r3 = new ModelRenderer((Model)this);
            cube_r3.setPos(0.0f, -9.0f, -6.5f);
            this.Head.addChild(cube_r3);
            this.setRotationAngle(cube_r3, 1.1345f, 0.0f, 0.0f);
            cube_r3.texOffs(22, 49).addBox(-2.0f, -6.0f, -3.5f, 4.0f, 10.0f, 3.0f, 0.0f, false);
            final ModelRenderer cube_r4 = new ModelRenderer((Model)this);
            cube_r4.setPos(0.0f, -5.5f, -3.0f);
            this.Head.addChild(cube_r4);
            this.setRotationAngle(cube_r4, -1.0472f, 0.0f, 0.0f);
            cube_r4.texOffs(56, 17).addBox(-6.0f, -9.5f, -3.0f, 12.0f, 3.0f, 4.0f, 0.0f, false);
            cube_r4.texOffs(0, 0).addBox(-6.0f, -10.5f, 1.0f, 12.0f, 11.0f, 6.0f, 0.0f, false);
            cube_r4.texOffs(36, 0).addBox(-6.0f, -6.5f, -5.0f, 12.0f, 11.0f, 6.0f, 0.0f, false);
            (this.Body = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Body.texOffs(32, 17).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 1.01f, false);
            this.Body.texOffs(36, 55).addBox(-4.0f, 1.0f, -4.0f, 8.0f, 5.0f, 1.0f, 0.0f, false);
            this.Body.texOffs(14, 62).addBox(-3.0f, 6.0f, -3.5f, 6.0f, 5.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r5 = new ModelRenderer((Model)this);
            cube_r5.setPos(0.0f, 3.0f, 3.5f);
            this.Body.addChild(cube_r5);
            this.setRotationAngle(cube_r5, 0.4363f, 0.0f, 0.0f);
            cube_r5.texOffs(42, 62).addBox(-2.0f, 3.0f, -3.5f, 4.0f, 5.0f, 1.0f, 0.0f, false);
            cube_r5.texOffs(0, 62).addBox(-3.0f, 1.0f, -2.5f, 6.0f, 5.0f, 1.0f, 0.0f, false);
            cube_r5.texOffs(36, 49).addBox(-4.0f, -1.0f, -1.5f, 8.0f, 5.0f, 1.0f, 0.0f, false);
            cube_r5.texOffs(0, 49).addBox(-5.0f, -2.0f, -0.5f, 10.0f, 4.0f, 1.0f, 0.0f, false);
            (this.RightArm = new ModelRenderer((Model)this)).setPos(-5.0f, 2.0f, 0.0f);
            this.RightArm.texOffs(32, 33).addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            (this.LeftArm = new ModelRenderer((Model)this)).setPos(5.0f, 2.0f, 0.0f);
            this.LeftArm.texOffs(16, 33).addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            (this.RightBoot = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightBoot.texOffs(0, 33).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            (this.LeftBoot = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftBoot.texOffs(0, 33).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, true);
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
            this.Head.texOffs(0, 0).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, 1.0f, false);
            this.Head.texOffs(32, 0).addBox(-1.0f, -10.0f, -6.0f, 2.0f, 3.0f, 12.0f, 0.0f, false);
            this.Head.texOffs(22, 67).addBox(-0.5f, -12.0f, 2.0f, 1.0f, 2.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(18, 64).addBox(-0.5f, -14.0f, -3.0f, 1.0f, 4.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(60, 0).addBox(-0.5f, -18.0f, -8.0f, 1.0f, 4.0f, 12.0f, 0.0f, false);
            this.Head.texOffs(16, 48).addBox(-0.5f, -14.0f, -1.0f, 1.0f, 2.0f, 8.0f, 0.0f, false);
            (this.Body = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Body.texOffs(56, 16).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 1.01f, false);
            (this.RightArm = new ModelRenderer((Model)this)).setPos(-5.0f, 2.0f, 0.0f);
            this.RightArm.texOffs(0, 48).addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            this.RightArm.texOffs(28, 16).addBox(-5.0f, -5.0f, -4.0f, 6.0f, 6.0f, 8.0f, 0.0f, false);
            this.RightArm.texOffs(22, 64).addBox(-2.0f, -9.0f, -0.5f, 1.0f, 2.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r1 = new ModelRenderer((Model)this);
            cube_r1.setPos(-1.5f, -6.0f, 0.0f);
            this.RightArm.addChild(cube_r1);
            this.setRotationAngle(cube_r1, -2.3557f, 1.5091f, -2.3567f);
            cube_r1.texOffs(6, 64).addBox(-0.5f, -1.0f, -1.0f, 1.0f, 2.0f, 2.0f, 0.0f, false);
            final ModelRenderer cube_r2 = new ModelRenderer((Model)this);
            cube_r2.setPos(-7.0f, -3.25f, 0.0f);
            this.RightArm.addChild(cube_r2);
            this.setRotationAngle(cube_r2, 0.0f, 0.0f, -0.6545f);
            cube_r2.texOffs(12, 64).addBox(-1.0f, 0.25f, -1.0f, 1.0f, 2.0f, 2.0f, 0.0f, false);
            cube_r2.texOffs(34, 54).addBox(-1.0f, 2.25f, -1.0f, 3.0f, 1.0f, 2.0f, 0.0f, false);
            final ModelRenderer cube_r3 = new ModelRenderer((Model)this);
            cube_r3.setPos(-6.5f, -8.3333f, 0.0f);
            this.RightArm.addChild(cube_r3);
            this.setRotationAngle(cube_r3, 0.0f, 0.0f, 0.3491f);
            cube_r3.texOffs(0, 64).addBox(1.5f, -6.1667f, -1.0f, 1.0f, 4.0f, 2.0f, 0.0f, false);
            cube_r3.texOffs(34, 48).addBox(0.5f, -2.1667f, -1.0f, 2.0f, 4.0f, 2.0f, 0.0f, false);
            cube_r3.texOffs(16, 58).addBox(0.5f, 1.8333f, -1.0f, 4.0f, 2.0f, 2.0f, 0.0f, false);
            (this.LeftArm = new ModelRenderer((Model)this)).setPos(5.0f, 2.0f, 0.0f);
            this.LeftArm.texOffs(32, 32).addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            this.LeftArm.texOffs(0, 16).addBox(-1.0f, -5.0f, -4.0f, 6.0f, 6.0f, 8.0f, 0.0f, false);
            this.LeftArm.texOffs(22, 64).addBox(1.5f, -9.0f, -0.5f, 1.0f, 2.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r4 = new ModelRenderer((Model)this);
            cube_r4.setPos(2.0f, -6.0f, 0.0f);
            this.LeftArm.addChild(cube_r4);
            this.setRotationAngle(cube_r4, -2.3557f, 1.5091f, -2.3567f);
            cube_r4.texOffs(6, 64).addBox(-0.5f, -1.0f, -1.0f, 1.0f, 2.0f, 2.0f, 0.0f, false);
            final ModelRenderer cube_r5 = new ModelRenderer((Model)this);
            cube_r5.setPos(-16.5f, -8.3333f, 0.0f);
            this.LeftArm.addChild(cube_r5);
            this.setRotationAngle(cube_r5, -3.1416f, 0.0f, 2.7053f);
            cube_r5.texOffs(0, 64).addBox(-19.5f, 3.8333f, -1.0f, 1.0f, 4.0f, 2.0f, 0.0f, false);
            cube_r5.texOffs(34, 48).addBox(-20.5f, 7.8333f, -1.0f, 2.0f, 4.0f, 2.0f, 0.0f, false);
            cube_r5.texOffs(16, 58).addBox(-20.5f, 11.8333f, -1.0f, 4.0f, 2.0f, 2.0f, 0.0f, false);
            final ModelRenderer cube_r6 = new ModelRenderer((Model)this);
            cube_r6.setPos(-17.0f, -3.25f, 0.0f);
            this.LeftArm.addChild(cube_r6);
            this.setRotationAngle(cube_r6, 3.1416f, 0.0f, -2.618f);
            cube_r6.texOffs(34, 54).addBox(-22.0f, -9.75f, -1.0f, 3.0f, 1.0f, 2.0f, 0.0f, false);
            cube_r6.texOffs(12, 64).addBox(-22.0f, -11.75f, -1.0f, 1.0f, 2.0f, 2.0f, 0.0f, false);
            (this.RightBoot = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightBoot.texOffs(16, 32).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            (this.LeftBoot = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftBoot.texOffs(0, 32).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            (this.Belt = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Belt.texOffs(16, 16).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 0.51f, false);
            (this.RightLeg = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightLeg.texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, false);
            (this.LeftLeg = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftLeg.texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, true);
        }
    }
}
