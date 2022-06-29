// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.container.slot.player;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import javax.annotation.Nullable;
import net.minecraft.inventory.container.PlayerContainer;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.inventory.container.Slot;

public class PlayerArmorEditSlot extends Slot
{
    private static final ResourceLocation[] ARMOR_SLOT_TEXTURES;
    private final PlayerInventory playerInventory;
    private final EquipmentSlotType slotType;
    
    public PlayerArmorEditSlot(final PlayerInventory inventory, final EquipmentSlotType slotType, final int index, final int xPosition, final int yPosition) {
        super((IInventory)inventory, index, xPosition, yPosition);
        this.playerInventory = inventory;
        this.slotType = slotType;
    }
    
    public int getMaxStackSize() {
        return 1;
    }
    
    public boolean mayPlace(final ItemStack stack) {
        return stack.canEquip(this.slotType, (Entity)this.playerInventory.player);
    }
    
    @Nullable
    @OnlyIn(Dist.CLIENT)
    public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
        if (this.slotType.getType() != EquipmentSlotType.Group.ARMOR) {
            return null;
        }
        return (Pair<ResourceLocation, ResourceLocation>)Pair.of((Object)PlayerContainer.BLOCK_ATLAS, (Object)PlayerArmorEditSlot.ARMOR_SLOT_TEXTURES[this.slotType.getIndex()]);
    }
    
    static {
        ARMOR_SLOT_TEXTURES = new ResourceLocation[] { PlayerContainer.EMPTY_ARMOR_SLOT_BOOTS, PlayerContainer.EMPTY_ARMOR_SLOT_LEGGINGS, PlayerContainer.EMPTY_ARMOR_SLOT_CHESTPLATE, PlayerContainer.EMPTY_ARMOR_SLOT_HELMET };
    }
}
