// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.item.gear.specials;

import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.inventory.EquipmentSlotType;
import iskallia.vault.item.gear.model.VaultGearModel;
import net.minecraft.entity.LivingEntity;

public class XnetArmorModel<T extends LivingEntity> extends VaultGearModel<T>
{
    public XnetArmorModel(final float modelSize, final EquipmentSlotType slotType) {
        super(modelSize, slotType);
        this.texWidth = (this.isLayer2() ? 64 : 128);
        this.texHeight = (this.isLayer2() ? 64 : 128);
        (this.Head = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
        this.Head.texOffs(0, 0).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, 1.0f, false);
        this.Head.texOffs(50, 37).addBox(-6.0f, -6.0f, -2.0f, 1.0f, 3.0f, 2.0f, 0.0f, false);
        this.Head.texOffs(46, 40).addBox(5.0f, -6.0f, -2.0f, 1.0f, 3.0f, 2.0f, 0.0f, false);
        this.Head.texOffs(24, 0).addBox(-6.0f, -6.0f, -6.0f, 12.0f, 4.0f, 4.0f, 0.0f, false);
        (this.Body = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
        this.Body.texOffs(24, 26).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 1.01f, false);
        this.Body.texOffs(44, 24).addBox(-4.0f, 1.0f, 3.0f, 8.0f, 3.0f, 3.0f, 0.0f, false);
        this.Body.texOffs(28, 21).addBox(-3.0f, 4.0f, 3.0f, 6.0f, 3.0f, 2.0f, 0.0f, false);
        (this.RightArm = new ModelRenderer((Model)this)).setPos(-5.0f, 2.0f, 0.0f);
        this.RightArm.texOffs(48, 8).addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
        this.RightArm.texOffs(0, 16).addBox(-4.5f, 5.0f, -3.5f, 7.0f, 7.0f, 7.0f, 0.0f, false);
        this.RightArm.texOffs(0, 30).addBox(-4.5f, -2.0f, -3.5f, 4.0f, 6.0f, 7.0f, 0.0f, false);
        this.RightArm.texOffs(24, 8).addBox(-5.5f, 7.25f, -4.0f, 4.0f, 5.0f, 8.0f, 0.0f, false);
        this.RightArm.texOffs(21, 21).addBox(-7.0f, 8.0f, 2.0f, 2.0f, 1.0f, 1.0f, 0.0f, false);
        this.RightArm.texOffs(48, 30).addBox(-7.0f, 0.0f, 2.0f, 1.0f, 8.0f, 1.0f, 0.0f, false);
        this.RightArm.texOffs(0, 6).addBox(-6.0f, 0.0f, 2.0f, 2.0f, 1.0f, 1.0f, 0.0f, false);
        final ModelRenderer cube_r1 = new ModelRenderer((Model)this);
        cube_r1.setPos(-2.5f, 8.2069f, -3.3363f);
        this.RightArm.addChild(cube_r1);
        this.setRotationAngle(cube_r1, -1.3526f, 0.0f, 0.0f);
        cube_r1.texOffs(0, 0).addBox(-0.5f, -1.0f, -1.0f, 1.0f, 4.0f, 2.0f, 0.0f, false);
        final ModelRenderer cube_r2 = new ModelRenderer((Model)this);
        cube_r2.setPos(-2.5f, 14.0f, -4.0f);
        this.RightArm.addChild(cube_r2);
        this.setRotationAngle(cube_r2, -0.6545f, 0.0f, 0.0f);
        cube_r2.texOffs(15, 30).addBox(-0.5f, -4.0f, -2.0f, 1.0f, 4.0f, 2.0f, 0.0f, false);
        final ModelRenderer cube_r3 = new ModelRenderer((Model)this);
        cube_r3.setPos(-2.5f, 14.0f, 4.0f);
        this.RightArm.addChild(cube_r3);
        this.setRotationAngle(cube_r3, 0.4363f, 0.0f, 0.0f);
        cube_r3.texOffs(0, 30).addBox(-0.5f, -4.0f, -1.0f, 1.0f, 4.0f, 2.0f, 0.0f, false);
        final ModelRenderer cube_r4 = new ModelRenderer((Model)this);
        cube_r4.setPos(-2.5f, 14.0f, 1.0f);
        this.RightArm.addChild(cube_r4);
        this.setRotationAngle(cube_r4, 0.2598f, 0.0173f, -0.0254f);
        cube_r4.texOffs(0, 16).addBox(-0.5f, -2.5f, -1.0f, 1.0f, 4.0f, 2.0f, 0.0f, false);
        final ModelRenderer cube_r5 = new ModelRenderer((Model)this);
        cube_r5.setPos(-2.5f, 14.0f, -1.0f);
        this.RightArm.addChild(cube_r5);
        this.setRotationAngle(cube_r5, -0.3054f, 0.0f, 0.0f);
        cube_r5.texOffs(40, 8).addBox(-0.5f, -2.25f, -2.0f, 1.0f, 4.0f, 2.0f, 0.0f, false);
        (this.LeftArm = new ModelRenderer((Model)this)).setPos(5.0f, 2.0f, 0.0f);
        this.LeftArm.texOffs(0, 43).addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
        (this.RightBoot = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
        this.RightBoot.texOffs(34, 42).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
        (this.LeftBoot = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
        this.LeftBoot.texOffs(18, 42).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
        (this.Belt = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
        this.Belt.texOffs(0, 0).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 0.51f, false);
        (this.RightLeg = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
        this.RightLeg.texOffs(16, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, false);
        this.RightLeg.texOffs(4, 32).addBox(-1.0f, 1.0f, 3.0f, 1.0f, 7.0f, 1.0f, 0.0f, false);
        this.RightLeg.texOffs(12, 16).addBox(-1.0f, 1.0f, 2.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.RightLeg.texOffs(0, 16).addBox(-1.0f, 7.0f, 2.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.RightLeg.texOffs(30, 1).addBox(-3.0f, 3.0f, -2.0f, 1.0f, 1.0f, 4.0f, 0.0f, false);
        this.RightLeg.texOffs(28, 28).addBox(-3.0f, 5.0f, -2.0f, 1.0f, 1.0f, 4.0f, 0.0f, false);
        this.RightLeg.texOffs(28, 15).addBox(-3.0f, 7.0f, -2.0f, 1.0f, 1.0f, 4.0f, 0.0f, false);
        (this.LeftLeg = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
        this.LeftLeg.texOffs(24, 0).addBox(1.95f, 7.0f, -2.0f, 1.0f, 1.0f, 4.0f, 0.0f, false);
        this.LeftLeg.texOffs(24, 5).addBox(1.95f, 5.0f, -2.0f, 1.0f, 1.0f, 4.0f, 0.0f, false);
        this.LeftLeg.texOffs(24, 10).addBox(1.95f, 3.0f, -2.0f, 1.0f, 1.0f, 4.0f, 0.0f, false);
        this.LeftLeg.texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, false);
        this.LeftLeg.texOffs(0, 0).addBox(0.2f, 7.0f, 2.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.LeftLeg.texOffs(0, 32).addBox(0.2f, 1.0f, 3.0f, 1.0f, 7.0f, 1.0f, 0.0f, false);
        this.LeftLeg.texOffs(0, 2).addBox(0.2f, 1.0f, 2.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
    }
}
