// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.util;

import java.util.Iterator;
import net.minecraft.loot.LootParameter;
import net.minecraft.loot.LootParameterSet;
import net.minecraft.loot.LootContext;

public class LootUtils
{
    public static boolean doesContextFulfillSet(final LootContext ctx, final LootParameterSet set) {
        for (final LootParameter<?> required : set.getRequired()) {
            if (!ctx.hasParam((LootParameter)required)) {
                return false;
            }
        }
        return true;
    }
}
