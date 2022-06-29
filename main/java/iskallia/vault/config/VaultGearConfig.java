
package iskallia.vault.config;

import net.minecraft.util.text.Color;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.potion.Potions;
import net.minecraft.potion.Effects;
import iskallia.vault.init.ModItems;
import java.util.LinkedHashMap;
import iskallia.vault.attribute.NumberAttribute;
import iskallia.vault.attribute.PooledAttribute;
import java.util.Optional;
import java.util.Map;
import javax.annotation.Nullable;
import iskallia.vault.item.gear.VaultGearHelper;
import net.minecraft.util.ResourceLocation;
import java.util.function.Supplier;
import iskallia.vault.skill.talent.type.EffectTalent;
import java.util.Collections;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Arrays;
import iskallia.vault.attribute.EffectTalentAttribute;
import iskallia.vault.util.data.WeightedList;
import iskallia.vault.entity.EffectCloudEntity;
import iskallia.vault.attribute.VAttribute;
import iskallia.vault.init.ModAttributes;
import java.util.Random;
import net.minecraft.item.ItemStack;
import iskallia.vault.attribute.BooleanAttribute;
import iskallia.vault.attribute.EffectCloudAttribute;
import iskallia.vault.attribute.EffectAttribute;
import iskallia.vault.attribute.FloatAttribute;
import iskallia.vault.world.data.PlayerFavourData;
import iskallia.vault.attribute.EnumAttribute;
import iskallia.vault.attribute.IntegerAttribute;
import iskallia.vault.attribute.DoubleAttribute;
import iskallia.vault.init.ModConfigs;
import iskallia.vault.item.gear.VaultGear;
import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import java.util.List;

public abstract class VaultGearConfig extends Config {
    @Expose
    public List<Tier> TIERS;

    public VaultGearConfig() {
        this.TIERS = new ArrayList<Tier>();
    }

    public static VaultGearConfig get(final VaultGear.Rarity rarity) {
        switch (rarity) {
            case COMMON: {
                return ModConfigs.VAULT_GEAR_COMMON;
            }
            case RARE: {
                return ModConfigs.VAULT_GEAR_RARE;
            }
            case EPIC: {
                return ModConfigs.VAULT_GEAR_EPIC;
            }
            case UNIQUE:
            case OMEGA: {
                return ModConfigs.VAULT_GEAR_OMEGA;
            }
            default: {
                return ModConfigs.VAULT_GEAR_SCRAPPY;
            }
        }
    }

    @Override
    protected void reset() {
        final Tier tier1 = new Tier();
        final Tier tier2 = new Tier();
        final Tier tier3 = new Tier();
        tier1.NAME = "1";
        tier1.reset();
        tier2.NAME = "2";
        tier2.reset();
        tier3.NAME = "3";
        tier3.reset();
        this.TIERS.add(tier1);
        this.TIERS.add(tier2);
        this.TIERS.add(tier3);
    }

    public static class BaseAttributes {
        @Expose
        public DoubleAttribute.Generator ARMOR;
        @Expose
        public DoubleAttribute.Generator ARMOR_TOUGHNESS;
        @Expose
        public DoubleAttribute.Generator KNOCKBACK_RESISTANCE;
        @Expose
        public DoubleAttribute.Generator ATTACK_DAMAGE;
        @Expose
        public DoubleAttribute.Generator ATTACK_SPEED;
        @Expose
        public IntegerAttribute.Generator DURABILITY;
        @Expose
        public EnumAttribute.Generator<VaultGear.Set> GEAR_SET;
        @Expose
        public EnumAttribute.Generator<PlayerFavourData.VaultGodType> IDOL_TYPE;
        @Expose
        public FloatAttribute.Generator GEAR_LEVEL_CHANCE;
        @Expose
        public IntegerAttribute.Generator GEAR_MAX_LEVEL;
        @Expose
        public IntegerAttribute.Generator GEAR_MODIFIERS_TO_ROLL;
        @Expose
        public IntegerAttribute.Generator MAX_REPAIRS;
        @Expose
        public IntegerAttribute.Generator MIN_VAULT_LEVEL;
        @Expose
        public DoubleAttribute.Generator REACH;
        @Expose
        public FloatAttribute.Generator FEATHER_FEET;
        @Expose
        public EffectAttribute.Generator EFFECT_IMMUNITY;
        @Expose
        public EffectCloudAttribute.Generator EFFECT_CLOUD;
        @Expose
        public FloatAttribute.Generator COOLDOWN_REDUCTION;
        @Expose
        public BooleanAttribute.Generator SOULBOUND;
        @Expose
        public BooleanAttribute.Generator REFORGED;

        public void initialize(final ItemStack stack, final Random random) {
            if (this.ARMOR != null) {
                ModAttributes.ARMOR.create(stack, random, this.ARMOR);
            }
            if (this.ARMOR_TOUGHNESS != null) {
                ModAttributes.ARMOR_TOUGHNESS.create(stack, random, this.ARMOR_TOUGHNESS);
            }
            if (this.KNOCKBACK_RESISTANCE != null) {
                ModAttributes.KNOCKBACK_RESISTANCE.create(stack, random, this.KNOCKBACK_RESISTANCE);
            }
            if (this.ATTACK_DAMAGE != null) {
                ModAttributes.ATTACK_DAMAGE.create(stack, random, this.ATTACK_DAMAGE);
            }
            if (this.ATTACK_SPEED != null) {
                ModAttributes.ATTACK_SPEED.create(stack, random, this.ATTACK_SPEED);
            }
            if (this.DURABILITY != null) {
                ModAttributes.DURABILITY.create(stack, random, this.DURABILITY);
            }
            if (this.GEAR_SET != null) {
                ModAttributes.GEAR_SET.create(stack, random,
                        (VAttribute.Instance.Generator<VaultGear.Set>) this.GEAR_SET);
            }
            if (this.IDOL_TYPE != null) {
                ModAttributes.IDOL_TYPE.create(stack, random,
                        (VAttribute.Instance.Generator<PlayerFavourData.VaultGodType>) this.IDOL_TYPE);
            }
            if (this.GEAR_LEVEL_CHANCE != null) {
                ModAttributes.GEAR_LEVEL_CHANCE.create(stack, random, this.GEAR_LEVEL_CHANCE);
            }
            if (this.GEAR_MAX_LEVEL != null) {
                ModAttributes.GEAR_MAX_LEVEL.create(stack, random, this.GEAR_MAX_LEVEL);
            }
            if (this.GEAR_MODIFIERS_TO_ROLL != null) {
                ModAttributes.GEAR_MODIFIERS_TO_ROLL.create(stack, random, this.GEAR_MODIFIERS_TO_ROLL);
            }
            if (this.MAX_REPAIRS != null) {
                ModAttributes.MAX_REPAIRS.create(stack, random, this.MAX_REPAIRS);
            }
            if (this.MIN_VAULT_LEVEL != null) {
                ModAttributes.MIN_VAULT_LEVEL.create(stack, random, this.MIN_VAULT_LEVEL);
            }
            if (this.REACH != null) {
                ModAttributes.REACH.create(stack, random, this.REACH);
            }
            if (this.FEATHER_FEET != null) {
                ModAttributes.FEATHER_FEET.create(stack, random, this.FEATHER_FEET);
            }
            if (this.EFFECT_IMMUNITY != null) {
                ModAttributes.EFFECT_IMMUNITY.create(stack, random, this.EFFECT_IMMUNITY);
            }
            if (this.EFFECT_CLOUD != null) {
                ModAttributes.EFFECT_CLOUD.create(stack, random, this.EFFECT_CLOUD);
            }
            if (this.COOLDOWN_REDUCTION != null) {
                ModAttributes.COOLDOWN_REDUCTION.create(stack, random, this.COOLDOWN_REDUCTION);
            }
            if (this.SOULBOUND != null) {
                ModAttributes.SOULBOUND.create(stack, random, this.SOULBOUND);
            }
            if (this.REFORGED != null) {
                ModAttributes.REFORGED.create(stack, random, this.REFORGED);
            }
        }

        private BaseAttributes copy() {
            final BaseAttributes copy = new BaseAttributes();
            copy.ARMOR = this.ARMOR;
            copy.ARMOR_TOUGHNESS = this.ARMOR_TOUGHNESS;
            copy.KNOCKBACK_RESISTANCE = this.KNOCKBACK_RESISTANCE;
            copy.ATTACK_DAMAGE = this.ATTACK_DAMAGE;
            copy.ATTACK_SPEED = this.ATTACK_SPEED;
            copy.DURABILITY = this.DURABILITY;
            copy.GEAR_SET = this.GEAR_SET;
            copy.IDOL_TYPE = this.IDOL_TYPE;
            copy.GEAR_LEVEL_CHANCE = this.GEAR_LEVEL_CHANCE;
            copy.GEAR_MAX_LEVEL = this.GEAR_MAX_LEVEL;
            copy.GEAR_MODIFIERS_TO_ROLL = this.GEAR_MODIFIERS_TO_ROLL;
            copy.MAX_REPAIRS = this.MAX_REPAIRS;
            copy.MIN_VAULT_LEVEL = this.MIN_VAULT_LEVEL;
            copy.REACH = this.REACH;
            copy.FEATHER_FEET = this.FEATHER_FEET;
            copy.EFFECT_IMMUNITY = this.EFFECT_IMMUNITY;
            copy.EFFECT_CLOUD = this.EFFECT_CLOUD;
            copy.COOLDOWN_REDUCTION = this.COOLDOWN_REDUCTION;
            copy.SOULBOUND = this.SOULBOUND;
            return copy;
        }
    }

    public static class BaseModifiers {
        @Expose
        public WeightedList.Entry<DoubleAttribute.Generator> ADD_ARMOR;
        @Expose
        public WeightedList.Entry<DoubleAttribute.Generator> ADD_ARMOR_2;
        @Expose
        public WeightedList.Entry<DoubleAttribute.Generator> ADD_ARMOR_TOUGHNESS;
        @Expose
        public WeightedList.Entry<DoubleAttribute.Generator> ADD_ARMOR_TOUGHNESS_2;
        @Expose
        public WeightedList.Entry<DoubleAttribute.Generator> ADD_KNOCKBACK_RESISTANCE;
        @Expose
        public WeightedList.Entry<DoubleAttribute.Generator> ADD_KNOCKBACK_RESISTANCE_2;
        @Expose
        public WeightedList.Entry<DoubleAttribute.Generator> ADD_ATTACK_DAMAGE;
        @Expose
        public WeightedList.Entry<DoubleAttribute.Generator> ADD_ATTACK_DAMAGE_2;
        @Expose
        public WeightedList.Entry<DoubleAttribute.Generator> ADD_ATTACK_SPEED;
        @Expose
        public WeightedList.Entry<DoubleAttribute.Generator> ADD_ATTACK_SPEED_2;
        @Expose
        public WeightedList.Entry<IntegerAttribute.Generator> ADD_DURABILITY;
        @Expose
        public WeightedList.Entry<IntegerAttribute.Generator> ADD_DURABILITY_2;
        @Expose
        public WeightedList.Entry<FloatAttribute.Generator> EXTRA_LEECH_RATIO;
        @Expose
        public WeightedList.Entry<FloatAttribute.Generator> ADD_EXTRA_LEECH_RATIO;
        @Expose
        public WeightedList.Entry<FloatAttribute.Generator> EXTRA_RESISTANCE;
        @Expose
        public WeightedList.Entry<FloatAttribute.Generator> ADD_EXTRA_RESISTANCE;
        @Expose
        public WeightedList.Entry<FloatAttribute.Generator> EXTRA_PARRY_CHANCE;
        @Expose
        public WeightedList.Entry<FloatAttribute.Generator> ADD_EXTRA_PARRY_CHANCE;
        @Expose
        public WeightedList.Entry<FloatAttribute.Generator> EXTRA_HEALTH;
        @Expose
        public WeightedList.Entry<FloatAttribute.Generator> ADD_EXTRA_HEALTH;
        @Expose
        public WeightedList.Entry<EffectTalentAttribute.Generator> EXTRA_EFFECTS;
        @Expose
        public WeightedList.Entry<DoubleAttribute.Generator> ADD_REACH;
        @Expose
        public WeightedList.Entry<DoubleAttribute.Generator> ADD_REACH_2;
        @Expose
        public WeightedList.Entry<FloatAttribute.Generator> ADD_COOLDOWN_REDUCTION;
        @Expose
        public WeightedList.Entry<FloatAttribute.Generator> ADD_COOLDOWN_REDUCTION_2;
        @Expose
        public WeightedList.Entry<IntegerAttribute.Generator> ADD_MIN_VAULT_LEVEL;
        @Expose
        public WeightedList.Entry<EffectCloudAttribute.Generator> ADD_REGEN_CLOUD;
        @Expose
        public WeightedList.Entry<EffectCloudAttribute.Generator> ADD_WEAKENING_CLOUD;
        @Expose
        public WeightedList.Entry<EffectCloudAttribute.Generator> ADD_WITHER_CLOUD;
        @Expose
        public WeightedList.Entry<EffectAttribute.Generator> ADD_POISON_IMMUNITY;
        @Expose
        public WeightedList.Entry<EffectAttribute.Generator> ADD_WITHER_IMMUNITY;
        @Expose
        public WeightedList.Entry<EffectAttribute.Generator> ADD_HUNGER_IMMUNITY;
        @Expose
        public WeightedList.Entry<EffectAttribute.Generator> ADD_MINING_FATIGUE_IMMUNITY;
        @Expose
        public WeightedList.Entry<EffectAttribute.Generator> ADD_SLOWNESS_IMMUNITY;
        @Expose
        public WeightedList.Entry<EffectAttribute.Generator> ADD_WEAKNESS_IMMUNITY;
        @Expose
        public WeightedList.Entry<FloatAttribute.Generator> ADD_FEATHER_FEET;
        @Expose
        public WeightedList.Entry<BooleanAttribute.Generator> ADD_SOULBOUND;
        @Expose
        public WeightedList.Entry<FloatAttribute.Generator> FATAL_STRIKE_CHANCE;
        @Expose
        public WeightedList.Entry<FloatAttribute.Generator> FATAL_STRIKE_DAMAGE;
        @Expose
        public WeightedList.Entry<FloatAttribute.Generator> THORNS_CHANCE;
        @Expose
        public WeightedList.Entry<FloatAttribute.Generator> THORNS_DAMAGE;
        @Expose
        public WeightedList.Entry<FloatAttribute.Generator> CHEST_RARITY;
        @Expose
        public WeightedList.Entry<FloatAttribute.Generator> DAMAGE_INCREASE;
        @Expose
        public WeightedList.Entry<FloatAttribute.Generator> DAMAGE_INCREASE_2;
        @Expose
        public WeightedList.Entry<FloatAttribute.Generator> DAMAGE_ILLAGERS;
        @Expose
        public WeightedList.Entry<FloatAttribute.Generator> DAMAGE_SPIDERS;
        @Expose
        public WeightedList.Entry<FloatAttribute.Generator> DAMAGE_UNDEAD;
        @Expose
        public WeightedList.Entry<IntegerAttribute.Generator> ON_HIT_CHAIN;
        @Expose
        public WeightedList.Entry<IntegerAttribute.Generator> ON_HIT_AOE;
        @Expose
        public WeightedList.Entry<FloatAttribute.Generator> ON_HIT_STUN;

