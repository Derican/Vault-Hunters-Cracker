
package iskallia.vault.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.item.CrossbowItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ CrossbowItem.class })
public class MixinCrossbowItem {
    @Redirect(method = {
            "fireProjectile" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addEntity(Lnet/minecraft/entity/Entity;)Z"))
    private static boolean applyEntityDamage(final World world, final Entity entity) {
        if (entity instanceof AbstractArrowEntity) {
            final Entity shooter = ((AbstractArrowEntity) entity).getOwner();
            if (shooter instanceof LivingEntity && !(shooter instanceof PlayerEntity)) {
                final AttributeModifierManager mgr = ((LivingEntity) shooter).getAttributes();
                if (mgr.hasAttribute(Attributes.ATTACK_DAMAGE)) {
                    ((AbstractArrowEntity) entity).setBaseDamage(mgr.getValue(Attributes.ATTACK_DAMAGE));
                }
            }
        }
        return world.addFreshEntity(entity);
    }
}
