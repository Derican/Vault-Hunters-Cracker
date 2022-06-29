
package iskallia.vault.world.vault.logic.objective.raid.modifier;

import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import iskallia.vault.world.vault.logic.objective.raid.ActiveRaid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import iskallia.vault.world.vault.VaultRaid;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.MobEntity;
import java.util.UUID;

public class MonsterHealthModifier extends RaidModifier {
    private static final UUID MOB_HEALTH_INCREASE;

    public MonsterHealthModifier(final String name) {
        super(true, false, name);
    }

    @Override
    public void affectRaidMob(final MobEntity mob, final float value) {
        final ModifiableAttributeInstance attr = mob.getAttribute(Attributes.MAX_HEALTH);
        if (attr != null) {
            attr.addPermanentModifier(new AttributeModifier(MonsterHealthModifier.MOB_HEALTH_INCREASE,
                    "Raid Mob Health Increase", (double) value, AttributeModifier.Operation.MULTIPLY_BASE));
        }
    }

    @Override
    public void onVaultRaidFinish(final VaultRaid vault, final ServerWorld world, final BlockPos controller,
            final ActiveRaid raid, final float value) {
    }

    @Override
    public ITextComponent getDisplay(final float value) {
        final int percDisplay = Math.round(value * 100.0f);
        return (ITextComponent) new StringTextComponent("+" + percDisplay + "% increased Mob Health")
                .withStyle(TextFormatting.RED);
    }

    static {
        MOB_HEALTH_INCREASE = UUID.fromString("1fcb7b39-1850-4fc2-8f90-886746ee8b41");
    }
}
