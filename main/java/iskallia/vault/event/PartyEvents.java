
package iskallia.vault.event;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.world.World;
import iskallia.vault.world.data.VaultPartyData;
import net.minecraft.entity.Entity;
import java.util.function.Function;
import java.util.UUID;
import iskallia.vault.entity.EternalEntity;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PartyEvents {
    @SubscribeEvent
    public static void onAttack(final LivingAttackEvent event) {
        final Entity source = event.getSource().getEntity();
        if (!(source instanceof LivingEntity)) {
            return;
        }
        final LivingEntity attacker = (LivingEntity) source;
        final LivingEntity attacked = event.getEntityLiving();
        final World world = attacked.getCommandSenderWorld();
        if (!(world instanceof ServerWorld)) {
            return;
        }
        final ServerWorld sWorld = (ServerWorld) world;
        UUID attackerUUID = attacker.getUUID();
        if (attacker instanceof EternalEntity) {
            attackerUUID = (UUID) ((EternalEntity) attacker).getOwner().map((Function) Function.identity(),
                    (Function) Entity::getUUID);
        }
        final UUID attackedUUID = attacked.getUUID();
        if (attacked instanceof EternalEntity) {
            attackerUUID = (UUID) ((EternalEntity) attacked).getOwner().map((Function) Function.identity(),
                    (Function) Entity::getUUID);
        }
        final VaultPartyData.Party party = VaultPartyData.get(sWorld).getParty(attackerUUID).orElse(null);
        if (party != null && party.hasMember(attackedUUID)) {
            event.setCanceled(true);
        }
    }
}
