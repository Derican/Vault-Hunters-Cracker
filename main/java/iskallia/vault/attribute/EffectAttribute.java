// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.attribute;

import java.util.Optional;
import java.util.Collection;
import java.util.Random;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.potion.Effect;
import com.google.gson.annotations.Expose;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.ArrayList;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.CompoundNBT;
import java.util.List;

public class EffectAttribute extends PooledAttribute<List<Instance>>
{
    public EffectAttribute() {
    }
    
    public EffectAttribute(final VAttribute.Modifier<List<Instance>> modifier) {
        super(modifier);
    }
    
    @Override
    public void write(final CompoundNBT nbt) {
        if (this.getBaseValue() == null) {
            return;
        }
        final CompoundNBT tag = new CompoundNBT();
        final ListNBT effectsList = new ListNBT();
        ((VAttribute.Instance<List>)this).getBaseValue().forEach(effect -> {
            final CompoundNBT effectTag = new CompoundNBT();
            tag.putString("Id", effect.effect);
            effectsList.add((Object)effectTag);
            return;
        });
        tag.put("Effects", (INBT)effectsList);
        nbt.put("BaseValue", (INBT)tag);
    }
    
    @Override
    public void read(final CompoundNBT nbt) {
        if (!nbt.contains("BaseValue", 10)) {
            ((VAttribute.Instance<ArrayList<Instance>>)this).setBaseValue(new ArrayList<Instance>());
            return;
        }
        final CompoundNBT tag = nbt.getCompound("BaseValue");
        final ListNBT effectsList = tag.getList("Effects", 10);
        this.setBaseValue(effectsList.stream().map(inbt -> (CompoundNBT)inbt).map(effect -> new Instance(tag.getString("Id"))).collect((Collector<? super Object, ?, List<Instance>>)Collectors.toList()));
    }
    
    public static Generator generator() {
        return new Generator();
    }
    
    public static Generator.Operator of(final Type type) {
        return new Generator.Operator(type);
    }
    
    public static class Instance
    {
        @Expose
        protected String effect;
        
        public Instance(final String effect) {
            this.effect = effect;
        }
        
        public Instance(final Effect effect) {
            this(effect.getRegistryName().toString());
        }
        
        public String getId() {
            return this.effect;
        }
        
        public Effect toEffect() {
            return Registry.MOB_EFFECT.getOptional(new ResourceLocation(this.effect)).orElse(null);
        }
        
        @Override
        public String toString() {
            return "Instance{effect='" + this.effect + '\'' + '}';
        }
    }
    
    public static class Generator extends PooledAttribute.Generator<List<EffectAttribute.Instance>, Operator>
    {
        @Override
        public List<EffectAttribute.Instance> getDefaultValue(final Random random) {
            return new ArrayList<EffectAttribute.Instance>();
        }
        
        public static class Operator extends PooledAttribute.Generator.Operator<List<EffectAttribute.Instance>>
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
            public List<EffectAttribute.Instance> apply(final List<EffectAttribute.Instance> value, final List<EffectAttribute.Instance> modifier) {
                if (this.getType() == Type.SET) {
                    return modifier;
                }
                if (this.getType() == Type.MERGE) {
                    final List<EffectAttribute.Instance> res = new ArrayList<EffectAttribute.Instance>(value);
                    res.addAll(modifier);
                    return res;
                }
                return value;
            }
        }
    }
    
    public enum Type
    {
        SET, 
        MERGE;
        
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
