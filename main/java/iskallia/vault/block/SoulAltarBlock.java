// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.block;

import net.minecraft.tileentity.TileEntity;
import iskallia.vault.block.base.FillableAltarTileEntity;
import net.minecraft.state.Property;
import net.minecraft.util.Direction;
import net.minecraft.block.ChestBlock;
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
import iskallia.vault.block.entity.SoulAltarTileEntity;
import iskallia.vault.block.base.FillableAltarBlock;

public class SoulAltarBlock extends FillableAltarBlock<SoulAltarTileEntity>
{
    @Override
    public SoulAltarTileEntity createTileEntity(final BlockState state, final IBlockReader world) {
        return (SoulAltarTileEntity)ModBlocks.SOUL_ALTAR_TILE_ENTITY.create();
    }
    
    @Override
    public IParticleData getFlameParticle() {
        return (IParticleData)ModParticles.RED_FLAME.get();
    }
    
    @Override
    public PlayerFavourData.VaultGodType getAssociatedVaultGod() {
        return PlayerFavourData.VaultGodType.MALEVOLENCE;
    }
    
    @Override
    public ActionResultType rightClicked(final BlockState state, final ServerWorld world, final BlockPos pos, final SoulAltarTileEntity tileEntity, final ServerPlayerEntity player, final ItemStack heldStack) {
        if (!tileEntity.initialized()) {
            return ActionResultType.SUCCESS;
        }
        if (player.isCreative()) {
            tileEntity.makeProgress(player, 1, sPlayer -> {});
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.FAIL;
    }
    
    @Override
    protected BlockState getSuccessChestState(final BlockState altarState) {
        final BlockState chestState = super.getSuccessChestState(altarState);
        return (BlockState)chestState.setValue((Property)ChestBlock.FACING, (Comparable)((Direction)chestState.getValue((Property)SoulAltarBlock.FACING)).getOpposite());
    }
}
