// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.attribute;

import java.util.Optional;
import com.google.gson.annotations.Expose;

public abstract class NumberAttribute<T> extends PooledAttribute<T>
{
    protected NumberAttribute() {
    }
    
    protected NumberAttribute(final VAttribute.Modifier<T> modifier) {
        super(modifier);
    }
    
    public abstract static class Generator<T, O extends Operator<T>> extends PooledAttribute.Generator<T, O>
    {
        public abstract static class Operator<T> extends PooledAttribute.Generator.Operator<T>
        {
            @Expose
            protected String type;
            
            public Operator(final Type type) {
                this.type = type.name();
            }
            
            public Type getType() {
                return Type.getByName(this.type).orElseThrow(() -> {
                    new IllegalStateException("Unknown type \"" + this.type + "\"");
                    return;
                });
            }
        }
    }
    
    public enum Type
    {
        SET, 
        ADD, 
        MULTIPLY;
        
        public static Optional<Type> getByName(final String name) {
            for (final Type value : values()) {
                if (value.name().equalsIgnoreCase(name)) {
                    return Optional.of(value);
                }
            }
            return Optional.empty();
        }
    }
}
