// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.attribute;

import java.util.Random;
import net.minecraft.nbt.CompoundNBT;

public class IntegerAttribute extends NumberAttribute<Integer>
{
    public IntegerAttribute() {
    }
    
    public IntegerAttribute(final VAttribute.Modifier<Integer> modifier) {
        super(modifier);
    }
    
    @Override
    public void write(final CompoundNBT nbt) {
        nbt.putInt("BaseValue", (int)this.getBaseValue());
    }
    
    @Override
    public void read(final CompoundNBT nbt) {
        this.setBaseValue(nbt.getInt("BaseValue"));
    }
    
    public static Generator generator() {
        return new Generator();
    }
    
    public static Generator.Operator of(final Type type) {
        return new Generator.Operator(type);
    }
    
    public static class Generator extends NumberAttribute.Generator<Integer, Operator>
    {
        @Override
        public Integer getDefaultValue(final Random random) {
            return 0;
        }
        
        public static Operator of(final Type type) {
            return new Operator(type);
        }
        
        public static class Operator extends NumberAttribute.Generator.Operator<Integer>
        {
            public Operator(final Type type) {
                super(type);
            }
            
            @Override
            public Integer apply(final Integer value, final Integer modifier) {
                if (this.getType() == Type.SET) {
                    return modifier;
                }
                if (this.getType() == Type.ADD) {
                    return value + modifier;
                }
                if (this.getType() == Type.MULTIPLY) {
                    return value * modifier;
                }
                return value;
            }
        }
    }
}
