// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.world.vault.logic.objective.architect;

import net.minecraft.entity.player.ServerPlayerEntity;
import iskallia.vault.util.nbt.NBTHelper;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.INBT;
import iskallia.vault.world.vault.gen.VaultGenerator;
import java.util.function.Supplier;
import iskallia.vault.config.LootTablesConfig;
import net.minecraft.loot.LootTable;
import javax.annotation.Nonnull;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.entity.item.ItemEntity;
import iskallia.vault.block.VaultCrateBlock;
import iskallia.vault.init.ModBlocks;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.IItemProvider;
import iskallia.vault.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.LootContext;
import iskallia.vault.world.vault.player.VaultRunner;
import iskallia.vault.world.vault.player.VaultPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import java.util.function.Function;
import java.util.Optional;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraft.entity.LivingEntity;
import iskallia.vault.world.vault.logic.VaultBossSpawner;
import iskallia.vault.world.gen.decorator.BreadcrumbFeature;
import iskallia.vault.world.vault.gen.piece.VaultPiece;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.STitlePacket;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import iskallia.vault.world.vault.logic.objective.architect.processor.VaultPieceProcessor;
import java.util.function.Predicate;
import java.util.Objects;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import iskallia.vault.world.vault.logic.objective.architect.modifier.RandomVoteModifier;
import net.minecraftforge.fml.network.NetworkDirection;
import iskallia.vault.init.ModNetwork;
import iskallia.vault.network.message.VaultGoalMessage;
import iskallia.vault.util.PlayerFilter;
import net.minecraft.server.MinecraftServer;
import javax.annotation.Nullable;
import net.minecraft.util.text.IFormattableTextComponent;
import iskallia.vault.world.vault.logic.objective.architect.modifier.VoteModifier;
import java.util.Iterator;
import iskallia.vault.world.vault.VaultRaid;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import java.util.Collection;
import iskallia.vault.util.MiscUtils;
import iskallia.vault.world.gen.structure.VaultJigsawHelper;
import net.minecraft.util.Direction;
import com.google.common.collect.Iterables;
import iskallia.vault.world.vault.gen.piece.VaultRoom;
import iskallia.vault.world.data.VaultRaidData;
import net.minecraft.world.server.ServerWorld;
import iskallia.vault.init.ModConfigs;
import com.mojang.serialization.Codec;
import java.util.ArrayList;
import iskallia.vault.world.vault.logic.task.VaultTask;
import net.minecraft.util.ResourceLocation;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import iskallia.vault.nbt.VListNBT;
import java.util.UUID;
import java.util.List;
import net.minecraftforge.fml.common.Mod;
import iskallia.vault.world.vault.logic.objective.VaultObjective;

