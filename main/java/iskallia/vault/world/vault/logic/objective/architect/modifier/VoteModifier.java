// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.world.vault.logic.objective.architect.modifier;

import net.minecraft.world.server.ServerWorld;
import iskallia.vault.world.vault.logic.objective.architect.processor.VaultPieceProcessor;
import javax.annotation.Nullable;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import iskallia.vault.world.vault.VaultRaid;
import iskallia.vault.world.vault.logic.objective.architect.ArchitectObjective;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import com.google.gson.annotations.Expose;
import java.util.Random;

public class VoteModifier
{
    protected static final Random rand;
    @Expose
    private final String name;
    @Expose
    private final String description;
    @Expose
    private final String color;
    @Expose
    private final int voteLockDurationChangeSeconds;
    
    public VoteModifier(final String name, final String description, final int voteLockDurationChangeSeconds) {
        this.color = String.valueOf(65535);
        this.name = name;
        this.description = description;
        this.voteLockDurationChangeSeconds = voteLockDurationChangeSeconds;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDescriptionText() {
        return this.description;
    }
    
    public int getVoteLockDurationChangeSeconds() {
        return this.voteLockDurationChangeSeconds;
    }
    
    public ITextComponent getDescription() {
        return (ITextComponent)new StringTextComponent(this.getDescriptionText()).withStyle(Style.EMPTY.withColor(Color.fromRgb(Integer.parseInt(this.color))));
    }
    
    @Nullable
    public JigsawPiece getSpecialRoom(final ArchitectObjective objective, final VaultRaid vault) {
        return null;
    }
    
    @Nullable
    public VaultPieceProcessor getPostProcessor(final ArchitectObjective objective, final VaultRaid vault) {
        return null;
    }
    
    public void onApply(final ArchitectObjective objective, final VaultRaid vault, final ServerWorld world) {
    }
    
    static {
        rand = new Random();
    }
}
