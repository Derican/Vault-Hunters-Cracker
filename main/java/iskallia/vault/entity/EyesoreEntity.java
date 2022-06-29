// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.entity;

import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataSerializers;
import iskallia.vault.world.vault.player.VaultPlayer;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.entity.living.EntityTeleportEvent;
import net.minecraft.block.BlockState;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraft.tags.ITag;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.Minecraft;
import iskallia.vault.world.vault.VaultRaid;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import iskallia.vault.init.ModEntities;
import net.minecraft.util.math.MathHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import java.util.function.Function;
import iskallia.vault.world.data.VaultRaidData;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import javax.annotation.Nonnull;
import net.minecraft.util.DamageSource;
import iskallia.vault.init.ModConfigs;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.entity.EntityType;
import iskallia.vault.entity.ai.eyesore.EyesoreBrain;
import iskallia.vault.entity.ai.eyesore.EyesorePath;
import net.minecraft.world.server.ServerBossInfo;
import java.util.UUID;
import java.util.Optional;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.entity.monster.GhastEntity;

public class EyesoreEntity extends GhastEntity implements VaultBoss
{
    public static final DataParameter<Integer> STATE;
    public static final DataParameter<Optional<UUID>> LASER_TARGET;
    public static final DataParameter<Integer> TENTACLES_REMAINING;
    public static final DataParameter<Boolean> WATCH_CLIENT;
    public final ServerBossInfo bossInfo;
    public EyesorePath path;
    public float prevHealth;
    public EyesoreBrain brain;
    public int laserTick;
    
    public EyesoreEntity(final EntityType<? extends GhastEntity> type, final World worldIn) {
        super((EntityType)type, worldIn);
        this.path = new EyesorePath();
        this.brain = new EyesoreBrain(this);
        this.laserTick = 0;
        this.bossInfo = new ServerBossInfo(this.getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.NOTCHED_10);
        this.noPhysics = true;
        this.setPersistenceRequired();
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue((double)ModConfigs.EYESORE.getHealth(this));
        this.setHealth(ModConfigs.EYESORE.getHealth(this));
        this.prevHealth = this.getHealth();
    }
    
    public int getTentaclesRemaining() {
        return (int)this.entityData.get((DataParameter)EyesoreEntity.TENTACLES_REMAINING);
    }
    
    public State getState() {
        final Integer ordinal = (Integer)this.entityData.get((DataParameter)EyesoreEntity.STATE);
        return State.values()[ordinal];
    }
    
    public void setState(final State state) {
        this.entityData.set((DataParameter)EyesoreEntity.STATE, (Object)state.ordinal());
    }
    
