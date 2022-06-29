// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.client.gui.overlay;

import org.lwjgl.opengl.ARBShaderObjects;
import iskallia.vault.client.util.ShaderUtil;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.client.network.play.ClientPlayNetHandler;
import iskallia.vault.world.data.VaultPartyData;
import java.util.UUID;
import iskallia.vault.client.ClientPartyData;
import iskallia.vault.config.EternalAuraConfig;
import net.minecraft.util.text.IFormattableTextComponent;
import iskallia.vault.init.ModConfigs;
import java.util.Iterator;
import net.minecraft.util.text.ITextComponent;
import java.util.Set;
import iskallia.vault.entity.eternal.ActiveEternalData;
import iskallia.vault.client.gui.helper.FontHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.StringTextComponent;
import iskallia.vault.client.ClientActiveEternalData;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VaultPartyOverlay
{
    public static final ResourceLocation VAULT_HUD_SPRITE;
    
    @SubscribeEvent
    public static void renderSidebarHUD(final RenderGameOverlayEvent.Post event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.HOTBAR) {
            return;
        }
        final Minecraft mc = Minecraft.getInstance();
        final ClientPlayerEntity player = mc.player;
        if (player == null) {
            return;
        }
        final MatrixStack matrixStack = event.getMatrixStack();
        final int bottom = mc.getWindow().getGuiScaledHeight();
        final int right = mc.getWindow().getGuiScaledWidth();
        float offsetY = Math.max(bottom / 3.0f, 45.0f);
        offsetY += renderPartyHUD(matrixStack, offsetY, right);
        offsetY += renderEternalHUD(matrixStack, offsetY, right);
        mc.getTextureManager().bind(AbstractGui.GUI_ICONS_LOCATION);
    }
    
    private static int renderEternalHUD(final MatrixStack matrixStack, final float offsetY, final int right) {
        final Set<ActiveEternalData.ActiveEternal> eternals = ClientActiveEternalData.getActiveEternals();
        if (eternals.isEmpty()) {
            return 0;
        }
        int height = 0;
        matrixStack.pushPose();
        matrixStack.translate((double)(right - 5), (double)offsetY, 0.0);
        matrixStack.pushPose();
        matrixStack.scale(0.8f, 0.8f, 1.0f);
        final ITextComponent vpText = (ITextComponent)new StringTextComponent("Eternals").withStyle(TextFormatting.GOLD);
        FontHelper.drawTextComponent(matrixStack, vpText, true);
        matrixStack.popPose();
        height += 8;
        matrixStack.translate(0.0, 8.0, -50.0);
        matrixStack.scale(0.7f, 0.7f, 1.0f);
        for (final ActiveEternalData.ActiveEternal eternal : eternals) {
            final int eternalHeight = renderEternalSection(matrixStack, eternal) + 4;
            matrixStack.translate(0.0, (double)eternalHeight, 0.0);
            height += (int)(eternalHeight * 0.7f);
        }
        matrixStack.popPose();
        return height + 6;
    }
    
    private static int renderEternalSection(final MatrixStack matrixStack, final ActiveEternalData.ActiveEternal eternal) {
        final int textSize = 8;
        final int headSize = 16;
        final int gap = 2;
        final boolean dead = eternal.getHealth() <= 0.0f;
        final ResourceLocation skin = eternal.getSkin().getLocationSkin();
        final IFormattableTextComponent txt = (IFormattableTextComponent)new StringTextComponent("");
        matrixStack.pushPose();
        matrixStack.translate((double)(-headSize), 0.0, 0.0);
        render2DHead(matrixStack, skin, headSize, dead);
        matrixStack.translate((double)(-gap), (double)((headSize - textSize) / 2.0f), 0.0);
        if (dead) {
            txt.append((ITextComponent)new StringTextComponent("Unalived").withStyle(TextFormatting.RED));
        }
        else {
            final int heartSize = 9;
            final int heartU = 86;
            final int heartV = 2;
            matrixStack.translate((double)(-heartSize), 0.0, 0.0);
            Minecraft.getInstance().getTextureManager().bind(VaultPartyOverlay.VAULT_HUD_SPRITE);
            AbstractGui.blit(matrixStack, 0, 0, (float)heartU, (float)heartV, heartSize, heartSize, 256, 256);
            matrixStack.translate((double)(-gap), 0.0, 0.0);
            txt.append((ITextComponent)new StringTextComponent((int)eternal.getHealth() + "x").withStyle(TextFormatting.WHITE));
        }
        final int width = FontHelper.drawTextComponent(matrixStack, (ITextComponent)txt, true);
        if (eternal.getAbilityName() != null) {
            final EternalAuraConfig.AuraConfig cfg = ModConfigs.ETERNAL_AURAS.getByName(eternal.getAbilityName());
            if (cfg != null) {
                matrixStack.translate((double)(-(width + 18)), -4.0, 0.0);
                Minecraft.getInstance().getTextureManager().bind(new ResourceLocation(cfg.getIconPath()));
                AbstractGui.blit(matrixStack, 0, 0, 0.0f, 0.0f, 16, 16, 16, 16);
            }
        }
        matrixStack.popPose();
        return headSize;
    }
    
    private static int renderPartyHUD(final MatrixStack matrixStack, final float offsetY, final int right) {
        final ClientPlayerEntity player = Minecraft.getInstance().player;
        final VaultPartyData.Party thisParty = ClientPartyData.getParty(player.getUUID());
        if (thisParty == null) {
            return 0;
        }
        int height = 0;
        matrixStack.pushPose();
        matrixStack.translate((double)(right - 5), (double)offsetY, 0.0);
        matrixStack.pushPose();
        matrixStack.scale(0.8f, 0.8f, 1.0f);
        final ITextComponent vpText = (ITextComponent)new StringTextComponent("Vault Party").withStyle(TextFormatting.GREEN);
        FontHelper.drawTextComponent(matrixStack, vpText, true);
        matrixStack.popPose();
        height += 8;
        matrixStack.translate(0.0, 8.0, -50.0);
        matrixStack.scale(0.7f, 0.7f, 1.0f);
        final ClientPlayNetHandler netHandler = Minecraft.getInstance().getConnection();
        if (netHandler != null) {
            for (final UUID uuid : thisParty.getMembers()) {
                final NetworkPlayerInfo info = netHandler.getPlayerInfo(uuid);
                final int playerHeight = renderPartyPlayerSection(matrixStack, thisParty, uuid, info) + 4;
                matrixStack.translate(0.0, (double)playerHeight, 0.0);
                height += (int)(playerHeight * 0.7f);
            }
        }
        matrixStack.popPose();
        return height + 6;
    }
    
    private static int renderPartyPlayerSection(final MatrixStack matrixStack, final VaultPartyData.Party party, final UUID playerUUID, final NetworkPlayerInfo info) {
        final int textSize = 8;
        final int headSize = 16;
        final int gap = 2;
        final boolean offline = info == null;
        final ClientPartyData.PartyMember member = offline ? null : ClientPartyData.getCachedMember(info.getProfile().getId());
        final ResourceLocation skin = offline ? DefaultPlayerSkin.getDefaultSkin() : info.getSkinLocation();
        final String prefix = playerUUID.equals(party.getLeader()) ? "\u2b50 " : "";
        final IFormattableTextComponent txt = new StringTextComponent(prefix).withStyle(TextFormatting.GOLD);
        matrixStack.pushPose();
        matrixStack.translate((double)(-headSize), 0.0, 0.0);
        render2DHead(matrixStack, skin, headSize, offline);
        matrixStack.translate((double)(-gap), (double)((headSize - textSize) / 2.0f), 0.0);
        if (offline) {
            txt.append((ITextComponent)new StringTextComponent(prefix + "OFFLINE").withStyle(TextFormatting.GRAY));
        }
        else {
            final int heartSize = 9;
            final int heartU = 86;
            final int heartV = 2;
            matrixStack.translate((double)(-heartSize), 0.0, 0.0);
            Minecraft.getInstance().getTextureManager().bind(VaultPartyOverlay.VAULT_HUD_SPRITE);
            final ClientPartyData.PartyMember.Status status = (member == null) ? ClientPartyData.PartyMember.Status.NORMAL : member.status;
            AbstractGui.blit(matrixStack, 0, 0, (float)(heartU + getPartyPlayerStatusOffset(status)), (float)heartV, heartSize, heartSize, 256, 256);
            matrixStack.translate((double)(-gap), 0.0, 0.0);
            txt.append((ITextComponent)new StringTextComponent((member == null) ? "-" : ((int)member.healthPts + "x")).withStyle(TextFormatting.WHITE));
        }
        FontHelper.drawTextComponent(matrixStack, (ITextComponent)txt, true);
        matrixStack.popPose();
        return headSize;
    }
    
    private static int getPartyPlayerStatusOffset(final ClientPartyData.PartyMember.Status status) {
        switch (status) {
            case POISONED: {
                return 10;
            }
            case WITHERED: {
                return 20;
            }
            case DEAD: {
                return 30;
            }
            default: {
                return 0;
            }
        }
    }
    
    public static void render2DHead(final MatrixStack matrixStack, final ResourceLocation skin, final int size, final boolean grayscaled) {
        final Minecraft minecraft = Minecraft.getInstance();
        minecraft.getTextureManager().bind(skin);
        final int u1 = 8;
        final int v1 = 8;
        final int u2 = 40;
        final int v2 = 8;
        final int w = 8;
        final int h = 8;
        if (grayscaled) {
            ShaderUtil.useShader(ShaderUtil.GRAYSCALE_SHADER, () -> {
                final int grayScaleFactor = ShaderUtil.getUniformLocation(ShaderUtil.GRAYSCALE_SHADER, "grayFactor");
                ARBShaderObjects.glUniform1fARB(grayScaleFactor, 0.0f);
                return;
            });
        }
        AbstractGui.blit(matrixStack, 0, 0, size, size, (float)u1, (float)v1, w, h, 64, 64);
        AbstractGui.blit(matrixStack, 0, 0, size, size, (float)u2, (float)v2, w, h, 64, 64);
        if (grayscaled) {
            ShaderUtil.releaseShader();
        }
    }
    
    static {
        VAULT_HUD_SPRITE = new ResourceLocation("the_vault", "textures/gui/vault-hud.png");
    }
}
