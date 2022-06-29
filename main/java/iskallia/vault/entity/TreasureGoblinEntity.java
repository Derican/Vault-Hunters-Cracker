// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.entity;

import iskallia.vault.world.vault.player.VaultPlayer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.List;
import net.minecraftforge.event.TickEvent;
import net.minecraft.util.SoundCategory;
import iskallia.vault.init.ModSounds;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootTable;
import net.minecraft.util.ResourceLocation;
import java.util.function.Consumer;
import net.minecraft.loot.LootParameterSets;
import iskallia.vault.init.ModConfigs;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import iskallia.vault.world.vault.VaultRaid;
import iskallia.vault.world.data.VaultRaidData;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.entity.Entity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.World;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.entity.monster.MonsterEntity;

@Mod.EventBusSubscriber
public class TreasureGoblinEntity extends MonsterEntity
{
    protected int disappearTick;
    protected boolean shouldDisappear;
    protected PlayerEntity lastAttackedPlayer;
    
    public TreasureGoblinEntity(final EntityType<? extends MonsterEntity> type, final World worldIn) {
        super((EntityType)type, worldIn);
    }
    
    protected void registerGoals() {
        this.goalSelector.addGoal(3, (Goal)new AvoidEntityGoal((CreatureEntity)this, (Class)PlayerEntity.class, 6.0f, 1.7000000476837158, 2.0));
        this.goalSelector.addGoal(5, (Goal)new LookAtGoal((MobEntity)this, (Class)PlayerEntity.class, 20.0f));
        this.goalSelector.addGoal(5, (Goal)new LookRandomlyGoal((MobEntity)this));
    }
    
    public boolean isHitByPlayer() {
        return this.lastAttackedPlayer != null;
    }
    
    protected int calcDisappearTicks(final PlayerEntity player) {
        return 200;
    }
    
    public boolean hurt(final DamageSource source, final float amount) {
        final Entity entity = source.getEntity();
        if (entity instanceof PlayerEntity && !entity.level.isClientSide) {
            final PlayerEntity player = (PlayerEntity)entity;
            if (!this.isHitByPlayer()) {
                this.lastAttackedPlayer = player;
                this.disappearTick = this.calcDisappearTicks(player);
                this.addEffect(new EffectInstance(Effects.GLOWING, this.disappearTick));
            }
        }
        return super.hurt(source, amount);
    }
    
    protected void dropFromLootTable(final DamageSource source, final boolean attackedRecently) {
        final ServerWorld world = (ServerWorld)this.level;
        final VaultRaid vault = VaultRaidData.get(world).getAt(world, this.blockPosition());
        if (vault != null) {
            vault.getProperties().getBase(VaultRaid.HOST).flatMap((Function<? super UUID, ? extends Optional<?>>)vault::getPlayer).ifPresent(player -> {
                final int level = player.getProperties().getBase(VaultRaid.LEVEL).orElse(0);
                final ResourceLocation id = ModConfigs.LOOT_TABLES.getForLevel(level).getTreasureGoblin();
                final LootTable loot = this.level.getServer().getLootTables().get(id);
                final LootContext.Builder builder = this.createLootContext(attackedRecently, source);
                final LootContext ctx = builder.create(LootParameterSets.ENTITY);
                loot.getRandomItems(ctx).forEach(this::spawnAtLocation);
                return;
            });
        }
        super.dropFromLootTable(source, attackedRecently);
    }
    
    public void tick() {
        super.tick();
        if (this.isAlive() && this.isHitByPlayer()) {
            if (this.disappearTick <= 0) {
                this.shouldDisappear = true;
            }
            --this.disappearTick;
        }
    }
    
    public void disappear(final ServerWorld world) {
        world.despawn((Entity)this);
        if (this.lastAttackedPlayer != null) {
            final StringTextComponent bailText = (StringTextComponent)new StringTextComponent("Treasure Goblin escaped from you.").withStyle(Style.EMPTY.withColor(Color.fromRgb(8042883)));
            this.lastAttackedPlayer.displayClientMessage((ITextComponent)bailText, true);
            this.lastAttackedPlayer.playNotifySound(ModSounds.GOBLIN_BAIL, SoundCategory.MASTER, 0.7f, 1.0f);
            world.playSound(this.lastAttackedPlayer, this.getX(), this.getY(), this.getZ(), ModSounds.GOBLIN_BAIL, SoundCategory.MASTER, 0.7f, 1.0f);
        }
        else {
            world.playSound((PlayerEntity)null, this.getX(), this.getY(), this.getZ(), ModSounds.GOBLIN_BAIL, SoundCategory.MASTER, 0.7f, 1.0f);
        }
    }
    
    @SubscribeEvent
    public static void onWorldTick(final TickEvent.WorldTickEvent event) {
        if (event.world.isClientSide() || event.phase == TickEvent.Phase.START) {
            return;
        }
        final ServerWorld world = (ServerWorld)event.world;
        final List<TreasureGoblinEntity> goblins = world.getEntities().filter(entity -> entity instanceof TreasureGoblinEntity).map(entity -> (TreasureGoblinEntity)entity).collect((Collector<? super Object, ?, List<TreasureGoblinEntity>>)Collectors.toList());
        goblins.stream().filter(goblin -> goblin.shouldDisappear).forEach(goblin -> goblin.disappear(world));
    }
    
    @Nullable
    protected SoundEvent getAmbientSound() {
        return ModSounds.GOBLIN_IDLE;
    }
    
    protected SoundEvent getDeathSound() {
        return ModSounds.GOBLIN_DEATH;
    }
    
    protected SoundEvent getHurtSound(@Nonnull final DamageSource damageSourceIn) {
        return ModSounds.GOBLIN_HURT;
    }
}
