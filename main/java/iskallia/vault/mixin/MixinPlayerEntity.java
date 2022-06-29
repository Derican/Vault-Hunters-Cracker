
package iskallia.vault.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import iskallia.vault.world.vault.VaultRaid;
import iskallia.vault.world.data.VaultRaidData;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.entity.EntityType;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.entity.LivingEntity;

@Mixin({ PlayerEntity.class })
public abstract class MixinPlayerEntity extends LivingEntity {
    @Shadow
    public abstract ItemStack getItemBySlot(final EquipmentSlotType p0);

    protected MixinPlayerEntity(final EntityType<? extends LivingEntity> type, final World worldIn) {
        super((EntityType) type, worldIn);
    }

    @Redirect(method = {
            "dropInventory" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$RuleKey;)Z"))
    public boolean yes(final GameRules instance, final GameRules.RuleKey<GameRules.BooleanValue> key) {
        final VaultRaid vault = VaultRaidData.get((ServerWorld) this.level).getActiveFor(this.getUUID());
        return vault != null || instance.getBoolean((GameRules.RuleKey) key);
    }
}
