
package iskallia.vault.entity;

import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.entity.player.ServerPlayerEntity;
import iskallia.vault.world.vault.VaultRaid;
import iskallia.vault.world.vault.logic.objective.raid.RaidChallengeObjective;
import iskallia.vault.world.data.VaultRaidData;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.entity.Entity;
import iskallia.vault.skill.ability.effect.sub.RampageDotAbility;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import iskallia.vault.entity.ai.AOEGoal;
import iskallia.vault.entity.ai.ThrowProjectilesGoal;
import iskallia.vault.init.ModSounds;
import iskallia.vault.entity.ai.TeleportGoal;
import iskallia.vault.entity.ai.MobAttackGoal;
import iskallia.vault.entity.ai.CowDashAttackGoal;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.world.BossInfo;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.world.World;
import net.minecraft.entity.EntityType;
import iskallia.vault.entity.ai.RegenAfterAWhile;
import net.minecraft.world.server.ServerBossInfo;
import iskallia.vault.entity.ai.TeleportRandomly;

public class AggressiveCowBossEntity extends AggressiveCowEntity implements VaultBoss {
    public TeleportRandomly<AggressiveCowBossEntity> teleportTask;
    public final ServerBossInfo bossInfo;
    public final RegenAfterAWhile<AggressiveCowBossEntity> regenAfterAWhile;

    public AggressiveCowBossEntity(final EntityType<? extends AggressiveCowEntity> type, final World worldIn) {
        super(type, worldIn);
        this.teleportTask = new TeleportRandomly<AggressiveCowBossEntity>(this,
                (TeleportRandomly.Condition<AggressiveCowBossEntity>[]) new TeleportRandomly.Condition[] {
                        (entity, source, amount) -> {
                            if (!(source.getEntity() instanceof LivingEntity)) {
                                return 0.2;
                            } else {
                                return 0.0;
                            }
                        } });
        this.bossInfo = new ServerBossInfo(this.getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS);
        this.regenAfterAWhile = new RegenAfterAWhile<AggressiveCowBossEntity>(this);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(8, (Goal) new WaterAvoidingRandomWalkingGoal((CreatureEntity) this, 1.5));
        this.goalSelector.addGoal(8, (Goal) new LookAtGoal((MobEntity) this, (Class) PlayerEntity.class, 16.0f));
        this.goalSelector.addGoal(0, (Goal) new CowDashAttackGoal(this, 0.2f));
        this.goalSelector.addGoal(1, (Goal) new MobAttackGoal((CreatureEntity) this, 1.5, true));
        this.goalSelector.addGoal(1,
                (Goal) TeleportGoal.builder(this)
                        .start(entity -> entity.getTarget() != null && entity.tickCount % 60 == 0)
                        .to(entity -> entity.getTarget().position().add(
                                (entity.random.nextDouble() - 0.5) * 8.0,
                                (double) (entity.random.nextInt(16) - 8),
                                (entity.random.nextDouble() - 0.5) * 8.0))
                        .then(entity -> entity.playSound(ModSounds.BOSS_TP_SFX, 1.0f, 1.0f)).build());
        this.goalSelector.addGoal(1,
                (Goal) new ThrowProjectilesGoal<Object>(this, 96, 10, FighterEntity.SNOWBALLS));
        this.goalSelector.addGoal(1, (Goal) new AOEGoal<Object>(this, e -> !(e instanceof VaultBoss)));
        this.targetSelector.addGoal(1,
                (Goal) new NearestAttackableTargetGoal((MobEntity) this, (Class) PlayerEntity.class, false));
    }

    public boolean hurt(final DamageSource source, final float amount) {
        final Entity trueSource = source.getEntity();
        if (!(source instanceof RampageDotAbility.PlayerDamageOverTimeSource)
                && !(source.getEntity() instanceof PlayerEntity) && !(trueSource instanceof EternalEntity)
                && source != DamageSource.OUT_OF_WORLD) {
            return false;
        }
        if (this.isInvulnerableTo(source) || source == DamageSource.FALL) {
            return false;
        }
        if (this.teleportTask.attackEntityFrom(source, amount)) {
            return true;
        }
        this.regenAfterAWhile.onDamageTaken();
        return super.hurt(source, amount);
    }

    @Override
    public ServerBossInfo getServerBossInfo() {
        return this.bossInfo;
    }

    @Override
    public void onDash() {
        super.onDash();
        this.dashCooldown /= 2;
    }

    public void tick() {
        super.tick();
        if (!this.level.isClientSide) {
            this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
            this.regenAfterAWhile.tick();
            final VaultRaid vault = VaultRaidData.get((ServerWorld) this.level)
                    .getAt((ServerWorld) this.level, this.blockPosition());
            this.bossInfo.setVisible(
                    vault == null || !vault.getActiveObjective(RaidChallengeObjective.class).isPresent());
        }
    }

    public void startSeenByPlayer(final ServerPlayerEntity player) {
        super.startSeenByPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    public void stopSeenByPlayer(final ServerPlayerEntity player) {
        super.stopSeenByPlayer(player);
        this.bossInfo.removePlayer(player);
    }
}
