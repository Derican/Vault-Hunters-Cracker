
package iskallia.vault.client.gui.overlay;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.AbstractGui;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import iskallia.vault.client.gui.helper.ScreenDrawHelper;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import java.text.DecimalFormat;
import iskallia.vault.client.ClientDamageData;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PlayerDamageOverlay {
    private static final ResourceLocation STRENGTH_ICON;

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void setupHealthTexture(final RenderGameOverlayEvent.Post event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.FOOD) {
            return;
        }
        final PlayerEntity player = (PlayerEntity) Minecraft.getInstance().player;
        if (player == null) {
            return;
        }
        final Minecraft mc = Minecraft.getInstance();
        if (!mc.gameMode.hasExperience()) {
            return;
        }
        final float multiplier = ClientDamageData.getCurrentDamageMultiplier();
        if (Math.abs(multiplier - 1.0f) < 0.001) {
            return;
        }
        final DecimalFormat format = new DecimalFormat("0");
        final float value = (multiplier - 1.0f) * 100.0f;
        String displayStr = format.format(value);
        if (value >= 0.0f) {
            displayStr = "+" + displayStr;
        }
        displayStr += "%";
        final TextFormatting color = (value < 0.0f) ? TextFormatting.RED : TextFormatting.DARK_GREEN;
        final ITextComponent display = (ITextComponent) new StringTextComponent(displayStr).withStyle(color);
        ForgeIngameGui.left_height += 6;
        final int left = mc.getWindow().getGuiScaledWidth() / 2 - 91;
        final int top = mc.getWindow().getGuiScaledHeight() - ForgeIngameGui.left_height;
        final MatrixStack matrixStack = event.getMatrixStack();
        mc.getTextureManager().bind(PlayerDamageOverlay.STRENGTH_ICON);
        matrixStack.pushPose();
        matrixStack.translate((double) left, (double) top, 0.0);
        ScreenDrawHelper
                .drawQuad(buf -> ScreenDrawHelper.rect((IVertexBuilder) buf, matrixStack).dim(16.0f, 16.0f).draw());
        matrixStack.translate(16.0, 4.0, 0.0);
        mc.font.drawShadow(matrixStack, display, 0.0f, 0.0f, 16777215);
        matrixStack.popPose();
        mc.getTextureManager().bind(AbstractGui.GUI_ICONS_LOCATION);
    }

    static {
        STRENGTH_ICON = new ResourceLocation("minecraft", "textures/mob_effect/strength.png");
    }
}
