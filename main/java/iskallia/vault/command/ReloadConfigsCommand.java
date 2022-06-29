// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.command;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.StringTextComponent;
import iskallia.vault.network.message.ShardGlobalTradeMessage;
import net.minecraftforge.fml.network.PacketDistributor;
import iskallia.vault.init.ModNetwork;
import iskallia.vault.init.ModConfigs;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandSource;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

public class ReloadConfigsCommand extends Command
{
    @Override
    public String getName() {
        return "reloadcfg";
    }
    
    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }
    
    @Override
    public void build(final LiteralArgumentBuilder<CommandSource> builder) {
        builder.executes(this::reloadConfigs);
    }
    
    private int reloadConfigs(final CommandContext<CommandSource> context) {
        try {
            ModConfigs.register();
        }
        catch (final Exception e) {
            e.printStackTrace();
            throw e;
        }
        ModNetwork.CHANNEL.send(PacketDistributor.ALL.noArg(), (Object)new ShardGlobalTradeMessage(ModConfigs.SOUL_SHARD.getShardTrades()));
        ((CommandSource)context.getSource()).sendSuccess((ITextComponent)new StringTextComponent("Configs reloaded!").withStyle(TextFormatting.GREEN), true);
        return 0;
    }
    
    @Override
    public boolean isDedicatedServerOnly() {
        return false;
    }
}
