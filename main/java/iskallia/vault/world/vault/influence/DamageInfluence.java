// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.world.vault.influence;

import iskallia.vault.Vault;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import java.util.Iterator;
import iskallia.vault.world.vault.VaultRaid;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.entity.LivingEntity;
import iskallia.vault.world.data.VaultRaidData;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class DamageInfluence extends VaultInfluence
{
    public static final ResourceLocation ID;
    private float damageDealtMultiplier;
    
    DamageInfluence() {
        super(DamageInfluence.ID);
    }
    
    public DamageInfluence(final float damageDealtMultiplier) {
        this();
        this.damageDealtMultiplier = damageDealtMultiplier;
    }
    
    public float getDamageDealtMultiplier() {
        return this.damageDealtMultiplier;
    }
    
    @SubscribeEvent
    public static void onPlayerDamage(final LivingHurtEvent event) {
        final LivingEntity entity = event.getEntityLiving();
        final World world = entity.getCommandSenderWorld();
        if (world.isClientSide()) {
            return;
        }
        final Entity attacker = event.getSource().getEntity();
        if (attacker instanceof ServerPlayerEntity) {
            final ServerPlayerEntity sPlayer = (ServerPlayerEntity)attacker;
            final VaultRaid vault = VaultRaidData.get(sPlayer.getLevel()).getAt(sPlayer.getLevel(), sPlayer.blockPosition());
            if (vault != null) {
                float dmg = event.getAmount();
                for (final DamageInfluence influence : vault.getInfluences().getInfluences(DamageInfluence.class)) {
                    dmg *= influence.getDamageDealtMultiplier();
                }
                event.setAmount(dmg);
            }
        }
    }
    
    @Override
    public CompoundNBT serializeNBT() {
        final CompoundNBT tag = super.serializeNBT();
        tag.putFloat("dmg", this.damageDealtMultiplier);
        return tag;
    }
    
    @Override
    public void deserializeNBT(final CompoundNBT tag) {
        super.deserializeNBT(tag);
        this.damageDealtMultiplier = tag.getFloat("dmg");
    }
    
    static {
        ID = Vault.id("dmg_dealt");
    }
}
