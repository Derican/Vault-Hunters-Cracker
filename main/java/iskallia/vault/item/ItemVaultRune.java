// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.item;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;

public class ItemVaultRune extends Item
{
    public ItemVaultRune(final ItemGroup group, final ResourceLocation id) {
        super(new Item.Properties().tab(group).fireResistant().stacksTo(1));
        this.setRegistryName(id);
    }
}
