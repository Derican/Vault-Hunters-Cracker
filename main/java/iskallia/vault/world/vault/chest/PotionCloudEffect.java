// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.world.vault.chest;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.IItemProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.world.server.ServerWorld;
import iskallia.vault.world.vault.player.VaultPlayer;
import iskallia.vault.world.vault.VaultRaid;
import net.minecraft.util.registry.Registry;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import net.minecraft.util.ResourceLocation;
import java.util.function.Predicate;
import java.util.Objects;
import java.util.function.Function;
import net.minecraftforge.registries.ForgeRegistryEntry;
import java.util.Arrays;
import net.minecraft.potion.Potion;
import com.google.gson.annotations.Expose;
import java.util.List;

public class PotionCloudEffect extends VaultChestEffect
{
    @Expose
    public List<String> potions;
    
    public PotionCloudEffect(final String name, final Potion... potions) {
        super(name);
        this.potions = Arrays.stream(potions).map((Function<? super Potion, ?>)ForgeRegistryEntry::getRegistryName).filter(Objects::nonNull).map((Function<? super Object, ?>)ResourceLocation::toString).collect((Collector<? super Object, ?, List<String>>)Collectors.toList());
    }
    
    public List<Potion> getPotions() {
        return this.potions.stream().map(s -> Registry.POTION.getOptional(new ResourceLocation(s)).orElse(null)).filter(Objects::nonNull).collect((Collector<? super Object, ?, List<Potion>>)Collectors.toList());
    }
    
    @Override
    public void apply(final VaultRaid vault, final VaultPlayer player, final ServerWorld world) {
        player.runIfPresent(world.getServer(), playerEntity -> {
            final PotionEntity entity = new PotionEntity((World)world, (LivingEntity)playerEntity);
            final ItemStack stack = new ItemStack((IItemProvider)Items.LINGERING_POTION);
            this.getPotions().forEach(potion -> PotionUtils.setPotion(stack, potion));
            entity.setItem(stack);
            entity.shootFromRotation((Entity)playerEntity, playerEntity.xRot, playerEntity.yRot, -20.0f, 0.5f, 1.0f);
            world.addFreshEntity((Entity)entity);
        });
    }
}
