
package iskallia.vault.entity.renderer;

import iskallia.vault.Vault;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.layers.CrossedArmsItemLayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.HeadLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.entity.model.VillagerModel;
import iskallia.vault.entity.EtchingVendorEntity;
import net.minecraft.client.renderer.entity.MobRenderer;

public class EtchingVendorRenderer extends MobRenderer<EtchingVendorEntity, VillagerModel<EtchingVendorEntity>> {
    private static final ResourceLocation TEXTURES;

    public EtchingVendorRenderer(final EntityRendererManager renderManagerIn) {
        super(renderManagerIn, (EntityModel) new VillagerModel(0.0f), 0.5f);
        this.addLayer((LayerRenderer) new HeadLayer((IEntityRenderer) this));
        this.addLayer((LayerRenderer) new CrossedArmsItemLayer((IEntityRenderer) this));
    }

    public ResourceLocation getEntityTexture(final EtchingVendorEntity entity) {
        return EtchingVendorRenderer.TEXTURES;
    }

    protected void preRenderCallback(final EtchingVendorEntity entity, final MatrixStack matrixStack,
            final float pTicks) {
        this.shadowRadius = 0.5f;
        final float size = 0.9375f;
        matrixStack.scale(size, size, size);
    }

    static {
        TEXTURES = Vault.id("textures/entity/etching_trader.png");
    }
}
