// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.block.BlockState;
import net.minecraft.util.concurrent.TickDelayedTask;
import net.minecraft.block.Blocks;
import iskallia.vault.Vault;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.tileentity.CommandBlockLogic;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.tileentity.CommandBlockTileEntity;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.tileentity.TileEntity;

@Mixin({ CommandBlockTileEntity.class })
public abstract class MixinCommandBlockTileEntity extends TileEntity
{
    @Shadow
    private boolean auto;
    
    @Shadow
    public abstract void setAutomatic(final boolean p0);
    
    @Shadow
    public abstract CommandBlockLogic getCommandBlock();
    
    @Shadow
    public abstract void setPowered(final boolean p0);
    
    public MixinCommandBlockTileEntity(final TileEntityType<?> type) {
        super((TileEntityType)type);
    }
    
    @Inject(method = { "validate" }, at = { @At("RETURN") })
    public void validate(final CallbackInfo ci) {
        if (!this.level.isClientSide && this.level.dimension() == Vault.VAULT_KEY) {
            this.level.getServer().tell((Runnable)new TickDelayedTask(this.level.getServer().getTickCount() + 10, () -> {
                if (!this.level.getBlockTicks().hasScheduledTick(this.getBlockPos(), (Object)Blocks.COMMAND_BLOCK) && this.auto) {
                    this.auto = false;
                    this.setAutomatic(true);
                }
                this.setPowered(false);
                final BlockState state = this.level.getBlockState(this.getBlockPos());
                this.level.getBlockState(this.getBlockPos()).neighborChanged(this.level, this.getBlockPos(), state.getBlock(), this.getBlockPos(), false);
            }));
        }
    }
}
