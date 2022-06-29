// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.init;

import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraft.util.ResourceLocation;
import iskallia.vault.network.message.EnteredEyesoreDomainMessage;
import iskallia.vault.network.message.VaultCharmControllerScrollMessage;
import iskallia.vault.network.message.PlayerDamageMultiplierMessage;
import iskallia.vault.network.message.GlobalDifficultyMessage;
import iskallia.vault.network.message.ActiveEternalMessage;
import iskallia.vault.network.message.RageSyncMessage;
import iskallia.vault.network.message.ShardGlobalTradeMessage;
import iskallia.vault.network.message.EternalSyncMessage;
import iskallia.vault.network.message.EternalInteractionMessage;
import iskallia.vault.network.message.SyncOversizedStackMessage;
import iskallia.vault.network.message.ShardTraderScreenMessage;
import iskallia.vault.network.message.ShardTradeMessage;
import iskallia.vault.network.message.KnownTalentsMessage;
import iskallia.vault.network.message.EffectMessage;
import iskallia.vault.network.message.SandEventContributorMessage;
import iskallia.vault.network.message.SandEventUpdateMessage;
import iskallia.vault.network.message.PartyMembersMessage;
import iskallia.vault.network.message.PartyStatusMessage;
import iskallia.vault.network.message.PlayerStatisticsMessage;
import iskallia.vault.network.message.AbilityQuickselectMessage;
import iskallia.vault.network.message.VaultGoalMessage;
import iskallia.vault.network.message.VaultModifierMessage;
import iskallia.vault.network.message.OmegaStatueUIMessage;
import iskallia.vault.network.message.BossMusicMessage;
import iskallia.vault.network.message.StepHeightMessage;
import iskallia.vault.network.message.RenameUIMessage;
import iskallia.vault.network.message.AdvancedVendingUIMessage;
import iskallia.vault.network.message.VendingUIMessage;
import iskallia.vault.network.message.FighterSizeMessage;
import iskallia.vault.network.message.VaultOverlayMessage;
import iskallia.vault.network.message.AbilityActivityMessage;
import iskallia.vault.network.message.AbilityFocusMessage;
import iskallia.vault.network.message.AbilityKnownOnesMessage;
import iskallia.vault.network.message.AbilitySelectSpecializationMessage;
import iskallia.vault.network.message.AbilityUpgradeMessage;
import iskallia.vault.network.message.AbilityKeyMessage;
import iskallia.vault.network.message.ResearchTreeMessage;
import iskallia.vault.network.message.ResearchMessage;
import iskallia.vault.network.message.TalentUpgradeMessage;
import iskallia.vault.network.message.VaultLevelMessage;
import java.util.function.Function;
import java.util.function.BiConsumer;
import iskallia.vault.network.message.OpenSkillTreeMessage;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class ModNetwork
{
    private static final String NETWORK_VERSION = "0.25.0";
    public static final SimpleChannel CHANNEL;
    private static int ID;
    
    public static void initialize() {
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)OpenSkillTreeMessage.class, (BiConsumer)OpenSkillTreeMessage::encode, (Function)OpenSkillTreeMessage::decode, (BiConsumer)OpenSkillTreeMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)VaultLevelMessage.class, (BiConsumer)VaultLevelMessage::encode, (Function)VaultLevelMessage::decode, (BiConsumer)VaultLevelMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)TalentUpgradeMessage.class, (BiConsumer)TalentUpgradeMessage::encode, (Function)TalentUpgradeMessage::decode, (BiConsumer)TalentUpgradeMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)ResearchMessage.class, (BiConsumer)ResearchMessage::encode, (Function)ResearchMessage::decode, (BiConsumer)ResearchMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)ResearchTreeMessage.class, (BiConsumer)ResearchTreeMessage::encode, (Function)ResearchTreeMessage::decode, (BiConsumer)ResearchTreeMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)AbilityKeyMessage.class, (BiConsumer)AbilityKeyMessage::encode, (Function)AbilityKeyMessage::decode, (BiConsumer)AbilityKeyMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)AbilityUpgradeMessage.class, (BiConsumer)AbilityUpgradeMessage::encode, (Function)AbilityUpgradeMessage::decode, (BiConsumer)AbilityUpgradeMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)AbilitySelectSpecializationMessage.class, (BiConsumer)AbilitySelectSpecializationMessage::encode, (Function)AbilitySelectSpecializationMessage::decode, (BiConsumer)AbilitySelectSpecializationMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)AbilityKnownOnesMessage.class, (BiConsumer)AbilityKnownOnesMessage::encode, (Function)AbilityKnownOnesMessage::decode, (BiConsumer)AbilityKnownOnesMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)AbilityFocusMessage.class, (BiConsumer)AbilityFocusMessage::encode, (Function)AbilityFocusMessage::decode, (BiConsumer)AbilityFocusMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)AbilityActivityMessage.class, (BiConsumer)AbilityActivityMessage::encode, (Function)AbilityActivityMessage::decode, (BiConsumer)AbilityActivityMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)VaultOverlayMessage.class, (BiConsumer)VaultOverlayMessage::encode, (Function)VaultOverlayMessage::decode, (BiConsumer)VaultOverlayMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)FighterSizeMessage.class, (BiConsumer)FighterSizeMessage::encode, (Function)FighterSizeMessage::decode, (BiConsumer)FighterSizeMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)VendingUIMessage.class, (BiConsumer)VendingUIMessage::encode, (Function)VendingUIMessage::decode, (BiConsumer)VendingUIMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)AdvancedVendingUIMessage.class, (BiConsumer)AdvancedVendingUIMessage::encode, (Function)AdvancedVendingUIMessage::decode, (BiConsumer)AdvancedVendingUIMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)RenameUIMessage.class, (BiConsumer)RenameUIMessage::encode, (Function)RenameUIMessage::decode, (BiConsumer)RenameUIMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)StepHeightMessage.class, (BiConsumer)StepHeightMessage::encode, (Function)StepHeightMessage::decode, (BiConsumer)StepHeightMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)BossMusicMessage.class, (BiConsumer)BossMusicMessage::encode, (Function)BossMusicMessage::decode, (BiConsumer)BossMusicMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)OmegaStatueUIMessage.class, (BiConsumer)OmegaStatueUIMessage::encode, (Function)OmegaStatueUIMessage::decode, (BiConsumer)OmegaStatueUIMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)VaultModifierMessage.class, (BiConsumer)VaultModifierMessage::encode, (Function)VaultModifierMessage::decode, (BiConsumer)VaultModifierMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)VaultGoalMessage.class, (BiConsumer)VaultGoalMessage::encode, (Function)VaultGoalMessage::decode, (BiConsumer)VaultGoalMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)AbilityQuickselectMessage.class, (BiConsumer)AbilityQuickselectMessage::encode, (Function)AbilityQuickselectMessage::decode, (BiConsumer)AbilityQuickselectMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)PlayerStatisticsMessage.class, (BiConsumer)PlayerStatisticsMessage::encode, (Function)PlayerStatisticsMessage::decode, (BiConsumer)PlayerStatisticsMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)PartyStatusMessage.class, (BiConsumer)PartyStatusMessage::encode, (Function)PartyStatusMessage::decode, (BiConsumer)PartyStatusMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)PartyMembersMessage.class, (BiConsumer)PartyMembersMessage::encode, (Function)PartyMembersMessage::decode, (BiConsumer)PartyMembersMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)SandEventUpdateMessage.class, (BiConsumer)SandEventUpdateMessage::encode, (Function)SandEventUpdateMessage::decode, (BiConsumer)SandEventUpdateMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)SandEventContributorMessage.class, (BiConsumer)SandEventContributorMessage::encode, (Function)SandEventContributorMessage::decode, (BiConsumer)SandEventContributorMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)EffectMessage.class, (BiConsumer)EffectMessage::encode, (Function)EffectMessage::decode, (BiConsumer)EffectMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)KnownTalentsMessage.class, (BiConsumer)KnownTalentsMessage::encode, (Function)KnownTalentsMessage::decode, (BiConsumer)KnownTalentsMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)ShardTradeMessage.class, (BiConsumer)ShardTradeMessage::encode, (Function)ShardTradeMessage::decode, (BiConsumer)ShardTradeMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)ShardTraderScreenMessage.class, (BiConsumer)ShardTraderScreenMessage::encode, (Function)ShardTraderScreenMessage::decode, (BiConsumer)ShardTraderScreenMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)SyncOversizedStackMessage.class, (BiConsumer)SyncOversizedStackMessage::encode, (Function)SyncOversizedStackMessage::decode, (BiConsumer)SyncOversizedStackMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)EternalInteractionMessage.class, (BiConsumer)EternalInteractionMessage::encode, (Function)EternalInteractionMessage::decode, (BiConsumer)EternalInteractionMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)EternalSyncMessage.class, (BiConsumer)EternalSyncMessage::encode, (Function)EternalSyncMessage::decode, (BiConsumer)EternalSyncMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)ShardGlobalTradeMessage.class, (BiConsumer)ShardGlobalTradeMessage::encode, (Function)ShardGlobalTradeMessage::decode, (BiConsumer)ShardGlobalTradeMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)RageSyncMessage.class, (BiConsumer)RageSyncMessage::encode, (Function)RageSyncMessage::decode, (BiConsumer)RageSyncMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)ActiveEternalMessage.class, (BiConsumer)ActiveEternalMessage::encode, (Function)ActiveEternalMessage::decode, (BiConsumer)ActiveEternalMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)GlobalDifficultyMessage.class, (BiConsumer)GlobalDifficultyMessage::encode, (Function)GlobalDifficultyMessage::decode, (BiConsumer)GlobalDifficultyMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)PlayerDamageMultiplierMessage.class, (BiConsumer)PlayerDamageMultiplierMessage::encode, (Function)PlayerDamageMultiplierMessage::decode, (BiConsumer)PlayerDamageMultiplierMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)VaultCharmControllerScrollMessage.class, (BiConsumer)VaultCharmControllerScrollMessage::encode, (Function)VaultCharmControllerScrollMessage::decode, (BiConsumer)VaultCharmControllerScrollMessage::handle);
        ModNetwork.CHANNEL.registerMessage(nextId(), (Class)EnteredEyesoreDomainMessage.class, (BiConsumer)EnteredEyesoreDomainMessage::encode, (Function)EnteredEyesoreDomainMessage::decode, (BiConsumer)EnteredEyesoreDomainMessage::handle);
    }
    
    public static int nextId() {
        return ModNetwork.ID++;
    }
    
    static {
        CHANNEL = NetworkRegistry.newSimpleChannel(new ResourceLocation("the_vault", "network"), () -> "0.25.0", version -> version.equals("0.25.0"), version -> version.equals("0.25.0"));
        ModNetwork.ID = 0;
    }
}