@Mod.EventBusSubscriber(modid = "the_vault", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ArchitectObjective extends VaultObjective
{
    protected final List<VotingSession> completedSessions;
    protected VotingSession activeSession;
    private boolean votingLocked;
    protected int totalRequiredVotes;
    protected int voteDowntimeTicks;
    protected int ticksUntilNextVote;
    private UUID bossId;
    private boolean isBossDead;
    private final VListNBT<BlockPos, CompoundNBT> exitPortalLocations;
    private boolean collidedWithExitPortal;
    
    public ArchitectObjective(final ResourceLocation id) {
        super(id, VaultTask.EMPTY, VaultTask.EMPTY);
        this.completedSessions = new ArrayList<VotingSession>();
        this.activeSession = null;
        this.votingLocked = false;
        this.voteDowntimeTicks = 400;
        this.ticksUntilNextVote = 0;
        this.bossId = null;
        this.isBossDead = false;
        this.exitPortalLocations = VListNBT.ofCodec((com.mojang.serialization.Codec<BlockPos>)BlockPos.CODEC, BlockPos.ZERO);
        this.collidedWithExitPortal = false;
        this.totalRequiredVotes = ModConfigs.ARCHITECT_EVENT.getRandomTotalRequiredPolls();
    }
    
    public boolean createVotingSession(final ServerWorld world, final BlockPos origin) {
        if (this.activeSession != null || this.ticksUntilNextVote > 0 || this.isVotingLocked()) {
            return false;
        }
        final VaultRaid thisRaid = VaultRaidData.get(world).getAt(world, origin);
        if (thisRaid == null) {
            return false;
        }
        final VaultRoom room = (VaultRoom)Iterables.getFirst((Iterable)thisRaid.getGenerator().getPiecesAt(origin, VaultRoom.class), (Object)null);
        if (room == null) {
            return false;
        }
        final List<Direction> availableDirections = new ArrayList<Direction>();
        for (final Direction dir : Direction.values()) {
            if (dir.getAxis().isHorizontal() && VaultJigsawHelper.canExpand(thisRaid, room, dir)) {
                availableDirections.add(dir);
            }
        }
        if (availableDirections.size() <= 1) {
            return false;
        }
        Direction bossDir = null;
        if (this.completedSessions.size() >= this.totalRequiredVotes) {
            bossDir = MiscUtils.getRandomEntry(availableDirections, ArchitectObjective.rand);
        }
        final List<DirectionChoice> choices = new ArrayList<DirectionChoice>();
        for (final Direction dir : availableDirections) {
            final DirectionChoice choice = new DirectionChoice(dir);
            if (dir == bossDir) {
                choice.addModifier(ModConfigs.ARCHITECT_EVENT.getBossModifier());
            }
            else {
                final VoteModifier randomModifier = ModConfigs.ARCHITECT_EVENT.generateRandomModifier();
                if (randomModifier != null) {
                    choice.addModifier(randomModifier);
                }
            }
            choices.add(choice);
        }
        this.activeSession = new VotingSession(origin, choices);
        if (this.completedSessions.isEmpty()) {
            final IFormattableTextComponent display = new StringTextComponent("").append("Vote with ");
            final List<DirectionChoice> directions = this.activeSession.getDirections();
            for (int i = 0; i < directions.size(); ++i) {
                if (i != 0) {
                    display.append(", ");
                }
                final DirectionChoice choice2 = directions.get(i);
                display.append(choice2.getDirectionDisplay("/"));
            }
            display.append("!");
            thisRaid.getPlayers().forEach(vPlayer -> vPlayer.runIfPresent(world.getServer(), sPlayer -> sPlayer.sendMessage((ITextComponent)display, Util.NIL_UUID)));
        }
        return true;
    }
    
    @Nullable
    public VotingSession getActiveSession() {
        return this.activeSession;
    }
    
    public boolean handleVote(final String sender, final Direction dir) {
        return this.activeSession != null && this.activeSession.acceptVote(sender, dir);
    }
    
    @Override
    public boolean shouldPauseTimer(final MinecraftServer srv, final VaultRaid vault) {
        return super.shouldPauseTimer(srv, vault) || (this.activeSession == null && this.completedSessions.isEmpty());
    }
    
    @Override
    public void tick(final VaultRaid vault, final PlayerFilter filter, final ServerWorld world) {
        super.tick(vault, filter, world);
        final MinecraftServer srv = world.getServer();
        vault.getPlayers().stream().filter(vPlayer -> filter.test(vPlayer.getPlayerId())).forEach(vPlayer -> vPlayer.runIfPresent(srv, playerEntity -> {
            final VaultGoalMessage pkt = VaultGoalMessage.architectEvent(this.getCompletedPercent(), this.ticksUntilNextVote, this.voteDowntimeTicks, this.activeSession);
            ModNetwork.CHANNEL.sendTo((Object)pkt, playerEntity.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
        }));
        if (this.isCompleted()) {
            return;
        }
        if (this.ticksUntilNextVote > 0) {
            --this.ticksUntilNextVote;
        }
        if (this.activeSession != null) {
            this.activeSession.tick(world);
            if (this.activeSession.isFinished()) {
                this.finishVote(vault, this.activeSession, world);
                this.completedSessions.add(this.activeSession);
                this.activeSession = null;
                if (!this.isVotingLocked()) {
                    this.ticksUntilNextVote = this.voteDowntimeTicks;
                }
            }
        }
        if (!this.exitPortalLocations.isEmpty()) {
            vault.getPlayers().stream().filter(vPlayer -> filter.test(vPlayer.getPlayerId())).forEach(vPlayer -> vPlayer.runIfPresent(srv, sPlayer -> {
                final BlockPos pos = sPlayer.blockPosition();
                if (this.exitPortalLocations.contains(pos)) {
                    this.collidedWithExitPortal = true;
                    this.spawnBossLoot(vault, pos, vPlayer, world, false);
                }
            }));
        }
        if (this.bossId != null && this.isBossDead) {
            this.setCompleted();
        }
        if (!this.exitPortalLocations.isEmpty() && this.collidedWithExitPortal) {
            this.setCompleted();
        }
    }
    
    protected void finishVote(final VaultRaid vault, final VotingSession session, final ServerWorld world) {
        vault.getGenerator().getPiecesAt(session.getStabilizerPos(), VaultRoom.class).stream().findFirst().ifPresent(room -> {
            final DirectionChoice choice = session.getVotedDirection();
            final ArrayList<Object> modifiers = new ArrayList<Object>();
            choice.getModifiers().forEach(modifier -> {
                if (modifier instanceof RandomVoteModifier) {
                    modifiers.add(((RandomVoteModifier)modifier).rollModifier());
                }
                else {
                    modifiers.add(modifier);
                }
                return;
            });
            final JigsawPiece roomPiece = modifiers.stream().map(modifier -> modifier.getSpecialRoom(this, vault)).filter((Predicate<? super JigsawPiece>)Objects::nonNull).findFirst().orElse(null);
            modifiers.forEach(modifier -> modifier.onApply(this, vault, world));
            final List<VaultPiece> generatedPieces = this.expandVault(vault, world, room, session, choice.getDirection(), roomPiece, null);
            final List<VaultPieceProcessor> postProcessors = modifiers.stream().map(modifier -> modifier.getPostProcessor(this, vault)).filter(Objects::nonNull).collect((Collector<? super Object, ?, List<VaultPieceProcessor>>)Collectors.toList());
            generatedPieces.forEach(piece -> postProcessors.forEach(processor -> processor.postProcess(vault, world, piece, choice.getDirection())));
            choice.getModifiers().forEach(modifier -> this.voteDowntimeTicks += modifier.getVoteLockDurationChangeSeconds() * 20);
            this.voteDowntimeTicks = Math.max(0, this.voteDowntimeTicks);
            final STitlePacket titlePacket = new STitlePacket(STitlePacket.Type.TITLE, choice.getDirectionDisplay());
            final VoteModifier displayModifier = (VoteModifier)Iterables.getFirst((Iterable)modifiers, (Object)null);
            STitlePacket subtitlePacket;
            if (displayModifier != null) {
                subtitlePacket = new STitlePacket(STitlePacket.Type.SUBTITLE, displayModifier.getDescription());
            }
            else {
                subtitlePacket = null;
            }
            vault.getPlayers().forEach(vPlayer -> vPlayer.runIfPresent(world.getServer(), sPlayer -> {
                sPlayer.connection.send((IPacket)titlePacket);
                if (subtitlePacket != null) {
                    sPlayer.connection.send((IPacket)subtitlePacket);
                }
            }));
        });
    }
    
    protected List<VaultPiece> expandVault(final VaultRaid vault, final ServerWorld world, final VaultRoom room, final VotingSession session, final Direction direction, @Nullable final JigsawPiece roomToGenerate, @Nullable final JigsawPiece tunnelToGenerate) {
        final List<VaultPiece> generatedPieces = VaultJigsawHelper.expandVault(vault, world, room, direction, roomToGenerate, tunnelToGenerate);
        BreadcrumbFeature.generateVaultBreadcrumb(vault, world, generatedPieces);
        return generatedPieces;
    }
    
    public void buildPortal(final List<BlockPos> portalLocations) {
        this.exitPortalLocations.addAll(portalLocations);
    }
    
    public void spawnBoss(final VaultRaid vault, final ServerWorld world, final BlockPos pos) {
        final LivingEntity boss = VaultBossSpawner.spawnBoss(vault, world, pos);
        this.bossId = boss.getUUID();
    }
    
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onBossDeath(final LivingDeathEvent event) {
        final LivingEntity died = event.getEntityLiving();
        if (died.getCommandSenderWorld().isClientSide() || !(died.getCommandSenderWorld() instanceof ServerWorld)) {
            return;
        }
        final ServerWorld world = (ServerWorld)died.getCommandSenderWorld();
        final VaultRaid vault = VaultRaidData.get(world).getAt(world, died.blockPosition());
        if (vault == null) {
            return;
        }
        final List<ArchitectObjective> matchingObjectives = vault.getPlayers().stream().map(player -> player.getActiveObjective(ArchitectObjective.class)).filter(Optional::isPresent).map((Function<? super Object, ?>)Optional::get).filter(objective -> !objective.isCompleted()).filter(objective -> died.getUUID().equals(objective.bossId)).collect((Collector<? super Object, ?, List<ArchitectObjective>>)Collectors.toList());
        if (matchingObjectives.isEmpty()) {
            vault.getActiveObjective(ArchitectObjective.class).ifPresent(objective -> {
                if (objective.onBossKill(died)) {
                    objective.dropBossCrate(died, event.getSource(), world, vault);
                }
            });
        }
        else {
            matchingObjectives.forEach(objective -> objective.onBossKill(died));
        }
    }
    
    private boolean onBossKill(final LivingEntity boss) {
        return !this.isBossDead && boss.getUUID().equals(this.bossId) && (this.isBossDead = true);
    }
    
    private void dropBossCrate(final LivingEntity boss, final DamageSource killSrc, final ServerWorld world, final VaultRaid vault) {
        final Optional<UUID> source = Optional.ofNullable(killSrc.getEntity()).map((Function<? super Entity, ? extends UUID>)Entity::getUUID);
        final Optional<VaultPlayer> killer = source.flatMap((Function<? super UUID, ? extends Optional<? extends VaultPlayer>>)vault::getPlayer);
        final Optional<VaultPlayer> host = vault.getProperties().getBase(VaultRaid.HOST).flatMap((Function<? super UUID, ? extends Optional<? extends VaultPlayer>>)vault::getPlayer);
        if (killer.isPresent()) {
            this.spawnBossLoot(vault, boss.blockPosition(), killer.get(), world, true);
        }
        else if (host.isPresent() && host.get() instanceof VaultRunner) {
            this.spawnBossLoot(vault, boss.blockPosition(), host.get(), world, true);
        }
        else {
            vault.getPlayers().stream().filter(player -> player instanceof VaultRunner).findFirst().ifPresent(player -> this.spawnBossLoot(vault, boss.blockPosition(), player, world, true));
        }
    }
    
    public void spawnBossLoot(final VaultRaid vault, final BlockPos bossPos, final VaultPlayer player, final ServerWorld world, final boolean isBossKill) {
        player.runIfPresent(world.getServer(), playerEntity -> {
            final LootContext.Builder builder = new LootContext.Builder(world).withRandom(world.random).withParameter(LootParameters.THIS_ENTITY, (Object)playerEntity).withParameter(LootParameters.ORIGIN, (Object)Vector3d.atCenterOf((Vector3i)bossPos)).withParameter(LootParameters.DAMAGE_SOURCE, (Object)DamageSource.playerAttack((PlayerEntity)playerEntity)).withOptionalParameter(LootParameters.KILLER_ENTITY, (Object)playerEntity).withOptionalParameter(LootParameters.DIRECT_KILLER_ENTITY, (Object)playerEntity).withParameter(LootParameters.LAST_DAMAGE_PLAYER, (Object)playerEntity).withLuck(playerEntity.getLuck());
            final LootContext ctx = builder.create(LootParameterSets.ENTITY);
            this.spawnRewardCrate(world, (Vector3i)bossPos, vault, ctx);
            for (int i = 1; i < vault.getPlayers().size(); ++i) {
                if (ArchitectObjective.rand.nextFloat() < 0.5f) {
                    this.spawnRewardCrate(world, (Vector3i)bossPos, vault, ctx);
                }
            }
            MiscUtils.broadcast(isBossKill ? this.getBossKillMessage((PlayerEntity)playerEntity) : this.getEscapeMessage((PlayerEntity)playerEntity));
            vault.getPlayers().forEach(anyVPlayer -> anyVPlayer.runIfPresent(world.getServer(), anySPlayer -> MiscUtils.broadcast(this.getCompletionMessage((PlayerEntity)anySPlayer))));
        });
    }
    
    @Override
    protected void addSpecialLoot(final ServerWorld world, final VaultRaid vault, final LootContext context, final NonNullList<ItemStack> stacks) {
        super.addSpecialLoot(world, vault, context, stacks);
        if (ModConfigs.ARCHITECT_EVENT.isEnabled()) {
            stacks.add((Object)new ItemStack((IItemProvider)ModItems.VAULT_GEAR));
        }
    }
    
    private ITextComponent getBossKillMessage(final PlayerEntity player) {
        final IFormattableTextComponent msgContainer = new StringTextComponent("").withStyle(TextFormatting.WHITE);
        final IFormattableTextComponent playerName = player.getDisplayName().copy();
        playerName.setStyle(Style.EMPTY.withColor(Color.fromRgb(9974168)));
        return (ITextComponent)msgContainer.append((ITextComponent)playerName).append(" defeated Boss!");
    }
    
    private ITextComponent getEscapeMessage(final PlayerEntity player) {
        final IFormattableTextComponent msgContainer = new StringTextComponent("").withStyle(TextFormatting.WHITE);
        final IFormattableTextComponent playerName = player.getDisplayName().copy();
        playerName.setStyle(Style.EMPTY.withColor(Color.fromRgb(9974168)));
        return (ITextComponent)msgContainer.append((ITextComponent)playerName).append(" successfully escaped from the Vault!");
    }
    
    private ITextComponent getCompletionMessage(final PlayerEntity player) {
        final IFormattableTextComponent msgContainer = new StringTextComponent("").withStyle(TextFormatting.WHITE);
        final IFormattableTextComponent playerName = player.getDisplayName().copy();
        playerName.setStyle(Style.EMPTY.withColor(Color.fromRgb(9974168)));
        return (ITextComponent)msgContainer.append((ITextComponent)playerName).append(" finished building a Vault!");
    }
    
    private void spawnRewardCrate(final ServerWorld world, final Vector3i pos, final VaultRaid vault, final LootContext context) {
        final NonNullList<ItemStack> stacks = this.createLoot(world, vault, context);
        final ItemStack crate = VaultCrateBlock.getCrateWithLoot(ModBlocks.VAULT_CRATE, stacks);
        final ItemEntity item = new ItemEntity((World)world, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), crate);
        item.setDefaultPickUpDelay();
        world.addFreshEntity((Entity)item);
        this.crates.add(new Crate((List<ItemStack>)stacks));
    }
    
    public int getTicksUntilNextVote() {
        return this.ticksUntilNextVote;
    }
    
    public float getCompletedPercent() {
        return MathHelper.clamp(this.completedSessions.size() / (float)this.totalRequiredVotes, 0.0f, 1.0f);
    }
    
    public void setVotingLocked() {
        this.votingLocked = true;
    }
    
    public boolean isVotingLocked() {
        return this.votingLocked;
    }
    
    @Override
    public void setObjectiveTargetCount(final int amount) {
        this.totalRequiredVotes = amount;
    }
    
    @Nullable
    @Override
    public ITextComponent getObjectiveTargetDescription(final int amount) {
        return (ITextComponent)new StringTextComponent("Required amount of votes: ").append((ITextComponent)new StringTextComponent(String.valueOf(amount)).withStyle(TextFormatting.AQUA));
    }
    
    @Nonnull
    @Override
    public BlockState getObjectiveRelevantBlock(final VaultRaid vault, final ServerWorld world, final BlockPos pos) {
        return ModBlocks.STABILIZER.defaultBlockState();
    }
    
    @Nullable
    @Override
    public LootTable getRewardLootTable(final VaultRaid vault, final Function<ResourceLocation, LootTable> tblResolver) {
        final int level = vault.getProperties().getBase(VaultRaid.LEVEL).orElse(0);
        final LootTablesConfig.Level config = ModConfigs.LOOT_TABLES.getForLevel(level);
        return (config != null) ? tblResolver.apply(config.getBossCrate()) : LootTable.EMPTY;
    }
    
    @Override
    public ITextComponent getObjectiveDisplayName() {
        return (ITextComponent)new StringTextComponent("Build a Vault").withStyle(TextFormatting.AQUA);
    }
    
    @Override
    public ITextComponent getVaultName() {
        return (ITextComponent)new StringTextComponent("Architect Vault");
    }
    
    @Nonnull
    @Override
    public Supplier<? extends VaultGenerator> getVaultGenerator() {
        return VaultRaid.ARCHITECT_GENERATOR;
    }
    
    @Override
    public CompoundNBT serializeNBT() {
        final CompoundNBT tag = super.serializeNBT();
        if (this.activeSession != null) {
            tag.put("activeSession", (INBT)this.activeSession.serialize());
        }
        final ListNBT sessions = new ListNBT();
        for (final VotingSession session : this.completedSessions) {
            sessions.add((Object)session.serialize());
        }
        tag.put("completedSessions", (INBT)sessions);
        tag.putInt("totalRequiredVotes", this.totalRequiredVotes);
        tag.putInt("voteDowntimeTicks", this.voteDowntimeTicks);
        tag.putInt("ticksUntilNextVote", this.ticksUntilNextVote);
        tag.putBoolean("votingLocked", this.votingLocked);
        NBTHelper.writeOptional(tag, "bossId", this.bossId, (nbt, uuid) -> nbt.putUUID("bossId", uuid));
        tag.putBoolean("isBossDead", this.isBossDead);
        tag.put("exitPortalLocations", (INBT)this.exitPortalLocations.serializeNBT());
        tag.putBoolean("collidedWithExitPortal", this.collidedWithExitPortal);
        return tag;
    }
    
    @Override
    public void deserializeNBT(final CompoundNBT tag) {
        super.deserializeNBT(tag);
        if (tag.contains("activeSession", 10)) {
            this.activeSession = VotingSession.deserialize(tag.getCompound("activeSession"));
        }
        else {
            this.activeSession = null;
        }
        this.completedSessions.clear();
        final ListNBT sessions = tag.getList("completedSessions", 10);
        for (int i = 0; i < sessions.size(); ++i) {
            this.completedSessions.add(VotingSession.deserialize(sessions.getCompound(i)));
        }
        this.totalRequiredVotes = tag.getInt("totalRequiredVotes");
        this.voteDowntimeTicks = tag.getInt("voteDowntimeTicks");
        this.ticksUntilNextVote = tag.getInt("ticksUntilNextVote");
        this.votingLocked = tag.getBoolean("votingLocked");
        this.bossId = NBTHelper.readOptional(tag, "bossId", nbt -> nbt.getUUID("bossId"));
        this.isBossDead = tag.getBoolean("isBossDead");
        this.exitPortalLocations.deserializeNBT(tag.getList("exitPortalLocations", 10));
        this.collidedWithExitPortal = tag.getBoolean("collidedWithExitPortal");
    }
}
