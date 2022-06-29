// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.command;

import iskallia.vault.world.vault.VaultRaid;
import net.minecraft.entity.player.ServerPlayerEntity;
import iskallia.vault.world.vault.logic.objective.architect.ArchitectObjective;
import iskallia.vault.world.data.VaultRaidData;
import net.minecraft.util.Direction;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import iskallia.vault.world.data.SoulShardTraderData;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.command.Commands;
import net.minecraft.command.CommandSource;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

public class InternalCommand extends Command
{
    @Override
    public String getName() {
        return "internal";
    }
    
    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }
    
    @Override
    public void build(final LiteralArgumentBuilder<CommandSource> builder) {
        builder.then(Commands.literal("reset_shard_trades").executes(this::resetShardTrader));
        builder.then(Commands.literal("player_vote").then(Commands.argument("a", (ArgumentType)StringArgumentType.word()).then(Commands.argument("b", (ArgumentType)StringArgumentType.word()).executes(ctx -> this.voteFor((CommandSource)ctx.getSource(), StringArgumentType.getString(ctx, "a"), Direction.byName(StringArgumentType.getString(ctx, "b")))))));
    }
    
    private int resetShardTrader(final CommandContext<CommandSource> ctx) throws CommandSyntaxException {
        SoulShardTraderData.get(((CommandSource)ctx.getSource()).getServer()).resetTrades();
        return 0;
    }
    
    private int voteFor(final CommandSource src, final String voter, final Direction direction) throws CommandSyntaxException {
        final ServerPlayerEntity sPlayer = src.getPlayerOrException();
        final VaultRaid vault = VaultRaidData.get(sPlayer.getLevel()).getActiveFor(sPlayer);
        if (direction == null) {
            return 0;
        }
        if (vault == null) {
            return 0;
        }
        if (!vault.getActiveObjective(ArchitectObjective.class).map(objective -> objective.handleVote(voter, direction)).orElse(false)) {
            return 0;
        }
        return 1;
    }
    
    @Override
    public boolean isDedicatedServerOnly() {
        return false;
    }
}
