
package iskallia.vault.block;

import net.minecraft.tileentity.TileEntity;
import iskallia.vault.block.base.FillableAltarTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import iskallia.vault.util.EntityHelper;
import net.minecraft.util.ActionResultType;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import javax.annotation.Nonnull;
import net.minecraft.state.Property;
import net.minecraft.util.Direction;
import net.minecraft.item.BlockItemUseContext;
import iskallia.vault.world.data.PlayerFavourData;
import iskallia.vault.init.ModParticles;
import net.minecraft.particles.IParticleData;
import iskallia.vault.init.ModBlocks;
import net.minecraft.world.IBlockReader;
import net.minecraft.block.BlockState;
import iskallia.vault.block.entity.BloodAltarTileEntity;
import iskallia.vault.block.base.FillableAltarBlock;

public class BloodAltarBlock extends FillableAltarBlock<BloodAltarTileEntity> {
    @Override
    public BloodAltarTileEntity createTileEntity(final BlockState state, final IBlockReader world) {
        return (BloodAltarTileEntity) ModBlocks.BLOOD_ALTAR_TILE_ENTITY.create();
    }

    @Override
    public IParticleData getFlameParticle() {
        return (IParticleData) ModParticles.GREEN_FLAME.get();
    }

    @Override
    public PlayerFavourData.VaultGodType getAssociatedVaultGod() {
        return PlayerFavourData.VaultGodType.BENEVOLENT;
    }

    @Nonnull
    @Override
    public BlockState getStateForPlacement(final BlockItemUseContext context) {
        final BlockState state = super.getStateForPlacement(context);
        return (BlockState) state.setValue((Property) BloodAltarBlock.FACING,
                (Comparable) ((Direction) state.getValue((Property) BloodAltarBlock.FACING)).getOpposite());
    }

    @Override
    public ActionResultType rightClicked(final BlockState state, final ServerWorld world, final BlockPos pos,
            final BloodAltarTileEntity tileEntity, final ServerPlayerEntity player, final ItemStack heldStack) {
        if (!tileEntity.initialized()) {
            return ActionResultType.SUCCESS;
        }
        if (player.isCreative()) {
            tileEntity.makeProgress(player, 1, sPlayer -> {
            });
            return ActionResultType.SUCCESS;
        }
        EntityHelper.changeHealth((LivingEntity) player, -2);
        tileEntity.makeProgress(player, 1, sPlayer -> {
            final PlayerFavourData data = PlayerFavourData.get(sPlayer.getLevel());
            if (BloodAltarBlock.rand.nextFloat() < FillableAltarBlock.getFavourChance((PlayerEntity) sPlayer,
                    PlayerFavourData.VaultGodType.BENEVOLENT)) {
                final PlayerFavourData.VaultGodType vg = this.getAssociatedVaultGod();
                if (data.addFavour((PlayerEntity) sPlayer, vg, 1)) {
                    data.addFavour((PlayerEntity) sPlayer, vg.getOther(BloodAltarBlock.rand), -1);
                    FillableAltarBlock.playFavourInfo(sPlayer);
                }
            }
            return;
        });
        return ActionResultType.SUCCESS;
    }
}
