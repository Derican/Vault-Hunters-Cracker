// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.backup;

import net.minecraft.command.arguments.UUIDArgument;
import net.minecraft.command.arguments.EntityArgument;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.StringReader;
import java.util.UUID;
import net.minecraft.command.CommandSource;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.ArgumentType;

public abstract class BackupListArgument implements ArgumentType<String>
{
    protected abstract UUID getPlayerRef(final CommandContext<CommandSource> p0);
    
    public String parse(final StringReader reader) throws CommandSyntaxException {
        return reader.readUnquotedString();
    }
    
    public static class Player extends BackupListArgument
    {
        @Override
        protected UUID getPlayerRef(final CommandContext<CommandSource> ctx) {
            try {
                return EntityArgument.getPlayer((CommandContext)ctx, "player").getUUID();
            }
            catch (final CommandSyntaxException e) {
                throw new RuntimeException((Throwable)e);
            }
        }
    }
    
    public static class UUIDRef extends BackupListArgument
    {
        @Override
        protected UUID getPlayerRef(final CommandContext<CommandSource> ctx) {
            return UUIDArgument.getUuid((CommandContext)ctx, "player");
        }
    }
}
