// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.block;

import iskallia.vault.world.vault.player.VaultPlayer;
import net.minecraft.tileentity.TileEntity;
import iskallia.vault.block.base.FillableAltarTileEntity;
import net.minecraft.state.Property;
import net.minecraft.util.Direction;
import net.minecraft.block.ChestBlock;
import iskallia.vault.world.vault.VaultRaid;
import iskallia.vault.world.vault.time.extension.TimeExtension;
import iskallia.vault.world.vault.time.extension.TimeAltarExtension;
import net.minecraft.entity.player.PlayerEntity;
import iskallia.vault.world.data.VaultRaidData;
import net.minecraft.util.ActionResultType;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import iskallia.vault.world.data.PlayerFavourData;
import iskallia.vault.init.ModParticles;
import net.minecraft.particles.IParticleData;
import iskallia.vault.init.ModBlocks;
import net.minecraft.world.IBlockReader;
import net.minecraft.block.BlockState;
import iskallia.vault.block.entity.TimeAltarTileEntity;
import iskallia.vault.block.base.FillableAltarBlock;

public class TimeAltarBlock extends FillableAltarBlock<TimeAltarTileEntity>
{
    @Override
    public TimeAltarTileEntity createTileEntity(final BlockState state, final IBlockReader world) {
        return (TimeAltarTileEntity)ModBlocks.TIME_ALTAR_TILE_ENTITY.create();
    }
    
    @Override
    public IParticleData getFlameParticle() {
        return (IParticleData)ModParticles.YELLOW_FLAME.get();
    }
    
    @Override
    public PlayerFavourData.VaultGodType getAssociatedVaultGod() {
        return PlayerFavourData.VaultGodType.TIMEKEEPER;
    }
    
    @Override
    public ActionResultType rightClicked(final BlockState state, final ServerWorld world, final BlockPos pos, final TimeAltarTileEntity tileEntity, final ServerPlayerEntity player, final ItemStack heldStack) {
        if (!tileEntity.initialized()) {
            return ActionResultType.SUCCESS;
        }
        if (player.isCreative()) {
            tileEntity.makeProgress(player, 1, sPlayer -> {});
            return ActionResultType.SUCCESS;
        }
        final VaultRaid vault = VaultRaidData.get(world).getActiveFor(player);
        if (vault == null) {
            return ActionResultType.FAIL;
        }
        tileEntity.makeProgress(player, 1, sPlayer -> {
            final PlayerFavourData data = PlayerFavourData.get(sPlayer.getLevel());
            if (TimeAltarBlock.rand.nextFloat() < FillableAltarBlock.getFavourChance((PlayerEntity)sPlayer, PlayerFavourData.VaultGodType.TIMEKEEPER)) {
                final PlayerFavourData.VaultGodType vg = this.getAssociatedVaultGod();
                if (data.addFavour((PlayerEntity)sPlayer, vg, 1)) {
                    data.addFavour((PlayerEntity)sPlayer, vg.getOther(TimeAltarBlock.rand), -1);
                    FillableAltarBlock.playFavourInfo(sPlayer);
                }
            }
            return;
        });
        vault.getPlayers().forEach(vaultPlayer -> vaultPlayer.getTimer().addTime(new TimeAltarExtension(-1200), 0));
        return ActionResultType.SUCCESS;
    }
    
    @Override
    protected BlockState getSuccessChestState(final BlockState altarState) {
        final BlockState chestState = super.getSuccessChestState(altarState);
        return (BlockState)chestState.setValue((Property)ChestBlock.FACING, (Comparable)((Direction)chestState.getValue((Property)TimeAltarBlock.FACING)).getOpposite());
    }
}
