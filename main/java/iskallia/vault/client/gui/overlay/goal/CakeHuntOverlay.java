// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.client.gui.overlay.goal;

import iskallia.vault.Vault;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.client.gui.FontRenderer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.AbstractGui;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import iskallia.vault.client.gui.helper.ScreenDrawHelper;
import com.mojang.blaze3d.systems.RenderSystem;
import iskallia.vault.client.gui.helper.LightmapHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Tessellator;
import iskallia.vault.client.vault.goal.CakeHuntData;
import iskallia.vault.client.vault.goal.VaultGoalData;
import net.minecraft.client.Minecraft;
import iskallia.vault.network.message.VaultOverlayMessage;
import iskallia.vault.client.ClientVaultRaidData;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = { Dist.CLIENT }, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CakeHuntOverlay
{
    private static final ResourceLocation ARCHITECT_HUD;
    
    @SubscribeEvent
    public static void onArchitectBuild(final RenderGameOverlayEvent.Post event) {
        final VaultOverlayMessage.OverlayType type = ClientVaultRaidData.getOverlayType();
        if (event.getType() != RenderGameOverlayEvent.ElementType.HOTBAR || type != VaultOverlayMessage.OverlayType.VAULT) {
            return;
        }
        final Minecraft mc = Minecraft.getInstance();
        final VaultGoalData data = VaultGoalData.CURRENT_DATA;
        if (data instanceof CakeHuntData) {
            final CakeHuntData displayData = (CakeHuntData)data;
            final MatrixStack renderStack = event.getMatrixStack();
            final IRenderTypeBuffer.Impl buffer = IRenderTypeBuffer.immediate(Tessellator.getInstance().getBuilder());
            final FontRenderer fr = mc.font;
            final int bottom = mc.getWindow().getGuiScaledHeight();
            final float part = displayData.getCompletePercent();
            ITextComponent txt = (ITextComponent)new StringTextComponent("Find the cakes!").withStyle(TextFormatting.AQUA).withStyle(TextFormatting.BOLD);
            fr.drawInBatch(txt.getVisualOrderText(), 8.0f, (float)(bottom - 54), -1, true, renderStack.last().pose(), (IRenderTypeBuffer)buffer, false, 0, LightmapHelper.getPackedFullbrightCoords());
            txt = (ITextComponent)new StringTextComponent(displayData.getFoundCakes() + " / " + displayData.getTotalCakes()).withStyle(TextFormatting.AQUA).withStyle(TextFormatting.BOLD);
            fr.drawInBatch(txt.getVisualOrderText(), 12.0f, (float)(bottom - 44), -1, true, renderStack.last().pose(), (IRenderTypeBuffer)buffer, false, 0, LightmapHelper.getPackedFullbrightCoords());
            buffer.endBatch();
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            mc.getTextureManager().bind(CakeHuntOverlay.ARCHITECT_HUD);
            ScreenDrawHelper.drawQuad(buf -> {
                ScreenDrawHelper.rect((IVertexBuilder)buf, renderStack).at(15.0f, (float)(bottom - 31)).dim(54.0f, 7.0f).texVanilla(0.0f, 105.0f, 54.0f, 7.0f).draw();
                ScreenDrawHelper.rect((IVertexBuilder)buf, renderStack).at(16.0f, (float)(bottom - 30)).dim(52.0f * part, 5.0f).texVanilla(0.0f, 113.0f, 52.0f * part, 5.0f).draw();
                return;
            });
        }
        mc.getTextureManager().bind(AbstractGui.GUI_ICONS_LOCATION);
    }
    
    static {
        ARCHITECT_HUD = Vault.id("textures/gui/architect_event_bar.png");
    }
}
