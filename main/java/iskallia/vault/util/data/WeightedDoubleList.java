// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.util.data;

import java.util.function.BiConsumer;
import java.util.Iterator;
import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Predicate;
import java.util.Collection;
import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import java.util.List;
import java.util.AbstractList;

public class WeightedDoubleList<T> extends AbstractList<Entry<T>> implements RandomListAccess<T>
{
    @Expose
    private final List<Entry<T>> entries;
    
    public WeightedDoubleList() {
        this.entries = new ArrayList<Entry<T>>();
    }
    
    public WeightedDoubleList<T> add(final T value, final double weight) {
        this.add(new Entry<T>(value, weight));
        return this;
    }
    
    @Override
    public int size() {
        return this.entries.size();
    }
    
    @Override
    public Entry<T> get(final int index) {
        return this.entries.get(index);
    }
    
    @Override
    public boolean add(final Entry<T> entry) {
        return this.entries.add(entry);
    }
    
    @Override
    public Entry<T> remove(final int index) {
        return this.entries.remove(index);
    }
    
    @Override
    public boolean remove(final Object o) {
        return this.entries.remove(o);
    }
    
    @Override
    public boolean removeEntry(final T t) {
        return this.removeIf(entry -> entry.value.equals(t));
    }
    
    @Override
    public boolean removeAll(final Collection<?> c) {
        return this.entries.removeAll(c);
    }
    
    @Override
    public boolean removeIf(final Predicate<? super Entry<T>> filter) {
        return this.entries.removeIf(filter);
    }
    
    public double getTotalWeight() {
        return this.entries.stream().mapToDouble(entry -> entry.weight).sum();
    }
    
    @Nullable
    @Override
    public T getRandom(final Random random) {
        final double totalWeight = this.getTotalWeight();
        if (totalWeight <= 0.0) {
            return null;
        }
        return this.getWeightedAt(random.nextDouble() * totalWeight);
    }
    
    private T getWeightedAt(double weight) {
        for (final Entry<T> e : this.entries) {
            weight -= e.weight;
            if (weight < 0.0) {
                return e.value;
            }
        }
        return null;
    }
    
    public WeightedDoubleList<T> copy() {
        final WeightedDoubleList<T> copy = new WeightedDoubleList<T>();
        this.entries.forEach(entry -> copy.add(entry.value, entry.weight));
        return copy;
    }
    
    public WeightedDoubleList<T> copyFiltered(final Predicate<T> filter) {
        final WeightedDoubleList<T> copy = new WeightedDoubleList<T>();
        this.entries.forEach(entry -> {
            if (filter.test(entry.value)) {
                copy.add(entry);
            }
            return;
        });
        return copy;
    }
    
    public boolean containsValue(final T value) {
        return this.stream().map(entry -> entry.value).anyMatch(t -> t.equals(value));
    }
    
    @Override
    public void forEach(final BiConsumer<T, Number> weightEntryConsumer) {
        this.forEach(entry -> weightEntryConsumer.accept(entry.value, entry.weight));
    }
    
    public static class Entry<T>
    {
        @Expose
        public T value;
        @Expose
        public double weight;
        
        public Entry(final T value, final double weight) {
            this.value = value;
            this.weight = weight;
        }
    }
}
