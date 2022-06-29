
package iskallia.vault.util;

import net.minecraft.entity.LivingEntity;
import java.util.function.Function;
import net.minecraft.entity.Entity;
import java.util.function.Consumer;

public class DamageUtil {
    public static <T extends Entity> void shotgunAttack(final T e, final Consumer<T> attackFn) {
        shotgunAttackApply(e, entity -> {
            attackFn.accept(entity);
            return null;
        });
    }

    public static <T extends Entity, R> R shotgunAttackApply(final T e, final Function<T, R> attackFn) {
        final int prevHurtTicks = e.invulnerableTime;
        if (e instanceof LivingEntity) {
            final LivingEntity le = (LivingEntity) e;
            final float prevDamage = le.lastHurt;
            e.invulnerableTime = 0;
            le.lastHurt = 0.0f;
            try {
                return attackFn.apply(e);
            } finally {
                e.invulnerableTime = prevHurtTicks;
                le.lastHurt = prevDamage;
            }
        }
        e.invulnerableTime = 0;
        try {
            return attackFn.apply(e);
        } finally {
            e.invulnerableTime = prevHurtTicks;
        }
    }
}
