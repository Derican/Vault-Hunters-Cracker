
package iskallia.vault.world.data;

import java.util.function.Supplier;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.nbt.INBT;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Iterator;
import java.util.ArrayList;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.registry.Registry;
import net.minecraft.potion.Effect;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import net.minecraft.util.ResourceLocation;
import java.util.function.Predicate;
import java.util.Objects;
import net.minecraftforge.registries.ForgeRegistryEntry;
import java.util.function.Function;
import java.util.Collections;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.Map;
import net.minecraft.world.storage.WorldSavedData;

public class PlayerImmunityData extends WorldSavedData {
    protected static final String DATA_NAME = "the_vault_PlayerImmunity";
    protected Map<UUID, List<String>> effects;

    public PlayerImmunityData() {
        this("the_vault_PlayerImmunity");
    }

    public PlayerImmunityData(final String name) {
        super(name);
        this.effects = new HashMap<UUID, List<String>>();
    }

    public void addEffect(final PlayerEntity player, final EffectInstance effectInstance) {
        this.addEffect(player.getUUID(), effectInstance);
    }

    private void addEffect(final UUID playerUUID, final EffectInstance effectInstance) {
        this.effects.put(playerUUID,
                Collections.singletonList(effectInstance.getEffect().getRegistryName().toString()));
        this.setDirty();
    }

    public void addEffects(final PlayerEntity player, final List<EffectInstance> effects) {
        this.addEffects(player.getUUID(), effects);
    }

    public void addEffects(final UUID playerUUID, final List<EffectInstance> effects) {
        this.effects.put(playerUUID,
                effects.stream().map((Function<? super Object, ?>) EffectInstance::getEffect)
                        .map((Function<? super Object, ?>) ForgeRegistryEntry::getRegistryName).filter(Objects::nonNull)
                        .map((Function<? super Object, ?>) ResourceLocation::toString)
                        .collect((Collector<? super Object, ?, List<String>>) Collectors.toList()));
        this.setDirty();
    }

    public List<Effect> getEffects(final UUID playerUUID) {
        return Registry.MOB_EFFECT.stream()
                .filter(effect -> this.effects.get(playerUUID).contains(effect.getRegistryName().toString()))
                .collect((Collector<? super Object, ?, List<Effect>>) Collectors.toList());
    }

    public void removeEffects(final PlayerEntity player) {
        this.removeEffects(player.getUUID());
    }

    public void removeEffects(final UUID playerUUID) {
        this.effects.remove(playerUUID);
        this.setDirty();
    }

    public void load(final CompoundNBT nbt) {
        this.effects.clear();
        for (final String key : nbt.getAllKeys()) {
            UUID uuid;
            try {
                uuid = UUID.fromString(key);
            } catch (final IllegalArgumentException ignored) {
                continue;
            }
            final List<String> effects = new ArrayList<String>();
            final CompoundNBT effectTag = nbt.getCompound(key);
            effectTag.getAllKeys().forEach(effectKey -> effects.add(effectTag.getString(effectKey)));
            this.effects.put(uuid, effects);
        }
    }

    public CompoundNBT save(final CompoundNBT compound) {
        this.effects.forEach((uuid, effects) -> {
            final CompoundNBT effectTag = new CompoundNBT();
            final AtomicInteger index = new AtomicInteger();
            effects.forEach(effectId -> effectTag.putString("Effect" + index.getAndIncrement(), effectId));
            compound.put(uuid.toString(), (INBT) effectTag);
            return;
        });
        return compound;
    }

    public static PlayerImmunityData get(final ServerWorld world) {
        return (PlayerImmunityData) world.getServer().overworld().getDataStorage()
                .computeIfAbsent((Supplier) PlayerImmunityData::new, "the_vault_PlayerImmunity");
    }
}
