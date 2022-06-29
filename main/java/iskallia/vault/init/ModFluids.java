
package iskallia.vault.init;

import java.util.function.Supplier;
import net.minecraftforge.registries.ForgeRegistries;
import iskallia.vault.fluid.VoidFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraft.fluid.Fluid;
import net.minecraftforge.registries.DeferredRegister;

public class ModFluids {
    public static final DeferredRegister<Fluid> REGISTRY;
    public static final RegistryObject<VoidFluid.Source> VOID_LIQUID;
    public static final RegistryObject<VoidFluid.Flowing> FLOWING_VOID_LIQUID;

    static {
        REGISTRY = DeferredRegister.create(ForgeRegistries.FLUIDS, "the_vault");
        VOID_LIQUID = ModFluids.REGISTRY.register("void_liquid", (Supplier) VoidFluid.Source::new);
        FLOWING_VOID_LIQUID = ModFluids.REGISTRY.register("flowing_void_liquid", (Supplier) VoidFluid.Flowing::new);
    }
}
