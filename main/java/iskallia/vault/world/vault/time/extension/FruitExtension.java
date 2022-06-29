// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.world.vault.time.extension;

import net.minecraft.nbt.INBT;
import iskallia.vault.Vault;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import net.minecraft.nbt.CompoundNBT;
import iskallia.vault.item.ItemVaultFruit;
import net.minecraft.util.ResourceLocation;

public class FruitExtension extends TimeExtension
{
    public static final ResourceLocation ID;
    protected ItemVaultFruit fruit;
    
    public FruitExtension() {
    }
    
    public FruitExtension(final ItemVaultFruit fruit) {
        this(FruitExtension.ID, fruit);
    }
    
    public FruitExtension(final ResourceLocation id, final ItemVaultFruit fruit) {
        super(id, fruit.getExtraVaultTicks());
        this.fruit = fruit;
    }
    
    public ItemVaultFruit getFruit() {
        return this.fruit;
    }
    
    @Override
    public CompoundNBT serializeNBT() {
        final CompoundNBT nbt = super.serializeNBT();
        nbt.putString("Fruit", this.getFruit().getRegistryName().toString());
        return nbt;
    }
    
    @Override
    public void deserializeNBT(final CompoundNBT nbt) {
        super.deserializeNBT(nbt);
        this.fruit = Registry.ITEM.getOptional(new ResourceLocation(nbt.getString("Fruit"))).filter(item -> item instanceof ItemVaultFruit).map(item -> item).orElseThrow(() -> {
            Vault.LOGGER.error("Fruit item <" + nbt.getString("Fruit") + "> is not defined.");
            return new IllegalStateException();
        });
    }
    
    static {
        ID = Vault.id("fruit");
    }
}
