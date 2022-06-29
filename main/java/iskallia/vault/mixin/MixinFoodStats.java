// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import iskallia.vault.world.vault.VaultRaid;
import iskallia.vault.world.vault.player.VaultPlayer;
import iskallia.vault.world.data.VaultRaidData;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.FoodStats;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ FoodStats.class })
public class MixinFoodStats
{
    @Redirect(method = { "tick" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;shouldHeal()Z"))
    public boolean shouldHeal(final PlayerEntity player) {
        if (!player.level.isClientSide) {
            final VaultRaid vault = VaultRaidData.get((ServerWorld)player.level).getActiveFor(player.getUUID());
            if (vault != null) {
                final VaultPlayer vPlayer = vault.getPlayer(player.getUUID()).orElse(null);
                if (vPlayer != null && !vPlayer.getProperties().getBase(VaultRaid.CAN_HEAL).orElse(false)) {
                    return false;
                }
            }
        }
        return player.isHurt();
    }
}
