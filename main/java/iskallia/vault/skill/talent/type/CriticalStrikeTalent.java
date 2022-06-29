
package iskallia.vault.skill.talent.type;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import java.util.Iterator;
import iskallia.vault.skill.talent.TalentTree;
import net.minecraftforge.eventbus.api.Event;
import net.minecraft.entity.player.PlayerEntity;
import iskallia.vault.world.data.PlayerTalentsData;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import com.google.gson.annotations.Expose;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CriticalStrikeTalent extends PlayerTalent {
    @Expose
    private final float chance;

    public CriticalStrikeTalent(final int cost, final float chance) {
        super(cost);
        this.chance = chance;
    }

    public float getChance() {
        return this.chance;
    }

    @SubscribeEvent
    public static void onCriticalHit(final CriticalHitEvent event) {
        if (event.getEntity().level.isClientSide()) {
            return;
        }
        final ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        final TalentTree talents = PlayerTalentsData.get(player.getLevel()).getTalents((PlayerEntity) player);
        for (final CriticalStrikeTalent criticalStrikeTalent : talents.getTalents(CriticalStrikeTalent.class)) {
            if (player.level.random.nextFloat() < criticalStrikeTalent.getChance()) {
                if (event.getDamageModifier() < 1.5f) {
                    event.setDamageModifier(1.5f);
                }
                event.setResult(Event.Result.ALLOW);
            }
        }
    }
}
