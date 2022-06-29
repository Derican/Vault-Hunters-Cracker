
package iskallia.vault.init;

import net.minecraftforge.registries.IForgeRegistry;
import iskallia.vault.loot.LootModifierDestructive;
import net.minecraftforge.registries.IForgeRegistryEntry;
import iskallia.vault.Vault;
import iskallia.vault.loot.LootModifierAutoSmelt;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;

public class ModLootModifiers {
    public static void registerGlobalModifiers(final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
        final IForgeRegistry<GlobalLootModifierSerializer<?>> registry = (IForgeRegistry<GlobalLootModifierSerializer<?>>) event
                .getRegistry();
        registry.register((IForgeRegistryEntry) new LootModifierAutoSmelt.Serializer()
                .setRegistryName(Vault.id("paxel_auto_smelt")));
        registry.register((IForgeRegistryEntry) new LootModifierDestructive.Serializer()
                .setRegistryName(Vault.id("paxel_destructive")));
    }
}
