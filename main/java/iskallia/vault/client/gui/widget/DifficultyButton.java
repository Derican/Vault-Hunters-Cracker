// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.client.gui.widget;

import java.util.List;
import net.minecraftforge.fml.client.gui.GuiUtils;
import net.minecraft.client.Minecraft;
import java.util.ArrayList;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import iskallia.vault.world.data.GlobalDifficultyData;
import net.minecraft.client.gui.widget.button.Button;

public class DifficultyButton extends Button
{
    private final String text;
    private final String key;
    GlobalDifficultyData.Difficulty difficulty;
    
    public DifficultyButton(final String text, final String key, final int x, final int y, final int width, final int height, final ITextComponent label, final Button.IPressable onPress) {
        super(x, y, width, height, label, onPress);
        this.difficulty = GlobalDifficultyData.Difficulty.STANDARD;
        this.text = text;
        this.key = key;
    }
    
    public void getNextOption() {
        this.difficulty = this.difficulty.getNext();
        this.setMessage((ITextComponent)new StringTextComponent(this.text + ": " + this.difficulty.toString()));
    }
    
    public String getKey() {
        return this.key.replace(" ", "");
    }
    
    public GlobalDifficultyData.Difficulty getCurrentOption() {
        return this.difficulty;
    }
    
    public void renderToolTip(final MatrixStack matrixStack, final int mouseX, final int mouseY) {
        final List<StringTextComponent> tooltips = new ArrayList<StringTextComponent>();
        tooltips.add(new StringTextComponent(this.text));
        GuiUtils.drawHoveringText(matrixStack, (List)tooltips, mouseX, mouseY, this.width + mouseX, this.height + mouseY, -1, Minecraft.getInstance().font);
    }
}
