// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.item.gear.model;

import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.LivingEntity;

public class FurArmorModel
{
    public static class Variant1<T extends LivingEntity> extends VaultGearModel<T>
    {
        public Variant1(final float modelSize, final EquipmentSlotType slotType) {
            super(modelSize, slotType);
            this.texWidth = (this.isLayer2() ? 64 : 128);
            this.texHeight = (this.isLayer2() ? 32 : 128);
            (this.Head = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Head.texOffs(28, 28).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, 1.0f, false);
            this.Head.texOffs(36, 16).addBox(-2.0f, -9.0f, -9.0f, 4.0f, 3.0f, 5.0f, 0.0f, false);
            this.Head.texOffs(31, 6).addBox(-4.0f, -10.0f, -5.0f, 8.0f, 1.0f, 9.0f, 0.0f, false);
            this.Head.texOffs(0, 0).addBox(-3.0f, -17.0f, 0.0f, 1.0f, 7.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(6, 31).addBox(-3.0f, -20.0f, 1.0f, 1.0f, 4.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(29, 16).addBox(-8.0f, -18.0f, 1.0f, 5.0f, 1.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(29, 18).addBox(-6.0f, -15.0f, 0.0f, 3.0f, 1.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(29, 18).addBox(3.0f, -15.0f, 0.0f, 3.0f, 1.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(29, 16).addBox(3.0f, -18.0f, 1.0f, 5.0f, 1.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(10, 31).addBox(1.0f, -13.0f, -2.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(10, 31).addBox(-2.0f, -13.0f, -2.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(6, 31).addBox(2.0f, -20.0f, 1.0f, 1.0f, 4.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(6, 31).addBox(8.0f, -21.0f, 1.0f, 1.0f, 4.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(4, 0).addBox(6.0f, -24.0f, 1.0f, 1.0f, 6.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(0, 20).addBox(5.0f, -23.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(0, 20).addBox(-6.0f, -23.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(4, 0).addBox(-7.0f, -24.0f, 1.0f, 1.0f, 6.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(6, 31).addBox(-9.0f, -21.0f, 1.0f, 1.0f, 4.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(0, 0).addBox(2.0f, -17.0f, 0.0f, 1.0f, 7.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r1 = new ModelRenderer((Model)this);
            cube_r1.setPos(4.0f, -11.0f, -3.5f);
            this.Head.addChild(cube_r1);
            this.setRotationAngle(cube_r1, 0.0f, 0.0f, 0.6981f);
            cube_r1.texOffs(0, 15).addBox(-0.2f, -2.0f, 1.5f, 2.0f, 4.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r2 = new ModelRenderer((Model)this);
            cube_r2.setPos(-4.0f, -11.0f, -3.5f);
            this.Head.addChild(cube_r2);
            this.setRotationAngle(cube_r2, 0.0f, 0.0f, -0.7854f);
            cube_r2.texOffs(0, 15).addBox(-2.0f, -2.1f, 1.5f, 2.0f, 4.0f, 1.0f, 0.0f, false);
            (this.Body = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Body.texOffs(28, 44).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 1.01f, false);
            this.Body.texOffs(0, 0).addBox(-6.0f, -2.0f, -4.0f, 12.0f, 7.0f, 8.0f, 0.0f, false);
            this.Body.texOffs(0, 15).addBox(-5.5f, -1.0f, -3.5f, 11.0f, 9.0f, 7.0f, 0.0f, false);
            (this.RightArm = new ModelRenderer((Model)this)).setPos(-5.0f, 2.0f, 0.0f);
            this.RightArm.texOffs(52, 52).addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            this.RightArm.texOffs(0, 36).addBox(-5.0f, 4.0f, -4.0f, 8.0f, 1.0f, 8.0f, 0.0f, false);
            this.RightArm.texOffs(10, 31).addBox(-5.0f, 5.0f, 1.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            this.RightArm.texOffs(10, 31).addBox(-5.0f, 5.0f, -2.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            this.RightArm.texOffs(10, 31).addBox(-5.0f, 1.0f, -2.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            this.RightArm.texOffs(10, 31).addBox(-5.0f, -1.0f, -3.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            this.RightArm.texOffs(0, 31).addBox(-5.0f, 1.0f, -5.0f, 1.0f, 1.0f, 2.0f, 0.0f, false);
            this.RightArm.texOffs(0, 31).addBox(-5.0f, 1.0f, 3.0f, 1.0f, 1.0f, 2.0f, 0.0f, false);
            this.RightArm.texOffs(10, 31).addBox(-5.0f, -2.0f, -5.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            this.RightArm.texOffs(10, 31).addBox(-5.0f, -2.0f, 4.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            this.RightArm.texOffs(10, 31).addBox(-5.0f, -1.0f, 2.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            this.RightArm.texOffs(10, 31).addBox(-5.0f, 1.0f, 1.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            (this.LeftArm = new ModelRenderer((Model)this)).setPos(5.0f, 2.0f, 0.0f);
            this.LeftArm.texOffs(52, 52).addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, true);
            this.LeftArm.texOffs(0, 31).addBox(4.0f, 1.0f, -5.0f, 1.0f, 1.0f, 2.0f, 0.0f, false);
            this.LeftArm.texOffs(10, 31).addBox(4.0f, -2.0f, -5.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            this.LeftArm.texOffs(10, 31).addBox(4.0f, 1.0f, -2.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            this.LeftArm.texOffs(10, 31).addBox(4.0f, -1.0f, -3.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            this.LeftArm.texOffs(10, 31).addBox(4.0f, 5.0f, -2.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            this.LeftArm.texOffs(10, 31).addBox(4.0f, 5.0f, 1.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            this.LeftArm.texOffs(10, 31).addBox(4.0f, 1.0f, 1.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            this.LeftArm.texOffs(0, 31).addBox(4.0f, 1.0f, 3.0f, 1.0f, 1.0f, 2.0f, 0.0f, false);
            this.LeftArm.texOffs(10, 31).addBox(4.0f, -1.0f, 2.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            this.LeftArm.texOffs(10, 31).addBox(4.0f, -2.0f, 4.0f, 1.0f, 3.0f, 1.0f, 0.0f, false);
            this.LeftArm.texOffs(0, 36).addBox(-3.0f, 4.0f, -4.0f, 8.0f, 1.0f, 8.0f, 0.0f, false);
            (this.RightBoot = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightBoot.texOffs(0, 45).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            (this.LeftBoot = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftBoot.texOffs(0, 45).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, true);
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
            this.Head.texOffs(24, 0).addBox(-5.0f, -9.0f, -6.0f, 10.0f, 7.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r1 = new ModelRenderer((Model)this);
            cube_r1.setPos(4.3333f, -11.8333f, -4.5f);
            this.Head.addChild(cube_r1);
            this.setRotationAngle(cube_r1, 0.0f, 0.0f, 0.5236f);
            cube_r1.texOffs(38, 35).addBox(2.6667f, 0.8333f, -0.5f, 1.0f, 4.0f, 1.0f, 0.0f, false);
            cube_r1.texOffs(24, 24).addBox(1.6667f, -1.1667f, -0.5f, 1.0f, 6.0f, 1.0f, 0.0f, false);
            cube_r1.texOffs(0, 0).addBox(-0.3333f, -2.1667f, -0.5f, 2.0f, 7.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r2 = new ModelRenderer((Model)this);
            cube_r2.setPos(-4.3333f, -11.8333f, -4.5f);
            this.Head.addChild(cube_r2);
            this.setRotationAngle(cube_r2, 0.0f, 0.0f, -0.5236f);
            cube_r2.texOffs(38, 35).addBox(-3.6667f, 0.8333f, -0.5f, 1.0f, 4.0f, 1.0f, 0.0f, false);
            cube_r2.texOffs(24, 24).addBox(-2.6667f, -1.1667f, -0.5f, 1.0f, 6.0f, 1.0f, 0.0f, false);
            cube_r2.texOffs(0, 0).addBox(-1.6667f, -2.1667f, -0.5f, 2.0f, 7.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r3 = new ModelRenderer((Model)this);
            cube_r3.setPos(0.0f, -3.5f, -7.5f);
            this.Head.addChild(cube_r3);
            this.setRotationAngle(cube_r3, 0.3927f, 0.0f, 0.0f);
            cube_r3.texOffs(42, 14).addBox(-1.5f, -1.5f, -2.5f, 3.0f, 3.0f, 6.0f, 0.0f, false);
            (this.Body = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Body.texOffs(0, 16).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 1.01f, false);
            this.Body.texOffs(14, 32).addBox(-5.0f, 0.0f, -4.0f, 2.0f, 2.0f, 2.0f, 0.0f, false);
            this.Body.texOffs(14, 32).addBox(-4.0f, 2.0f, -5.0f, 2.0f, 2.0f, 2.0f, 0.0f, false);
            this.Body.texOffs(14, 32).addBox(3.0f, 0.0f, -4.0f, 2.0f, 2.0f, 2.0f, 0.0f, false);
            this.Body.texOffs(14, 32).addBox(2.0f, 2.0f, -5.0f, 2.0f, 2.0f, 2.0f, 0.0f, false);
            this.Body.texOffs(14, 32).addBox(1.0f, 4.0f, -4.0f, 2.0f, 2.0f, 2.0f, 0.0f, false);
            this.Body.texOffs(14, 32).addBox(-1.0f, 5.0f, -5.0f, 2.0f, 2.0f, 2.0f, 0.0f, false);
            this.Body.texOffs(14, 32).addBox(-3.0f, 4.0f, -4.0f, 2.0f, 2.0f, 2.0f, 0.0f, false);
            final ModelRenderer cube_r4 = new ModelRenderer((Model)this);
            cube_r4.setPos(-0.5f, 15.5f, 10.5f);
            this.Body.addChild(cube_r4);
            this.setRotationAngle(cube_r4, -0.9599f, 0.0f, 0.0f);
            cube_r4.texOffs(34, 32).addBox(-0.5f, 1.0f, 0.5f, 2.0f, 2.0f, 1.0f, 0.0f, false);
            cube_r4.texOffs(0, 32).addBox(-1.0f, 0.5f, -0.5f, 3.0f, 3.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r5 = new ModelRenderer((Model)this);
            cube_r5.setPos(-0.5f, 11.5f, 7.0f);
            this.Body.addChild(cube_r5);
            this.setRotationAngle(cube_r5, -0.7418f, 0.0f, 0.0f);
            cube_r5.texOffs(0, 32).addBox(-1.0f, 0.5f, -5.0f, 3.0f, 3.0f, 8.0f, 0.0f, false);
            final ModelRenderer cube_r6 = new ModelRenderer((Model)this);
            cube_r6.setPos(-0.5f, 11.5f, 7.0f);
            this.Body.addChild(cube_r6);
            this.setRotationAngle(cube_r6, -0.9599f, 0.0f, 0.0f);
            cube_r6.texOffs(24, 8).addBox(-1.5f, -0.5f, -3.0f, 4.0f, 4.0f, 8.0f, 0.0f, false);
            (this.RightArm = new ModelRenderer((Model)this)).setPos(-5.0f, 2.0f, 0.0f);
            this.RightArm.texOffs(38, 38).addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            this.RightArm.texOffs(40, 0).addBox(-5.0f, 2.0f, -4.0f, 3.0f, 1.0f, 8.0f, 0.0f, false);
            this.RightArm.texOffs(40, 0).addBox(-5.0f, 8.0f, -4.0f, 3.0f, 1.0f, 8.0f, 0.0f, false);
            (this.LeftArm = new ModelRenderer((Model)this)).setPos(5.0f, 2.0f, 0.0f);
            this.LeftArm.texOffs(38, 38).addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, true);
            this.LeftArm.texOffs(40, 0).addBox(2.0f, 8.0f, -4.0f, 3.0f, 1.0f, 8.0f, 0.0f, false);
            this.LeftArm.texOffs(40, 0).addBox(2.0f, 2.0f, -4.0f, 3.0f, 1.0f, 8.0f, 0.0f, false);
            (this.RightBoot = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightBoot.texOffs(22, 32).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            this.RightBoot.texOffs(24, 24).addBox(-3.5f, 12.25f, -3.75f, 7.0f, 1.0f, 7.0f, 0.0f, false);
            this.RightBoot.texOffs(24, 20).addBox(-2.5f, 13.25f, -2.75f, 5.0f, 1.0f, 1.0f, 0.0f, false);
            this.RightBoot.texOffs(24, 20).addBox(-2.5f, 13.25f, 1.25f, 5.0f, 1.0f, 1.0f, 0.0f, false);
            (this.LeftBoot = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftBoot.texOffs(22, 32).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, true);
            this.LeftBoot.texOffs(24, 20).addBox(-2.3f, 13.25f, -2.75f, 5.0f, 1.0f, 1.0f, 0.0f, false);
            this.LeftBoot.texOffs(24, 20).addBox(-2.3f, 13.25f, 1.25f, 5.0f, 1.0f, 1.0f, 0.0f, false);
            this.LeftBoot.texOffs(24, 24).addBox(-3.3f, 12.25f, -3.75f, 7.0f, 1.0f, 7.0f, 0.0f, false);
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
            this.Head.texOffs(0, 27).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, 1.0f, false);
            this.Head.texOffs(52, 0).addBox(-3.5f, -7.25f, -7.5f, 7.0f, 4.0f, 3.0f, 0.0f, false);
            this.Head.texOffs(4, 6).addBox(-3.0f, -3.25f, -7.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(0, 6).addBox(2.0f, -3.25f, -7.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
            this.Head.texOffs(0, 0).addBox(-1.5f, -7.25f, -8.5f, 3.0f, 2.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r1 = new ModelRenderer((Model)this);
            cube_r1.setPos(4.0f, -9.75f, -2.5f);
            this.Head.addChild(cube_r1);
            this.setRotationAngle(cube_r1, 0.0f, 0.0f, 0.7854f);
            cube_r1.texOffs(0, 3).addBox(0.0f, -1.0f, -0.5f, 2.0f, 2.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r2 = new ModelRenderer((Model)this);
            cube_r2.setPos(-4.0f, -9.75f, -2.5f);
            this.Head.addChild(cube_r2);
            this.setRotationAngle(cube_r2, 0.0f, 0.0f, -0.7854f);
            cube_r2.texOffs(0, 12).addBox(-2.0f, -1.0f, -0.5f, 2.0f, 2.0f, 1.0f, 0.0f, false);
            (this.Body = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Body.texOffs(24, 44).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 1.01f, false);
            this.Body.texOffs(0, 0).addBox(-6.0f, 1.0f, -4.0f, 12.0f, 4.0f, 8.0f, 0.0f, false);
            this.Body.texOffs(0, 12).addBox(-5.5f, 2.0f, -3.5f, 11.0f, 8.0f, 7.0f, 0.0f, false);
            (this.RightArm = new ModelRenderer((Model)this)).setPos(-5.0f, 2.0f, 0.0f);
            this.RightArm.texOffs(60, 25).addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            this.RightArm.texOffs(52, 12).addBox(-6.0f, -4.0f, -4.0f, 5.0f, 5.0f, 8.0f, 0.0f, false);
            this.RightArm.texOffs(0, 43).addBox(-5.0f, 4.0f, -4.0f, 4.0f, 8.0f, 8.0f, 0.0f, false);
            this.RightArm.texOffs(62, 62).addBox(-6.0f, 5.0f, -3.0f, 1.0f, 6.0f, 6.0f, 0.0f, false);
            this.RightArm.texOffs(29, 15).addBox(-4.0f, 12.0f, 1.5f, 1.0f, 2.0f, 1.0f, 0.0f, false);
            this.RightArm.texOffs(29, 12).addBox(-4.0f, 12.0f, -0.5f, 1.0f, 2.0f, 1.0f, 0.0f, false);
            this.RightArm.texOffs(24, 27).addBox(-4.0f, 12.0f, -2.5f, 1.0f, 2.0f, 1.0f, 0.0f, false);
            (this.LeftArm = new ModelRenderer((Model)this)).setPos(5.0f, 2.0f, 0.0f);
            this.LeftArm.texOffs(32, 60).addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            this.LeftArm.texOffs(48, 61).addBox(5.0f, 5.0f, -3.0f, 1.0f, 6.0f, 6.0f, 0.0f, false);
            this.LeftArm.texOffs(36, 4).addBox(1.0f, 4.0f, -4.0f, 4.0f, 8.0f, 8.0f, 0.0f, false);
            this.LeftArm.texOffs(4, 27).addBox(3.0f, 12.0f, -2.5f, 1.0f, 2.0f, 1.0f, 0.0f, false);
            this.LeftArm.texOffs(0, 27).addBox(3.0f, 12.0f, 1.5f, 1.0f, 2.0f, 1.0f, 0.0f, false);
            this.LeftArm.texOffs(0, 15).addBox(3.0f, 12.0f, -0.5f, 1.0f, 2.0f, 1.0f, 0.0f, false);
            this.LeftArm.texOffs(48, 48).addBox(1.0f, -4.0f, -4.0f, 5.0f, 5.0f, 8.0f, 0.0f, false);
            (this.RightBoot = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightBoot.texOffs(16, 60).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            this.RightBoot.texOffs(32, 32).addBox(-3.5f, 8.25f, -3.75f, 7.0f, 5.0f, 7.0f, 0.0f, false);
            this.RightBoot.texOffs(59, 43).addBox(-3.0f, 9.25f, -4.75f, 6.0f, 4.0f, 1.0f, 0.0f, false);
            (this.LeftBoot = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftBoot.texOffs(0, 59).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            this.LeftBoot.texOffs(52, 7).addBox(-3.1f, 9.25f, -4.75f, 6.0f, 4.0f, 1.0f, 0.0f, false);
            this.LeftBoot.texOffs(29, 20).addBox(-3.6f, 8.25f, -3.75f, 7.0f, 5.0f, 7.0f, 0.0f, false);
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
            this.Head.texOffs(0, 28).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, 1.0f, false);
            this.Head.texOffs(0, 0).addBox(-5.0f, -17.0f, -5.0f, 10.0f, 7.0f, 10.0f, 0.0f, false);
            this.Head.texOffs(32, 32).addBox(-4.0f, -17.0f, -13.0f, 8.0f, 5.0f, 8.0f, 0.0f, false);
            this.Head.texOffs(0, 44).addBox(-3.5f, -13.0f, -12.0f, 7.0f, 3.0f, 7.0f, 0.0f, false);
            this.Head.texOffs(0, 17).addBox(-5.0f, -10.0f, -5.0f, 10.0f, 1.0f, 10.0f, 0.0f, false);
            this.Head.texOffs(0, 54).addBox(-1.0f, -17.0f, 5.0f, 2.0f, 18.0f, 3.0f, 0.0f, false);
            final ModelRenderer cube_r1 = new ModelRenderer((Model)this);
            cube_r1.setPos(3.0f, -21.0f, 3.5f);
            this.Head.addChild(cube_r1);
            this.setRotationAngle(cube_r1, 0.48f, 0.0f, 0.0f);
            cube_r1.texOffs(0, 0).addBox(-1.0f, -4.0f, -2.5f, 2.0f, 8.0f, 1.0f, 0.0f, false);
            final ModelRenderer cube_r2 = new ModelRenderer((Model)this);
            cube_r2.setPos(-3.0f, -21.0f, 3.5f);
            this.Head.addChild(cube_r2);
            this.setRotationAngle(cube_r2, 0.5236f, 0.0f, 0.0f);
            cube_r2.texOffs(0, 0).addBox(-1.0f, -4.0f, -2.5f, 2.0f, 8.0f, 1.0f, 0.0f, false);
            (this.Body = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Body.texOffs(40, 0).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 1.01f, false);
            this.Body.texOffs(24, 28).addBox(-3.0f, 1.0f, -4.0f, 6.0f, 6.0f, 1.0f, 0.0f, false);
            this.Body.texOffs(0, 17).addBox(-2.0f, 7.0f, -4.0f, 4.0f, 1.0f, 1.0f, 0.0f, false);
            this.Body.texOffs(56, 26).addBox(-4.0f, 1.0f, 3.0f, 8.0f, 8.0f, 2.0f, 0.0f, false);
            (this.RightArm = new ModelRenderer((Model)this)).setPos(-5.0f, 2.0f, 0.0f);
            this.RightArm.texOffs(44, 45).addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            (this.LeftArm = new ModelRenderer((Model)this)).setPos(5.0f, 2.0f, 0.0f);
            this.LeftArm.texOffs(44, 45).addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, true);
            (this.RightBoot = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightBoot.texOffs(28, 45).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
            this.RightBoot.texOffs(30, 17).addBox(-3.45f, 8.0f, -4.0f, 7.0f, 1.0f, 8.0f, 0.0f, false);
            (this.LeftBoot = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftBoot.texOffs(28, 45).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, true);
            this.LeftBoot.texOffs(30, 17).addBox(-3.5f, 8.0f, -4.0f, 7.0f, 1.0f, 8.0f, 0.0f, false);
            (this.Belt = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
            this.Belt.texOffs(16, 16).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 0.51f, false);
            (this.RightLeg = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
            this.RightLeg.texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, false);
            (this.LeftLeg = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
            this.LeftLeg.texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, true);
        }
    }
}
