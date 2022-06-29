
package iskallia.vault.world.vault.influence;

import iskallia.vault.Vault;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import java.util.Iterator;
import iskallia.vault.world.vault.VaultRaid;
import net.minecraft.world.World;
import net.minecraft.entity.LivingEntity;
import iskallia.vault.world.data.VaultRaidData;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class DamageTakenInfluence extends VaultInfluence {
    public static final ResourceLocation ID;
    private float damageTakenMultiplier;

    DamageTakenInfluence() {
        super(DamageTakenInfluence.ID);
    }

    public DamageTakenInfluence(final float damageTakenMultiplier) {
        this();
        this.damageTakenMultiplier = damageTakenMultiplier;
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
        if (entity instanceof ServerPlayerEntity) {
            final ServerPlayerEntity sPlayer = (ServerPlayerEntity) entity;
            final VaultRaid vault = VaultRaidData.get(sPlayer.getLevel()).getAt(sPlayer.getLevel(),
                    sPlayer.blockPosition());
            if (vault != null) {
                float dmg = event.getAmount();
                for (final DamageTakenInfluence influence : vault.getInfluences()
                        .getInfluences(DamageTakenInfluence.class)) {
                    dmg *= influence.getDamageTakenMultiplier();
                }
                event.setAmount(dmg);
            }
        }
    }

    @Override
    public CompoundNBT serializeNBT() {
        final CompoundNBT tag = super.serializeNBT();
        tag.putFloat("dmg", this.damageTakenMultiplier);
        return tag;
    }

    @Override
    public void deserializeNBT(final CompoundNBT tag) {
        super.deserializeNBT(tag);
        this.damageTakenMultiplier = tag.getFloat("dmg");
    }

    static {
        ID = Vault.id("dmg_taken");
    }
}
