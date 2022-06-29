// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.skill.ability;

import com.google.common.collect.HashBiMap;
import javax.annotation.Nullable;
import net.minecraftforge.common.MinecraftForge;
import iskallia.vault.skill.ability.effect.AbilityEffect;
import com.google.common.collect.BiMap;

public class AbilityRegistry
{
    private static final BiMap<String, AbilityEffect<?>> abilityRegistry;
    
    public static <E extends AbilityEffect<?>> E register(final String key, final E ability) {
        AbilityRegistry.abilityRegistry.put((Object)key, (Object)ability);
        MinecraftForge.EVENT_BUS.register((Object)ability);
        return ability;
    }
    
    @Nullable
    public static AbilityEffect<?> getAbility(final String key) {
        return (AbilityEffect)AbilityRegistry.abilityRegistry.get((Object)key);
    }
    
    @Nullable
    public static String getKey(final AbilityEffect<?> ability) {
        return (String)AbilityRegistry.abilityRegistry.inverse().get((Object)ability);
    }
    
    static {
        abilityRegistry = (BiMap)HashBiMap.create();
    }
}
