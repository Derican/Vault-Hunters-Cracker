// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.util.nbt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface NBTSerialize {
    String name() default "";
    
    Class<?> typeOverride() default Object.class;
}
