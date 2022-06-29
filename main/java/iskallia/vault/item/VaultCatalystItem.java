// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.item;

import iskallia.vault.item.catalyst.SingleModifierOutcome;
import net.minecraft.nbt.INBT;
import com.mojang.serialization.Codec;
import iskallia.vault.util.CodecUtils;
import java.util.Optional;
import java.util.function.Function;
import iskallia.vault.item.crystal.CrystalData;
import java.util.ArrayList;
import iskallia.vault.item.crystal.VaultCrystalItem;
import net.minecraft.nbt.CompoundNBT;
import iskallia.vault.item.catalyst.CompoundModifierOutcome;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import iskallia.vault.init.ModConfigs;
import net.minecraft.util.IItemProvider;
import iskallia.vault.init.ModItems;
import java.util.Collections;
import iskallia.vault.util.MiscUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import java.util.Iterator;
import java.util.Collection;
import iskallia.vault.item.catalyst.ModifierRollResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.text.ITextComponent;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemGroup;
import java.util.Random;
import net.minecraft.item.Item;

public class VaultCatalystItem extends Item
{
    private static final Random rand;
    
    public VaultCatalystItem(final ItemGroup group, final ResourceLocation id) {
        super(new Item.Properties().tab(group).stacksTo(1));
        this.setRegistryName(id);
    }
    
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(final ItemStack stack, @Nullable final World world, final List<ITextComponent> tooltip, final ITooltipFlag flag) {
        getModifierRolls(stack).ifPresent(result -> {
            if (!result.isEmpty()) {
                final String modifierDesc = String.format("Adds Modifier%s:", (result.size() <= 1) ? "" : "s");
                tooltip.add(StringTextComponent.EMPTY);
                tooltip.add(new StringTextComponent(modifierDesc).withStyle(TextFormatting.GOLD));
                result.iterator();
                final Iterator iterator;
                while (iterator.hasNext()) {
                    final ModifierRollResult outcome = iterator.next();
                    tooltip.addAll(outcome.getTooltipDescription("- ", true));
                }
            }
        });
    }
    
    public void inventoryTick(final ItemStack stack, final World world, final Entity entity, final int itemSlot, final boolean isSelected) {
        if (world.isClientSide()) {
            return;
        }
        if (world instanceof ServerWorld) {
            final ServerPlayerEntity player = (ServerPlayerEntity)entity;
            if (stack.getCount() > 1) {
                while (stack.getCount() > 1) {
                    stack.shrink(1);
                    final ItemStack catalyst = stack.copy();
                    catalyst.setCount(1);
                    MiscUtils.giveItem(player, catalyst);
                }
            }
        }
        final List<ModifierRollResult> results = getModifierRolls(stack).orElse(Collections.emptyList());
        if (results.isEmpty()) {
            setModifierRolls(stack, createRandomModifiers());
        }
        getSeed(stack);
    }
    
    public static ItemStack createRandom() {
        final ItemStack stack = new ItemStack((IItemProvider)ModItems.VAULT_CATALYST);
        setModifierRolls(stack, createRandomModifiers());
        return stack;
    }
    
    private static List<ModifierRollResult> createRandomModifiers() {
        final CompoundModifierOutcome randomOutcome = ModConfigs.VAULT_CRYSTAL_CATALYST.getModifiers();
        if (randomOutcome == null) {
            return Collections.emptyList();
        }
        return randomOutcome.getRolls().stream().map(outcome -> outcome.resolve(VaultCatalystItem.rand)).collect((Collector<? super Object, ?, List<ModifierRollResult>>)Collectors.toList());
    }
    
    public static long getSeed(final ItemStack stack) {
        if (!(stack.getItem() instanceof VaultCatalystItem)) {
            return 0L;
        }
        final CompoundNBT nbt = stack.getOrCreateTag();
        if (!nbt.contains("Seed", 4)) {
            nbt.putLong("Seed", VaultCatalystItem.rand.nextLong());
        }
        return nbt.getLong("Seed");
    }
    
    @Nullable
    public static List<String> getCrystalCombinationModifiers(final ItemStack catalyst, final ItemStack crystal) {
        final CrystalData data = VaultCrystalItem.getData(crystal.copy());
        if (!data.canModifyWithCrafting()) {
            return null;
        }
        final Optional<List<ModifierRollResult>> rollsOpt = getModifierRolls(catalyst);
        if (!rollsOpt.isPresent()) {
            return null;
        }
        final List<ModifierRollResult> rolls = rollsOpt.get();
        final long seed = VaultCrystalItem.getSeed(crystal) ^ getSeed(catalyst);
        final Random rand = new Random(seed);
        for (int i = 0; i < rand.nextInt(32); ++i) {
            rand.nextLong();
        }
        final List<String> newModifiers = new ArrayList<String>();
        for (final ModifierRollResult modifierRoll : rolls) {
            final List<String> usedModifiers = data.getModifiers().stream().map((Function<? super Object, ?>)CrystalData.Modifier::getModifierName).collect((Collector<? super Object, ?, List<String>>)Collectors.toList());
            final String availableModifier = modifierRoll.getModifier(rand, modifierStr -> usedModifiers.contains(modifierStr) || newModifiers.contains(modifierStr));
            if (availableModifier == null) {
                return null;
            }
            if (!data.addCatalystModifier(availableModifier, true, CrystalData.Modifier.Operation.ADD)) {
                return null;
            }
            newModifiers.add(availableModifier);
        }
        return newModifiers;
    }
    
    public static Optional<List<ModifierRollResult>> getModifierRolls(final ItemStack stack) {
        if (!(stack.getItem() instanceof VaultCatalystItem)) {
            return Optional.empty();
        }
        final CompoundNBT tag = stack.getOrCreateTag();
        return CodecUtils.readNBT((com.mojang.serialization.Codec<List<ModifierRollResult>>)ModifierRollResult.CODEC.listOf(), (INBT)tag.getList("modifiers", 10));
    }
    
    public static void setModifierRolls(final ItemStack stack, final List<ModifierRollResult> result) {
        if (!(stack.getItem() instanceof VaultCatalystItem)) {
            return;
        }
        final CompoundNBT tag = stack.getOrCreateTag();
        CodecUtils.writeNBT((com.mojang.serialization.Codec<List<ModifierRollResult>>)ModifierRollResult.CODEC.listOf(), result, nbt -> tag.put("modifiers", nbt));
    }
    
    static {
        rand = new Random();
    }
}
