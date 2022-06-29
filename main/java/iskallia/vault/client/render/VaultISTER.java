// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.client.render;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import iskallia.vault.block.entity.ScavengerChestTileEntity;
import iskallia.vault.block.ScavengerChestBlock;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import iskallia.vault.block.entity.VaultChestTileEntity;
import net.minecraft.world.IBlockReader;
import iskallia.vault.block.VaultChestBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;

public class VaultISTER extends ItemStackTileEntityRenderer
{
    public static final VaultISTER INSTANCE;
    
    private VaultISTER() {
    }
    
    public void renderByItem(final ItemStack stack, final ItemCameraTransforms.TransformType type, final MatrixStack matrixStack, final IRenderTypeBuffer buffer, final int combinedLight, final int combinedOverlay) {
        final World world = (World)Minecraft.getInstance().level;
        if (world != null && stack.getItem() instanceof BlockItem) {
            final Block block = ((BlockItem)stack.getItem()).getBlock();
            if (block instanceof VaultChestBlock) {
                final TileEntity te = ((VaultChestBlock)block).newBlockEntity((IBlockReader)world);
                if (te instanceof VaultChestTileEntity) {
                    ((VaultChestTileEntity)te).setRenderState(block.defaultBlockState());
                    TileEntityRendererDispatcher.instance.renderItem(te, matrixStack, buffer, combinedLight, combinedOverlay);
                }
            }
            if (block instanceof ScavengerChestBlock) {
                final TileEntity te = ((ScavengerChestBlock)block).newBlockEntity((IBlockReader)world);
                if (te instanceof ScavengerChestTileEntity) {
                    TileEntityRendererDispatcher.instance.renderItem(te, matrixStack, buffer, combinedLight, combinedOverlay);
                }
            }
        }
    }
    
    static {
        INSTANCE = new VaultISTER();
    }
}
