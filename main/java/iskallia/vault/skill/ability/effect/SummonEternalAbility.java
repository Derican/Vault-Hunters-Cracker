
package iskallia.vault.skill.ability.effect;

import iskallia.vault.skill.talent.TalentNode;
import iskallia.vault.skill.ability.config.AbilityConfig;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import java.util.Iterator;
import iskallia.vault.skill.talent.TalentTree;
import net.minecraft.entity.Entity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.entity.ai.attributes.Attributes;
import iskallia.vault.entity.eternal.EternalDataAccess;
import net.minecraft.world.World;
import iskallia.vault.entity.eternal.EternalHelper;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import iskallia.vault.skill.talent.type.archetype.CommanderTalent;
import iskallia.vault.world.data.PlayerTalentsData;
import iskallia.vault.entity.eternal.ActiveEternalData;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import iskallia.vault.entity.EternalEntity;
import java.util.List;
import iskallia.vault.entity.eternal.EternalData;
import java.util.ArrayList;
import iskallia.vault.Vault;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.Util;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import iskallia.vault.world.data.EternalsData;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.entity.player.ServerPlayerEntity;
import iskallia.vault.skill.ability.config.SummonEternalConfig;

public class SummonEternalAbility<C extends SummonEternalConfig> extends AbilityEffect<C> {
    @Override
    public String getAbilityGroupName() {
        return "Summon Eternal";
    }

    @Override
    public boolean onAction(final C config, final ServerPlayerEntity player, final boolean active) {
        if (player.getCommandSenderWorld().isClientSide() || !(player.getCommandSenderWorld() instanceof ServerWorld)) {
            return false;
        }
        final ServerWorld sWorld = (ServerWorld) player.getCommandSenderWorld();
        final EternalsData.EternalGroup playerEternals = EternalsData.get(sWorld).getEternals((PlayerEntity) player);
        if (playerEternals.getEternals().isEmpty()) {
            player.sendMessage((ITextComponent) new StringTextComponent("You have no eternals to summon.")
                    .withStyle(TextFormatting.RED), Util.NIL_UUID);
            return false;
        }
        if (player.getCommandSenderWorld().dimension() != Vault.VAULT_KEY && config.isVaultOnly()) {
            player.sendMessage((ITextComponent) new StringTextComponent("You can only summon eternals in the Vault!")
                    .withStyle(TextFormatting.RED), Util.NIL_UUID);
            return false;
        }
        final List<EternalData> eternals = new ArrayList<EternalData>();
        int count = this.getEternalCount(playerEternals, config);
        EternalData eternal = null;
        final List<EternalEntity> summonedEternals = player.getLevel().getEntities()
                .filter(entity -> entity instanceof EternalEntity).map(entity -> (EternalEntity) entity)
                .filter(eternal -> eternal.getOwnerUUID().equals(player.getUUID()))
                .collect((Collector<? super Object, ?, List<EternalEntity>>) Collectors.toList());
        final int maxToSummon = config.getSummonedEternalsCap() - summonedEternals.size();
        count = Math.min(count, maxToSummon);
        for (int i = 0; i < count; ++i) {
            eternal = null;
            final EternalData eternalData;
            if (SummonEternalAbility.rand.nextFloat() < config.getAncientChance()) {
                eternal = playerEternals.getRandomAliveAncient(SummonEternalAbility.rand,
                        eternalData -> !eternals.contains(eternalData)
                                && !ActiveEternalData.getInstance().isEternalActive(eternalData.getId()));
            }
            if (eternal == null) {
                eternal = playerEternals.getRandomAlive(SummonEternalAbility.rand,
                        eternalData -> !eternals.contains(eternalData)
                                && !ActiveEternalData.getInstance().isEternalActive(eternalData.getId()));
            }
            if (eternal != null) {
                eternals.add(eternal);
            }
        }
        if (eternals.isEmpty()) {
            if (count > 0) {
                player.sendMessage((ITextComponent) new StringTextComponent("You have no (alive) eternals to summon.")
                        .withStyle(TextFormatting.RED), Util.NIL_UUID);
            } else {
                player.sendMessage((ITextComponent) new StringTextComponent("You have reached the eternal cap.")
                        .withStyle(TextFormatting.RED), Util.NIL_UUID);
            }
            return false;
        }
        final TalentTree talents = PlayerTalentsData.get(sWorld).getTalents((PlayerEntity) player);
        final double damageMultiplier = talents.getLearnedNodes(CommanderTalent.class).stream()
                .mapToDouble(node -> node.getTalent().getSummonEternalDamageDealtMultiplier()).max().orElse(1.0);
        final AttributeModifier modifier = new AttributeModifier(CommanderTalent.ETERNAL_DAMAGE_INCREASE_MODIFIER,
                "CommanderTalent", damageMultiplier, AttributeModifier.Operation.MULTIPLY_TOTAL);
        final Iterator<EternalData> iterator = eternals.iterator();
        while (iterator.hasNext()) {
            final EternalData eternalData = iterator.next();
            final EternalEntity eternal2 = EternalHelper.spawnEternal((World) sWorld, eternalData);
            eternal2.moveTo(player.getX(), player.getY(), player.getZ(),
                    player.yRot, player.xRot);
            eternal2.setDespawnTime(sWorld.getServer().getTickCount() + config.getDespawnTime());
            eternal2.setOwner(player.getUUID());
            eternal2.setEternalId(eternalData.getId());
            eternal2.getAttribute(Attributes.ATTACK_DAMAGE).addPermanentModifier(modifier);
            eternal2.addEffect(new EffectInstance(Effects.GLOWING, Integer.MAX_VALUE, 0, true, false));
            this.postProcessEternal(eternal2, config);
            if (eternalData.getAura() != null) {
                eternal2.setProvidedAura(eternalData.getAura().getAuraName());
            }
            sWorld.addFreshEntity((Entity) eternal2);
        }
        return true;
    }

    protected int getEternalCount(final EternalsData.EternalGroup eternals, final C config) {
        return config.getNumberOfEternals();
    }

    protected void postProcessEternal(final EternalEntity eternalEntity, final C config) {
    }

    @SubscribeEvent
    public void onDamage(final LivingAttackEvent event) {
        final LivingEntity damagedEntity = event.getEntityLiving();
        final Entity dealerEntity = event.getSource().getEntity();
        if (damagedEntity instanceof EternalEntity && dealerEntity instanceof PlayerEntity) {
            final PlayerEntity player = (PlayerEntity) dealerEntity;
            if (!player.isCreative()) {
                event.setCanceled(true);
            }
        }
    }
}
