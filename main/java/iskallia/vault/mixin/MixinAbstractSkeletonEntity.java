// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ AbstractSkeletonEntity.class })
public class MixinAbstractSkeletonEntity
{
    @Redirect(method = { "attackEntityWithRangedAttack" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addEntity(Lnet/minecraft/entity/Entity;)Z"))
    public boolean applySkeletonDamage(final World world, final Entity entityIn) {
        final AbstractSkeletonEntity shooter = (AbstractSkeletonEntity)this;
        final AbstractArrowEntity shot = (AbstractArrowEntity)entityIn;
        final double dmg = shooter.getAttributeValue(Attributes.ATTACK_DAMAGE);
        shot.setBaseDamage(dmg + 1.0 + shooter.getCommandSenderWorld().getDifficulty().getId() * 0.11);
        final int power = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER_ARROWS, (LivingEntity)shooter);
        if (power > 0) {
            shot.setBaseDamage(shot.getBaseDamage() + (power + 1) * 0.5);
        }
        return world.addFreshEntity(entityIn);
    }
}
