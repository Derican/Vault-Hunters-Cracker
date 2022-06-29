
package iskallia.vault.init;

import iskallia.vault.effect.TimerAccelerationEffect;
import iskallia.vault.effect.ImmunityEffect;
import iskallia.vault.effect.BasicEffect;
import iskallia.vault.effect.ExecuteEffect;
import iskallia.vault.effect.TankEffect;
import iskallia.vault.effect.RampageEffect;
import iskallia.vault.effect.GhostWalkEffect;
import iskallia.vault.Vault;
import java.awt.Color;
import net.minecraft.potion.EffectType;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraft.potion.Effect;

public class ModEffects {
    public static final Effect GHOST_WALK;
    public static final Effect RAMPAGE;
    public static final Effect TANK;
    public static final Effect EXECUTE;
    public static final Effect PARRY;
    public static final Effect RESISTANCE;
    public static final Effect VAULT_POWERUP;
    public static final Effect IMMUNITY;
    public static final Effect TIMER_ACCELERATION;

    public static void register(final RegistryEvent.Register<Effect> event) {
        event.getRegistry()
                .registerAll((IForgeRegistryEntry[]) new Effect[] { ModEffects.GHOST_WALK, ModEffects.RAMPAGE,
                        ModEffects.TANK, ModEffects.EXECUTE, ModEffects.TIMER_ACCELERATION, ModEffects.PARRY,
                        ModEffects.RESISTANCE, ModEffects.VAULT_POWERUP, ModEffects.IMMUNITY });
    }

    static {
        GHOST_WALK = new GhostWalkEffect(EffectType.BENEFICIAL, Color.GRAY.getRGB(), Vault.id("ghost_walk"));
        RAMPAGE = new RampageEffect(EffectType.BENEFICIAL, Color.RED.getRGB(), Vault.id("rampage"));
        TANK = new TankEffect(EffectType.BENEFICIAL, Color.WHITE.getRGB(), Vault.id("tank"));
        EXECUTE = new ExecuteEffect(EffectType.BENEFICIAL, Color.YELLOW.getRGB(), Vault.id("execute"));
        PARRY = new BasicEffect(EffectType.BENEFICIAL, Color.WHITE.getRGB(), Vault.id("parry"));
        RESISTANCE = new BasicEffect(EffectType.BENEFICIAL, Color.YELLOW.getRGB(), Vault.id("resistance"));
        VAULT_POWERUP = new BasicEffect(EffectType.BENEFICIAL, Color.BLACK.getRGB(), Vault.id("vault_powerup"));
        IMMUNITY = new ImmunityEffect(EffectType.BENEFICIAL, Color.WHITE.getRGB(), Vault.id("immunity"));
        TIMER_ACCELERATION = new TimerAccelerationEffect(EffectType.HARMFUL, -16448251, Vault.id("time_acceleration"));
    }
}
