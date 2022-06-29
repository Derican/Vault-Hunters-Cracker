// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.item;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;

public class ItemVaultGem extends Item
{
    public ItemVaultGem(final ItemGroup group, final ResourceLocation id) {
        super(new Item.Properties().tab(group).stacksTo(64));
        this.setRegistryName(id);
    }
}
