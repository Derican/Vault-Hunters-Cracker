// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.entity;

import iskallia.vault.world.vault.player.VaultPlayer;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import com.google.common.base.Strings;
import net.minecraft.util.text.StringTextComponent;
import iskallia.vault.init.ModEntities;
import iskallia.vault.util.NameProviderPublic;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
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
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.EntityType;

public class VaultFighterEntity extends FighterEntity
{
    public VaultFighterEntity(final EntityType<? extends ZombieEntity> type, final World world) {
        super(type, world);
    }
    
    @Override
    protected void dropFromLootTable(final DamageSource source, final boolean attackedRecently) {
        final ServerWorld world = (ServerWorld)this.level;
        final VaultRaid vault = VaultRaidData.get(world).getAt(world, this.blockPosition());
        if (vault != null) {
            vault.getProperties().getBase(VaultRaid.HOST).flatMap((Function<? super UUID, ? extends Optional<?>>)vault::getPlayer).ifPresent(player -> {
                final int level = player.getProperties().getBase(VaultRaid.LEVEL).orElse(0);
                final ResourceLocation id = ModConfigs.LOOT_TABLES.getForLevel(level).getVaultFighter();
                final LootTable loot = this.level.getServer().getLootTables().get(id);
                final LootContext.Builder builder = this.createLootContext(attackedRecently, source);
                final LootContext ctx = builder.create(LootParameterSets.ENTITY);
                loot.getRandomItems(ctx).forEach(this::spawnAtLocation);
                return;
            });
        }
        super.dropFromLootTable(source, attackedRecently);
    }
    
    @Override
    public ILivingEntityData finalizeSpawn(final IServerWorld world, final DifficultyInstance difficulty, final SpawnReason reason, final ILivingEntityData spawnData, final CompoundNBT dataTag) {
        final ILivingEntityData livingData = super.finalizeSpawn(world, difficulty, reason, spawnData, dataTag);
        final ServerWorld sWorld = (ServerWorld)this.level;
        if (!this.level.isClientSide()) {
            final VaultRaid vault = VaultRaidData.get(sWorld).getAt(sWorld, this.blockPosition());
            if (vault != null) {
                final String name = NameProviderPublic.getRandomName();
                final String star = String.valueOf('\u2726');
                final int count = Math.max(ModEntities.VAULT_FIGHTER_TYPES.indexOf(this.getType()), 0);
                final IFormattableTextComponent customName = new StringTextComponent("").append((ITextComponent)new StringTextComponent(Strings.repeat(star, count)).withStyle(TextFormatting.GOLD)).append(" ").append((ITextComponent)new StringTextComponent(name));
                this.setCustomName((ITextComponent)customName);
                this.getPersistentData().putString("VaultPlayerName", name);
            }
        }
        return livingData;
    }
}
