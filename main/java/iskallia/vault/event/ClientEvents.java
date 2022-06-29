
package iskallia.vault.event;

import iskallia.vault.Vault;
import java.util.Iterator;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.StringTextComponent;
import java.util.List;
import com.google.common.collect.Lists;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import iskallia.vault.client.ClientDamageData;
import iskallia.vault.client.ClientActiveEternalData;
import iskallia.vault.util.PlayerRageHelper;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraft.client.gui.AbstractGui;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import iskallia.vault.skill.talent.type.PlayerTalent;
import iskallia.vault.skill.talent.TalentNode;
import net.minecraft.entity.player.PlayerEntity;
import iskallia.vault.skill.talent.type.archetype.FrenzyTalent;
import iskallia.vault.skill.talent.TalentGroup;
import iskallia.vault.client.ClientTalentData;
import iskallia.vault.init.ModConfigs;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber({ Dist.CLIENT })
public class ClientEvents {
    private static final ResourceLocation OVERLAY_ICONS;

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void setupHealthTexture(final RenderGameOverlayEvent.Pre event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.HEALTH) {
            return;
        }
        final PlayerEntity player = (PlayerEntity) Minecraft.getInstance().player;
        if (player == null) {
            return;
        }
        final TalentNode<?> talentNode = ClientTalentData
                .getLearnedTalentNode((TalentGroup<?>) ModConfigs.TALENTS.FRENZY);
        if (talentNode == null || !talentNode.isLearned()) {
            return;
        }
        final PlayerTalent talent = (PlayerTalent) talentNode.getTalent();
        if (!(talent instanceof FrenzyTalent)) {
            return;
        }
        if (player.getHealth() / player.getMaxHealth() <= ((FrenzyTalent) talent).getThreshold()) {
            Minecraft.getInstance().getTextureManager().bind(ClientEvents.OVERLAY_ICONS);
        }
    }

    @SubscribeEvent
    public static void cleanupHealthTexture(final RenderGameOverlayEvent.Post event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.HEALTH) {
            return;
        }
        Minecraft.getInstance().getTextureManager().bind(AbstractGui.GUI_ICONS_LOCATION);
    }

    @SubscribeEvent
    public static void onDisconnect(final ClientPlayerNetworkEvent.LoggedOutEvent event) {
        PlayerRageHelper.clearClientCache();
        ClientActiveEternalData.clearClientCache();
        ClientDamageData.clearClientCache();
    }

    @SubscribeEvent
    public static void onItemTooltip(final ItemTooltipEvent event) {
        ModConfigs.TOOLTIP.getTooltipString(event.getItemStack().getItem()).ifPresent(str -> {
            final List tooltip = event.getToolTip();
            final List added = Lists.reverse((List) Lists.newArrayList((Object[]) str.split("\n")));
            if (!added.isEmpty()) {
                tooltip.add(1, StringTextComponent.EMPTY);
                added.iterator();
                final Iterator iterator;
                while (iterator.hasNext()) {
                    final String newStr = iterator.next();
                    tooltip.add(1, new StringTextComponent(newStr).withStyle(TextFormatting.GRAY));
                }
            }
        });
    }

    static {
        OVERLAY_ICONS = Vault.id("textures/gui/overlay_icons.png");
    }
}
