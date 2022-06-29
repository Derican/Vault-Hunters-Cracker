
package iskallia.vault.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.Arrays;
import java.util.StringJoiner;
import java.lang.management.ThreadInfo;
import net.minecraft.server.dedicated.ServerHangWatchdog;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ ServerHangWatchdog.class })
public class MixinServerHangWatchdog {
    @Redirect(method = {
            "run" }, at = @At(value = "INVOKE", target = "Ljava/lang/StringBuilder;append(Ljava/lang/Object;)Ljava/lang/StringBuilder;"))
    public StringBuilder appendThreadInfo(final StringBuilder sb, final Object obj) {
        if (obj instanceof ThreadInfo) {
            final StackTraceElement[] trace = ((ThreadInfo) obj).getStackTrace();
            final StringJoiner joiner = new StringJoiner("\n");
            Arrays.stream(trace).map((Function<? super StackTraceElement, ?>) StackTraceElement::toString)
                    .forEach((Consumer<? super Object>) joiner::add);
            sb.append(obj).append("\n");
            sb.append("Full Trace:\n");
            sb.append(joiner.toString());
            return sb;
        }
        return sb.append(obj);
    }
}
