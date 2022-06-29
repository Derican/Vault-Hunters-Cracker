// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.block.entity;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import iskallia.vault.init.ModConfigs;
import iskallia.vault.world.vault.VaultRaid;
import java.util.UUID;
import iskallia.vault.world.data.VaultRaidData;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.state.Property;
import net.minecraft.block.DoorBlock;
import iskallia.vault.block.UnknownVaultDoorBlock;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.tileentity.TileEntityType;
import iskallia.vault.init.ModBlocks;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class VaultDoorTileEntity extends TileEntity implements ITickableTileEntity
{
    public VaultDoorTileEntity() {
        super((TileEntityType)ModBlocks.VAULT_DOOR_TILE_ENTITY);
    }
    
    public void tick() {
        if (this.getLevel() == null || this.getLevel().isClientSide) {
            return;
        }
        final ServerWorld world = (ServerWorld)this.getLevel();
        final BlockState state = world.getBlockState(this.getBlockPos());
        if (state.getBlock() instanceof UnknownVaultDoorBlock && state.getValue((Property)DoorBlock.HALF) == DoubleBlockHalf.LOWER) {
            final VaultRaid vault = VaultRaidData.get(world).getAt(world, this.getBlockPos());
            if (vault == null) {
                return;
            }
            final UUID hostUUID = vault.getProperties().getBase(VaultRaid.HOST).orElse(null);
            final BlockState newBlock = ModConfigs.VAULT_LOOTABLES.DOOR.get(world, this.getBlockPos(), world.getRandom(), "DOOR", hostUUID);
            if (newBlock.getBlock() instanceof DoorBlock) {
                final BlockState newState = (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)newBlock.setValue((Property)DoorBlock.FACING, state.getValue((Property)DoorBlock.FACING))).setValue((Property)DoorBlock.OPEN, state.getValue((Property)DoorBlock.OPEN))).setValue((Property)DoorBlock.HINGE, state.getValue((Property)DoorBlock.HINGE))).setValue((Property)DoorBlock.POWERED, state.getValue((Property)DoorBlock.POWERED))).setValue((Property)DoorBlock.HALF, state.getValue((Property)DoorBlock.HALF));
                world.setBlock(this.getBlockPos().above(), Blocks.AIR.defaultBlockState(), 27);
                world.setBlock(this.getBlockPos(), newState, 11);
                world.setBlock(this.getBlockPos().above(), (BlockState)newState.setValue((Property)DoorBlock.HALF, (Comparable)DoubleBlockHalf.UPPER), 11);
            }
            boolean drilling = false;
            for (int i = 1; i < 32; ++i) {
                final BlockPos p = this.getBlockPos().relative(((Direction)state.getValue((Property)DoorBlock.FACING)).getOpposite(), i);
                if (this.getLevel().getBlockState(p).isAir() && this.getLevel().getBlockState(p.above()).isAir()) {
                    if (drilling) {
                        break;
                    }
                }
                else if (!drilling) {
                    drilling = true;
                }
                this.getLevel().setBlockAndUpdate(p, Blocks.AIR.defaultBlockState());
                this.getLevel().setBlockAndUpdate(p.above(), Blocks.AIR.defaultBlockState());
            }
        }
    }
}
