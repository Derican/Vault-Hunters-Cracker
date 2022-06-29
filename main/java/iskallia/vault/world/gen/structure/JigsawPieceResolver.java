// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.world.gen.structure;

import com.mojang.datafixers.util.Pair;
import java.util.Optional;
import net.minecraft.util.Direction;
import iskallia.vault.world.vault.gen.piece.VaultPiece;
import net.minecraft.world.gen.feature.jigsaw.EmptyJigsawPiece;
import iskallia.vault.util.data.WeightedList;
import net.minecraft.util.math.vector.Vector3i;
import iskallia.vault.Vault;
import java.util.Objects;
import net.minecraft.world.gen.feature.jigsaw.JigsawPatternRegistry;
import net.minecraft.block.JigsawBlock;
import net.minecraft.world.gen.feature.template.Template;
import java.util.Iterator;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.shapes.VoxelShape;
import org.apache.commons.lang3.mutable.MutableObject;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.template.TemplateManager;
import java.util.ArrayList;
import net.minecraft.util.math.AxisAlignedBB;
import java.util.List;
import net.minecraft.util.ResourceLocation;
import java.util.function.Predicate;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import java.util.Random;

public class JigsawPieceResolver
{
    private static final Object templateLoadLock;
    private static final Random rand;
    private final JigsawPiece piece;
    private final BlockPos pos;
    private Rotation pieceRotation;
    private Predicate<ResourceLocation> filter;
    private final List<AxisAlignedBB> additionalStructureBoxes;
    
    private JigsawPieceResolver(final JigsawPiece piece, final BlockPos pos) {
        this.pieceRotation = Rotation.NONE;
        this.filter = (key -> true);
        this.additionalStructureBoxes = new ArrayList<AxisAlignedBB>();
        this.piece = piece;
        this.pos = pos;
    }
    
    public static JigsawPieceResolver newResolver(final JigsawPiece piece, final BlockPos pos) {
        return new JigsawPieceResolver(piece, pos);
    }
    
    public JigsawPieceResolver withRotation(final Rotation rotation) {
        this.pieceRotation = rotation;
        return this;
    }
    
    public JigsawPieceResolver andJigsawFilter(final Predicate<ResourceLocation> filter) {
        this.filter = filter.and(filter);
        return this;
    }
    
    public JigsawPieceResolver addStructureBox(final AxisAlignedBB boundingBox) {
        this.additionalStructureBoxes.add(boundingBox);
        return this;
    }
    
    public List<AbstractVillagePiece> resolveJigsawPieces(final TemplateManager templateManager, final Registry<JigsawPattern> jigsawPatternRegistry) {
        final AbstractVillagePiece beginningPiece = new AbstractVillagePiece(templateManager, this.piece, this.pos, this.piece.getGroundLevelDelta(), this.pieceRotation, this.piece.getBoundingBox(templateManager, this.pos, this.pieceRotation));
        final MutableBoundingBox pieceBox = beginningPiece.getBoundingBox();
        final int centerY = this.pos.getY();
        final int offset = pieceBox.y0 + this.piece.getGroundLevelDelta();
        beginningPiece.move(0, centerY - offset, 0);
        VoxelShape generationShape = VoxelShapes.create(AxisAlignedBB.of(pieceBox).inflate(15.0));
        for (final AxisAlignedBB additionalBoxes : this.additionalStructureBoxes) {
            generationShape = VoxelShapes.join(generationShape, VoxelShapes.create(additionalBoxes), IBooleanFunction.ONLY_FIRST);
        }
        final MutableObject<VoxelShape> generationBoxRef = (MutableObject<VoxelShape>)new MutableObject((Object)generationShape);
        final List<AbstractVillagePiece> resolvedPieces = new ArrayList<AbstractVillagePiece>();
        resolvedPieces.add(beginningPiece);
        final List<Entry> generationEntries = new ArrayList<Entry>();
        generationEntries.add(new Entry(beginningPiece, (MutableObject)generationBoxRef));
        while (!generationEntries.isEmpty()) {
            final Entry generationEntry = generationEntries.remove(0);
            this.calculatePieces(resolvedPieces, generationEntries, generationEntry.villagePiece, generationEntry.generationBox, templateManager, jigsawPatternRegistry);
        }
        return resolvedPieces;
    }
    
