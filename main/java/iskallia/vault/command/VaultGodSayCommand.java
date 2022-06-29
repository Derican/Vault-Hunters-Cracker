// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.command;

import net.minecraft.util.text.Style;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Util;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.StringTextComponent;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.arguments.MessageArgument;
import com.mojang.brigadier.arguments.ArgumentType;
import net.minecraft.command.Commands;
import net.minecraftforge.server.command.EnumArgument;
import iskallia.vault.world.data.PlayerFavourData;
import net.minecraft.command.CommandSource;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

public class VaultGodSayCommand extends Command
{
    @Override
    public String getName() {
        return "say";
    }
    
    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }
    
    @Override
    public void build(final LiteralArgumentBuilder<CommandSource> builder) {
        builder.then(Commands.argument("sender", (ArgumentType)EnumArgument.enumArgument((Class)PlayerFavourData.VaultGodType.class)).then(Commands.argument("message", (ArgumentType)MessageArgument.message()).executes(this::onSay)));
    }
    
    private int onSay(final CommandContext<CommandSource> ctx) throws CommandSyntaxException {
        final MinecraftServer srv = ((CommandSource)ctx.getSource()).getServer();
        final ITextComponent text = MessageArgument.getMessage((CommandContext)ctx, "message");
        final PlayerFavourData.VaultGodType sender = (PlayerFavourData.VaultGodType)ctx.getArgument("sender", (Class)PlayerFavourData.VaultGodType.class);
        final StringTextComponent senderTxt = new StringTextComponent("[VG] ");
        senderTxt.withStyle(TextFormatting.DARK_PURPLE).append((ITextComponent)new StringTextComponent(sender.getName()).withStyle(sender.getChatColor())).append((ITextComponent)new StringTextComponent(": ").withStyle(TextFormatting.WHITE));
        senderTxt.withStyle(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (Object)sender.getHoverChatComponent())));
        srv.getPlayerList().broadcastMessage((ITextComponent)new StringTextComponent("").append((ITextComponent)senderTxt).append(text), ChatType.SYSTEM, Util.NIL_UUID);
        return 0;
    }
    
    @Override
    public boolean isDedicatedServerOnly() {
        return false;
    }
}
