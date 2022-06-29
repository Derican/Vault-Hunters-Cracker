// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.integration;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effect;
import net.minecraft.item.SuspiciousStewItem;
import tfar.dankstorage.utils.Utils;
import tfar.dankstorage.item.DankItem;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;

public class IntegrationDankStorage
{
    @SubscribeEvent
    public static void onStewFinish(final LivingEntityUseItemEvent.Finish event) {
        final ItemStack dank = event.getItem();
        if (!(dank.getItem() instanceof DankItem) || !Utils.isConstruction(dank)) {
            return;
        }
        final ItemStack dankUsedStack = Utils.getItemStackInSelectedSlot(dank);
        if (!(dankUsedStack.getItem() instanceof SuspiciousStewItem)) {
            return;
        }
        final CompoundNBT tag = dankUsedStack.getTag();
        if (tag != null && tag.contains("Effects", 9)) {
            final ListNBT effectList = tag.getList("Effects", 10);
            for (int i = 0; i < effectList.size(); ++i) {
                int duration = 160;
                final CompoundNBT effectTag = effectList.getCompound(i);
                if (effectTag.contains("EffectDuration", 3)) {
                    duration = effectTag.getInt("EffectDuration");
                }
                final Effect effect = Effect.byId((int)effectTag.getByte("EffectId"));
                if (effect != null) {
                    event.getEntityLiving().addEffect(new EffectInstance(effect, duration));
                }
            }
        }
    }
}
