
package iskallia.vault.init;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.fml.RegistryObject;
import net.minecraft.potion.Potion;
import net.minecraftforge.registries.DeferredRegister;

public class ModPotions {
    public static final DeferredRegister<Potion> REGISTRY;
    public static RegistryObject<Potion> TIME_ACCELERATION_X2;
    public static RegistryObject<Potion> TIME_ACCELERATION_X3;
    public static RegistryObject<Potion> TIME_ACCELERATION_X4;

    static {
        REGISTRY = DeferredRegister.create(ForgeRegistries.POTION_TYPES, "the_vault");
        ModPotions.TIME_ACCELERATION_X2 = (RegistryObject<Potion>) ModPotions.REGISTRY.register("time_acceleration_x2",
                () -> {
                    new Potion(new EffectInstance[] { new EffectInstance(ModEffects.TIMER_ACCELERATION, 200, 1) });
                    return;
                });
        ModPotions.TIME_ACCELERATION_X3 = (RegistryObject<Potion>) ModPotions.REGISTRY.register("time_acceleration_x3",
                () -> {
                    new Potion(new EffectInstance[] { new EffectInstance(ModEffects.TIMER_ACCELERATION, 200, 2) });
                    return;
                });
        ModPotions.TIME_ACCELERATION_X4 = (RegistryObject<Potion>) ModPotions.REGISTRY.register("time_acceleration_x4",
                () -> {
                    new Potion(new EffectInstance[] { new EffectInstance(ModEffects.TIMER_ACCELERATION, 200, 3) });
                    return;
                });
    }
}
