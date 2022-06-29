// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.world.vault.logic.objective;

import iskallia.vault.world.vault.gen.VaultGenerator;
import java.util.function.Supplier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import javax.annotation.Nullable;
import net.minecraft.loot.LootTable;
import java.util.function.Function;
import javax.annotation.Nonnull;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import iskallia.vault.world.vault.VaultRaid;
import iskallia.vault.world.vault.logic.task.VaultTask;
import net.minecraft.util.ResourceLocation;

public class TroveObjective extends VaultObjective
{
    public TroveObjective(final ResourceLocation id) {
        super(id, VaultTask.EMPTY, VaultTask.EMPTY);
    }
    
    @Nonnull
    @Override
    public BlockState getObjectiveRelevantBlock(final VaultRaid vault, final ServerWorld world, final BlockPos pos) {
        return Blocks.AIR.defaultBlockState();
    }
    
    @Nullable
    @Override
    public LootTable getRewardLootTable(final VaultRaid vault, final Function<ResourceLocation, LootTable> tblResolver) {
        return null;
    }
    
    @Override
    public ITextComponent getObjectiveDisplayName() {
        return this.getVaultName();
    }
    
    @Override
    public ITextComponent getVaultName() {
        return (ITextComponent)new StringTextComponent("Vault Trove").withStyle(TextFormatting.GOLD);
    }
    
    @Override
    public int getVaultTimerStart(final int vaultTime) {
        return 12000;
    }
    
    @Override
    public boolean preventsEatingExtensionFruit(final MinecraftServer srv, final VaultRaid vault) {
        return true;
    }
    
    @Override
    public boolean preventsMobSpawning() {
        return true;
    }
    
    @Override
    public boolean preventsTrappedChests() {
        return true;
    }
    
    @Override
    public boolean preventsInfluences() {
        return true;
    }
    
    @Nonnull
    @Override
    public Supplier<? extends VaultGenerator> getVaultGenerator() {
        return VaultRaid.TROVE_GENERATOR;
    }
}
