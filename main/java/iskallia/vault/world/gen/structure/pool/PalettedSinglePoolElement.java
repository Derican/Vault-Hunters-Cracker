// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.world.gen.structure.pool;

import com.mojang.datafixers.kinds.Applicative;
import com.mojang.datafixers.kinds.App;
import iskallia.vault.init.ModStructures;
import net.minecraft.world.gen.feature.jigsaw.IJigsawDeserializer;
import java.util.function.Consumer;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.JigsawReplacementStructureProcessor;
import net.minecraft.world.IWorld;
import net.minecraft.world.IServerWorld;
import javax.annotation.Nullable;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.ISeedReader;
import net.minecraft.util.math.MutableBoundingBox;
import java.util.Collections;
import java.util.Random;
import java.util.Iterator;
import net.minecraft.state.properties.StructureMode;
import com.google.common.collect.Lists;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import java.util.List;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import java.util.function.Function;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.gen.feature.template.ProcessorLists;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Optional;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import net.minecraft.world.gen.feature.template.StructureProcessorList;
import java.util.function.Supplier;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.util.ResourceLocation;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;

public class PalettedSinglePoolElement extends JigsawPiece
{
    private static final Codec<Either<ResourceLocation, Template>> TEMPLATE_CODEC;
    public static final Codec<PalettedSinglePoolElement> CODEC;
    protected final Either<ResourceLocation, Template> template;
    protected final Supplier<StructureProcessorList> processors;
    
    private static <T> DataResult<T> encodeTemplate(final Either<ResourceLocation, Template> p_236840_0_, final DynamicOps<T> p_236840_1_, final T p_236840_2_) {
        final Optional<ResourceLocation> optional = p_236840_0_.left();
        return (DataResult<T>)(optional.isPresent() ? ResourceLocation.CODEC.encode((Object)optional.get(), (DynamicOps)p_236840_1_, (Object)p_236840_2_) : DataResult.error("Can not serialize a runtime pool element"));
    }
    
    protected static <E extends PalettedSinglePoolElement> RecordCodecBuilder<E, Supplier<StructureProcessorList>> processorsCodec() {
        return (RecordCodecBuilder<E, Supplier<StructureProcessorList>>)IStructureProcessorType.LIST_CODEC.fieldOf("processors").forGetter(p_236845_0_ -> p_236845_0_.processors);
    }
    
    protected static <E extends PalettedSinglePoolElement> RecordCodecBuilder<E, Either<ResourceLocation, Template>> templateCodec() {
        return (RecordCodecBuilder<E, Either<ResourceLocation, Template>>)PalettedSinglePoolElement.TEMPLATE_CODEC.fieldOf("location").forGetter(p_236842_0_ -> p_236842_0_.template);
    }
    
    protected PalettedSinglePoolElement(final Either<ResourceLocation, Template> p_i242008_1_, final Supplier<StructureProcessorList> p_i242008_2_, final JigsawPattern.PlacementBehaviour p_i242008_3_) {
        super(p_i242008_3_);
        this.template = p_i242008_1_;
        this.processors = p_i242008_2_;
    }
    
    public PalettedSinglePoolElement(final Template p_i242009_1_) {
        this((Either<ResourceLocation, Template>)Either.right((Object)p_i242009_1_), () -> ProcessorLists.EMPTY, JigsawPattern.PlacementBehaviour.RIGID);
    }
    
    public Either<ResourceLocation, Template> getTemplate() {
        return this.template;
    }
    
    public Template getTemplate(final TemplateManager manager) {
        return (Template)this.template.map((Function)manager::getOrCreate, (Function)Function.identity());
    }
    
    public List<Template.BlockInfo> getDataMarkers(final TemplateManager p_214857_1_, final BlockPos p_214857_2_, final Rotation p_214857_3_, final boolean p_214857_4_) {
        final Template template = this.getTemplate(p_214857_1_);
        final List<Template.BlockInfo> list = template.filterBlocks(p_214857_2_, new PlacementSettings().setRotation(p_214857_3_), Blocks.STRUCTURE_BLOCK, p_214857_4_);
        final List<Template.BlockInfo> list2 = Lists.newArrayList();
        for (final Template.BlockInfo template$blockinfo : list) {
            if (template$blockinfo.nbt != null) {
                final StructureMode structuremode = StructureMode.valueOf(template$blockinfo.nbt.getString("mode"));
                if (structuremode != StructureMode.DATA) {
                    continue;
                }
                list2.add(template$blockinfo);
            }
        }
        return list2;
    }
    
