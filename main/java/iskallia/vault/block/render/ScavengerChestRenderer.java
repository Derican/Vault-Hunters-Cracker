// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.block.render;

import iskallia.vault.Vault;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.tileentity.TileEntity;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.util.math.vector.Vector3f;
import java.util.function.Function;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.DualBrightnessCallback;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.tileentity.IChestLid;
import iskallia.vault.block.ScavengerChestBlock;
import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.state.Property;
import net.minecraft.util.Direction;
import net.minecraft.block.ChestBlock;
import iskallia.vault.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import iskallia.vault.block.model.ScavengerChestModel;
import net.minecraft.client.renderer.model.RenderMaterial;
import iskallia.vault.block.entity.ScavengerChestTileEntity;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;

public class ScavengerChestRenderer extends TileEntityRenderer<ScavengerChestTileEntity>
{
    public static final RenderMaterial MATERIAL;
    private static final ScavengerChestModel CHEST_MODEL;
    
    public ScavengerChestRenderer(final TileEntityRendererDispatcher terd) {
        super(terd);
    }
    
    public void render(final ScavengerChestTileEntity tileEntity, final float partialTicks, final MatrixStack matrixStack, final IRenderTypeBuffer buffer, final int combinedLight, final int combinedOverlay) {
        final boolean isInWorldRender = tileEntity.hasLevel();
        final BlockState renderState = (BlockState)(isInWorldRender ? tileEntity.getBlockState() : ModBlocks.SCAVENGER_CHEST.defaultBlockState().setValue((Property)ChestBlock.FACING, (Comparable)Direction.SOUTH));
        final float hAngle = ((Direction)renderState.getValue((Property)ChestBlock.FACING)).toYRot();
        final TileEntityMerger.ICallbackWrapper<? extends ChestTileEntity> lidCallback = (TileEntityMerger.ICallbackWrapper<? extends ChestTileEntity>)TileEntityMerger.ICallback::acceptNone;
        float lidRotation = ((Float2FloatFunction)lidCallback.apply(ScavengerChestBlock.opennessCombiner((IChestLid)tileEntity))).get(partialTicks);
        lidRotation = 1.0f - lidRotation;
        lidRotation = 1.0f - lidRotation * lidRotation * lidRotation;
        ScavengerChestRenderer.CHEST_MODEL.setLidAngle(lidRotation);
        final int combinedLidLight = ((Int2IntFunction)lidCallback.apply((TileEntityMerger.ICallback)new DualBrightnessCallback())).applyAsInt(combinedLight);
        final IVertexBuilder vb = ScavengerChestRenderer.MATERIAL.buffer(buffer, (Function)RenderType::entityCutout);
        matrixStack.pushPose();
        matrixStack.translate(0.5, 0.5, 0.5);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(-hAngle));
        ScavengerChestRenderer.CHEST_MODEL.renderToBuffer(matrixStack, vb, combinedLidLight, combinedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        matrixStack.popPose();
    }
    
    static {
        MATERIAL = new RenderMaterial(Atlases.CHEST_SHEET, Vault.id("entity/chest/scavenger_chest"));
        CHEST_MODEL = new ScavengerChestModel();
    }
}
