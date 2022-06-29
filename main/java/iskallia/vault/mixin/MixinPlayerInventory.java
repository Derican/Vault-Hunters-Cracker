// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.mixin;

import net.minecraftforge.items.IItemHandler;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraftforge.items.CapabilityItemHandler;
import iskallia.vault.item.ItemShardPouch;
import iskallia.vault.container.inventory.ShardPouchContainer;
import iskallia.vault.init.ModItems;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import java.util.Iterator;
import iskallia.vault.world.vault.VaultRaid;
import iskallia.vault.world.vault.modifier.DurabilityDamageModifier;
import iskallia.vault.util.PlayerFilter;
import iskallia.vault.world.vault.influence.VaultAttributeInfluence;
import iskallia.vault.world.data.VaultRaidData;
import net.minecraft.world.server.ServerWorld;
import iskallia.vault.Vault;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import java.util.List;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import org.spongepowered.asm.mixin.Mixin;
import iskallia.vault.world.data.InventorySnapshotData;

@Mixin({ PlayerInventory.class })
public class MixinPlayerInventory implements InventorySnapshotData.InventoryAccessor
{
    @Shadow
    @Final
    public PlayerEntity player;
    @Shadow
    @Final
    private List<NonNullList<ItemStack>> compartments;
    
    @ModifyArg(method = { "hurtArmor" }, index = 0, at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;damageItem(ILnet/minecraft/entity/LivingEntity;Ljava/util/function/Consumer;)V"))
    public int limitMaxArmorDamage(int damageAmount) {
        if (this.player.level.dimension() == Vault.VAULT_KEY) {
            damageAmount = Math.min(damageAmount, 5);
        }
        if (this.player.getCommandSenderWorld() instanceof ServerWorld) {
            final ServerWorld sWorld = (ServerWorld)this.player.getCommandSenderWorld();
            final VaultRaid vault = VaultRaidData.get(sWorld).getAt(sWorld, this.player.blockPosition());
            if (vault != null) {
                for (final VaultAttributeInfluence influence : vault.getInfluences().getInfluences(VaultAttributeInfluence.class)) {
                    if (influence.getType() == VaultAttributeInfluence.Type.DURABILITY_DAMAGE && !influence.isMultiplicative()) {
                        damageAmount += (int)influence.getValue();
                    }
                }
                for (final DurabilityDamageModifier modifier : vault.getActiveModifiersFor(PlayerFilter.of(this.player), DurabilityDamageModifier.class)) {
                    damageAmount *= (int)modifier.getDurabilityDamageTakenMultiplier();
                }
                for (final VaultAttributeInfluence influence : vault.getInfluences().getInfluences(VaultAttributeInfluence.class)) {
                    if (influence.getType() == VaultAttributeInfluence.Type.DURABILITY_DAMAGE && influence.isMultiplicative()) {
                        damageAmount *= (int)influence.getValue();
                    }
                }
            }
        }
        return damageAmount;
    }
    
    @Inject(method = { "addItemStackToInventory" }, at = { @At("HEAD") }, cancellable = true)
    public void interceptItemAddition(final ItemStack stack, final CallbackInfoReturnable<Boolean> cir) {
        if (stack.getItem() != ModItems.SOUL_SHARD) {
            return;
        }
        if (this.player.containerMenu instanceof ShardPouchContainer) {
            return;
        }
        final PlayerInventory thisInventory = (PlayerInventory)this;
        ItemStack pouchStack = ItemStack.EMPTY;
        for (int slot = 0; slot < thisInventory.getContainerSize(); ++slot) {
            final ItemStack invStack = thisInventory.getItem(slot);
            if (invStack.getItem() instanceof ItemShardPouch) {
                pouchStack = invStack;
                break;
            }
        }
        if (pouchStack.isEmpty()) {
            return;
        }
        pouchStack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
            final ItemStack remainder = handler.insertItem(0, stack, false);
            stack.setCount(remainder.getCount());
            if (stack.isEmpty()) {
                cir.setReturnValue((Object)true);
            }
        });
    }
    
    @Override
    public int getSize() {
        return this.compartments.stream().mapToInt(NonNullList::size).sum();
    }
}
