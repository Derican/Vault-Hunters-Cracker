// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.skill.ability.effect.sub;

import iskallia.vault.skill.ability.config.AbilityConfig;
import iskallia.vault.skill.ability.config.MegaJumpConfig;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.util.IItemProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.block.BlockState;
import java.util.Iterator;
import iskallia.vault.skill.ability.AbilityNode;
import iskallia.vault.skill.ability.AbilityTree;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.BlockPos;
import iskallia.vault.util.BlockHelper;
import iskallia.vault.world.data.PlayerAbilitiesData;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraft.entity.player.ServerPlayerEntity;
import java.util.HashMap;
import java.util.UUID;
import java.util.Map;
import iskallia.vault.skill.ability.config.sub.MegaJumpBreakConfig;
import iskallia.vault.skill.ability.effect.MegaJumpAbility;

public class MegaJumpBreakAbility extends MegaJumpAbility<MegaJumpBreakConfig>
{
    private final Map<UUID, Integer> playerBreakMap;
    
    public MegaJumpBreakAbility() {
        this.playerBreakMap = new HashMap<UUID, Integer>();
    }
    
    @Override
    public boolean onAction(final MegaJumpBreakConfig config, final ServerPlayerEntity player, final boolean active) {
        if (super.onAction(config, player, active)) {
            this.playerBreakMap.put(player.getUUID(), 30);
            return true;
        }
        return false;
    }
    
    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.START || event.player.getCommandSenderWorld().isClientSide() || !(event.player.getCommandSenderWorld() instanceof ServerWorld)) {
            return;
        }
        final PlayerEntity player = event.player;
        final UUID plUUID = player.getUUID();
        if (!this.playerBreakMap.containsKey(plUUID)) {
            return;
        }
        int ticks = this.playerBreakMap.get(plUUID);
        if (--ticks <= 0) {
            this.playerBreakMap.remove(plUUID);
            return;
        }
        this.playerBreakMap.put(plUUID, ticks);
        final ServerWorld sWorld = (ServerWorld)player.getCommandSenderWorld();
        final AbilityTree abilityTree = PlayerAbilitiesData.get(sWorld).getAbilities(player);
        final AbilityNode<?, ?> focusedAbilityNode = abilityTree.getSelectedAbility();
        if (focusedAbilityNode != null && focusedAbilityNode.getAbility() == this) {
            for (final BlockPos offset : BlockHelper.getOvalPositions(player.blockPosition().above(3), 4.0f, 6.0f)) {
                final BlockState state = sWorld.getBlockState(offset);
                if (!state.isAir((IBlockReader)sWorld, offset) && (!state.requiresCorrectToolForDrops() || state.getHarvestLevel() <= 2)) {
                    final float hardness = state.getDestroySpeed((IBlockReader)sWorld, offset);
                    if (hardness < 0.0f || hardness > 25.0f) {
                        continue;
                    }
                    this.destroyBlock(sWorld, offset, player);
                }
            }
        }
    }
    
    private void destroyBlock(final ServerWorld world, final BlockPos pos, final PlayerEntity player) {
        final ItemStack miningItem = new ItemStack((IItemProvider)Items.DIAMOND_PICKAXE);
        Block.dropResources(world.getBlockState(pos), (World)world, pos, world.getBlockEntity(pos), (Entity)null, miningItem);
        world.destroyBlock(pos, false, (Entity)player);
    }
}
