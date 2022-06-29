
package iskallia.vault.world.gen.decorator;

import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.util.registry.DynamicRegistries;
import iskallia.vault.world.gen.structure.JigsawGenerator;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import iskallia.vault.world.gen.structure.ArchitectEventStructure;
import net.minecraft.world.gen.feature.StructureFeature;

public class ArchitectEventFeature
        extends StructureFeature<ArchitectEventStructure.Config, Structure<ArchitectEventStructure.Config>> {
    public ArchitectEventFeature(final Structure<ArchitectEventStructure.Config> structure,
            final ArchitectEventStructure.Config config) {
        super((Structure) structure, (IFeatureConfig) config);
    }

    public StructureStart<?> generate(final JigsawGenerator jigsaw, final DynamicRegistries registry,
            final ChunkGenerator gen, final TemplateManager manager, final int references, final long worldSeed) {
        final ArchitectEventStructure.Start start = (ArchitectEventStructure.Start) this.feature.getStartFactory()
                .create(this.feature, jigsaw.getStartPos().getX() >> 4,
                        jigsaw.getStartPos().getZ() >> 4, MutableBoundingBox.getUnknownBox(), references,
                        worldSeed);
        start.generate(jigsaw, registry, gen, manager);
        if (start.isValid()) {
            return (StructureStart<?>) start;
        }
        return (StructureStart<?>) StructureStart.INVALID_START;
    }
}