    public List<Template.BlockInfo> getShuffledJigsawBlocks(final TemplateManager templateManager, final BlockPos pos, final Rotation rotation, final Random random) {
        final Template template = this.getTemplate(templateManager);
        final List<Template.BlockInfo> list = template.filterBlocks(pos, new PlacementSettings().setRotation(rotation), Blocks.JIGSAW, true);
        Collections.shuffle(list, random);
        return list;
    }
    
    public MutableBoundingBox getBoundingBox(final TemplateManager templateManager, final BlockPos pos, final Rotation rotation) {
        final Template template = this.getTemplate(templateManager);
        return template.getBoundingBox(new PlacementSettings().setRotation(rotation), pos);
    }
    
    public boolean place(final TemplateManager templateManager, final ISeedReader world, final StructureManager structureManager, final ChunkGenerator chunkGen, final BlockPos pos1, final BlockPos pos2, final Rotation rotation, final MutableBoundingBox box, final Random random, final boolean keepJigsaws) {
        return this.generate(null, templateManager, world, structureManager, chunkGen, pos1, pos2, rotation, box, random, keepJigsaws, 18);
    }
    
    public boolean generate(@Nullable final Supplier<StructureProcessorList> extra, final TemplateManager templateManager, final ISeedReader world, final StructureManager structureManager, final ChunkGenerator chunkGen, final BlockPos pos1, final BlockPos pos2, final Rotation rotation, final MutableBoundingBox box, final Random random, final boolean keepJigsaws, final int updateFlags) {
        final Template template = this.getTemplate(templateManager);
        final PlacementSettings placementsettings = this.getSettings(extra, rotation, box, keepJigsaws);
        if (!template.placeInWorld((IServerWorld)world, pos1, pos2, placementsettings, random, updateFlags)) {
            return false;
        }
        for (final Template.BlockInfo info : Template.processBlockInfos((IWorld)world, pos1, pos2, placementsettings, (List)this.getDataMarkers(templateManager, pos1, rotation, false), template)) {
            this.handleDataMarker((IWorld)world, info, pos1, rotation, random, box);
        }
        return true;
    }
    
    protected PlacementSettings getSettings(@Nullable final Supplier<StructureProcessorList> extra, final Rotation p_230379_1_, final MutableBoundingBox p_230379_2_, final boolean p_230379_3_) {
        final PlacementSettings placementsettings = new PlacementSettings();
        placementsettings.setBoundingBox(p_230379_2_);
        placementsettings.setRotation(p_230379_1_);
        placementsettings.setKnownShape(true);
        placementsettings.setIgnoreEntities(false);
        placementsettings.setFinalizeEntities(true);
        if (!p_230379_3_) {
            placementsettings.addProcessor((StructureProcessor)JigsawReplacementStructureProcessor.INSTANCE);
        }
        this.processors.get().list().forEach(placementsettings::addProcessor);
        if (extra != null) {
            extra.get().list().forEach(placementsettings::addProcessor);
        }
        this.getProjection().getProcessors().forEach((Consumer)placementsettings::addProcessor);
        return placementsettings;
    }
    
    public IJigsawDeserializer<?> getType() {
        return ModStructures.PoolElements.PALETTED_SINGLE_POOL_ELEMENT;
    }
    
    public String toString() {
        return "PalettedSingle[" + this.template + "]";
    }
    
    static {
        TEMPLATE_CODEC = Codec.of(PalettedSinglePoolElement::encodeTemplate, ResourceLocation.CODEC.map((Function)Either::left));
        CODEC = RecordCodecBuilder.create(p_236841_0_ -> p_236841_0_.group((App)templateCodec(), (App)processorsCodec(), (App)projectionCodec()).apply((Applicative)p_236841_0_, PalettedSinglePoolElement::new));
    }
}
