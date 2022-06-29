// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.item.gear.specials;

import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.inventory.EquipmentSlotType;
import iskallia.vault.item.gear.model.VaultGearModel;
import net.minecraft.entity.LivingEntity;

public class DankArmorModel<T extends LivingEntity> extends VaultGearModel<T>
{
    public DankArmorModel(final float modelSize, final EquipmentSlotType slotType) {
        super(modelSize, slotType);
        this.texWidth = (this.isLayer2() ? 32 : 128);
        this.texHeight = (this.isLayer2() ? 32 : 128);
        (this.Head = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
        this.Head.texOffs(66, 62).addBox(-5.0f, -9.0f, -5.0f, 2.0f, 10.0f, 2.0f, 0.0f, false);
        this.Head.texOffs(16, 66).addBox(3.0f, -9.0f, -5.0f, 2.0f, 10.0f, 2.0f, 0.0f, false);
        this.Head.texOffs(8, 66).addBox(3.0f, -9.0f, 3.0f, 2.0f, 10.0f, 2.0f, 0.0f, false);
        this.Head.texOffs(48, 64).addBox(-5.0f, -9.0f, 3.0f, 2.0f, 10.0f, 2.0f, 0.0f, false);
        this.Head.texOffs(10, 46).addBox(-5.0f, -9.0f, -3.0f, 2.0f, 2.0f, 6.0f, 0.0f, false);
        this.Head.texOffs(40, 44).addBox(3.0f, -9.0f, -3.0f, 2.0f, 2.0f, 6.0f, 0.0f, false);
        this.Head.texOffs(30, 42).addBox(3.0f, -1.0f, -3.0f, 2.0f, 2.0f, 6.0f, 0.0f, false);
        this.Head.texOffs(42, 26).addBox(-5.0f, -1.0f, -3.0f, 2.0f, 2.0f, 6.0f, 0.0f, false);
        this.Head.texOffs(64, 58).addBox(-3.0f, -1.0f, 3.0f, 6.0f, 2.0f, 2.0f, 0.0f, false);
        this.Head.texOffs(64, 46).addBox(-3.0f, -9.0f, 3.0f, 6.0f, 2.0f, 2.0f, 0.0f, false);
        this.Head.texOffs(64, 42).addBox(-3.0f, -9.0f, -5.0f, 6.0f, 2.0f, 2.0f, 0.0f, false);
        this.Head.texOffs(32, 64).addBox(-3.0f, -1.0f, -5.0f, 6.0f, 2.0f, 2.0f, 0.0f, false);
        (this.Body = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
        this.Body.texOffs(60, 36).addBox(-3.0f, 10.0f, -5.0f, 6.0f, 2.0f, 2.0f, 0.0f, false);
        this.Body.texOffs(58, 30).addBox(-3.0f, 2.0f, -5.0f, 6.0f, 2.0f, 2.0f, 0.0f, false);
        this.Body.texOffs(56, 12).addBox(-3.0f, 2.0f, 3.0f, 6.0f, 2.0f, 2.0f, 0.0f, false);
        this.Body.texOffs(56, 4).addBox(-3.0f, 10.0f, 3.0f, 6.0f, 2.0f, 2.0f, 0.0f, false);
        this.Body.texOffs(42, 18).addBox(-5.0f, 10.0f, -3.0f, 2.0f, 2.0f, 6.0f, 0.0f, false);
        this.Body.texOffs(20, 40).addBox(3.0f, 10.0f, -3.0f, 2.0f, 2.0f, 6.0f, 0.0f, false);
        this.Body.texOffs(40, 8).addBox(-5.0f, 2.0f, -3.0f, 2.0f, 2.0f, 6.0f, 0.0f, false);
        this.Body.texOffs(40, 0).addBox(3.0f, 2.0f, -3.0f, 2.0f, 2.0f, 6.0f, 0.0f, false);
        this.Body.texOffs(24, 62).addBox(-5.0f, 2.0f, 3.0f, 2.0f, 10.0f, 2.0f, 0.0f, false);
        this.Body.texOffs(58, 60).addBox(3.0f, 2.0f, 3.0f, 2.0f, 10.0f, 2.0f, 0.0f, false);
        this.Body.texOffs(0, 60).addBox(3.0f, 2.0f, -5.0f, 2.0f, 10.0f, 2.0f, 0.0f, false);
        this.Body.texOffs(58, 48).addBox(-5.0f, 2.0f, -5.0f, 2.0f, 10.0f, 2.0f, 0.0f, false);
        (this.RightArm = new ModelRenderer((Model)this)).setPos(-5.0f, 2.0f, 0.0f);
        this.RightArm.texOffs(50, 40).addBox(-8.0f, 3.0f, -5.0f, 6.0f, 2.0f, 2.0f, 0.0f, false);
        this.RightArm.texOffs(50, 8).addBox(-8.0f, -5.0f, -5.0f, 6.0f, 2.0f, 2.0f, 0.0f, false);
        this.RightArm.texOffs(50, 0).addBox(-8.0f, -5.0f, 3.0f, 6.0f, 2.0f, 2.0f, 0.0f, false);
        this.RightArm.texOffs(46, 34).addBox(-8.0f, 3.0f, 3.0f, 6.0f, 2.0f, 2.0f, 0.0f, false);
        this.RightArm.texOffs(32, 24).addBox(-10.0f, 3.0f, -3.0f, 2.0f, 2.0f, 6.0f, 0.0f, false);
        this.RightArm.texOffs(32, 16).addBox(-2.0f, 3.0f, -3.0f, 2.0f, 2.0f, 6.0f, 0.0f, false);
        this.RightArm.texOffs(16, 30).addBox(-10.0f, -5.0f, -3.0f, 2.0f, 2.0f, 6.0f, 0.0f, false);
        this.RightArm.texOffs(34, 50).addBox(-10.0f, -5.0f, 3.0f, 2.0f, 10.0f, 2.0f, 0.0f, false);
        this.RightArm.texOffs(22, 22).addBox(-2.0f, -5.0f, -3.0f, 2.0f, 2.0f, 6.0f, 0.0f, false);
        this.RightArm.texOffs(26, 50).addBox(-2.0f, -5.0f, 3.0f, 2.0f, 10.0f, 2.0f, 0.0f, false);
        this.RightArm.texOffs(0, 48).addBox(-2.0f, -5.0f, -5.0f, 2.0f, 10.0f, 2.0f, 0.0f, false);
        this.RightArm.texOffs(16, 0).addBox(-10.0f, -5.0f, -5.0f, 2.0f, 10.0f, 2.0f, 0.0f, false);
        this.RightArm.texOffs(24, 0).addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
        (this.LeftArm = new ModelRenderer((Model)this)).setPos(5.0f, 2.0f, 0.0f);
        this.LeftArm.texOffs(52, 26).addBox(2.0f, 3.0f, -5.0f, 6.0f, 2.0f, 2.0f, 0.0f, false);
        this.LeftArm.texOffs(52, 20).addBox(2.0f, -5.0f, -5.0f, 6.0f, 2.0f, 2.0f, 0.0f, false);
        this.LeftArm.texOffs(52, 16).addBox(2.0f, -5.0f, 3.0f, 6.0f, 2.0f, 2.0f, 0.0f, false);
        this.LeftArm.texOffs(50, 44).addBox(2.0f, 3.0f, 3.0f, 6.0f, 2.0f, 2.0f, 0.0f, false);
        this.LeftArm.texOffs(0, 40).addBox(0.0f, 3.0f, -3.0f, 2.0f, 2.0f, 6.0f, 0.0f, false);
        this.LeftArm.texOffs(10, 38).addBox(8.0f, 3.0f, -3.0f, 2.0f, 2.0f, 6.0f, 0.0f, false);
        this.LeftArm.texOffs(36, 34).addBox(0.0f, -5.0f, -3.0f, 2.0f, 2.0f, 6.0f, 0.0f, false);
        this.LeftArm.texOffs(16, 54).addBox(0.0f, -5.0f, 3.0f, 2.0f, 10.0f, 2.0f, 0.0f, false);
        this.LeftArm.texOffs(26, 32).addBox(8.0f, -5.0f, -3.0f, 2.0f, 2.0f, 6.0f, 0.0f, false);
        this.LeftArm.texOffs(8, 54).addBox(8.0f, -5.0f, 3.0f, 2.0f, 10.0f, 2.0f, 0.0f, false);
        this.LeftArm.texOffs(50, 52).addBox(8.0f, -5.0f, -5.0f, 2.0f, 10.0f, 2.0f, 0.0f, false);
        this.LeftArm.texOffs(42, 52).addBox(0.0f, -5.0f, -5.0f, 2.0f, 10.0f, 2.0f, 0.0f, false);
        this.LeftArm.texOffs(0, 0).addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
        (this.RightBoot = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
        this.RightBoot.texOffs(0, 24).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
        (this.LeftBoot = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
        this.LeftBoot.texOffs(12, 12).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 1.0f, false);
        (this.Belt = new ModelRenderer((Model)this)).setPos(0.0f, 0.0f, 0.0f);
        this.Belt.texOffs(0, 0).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, 0.51f, false);
        this.Belt.texOffs(0, 16).addBox(-2.0f, 12.0f, 2.0f, 4.0f, 1.0f, 1.0f, 0.0f, false);
        this.Belt.texOffs(0, 16).addBox(-2.0f, 10.0f, 2.0f, 4.0f, 1.0f, 1.0f, 0.0f, false);
        this.Belt.texOffs(0, 0).addBox(1.0f, 11.0f, 2.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        this.Belt.texOffs(0, 0).addBox(-2.0f, 11.0f, 2.0f, 1.0f, 1.0f, 1.0f, 0.0f, false);
        (this.RightLeg = new ModelRenderer((Model)this)).setPos(-1.9f, 12.0f, 0.0f);
        this.RightLeg.texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, false);
        (this.LeftLeg = new ModelRenderer((Model)this)).setPos(1.9f, 12.0f, 0.0f);
        this.LeftLeg.texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, 0.5f, true);
    }
}