        public void initialize(final ItemStack stack, final Random random) {
            final int rolls = ModAttributes.GEAR_MODIFIERS_TO_ROLL.getOrDefault(stack, 0).getValue(stack);
            if (rolls == 0) {
                return;
            }
            if (rolls >= 0) {
                final List<WeightedList.Entry<? extends VAttribute.Instance.Generator<?>>> generators = this
                        .getGenerators();
                final List<Boolean> existing = Arrays.asList(ModAttributes.ADD_ARMOR.exists(stack),
                        ModAttributes.ADD_ARMOR_2.exists(stack), ModAttributes.ADD_ARMOR_TOUGHNESS.exists(stack),
                        ModAttributes.ADD_ARMOR_TOUGHNESS_2.exists(stack),
                        ModAttributes.ADD_KNOCKBACK_RESISTANCE.exists(stack),
                        ModAttributes.ADD_KNOCKBACK_RESISTANCE_2.exists(stack),
                        ModAttributes.ADD_ATTACK_DAMAGE.exists(stack), ModAttributes.ADD_ATTACK_DAMAGE_2.exists(stack),
                        ModAttributes.ADD_ATTACK_SPEED.exists(stack), ModAttributes.ADD_ATTACK_SPEED_2.exists(stack),
                        ModAttributes.ADD_DURABILITY.exists(stack), ModAttributes.ADD_DURABILITY_2.exists(stack),
                        ModAttributes.EXTRA_LEECH_RATIO.exists(stack),
                        ModAttributes.ADD_EXTRA_LEECH_RATIO.exists(stack), ModAttributes.EXTRA_RESISTANCE.exists(stack),
                        ModAttributes.ADD_EXTRA_RESISTANCE.exists(stack),
                        ModAttributes.EXTRA_PARRY_CHANCE.exists(stack),
                        ModAttributes.ADD_EXTRA_PARRY_CHANCE.exists(stack), ModAttributes.EXTRA_HEALTH.exists(stack),
                        ModAttributes.ADD_EXTRA_HEALTH.exists(stack), ModAttributes.EXTRA_EFFECTS.exists(stack),
                        ModAttributes.ADD_REACH.exists(stack), ModAttributes.ADD_REACH_2.exists(stack),
                        ModAttributes.ADD_COOLDOWN_REDUCTION.exists(stack),
                        ModAttributes.ADD_COOLDOWN_REDUCTION_2.exists(stack),
                        ModAttributes.ADD_MIN_VAULT_LEVEL.exists(stack), ModAttributes.ADD_REGEN_CLOUD.exists(stack),
                        ModAttributes.ADD_WEAKENING_CLOUD.exists(stack), ModAttributes.ADD_WITHER_CLOUD.exists(stack),
                        ModAttributes.ADD_POISON_IMMUNITY.exists(stack),
                        ModAttributes.ADD_WITHER_IMMUNITY.exists(stack),
                        ModAttributes.ADD_HUNGER_IMMUNITY.exists(stack),
                        ModAttributes.ADD_MINING_FATIGUE_IMMUNITY.exists(stack),
                        ModAttributes.ADD_SLOWNESS_IMMUNITY.exists(stack),
                        ModAttributes.ADD_WEAKNESS_IMMUNITY.exists(stack), ModAttributes.ADD_FEATHER_FEET.exists(stack),
                        ModAttributes.ADD_SOULBOUND.exists(stack), ModAttributes.FATAL_STRIKE_CHANCE.exists(stack),
                        ModAttributes.FATAL_STRIKE_DAMAGE.exists(stack), ModAttributes.THORNS_CHANCE.exists(stack),
                        ModAttributes.THORNS_DAMAGE.exists(stack), ModAttributes.CHEST_RARITY.exists(stack),
                        ModAttributes.DAMAGE_INCREASE.exists(stack), ModAttributes.DAMAGE_INCREASE_2.exists(stack),
                        ModAttributes.DAMAGE_ILLAGERS.exists(stack), ModAttributes.DAMAGE_SPIDERS.exists(stack),
                        ModAttributes.DAMAGE_UNDEAD.exists(stack), ModAttributes.ON_HIT_CHAIN.exists(stack),
                        ModAttributes.ON_HIT_AOE.exists(stack), ModAttributes.ON_HIT_STUN.exists(stack));
                int i = 0;
                List<Integer> picked = IntStream.range(0, generators.size()).filter(i -> generators.get(i) != null)
                        .flatMap(i -> IntStream.range(0, generators.get(i).weight).map(j -> i))
                        .filter(i -> !existing.get(i)).boxed()
                        .collect((Collector<? super Integer, ?, List<Integer>>) Collectors.toList());
                Collections.shuffle(picked, random);
                picked = picked.stream().distinct()
                        .collect((Collector<? super Object, ?, List<Integer>>) Collectors.toList());
                int added;
                for (added = Math.min(rolls, picked.size()), i = 0; i < added; ++i) {
                    if (this.ADD_ARMOR == generators.get(picked.get(i))) {
                        ModAttributes.ADD_ARMOR.create(stack, random, this.ADD_ARMOR.value);
                    }
                    if (this.ADD_ARMOR_2 == generators.get(picked.get(i))) {
                        ModAttributes.ADD_ARMOR_2.create(stack, random, this.ADD_ARMOR_2.value);
                    }
                    if (this.ADD_ARMOR_TOUGHNESS == generators.get(picked.get(i))) {
                        ModAttributes.ADD_ARMOR_TOUGHNESS.create(stack, random, this.ADD_ARMOR_TOUGHNESS.value);
                    }
                    if (this.ADD_ARMOR_TOUGHNESS_2 == generators.get(picked.get(i))) {
                        ModAttributes.ADD_ARMOR_TOUGHNESS_2.create(stack, random, this.ADD_ARMOR_TOUGHNESS_2.value);
                    }
                    if (this.ADD_KNOCKBACK_RESISTANCE == generators.get(picked.get(i))) {
                        ModAttributes.ADD_KNOCKBACK_RESISTANCE.create(stack, random,
                                this.ADD_KNOCKBACK_RESISTANCE.value);
                    }
                    if (this.ADD_KNOCKBACK_RESISTANCE_2 == generators.get(picked.get(i))) {
                        ModAttributes.ADD_KNOCKBACK_RESISTANCE_2.create(stack, random,
                                this.ADD_KNOCKBACK_RESISTANCE_2.value);
                    }
                    if (this.ADD_ATTACK_DAMAGE == generators.get(picked.get(i))) {
                        ModAttributes.ADD_ATTACK_DAMAGE.create(stack, random, this.ADD_ATTACK_DAMAGE.value);
                    }
                    if (this.ADD_ATTACK_DAMAGE_2 == generators.get(picked.get(i))) {
                        ModAttributes.ADD_ATTACK_DAMAGE_2.create(stack, random, this.ADD_ATTACK_DAMAGE_2.value);
                    }
                    if (this.ADD_ATTACK_SPEED == generators.get(picked.get(i))) {
                        ModAttributes.ADD_ATTACK_SPEED.create(stack, random, this.ADD_ATTACK_SPEED.value);
                    }
                    if (this.ADD_ATTACK_SPEED_2 == generators.get(picked.get(i))) {
                        ModAttributes.ADD_ATTACK_SPEED_2.create(stack, random, this.ADD_ATTACK_SPEED_2.value);
                    }
                    if (this.ADD_DURABILITY == generators.get(picked.get(i))) {
                        ModAttributes.ADD_DURABILITY.create(stack, random, this.ADD_DURABILITY.value);
                    }
                    if (this.ADD_DURABILITY_2 == generators.get(picked.get(i))) {
                        ModAttributes.ADD_DURABILITY_2.create(stack, random, this.ADD_DURABILITY_2.value);
                    }
                    if (this.EXTRA_LEECH_RATIO == generators.get(picked.get(i))) {
                        ModAttributes.EXTRA_LEECH_RATIO.create(stack, random, this.EXTRA_LEECH_RATIO.value);
                    }
                    if (this.ADD_EXTRA_LEECH_RATIO == generators.get(picked.get(i))) {
                        ModAttributes.ADD_EXTRA_LEECH_RATIO.create(stack, random, this.ADD_EXTRA_LEECH_RATIO.value);
                    }
                    if (this.EXTRA_RESISTANCE == generators.get(picked.get(i))) {
                        ModAttributes.EXTRA_RESISTANCE.create(stack, random, this.EXTRA_RESISTANCE.value);
                    }
                    if (this.ADD_EXTRA_RESISTANCE == generators.get(picked.get(i))) {
                        ModAttributes.ADD_EXTRA_RESISTANCE.create(stack, random, this.ADD_EXTRA_RESISTANCE.value);
                    }
                    if (this.EXTRA_PARRY_CHANCE == generators.get(picked.get(i))) {
                        ModAttributes.EXTRA_PARRY_CHANCE.create(stack, random, this.EXTRA_PARRY_CHANCE.value);
                    }
                    if (this.ADD_EXTRA_PARRY_CHANCE == generators.get(picked.get(i))) {
                        ModAttributes.ADD_EXTRA_PARRY_CHANCE.create(stack, random, this.ADD_EXTRA_PARRY_CHANCE.value);
                    }
                    if (this.EXTRA_HEALTH == generators.get(picked.get(i))) {
                        ModAttributes.EXTRA_HEALTH.create(stack, random, this.EXTRA_HEALTH.value);
                    }
                    if (this.ADD_EXTRA_HEALTH == generators.get(picked.get(i))) {
                        ModAttributes.ADD_EXTRA_HEALTH.create(stack, random, this.ADD_EXTRA_HEALTH.value);
                    }
                    if (this.EXTRA_EFFECTS == generators.get(picked.get(i))) {
                        ModAttributes.EXTRA_EFFECTS.create(stack, random, this.EXTRA_EFFECTS.value);
                    }
                    if (this.ADD_REACH == generators.get(picked.get(i))) {
                        ModAttributes.ADD_REACH.create(stack, random, this.ADD_REACH.value);
                    }
                    if (this.ADD_REACH_2 == generators.get(picked.get(i))) {
                        ModAttributes.ADD_REACH_2.create(stack, random, this.ADD_REACH_2.value);
                    }
                    if (this.ADD_COOLDOWN_REDUCTION == generators.get(picked.get(i))) {
                        ModAttributes.ADD_COOLDOWN_REDUCTION.create(stack, random, this.ADD_COOLDOWN_REDUCTION.value);
                    }
                    if (this.ADD_MIN_VAULT_LEVEL == generators.get(picked.get(i))) {
                        ModAttributes.ADD_MIN_VAULT_LEVEL.create(stack, random, this.ADD_MIN_VAULT_LEVEL.value);
                    }
                    if (this.ADD_COOLDOWN_REDUCTION == generators.get(picked.get(i))) {
                        ModAttributes.ADD_COOLDOWN_REDUCTION.create(stack, random, this.ADD_COOLDOWN_REDUCTION.value);
                    }
                    if (this.ADD_COOLDOWN_REDUCTION_2 == generators.get(picked.get(i))) {
                        ModAttributes.ADD_COOLDOWN_REDUCTION_2.create(stack, random,
                                this.ADD_COOLDOWN_REDUCTION_2.value);
                    }
                    if (this.ADD_REGEN_CLOUD == generators.get(picked.get(i))) {
                        ModAttributes.ADD_REGEN_CLOUD.create(stack, random, this.ADD_REGEN_CLOUD.value);
                    }
                    if (this.ADD_WEAKENING_CLOUD == generators.get(picked.get(i))) {
                        ModAttributes.ADD_WEAKENING_CLOUD.create(stack, random, this.ADD_WEAKENING_CLOUD.value);
                    }
                    if (this.ADD_WITHER_CLOUD == generators.get(picked.get(i))) {
                        ModAttributes.ADD_WITHER_CLOUD.create(stack, random, this.ADD_WITHER_CLOUD.value);
                    }
                    if (this.ADD_POISON_IMMUNITY == generators.get(picked.get(i))) {
                        ModAttributes.ADD_POISON_IMMUNITY.create(stack, random, this.ADD_POISON_IMMUNITY.value);
                    }
                    if (this.ADD_WITHER_IMMUNITY == generators.get(picked.get(i))) {
                        ModAttributes.ADD_WITHER_IMMUNITY.create(stack, random, this.ADD_WITHER_IMMUNITY.value);
                    }
                    if (this.ADD_HUNGER_IMMUNITY == generators.get(picked.get(i))) {
                        ModAttributes.ADD_HUNGER_IMMUNITY.create(stack, random, this.ADD_HUNGER_IMMUNITY.value);
                    }
                    if (this.ADD_MINING_FATIGUE_IMMUNITY == generators.get(picked.get(i))) {
                        ModAttributes.ADD_MINING_FATIGUE_IMMUNITY.create(stack, random,
                                this.ADD_MINING_FATIGUE_IMMUNITY.value);
                    }
                    if (this.ADD_SLOWNESS_IMMUNITY == generators.get(picked.get(i))) {
                        ModAttributes.ADD_SLOWNESS_IMMUNITY.create(stack, random, this.ADD_SLOWNESS_IMMUNITY.value);
                    }
                    if (this.ADD_WEAKNESS_IMMUNITY == generators.get(picked.get(i))) {
                        ModAttributes.ADD_WEAKNESS_IMMUNITY.create(stack, random, this.ADD_WEAKNESS_IMMUNITY.value);
                    }
                    if (this.ADD_FEATHER_FEET == generators.get(picked.get(i))) {
                        ModAttributes.ADD_FEATHER_FEET.create(stack, random, this.ADD_FEATHER_FEET.value);
                    }
                    if (this.ADD_SOULBOUND == generators.get(picked.get(i))) {
                        ModAttributes.ADD_SOULBOUND.create(stack, random, this.ADD_SOULBOUND.value);
                    }
                    if (this.FATAL_STRIKE_CHANCE == generators.get(picked.get(i))) {
                        ModAttributes.FATAL_STRIKE_CHANCE.create(stack, random, this.FATAL_STRIKE_CHANCE.value);
                    }
                    if (this.FATAL_STRIKE_DAMAGE == generators.get(picked.get(i))) {
                        ModAttributes.FATAL_STRIKE_DAMAGE.create(stack, random, this.FATAL_STRIKE_DAMAGE.value);
                    }
                    if (this.THORNS_CHANCE == generators.get(picked.get(i))) {
                        ModAttributes.THORNS_CHANCE.create(stack, random, this.THORNS_CHANCE.value);
                    }
                    if (this.THORNS_DAMAGE == generators.get(picked.get(i))) {
                        ModAttributes.THORNS_DAMAGE.create(stack, random, this.THORNS_DAMAGE.value);
                    }
                    if (this.CHEST_RARITY == generators.get(picked.get(i))) {
                        ModAttributes.CHEST_RARITY.create(stack, random, this.CHEST_RARITY.value);
                    }
                    if (this.DAMAGE_INCREASE == generators.get(picked.get(i))) {
                        ModAttributes.DAMAGE_INCREASE.create(stack, random, this.DAMAGE_INCREASE.value);
                    }
                    if (this.DAMAGE_INCREASE_2 == generators.get(picked.get(i))) {
                        ModAttributes.DAMAGE_INCREASE_2.create(stack, random, this.DAMAGE_INCREASE_2.value);
                    }
                    if (this.DAMAGE_ILLAGERS == generators.get(picked.get(i))) {
                        ModAttributes.DAMAGE_ILLAGERS.create(stack, random, this.DAMAGE_ILLAGERS.value);
                    }
                    if (this.DAMAGE_SPIDERS == generators.get(picked.get(i))) {
                        ModAttributes.DAMAGE_SPIDERS.create(stack, random, this.DAMAGE_SPIDERS.value);
                    }
                    if (this.DAMAGE_UNDEAD == generators.get(picked.get(i))) {
                        ModAttributes.DAMAGE_UNDEAD.create(stack, random, this.DAMAGE_UNDEAD.value);
                    }
                    if (this.ON_HIT_CHAIN == generators.get(picked.get(i))) {
                        ModAttributes.ON_HIT_CHAIN.create(stack, random, this.ON_HIT_CHAIN.value);
                    }
                    if (this.ON_HIT_AOE == generators.get(picked.get(i))) {
                        ModAttributes.ON_HIT_AOE.create(stack, random, this.ON_HIT_AOE.value);
                    }
                    if (this.ON_HIT_STUN == generators.get(picked.get(i))) {
                        ModAttributes.ON_HIT_STUN.create(stack, random, this.ON_HIT_STUN.value);
                    }
                }
                ModAttributes.GEAR_MODIFIERS_TO_ROLL.create(stack, rolls - added);
                return;
            }
            if (ModAttributes.GUARANTEED_MODIFIER_REMOVAL.exists(stack)) {
                final String attrName = ModAttributes.GUARANTEED_MODIFIER_REMOVAL.getBase(stack)
                        .orElseThrow((Supplier<? extends Throwable>) RuntimeException::new);
                final VAttribute<?, ?> modifier = ModAttributes.REGISTRY.get(new ResourceLocation(attrName));
                if (modifier != null && VaultGearHelper.removeAttribute(stack, modifier)) {
                    ModAttributes.GEAR_MODIFIERS_TO_ROLL.create(stack, rolls + 1);
                    VaultGear.decrementLevel(stack, 1);
                    if (Math.random() < ModConfigs.VAULT_GEAR_UTILITIES.getVoidOrbPredefinedRepairCostChance()) {
                        VaultGear.incrementRepairs(stack);
                    }
                }
                VaultGearHelper.removeAttribute(stack, ModAttributes.GUARANTEED_MODIFIER_REMOVAL);
                return;
            }
            final int removed = VaultGearHelper.removeRandomModifiers(stack, Math.abs(rolls));
            if (removed > 0) {
                ModAttributes.GEAR_MODIFIERS_TO_ROLL.create(stack, rolls + removed);
                VaultGear.decrementLevel(stack, removed);
                if (Math.random() < ModConfigs.VAULT_GEAR_UTILITIES.getVoidOrbRepairCostChance()) {
                    VaultGear.incrementRepairs(stack);
                }
            } else {
                ModAttributes.GEAR_MODIFIERS_TO_ROLL.create(stack, 0);
            }
        }

        public BaseModifiers copy() {
            final BaseModifiers copy = new BaseModifiers();
            copy.ADD_ARMOR = this.ADD_ARMOR;
            copy.ADD_ARMOR_2 = this.ADD_ARMOR_2;
            copy.ADD_ARMOR_TOUGHNESS = this.ADD_ARMOR_TOUGHNESS;
            copy.ADD_ARMOR_TOUGHNESS_2 = this.ADD_ARMOR_TOUGHNESS_2;
            copy.ADD_KNOCKBACK_RESISTANCE = this.ADD_KNOCKBACK_RESISTANCE;
            copy.ADD_KNOCKBACK_RESISTANCE_2 = this.ADD_KNOCKBACK_RESISTANCE_2;
            copy.ADD_ATTACK_DAMAGE = this.ADD_ATTACK_DAMAGE;
            copy.ADD_ATTACK_DAMAGE_2 = this.ADD_ATTACK_DAMAGE_2;
            copy.ADD_ATTACK_SPEED = this.ADD_ATTACK_SPEED;
            copy.ADD_ATTACK_SPEED_2 = this.ADD_ATTACK_SPEED_2;
            copy.ADD_DURABILITY = this.ADD_DURABILITY;
            copy.ADD_DURABILITY_2 = this.ADD_DURABILITY_2;
            copy.EXTRA_LEECH_RATIO = this.EXTRA_LEECH_RATIO;
            copy.ADD_EXTRA_LEECH_RATIO = this.ADD_EXTRA_LEECH_RATIO;
            copy.EXTRA_RESISTANCE = this.EXTRA_RESISTANCE;
            copy.ADD_EXTRA_RESISTANCE = this.ADD_EXTRA_RESISTANCE;
            copy.EXTRA_PARRY_CHANCE = this.EXTRA_PARRY_CHANCE;
            copy.ADD_EXTRA_PARRY_CHANCE = this.ADD_EXTRA_PARRY_CHANCE;
            copy.EXTRA_HEALTH = this.EXTRA_HEALTH;
            copy.ADD_EXTRA_HEALTH = this.ADD_EXTRA_HEALTH;
            copy.EXTRA_EFFECTS = this.EXTRA_EFFECTS;
            copy.ADD_REACH = this.ADD_REACH;
            copy.ADD_REACH_2 = this.ADD_REACH_2;
            copy.ADD_COOLDOWN_REDUCTION = this.ADD_COOLDOWN_REDUCTION;
            copy.ADD_COOLDOWN_REDUCTION_2 = this.ADD_COOLDOWN_REDUCTION_2;
            copy.ADD_MIN_VAULT_LEVEL = this.ADD_MIN_VAULT_LEVEL;
            copy.ADD_REGEN_CLOUD = this.ADD_REGEN_CLOUD;
            copy.ADD_WEAKENING_CLOUD = this.ADD_WEAKENING_CLOUD;
            copy.ADD_WITHER_CLOUD = this.ADD_WITHER_CLOUD;
            copy.ADD_POISON_IMMUNITY = this.ADD_POISON_IMMUNITY;
            copy.ADD_WITHER_IMMUNITY = this.ADD_WITHER_IMMUNITY;
            copy.ADD_HUNGER_IMMUNITY = this.ADD_HUNGER_IMMUNITY;
            copy.ADD_MINING_FATIGUE_IMMUNITY = this.ADD_MINING_FATIGUE_IMMUNITY;
            copy.ADD_SLOWNESS_IMMUNITY = this.ADD_SLOWNESS_IMMUNITY;
            copy.ADD_WEAKNESS_IMMUNITY = this.ADD_WEAKNESS_IMMUNITY;
            copy.ADD_FEATHER_FEET = this.ADD_FEATHER_FEET;
            copy.ADD_SOULBOUND = this.ADD_SOULBOUND;
            copy.FATAL_STRIKE_CHANCE = this.FATAL_STRIKE_CHANCE;
            copy.FATAL_STRIKE_DAMAGE = this.FATAL_STRIKE_DAMAGE;
            copy.THORNS_CHANCE = this.THORNS_CHANCE;
            copy.THORNS_DAMAGE = this.THORNS_DAMAGE;
            copy.CHEST_RARITY = this.CHEST_RARITY;
            copy.DAMAGE_INCREASE = this.DAMAGE_INCREASE;
            copy.DAMAGE_INCREASE_2 = this.DAMAGE_INCREASE_2;
            copy.DAMAGE_ILLAGERS = this.DAMAGE_ILLAGERS;
            copy.DAMAGE_SPIDERS = this.DAMAGE_SPIDERS;
            copy.DAMAGE_UNDEAD = this.DAMAGE_UNDEAD;
            copy.ON_HIT_CHAIN = this.ON_HIT_CHAIN;
            copy.ON_HIT_AOE = this.ON_HIT_AOE;
            copy.ON_HIT_STUN = this.ON_HIT_STUN;
            return copy;
        }

        public List<WeightedList.Entry<? extends VAttribute.Instance.Generator<?>>> getGenerators() {
            return (List<WeightedList.Entry<? extends VAttribute.Instance.Generator<?>>>) Arrays.asList(this.ADD_ARMOR,
                    this.ADD_ARMOR_2, this.ADD_ARMOR_TOUGHNESS, this.ADD_ARMOR_TOUGHNESS_2,
                    this.ADD_KNOCKBACK_RESISTANCE, this.ADD_KNOCKBACK_RESISTANCE_2, this.ADD_ATTACK_DAMAGE,
                    this.ADD_ATTACK_DAMAGE_2, this.ADD_ATTACK_SPEED, this.ADD_ATTACK_SPEED_2, this.ADD_DURABILITY,
                    this.ADD_DURABILITY_2, this.EXTRA_LEECH_RATIO, this.ADD_EXTRA_LEECH_RATIO, this.EXTRA_RESISTANCE,
                    this.ADD_EXTRA_RESISTANCE, this.EXTRA_PARRY_CHANCE, this.ADD_EXTRA_PARRY_CHANCE, this.EXTRA_HEALTH,
                    this.ADD_EXTRA_HEALTH, this.EXTRA_EFFECTS, this.ADD_REACH, this.ADD_REACH_2,
                    this.ADD_COOLDOWN_REDUCTION, this.ADD_COOLDOWN_REDUCTION_2, this.ADD_MIN_VAULT_LEVEL,
                    this.ADD_REGEN_CLOUD, this.ADD_WEAKENING_CLOUD, this.ADD_WITHER_CLOUD, this.ADD_POISON_IMMUNITY,
                    this.ADD_WITHER_IMMUNITY, this.ADD_HUNGER_IMMUNITY, this.ADD_MINING_FATIGUE_IMMUNITY,
                    this.ADD_SLOWNESS_IMMUNITY, this.ADD_WEAKNESS_IMMUNITY, this.ADD_FEATHER_FEET, this.ADD_SOULBOUND,
                    this.FATAL_STRIKE_CHANCE, this.FATAL_STRIKE_DAMAGE, this.THORNS_CHANCE, this.THORNS_DAMAGE,
                    this.CHEST_RARITY, this.DAMAGE_INCREASE, this.DAMAGE_INCREASE_2, this.DAMAGE_ILLAGERS,
                    this.DAMAGE_SPIDERS, this.DAMAGE_UNDEAD, this.ON_HIT_CHAIN, this.ON_HIT_AOE, this.ON_HIT_STUN);
        }

