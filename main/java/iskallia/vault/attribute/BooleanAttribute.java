// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.attribute;

import java.util.Optional;
import com.google.gson.annotations.Expose;
import java.util.Random;
import net.minecraft.nbt.CompoundNBT;

public class BooleanAttribute extends PooledAttribute<Boolean>
{
    public BooleanAttribute() {
    }
    
    public BooleanAttribute(final VAttribute.Modifier<Boolean> modifier) {
        super(modifier);
    }
    
    @Override
    public void write(final CompoundNBT nbt) {
        nbt.putBoolean("BaseValue", (boolean)this.getBaseValue());
    }
    
    @Override
    public void read(final CompoundNBT nbt) {
        this.setBaseValue(nbt.getBoolean("BaseValue"));
    }
    
    public static Generator generator() {
        return new Generator();
    }
    
    public static Generator.Operator of(final Type type) {
        return new Generator.Operator(type);
    }
    
    public static class Generator extends PooledAttribute.Generator<Boolean, Operator>
    {
        @Override
        public Boolean getDefaultValue(final Random random) {
            return false;
        }
        
        public static class Operator extends PooledAttribute.Generator.Operator<Boolean>
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
            
            @Override
            public Boolean apply(final Boolean value, final Boolean modifier) {
                if (this.getType() == Type.SET) {
                    return modifier;
                }
                if (this.getType() == Type.AND) {
                    return value & modifier;
                }
                if (this.getType() == Type.OR) {
                    return value | modifier;
                }
                if (this.getType() == Type.XOR) {
                    return value ^ modifier;
                }
                if (this.getType() == Type.NAND) {
                    return !(value & modifier);
                }
                if (this.getType() == Type.NOR) {
                    return !(value | modifier);
                }
                if (this.getType() == Type.XNOR) {
                    return value == modifier;
                }
                return value;
            }
        }
    }
    
    public enum Type
    {
        SET, 
        AND, 
        OR, 
        XOR, 
        NAND, 
        NOR, 
        XNOR;
        
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