    private void calculatePieces(final List<AbstractVillagePiece> resolvedPieces, final List<Entry> generationEntries, final AbstractVillagePiece piece, final MutableObject<VoxelShape> generationBox, final TemplateManager templateMgr, final Registry<JigsawPattern> jigsawPatternRegistry) {
        final JigsawPiece jigsawpiece = piece.getElement();
        final BlockPos pos = piece.getPosition();
        final Rotation rotation = piece.getRotation();
        final MutableBoundingBox pieceBox = piece.getBoundingBox();
        final MutableObject<VoxelShape> thisPieceGenerationBox = (MutableObject<VoxelShape>)new MutableObject();
        final int minY = pieceBox.y0;
        final List<Template.BlockInfo> thisPieceBlocks;
        synchronized (JigsawPieceResolver.templateLoadLock) {
            thisPieceBlocks = jigsawpiece.getShuffledJigsawBlocks(templateMgr, pos, rotation, JigsawPieceResolver.rand);
        }
    Label_0086:
        while (true) {
            for (final Template.BlockInfo blockInfo : thisPieceBlocks) {
                final Direction connectingDirection = JigsawBlock.getFrontFacing(blockInfo.state);
                final BlockPos jigsawConnectorPos = blockInfo.pos;
                final BlockPos expectedConnectionPos = jigsawConnectorPos.relative(connectingDirection);
                final int jigsawYPos = jigsawConnectorPos.getY() - minY;
                final ResourceLocation connectorPool = new ResourceLocation(blockInfo.nbt.getString("pool"));
                final Optional<JigsawPattern> mainJigsawPattern = jigsawPatternRegistry.getOptional(connectorPool);
                if (!mainJigsawPattern.isPresent() || (mainJigsawPattern.get().size() == 0 && !Objects.equals(connectorPool, JigsawPatternRegistry.EMPTY.location()))) {
                    Vault.LOGGER.warn("Empty or none existent pool: {}", (Object)connectorPool);
                }
                else {
                    final ResourceLocation fallbackConnectorPool = mainJigsawPattern.get().getFallback();
                    final Optional<JigsawPattern> fallbackJigsawPattern = jigsawPatternRegistry.getOptional(fallbackConnectorPool);
                    if (!fallbackJigsawPattern.isPresent() || (fallbackJigsawPattern.get().size() == 0 && !Objects.equals(fallbackConnectorPool, JigsawPatternRegistry.EMPTY.location()))) {
                        Vault.LOGGER.warn("Empty or none existent fallback pool: {}", (Object)fallbackConnectorPool);
                    }
                    else {
                        MutableObject<VoxelShape> nextGenerationBox;
                        if (pieceBox.isInside((Vector3i)expectedConnectionPos)) {
                            nextGenerationBox = thisPieceGenerationBox;
                            if (thisPieceGenerationBox.getValue() == null) {
                                thisPieceGenerationBox.setValue((Object)VoxelShapes.create(AxisAlignedBB.of(pieceBox)));
                            }
                        }
                        else {
                            nextGenerationBox = generationBox;
                        }
                        final WeightedList<JigsawPiece> weightedPieces = new WeightedList<JigsawPiece>();
                        if (!connectorPool.equals((Object)new ResourceLocation("empty")) && this.filter.test(connectorPool)) {
                            mainJigsawPattern.get().rawTemplates.forEach(weightedPiece -> weightedPieces.add(weightedPiece.getFirst(), (int)weightedPiece.getSecond()));
                        }
                        if (!fallbackConnectorPool.equals((Object)new ResourceLocation("empty")) && this.filter.test(fallbackConnectorPool)) {
                            fallbackJigsawPattern.get().rawTemplates.forEach(weightedPiece -> weightedPieces.add(weightedPiece.getFirst(), (int)weightedPiece.getSecond()));
                        }
                        while (!weightedPieces.isEmpty()) {
                            final JigsawPiece nextPiece = weightedPieces.removeRandom(JigsawPieceResolver.rand);
                            if (nextPiece == null) {
                                break;
                            }
                            if (nextPiece == EmptyJigsawPiece.INSTANCE) {
                                break;
                            }
                            for (final Rotation nextPieceRotation : Rotation.getShuffled(JigsawPieceResolver.rand)) {
                                final List<Template.BlockInfo> nextPieceBlocks;
                                synchronized (JigsawPieceResolver.templateLoadLock) {
                                    nextPieceBlocks = nextPiece.getShuffledJigsawBlocks(templateMgr, BlockPos.ZERO, nextPieceRotation, JigsawPieceResolver.rand);
                                }
                                for (final Template.BlockInfo nextPieceBlockInfo : nextPieceBlocks) {
                                    if (!JigsawBlock.canAttach(blockInfo, nextPieceBlockInfo)) {
                                        continue;
                                    }
                                    BlockPos nextPiecePos = nextPieceBlockInfo.pos;
                                    if (connectorPool.equals((Object)Vault.id("final_vault/tenos/obelisk"))) {
                                        nextPiecePos = nextPiecePos.above();
                                    }
                                    final BlockPos pieceDiff = new BlockPos(expectedConnectionPos.getX() - nextPiecePos.getX(), expectedConnectionPos.getY() - nextPiecePos.getY(), expectedConnectionPos.getZ() - nextPiecePos.getZ());
                                    final MutableBoundingBox nextPieceBox = nextPiece.getBoundingBox(templateMgr, pieceDiff, nextPieceRotation);
                                    final boolean isNextPieceRigid = nextPiece.getProjection() == JigsawPattern.PlacementBehaviour.RIGID;
                                    final int nextY = nextPiecePos.getY();
                                    final int l1 = jigsawYPos - nextY + JigsawBlock.getFrontFacing(nextPieceBlockInfo.state).getStepY();
                                    if (VaultPiece.shouldIgnoreCollision(nextPiece) || !VoxelShapes.joinIsNotEmpty((VoxelShape)nextGenerationBox.getValue(), VoxelShapes.create(AxisAlignedBB.of(nextPieceBox).deflate(0.25)), IBooleanFunction.ONLY_SECOND)) {
                                        nextGenerationBox.setValue((Object)VoxelShapes.joinUnoptimized((VoxelShape)nextGenerationBox.getValue(), VoxelShapes.create(AxisAlignedBB.of(nextPieceBox)), IBooleanFunction.ONLY_FIRST));
                                        int l2;
                                        if (isNextPieceRigid) {
                                            l2 = piece.getGroundLevelDelta() - l1;
                                        }
                                        else {
                                            l2 = nextPiece.getGroundLevelDelta();
                                        }
                                        final AbstractVillagePiece nextPieceVillagePiece = new AbstractVillagePiece(templateMgr, nextPiece, pieceDiff, l2, nextPieceRotation, nextPieceBox);
                                        resolvedPieces.add(nextPieceVillagePiece);
                                        generationEntries.add(new Entry(nextPieceVillagePiece, (MutableObject)nextGenerationBox));
                                        continue Label_0086;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            break;
        }
    }
    
    static {
        templateLoadLock = new Object();
        rand = new Random();
    }
    
    static final class Entry
    {
        private final AbstractVillagePiece villagePiece;
        private final MutableObject<VoxelShape> generationBox;
        
        private Entry(final AbstractVillagePiece piece, final MutableObject<VoxelShape> generationBox) {
            this.villagePiece = piece;
            this.generationBox = generationBox;
        }
    }
}