        @Nullable
        public WeightedList.Entry<? extends VAttribute.Instance.Generator<?>> getGenerator(
                final VAttribute<?, ?> attribute) {
            WeightedList.Entry<? extends VAttribute.Instance.Generator<?>> generatorEntry = null;
            if (attribute == ModAttributes.ADD_ARMOR) {
                generatorEntry = this.ADD_ARMOR;
            } else if (attribute == ModAttributes.ADD_ARMOR_2) {
                generatorEntry = this.ADD_ARMOR_2;
            } else if (attribute == ModAttributes.ADD_ARMOR_TOUGHNESS) {
                generatorEntry = this.ADD_ARMOR_TOUGHNESS;
            } else if (attribute == ModAttributes.ADD_ARMOR_TOUGHNESS_2) {
                generatorEntry = this.ADD_ARMOR_TOUGHNESS_2;
            } else if (attribute == ModAttributes.ADD_KNOCKBACK_RESISTANCE) {
                generatorEntry = this.ADD_KNOCKBACK_RESISTANCE;
            } else if (attribute == ModAttributes.ADD_KNOCKBACK_RESISTANCE_2) {
                generatorEntry = this.ADD_KNOCKBACK_RESISTANCE_2;
            } else if (attribute == ModAttributes.ADD_ATTACK_DAMAGE) {
                generatorEntry = this.ADD_ATTACK_DAMAGE;
            } else if (attribute == ModAttributes.ADD_ATTACK_DAMAGE_2) {
                generatorEntry = this.ADD_ATTACK_DAMAGE_2;
            } else if (attribute == ModAttributes.ADD_ATTACK_SPEED) {
                generatorEntry = this.ADD_ATTACK_SPEED;
            } else if (attribute == ModAttributes.ADD_ATTACK_SPEED_2) {
                generatorEntry = this.ADD_ATTACK_SPEED_2;
            } else if (attribute == ModAttributes.ADD_DURABILITY) {
                generatorEntry = this.ADD_DURABILITY;
            } else if (attribute == ModAttributes.ADD_DURABILITY_2) {
                generatorEntry = this.ADD_DURABILITY_2;
            } else if (attribute == ModAttributes.EXTRA_LEECH_RATIO) {
                generatorEntry = this.EXTRA_LEECH_RATIO;
            } else if (attribute == ModAttributes.ADD_EXTRA_LEECH_RATIO) {
                generatorEntry = this.ADD_EXTRA_LEECH_RATIO;
            } else if (attribute == ModAttributes.EXTRA_RESISTANCE) {
                generatorEntry = this.EXTRA_RESISTANCE;
            } else if (attribute == ModAttributes.ADD_EXTRA_RESISTANCE) {
                generatorEntry = this.ADD_EXTRA_RESISTANCE;
            } else if (attribute == ModAttributes.EXTRA_PARRY_CHANCE) {
                generatorEntry = this.EXTRA_PARRY_CHANCE;
            } else if (attribute == ModAttributes.ADD_EXTRA_PARRY_CHANCE) {
                generatorEntry = this.ADD_EXTRA_PARRY_CHANCE;
            } else if (attribute == ModAttributes.EXTRA_EFFECTS) {
                generatorEntry = this.EXTRA_EFFECTS;
            } else if (attribute == ModAttributes.ADD_EXTRA_HEALTH) {
                generatorEntry = this.ADD_EXTRA_HEALTH;
            } else if (attribute == ModAttributes.EXTRA_HEALTH) {
                generatorEntry = this.EXTRA_HEALTH;
            } else if (attribute == ModAttributes.ADD_REACH) {
                generatorEntry = this.ADD_REACH;
            } else if (attribute == ModAttributes.ADD_REACH_2) {
                generatorEntry = this.ADD_REACH_2;
            } else if (attribute == ModAttributes.ADD_COOLDOWN_REDUCTION) {
                generatorEntry = this.ADD_COOLDOWN_REDUCTION;
            } else if (attribute == ModAttributes.ADD_COOLDOWN_REDUCTION_2) {
                generatorEntry = this.ADD_COOLDOWN_REDUCTION_2;
            } else if (attribute == ModAttributes.ADD_MIN_VAULT_LEVEL) {
                generatorEntry = this.ADD_MIN_VAULT_LEVEL;
            } else if (attribute == ModAttributes.ADD_REGEN_CLOUD) {
                generatorEntry = this.ADD_REGEN_CLOUD;
            } else if (attribute == ModAttributes.ADD_WEAKENING_CLOUD) {
                generatorEntry = this.ADD_WEAKENING_CLOUD;
            } else if (attribute == ModAttributes.ADD_WITHER_CLOUD) {
                generatorEntry = this.ADD_WITHER_CLOUD;
            } else if (attribute == ModAttributes.ADD_POISON_IMMUNITY) {
                generatorEntry = this.ADD_POISON_IMMUNITY;
            } else if (attribute == ModAttributes.ADD_WITHER_IMMUNITY) {
                generatorEntry = this.ADD_WITHER_IMMUNITY;
            } else if (attribute == ModAttributes.ADD_HUNGER_IMMUNITY) {
                generatorEntry = this.ADD_HUNGER_IMMUNITY;
            } else if (attribute == ModAttributes.ADD_MINING_FATIGUE_IMMUNITY) {
                generatorEntry = this.ADD_MINING_FATIGUE_IMMUNITY;
            } else if (attribute == ModAttributes.ADD_SLOWNESS_IMMUNITY) {
                generatorEntry = this.ADD_SLOWNESS_IMMUNITY;
            } else if (attribute == ModAttributes.ADD_WEAKNESS_IMMUNITY) {
                generatorEntry = this.ADD_WEAKNESS_IMMUNITY;
            } else if (attribute == ModAttributes.ADD_FEATHER_FEET) {
                generatorEntry = this.ADD_FEATHER_FEET;
            } else if (attribute == ModAttributes.ADD_SOULBOUND) {
                generatorEntry = this.ADD_SOULBOUND;
            } else if (attribute == ModAttributes.FATAL_STRIKE_CHANCE) {
                generatorEntry = this.FATAL_STRIKE_CHANCE;
            } else if (attribute == ModAttributes.FATAL_STRIKE_DAMAGE) {
                generatorEntry = this.FATAL_STRIKE_DAMAGE;
            } else if (attribute == ModAttributes.THORNS_CHANCE) {
                generatorEntry = this.THORNS_CHANCE;
            } else if (attribute == ModAttributes.THORNS_DAMAGE) {
                generatorEntry = this.THORNS_DAMAGE;
            } else if (attribute == ModAttributes.CHEST_RARITY) {
                generatorEntry = this.CHEST_RARITY;
            } else if (attribute == ModAttributes.DAMAGE_INCREASE) {
                generatorEntry = this.DAMAGE_INCREASE;
            } else if (attribute == ModAttributes.DAMAGE_INCREASE_2) {
                generatorEntry = this.DAMAGE_INCREASE_2;
            } else if (attribute == ModAttributes.DAMAGE_ILLAGERS) {
                generatorEntry = this.DAMAGE_ILLAGERS;
            } else if (attribute == ModAttributes.DAMAGE_SPIDERS) {
                generatorEntry = this.DAMAGE_SPIDERS;
            } else if (attribute == ModAttributes.DAMAGE_UNDEAD) {
                generatorEntry = this.DAMAGE_UNDEAD;
            } else if (attribute == ModAttributes.ON_HIT_CHAIN) {
                generatorEntry = this.ON_HIT_CHAIN;
            } else if (attribute == ModAttributes.ON_HIT_AOE) {
                generatorEntry = this.ON_HIT_AOE;
            } else if (attribute == ModAttributes.ON_HIT_STUN) {
                generatorEntry = this.ON_HIT_STUN;
            }
            return generatorEntry;
        }
    }

    public static class Tier {
        @Expose
        public String NAME;
        @Expose
        public Map<String, BaseAttributes> BASE_ATTRIBUTES;
        @Expose
        public Map<String, BaseModifiers> BASE_MODIFIERS;

        public String getName() {
            return this.NAME;
        }

        public Optional<BaseAttributes> getAttributes(final ItemStack stack) {
            if (stack.getItem() instanceof VaultGear) {
                return Optional.ofNullable(this.BASE_ATTRIBUTES.get(stack.getItem().getRegistryName().toString()));
            }
            return Optional.empty();
        }

        public Optional<BaseModifiers> getModifiers(final ItemStack stack) {
            if (stack.getItem() instanceof VaultGear) {
                return Optional.ofNullable(this.BASE_MODIFIERS.get(stack.getItem().getRegistryName().toString()));
            }
            return Optional.empty();
        }

        protected void reset() {
            this.resetAttributes();
            this.resetModifiers();
        }