    protected void dropFromLootTable(@Nonnull final DamageSource damageSource, final boolean attackedRecently) {
    }
    
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define((DataParameter)EyesoreEntity.STATE, (Object)State.NORMAL.ordinal());
        this.entityData.define((DataParameter)EyesoreEntity.LASER_TARGET, (Object)Optional.empty());
        this.entityData.define((DataParameter)EyesoreEntity.TENTACLES_REMAINING, (Object)9);
        this.entityData.define((DataParameter)EyesoreEntity.WATCH_CLIENT, (Object)false);
    }
    
    protected void registerGoals() {
    }
    
    protected float getStandingEyeHeight(final Pose poseIn, final EntitySize sizeIn) {
        return 5.0f;
    }
    
    @Nonnull
    @OnlyIn(Dist.CLIENT)
    public AxisAlignedBB getBoundingBoxForCulling() {
        return super.getBoundingBoxForCulling().inflate(100.0);
    }
    
    public ServerBossInfo getServerBossInfo() {
        return this.bossInfo;
    }
    
    public void startSeenByPlayer(final ServerPlayerEntity player) {
        super.startSeenByPlayer(player);
        this.bossInfo.addPlayer(player);
    }
    
    public void stopSeenByPlayer(final ServerPlayerEntity player) {
        super.stopSeenByPlayer(player);
        this.bossInfo.removePlayer(player);
    }
    
    public void tick() {
        super.tick();
        if (!this.level.isClientSide) {
            this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
            final float maxHealth = ModConfigs.EYESORE.getHealth(this);
            final float currentMaxHealth = (float)this.getAttribute(Attributes.MAX_HEALTH).getBaseValue();
            if (Math.abs(maxHealth - currentMaxHealth) > 0.1f) {
                this.setHealth(this.getHealth() / currentMaxHealth * maxHealth);
                this.getAttribute(Attributes.MAX_HEALTH).setBaseValue((double)maxHealth);
            }
            final ServerWorld serverWorld = (ServerWorld)this.level;
            final VaultRaid vault = VaultRaidData.get(serverWorld).getAt(serverWorld, this.blockPosition());
            if (vault != null && this.level.getGameTime() % 40L == 0L) {
                vault.getPlayers().stream().map(p -> p.getServerPlayer(((ServerWorld)this.level).getServer())).filter(Optional::isPresent).map((Function<? super Object, ?>)Optional::get).forEach(p -> this.level.playSound((PlayerEntity)null, p.getX(), p.getY(), p.getZ(), SoundEvents.CONDUIT_AMBIENT, SoundCategory.HOSTILE, 1.0f, 0.5f));
            }
            this.brain.tick();
            this.path.tick(this);
            final float healthPercentage = this.getHealth() / this.getMaxHealth();
            final int tentaclesRemaining = (int)this.entityData.get((DataParameter)EyesoreEntity.TENTACLES_REMAINING);
            final int expectedTentacles = MathHelper.clamp((int)(healthPercentage * 10.0f), 0, 9);
            if (tentaclesRemaining != expectedTentacles) {
                this.entityData.set((DataParameter)EyesoreEntity.TENTACLES_REMAINING, (Object)expectedTentacles);
                for (int i = 0; i < tentaclesRemaining - expectedTentacles; ++i) {
                    final EyestalkEntity eyestalkEntity = (EyestalkEntity)ModEntities.EYESTALK.create((World)serverWorld);
                    if (eyestalkEntity != null) {
                        eyestalkEntity.moveTo(this.getX(), this.getY() + 7.0, this.getZ(), this.xRot, this.yRot);
                        eyestalkEntity.setDeltaMovement(0.0, 0.25, 0.0);
                        serverWorld.addWithUUID((Entity)eyestalkEntity);
                        eyestalkEntity.mother = this.getUUID();
                    }
                }
            }
        }
        final LivingEntity target = ((Optional)this.getEntityData().get((DataParameter)EyesoreEntity.LASER_TARGET)).map(playerId -> this.getCommandSenderWorld().getPlayerByUUID(playerId)).orElse(null);
        if (target != null) {
            ++this.laserTick;
            this.lookAtTarget(target);
        }
        else {
            this.laserTick = 0;
            final PlayerEntity closestPlayer = this.level.getNearestPlayer((Entity)this, 40.0);
            if (closestPlayer != null) {}
        }
        if ((boolean)this.getEntityData().get((DataParameter)EyesoreEntity.WATCH_CLIENT) && this.level.isClientSide) {
            this.lookAtClientPlayer();
        }
    }
    
    @OnlyIn(Dist.CLIENT)
    private void lookAtClientPlayer() {
        final ClientPlayerEntity playerEntity = Minecraft.getInstance().player;
        this.lookAtTarget((LivingEntity)playerEntity);
    }
    
    protected void lookAtTarget(final LivingEntity target) {
        this.xRot = this.getTargetPitch(target);
        this.yHeadRot = this.getTargetYaw(target);
    }
    
    private double getEyePosition(final Entity entity) {
        return (entity instanceof LivingEntity) ? entity.getEyeY() : ((entity.getBoundingBox().minY + entity.getBoundingBox().maxY) / 2.0);
    }
    
    protected float getTargetPitch(final LivingEntity target) {
        final double d0 = target.getX() - this.getX();
        final double d2 = this.getEyePosition((Entity)target) - this.getEyeY();
        final double d3 = target.getZ() - this.getZ();
        final double d4 = MathHelper.sqrt(d0 * d0 + d3 * d3);
        return (float)(-(MathHelper.atan2(d2, d4) * 57.2957763671875));
    }
    
    protected float getTargetYaw(final LivingEntity target) {
        final double d0 = target.getX() - this.getX();
        final double d2 = target.getZ() - this.getZ();
        return (float)(MathHelper.atan2(d2, d0) * 57.2957763671875) - 90.0f;
    }
    
    public boolean hurt(final DamageSource source, final float amount) {
        if (source instanceof IndirectEntityDamageSource) {
            for (int i = 0; i < 64; ++i) {
                if (this.teleportRandomly()) {
                    return true;
                }
            }
            return false;
        }
        return super.hurt(source, amount);
    }
    
    protected boolean teleportRandomly() {
        if (!this.level.isClientSide() && this.isAlive()) {
            final double d0 = this.getX() + (this.random.nextDouble() - 0.5) * 64.0;
            final double d2 = this.getY() + (this.random.nextInt(64) - 32);
            final double d3 = this.getZ() + (this.random.nextDouble() - 0.5) * 64.0;
            return this.teleportTo(d0, d2, d3);
        }
        return false;
    }
    
    private boolean teleportTo(final double x, final double y, final double z) {
        final BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable(x, y, z);
        while (blockpos$mutable.getY() > 0 && !this.level.getBlockState((BlockPos)blockpos$mutable).getMaterial().blocksMotion()) {
            blockpos$mutable.move(Direction.DOWN);
        }
        final BlockState blockstate = this.level.getBlockState((BlockPos)blockpos$mutable);
        final boolean flag = blockstate.getMaterial().blocksMotion();
        final boolean flag2 = blockstate.getFluidState().is((ITag)FluidTags.WATER);
        if (!flag || flag2) {
            return false;
        }
        final EntityTeleportEvent.EnderEntity event = ForgeEventFactory.onEnderTeleport((LivingEntity)this, x, y, z);
        if (event.isCanceled()) {
            return false;
        }
        final boolean flag3 = this.randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true);
        if (flag3 && !this.isSilent()) {
            this.level.playSound((PlayerEntity)null, this.xo, this.yo, this.zo, SoundEvents.ENDERMAN_TELEPORT, this.getSoundSource(), 1.0f, 1.0f);
            this.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0f, 1.0f);
        }
        return flag3;
    }
    
    public void playerTouch(@Nonnull final PlayerEntity playerEntity) {
        if (!(playerEntity instanceof ServerPlayerEntity)) {
            return;
        }
        final ServerPlayerEntity player = (ServerPlayerEntity)playerEntity;
        final Vector3d posPlayer = player.position();
        final Vector3d posEyesore = this.position();
        player.hurt(DamageSource.mobAttack((LivingEntity)this), ModConfigs.EYESORE.meleeAttack.getDamage(this));
        this.applyKnockback((Entity)player, ModConfigs.EYESORE.meleeAttack.knockback, posEyesore.x - posPlayer.x, posEyesore.z - posPlayer.z);
    }
    
    public void applyKnockback(final Entity target, final float strength, final double ratioX, final double ratioZ) {
        if (strength > 0.0f) {
            target.hasImpulse = true;
            final Vector3d vector3d = target.getDeltaMovement();
            final Vector3d vector3d2 = new Vector3d(ratioX, 0.0, ratioZ).normalize().scale((double)strength);
            target.setDeltaMovement(vector3d.x / 2.0 - vector3d2.x, this.onGround ? Math.min(0.4, vector3d.y / 2.0 + strength) : vector3d.y, vector3d.z / 2.0 - vector3d2.z);
        }
    }
    
    @Nonnull
    public SoundCategory getSoundSource() {
        return SoundCategory.HOSTILE;
    }
    
    protected SoundEvent getDeathSound() {
        return super.getDeathSound();
    }
    
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENDER_DRAGON_AMBIENT;
    }
    
    protected SoundEvent getHurtSound(final DamageSource damageSource) {
        return SoundEvents.ENDER_DRAGON_HURT;
    }
    
    protected float getVoicePitch() {
        return (this.random.nextFloat() - this.random.nextFloat()) * 0.2f + 1.3f;
    }
    
    public void setCustomName(final ITextComponent name) {
        super.setCustomName(name);
        this.bossInfo.setName(this.getDisplayName());
    }
    
    public void addAdditionalSaveData(final CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
    }
    
    public void readAdditionalSaveData(final CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.bossInfo.setName(this.getDisplayName());
    }
    
    public static AttributeModifierMap.MutableAttribute getAttributes() {
        return MonsterEntity.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 100.0).add(Attributes.MOVEMENT_SPEED, 0.25).add(Attributes.ATTACK_DAMAGE, 3.0).add(Attributes.ATTACK_KNOCKBACK, 3.0).add(Attributes.KNOCKBACK_RESISTANCE, 0.4).add(Attributes.ARMOR, 2.0);
    }
    
    static {
        STATE = EntityDataManager.defineId((Class)EyesoreEntity.class, DataSerializers.INT);
        LASER_TARGET = EntityDataManager.defineId((Class)EyesoreEntity.class, DataSerializers.OPTIONAL_UUID);
        TENTACLES_REMAINING = EntityDataManager.defineId((Class)EyesoreEntity.class, DataSerializers.INT);
        WATCH_CLIENT = EntityDataManager.defineId((Class)EyesoreEntity.class, DataSerializers.BOOLEAN);
    }
    
    public enum State
    {
        NORMAL, 
        GIVING_BIRTH;
    }
}
