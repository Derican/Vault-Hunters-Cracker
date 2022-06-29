
package iskallia.vault.item.catalyst;

import java.util.function.BiFunction;
import com.mojang.datafixers.kinds.Applicative;
import iskallia.vault.util.EnumCodec;
import com.mojang.datafixers.kinds.App;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.function.Predicate;
import java.util.Random;
import javax.annotation.Nullable;
import iskallia.vault.config.VaultCrystalCatalystConfig;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.Style;
import iskallia.vault.init.ModConfigs;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.client.gui.screen.Screen;
import java.util.ArrayList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import java.util.List;
import iskallia.vault.world.vault.modifier.VaultModifier;
import com.mojang.serialization.Codec;

public class ModifierRollResult {
    public static final Codec<ModifierRollResult> CODEC;
    private final ModifierRollType type;
    private final String value;

    private ModifierRollResult(final ModifierRollType type, final String value) {
        this.type = type;
        this.value = ((type == ModifierRollType.ADD_SPECIFIC_MODIFIER) ? VaultModifier.migrateModifierName(value)
                : value);
    }

    public static ModifierRollResult ofModifier(final String modifier) {
        return new ModifierRollResult(ModifierRollType.ADD_SPECIFIC_MODIFIER, modifier);
    }

    public static ModifierRollResult ofPool(final String pool) {
        return new ModifierRollResult(ModifierRollType.ADD_RANDOM_MODIFIER, pool);
    }

    @OnlyIn(Dist.CLIENT)
    public List<ITextComponent> getTooltipDescription(final String prefix, final boolean canAddDetail) {
        return this.getTooltipDescription((IFormattableTextComponent) new StringTextComponent(prefix), canAddDetail);
    }

    @OnlyIn(Dist.CLIENT)
    public List<ITextComponent> getTooltipDescription(final IFormattableTextComponent prefix,
            final boolean canAddDetail) {
        final List<ITextComponent> description = new ArrayList<ITextComponent>();
        description.add((ITextComponent) prefix.append(this.getDescription()));
        if (canAddDetail && Screen.hasShiftDown()) {
            final IFormattableTextComponent modifierDescription = this.getModifierDescription();
            if (modifierDescription != null) {
                description.add((ITextComponent) new StringTextComponent("   ")
                        .append((ITextComponent) modifierDescription.withStyle(TextFormatting.DARK_GRAY)));
            }
        }
        return description;
    }

    public ITextComponent getDescription() {
        IFormattableTextComponent name = (IFormattableTextComponent) new StringTextComponent(this.value);
        if (this.type == ModifierRollType.ADD_RANDOM_MODIFIER) {
            final VaultCrystalCatalystConfig.TaggedPool pool = ModConfigs.VAULT_CRYSTAL_CATALYST.getPool(this.value);
            if (pool != null) {
                name = pool.getDisplayName();
            }
        } else {
            final VaultModifier modifier = ModConfigs.VAULT_MODIFIERS.getByName(this.value);
            if (modifier != null) {
                name.setStyle(Style.EMPTY.withColor(Color.fromRgb(modifier.getColor())));
            }
        }
        return this.type.getDescription((ITextComponent) name);
    }

    @Nullable
    public IFormattableTextComponent getModifierDescription() {
        if (this.type == ModifierRollType.ADD_SPECIFIC_MODIFIER) {
            final VaultModifier modifier = ModConfigs.VAULT_MODIFIERS.getByName(this.value);
            if (modifier != null) {
                return (IFormattableTextComponent) new StringTextComponent(modifier.getDescription());
            }
        }
        return null;
    }

    @Nullable
    public String getModifier(final Random random, final Predicate<String> modifierFilter) {
        if (this.type == ModifierRollType.ADD_SPECIFIC_MODIFIER) {
            if (!modifierFilter.test(this.value)) {
                return this.value;
            }
        } else {
            final VaultCrystalCatalystConfig.TaggedPool pool = ModConfigs.VAULT_CRYSTAL_CATALYST.getPool(this.value);
            if (pool != null) {
                return pool.getModifier(random, modifierFilter);
            }
        }
        return null;
    }

    static {
        CODEC = RecordCodecBuilder.create(rollInstance -> rollInstance
                .group((App) EnumCodec.of(ModifierRollType.class).fieldOf("type").forGetter(outcome -> outcome.type),
                        (App) Codec.STRING.fieldOf("value").forGetter(outcome -> outcome.value))
                .apply((Applicative) rollInstance, (BiFunction) ModifierRollResult::new));
    }
}
