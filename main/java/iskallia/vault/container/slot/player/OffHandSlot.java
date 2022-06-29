
package iskallia.vault.container.slot.player;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.ResourceLocation;
import com.mojang.datafixers.util.Pair;
import net.minecraft.inventory.IInventory;
import net.minecraft.entity.player.PlayerEntity;
import iskallia.vault.container.slot.ReadOnlySlot;

public class OffHandSlot extends ReadOnlySlot {
    public OffHandSlot(final PlayerEntity player, final int xPosition, final int yPosition) {
        super((IInventory) player.inventory, 40, xPosition, yPosition);
    }

    @OnlyIn(Dist.CLIENT)
    public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
        return (Pair<ResourceLocation, ResourceLocation>) Pair.of((Object) PlayerContainer.BLOCK_ATLAS,
                (Object) PlayerContainer.EMPTY_ARMOR_SLOT_SHIELD);
    }
}
