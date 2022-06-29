
package iskallia.vault.world.vault.logic.objective.raid.modifier;

import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import iskallia.vault.entity.FloatingItemEntity;
import net.minecraft.util.IItemProvider;
import net.minecraft.item.ItemStack;
import iskallia.vault.init.ModItems;
import net.minecraft.util.Direction;
import iskallia.vault.world.vault.logic.objective.raid.ActiveRaid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import iskallia.vault.world.vault.VaultRaid;
import net.minecraft.entity.MobEntity;

public class ArtifactFragmentModifier extends RaidModifier {
    public ArtifactFragmentModifier(final String name) {
        super(true, true, name);
    }

    @Override
    public void affectRaidMob(final MobEntity mob, final float value) {
    }

    @Override
    public void onVaultRaidFinish(final VaultRaid vault, final ServerWorld world, final BlockPos controller,
            final ActiveRaid raid, final float value) {
        if (ArtifactFragmentModifier.rand.nextFloat() >= value) {
            return;
        }
        final BlockPos at = controller.relative(Direction.UP, 3);
        final FloatingItemEntity itemEntity = FloatingItemEntity.create((World) world, at,
                new ItemStack((IItemProvider) ModItems.ARTIFACT_FRAGMENT));
        world.addFreshEntity((Entity) itemEntity);
    }

    @Override
    public ITextComponent getDisplay(final float value) {
        final int percDisplay = Math.round(value * 100.0f);
        return (ITextComponent) new StringTextComponent("+" + percDisplay + "% Artifact Fragment chance")
                .withStyle(TextFormatting.GOLD);
    }
}
