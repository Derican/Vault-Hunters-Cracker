
package iskallia.vault.client.gui.overlay;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import com.mojang.blaze3d.matrix.MatrixStack;
import iskallia.vault.client.gui.overlay.goal.BossBarOverlay;
import iskallia.vault.client.vault.goal.VaultGoalData;
import iskallia.vault.network.message.VaultOverlayMessage;
import iskallia.vault.client.ClientVaultRaidData;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VaultGoalBossBarOverlay {
    @SubscribeEvent
    public static void onBossBarRender(final RenderGameOverlayEvent.Pre event) {
        final VaultOverlayMessage.OverlayType type = ClientVaultRaidData.getOverlayType();
        if (event.getType() != RenderGameOverlayEvent.ElementType.BOSSHEALTH) {
            return;
        }
        if (type != VaultOverlayMessage.OverlayType.VAULT) {
            return;
        }
        final VaultGoalData data = VaultGoalData.CURRENT_DATA;
        if (data == null) {
            return;
        }
        final BossBarOverlay overlay = data.getBossBarOverlay();
        if (overlay == null || !overlay.shouldDisplay()) {
            return;
        }
        final MatrixStack renderStack = event.getMatrixStack();
        overlay.drawOverlay(renderStack, event.getPartialTicks());
    }
}
