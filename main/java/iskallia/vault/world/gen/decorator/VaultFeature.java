
package iskallia.vault.world.gen.decorator;

import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.util.registry.DynamicRegistries;
import iskallia.vault.world.gen.VaultJigsawGenerator;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import iskallia.vault.world.gen.structure.VaultStructure;
import net.minecraft.world.gen.feature.StructureFeature;

public class VaultFeature extends StructureFeature<VaultStructure.Config, Structure<VaultStructure.Config>> {
    public VaultFeature(final Structure<VaultStructure.Config> p_i231937_1_, final VaultStructure.Config p_i231937_2_) {
        super((Structure) p_i231937_1_, (IFeatureConfig) p_i231937_2_);
    }

    public StructureStart<?> generate(final VaultJigsawGenerator jigsaw, final DynamicRegistries registry,
            final ChunkGenerator gen, final TemplateManager manager, final int references, final long worldSeed) {
        final VaultStructure.Start start = (VaultStructure.Start) this.feature.getStartFactory().create(
                this.feature, jigsaw.getStartPos().getX() >> 4,
                jigsaw.getStartPos().getZ() >> 4, MutableBoundingBox.getUnknownBox(), references, worldSeed);
        start.generate(jigsaw, registry, gen, manager);
        if (start.isValid()) {
            return (StructureStart<?>) start;
        }
        return (StructureStart<?>) StructureStart.INVALID_START;
    }
}
