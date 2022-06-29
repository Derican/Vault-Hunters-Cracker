
package iskallia.vault.skill.talent.type;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import java.util.Iterator;
import net.minecraft.entity.item.ExperienceOrbEntity;
import iskallia.vault.skill.talent.TalentTree;
import iskallia.vault.world.data.PlayerFavourData;
import net.minecraft.entity.player.PlayerEntity;
import iskallia.vault.world.data.PlayerTalentsData;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import com.google.gson.annotations.Expose;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ExperiencedTalent extends PlayerTalent {
    @Expose
    private final float increasedExpPercentage;

    public ExperiencedTalent(final int cost, final float increasedExpPercentage) {
        super(cost);
        this.increasedExpPercentage = increasedExpPercentage;
    }

    public float getIncreasedExpPercentage() {
        return this.increasedExpPercentage;
    }

    @SubscribeEvent
    public static void onOrbPickup(final PlayerXpEvent.PickupXp event) {
        if (!(event.getPlayer() instanceof ServerPlayerEntity)) {
            return;
        }
        final ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        final TalentTree talents = PlayerTalentsData.get(player.getLevel()).getTalents((PlayerEntity) player);
        final ExperienceOrbEntity orb = event.getOrb();
        float increase = 0.0f;
        for (final ExperiencedTalent talent : talents.getTalents(ExperiencedTalent.class)) {
            increase += talent.getIncreasedExpPercentage();
        }
        final int favour = PlayerFavourData.get(player.getLevel()).getFavour(player.getUUID(),
                PlayerFavourData.VaultGodType.OMNISCIENT);
        if (favour >= 4) {
            increase += favour * 0.2f;
        } else if (favour <= -4) {
            increase -= Math.min(Math.abs(favour), 8) * 0.0625f;
        }
        final ExperienceOrbEntity experienceOrbEntity = orb;
        experienceOrbEntity.value *= (int) (1.0f + increase);
    }
}