        private void resetAttributes() {
            final BaseAttributes SWORD = new BaseAttributes();
            final BaseAttributes AXE = new BaseAttributes();
            final BaseAttributes DAGGER = new BaseAttributes();
            final BaseAttributes HELMET = new BaseAttributes();
            final BaseAttributes CHESTPLATE = new BaseAttributes();
            final BaseAttributes LEGGINGS = new BaseAttributes();
            final BaseAttributes BOOTS = new BaseAttributes();
            final BaseAttributes ALL_IDOLS = new BaseAttributes();
            SWORD.ATTACK_DAMAGE = (DoubleAttribute.Generator) DoubleAttribute.generator()
                    .add(0.0, PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(6.0, DoubleAttribute.of(NumberAttribute.Type.SET), 2).add(7.0,
                                    DoubleAttribute.of(NumberAttribute.Type.SET), 1))
                    .add(0.0, PooledAttribute.Rolls.ofBinomial(20, 0.5),
                            pool -> pool.add(0.1, DoubleAttribute.of(NumberAttribute.Type.ADD), 1))
                    .add(-0.5, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(DoubleAttribute.of(NumberAttribute.Type.ADD));
            SWORD.ATTACK_DAMAGE = (DoubleAttribute.Generator) DoubleAttribute.generator()
                    .add(0.0, PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(6.0, DoubleAttribute.of(NumberAttribute.Type.SET), 2).add(7.0,
                                    DoubleAttribute.of(NumberAttribute.Type.SET), 1))
                    .add(0.0, PooledAttribute.Rolls.ofBinomial(20, 0.5),
                            pool -> pool.add(0.1, DoubleAttribute.of(NumberAttribute.Type.ADD), 1))
                    .add(-0.5, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(DoubleAttribute.of(NumberAttribute.Type.ADD));
            SWORD.ATTACK_SPEED = (DoubleAttribute.Generator) DoubleAttribute.generator()
                    .add(0.0, PooledAttribute.Rolls.ofBinomial(16, 0.1),
                            pool -> pool.add(0.1, DoubleAttribute.of(NumberAttribute.Type.ADD), 1))
                    .add(-1.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(DoubleAttribute.of(NumberAttribute.Type.MULTIPLY));
            SWORD.DURABILITY = (IntegerAttribute.Generator) IntegerAttribute.generator()
                    .add(0, PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(1561, IntegerAttribute.of(NumberAttribute.Type.SET), 2).add(2031,
                                    IntegerAttribute.of(NumberAttribute.Type.SET), 1))
                    .collect(IntegerAttribute.of(NumberAttribute.Type.SET));
            SWORD.GEAR_LEVEL_CHANCE = (FloatAttribute.Generator) FloatAttribute.generator()
                    .add(0.5f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(FloatAttribute.of(NumberAttribute.Type.SET));
            SWORD.GEAR_MAX_LEVEL = (IntegerAttribute.Generator) IntegerAttribute.generator()
                    .add(15, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(IntegerAttribute.of(NumberAttribute.Type.SET));
            SWORD.MAX_REPAIRS = (IntegerAttribute.Generator) IntegerAttribute.generator()
                    .add(3, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(IntegerAttribute.of(NumberAttribute.Type.SET));
            SWORD.MIN_VAULT_LEVEL = (IntegerAttribute.Generator) IntegerAttribute.generator()
                    .add(5, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(IntegerAttribute.of(NumberAttribute.Type.SET));
            SWORD.REACH = (DoubleAttribute.Generator) DoubleAttribute.generator()
                    .add(0.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(DoubleAttribute.of(NumberAttribute.Type.SET));
            SWORD.FEATHER_FEET = (FloatAttribute.Generator) FloatAttribute.generator()
                    .add(0.0f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(FloatAttribute.of(NumberAttribute.Type.SET));
            SWORD.EFFECT_IMMUNITY = (EffectAttribute.Generator) EffectAttribute.generator()
                    .add(new ArrayList<EffectAttribute.Instance>(), PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(EffectAttribute.of(EffectAttribute.Type.MERGE));
            SWORD.EFFECT_CLOUD = (EffectCloudAttribute.Generator) EffectCloudAttribute.generator()
                    .add(new ArrayList<EffectCloudEntity.Config>(), PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(EffectCloudAttribute.of(EffectCloudAttribute.Type.MERGE));
            SWORD.COOLDOWN_REDUCTION = (FloatAttribute.Generator) FloatAttribute.generator()
                    .add(0.0f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(FloatAttribute.of(NumberAttribute.Type.SET));
            SWORD.SOULBOUND = (BooleanAttribute.Generator) BooleanAttribute.generator()
                    .add(false, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(BooleanAttribute.of(BooleanAttribute.Type.SET));
            AXE.ATTACK_DAMAGE = (DoubleAttribute.Generator) DoubleAttribute.generator()
                    .add(0.0, PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(8.0, DoubleAttribute.of(NumberAttribute.Type.SET), 2).add(9.0,
                                    DoubleAttribute.of(NumberAttribute.Type.SET), 1))
                    .add(0.0, PooledAttribute.Rolls.ofBinomial(20, 0.5),
                            pool -> pool.add(0.1, DoubleAttribute.of(NumberAttribute.Type.ADD), 1))
                    .add(0.5, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(DoubleAttribute.of(NumberAttribute.Type.ADD));
            AXE.ATTACK_SPEED = (DoubleAttribute.Generator) DoubleAttribute.generator()
                    .add(0.0, PooledAttribute.Rolls.ofBinomial(10, 0.1),
                            pool -> pool.add(0.1, DoubleAttribute.of(NumberAttribute.Type.ADD), 1))
                    .add(-1.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(DoubleAttribute.of(NumberAttribute.Type.MULTIPLY));
            AXE.DURABILITY = (IntegerAttribute.Generator) IntegerAttribute.generator()
                    .add(0, PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(1561, IntegerAttribute.of(NumberAttribute.Type.SET), 2).add(2031,
                                    IntegerAttribute.of(NumberAttribute.Type.SET), 1))
                    .collect(IntegerAttribute.of(NumberAttribute.Type.SET));
            AXE.GEAR_LEVEL_CHANCE = (FloatAttribute.Generator) FloatAttribute.generator()
                    .add(0.5f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(FloatAttribute.of(NumberAttribute.Type.SET));
            AXE.GEAR_MAX_LEVEL = (IntegerAttribute.Generator) IntegerAttribute.generator()
                    .add(15, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(IntegerAttribute.of(NumberAttribute.Type.SET));
            AXE.MAX_REPAIRS = (IntegerAttribute.Generator) IntegerAttribute.generator()
                    .add(3, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(IntegerAttribute.of(NumberAttribute.Type.SET));
            AXE.MIN_VAULT_LEVEL = (IntegerAttribute.Generator) IntegerAttribute.generator()
                    .add(5, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(IntegerAttribute.of(NumberAttribute.Type.SET));
            AXE.REACH = (DoubleAttribute.Generator) DoubleAttribute.generator()
                    .add(0.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(DoubleAttribute.of(NumberAttribute.Type.SET));
            AXE.FEATHER_FEET = (FloatAttribute.Generator) FloatAttribute.generator()
                    .add(0.0f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(FloatAttribute.of(NumberAttribute.Type.SET));
            AXE.EFFECT_IMMUNITY = (EffectAttribute.Generator) EffectAttribute.generator()
                    .add(new ArrayList<EffectAttribute.Instance>(), PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(EffectAttribute.of(EffectAttribute.Type.MERGE));
            AXE.EFFECT_CLOUD = (EffectCloudAttribute.Generator) EffectCloudAttribute.generator()
                    .add(new ArrayList<EffectCloudEntity.Config>(), PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(EffectCloudAttribute.of(EffectCloudAttribute.Type.MERGE));
            AXE.COOLDOWN_REDUCTION = (FloatAttribute.Generator) FloatAttribute.generator()
                    .add(0.0f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(FloatAttribute.of(NumberAttribute.Type.SET));
            AXE.SOULBOUND = (BooleanAttribute.Generator) BooleanAttribute.generator()
                    .add(false, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(BooleanAttribute.of(BooleanAttribute.Type.SET));
            DAGGER.ATTACK_DAMAGE = (DoubleAttribute.Generator) DoubleAttribute.generator()
                    .add(0.0, PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(3.0, DoubleAttribute.of(NumberAttribute.Type.SET), 2).add(4.0,
                                    DoubleAttribute.of(NumberAttribute.Type.SET), 1))
                    .add(0.0, PooledAttribute.Rolls.ofBinomial(20, 0.5),
                            pool -> pool.add(0.1, DoubleAttribute.of(NumberAttribute.Type.ADD), 1))
                    .add(-0.5, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(DoubleAttribute.of(NumberAttribute.Type.ADD));
            DAGGER.ATTACK_SPEED = (DoubleAttribute.Generator) DoubleAttribute.generator()
                    .add(0.0, PooledAttribute.Rolls.ofBinomial(20, 0.1),
                            pool -> pool.add(0.1, DoubleAttribute.of(NumberAttribute.Type.ADD), 1))
                    .add(-1.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(DoubleAttribute.of(NumberAttribute.Type.MULTIPLY));
            DAGGER.DURABILITY = (IntegerAttribute.Generator) IntegerAttribute.generator()
                    .add(0, PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(1561, IntegerAttribute.of(NumberAttribute.Type.SET), 2).add(2031,
                                    IntegerAttribute.of(NumberAttribute.Type.SET), 1))
                    .collect(IntegerAttribute.of(NumberAttribute.Type.SET));
            DAGGER.GEAR_LEVEL_CHANCE = (FloatAttribute.Generator) FloatAttribute.generator()
                    .add(0.5f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(FloatAttribute.of(NumberAttribute.Type.SET));
            DAGGER.GEAR_MAX_LEVEL = (IntegerAttribute.Generator) IntegerAttribute.generator()
                    .add(15, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(IntegerAttribute.of(NumberAttribute.Type.SET));
            DAGGER.MAX_REPAIRS = (IntegerAttribute.Generator) IntegerAttribute.generator()
                    .add(3, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(IntegerAttribute.of(NumberAttribute.Type.SET));
            DAGGER.MIN_VAULT_LEVEL = (IntegerAttribute.Generator) IntegerAttribute.generator()
                    .add(5, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(IntegerAttribute.of(NumberAttribute.Type.SET));
            DAGGER.REACH = (DoubleAttribute.Generator) DoubleAttribute.generator()
                    .add(0.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(DoubleAttribute.of(NumberAttribute.Type.SET));
            DAGGER.FEATHER_FEET = (FloatAttribute.Generator) FloatAttribute.generator()
                    .add(0.0f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(FloatAttribute.of(NumberAttribute.Type.SET));
            DAGGER.EFFECT_IMMUNITY = (EffectAttribute.Generator) EffectAttribute.generator()
                    .add(new ArrayList<EffectAttribute.Instance>(), PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(EffectAttribute.of(EffectAttribute.Type.MERGE));
            DAGGER.EFFECT_CLOUD = (EffectCloudAttribute.Generator) EffectCloudAttribute.generator()
                    .add(new ArrayList<EffectCloudEntity.Config>(), PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(EffectCloudAttribute.of(EffectCloudAttribute.Type.MERGE));
            DAGGER.COOLDOWN_REDUCTION = (FloatAttribute.Generator) FloatAttribute.generator()
                    .add(0.0f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(FloatAttribute.of(NumberAttribute.Type.SET));
            DAGGER.SOULBOUND = (BooleanAttribute.Generator) BooleanAttribute.generator()
                    .add(false, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(BooleanAttribute.of(BooleanAttribute.Type.SET));
            HELMET.ARMOR = (DoubleAttribute.Generator) DoubleAttribute.generator()
                    .add(3.0, PooledAttribute.Rolls.ofBinomial(2, 0.5),
                            pool -> pool.add(1.0, DoubleAttribute.of(NumberAttribute.Type.ADD), 1))
                    .collect(DoubleAttribute.of(NumberAttribute.Type.SET));
            HELMET.ARMOR_TOUGHNESS = (DoubleAttribute.Generator) DoubleAttribute.generator()
                    .add(0.0, PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(2.0, DoubleAttribute.of(NumberAttribute.Type.SET), 2).add(3.0,
                                    DoubleAttribute.of(NumberAttribute.Type.SET), 1))
                    .collect(DoubleAttribute.of(NumberAttribute.Type.SET));
            HELMET.KNOCKBACK_RESISTANCE = (DoubleAttribute.Generator) DoubleAttribute.generator()
                    .add(0.0, PooledAttribute.Rolls.ofBinomial(4, 0.4),
                            pool -> pool.add(0.1, DoubleAttribute.of(NumberAttribute.Type.ADD), 1))
                    .collect(DoubleAttribute.of(NumberAttribute.Type.SET));
            HELMET.DURABILITY = (IntegerAttribute.Generator) IntegerAttribute.generator()
                    .add(0, PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(1561, IntegerAttribute.of(NumberAttribute.Type.SET), 2).add(2031,
                                    IntegerAttribute.of(NumberAttribute.Type.SET), 1))
                    .collect(IntegerAttribute.of(NumberAttribute.Type.SET));
            HELMET.GEAR_LEVEL_CHANCE = (FloatAttribute.Generator) FloatAttribute.generator()
                    .add(0.5f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(FloatAttribute.of(NumberAttribute.Type.SET));
            HELMET.GEAR_MAX_LEVEL = (IntegerAttribute.Generator) IntegerAttribute.generator()
                    .add(15, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(IntegerAttribute.of(NumberAttribute.Type.SET));
            HELMET.MAX_REPAIRS = (IntegerAttribute.Generator) IntegerAttribute.generator()
                    .add(3, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(IntegerAttribute.of(NumberAttribute.Type.SET));
            HELMET.MIN_VAULT_LEVEL = (IntegerAttribute.Generator) IntegerAttribute.generator()
                    .add(5, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(IntegerAttribute.of(NumberAttribute.Type.SET));
            HELMET.REACH = (DoubleAttribute.Generator) DoubleAttribute.generator()
                    .add(0.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(DoubleAttribute.of(NumberAttribute.Type.SET));
            HELMET.FEATHER_FEET = (FloatAttribute.Generator) FloatAttribute.generator()
                    .add(0.0f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(FloatAttribute.of(NumberAttribute.Type.SET));
            HELMET.EFFECT_IMMUNITY = (EffectAttribute.Generator) EffectAttribute.generator()
                    .add(new ArrayList<EffectAttribute.Instance>(), PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(EffectAttribute.of(EffectAttribute.Type.MERGE));
            HELMET.EFFECT_CLOUD = (EffectCloudAttribute.Generator) EffectCloudAttribute.generator()
                    .add(new ArrayList<EffectCloudEntity.Config>(), PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(EffectCloudAttribute.of(EffectCloudAttribute.Type.MERGE));
            HELMET.COOLDOWN_REDUCTION = (FloatAttribute.Generator) FloatAttribute.generator()
                    .add(0.0f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(FloatAttribute.of(NumberAttribute.Type.SET));
            HELMET.SOULBOUND = (BooleanAttribute.Generator) BooleanAttribute.generator()
                    .add(false, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(BooleanAttribute.of(BooleanAttribute.Type.SET));
            CHESTPLATE.ARMOR = (DoubleAttribute.Generator) DoubleAttribute.generator()
                    .add(8.0, PooledAttribute.Rolls.ofBinomial(2, 0.5),
                            pool -> pool.add(1.0, DoubleAttribute.of(NumberAttribute.Type.ADD), 1))
                    .collect(DoubleAttribute.of(NumberAttribute.Type.SET));
            CHESTPLATE.ARMOR_TOUGHNESS = (DoubleAttribute.Generator) DoubleAttribute.generator()
                    .add(0.0, PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(2.0, DoubleAttribute.of(NumberAttribute.Type.SET), 2).add(3.0,
                                    DoubleAttribute.of(NumberAttribute.Type.SET), 1))
                    .collect(DoubleAttribute.of(NumberAttribute.Type.SET));
            CHESTPLATE.KNOCKBACK_RESISTANCE = (DoubleAttribute.Generator) DoubleAttribute.generator()
                    .add(0.0, PooledAttribute.Rolls.ofBinomial(4, 0.4),
                            pool -> pool.add(0.1, DoubleAttribute.of(NumberAttribute.Type.ADD), 1))
                    .collect(DoubleAttribute.of(NumberAttribute.Type.SET));
            CHESTPLATE.DURABILITY = (IntegerAttribute.Generator) IntegerAttribute.generator()
                    .add(0, PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(1561, IntegerAttribute.of(NumberAttribute.Type.SET), 2).add(2031,
                                    IntegerAttribute.of(NumberAttribute.Type.SET), 1))
                    .collect(IntegerAttribute.of(NumberAttribute.Type.SET));
            CHESTPLATE.GEAR_LEVEL_CHANCE = (FloatAttribute.Generator) FloatAttribute.generator()
                    .add(0.5f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(FloatAttribute.of(NumberAttribute.Type.SET));
            CHESTPLATE.GEAR_MAX_LEVEL = (IntegerAttribute.Generator) IntegerAttribute.generator()
                    .add(15, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(IntegerAttribute.of(NumberAttribute.Type.SET));
            CHESTPLATE.MAX_REPAIRS = (IntegerAttribute.Generator) IntegerAttribute.generator()
                    .add(3, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(IntegerAttribute.of(NumberAttribute.Type.SET));
            CHESTPLATE.MIN_VAULT_LEVEL = (IntegerAttribute.Generator) IntegerAttribute.generator()
                    .add(5, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(IntegerAttribute.of(NumberAttribute.Type.SET));
            CHESTPLATE.REACH = (DoubleAttribute.Generator) DoubleAttribute.generator()
                    .add(0.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(DoubleAttribute.of(NumberAttribute.Type.SET));
            CHESTPLATE.FEATHER_FEET = (FloatAttribute.Generator) FloatAttribute.generator()
                    .add(0.0f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(FloatAttribute.of(NumberAttribute.Type.SET));
            CHESTPLATE.EFFECT_IMMUNITY = (EffectAttribute.Generator) EffectAttribute.generator()
                    .add(new ArrayList<EffectAttribute.Instance>(), PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(EffectAttribute.of(EffectAttribute.Type.MERGE));
            CHESTPLATE.EFFECT_CLOUD = (EffectCloudAttribute.Generator) EffectCloudAttribute.generator()
                    .add(new ArrayList<EffectCloudEntity.Config>(), PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(EffectCloudAttribute.of(EffectCloudAttribute.Type.MERGE));
            CHESTPLATE.COOLDOWN_REDUCTION = (FloatAttribute.Generator) FloatAttribute.generator()
                    .add(0.0f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(FloatAttribute.of(NumberAttribute.Type.SET));
            CHESTPLATE.SOULBOUND = (BooleanAttribute.Generator) BooleanAttribute.generator()
                    .add(false, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(BooleanAttribute.of(BooleanAttribute.Type.SET));
            LEGGINGS.ARMOR = (DoubleAttribute.Generator) DoubleAttribute.generator()
                    .add(6.0, PooledAttribute.Rolls.ofBinomial(2, 0.5),
                            pool -> pool.add(1.0, DoubleAttribute.of(NumberAttribute.Type.ADD), 1))
                    .collect(DoubleAttribute.of(NumberAttribute.Type.SET));
            LEGGINGS.ARMOR_TOUGHNESS = (DoubleAttribute.Generator) DoubleAttribute.generator()
                    .add(0.0, PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(2.0, DoubleAttribute.of(NumberAttribute.Type.SET), 2).add(3.0,
                                    DoubleAttribute.of(NumberAttribute.Type.SET), 1))
                    .collect(DoubleAttribute.of(NumberAttribute.Type.SET));
            LEGGINGS.KNOCKBACK_RESISTANCE = (DoubleAttribute.Generator) DoubleAttribute.generator()
                    .add(0.0, PooledAttribute.Rolls.ofBinomial(4, 0.4),
                            pool -> pool.add(0.1, DoubleAttribute.of(NumberAttribute.Type.ADD), 1))
                    .collect(DoubleAttribute.of(NumberAttribute.Type.SET));
            LEGGINGS.DURABILITY = (IntegerAttribute.Generator) IntegerAttribute.generator()
                    .add(0, PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(1561, IntegerAttribute.of(NumberAttribute.Type.SET), 2).add(2031,
                                    IntegerAttribute.of(NumberAttribute.Type.SET), 1))
                    .collect(IntegerAttribute.of(NumberAttribute.Type.SET));
            LEGGINGS.GEAR_LEVEL_CHANCE = (FloatAttribute.Generator) FloatAttribute.generator()
                    .add(0.5f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(FloatAttribute.of(NumberAttribute.Type.SET));
            LEGGINGS.GEAR_MAX_LEVEL = (IntegerAttribute.Generator) IntegerAttribute.generator()
                    .add(15, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(IntegerAttribute.of(NumberAttribute.Type.SET));
            LEGGINGS.MAX_REPAIRS = (IntegerAttribute.Generator) IntegerAttribute.generator()
                    .add(3, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(IntegerAttribute.of(NumberAttribute.Type.SET));
            LEGGINGS.MIN_VAULT_LEVEL = (IntegerAttribute.Generator) IntegerAttribute.generator()
                    .add(5, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(IntegerAttribute.of(NumberAttribute.Type.SET));
            LEGGINGS.REACH = (DoubleAttribute.Generator) DoubleAttribute.generator()
                    .add(0.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(DoubleAttribute.of(NumberAttribute.Type.SET));
            LEGGINGS.FEATHER_FEET = (FloatAttribute.Generator) FloatAttribute.generator()
                    .add(0.0f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(FloatAttribute.of(NumberAttribute.Type.SET));
            LEGGINGS.EFFECT_IMMUNITY = (EffectAttribute.Generator) EffectAttribute.generator()
                    .add(new ArrayList<EffectAttribute.Instance>(), PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(EffectAttribute.of(EffectAttribute.Type.MERGE));
            LEGGINGS.EFFECT_CLOUD = (EffectCloudAttribute.Generator) EffectCloudAttribute.generator()
                    .add(new ArrayList<EffectCloudEntity.Config>(), PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(EffectCloudAttribute.of(EffectCloudAttribute.Type.MERGE));
            LEGGINGS.COOLDOWN_REDUCTION = (FloatAttribute.Generator) FloatAttribute.generator()
                    .add(0.0f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(FloatAttribute.of(NumberAttribute.Type.SET));
            LEGGINGS.SOULBOUND = (BooleanAttribute.Generator) BooleanAttribute.generator()
                    .add(false, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(BooleanAttribute.of(BooleanAttribute.Type.SET));
            BOOTS.ARMOR = (DoubleAttribute.Generator) DoubleAttribute.generator()
                    .add(3.0, PooledAttribute.Rolls.ofBinomial(2, 0.5),
                            pool -> pool.add(1.0, DoubleAttribute.of(NumberAttribute.Type.ADD), 1))
                    .collect(DoubleAttribute.of(NumberAttribute.Type.SET));
            BOOTS.ARMOR_TOUGHNESS = (DoubleAttribute.Generator) DoubleAttribute.generator()
                    .add(0.0, PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(2.0, DoubleAttribute.of(NumberAttribute.Type.SET), 2).add(3.0,
                                    DoubleAttribute.of(NumberAttribute.Type.SET), 1))
                    .collect(DoubleAttribute.of(NumberAttribute.Type.SET));
            BOOTS.KNOCKBACK_RESISTANCE = (DoubleAttribute.Generator) DoubleAttribute.generator()
                    .add(0.0, PooledAttribute.Rolls.ofBinomial(4, 0.4),
                            pool -> pool.add(0.1, DoubleAttribute.of(NumberAttribute.Type.ADD), 1))
                    .collect(DoubleAttribute.of(NumberAttribute.Type.SET));
            BOOTS.DURABILITY = (IntegerAttribute.Generator) IntegerAttribute.generator()
                    .add(0, PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(1561, IntegerAttribute.of(NumberAttribute.Type.SET), 2).add(2031,
                                    IntegerAttribute.of(NumberAttribute.Type.SET), 1))
                    .collect(IntegerAttribute.of(NumberAttribute.Type.SET));
            BOOTS.GEAR_LEVEL_CHANCE = (FloatAttribute.Generator) FloatAttribute.generator()
                    .add(0.5f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(FloatAttribute.of(NumberAttribute.Type.SET));
            BOOTS.GEAR_MAX_LEVEL = (IntegerAttribute.Generator) IntegerAttribute.generator()
                    .add(15, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(IntegerAttribute.of(NumberAttribute.Type.SET));
            BOOTS.MAX_REPAIRS = (IntegerAttribute.Generator) IntegerAttribute.generator()
                    .add(3, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(IntegerAttribute.of(NumberAttribute.Type.SET));
            BOOTS.MIN_VAULT_LEVEL = (IntegerAttribute.Generator) IntegerAttribute.generator()
                    .add(5, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(IntegerAttribute.of(NumberAttribute.Type.SET));
            BOOTS.REACH = (DoubleAttribute.Generator) DoubleAttribute.generator()
                    .add(0.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(DoubleAttribute.of(NumberAttribute.Type.SET));
            BOOTS.FEATHER_FEET = (FloatAttribute.Generator) FloatAttribute.generator()
                    .add(0.0f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(FloatAttribute.of(NumberAttribute.Type.SET));
            BOOTS.EFFECT_IMMUNITY = (EffectAttribute.Generator) EffectAttribute.generator()
                    .add(new ArrayList<EffectAttribute.Instance>(), PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(EffectAttribute.of(EffectAttribute.Type.MERGE));
            BOOTS.EFFECT_CLOUD = (EffectCloudAttribute.Generator) EffectCloudAttribute.generator()
                    .add(new ArrayList<EffectCloudEntity.Config>(), PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(EffectCloudAttribute.of(EffectCloudAttribute.Type.MERGE));
            BOOTS.COOLDOWN_REDUCTION = (FloatAttribute.Generator) FloatAttribute.generator()
                    .add(0.0f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(FloatAttribute.of(NumberAttribute.Type.SET));
            BOOTS.SOULBOUND = (BooleanAttribute.Generator) BooleanAttribute.generator()
                    .add(false, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(BooleanAttribute.of(BooleanAttribute.Type.SET));
            ALL_IDOLS.DURABILITY = (IntegerAttribute.Generator) IntegerAttribute.generator()
                    .add(0, PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(1000, IntegerAttribute.of(NumberAttribute.Type.SET), 1))
                    .collect(IntegerAttribute.of(NumberAttribute.Type.SET));
            ALL_IDOLS.GEAR_LEVEL_CHANCE = (FloatAttribute.Generator) FloatAttribute.generator()
                    .add(1.0f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(FloatAttribute.of(NumberAttribute.Type.SET));
            ALL_IDOLS.GEAR_MAX_LEVEL = (IntegerAttribute.Generator) IntegerAttribute.generator()
                    .add(2, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(IntegerAttribute.of(NumberAttribute.Type.SET));
            ALL_IDOLS.REACH = (DoubleAttribute.Generator) DoubleAttribute.generator()
                    .add(0.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(DoubleAttribute.of(NumberAttribute.Type.SET));
            ALL_IDOLS.EFFECT_IMMUNITY = (EffectAttribute.Generator) EffectAttribute.generator()
                    .add(new ArrayList<EffectAttribute.Instance>(), PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(EffectAttribute.of(EffectAttribute.Type.MERGE));
            ALL_IDOLS.EFFECT_CLOUD = (EffectCloudAttribute.Generator) EffectCloudAttribute.generator()
                    .add(new ArrayList<EffectCloudEntity.Config>(), PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(EffectCloudAttribute.of(EffectCloudAttribute.Type.MERGE));
            ALL_IDOLS.COOLDOWN_REDUCTION = (FloatAttribute.Generator) FloatAttribute.generator()
                    .add(0.0f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(FloatAttribute.of(NumberAttribute.Type.SET));
            ALL_IDOLS.SOULBOUND = (BooleanAttribute.Generator) BooleanAttribute.generator()
                    .add(false, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(BooleanAttribute.of(BooleanAttribute.Type.SET));
            final BaseAttributes IDOL_BENEVOLENT = ALL_IDOLS.copy();
            IDOL_BENEVOLENT.IDOL_TYPE = (EnumAttribute.Generator) EnumAttribute
                    .generator(PlayerFavourData.VaultGodType.class)
                    .add(PlayerFavourData.VaultGodType.BENEVOLENT, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(EnumAttribute.of(EnumAttribute.Type.SET));
            final BaseAttributes IDOL_OMNISCIENT = ALL_IDOLS.copy();
            IDOL_OMNISCIENT.IDOL_TYPE = (EnumAttribute.Generator) EnumAttribute
                    .generator(PlayerFavourData.VaultGodType.class)
                    .add(PlayerFavourData.VaultGodType.OMNISCIENT, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(EnumAttribute.of(EnumAttribute.Type.SET));
            final BaseAttributes IDOL_TIMEKEEPER = ALL_IDOLS.copy();
            IDOL_TIMEKEEPER.IDOL_TYPE = (EnumAttribute.Generator) EnumAttribute
                    .generator(PlayerFavourData.VaultGodType.class)
                    .add(PlayerFavourData.VaultGodType.TIMEKEEPER, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(EnumAttribute.of(EnumAttribute.Type.SET));
            final BaseAttributes IDOL_MALEVOLENCE = ALL_IDOLS.copy();
            IDOL_MALEVOLENCE.IDOL_TYPE = (EnumAttribute.Generator) EnumAttribute
                    .generator(PlayerFavourData.VaultGodType.class)
                    .add(PlayerFavourData.VaultGodType.MALEVOLENCE, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(EnumAttribute.of(EnumAttribute.Type.SET));
            IDOL_MALEVOLENCE.ATTACK_DAMAGE = (DoubleAttribute.Generator) DoubleAttribute.generator()
                    .add(0.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(DoubleAttribute.of(NumberAttribute.Type.ADD));
            IDOL_MALEVOLENCE.ATTACK_SPEED = (DoubleAttribute.Generator) DoubleAttribute.generator()
                    .add(0.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                    }).collect(DoubleAttribute.of(NumberAttribute.Type.MULTIPLY));
            (this.BASE_ATTRIBUTES = new LinkedHashMap<String, BaseAttributes>())
                    .put(ModItems.SWORD.getRegistryName().toString().toString(), SWORD);
            this.BASE_ATTRIBUTES.put(ModItems.AXE.getRegistryName().toString(), AXE);
            this.BASE_ATTRIBUTES.put(ModItems.HELMET.getRegistryName().toString(), HELMET);
            this.BASE_ATTRIBUTES.put(ModItems.CHESTPLATE.getRegistryName().toString(), CHESTPLATE);
            this.BASE_ATTRIBUTES.put(ModItems.LEGGINGS.getRegistryName().toString(), LEGGINGS);
            this.BASE_ATTRIBUTES.put(ModItems.BOOTS.getRegistryName().toString(), BOOTS);
            this.BASE_ATTRIBUTES.put(ModItems.IDOL_BENEVOLENT.getRegistryName().toString(), IDOL_BENEVOLENT);
            this.BASE_ATTRIBUTES.put(ModItems.IDOL_OMNISCIENT.getRegistryName().toString(), IDOL_OMNISCIENT);
            this.BASE_ATTRIBUTES.put(ModItems.IDOL_TIMEKEEPER.getRegistryName().toString(), IDOL_TIMEKEEPER);
            this.BASE_ATTRIBUTES.put(ModItems.IDOL_MALEVOLENCE.getRegistryName().toString(), IDOL_MALEVOLENCE);
        }

        private void resetModifiers() {
            final BaseModifiers SWORD = new BaseModifiers();
            final BaseModifiers AXE = new BaseModifiers();
            final BaseModifiers DAGGER = new BaseModifiers();
            final BaseModifiers HELMET = new BaseModifiers();
            final BaseModifiers CHESTPLATE = new BaseModifiers();
            final BaseModifiers LEGGINGS = new BaseModifiers();
            final BaseModifiers BOOTS = new BaseModifiers();
            final BaseModifiers ALL_IDOLS = new BaseModifiers();
            SWORD.ADD_ATTACK_DAMAGE = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(2.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            SWORD.ADD_ATTACK_DAMAGE_2 = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(2.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            SWORD.ADD_ATTACK_SPEED = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(0.3, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            SWORD.ADD_ATTACK_SPEED_2 = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(0.3, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            SWORD.ADD_DURABILITY = new WeightedList.Entry<IntegerAttribute.Generator>(
                    (IntegerAttribute.Generator) IntegerAttribute.generator()
                            .add(500, PooledAttribute.Rolls.ofConstant(1), pool -> {
                            }).collect(IntegerAttribute.of(NumberAttribute.Type.SET)),
                    1);
            SWORD.ADD_DURABILITY_2 = new WeightedList.Entry<IntegerAttribute.Generator>(
                    (IntegerAttribute.Generator) IntegerAttribute.generator()
                            .add(500, PooledAttribute.Rolls.ofConstant(1), pool -> {
                            }).collect(IntegerAttribute.of(NumberAttribute.Type.SET)),
                    1);
            SWORD.EXTRA_LEECH_RATIO = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            SWORD.ADD_EXTRA_LEECH_RATIO = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            SWORD.EXTRA_RESISTANCE = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            SWORD.ADD_EXTRA_RESISTANCE = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            SWORD.EXTRA_PARRY_CHANCE = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.05f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            SWORD.ADD_EXTRA_PARRY_CHANCE = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.05f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            SWORD.EXTRA_HEALTH = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(2.0f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            SWORD.ADD_EXTRA_HEALTH = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(2.0f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            SWORD.EXTRA_EFFECTS = new WeightedList.Entry<EffectTalentAttribute.Generator>(
                    (EffectTalentAttribute.Generator) EffectTalentAttribute.generator()
                            .add(new ArrayList<EffectTalent>(), PooledAttribute.Rolls.ofConstant(1),
                                    pool -> pool
                                            .add(Collections.singletonList(new EffectTalent(0, Effects.DAMAGE_RESISTANCE, 1,
                                                    EffectTalent.Type.ICON_ONLY, EffectTalent.Operator.ADD)),
                                                    EffectTalentAttribute.of(EffectTalentAttribute.Type.SET), 2)
                                            .add(Collections.singletonList(new EffectTalent(0, Effects.DAMAGE_RESISTANCE, 2,
                                                    EffectTalent.Type.ICON_ONLY, EffectTalent.Operator.ADD)),
                                                    EffectTalentAttribute.of(EffectTalentAttribute.Type.SET), 1)
                                            .add(Collections.singletonList(new EffectTalent(0, Effects.LUCK,
                                                    1, EffectTalent.Type.ICON_ONLY, EffectTalent.Operator.ADD)),
                                                    EffectTalentAttribute.of(EffectTalentAttribute.Type.SET), 2)
                                            .add(Collections.singletonList(new EffectTalent(0, Effects.LUCK,
                                                    2, EffectTalent.Type.ICON_ONLY, EffectTalent.Operator.ADD)),
                                                    EffectTalentAttribute.of(EffectTalentAttribute.Type.SET), 1))
                            .collect(EffectTalentAttribute.of(EffectTalentAttribute.Type.MERGE)),
                    1);
            SWORD.ADD_REACH = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(1.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            SWORD.ADD_REACH_2 = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(1.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            SWORD.ADD_COOLDOWN_REDUCTION = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            SWORD.ADD_COOLDOWN_REDUCTION_2 = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            SWORD.ADD_MIN_VAULT_LEVEL = new WeightedList.Entry<IntegerAttribute.Generator>(
                    (IntegerAttribute.Generator) IntegerAttribute.generator()
                            .add(-5, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(IntegerAttribute.of(NumberAttribute.Type.SET)),
                    1);
            SWORD.ADD_REGEN_CLOUD = new WeightedList.Entry<EffectCloudAttribute.Generator>(
                    (EffectCloudAttribute.Generator) EffectCloudAttribute.generator().add(
                            new ArrayList<EffectCloudEntity.Config>(), PooledAttribute.Rolls.ofConstant(1), pool -> {
                                new EffectCloudEntity.Config("Rejuvenate I", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.REGENERATION,
                                                20, 0, false, true)),
                                        20, 2.0f, -1, true, 0.5f);
                                final EffectCloudEntity.Config o;
                                pool.add(Collections.singletonList(o),
                                        EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Rejuvenate II", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.REGENERATION,
                                                20, 1, false, true)),
                                        40, 3.0f, -1, true, 0.5f);
                                final EffectCloudEntity.Config o2;
                                final Object o3;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o3)
                                        .add(Collections.singletonList(o2),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Rejuvenate III", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.REGENERATION,
                                                20, 2, false, true)),
                                        60, 4.0f, -1, true, 0.5f);
                                final EffectCloudEntity.Config o4;
                                final Object o5;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o5)
                                        .add(Collections.singletonList(o4),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                return;
                            }).collect(EffectCloudAttribute.of(EffectCloudAttribute.Type.MERGE)),
                    1);
            SWORD.ADD_WEAKENING_CLOUD = new WeightedList.Entry<EffectCloudAttribute.Generator>(
                    (EffectCloudAttribute.Generator) EffectCloudAttribute.generator().add(
                            new ArrayList<EffectCloudEntity.Config>(), PooledAttribute.Rolls.ofConstant(1), pool -> {
                                new EffectCloudEntity.Config("Weakening I", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WEAKNESS,
                                                20, 0, false, true)),
                                        20, 2.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o6;
                                pool.add(Collections.singletonList(o6),
                                        EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Weakening II", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WEAKNESS,
                                                20, 1, false, true)),
                                        40, 3.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o7;
                                final Object o8;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o8)
                                        .add(Collections.singletonList(o7),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Weakening III", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WEAKNESS,
                                                20, 2, false, true)),
                                        60, 4.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o9;
                                final Object o10;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o10)
                                        .add(Collections.singletonList(o9),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                return;
                            }).collect(EffectCloudAttribute.of(EffectCloudAttribute.Type.MERGE)),
                    1);
            SWORD.ADD_WITHER_CLOUD = new WeightedList.Entry<EffectCloudAttribute.Generator>(
                    (EffectCloudAttribute.Generator) EffectCloudAttribute.generator().add(
                            new ArrayList<EffectCloudEntity.Config>(), PooledAttribute.Rolls.ofConstant(1), pool -> {
                                new EffectCloudEntity.Config("Withering I", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WITHER,
                                                20, 0, false, true)),
                                        20, 2.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o11;
                                pool.add(Collections.singletonList(o11),
                                        EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Withering II", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WITHER,
                                                20, 1, false, true)),
                                        40, 3.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o12;
                                final Object o13;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o13)
                                        .add(Collections.singletonList(o12),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Withering III", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WITHER,
                                                20, 2, false, true)),
                                        60, 4.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o14;
                                final Object o15;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o15)
                                        .add(Collections.singletonList(o14),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                return;
                            }).collect(EffectCloudAttribute.of(EffectCloudAttribute.Type.MERGE)),
                    1);
            SWORD.ADD_WITHER_IMMUNITY = new WeightedList.Entry<EffectAttribute.Generator>(
                    (EffectAttribute.Generator) EffectAttribute.generator().add(
                            new ArrayList<EffectAttribute.Instance>(), PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(
                                    Collections.singletonList(new EffectAttribute.Instance(Effects.WITHER)),
                                    EffectAttribute.of(EffectAttribute.Type.SET), 1))
                            .collect(EffectAttribute.of(EffectAttribute.Type.MERGE)),
                    1);
            SWORD.ADD_POISON_IMMUNITY = new WeightedList.Entry<EffectAttribute.Generator>(
                    (EffectAttribute.Generator) EffectAttribute.generator().add(
                            new ArrayList<EffectAttribute.Instance>(), PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(
                                    Collections.singletonList(new EffectAttribute.Instance(Effects.POISON)),
                                    EffectAttribute.of(EffectAttribute.Type.SET), 1))
                            .collect(EffectAttribute.of(EffectAttribute.Type.MERGE)),
                    1);
            SWORD.ADD_FEATHER_FEET = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.05f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            SWORD.ADD_SOULBOUND = new WeightedList.Entry<BooleanAttribute.Generator>(
                    (BooleanAttribute.Generator) BooleanAttribute.generator()
                            .add(true, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(BooleanAttribute.of(BooleanAttribute.Type.SET)),
                    1);
            AXE.ADD_ATTACK_DAMAGE = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(2.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            AXE.ADD_ATTACK_DAMAGE_2 = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(2.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            AXE.ADD_ATTACK_SPEED = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(0.3, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            AXE.ADD_ATTACK_SPEED_2 = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(0.3, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            AXE.ADD_DURABILITY = new WeightedList.Entry<IntegerAttribute.Generator>(
                    (IntegerAttribute.Generator) IntegerAttribute.generator()
                            .add(500, PooledAttribute.Rolls.ofConstant(1), pool -> {
                            }).collect(IntegerAttribute.of(NumberAttribute.Type.SET)),
                    1);
            AXE.ADD_DURABILITY_2 = new WeightedList.Entry<IntegerAttribute.Generator>(
                    (IntegerAttribute.Generator) IntegerAttribute.generator()
                            .add(500, PooledAttribute.Rolls.ofConstant(1), pool -> {
                            }).collect(IntegerAttribute.of(NumberAttribute.Type.SET)),
                    1);
            AXE.EXTRA_LEECH_RATIO = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            AXE.ADD_EXTRA_LEECH_RATIO = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            AXE.EXTRA_RESISTANCE = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            AXE.ADD_EXTRA_RESISTANCE = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            AXE.EXTRA_PARRY_CHANCE = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.05f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            AXE.ADD_EXTRA_PARRY_CHANCE = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.05f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            AXE.EXTRA_HEALTH = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(2.0f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            AXE.ADD_EXTRA_HEALTH = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(2.0f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            AXE.EXTRA_EFFECTS = new WeightedList.Entry<EffectTalentAttribute.Generator>(
                    (EffectTalentAttribute.Generator) EffectTalentAttribute.generator()
                            .add(new ArrayList<EffectTalent>(), PooledAttribute.Rolls.ofConstant(1),
                                    pool -> pool
                                            .add(Collections.singletonList(new EffectTalent(0, Effects.DAMAGE_RESISTANCE, 1,
                                                    EffectTalent.Type.ICON_ONLY, EffectTalent.Operator.ADD)),
                                                    EffectTalentAttribute.of(EffectTalentAttribute.Type.SET), 2)
                                            .add(Collections.singletonList(new EffectTalent(0, Effects.DAMAGE_RESISTANCE, 2,
                                                    EffectTalent.Type.ICON_ONLY, EffectTalent.Operator.ADD)),
                                                    EffectTalentAttribute.of(EffectTalentAttribute.Type.SET), 1)
                                            .add(Collections.singletonList(new EffectTalent(0, Effects.LUCK,
                                                    1, EffectTalent.Type.ICON_ONLY, EffectTalent.Operator.ADD)),
                                                    EffectTalentAttribute.of(EffectTalentAttribute.Type.SET), 2)
                                            .add(Collections.singletonList(new EffectTalent(0, Effects.LUCK,
                                                    2, EffectTalent.Type.ICON_ONLY, EffectTalent.Operator.ADD)),
                                                    EffectTalentAttribute.of(EffectTalentAttribute.Type.SET), 1))
                            .collect(EffectTalentAttribute.of(EffectTalentAttribute.Type.MERGE)),
                    1);
            AXE.ADD_REACH = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(1.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            AXE.ADD_REACH_2 = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(1.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            AXE.ADD_COOLDOWN_REDUCTION = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            AXE.ADD_COOLDOWN_REDUCTION_2 = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            AXE.ADD_MIN_VAULT_LEVEL = new WeightedList.Entry<IntegerAttribute.Generator>(
                    (IntegerAttribute.Generator) IntegerAttribute.generator()
                            .add(-5, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(IntegerAttribute.of(NumberAttribute.Type.SET)),
                    1);
            AXE.ADD_REGEN_CLOUD = new WeightedList.Entry<EffectCloudAttribute.Generator>(
                    (EffectCloudAttribute.Generator) EffectCloudAttribute.generator().add(
                            new ArrayList<EffectCloudEntity.Config>(), PooledAttribute.Rolls.ofConstant(1), pool -> {
                                new EffectCloudEntity.Config("Rejuvenate I", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.REGENERATION,
                                                20, 0, false, true)),
                                        20, 2.0f, -1, true, 0.5f);
                                final EffectCloudEntity.Config o16;
                                pool.add(Collections.singletonList(o16),
                                        EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Rejuvenate II", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.REGENERATION,
                                                20, 1, false, true)),
                                        40, 3.0f, -1, true, 0.5f);
                                final EffectCloudEntity.Config o17;
                                final Object o18;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o18)
                                        .add(Collections.singletonList(o17),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Rejuvenate III", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.REGENERATION,
                                                20, 2, false, true)),
                                        60, 4.0f, -1, true, 0.5f);
                                final EffectCloudEntity.Config o19;
                                final Object o20;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o20)
                                        .add(Collections.singletonList(o19),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                return;
                            }).collect(EffectCloudAttribute.of(EffectCloudAttribute.Type.MERGE)),
                    1);
            AXE.ADD_WEAKENING_CLOUD = new WeightedList.Entry<EffectCloudAttribute.Generator>(
                    (EffectCloudAttribute.Generator) EffectCloudAttribute.generator().add(
                            new ArrayList<EffectCloudEntity.Config>(), PooledAttribute.Rolls.ofConstant(1), pool -> {
                                new EffectCloudEntity.Config("Weakening I", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WEAKNESS,
                                                20, 0, false, true)),
                                        20, 2.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o21;
                                pool.add(Collections.singletonList(o21),
                                        EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Weakening II", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WEAKNESS,
                                                20, 1, false, true)),
                                        40, 3.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o22;
                                final Object o23;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o23)
                                        .add(Collections.singletonList(o22),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Weakening III", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WEAKNESS,
                                                20, 2, false, true)),
                                        60, 4.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o24;
                                final Object o25;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o25)
                                        .add(Collections.singletonList(o24),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                return;
                            }).collect(EffectCloudAttribute.of(EffectCloudAttribute.Type.MERGE)),
                    1);
            AXE.ADD_WITHER_CLOUD = new WeightedList.Entry<EffectCloudAttribute.Generator>(
                    (EffectCloudAttribute.Generator) EffectCloudAttribute.generator().add(
                            new ArrayList<EffectCloudEntity.Config>(), PooledAttribute.Rolls.ofConstant(1), pool -> {
                                new EffectCloudEntity.Config("Withering I", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WITHER,
                                                20, 0, false, true)),
                                        20, 2.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o26;
                                pool.add(Collections.singletonList(o26),
                                        EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Withering II", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WITHER,
                                                20, 1, false, true)),
                                        40, 3.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o27;
                                final Object o28;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o28)
                                        .add(Collections.singletonList(o27),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Withering III", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WITHER,
                                                20, 2, false, true)),
                                        60, 4.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o29;
                                final Object o30;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o30)
                                        .add(Collections.singletonList(o29),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                return;
                            }).collect(EffectCloudAttribute.of(EffectCloudAttribute.Type.MERGE)),
                    1);
            AXE.ADD_WITHER_IMMUNITY = new WeightedList.Entry<EffectAttribute.Generator>(
                    (EffectAttribute.Generator) EffectAttribute.generator().add(
                            new ArrayList<EffectAttribute.Instance>(), PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(
                                    Collections.singletonList(new EffectAttribute.Instance(Effects.WITHER)),
                                    EffectAttribute.of(EffectAttribute.Type.SET), 1))
                            .collect(EffectAttribute.of(EffectAttribute.Type.MERGE)),
                    1);
            AXE.ADD_POISON_IMMUNITY = new WeightedList.Entry<EffectAttribute.Generator>(
                    (EffectAttribute.Generator) EffectAttribute.generator().add(
                            new ArrayList<EffectAttribute.Instance>(), PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(
                                    Collections.singletonList(new EffectAttribute.Instance(Effects.POISON)),
                                    EffectAttribute.of(EffectAttribute.Type.SET), 1))
                            .collect(EffectAttribute.of(EffectAttribute.Type.MERGE)),
                    1);
            AXE.ADD_FEATHER_FEET = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.05f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            AXE.ADD_SOULBOUND = new WeightedList.Entry<BooleanAttribute.Generator>(
                    (BooleanAttribute.Generator) BooleanAttribute.generator()
                            .add(true, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(BooleanAttribute.of(BooleanAttribute.Type.SET)),
                    1);
            DAGGER.ADD_ATTACK_DAMAGE = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(2.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            DAGGER.ADD_ATTACK_DAMAGE_2 = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(2.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            DAGGER.ADD_ATTACK_SPEED = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(0.3, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            DAGGER.ADD_ATTACK_SPEED_2 = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(0.3, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            DAGGER.ADD_DURABILITY = new WeightedList.Entry<IntegerAttribute.Generator>(
                    (IntegerAttribute.Generator) IntegerAttribute.generator()
                            .add(500, PooledAttribute.Rolls.ofConstant(1), pool -> {
                            }).collect(IntegerAttribute.of(NumberAttribute.Type.SET)),
                    1);
            DAGGER.ADD_DURABILITY_2 = new WeightedList.Entry<IntegerAttribute.Generator>(
                    (IntegerAttribute.Generator) IntegerAttribute.generator()
                            .add(500, PooledAttribute.Rolls.ofConstant(1), pool -> {
                            }).collect(IntegerAttribute.of(NumberAttribute.Type.SET)),
                    1);
            DAGGER.EXTRA_LEECH_RATIO = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            DAGGER.ADD_EXTRA_LEECH_RATIO = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            DAGGER.EXTRA_RESISTANCE = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            DAGGER.ADD_EXTRA_RESISTANCE = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            DAGGER.EXTRA_PARRY_CHANCE = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.05f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            DAGGER.ADD_EXTRA_PARRY_CHANCE = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.05f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            DAGGER.EXTRA_HEALTH = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(2.0f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            DAGGER.ADD_EXTRA_HEALTH = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(2.0f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            DAGGER.EXTRA_EFFECTS = new WeightedList.Entry<EffectTalentAttribute.Generator>(
                    (EffectTalentAttribute.Generator) EffectTalentAttribute.generator()
                            .add(new ArrayList<EffectTalent>(), PooledAttribute.Rolls.ofConstant(1),
                                    pool -> pool
                                            .add(Collections.singletonList(new EffectTalent(0, Effects.DAMAGE_RESISTANCE, 1,
                                                    EffectTalent.Type.ICON_ONLY, EffectTalent.Operator.ADD)),
                                                    EffectTalentAttribute.of(EffectTalentAttribute.Type.SET), 2)
                                            .add(Collections.singletonList(new EffectTalent(0, Effects.DAMAGE_RESISTANCE, 2,
                                                    EffectTalent.Type.ICON_ONLY, EffectTalent.Operator.ADD)),
                                                    EffectTalentAttribute.of(EffectTalentAttribute.Type.SET), 1)
                                            .add(Collections.singletonList(new EffectTalent(0, Effects.LUCK,
                                                    1, EffectTalent.Type.ICON_ONLY, EffectTalent.Operator.ADD)),
                                                    EffectTalentAttribute.of(EffectTalentAttribute.Type.SET), 2)
                                            .add(Collections.singletonList(new EffectTalent(0, Effects.LUCK,
                                                    2, EffectTalent.Type.ICON_ONLY, EffectTalent.Operator.ADD)),
                                                    EffectTalentAttribute.of(EffectTalentAttribute.Type.SET), 1))
                            .collect(EffectTalentAttribute.of(EffectTalentAttribute.Type.MERGE)),
                    1);
            DAGGER.ADD_REACH = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(1.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            DAGGER.ADD_REACH_2 = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(1.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            DAGGER.ADD_COOLDOWN_REDUCTION = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            DAGGER.ADD_COOLDOWN_REDUCTION_2 = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            DAGGER.ADD_MIN_VAULT_LEVEL = new WeightedList.Entry<IntegerAttribute.Generator>(
                    (IntegerAttribute.Generator) IntegerAttribute.generator()
                            .add(-5, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(IntegerAttribute.of(NumberAttribute.Type.SET)),
                    1);
            DAGGER.ADD_REGEN_CLOUD = new WeightedList.Entry<EffectCloudAttribute.Generator>(
                    (EffectCloudAttribute.Generator) EffectCloudAttribute.generator().add(
                            new ArrayList<EffectCloudEntity.Config>(), PooledAttribute.Rolls.ofConstant(1), pool -> {
                                new EffectCloudEntity.Config("Rejuvenate I", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.REGENERATION,
                                                20, 0, false, true)),
                                        20, 2.0f, -1, true, 0.5f);
                                final EffectCloudEntity.Config o31;
                                pool.add(Collections.singletonList(o31),
                                        EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Rejuvenate II", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.REGENERATION,
                                                20, 1, false, true)),
                                        40, 3.0f, -1, true, 0.5f);
                                final EffectCloudEntity.Config o32;
                                final Object o33;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o33)
                                        .add(Collections.singletonList(o32),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Rejuvenate III", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.REGENERATION,
                                                20, 2, false, true)),
                                        60, 4.0f, -1, true, 0.5f);
                                final EffectCloudEntity.Config o34;
                                final Object o35;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o35)
                                        .add(Collections.singletonList(o34),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                return;
                            }).collect(EffectCloudAttribute.of(EffectCloudAttribute.Type.MERGE)),
                    1);
            DAGGER.ADD_WEAKENING_CLOUD = new WeightedList.Entry<EffectCloudAttribute.Generator>(
                    (EffectCloudAttribute.Generator) EffectCloudAttribute.generator().add(
                            new ArrayList<EffectCloudEntity.Config>(), PooledAttribute.Rolls.ofConstant(1), pool -> {
                                new EffectCloudEntity.Config("Weakening I", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WEAKNESS,
                                                20, 0, false, true)),
                                        20, 2.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o36;
                                pool.add(Collections.singletonList(o36),
                                        EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Weakening II", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WEAKNESS,
                                                20, 1, false, true)),
                                        40, 3.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o37;
                                final Object o38;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o38)
                                        .add(Collections.singletonList(o37),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Weakening III", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WEAKNESS,
                                                20, 2, false, true)),
                                        60, 4.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o39;
                                final Object o40;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o40)
                                        .add(Collections.singletonList(o39),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                return;
                            }).collect(EffectCloudAttribute.of(EffectCloudAttribute.Type.MERGE)),
                    1);
            DAGGER.ADD_WITHER_CLOUD = new WeightedList.Entry<EffectCloudAttribute.Generator>(
                    (EffectCloudAttribute.Generator) EffectCloudAttribute.generator().add(
                            new ArrayList<EffectCloudEntity.Config>(), PooledAttribute.Rolls.ofConstant(1), pool -> {
                                new EffectCloudEntity.Config("Withering I", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WITHER,
                                                20, 0, false, true)),
                                        20, 2.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o41;
                                pool.add(Collections.singletonList(o41),
                                        EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Withering II", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WITHER,
                                                20, 1, false, true)),
                                        40, 3.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o42;
                                final Object o43;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o43)
                                        .add(Collections.singletonList(o42),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Withering III", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WITHER,
                                                20, 2, false, true)),
                                        60, 4.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o44;
                                final Object o45;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o45)
                                        .add(Collections.singletonList(o44),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                return;
                            }).collect(EffectCloudAttribute.of(EffectCloudAttribute.Type.MERGE)),
                    1);
            DAGGER.ADD_WITHER_IMMUNITY = new WeightedList.Entry<EffectAttribute.Generator>(
                    (EffectAttribute.Generator) EffectAttribute.generator().add(
                            new ArrayList<EffectAttribute.Instance>(), PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(
                                    Collections.singletonList(new EffectAttribute.Instance(Effects.WITHER)),
                                    EffectAttribute.of(EffectAttribute.Type.SET), 1))
                            .collect(EffectAttribute.of(EffectAttribute.Type.MERGE)),
                    1);
            DAGGER.ADD_POISON_IMMUNITY = new WeightedList.Entry<EffectAttribute.Generator>(
                    (EffectAttribute.Generator) EffectAttribute.generator().add(
                            new ArrayList<EffectAttribute.Instance>(), PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(
                                    Collections.singletonList(new EffectAttribute.Instance(Effects.POISON)),
                                    EffectAttribute.of(EffectAttribute.Type.SET), 1))
                            .collect(EffectAttribute.of(EffectAttribute.Type.MERGE)),
                    1);
            DAGGER.ADD_FEATHER_FEET = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.05f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            DAGGER.ADD_SOULBOUND = new WeightedList.Entry<BooleanAttribute.Generator>(
                    (BooleanAttribute.Generator) BooleanAttribute.generator()
                            .add(true, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(BooleanAttribute.of(BooleanAttribute.Type.SET)),
                    1);
            HELMET.ADD_ARMOR = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(1.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            HELMET.ADD_ARMOR_2 = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(1.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            HELMET.ADD_ARMOR_TOUGHNESS = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(2.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            HELMET.ADD_ARMOR_TOUGHNESS_2 = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(2.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            HELMET.ADD_KNOCKBACK_RESISTANCE = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(0.2, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            HELMET.ADD_KNOCKBACK_RESISTANCE_2 = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(0.2, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            HELMET.ADD_DURABILITY = new WeightedList.Entry<IntegerAttribute.Generator>(
                    (IntegerAttribute.Generator) IntegerAttribute.generator()
                            .add(500, PooledAttribute.Rolls.ofConstant(1), pool -> {
                            }).collect(IntegerAttribute.of(NumberAttribute.Type.SET)),
                    1);
            HELMET.ADD_DURABILITY_2 = new WeightedList.Entry<IntegerAttribute.Generator>(
                    (IntegerAttribute.Generator) IntegerAttribute.generator()
                            .add(500, PooledAttribute.Rolls.ofConstant(1), pool -> {
                            }).collect(IntegerAttribute.of(NumberAttribute.Type.SET)),
                    1);
            HELMET.EXTRA_LEECH_RATIO = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            HELMET.ADD_EXTRA_LEECH_RATIO = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            HELMET.EXTRA_RESISTANCE = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            HELMET.ADD_EXTRA_RESISTANCE = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            HELMET.EXTRA_PARRY_CHANCE = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.05f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            HELMET.ADD_EXTRA_PARRY_CHANCE = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.05f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            HELMET.EXTRA_HEALTH = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(2.0f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            HELMET.ADD_EXTRA_HEALTH = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(2.0f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            HELMET.EXTRA_EFFECTS = new WeightedList.Entry<EffectTalentAttribute.Generator>(
                    (EffectTalentAttribute.Generator) EffectTalentAttribute.generator()
                            .add(new ArrayList<EffectTalent>(), PooledAttribute.Rolls.ofConstant(1),
                                    pool -> pool
                                            .add(Collections.singletonList(new EffectTalent(0, Effects.DAMAGE_RESISTANCE, 1,
                                                    EffectTalent.Type.ICON_ONLY, EffectTalent.Operator.ADD)),
                                                    EffectTalentAttribute.of(EffectTalentAttribute.Type.SET), 2)
                                            .add(Collections.singletonList(new EffectTalent(0, Effects.DAMAGE_RESISTANCE, 2,
                                                    EffectTalent.Type.ICON_ONLY, EffectTalent.Operator.ADD)),
                                                    EffectTalentAttribute.of(EffectTalentAttribute.Type.SET), 1)
                                            .add(Collections.singletonList(new EffectTalent(0, Effects.LUCK,
                                                    1, EffectTalent.Type.ICON_ONLY, EffectTalent.Operator.ADD)),
                                                    EffectTalentAttribute.of(EffectTalentAttribute.Type.SET), 2)
                                            .add(Collections.singletonList(new EffectTalent(0, Effects.LUCK,
                                                    2, EffectTalent.Type.ICON_ONLY, EffectTalent.Operator.ADD)),
                                                    EffectTalentAttribute.of(EffectTalentAttribute.Type.SET), 1))
                            .collect(EffectTalentAttribute.of(EffectTalentAttribute.Type.MERGE)),
                    1);
            HELMET.ADD_REACH = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(1.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            HELMET.ADD_REACH_2 = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(1.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            HELMET.ADD_COOLDOWN_REDUCTION = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            HELMET.ADD_MIN_VAULT_LEVEL = new WeightedList.Entry<IntegerAttribute.Generator>(
                    (IntegerAttribute.Generator) IntegerAttribute.generator()
                            .add(-5, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(IntegerAttribute.of(NumberAttribute.Type.SET)),
                    1);
            HELMET.ADD_REGEN_CLOUD = new WeightedList.Entry<EffectCloudAttribute.Generator>(
                    (EffectCloudAttribute.Generator) EffectCloudAttribute.generator().add(
                            new ArrayList<EffectCloudEntity.Config>(), PooledAttribute.Rolls.ofConstant(1), pool -> {
                                new EffectCloudEntity.Config("Rejuvenate I", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.REGENERATION,
                                                20, 0, false, true)),
                                        20, 2.0f, -1, true, 0.5f);
                                final EffectCloudEntity.Config o46;
                                pool.add(Collections.singletonList(o46),
                                        EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Rejuvenate II", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.REGENERATION,
                                                20, 1, false, true)),
                                        40, 3.0f, -1, true, 0.5f);
                                final EffectCloudEntity.Config o47;
                                final Object o48;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o48)
                                        .add(Collections.singletonList(o47),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Rejuvenate III", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.REGENERATION,
                                                20, 2, false, true)),
                                        60, 4.0f, -1, true, 0.5f);
                                final EffectCloudEntity.Config o49;
                                final Object o50;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o50)
                                        .add(Collections.singletonList(o49),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                return;
                            }).collect(EffectCloudAttribute.of(EffectCloudAttribute.Type.MERGE)),
                    1);
            HELMET.ADD_WEAKENING_CLOUD = new WeightedList.Entry<EffectCloudAttribute.Generator>(
                    (EffectCloudAttribute.Generator) EffectCloudAttribute.generator().add(
                            new ArrayList<EffectCloudEntity.Config>(), PooledAttribute.Rolls.ofConstant(1), pool -> {
                                new EffectCloudEntity.Config("Weakening I", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WEAKNESS,
                                                20, 0, false, true)),
                                        20, 2.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o51;
                                pool.add(Collections.singletonList(o51),
                                        EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Weakening II", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WEAKNESS,
                                                20, 1, false, true)),
                                        40, 3.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o52;
                                final Object o53;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o53)
                                        .add(Collections.singletonList(o52),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Weakening III", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WEAKNESS,
                                                20, 2, false, true)),
                                        60, 4.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o54;
                                final Object o55;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o55)
                                        .add(Collections.singletonList(o54),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                return;
                            }).collect(EffectCloudAttribute.of(EffectCloudAttribute.Type.MERGE)),
                    1);
            HELMET.ADD_WITHER_CLOUD = new WeightedList.Entry<EffectCloudAttribute.Generator>(
                    (EffectCloudAttribute.Generator) EffectCloudAttribute.generator().add(
                            new ArrayList<EffectCloudEntity.Config>(), PooledAttribute.Rolls.ofConstant(1), pool -> {
                                new EffectCloudEntity.Config("Withering I", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WITHER,
                                                20, 0, false, true)),
                                        20, 2.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o56;
                                pool.add(Collections.singletonList(o56),
                                        EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Withering II", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WITHER,
                                                20, 1, false, true)),
                                        40, 3.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o57;
                                final Object o58;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o58)
                                        .add(Collections.singletonList(o57),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Withering III", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WITHER,
                                                20, 2, false, true)),
                                        60, 4.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o59;
                                final Object o60;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o60)
                                        .add(Collections.singletonList(o59),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                return;
                            }).collect(EffectCloudAttribute.of(EffectCloudAttribute.Type.MERGE)),
                    1);
            HELMET.ADD_WITHER_IMMUNITY = new WeightedList.Entry<EffectAttribute.Generator>(
                    (EffectAttribute.Generator) EffectAttribute.generator().add(
                            new ArrayList<EffectAttribute.Instance>(), PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(
                                    Collections.singletonList(new EffectAttribute.Instance(Effects.WITHER)),
                                    EffectAttribute.of(EffectAttribute.Type.SET), 1))
                            .collect(EffectAttribute.of(EffectAttribute.Type.MERGE)),
                    1);
            HELMET.ADD_POISON_IMMUNITY = new WeightedList.Entry<EffectAttribute.Generator>(
                    (EffectAttribute.Generator) EffectAttribute.generator().add(
                            new ArrayList<EffectAttribute.Instance>(), PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(
                                    Collections.singletonList(new EffectAttribute.Instance(Effects.POISON)),
                                    EffectAttribute.of(EffectAttribute.Type.SET), 1))
                            .collect(EffectAttribute.of(EffectAttribute.Type.MERGE)),
                    1);
            HELMET.ADD_FEATHER_FEET = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.05f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            HELMET.ADD_SOULBOUND = new WeightedList.Entry<BooleanAttribute.Generator>(
                    (BooleanAttribute.Generator) BooleanAttribute.generator()
                            .add(true, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(BooleanAttribute.of(BooleanAttribute.Type.SET)),
                    1);
            CHESTPLATE.ADD_ARMOR = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(1.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            CHESTPLATE.ADD_ARMOR_2 = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(1.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            CHESTPLATE.ADD_ARMOR_TOUGHNESS = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(2.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            CHESTPLATE.ADD_ARMOR_TOUGHNESS_2 = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(2.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            CHESTPLATE.ADD_KNOCKBACK_RESISTANCE = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(0.2, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            CHESTPLATE.ADD_KNOCKBACK_RESISTANCE_2 = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(0.2, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            CHESTPLATE.ADD_DURABILITY = new WeightedList.Entry<IntegerAttribute.Generator>(
                    (IntegerAttribute.Generator) IntegerAttribute.generator()
                            .add(500, PooledAttribute.Rolls.ofConstant(1), pool -> {
                            }).collect(IntegerAttribute.of(NumberAttribute.Type.SET)),
                    1);
            CHESTPLATE.ADD_DURABILITY_2 = new WeightedList.Entry<IntegerAttribute.Generator>(
                    (IntegerAttribute.Generator) IntegerAttribute.generator()
                            .add(500, PooledAttribute.Rolls.ofConstant(1), pool -> {
                            }).collect(IntegerAttribute.of(NumberAttribute.Type.SET)),
                    1);
            CHESTPLATE.EXTRA_LEECH_RATIO = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            CHESTPLATE.ADD_EXTRA_LEECH_RATIO = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            CHESTPLATE.EXTRA_RESISTANCE = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            CHESTPLATE.ADD_EXTRA_RESISTANCE = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            CHESTPLATE.EXTRA_PARRY_CHANCE = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.05f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            CHESTPLATE.ADD_EXTRA_PARRY_CHANCE = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.05f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            CHESTPLATE.EXTRA_HEALTH = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(2.0f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            CHESTPLATE.ADD_EXTRA_HEALTH = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(2.0f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            CHESTPLATE.EXTRA_EFFECTS = new WeightedList.Entry<EffectTalentAttribute.Generator>(
                    (EffectTalentAttribute.Generator) EffectTalentAttribute.generator()
                            .add(new ArrayList<EffectTalent>(), PooledAttribute.Rolls.ofConstant(1),
                                    pool -> pool
                                            .add(Collections.singletonList(new EffectTalent(0, Effects.DAMAGE_RESISTANCE, 1,
                                                    EffectTalent.Type.ICON_ONLY, EffectTalent.Operator.ADD)),
                                                    EffectTalentAttribute.of(EffectTalentAttribute.Type.SET), 2)
                                            .add(Collections.singletonList(new EffectTalent(0, Effects.DAMAGE_RESISTANCE, 2,
                                                    EffectTalent.Type.ICON_ONLY, EffectTalent.Operator.ADD)),
                                                    EffectTalentAttribute.of(EffectTalentAttribute.Type.SET), 1)
                                            .add(Collections.singletonList(new EffectTalent(0, Effects.LUCK,
                                                    1, EffectTalent.Type.ICON_ONLY, EffectTalent.Operator.ADD)),
                                                    EffectTalentAttribute.of(EffectTalentAttribute.Type.SET), 2)
                                            .add(Collections.singletonList(new EffectTalent(0, Effects.LUCK,
                                                    2, EffectTalent.Type.ICON_ONLY, EffectTalent.Operator.ADD)),
                                                    EffectTalentAttribute.of(EffectTalentAttribute.Type.SET), 1))
                            .collect(EffectTalentAttribute.of(EffectTalentAttribute.Type.MERGE)),
                    1);
            CHESTPLATE.ADD_REACH = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(1.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            CHESTPLATE.ADD_REACH_2 = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(1.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            CHESTPLATE.ADD_COOLDOWN_REDUCTION = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            CHESTPLATE.ADD_MIN_VAULT_LEVEL = new WeightedList.Entry<IntegerAttribute.Generator>(
                    (IntegerAttribute.Generator) IntegerAttribute.generator()
                            .add(-5, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(IntegerAttribute.of(NumberAttribute.Type.SET)),
                    1);
            CHESTPLATE.ADD_REGEN_CLOUD = new WeightedList.Entry<EffectCloudAttribute.Generator>(
                    (EffectCloudAttribute.Generator) EffectCloudAttribute.generator().add(
                            new ArrayList<EffectCloudEntity.Config>(), PooledAttribute.Rolls.ofConstant(1), pool -> {
                                new EffectCloudEntity.Config("Rejuvenate I", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.REGENERATION,
                                                20, 0, false, true)),
                                        20, 2.0f, -1, true, 0.5f);
                                final EffectCloudEntity.Config o61;
                                pool.add(Collections.singletonList(o61),
                                        EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Rejuvenate II", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.REGENERATION,
                                                20, 1, false, true)),
                                        40, 3.0f, -1, true, 0.5f);
                                final EffectCloudEntity.Config o62;
                                final Object o63;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o63)
                                        .add(Collections.singletonList(o62),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Rejuvenate III", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.REGENERATION,
                                                20, 2, false, true)),
                                        60, 4.0f, -1, true, 0.5f);
                                final EffectCloudEntity.Config o64;
                                final Object o65;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o65)
                                        .add(Collections.singletonList(o64),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                return;
                            }).collect(EffectCloudAttribute.of(EffectCloudAttribute.Type.MERGE)),
                    1);
            CHESTPLATE.ADD_WEAKENING_CLOUD = new WeightedList.Entry<EffectCloudAttribute.Generator>(
                    (EffectCloudAttribute.Generator) EffectCloudAttribute.generator().add(
                            new ArrayList<EffectCloudEntity.Config>(), PooledAttribute.Rolls.ofConstant(1), pool -> {
                                new EffectCloudEntity.Config("Weakening I", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WEAKNESS,
                                                20, 0, false, true)),
                                        20, 2.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o66;
                                pool.add(Collections.singletonList(o66),
                                        EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Weakening II", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WEAKNESS,
                                                20, 1, false, true)),
                                        40, 3.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o67;
                                final Object o68;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o68)
                                        .add(Collections.singletonList(o67),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Weakening III", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WEAKNESS,
                                                20, 2, false, true)),
                                        60, 4.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o69;
                                final Object o70;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o70)
                                        .add(Collections.singletonList(o69),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                return;
                            }).collect(EffectCloudAttribute.of(EffectCloudAttribute.Type.MERGE)),
                    1);
            CHESTPLATE.ADD_WITHER_CLOUD = new WeightedList.Entry<EffectCloudAttribute.Generator>(
                    (EffectCloudAttribute.Generator) EffectCloudAttribute.generator().add(
                            new ArrayList<EffectCloudEntity.Config>(), PooledAttribute.Rolls.ofConstant(1), pool -> {
                                new EffectCloudEntity.Config("Withering I", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WITHER,
                                                20, 0, false, true)),
                                        20, 2.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o71;
                                pool.add(Collections.singletonList(o71),
                                        EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Withering II", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WITHER,
                                                20, 1, false, true)),
                                        40, 3.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o72;
                                final Object o73;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o73)
                                        .add(Collections.singletonList(o72),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Withering III", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WITHER,
                                                20, 2, false, true)),
                                        60, 4.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o74;
                                final Object o75;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o75)
                                        .add(Collections.singletonList(o74),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                return;
                            }).collect(EffectCloudAttribute.of(EffectCloudAttribute.Type.MERGE)),
                    1);
            CHESTPLATE.ADD_WITHER_IMMUNITY = new WeightedList.Entry<EffectAttribute.Generator>(
                    (EffectAttribute.Generator) EffectAttribute.generator().add(
                            new ArrayList<EffectAttribute.Instance>(), PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(
                                    Collections.singletonList(new EffectAttribute.Instance(Effects.WITHER)),
                                    EffectAttribute.of(EffectAttribute.Type.SET), 1))
                            .collect(EffectAttribute.of(EffectAttribute.Type.MERGE)),
                    1);
            CHESTPLATE.ADD_POISON_IMMUNITY = new WeightedList.Entry<EffectAttribute.Generator>(
                    (EffectAttribute.Generator) EffectAttribute.generator().add(
                            new ArrayList<EffectAttribute.Instance>(), PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(
                                    Collections.singletonList(new EffectAttribute.Instance(Effects.POISON)),
                                    EffectAttribute.of(EffectAttribute.Type.SET), 1))
                            .collect(EffectAttribute.of(EffectAttribute.Type.MERGE)),
                    1);
            CHESTPLATE.ADD_FEATHER_FEET = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.05f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            CHESTPLATE.ADD_SOULBOUND = new WeightedList.Entry<BooleanAttribute.Generator>(
                    (BooleanAttribute.Generator) BooleanAttribute.generator()
                            .add(true, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(BooleanAttribute.of(BooleanAttribute.Type.SET)),
                    1);
            LEGGINGS.ADD_ARMOR = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(1.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            LEGGINGS.ADD_ARMOR_2 = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(1.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            LEGGINGS.ADD_ARMOR_TOUGHNESS = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(2.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            LEGGINGS.ADD_ARMOR_TOUGHNESS_2 = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(2.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            LEGGINGS.ADD_KNOCKBACK_RESISTANCE = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(0.2, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            LEGGINGS.ADD_KNOCKBACK_RESISTANCE_2 = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(0.2, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            LEGGINGS.ADD_DURABILITY = new WeightedList.Entry<IntegerAttribute.Generator>(
                    (IntegerAttribute.Generator) IntegerAttribute.generator()
                            .add(500, PooledAttribute.Rolls.ofConstant(1), pool -> {
                            }).collect(IntegerAttribute.of(NumberAttribute.Type.SET)),
                    1);
            LEGGINGS.ADD_DURABILITY_2 = new WeightedList.Entry<IntegerAttribute.Generator>(
                    (IntegerAttribute.Generator) IntegerAttribute.generator()
                            .add(500, PooledAttribute.Rolls.ofConstant(1), pool -> {
                            }).collect(IntegerAttribute.of(NumberAttribute.Type.SET)),
                    1);
            LEGGINGS.EXTRA_LEECH_RATIO = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            LEGGINGS.ADD_EXTRA_LEECH_RATIO = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            LEGGINGS.EXTRA_RESISTANCE = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            LEGGINGS.ADD_EXTRA_RESISTANCE = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            LEGGINGS.EXTRA_PARRY_CHANCE = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.05f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            LEGGINGS.ADD_EXTRA_PARRY_CHANCE = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.05f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            LEGGINGS.EXTRA_HEALTH = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(2.0f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            LEGGINGS.ADD_EXTRA_HEALTH = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(2.0f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            LEGGINGS.EXTRA_EFFECTS = new WeightedList.Entry<EffectTalentAttribute.Generator>(
                    (EffectTalentAttribute.Generator) EffectTalentAttribute.generator()
                            .add(new ArrayList<EffectTalent>(), PooledAttribute.Rolls.ofConstant(1),
                                    pool -> pool
                                            .add(Collections.singletonList(new EffectTalent(0, Effects.DAMAGE_RESISTANCE, 1,
                                                    EffectTalent.Type.ICON_ONLY, EffectTalent.Operator.ADD)),
                                                    EffectTalentAttribute.of(EffectTalentAttribute.Type.SET), 2)
                                            .add(Collections.singletonList(new EffectTalent(0, Effects.DAMAGE_RESISTANCE, 2,
                                                    EffectTalent.Type.ICON_ONLY, EffectTalent.Operator.ADD)),
                                                    EffectTalentAttribute.of(EffectTalentAttribute.Type.SET), 1)
                                            .add(Collections.singletonList(new EffectTalent(0, Effects.LUCK,
                                                    1, EffectTalent.Type.ICON_ONLY, EffectTalent.Operator.ADD)),
                                                    EffectTalentAttribute.of(EffectTalentAttribute.Type.SET), 2)
                                            .add(Collections.singletonList(new EffectTalent(0, Effects.LUCK,
                                                    2, EffectTalent.Type.ICON_ONLY, EffectTalent.Operator.ADD)),
                                                    EffectTalentAttribute.of(EffectTalentAttribute.Type.SET), 1))
                            .collect(EffectTalentAttribute.of(EffectTalentAttribute.Type.MERGE)),
                    1);
            LEGGINGS.ADD_REACH = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(1.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            LEGGINGS.ADD_REACH_2 = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(1.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            LEGGINGS.ADD_COOLDOWN_REDUCTION = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            LEGGINGS.ADD_MIN_VAULT_LEVEL = new WeightedList.Entry<IntegerAttribute.Generator>(
                    (IntegerAttribute.Generator) IntegerAttribute.generator()
                            .add(-5, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(IntegerAttribute.of(NumberAttribute.Type.SET)),
                    1);
            LEGGINGS.ADD_REGEN_CLOUD = new WeightedList.Entry<EffectCloudAttribute.Generator>(
                    (EffectCloudAttribute.Generator) EffectCloudAttribute.generator().add(
                            new ArrayList<EffectCloudEntity.Config>(), PooledAttribute.Rolls.ofConstant(1), pool -> {
                                new EffectCloudEntity.Config("Rejuvenate I", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.REGENERATION,
                                                20, 0, false, true)),
                                        20, 2.0f, -1, true, 0.5f);
                                final EffectCloudEntity.Config o76;
                                pool.add(Collections.singletonList(o76),
                                        EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Rejuvenate II", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.REGENERATION,
                                                20, 1, false, true)),
                                        40, 3.0f, -1, true, 0.5f);
                                final EffectCloudEntity.Config o77;
                                final Object o78;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o78)
                                        .add(Collections.singletonList(o77),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Rejuvenate III", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.REGENERATION,
                                                20, 2, false, true)),
                                        60, 4.0f, -1, true, 0.5f);
                                final EffectCloudEntity.Config o79;
                                final Object o80;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o80)
                                        .add(Collections.singletonList(o79),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                return;
                            }).collect(EffectCloudAttribute.of(EffectCloudAttribute.Type.MERGE)),
                    1);
            LEGGINGS.ADD_WEAKENING_CLOUD = new WeightedList.Entry<EffectCloudAttribute.Generator>(
                    (EffectCloudAttribute.Generator) EffectCloudAttribute.generator().add(
                            new ArrayList<EffectCloudEntity.Config>(), PooledAttribute.Rolls.ofConstant(1), pool -> {
                                new EffectCloudEntity.Config("Weakening I", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WEAKNESS,
                                                20, 0, false, true)),
                                        20, 2.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o81;
                                pool.add(Collections.singletonList(o81),
                                        EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Weakening II", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WEAKNESS,
                                                20, 1, false, true)),
                                        40, 3.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o82;
                                final Object o83;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o83)
                                        .add(Collections.singletonList(o82),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Weakening III", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WEAKNESS,
                                                20, 2, false, true)),
                                        60, 4.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o84;
                                final Object o85;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o85)
                                        .add(Collections.singletonList(o84),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                return;
                            }).collect(EffectCloudAttribute.of(EffectCloudAttribute.Type.MERGE)),
                    1);
            LEGGINGS.ADD_WITHER_CLOUD = new WeightedList.Entry<EffectCloudAttribute.Generator>(
                    (EffectCloudAttribute.Generator) EffectCloudAttribute.generator().add(
                            new ArrayList<EffectCloudEntity.Config>(), PooledAttribute.Rolls.ofConstant(1), pool -> {
                                new EffectCloudEntity.Config("Withering I", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WITHER,
                                                20, 0, false, true)),
                                        20, 2.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o86;
                                pool.add(Collections.singletonList(o86),
                                        EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Withering II", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WITHER,
                                                20, 1, false, true)),
                                        40, 3.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o87;
                                final Object o88;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o88)
                                        .add(Collections.singletonList(o87),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Withering III", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WITHER,
                                                20, 2, false, true)),
                                        60, 4.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o89;
                                final Object o90;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o90)
                                        .add(Collections.singletonList(o89),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                return;
                            }).collect(EffectCloudAttribute.of(EffectCloudAttribute.Type.MERGE)),
                    1);
            LEGGINGS.ADD_WITHER_IMMUNITY = new WeightedList.Entry<EffectAttribute.Generator>(
                    (EffectAttribute.Generator) EffectAttribute.generator().add(
                            new ArrayList<EffectAttribute.Instance>(), PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(
                                    Collections.singletonList(new EffectAttribute.Instance(Effects.WITHER)),
                                    EffectAttribute.of(EffectAttribute.Type.SET), 1))
                            .collect(EffectAttribute.of(EffectAttribute.Type.MERGE)),
                    1);
            LEGGINGS.ADD_POISON_IMMUNITY = new WeightedList.Entry<EffectAttribute.Generator>(
                    (EffectAttribute.Generator) EffectAttribute.generator().add(
                            new ArrayList<EffectAttribute.Instance>(), PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(
                                    Collections.singletonList(new EffectAttribute.Instance(Effects.POISON)),
                                    EffectAttribute.of(EffectAttribute.Type.SET), 1))
                            .collect(EffectAttribute.of(EffectAttribute.Type.MERGE)),
                    1);
            LEGGINGS.ADD_FEATHER_FEET = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.05f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            LEGGINGS.ADD_SOULBOUND = new WeightedList.Entry<BooleanAttribute.Generator>(
                    (BooleanAttribute.Generator) BooleanAttribute.generator()
                            .add(true, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(BooleanAttribute.of(BooleanAttribute.Type.SET)),
                    1);
            BOOTS.ADD_ARMOR = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(1.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            BOOTS.ADD_ARMOR_2 = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(1.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            BOOTS.ADD_ARMOR_TOUGHNESS = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(2.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            BOOTS.ADD_ARMOR_TOUGHNESS_2 = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(2.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            BOOTS.ADD_KNOCKBACK_RESISTANCE = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(0.2, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            BOOTS.ADD_KNOCKBACK_RESISTANCE_2 = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(0.2, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            BOOTS.ADD_DURABILITY = new WeightedList.Entry<IntegerAttribute.Generator>(
                    (IntegerAttribute.Generator) IntegerAttribute.generator()
                            .add(500, PooledAttribute.Rolls.ofConstant(1), pool -> {
                            }).collect(IntegerAttribute.of(NumberAttribute.Type.SET)),
                    1);
            BOOTS.ADD_DURABILITY_2 = new WeightedList.Entry<IntegerAttribute.Generator>(
                    (IntegerAttribute.Generator) IntegerAttribute.generator()
                            .add(500, PooledAttribute.Rolls.ofConstant(1), pool -> {
                            }).collect(IntegerAttribute.of(NumberAttribute.Type.SET)),
                    1);
            BOOTS.EXTRA_LEECH_RATIO = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            BOOTS.ADD_EXTRA_LEECH_RATIO = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            BOOTS.EXTRA_RESISTANCE = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            BOOTS.ADD_EXTRA_RESISTANCE = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            BOOTS.EXTRA_PARRY_CHANCE = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.05f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            BOOTS.ADD_EXTRA_PARRY_CHANCE = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.05f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            BOOTS.EXTRA_HEALTH = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(2.0f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            BOOTS.ADD_EXTRA_HEALTH = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(2.0f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            BOOTS.EXTRA_EFFECTS = new WeightedList.Entry<EffectTalentAttribute.Generator>(
                    (EffectTalentAttribute.Generator) EffectTalentAttribute.generator()
                            .add(new ArrayList<EffectTalent>(), PooledAttribute.Rolls.ofConstant(1),
                                    pool -> pool
                                            .add(Collections.singletonList(new EffectTalent(0, Effects.DAMAGE_RESISTANCE, 1,
                                                    EffectTalent.Type.ICON_ONLY, EffectTalent.Operator.ADD)),
                                                    EffectTalentAttribute.of(EffectTalentAttribute.Type.SET), 2)
                                            .add(Collections.singletonList(new EffectTalent(0, Effects.DAMAGE_RESISTANCE, 2,
                                                    EffectTalent.Type.ICON_ONLY, EffectTalent.Operator.ADD)),
                                                    EffectTalentAttribute.of(EffectTalentAttribute.Type.SET), 1)
                                            .add(Collections.singletonList(new EffectTalent(0, Effects.LUCK,
                                                    1, EffectTalent.Type.ICON_ONLY, EffectTalent.Operator.ADD)),
                                                    EffectTalentAttribute.of(EffectTalentAttribute.Type.SET), 2)
                                            .add(Collections.singletonList(new EffectTalent(0, Effects.LUCK,
                                                    2, EffectTalent.Type.ICON_ONLY, EffectTalent.Operator.ADD)),
                                                    EffectTalentAttribute.of(EffectTalentAttribute.Type.SET), 1))
                            .collect(EffectTalentAttribute.of(EffectTalentAttribute.Type.MERGE)),
                    1);
            BOOTS.ADD_REACH = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(1.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            BOOTS.ADD_REACH_2 = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(1.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    1);
            BOOTS.ADD_COOLDOWN_REDUCTION = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.2f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            BOOTS.ADD_MIN_VAULT_LEVEL = new WeightedList.Entry<IntegerAttribute.Generator>(
                    (IntegerAttribute.Generator) IntegerAttribute.generator()
                            .add(-5, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(IntegerAttribute.of(NumberAttribute.Type.SET)),
                    1);
            BOOTS.ADD_REGEN_CLOUD = new WeightedList.Entry<EffectCloudAttribute.Generator>(
                    (EffectCloudAttribute.Generator) EffectCloudAttribute.generator().add(
                            new ArrayList<EffectCloudEntity.Config>(), PooledAttribute.Rolls.ofConstant(1), pool -> {
                                new EffectCloudEntity.Config("Rejuvenate I", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.REGENERATION,
                                                20, 0, false, true)),
                                        20, 2.0f, -1, true, 0.5f);
                                final EffectCloudEntity.Config o91;
                                pool.add(Collections.singletonList(o91),
                                        EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Rejuvenate II", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.REGENERATION,
                                                20, 1, false, true)),
                                        40, 3.0f, -1, true, 0.5f);
                                final EffectCloudEntity.Config o92;
                                final Object o93;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o93)
                                        .add(Collections.singletonList(o92),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Rejuvenate III", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.REGENERATION,
                                                20, 2, false, true)),
                                        60, 4.0f, -1, true, 0.5f);
                                final EffectCloudEntity.Config o94;
                                final Object o95;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o95)
                                        .add(Collections.singletonList(o94),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                return;
                            }).collect(EffectCloudAttribute.of(EffectCloudAttribute.Type.MERGE)),
                    1);
            BOOTS.ADD_WEAKENING_CLOUD = new WeightedList.Entry<EffectCloudAttribute.Generator>(
                    (EffectCloudAttribute.Generator) EffectCloudAttribute.generator().add(
                            new ArrayList<EffectCloudEntity.Config>(), PooledAttribute.Rolls.ofConstant(1), pool -> {
                                new EffectCloudEntity.Config("Weakening I", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WEAKNESS,
                                                20, 0, false, true)),
                                        20, 2.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o96;
                                pool.add(Collections.singletonList(o96),
                                        EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Weakening II", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WEAKNESS,
                                                20, 1, false, true)),
                                        40, 3.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o97;
                                final Object o98;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o98)
                                        .add(Collections.singletonList(o97),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Weakening III", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WEAKNESS,
                                                20, 2, false, true)),
                                        60, 4.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o99;
                                final Object o100;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o100)
                                        .add(Collections.singletonList(o99),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                return;
                            }).collect(EffectCloudAttribute.of(EffectCloudAttribute.Type.MERGE)),
                    1);
            BOOTS.ADD_WITHER_CLOUD = new WeightedList.Entry<EffectCloudAttribute.Generator>(
                    (EffectCloudAttribute.Generator) EffectCloudAttribute.generator().add(
                            new ArrayList<EffectCloudEntity.Config>(), PooledAttribute.Rolls.ofConstant(1), pool -> {
                                new EffectCloudEntity.Config("Withering I", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WITHER,
                                                20, 0, false, true)),
                                        20, 2.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o101;
                                pool.add(Collections.singletonList(o101),
                                        EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Withering II", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WITHER,
                                                20, 1, false, true)),
                                        40, 3.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o102;
                                final Object o103;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o103)
                                        .add(Collections.singletonList(o102),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                new EffectCloudEntity.Config("Withering III", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WITHER,
                                                20, 2, false, true)),
                                        60, 4.0f, -1, false, 0.5f);
                                final EffectCloudEntity.Config o104;
                                final Object o105;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o105)
                                        .add(Collections.singletonList(o104),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                return;
                            }).collect(EffectCloudAttribute.of(EffectCloudAttribute.Type.MERGE)),
                    1);
            BOOTS.ADD_WITHER_IMMUNITY = new WeightedList.Entry<EffectAttribute.Generator>(
                    (EffectAttribute.Generator) EffectAttribute.generator().add(
                            new ArrayList<EffectAttribute.Instance>(), PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(
                                    Collections.singletonList(new EffectAttribute.Instance(Effects.WITHER)),
                                    EffectAttribute.of(EffectAttribute.Type.SET), 1))
                            .collect(EffectAttribute.of(EffectAttribute.Type.MERGE)),
                    1);
            BOOTS.ADD_POISON_IMMUNITY = new WeightedList.Entry<EffectAttribute.Generator>(
                    (EffectAttribute.Generator) EffectAttribute.generator().add(
                            new ArrayList<EffectAttribute.Instance>(), PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(
                                    Collections.singletonList(new EffectAttribute.Instance(Effects.POISON)),
                                    EffectAttribute.of(EffectAttribute.Type.SET), 1))
                            .collect(EffectAttribute.of(EffectAttribute.Type.MERGE)),
                    1);
            BOOTS.ADD_FEATHER_FEET = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.05f, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    1);
            BOOTS.ADD_SOULBOUND = new WeightedList.Entry<BooleanAttribute.Generator>(
                    (BooleanAttribute.Generator) BooleanAttribute.generator()
                            .add(true, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(BooleanAttribute.of(BooleanAttribute.Type.SET)),
                    1);
            ALL_IDOLS.ADD_DURABILITY = new WeightedList.Entry<IntegerAttribute.Generator>(
                    (IntegerAttribute.Generator) IntegerAttribute.generator()
                            .add(200, PooledAttribute.Rolls.ofBinomial(5, 0.5),
                                    pool -> pool.add(50, IntegerAttribute.of(NumberAttribute.Type.ADD), 1))
                            .collect(IntegerAttribute.of(NumberAttribute.Type.SET)),
                    20);
            ALL_IDOLS.ADD_DURABILITY_2 = new WeightedList.Entry<IntegerAttribute.Generator>(
                    (IntegerAttribute.Generator) IntegerAttribute.generator()
                            .add(200, PooledAttribute.Rolls.ofBinomial(5, 0.5),
                                    pool -> pool.add(50, IntegerAttribute.of(NumberAttribute.Type.ADD), 1))
                            .collect(IntegerAttribute.of(NumberAttribute.Type.SET)),
                    20);
            ALL_IDOLS.ADD_WEAKENING_CLOUD = new WeightedList.Entry<EffectCloudAttribute.Generator>(
                    (EffectCloudAttribute.Generator) EffectCloudAttribute.generator().add(
                            new ArrayList<EffectCloudEntity.Config>(), PooledAttribute.Rolls.ofConstant(1), pool -> {
                                new EffectCloudEntity.Config("Weakening I", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WEAKNESS,
                                                40, 0, false, true)),
                                        40, 2.0f, 2824704, false, 0.04f);
                                final EffectCloudEntity.Config o106;
                                pool.add(Collections.singletonList(o106),
                                        EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 6);
                                new EffectCloudEntity.Config("Weakening II", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WEAKNESS,
                                                40, 0, false, true)),
                                        40, 3.0f, 2824704, false, 0.05f);
                                final EffectCloudEntity.Config o107;
                                final Object o108;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o108)
                                        .add(Collections.singletonList(o107),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 3);
                                new EffectCloudEntity.Config("Weakening III", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WEAKNESS,
                                                60, 0, false, true)),
                                        40, 4.0f, 2824704, false, 0.06f);
                                final EffectCloudEntity.Config o109;
                                final Object o110;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o110)
                                        .add(Collections.singletonList(o109),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                return;
                            }).collect(EffectCloudAttribute.of(EffectCloudAttribute.Type.MERGE)),
                    20);
            ALL_IDOLS.ADD_WITHER_CLOUD = new WeightedList.Entry<EffectCloudAttribute.Generator>(
                    (EffectCloudAttribute.Generator) EffectCloudAttribute.generator().add(
                            new ArrayList<EffectCloudEntity.Config>(), PooledAttribute.Rolls.ofConstant(1), pool -> {
                                new EffectCloudEntity.Config("Withering I", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WITHER,
                                                60, 0, false, true)),
                                        40, 2.0f, 0, false, 0.05f);
                                final EffectCloudEntity.Config o111;
                                pool.add(Collections.singletonList(o111),
                                        EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 6);
                                new EffectCloudEntity.Config("Withering II", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WITHER,
                                                60, 1, false, true)),
                                        60, 3.0f, 0, false, 0.075f);
                                final EffectCloudEntity.Config o112;
                                final Object o113;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o113)
                                        .add(Collections.singletonList(o112),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 3);
                                new EffectCloudEntity.Config("Withering III", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.WITHER,
                                                60, 2, false, true)),
                                        60, 4.0f, 0, false, 0.1f);
                                final EffectCloudEntity.Config o114;
                                final Object o115;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o115)
                                        .add(Collections.singletonList(o114),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                return;
                            }).collect(EffectCloudAttribute.of(EffectCloudAttribute.Type.MERGE)),
                    20);
            ALL_IDOLS.ADD_WITHER_IMMUNITY = new WeightedList.Entry<EffectAttribute.Generator>(
                    (EffectAttribute.Generator) EffectAttribute.generator().add(
                            new ArrayList<EffectAttribute.Instance>(), PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(
                                    Collections.singletonList(new EffectAttribute.Instance(Effects.WITHER)),
                                    EffectAttribute.of(EffectAttribute.Type.SET), 1))
                            .collect(EffectAttribute.of(EffectAttribute.Type.MERGE)),
                    20);
            ALL_IDOLS.ADD_POISON_IMMUNITY = new WeightedList.Entry<EffectAttribute.Generator>(
                    (EffectAttribute.Generator) EffectAttribute.generator().add(
                            new ArrayList<EffectAttribute.Instance>(), PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(
                                    Collections.singletonList(new EffectAttribute.Instance(Effects.POISON)),
                                    EffectAttribute.of(EffectAttribute.Type.SET), 1))
                            .collect(EffectAttribute.of(EffectAttribute.Type.MERGE)),
                    20);
            ALL_IDOLS.ADD_SOULBOUND = new WeightedList.Entry<BooleanAttribute.Generator>(
                    (BooleanAttribute.Generator) BooleanAttribute.generator()
                            .add(true, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(BooleanAttribute.of(BooleanAttribute.Type.SET)),
                    20);
            final BaseModifiers IDOL_BENEVOLENT = ALL_IDOLS.copy();
            IDOL_BENEVOLENT.EXTRA_HEALTH = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(1.0f, PooledAttribute.Rolls.ofBinomial(3, 0.5),
                                    pool -> pool.add(1.0f, FloatAttribute.of(NumberAttribute.Type.ADD), 1))
                            .collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    20);
            IDOL_BENEVOLENT.ADD_EXTRA_HEALTH = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(1.0f, PooledAttribute.Rolls.ofBinomial(3, 0.5),
                                    pool -> pool.add(1.0f, FloatAttribute.of(NumberAttribute.Type.ADD), 1))
                            .collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    20);
            IDOL_BENEVOLENT.ADD_REGEN_CLOUD = new WeightedList.Entry<EffectCloudAttribute.Generator>(
                    (EffectCloudAttribute.Generator) EffectCloudAttribute.generator().add(
                            new ArrayList<EffectCloudEntity.Config>(), PooledAttribute.Rolls.ofConstant(1), pool -> {
                                new EffectCloudEntity.Config("Rejuvenate I", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.HEAL,
                                                60, 0, false, true)),
                                        60, 2.0f, 16711772, true, 0.04f);
                                final EffectCloudEntity.Config o116;
                                pool.add(Collections.singletonList(o116),
                                        EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 6);
                                new EffectCloudEntity.Config("Rejuvenate II", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.HEAL,
                                                60, 1, false, true)),
                                        40, 3.0f, -1, true, 0.04f);
                                final EffectCloudEntity.Config o117;
                                final Object o118;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o118)
                                        .add(Collections.singletonList(o117),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 3);
                                new EffectCloudEntity.Config("Rejuvenate III", Potions.EMPTY,
                                        Arrays.asList(new EffectCloudEntity.Config.CloudEffect(Effects.HEAL,
                                                60, 2, false, true)),
                                        60, 3.0f, -1, true, 0.04f);
                                final EffectCloudEntity.Config o119;
                                final Object o120;
                                ((PooledAttribute.Pool<List<EffectCloudEntity.Config>, EffectCloudAttribute.Generator.Operator>) o120)
                                        .add(Collections.singletonList(o119),
                                                EffectCloudAttribute.of(EffectCloudAttribute.Type.SET), 1);
                                return;
                            }).collect(EffectCloudAttribute.of(EffectCloudAttribute.Type.MERGE)),
                    12);
            IDOL_BENEVOLENT.EXTRA_EFFECTS = new WeightedList.Entry<EffectTalentAttribute.Generator>(
                    (EffectTalentAttribute.Generator) EffectTalentAttribute.generator()
                            .add(new ArrayList<EffectTalent>(), PooledAttribute.Rolls.ofConstant(1),
                                    pool -> pool.add(
                                            Collections.singletonList(new EffectTalent(0, Effects.REGENERATION, 1,
                                                    EffectTalent.Type.ICON_ONLY, EffectTalent.Operator.ADD)),
                                            EffectTalentAttribute.of(EffectTalentAttribute.Type.SET), 1))
                            .collect(EffectTalentAttribute.of(EffectTalentAttribute.Type.MERGE)),
                    1);
            IDOL_BENEVOLENT.ADD_HUNGER_IMMUNITY = new WeightedList.Entry<EffectAttribute.Generator>(
                    (EffectAttribute.Generator) EffectAttribute.generator().add(
                            new ArrayList<EffectAttribute.Instance>(), PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(
                                    Collections.singletonList(new EffectAttribute.Instance(Effects.HUNGER)),
                                    EffectAttribute.of(EffectAttribute.Type.SET), 1))
                            .collect(EffectAttribute.of(EffectAttribute.Type.MERGE)),
                    4);
            final BaseModifiers IDOL_OMNISCIENT = ALL_IDOLS.copy();
            IDOL_OMNISCIENT.EXTRA_RESISTANCE = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.01f, PooledAttribute.Rolls.ofBinomial(5, 0.2),
                                    pool -> pool.add(0.01f, FloatAttribute.of(NumberAttribute.Type.ADD), 1))
                            .collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    20);
            IDOL_OMNISCIENT.ADD_EXTRA_RESISTANCE = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.01f, PooledAttribute.Rolls.ofBinomial(5, 0.2),
                                    pool -> pool.add(0.01f, FloatAttribute.of(NumberAttribute.Type.ADD), 1))
                            .collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    20);
            IDOL_OMNISCIENT.EXTRA_PARRY_CHANCE = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.01f, PooledAttribute.Rolls.ofBinomial(5, 0.2),
                                    pool -> pool.add(0.01f, FloatAttribute.of(NumberAttribute.Type.ADD), 1))
                            .collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    20);
            IDOL_OMNISCIENT.ADD_EXTRA_PARRY_CHANCE = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.01f, PooledAttribute.Rolls.ofBinomial(5, 0.2),
                                    pool -> pool.add(0.01f, FloatAttribute.of(NumberAttribute.Type.ADD), 1))
                            .collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    20);
            IDOL_OMNISCIENT.EXTRA_EFFECTS = new WeightedList.Entry<EffectTalentAttribute.Generator>(
                    (EffectTalentAttribute.Generator) EffectTalentAttribute.generator()
                            .add(new ArrayList<EffectTalent>(), PooledAttribute.Rolls.ofConstant(1),
                                    pool -> pool.add(
                                            Collections.singletonList(new EffectTalent(0, Effects.LUCK, 1,
                                                    EffectTalent.Type.ICON_ONLY, EffectTalent.Operator.ADD)),
                                            EffectTalentAttribute.of(EffectTalentAttribute.Type.SET), 1))
                            .collect(EffectTalentAttribute.of(EffectTalentAttribute.Type.MERGE)),
                    1);
            IDOL_OMNISCIENT.ADD_MINING_FATIGUE_IMMUNITY = new WeightedList.Entry<EffectAttribute.Generator>(
                    (EffectAttribute.Generator) EffectAttribute.generator().add(
                            new ArrayList<EffectAttribute.Instance>(), PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(
                                    Collections.singletonList(new EffectAttribute.Instance(Effects.DIG_SLOWDOWN)),
                                    EffectAttribute.of(EffectAttribute.Type.SET), 1))
                            .collect(EffectAttribute.of(EffectAttribute.Type.MERGE)),
                    4);
            final BaseModifiers IDOL_TIMEKEEPER = ALL_IDOLS.copy();
            IDOL_TIMEKEEPER.ADD_REACH = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(1.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.ADD)),
                    20);
            IDOL_TIMEKEEPER.ADD_REACH_2 = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(1.0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(DoubleAttribute.of(NumberAttribute.Type.ADD)),
                    20);
            IDOL_TIMEKEEPER.ADD_COOLDOWN_REDUCTION = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.01f, PooledAttribute.Rolls.ofBinomial(5, 0.2),
                                    pool -> pool.add(0.01f, FloatAttribute.of(NumberAttribute.Type.ADD), 1))
                            .collect(FloatAttribute.of(NumberAttribute.Type.ADD)),
                    20);
            IDOL_TIMEKEEPER.ADD_COOLDOWN_REDUCTION_2 = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.01f, PooledAttribute.Rolls.ofBinomial(5, 0.2),
                                    pool -> pool.add(0.01f, FloatAttribute.of(NumberAttribute.Type.ADD), 1))
                            .collect(FloatAttribute.of(NumberAttribute.Type.ADD)),
                    20);
            IDOL_TIMEKEEPER.EXTRA_EFFECTS = new WeightedList.Entry<EffectTalentAttribute.Generator>(
                    (EffectTalentAttribute.Generator) EffectTalentAttribute.generator()
                            .add(new ArrayList<EffectTalent>(), PooledAttribute.Rolls.ofConstant(1),
                                    pool -> pool.add(
                                            Collections.singletonList(new EffectTalent(0, Effects.MOVEMENT_SPEED, 1,
                                                    EffectTalent.Type.ICON_ONLY, EffectTalent.Operator.ADD)),
                                            EffectTalentAttribute.of(EffectTalentAttribute.Type.SET), 1))
                            .collect(EffectTalentAttribute.of(EffectTalentAttribute.Type.MERGE)),
                    2);
            IDOL_TIMEKEEPER.ADD_SLOWNESS_IMMUNITY = new WeightedList.Entry<EffectAttribute.Generator>(
                    (EffectAttribute.Generator) EffectAttribute.generator().add(
                            new ArrayList<EffectAttribute.Instance>(), PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(
                                    Collections.singletonList(new EffectAttribute.Instance(Effects.MOVEMENT_SLOWDOWN)),
                                    EffectAttribute.of(EffectAttribute.Type.SET), 1))
                            .collect(EffectAttribute.of(EffectAttribute.Type.MERGE)),
                    3);
            final BaseModifiers IDOL_MALEVOLENCE = ALL_IDOLS.copy();
            IDOL_MALEVOLENCE.ADD_ATTACK_DAMAGE = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(0.1, PooledAttribute.Rolls.ofBinomial(9, 0.5),
                                    pool -> pool.add(0.1, DoubleAttribute.of(NumberAttribute.Type.ADD), 1))
                            .collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    16);
            IDOL_MALEVOLENCE.ADD_ATTACK_DAMAGE_2 = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(0.1, PooledAttribute.Rolls.ofBinomial(9, 0.5),
                                    pool -> pool.add(0.1, DoubleAttribute.of(NumberAttribute.Type.ADD), 1))
                            .collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    16);
            IDOL_MALEVOLENCE.ADD_ATTACK_SPEED = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(0.01, PooledAttribute.Rolls.ofBinomial(15, 0.5),
                                    pool -> pool.add(0.01, DoubleAttribute.of(NumberAttribute.Type.ADD), 1))
                            .collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    16);
            IDOL_MALEVOLENCE.ADD_ATTACK_SPEED_2 = new WeightedList.Entry<DoubleAttribute.Generator>(
                    (DoubleAttribute.Generator) DoubleAttribute.generator()
                            .add(0.01, PooledAttribute.Rolls.ofBinomial(15, 0.5),
                                    pool -> pool.add(0.01, DoubleAttribute.of(NumberAttribute.Type.ADD), 1))
                            .collect(DoubleAttribute.of(NumberAttribute.Type.SET)),
                    16);
            IDOL_MALEVOLENCE.EXTRA_LEECH_RATIO = new WeightedList.Entry<FloatAttribute.Generator>(
                    (FloatAttribute.Generator) FloatAttribute.generator()
                            .add(0.01f, PooledAttribute.Rolls.ofBinomial(2, 0.5),
                                    pool -> pool.add(0.01f, FloatAttribute.of(NumberAttribute.Type.ADD), 1))
                            .collect(FloatAttribute.of(NumberAttribute.Type.SET)),
                    4);
            IDOL_MALEVOLENCE.EXTRA_EFFECTS = new WeightedList.Entry<EffectTalentAttribute.Generator>(
                    (EffectTalentAttribute.Generator) EffectTalentAttribute.generator()
                            .add(new ArrayList<EffectTalent>(), PooledAttribute.Rolls.ofConstant(1),
                                    pool -> pool.add(
                                            Collections.singletonList(new EffectTalent(0, Effects.DIG_SPEED, 1,
                                                    EffectTalent.Type.ICON_ONLY, EffectTalent.Operator.ADD)),
                                            EffectTalentAttribute.of(EffectTalentAttribute.Type.SET), 1))
                            .collect(EffectTalentAttribute.of(EffectTalentAttribute.Type.MERGE)),
                    3);
            IDOL_MALEVOLENCE.ADD_WEAKNESS_IMMUNITY = new WeightedList.Entry<EffectAttribute.Generator>(
                    (EffectAttribute.Generator) EffectAttribute.generator().add(
                            new ArrayList<EffectAttribute.Instance>(), PooledAttribute.Rolls.ofConstant(1),
                            pool -> pool.add(
                                    Collections.singletonList(new EffectAttribute.Instance(Effects.WEAKNESS)),
                                    EffectAttribute.of(EffectAttribute.Type.SET), 1))
                            .collect(EffectAttribute.of(EffectAttribute.Type.MERGE)),
                    3);
            (this.BASE_MODIFIERS = new LinkedHashMap<String, BaseModifiers>())
                    .put(ModItems.SWORD.getRegistryName().toString(), SWORD);
            this.BASE_MODIFIERS.put(ModItems.AXE.getRegistryName().toString(), AXE);
            this.BASE_MODIFIERS.put(ModItems.HELMET.getRegistryName().toString(), HELMET);
            this.BASE_MODIFIERS.put(ModItems.CHESTPLATE.getRegistryName().toString(), CHESTPLATE);
            this.BASE_MODIFIERS.put(ModItems.LEGGINGS.getRegistryName().toString(), LEGGINGS);
            this.BASE_MODIFIERS.put(ModItems.BOOTS.getRegistryName().toString(), BOOTS);
            this.BASE_MODIFIERS.put(ModItems.IDOL_BENEVOLENT.getRegistryName().toString(), IDOL_BENEVOLENT);
            this.BASE_MODIFIERS.put(ModItems.IDOL_OMNISCIENT.getRegistryName().toString(), IDOL_OMNISCIENT);
            this.BASE_MODIFIERS.put(ModItems.IDOL_TIMEKEEPER.getRegistryName().toString(), IDOL_TIMEKEEPER);
            this.BASE_MODIFIERS.put(ModItems.IDOL_MALEVOLENCE.getRegistryName().toString(), IDOL_MALEVOLENCE);
        }
    }

    public static class Scrappy extends VaultGearConfig {
        @Override
        public String getName() {
            return "vault_gear_" + VaultGear.Rarity.SCRAPPY.name().toLowerCase();
        }

        @Override
        protected void reset() {
            super.reset();
            this.TIERS.forEach(tier -> tier.BASE_ATTRIBUTES.forEach((key, value) -> {
                if (!ModItems.ETCHING.getRegistryName().toString().equals(key)) {
                    value.GEAR_MODIFIERS_TO_ROLL = (IntegerAttribute.Generator) IntegerAttribute.generator()
                            .add(0, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(IntegerAttribute.of(NumberAttribute.Type.SET));
                }
            }));
        }
    }

    public static class Common extends VaultGearConfig {
        @Override
        public String getName() {
            return "vault_gear_" + VaultGear.Rarity.COMMON.name().toLowerCase();
        }

        @Override
        protected void reset() {
            super.reset();
            this.TIERS.forEach(tier -> tier.BASE_ATTRIBUTES.forEach((key, value) -> {
                if (!ModItems.ETCHING.getRegistryName().toString().equals(key)) {
                    value.GEAR_MODIFIERS_TO_ROLL = (IntegerAttribute.Generator) IntegerAttribute.generator()
                            .add(1, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(IntegerAttribute.of(NumberAttribute.Type.SET));
                }
            }));
        }
    }

    public static class Rare extends VaultGearConfig {
        @Override
        public String getName() {
            return "vault_gear_" + VaultGear.Rarity.RARE.name().toLowerCase();
        }

        @Override
        protected void reset() {
            super.reset();
            this.TIERS.forEach(tier -> tier.BASE_ATTRIBUTES.forEach((key, value) -> {
                if (!ModItems.ETCHING.getRegistryName().toString().equals(key)) {
                    value.GEAR_MODIFIERS_TO_ROLL = (IntegerAttribute.Generator) IntegerAttribute.generator()
                            .add(1, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(IntegerAttribute.of(NumberAttribute.Type.SET));
                }
            }));
        }
    }

    public static class Epic extends VaultGearConfig {
        @Override
        public String getName() {
            return "vault_gear_" + VaultGear.Rarity.EPIC.name().toLowerCase();
        }

        @Override
        protected void reset() {
            super.reset();
            this.TIERS.forEach(tier -> tier.BASE_ATTRIBUTES.forEach((key, value) -> {
                if (!ModItems.ETCHING.getRegistryName().toString().equals(key)) {
                    value.GEAR_MODIFIERS_TO_ROLL = (IntegerAttribute.Generator) IntegerAttribute.generator()
                            .add(2, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(IntegerAttribute.of(NumberAttribute.Type.SET));
                }
            }));
        }
    }

    public static class Omega extends VaultGearConfig {
        @Override
        public String getName() {
            return "vault_gear_" + VaultGear.Rarity.OMEGA.name().toLowerCase();
        }

        @Override
        protected void reset() {
            super.reset();
            this.TIERS.forEach(tier -> tier.BASE_ATTRIBUTES.forEach((key, value) -> {
                if (!ModItems.ETCHING.getRegistryName().toString().equals(key)) {
                    value.GEAR_MODIFIERS_TO_ROLL = (IntegerAttribute.Generator) IntegerAttribute.generator()
                            .add(2, PooledAttribute.Rolls.ofEmpty(), pool -> {
                            }).collect(IntegerAttribute.of(NumberAttribute.Type.SET));
                }
            }));
        }
    }

    public static class General extends Config {
        @Expose
        public String DEFAULT_ROLL;
        @Expose
        public Map<String, Roll> ROLLS;
        @Expose
        public List<TierConfig> TIER;

        public General() {
            this.DEFAULT_ROLL = "All";
        }

        @Override
        public String getName() {
            return "vault_gear";
        }

        @Override
        protected void reset() {
            (this.TIER = new ArrayList<TierConfig>()).add(new TierConfig("", String.valueOf(65535), 0));
            this.TIER.add(new TierConfig("2", String.valueOf(65535), 100));
            this.TIER.add(new TierConfig("3", String.valueOf(65535), 200));
            (this.ROLLS = new LinkedHashMap<String, Roll>()).put("Scrappy Only",
                    new Roll(new WeightedList<VaultGear.Rarity>().add(VaultGear.Rarity.SCRAPPY, 1)));
            this.ROLLS.put("Treasure Only",
                    new Roll(new WeightedList<VaultGear.Rarity>().add(VaultGear.Rarity.COMMON, 1)
                            .add(VaultGear.Rarity.RARE, 1).add(VaultGear.Rarity.EPIC, 1)
                            .add(VaultGear.Rarity.OMEGA, 1)));
            this.ROLLS.put("All",
                    new Roll(new WeightedList<VaultGear.Rarity>().add(VaultGear.Rarity.SCRAPPY, 1)
                            .add(VaultGear.Rarity.COMMON, 1).add(VaultGear.Rarity.RARE, 1).add(VaultGear.Rarity.EPIC, 1)
                            .add(VaultGear.Rarity.OMEGA, 1)));
        }

        public Roll getDefaultRoll() {
            return this.getRoll(this.DEFAULT_ROLL).get();
        }

        public Optional<Roll> getRoll(final String name) {
            final Roll roll = this.ROLLS.get(name);
            if (roll != null) {
                roll.name = name;
            }
            return Optional.ofNullable(roll);
        }

        public TierConfig getTierConfig(final int tier) {
            return this.TIER.get(MathHelper.clamp(tier, 0, this.TIER.size()));
        }

        public static class TierConfig {
            @Expose
            private final String name;
            @Expose
            private final String color;
            @Expose
            private final int minLevel;

            public TierConfig(final String name, final String color, final int minLevel) {
                this.name = name;
                this.color = color;
                this.minLevel = minLevel;
            }

            public ITextComponent getDisplay() {
                return (ITextComponent) new StringTextComponent(this.name).setStyle(this.getDisplayColorStyle());
            }

            public Style getDisplayColorStyle() {
                return Style.EMPTY.withColor(Color.fromRgb(Integer.parseInt(this.color)));
            }

            public int getMinLevel() {
                return this.minLevel;
            }
        }

        public static class Roll {
            protected String name;
            @Expose
            protected WeightedList<VaultGear.Rarity> POOL;
            @Expose
            protected int COLOR;

            public Roll(final WeightedList<VaultGear.Rarity> pool) {
                this.POOL = pool;
            }

            public String getName() {
                return this.name;
            }

            public int getColor() {
                return this.COLOR;
            }

            public VaultGear.Rarity getRandom(final Random random) {
                return this.POOL.getRandom(random);
            }
        }
    }
}
