// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.attribute;

import java.util.Optional;
import iskallia.vault.util.gson.IgnoreEmpty;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.Expose;
import java.util.Random;
import net.minecraft.nbt.CompoundNBT;

public class StringAttribute extends PooledAttribute<String>
{
    public StringAttribute() {
    }
    
    public StringAttribute(final VAttribute.Modifier<String> modifier) {
        super(modifier);
    }
    
    @Override
    public void write(final CompoundNBT nbt) {
        nbt.putString("BaseValue", (String)this.getBaseValue());
    }
    
    @Override
    public void read(final CompoundNBT nbt) {
        this.setBaseValue(nbt.getString("BaseValue"));
    }
    
    public static class Generator extends PooledAttribute.Generator<String, Operator>
    {
        @Override
        public String getDefaultValue(final Random random) {
            return "";
        }
        
        public static class Operator extends PooledAttribute.Generator.Operator<String>
        {
            @Expose
            protected String type;
            @Expose
            @JsonAdapter(IgnoreEmpty.StringAdapter.class)
            protected String delimiter;
            @Expose
            @JsonAdapter(IgnoreEmpty.StringAdapter.class)
            protected String regex;
            
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
            public String apply(final String value, final String modifier) {
                if (this.getType() == Type.SET) {
                    return modifier;
                }
                if (this.getType() == Type.APPEND) {
                    return value + modifier;
                }
                if (this.getType() == Type.JOIN) {
                    return value + this.delimiter + modifier;
                }
                if (this.getType() == Type.REPLACE_FIRST) {
                    return value.replaceFirst(this.regex, modifier);
                }
                if (this.getType() == Type.REPLACE_ALL) {
                    return value.replaceAll(this.regex, modifier);
                }
                return value;
            }
        }
    }
    
    public enum Type
    {
        SET, 
        APPEND, 
        JOIN, 
        REPLACE_FIRST, 
        REPLACE_ALL;
        
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
