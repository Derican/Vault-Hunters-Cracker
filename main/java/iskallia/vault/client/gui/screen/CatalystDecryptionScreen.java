// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.client.gui.screen;

import iskallia.vault.Vault;
import java.util.Iterator;
import net.minecraft.item.ItemStack;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Collection;
import java.util.function.Function;
import iskallia.vault.item.catalyst.ModifierRollResult;
import java.util.List;
import iskallia.vault.item.VaultInhibitorItem;
import iskallia.vault.item.VaultCatalystItem;
import net.minecraft.inventory.container.Slot;
import iskallia.vault.item.crystal.VaultCrystalItem;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import iskallia.vault.container.inventory.CatalystDecryptionContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;

public class CatalystDecryptionScreen extends ContainerScreen<CatalystDecryptionContainer>
{
    private static final ResourceLocation TEXTURE;
    
    public CatalystDecryptionScreen(final CatalystDecryptionContainer screenContainer, final PlayerInventory inv, final ITextComponent titleIn) {
        super((Container)screenContainer, inv, titleIn);
        this.imageWidth = 176;
        this.imageHeight = 234;
    }
    
    protected void renderBg(final MatrixStack matrixStack, final float partialTicks, final int x, final int y) {
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.getTextureManager().bind(CatalystDecryptionScreen.TEXTURE);
        final int offsetX = (this.width - this.imageWidth) / 2;
        final int offsetY = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack, offsetX, offsetY, 0, 0, this.imageWidth, this.imageHeight);
    }
    
    protected void renderLabels(final MatrixStack matrixStack, final int x, final int y) {
        this.font.draw(matrixStack, this.title, 5.0f, 5.0f, 4210752);
        final Slot crystalSlot = ((CatalystDecryptionContainer)this.menu).getSlot(((CatalystDecryptionContainer)this.menu).slots.size() - 1);
        if (!crystalSlot.hasItem()) {
            return;
        }
        final ItemStack crystal = crystalSlot.getItem();
        if (crystal.isEmpty() || !(crystal.getItem() instanceof VaultCrystalItem)) {
            return;
        }
        for (final Slot catalystSlot : ((CatalystDecryptionContainer)this.menu).getCatalystSlots()) {
            if (!catalystSlot.hasItem()) {
                continue;
            }
            final ItemStack modifyingItem = catalystSlot.getItem();
            if (modifyingItem.isEmpty()) {
                continue;
            }
            if (!(modifyingItem.getItem() instanceof VaultCatalystItem) && !(modifyingItem.getItem() instanceof VaultInhibitorItem)) {
                continue;
            }
            final boolean catalyst = modifyingItem.getItem() instanceof VaultCatalystItem;
            final List<String> modifierOutcomes = catalyst ? VaultCatalystItem.getCrystalCombinationModifiers(modifyingItem, crystal) : VaultInhibitorItem.getCrystalCombinationModifiers(modifyingItem, crystal);
            if (modifierOutcomes == null) {
                continue;
            }
            if (modifierOutcomes.isEmpty()) {
                continue;
            }
            final boolean isLeft = catalystSlot.index % 2 == 0;
            final List<ITextComponent> results = modifierOutcomes.stream().map((Function<? super Object, ?>)ModifierRollResult::ofModifier).map(result -> result.getTooltipDescription(catalyst ? "Adds " : "Removes ", false)).flatMap((Function<? super Object, ? extends Stream<?>>)Collection::stream).collect((Collector<? super Object, ?, List<ITextComponent>>)Collectors.toList());
            RenderSystem.pushMatrix();
            if (!isLeft) {
                RenderSystem.translatef((float)(catalystSlot.x + 14), (float)catalystSlot.y, 0.0f);
                RenderSystem.scalef(0.65f, 0.65f, 0.65f);
                this.renderWrappedToolTip(matrixStack, (List)results, 0, 0, this.font);
            }
            else {
                final int maxLength = results.stream().mapToInt(txt -> this.font.width(txt.getVisualOrderText())).max().orElse(0);
                RenderSystem.translatef(catalystSlot.x - 14 - maxLength * 0.65f, (float)catalystSlot.y, 0.0f);
                RenderSystem.scalef(0.65f, 0.65f, 0.65f);
                this.renderWrappedToolTip(matrixStack, (List)results, 0, 0, this.font);
            }
            RenderSystem.popMatrix();
        }
    }
    
    public void render(final MatrixStack matrixStack, final int mouseX, final int mouseY, final float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }
    
    public boolean isPauseScreen() {
        return false;
    }
    
    static {
        TEXTURE = Vault.id("textures/gui/catalyst-decryption-table.png");
    }
}
