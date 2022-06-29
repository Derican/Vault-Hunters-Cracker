// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.item;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;

public class ItemVaultKey extends Item
{
    public ItemVaultKey(final ItemGroup group, final ResourceLocation id) {
        super(new Item.Properties().tab(group).stacksTo(64));
        this.setRegistryName(id);
    }
}
