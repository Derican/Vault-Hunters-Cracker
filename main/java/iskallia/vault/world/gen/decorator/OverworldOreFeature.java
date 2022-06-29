
package iskallia.vault.world.gen.decorator;

import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraftforge.registries.IForgeRegistryEntry;
import iskallia.vault.Vault;
import net.minecraftforge.event.RegistryEvent;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import java.util.Random;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.ISeedReader;
import com.mojang.serialization.Codec;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeature;

public class OverworldOreFeature extends OreFeature {
    public static Feature<OreFeatureConfig> INSTANCE;

    public OverworldOreFeature(final Codec<OreFeatureConfig> codec) {
        super((Codec) codec);
    }

    public boolean place(final ISeedReader world, final ChunkGenerator gen, final Random random,
            final BlockPos pos, final OreFeatureConfig config) {
        if (world.getLevel().dimension() != World.OVERWORLD) {
            return false;
        }
        if (config.size != 1) {
            return super.place(world, gen, random, pos, config);
        }
        if (config.target.test(world.getBlockState(pos), random)) {
            world.setBlock(pos, config.state, 2);
            return true;
        }
        return false;
    }

    public static void register(final RegistryEvent.Register<Feature<?>> event) {
        (OverworldOreFeature.INSTANCE = (Feature<OreFeatureConfig>) new OverworldOreFeature(
                (Codec<OreFeatureConfig>) OreFeatureConfig.CODEC)).setRegistryName(Vault.id("overworld_ore"));
        event.getRegistry().register((IForgeRegistryEntry) OverworldOreFeature.INSTANCE);
    }
}
