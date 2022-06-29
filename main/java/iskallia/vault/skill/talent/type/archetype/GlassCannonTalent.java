// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.skill.talent.type.archetype;

import java.util.HashMap;
import iskallia.vault.skill.talent.TalentGroup;
import iskallia.vault.init.ModConfigs;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import java.util.Iterator;
import iskallia.vault.skill.talent.TalentTree;
import net.minecraft.world.World;
import net.minecraft.entity.LivingEntity;
import iskallia.vault.skill.talent.TalentNode;
import net.minecraft.entity.player.PlayerEntity;
import iskallia.vault.world.data.PlayerTalentsData;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import com.google.gson.annotations.Expose;
import iskallia.vault.util.PlayerDamageHelper;
import java.util.UUID;
import java.util.Map;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class GlassCannonTalent extends ArchetypeTalent
{
    private static final Map<UUID, PlayerDamageHelper.DamageMultiplier> multiplierMap;
    @Expose
    protected float damageTakenMultiplier;
    @Expose
    protected float damageDealtMultiplier;
    
    public GlassCannonTalent(final int cost, final float damageTakenMultiplier, final float damageDealtMultiplier) {
        super(cost);
        this.damageTakenMultiplier = damageTakenMultiplier;
        this.damageDealtMultiplier = damageDealtMultiplier;
    }
    
    public float getDamageDealtMultiplier() {
        return this.damageDealtMultiplier;
    }
    
    public float getDamageTakenMultiplier() {
        return this.damageTakenMultiplier;
    }
    
    @SubscribeEvent
    public static void onPlayerDamage(final LivingHurtEvent event) {
        final LivingEntity entity = event.getEntityLiving();
        final World world = entity.getCommandSenderWorld();
        if (world.isClientSide()) {
            return;
        }
        if (!ArchetypeTalent.isEnabled(world)) {
            return;
        }
        if (entity instanceof ServerPlayerEntity) {
            final ServerPlayerEntity sPlayer = (ServerPlayerEntity)entity;
            final TalentTree talents = PlayerTalentsData.get(sPlayer.getLevel()).getTalents((PlayerEntity)sPlayer);
            for (final TalentNode<GlassCannonTalent> node : talents.getLearnedNodes(GlassCannonTalent.class)) {
                final GlassCannonTalent talent = node.getTalent();
                event.setAmount(event.getAmount() * talent.getDamageTakenMultiplier());
            }
        }
    }
    
    @SubscribeEvent
    public static void onPlayerTick(final TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END || !(event.player instanceof ServerPlayerEntity)) {
            return;
        }
        final ServerPlayerEntity sPlayer = (ServerPlayerEntity)event.player;
        final TalentTree talents = PlayerTalentsData.get(sPlayer.getLevel()).getTalents((PlayerEntity)sPlayer);
        if (talents.hasLearnedNode(ModConfigs.TALENTS.GLASS_CANNON) && ArchetypeTalent.isEnabled((World)sPlayer.getLevel())) {
            final float damageMultiplier = talents.getNodeOf(ModConfigs.TALENTS.GLASS_CANNON).getTalent().getDamageDealtMultiplier();
            PlayerDamageHelper.DamageMultiplier existing = GlassCannonTalent.multiplierMap.get(sPlayer.getUUID());
            if (existing != null) {
                if (existing.getMultiplier() == damageMultiplier) {
                    existing.refreshDuration(sPlayer.getServer());
                }
                else {
                    PlayerDamageHelper.removeMultiplier(sPlayer, existing);
                    existing = null;
                }
            }
            if (existing == null) {
                existing = PlayerDamageHelper.applyMultiplier(sPlayer, damageMultiplier, PlayerDamageHelper.Operation.ADDITIVE_MULTIPLY);
                GlassCannonTalent.multiplierMap.put(sPlayer.getUUID(), existing);
            }
        }
        else {
            removeExistingDamageBuff(sPlayer);
        }
    }
    
    private static void removeExistingDamageBuff(final ServerPlayerEntity player) {
        final PlayerDamageHelper.DamageMultiplier existing = GlassCannonTalent.multiplierMap.get(player.getUUID());
        if (existing != null) {
            PlayerDamageHelper.removeMultiplier(player, existing);
        }
    }
    
    static {
        multiplierMap = new HashMap<UUID, PlayerDamageHelper.DamageMultiplier>();
    }
}
