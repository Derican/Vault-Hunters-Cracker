
package iskallia.vault.item;

import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import iskallia.vault.init.ModSounds;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.text.ITextComponent;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.world.server.ServerWorld;
import iskallia.vault.world.data.PlayerVaultStatsData;
import iskallia.vault.util.MathUtilities;
import net.minecraft.entity.player.ServerPlayerEntity;
import iskallia.vault.item.crystal.VaultCrystalItem;
import iskallia.vault.world.vault.VaultRaid;
import iskallia.vault.world.vault.logic.objective.architect.ArchitectObjective;
import iskallia.vault.init.ModItems;
import net.minecraft.util.IItemProvider;
import iskallia.vault.init.ModConfigs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;

public class ItemRelicBoosterPack extends Item {
    public ItemRelicBoosterPack(final ItemGroup group, final ResourceLocation id) {
        super(new Item.Properties().tab(group).stacksTo(64));
        this.setRegistryName(id);
    }

    public ActionResult<ItemStack> use(final World world, final PlayerEntity player, final Hand hand) {
        if (!world.isClientSide) {
            final int rand = world.random.nextInt(100);
            final ItemStack heldStack = player.getItemInHand(hand);
            ItemStack stackToDrop = ItemStack.EMPTY;
            if (rand == 99) {
                final RelicPartItem randomPart = ModConfigs.VAULT_RELICS.getRandomPart();
                stackToDrop = new ItemStack((IItemProvider) randomPart);
                successEffects(world, player.position());
            } else if (rand == 98) {
                stackToDrop = new ItemStack((IItemProvider) ModItems.MYSTERY_BOX);
                successEffects(world, player.position());
            } else if (rand == 97 && "architect_event".equals(getKey(heldStack))) {
                stackToDrop = VaultCrystalItem.getCrystalWithObjective(VaultRaid.ARCHITECT_EVENT.get().getId());
                successEffects(world, player.position());
            } else {
                final ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
                final ServerWorld serverWorld = serverPlayer.getLevel();
                final float coef = MathUtilities.randomFloat(0.1f, 0.25f);
                PlayerVaultStatsData.get(serverWorld).addVaultExp(serverPlayer, (int) (90.0f * coef));
                failureEffects(world, player.position());
            }
            if (!stackToDrop.isEmpty()) {
                player.drop(stackToDrop, false, false);
            }
            heldStack.shrink(1);
        }
        return (ActionResult<ItemStack>) super.use(world, player, hand);
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(final ItemStack stack, @Nullable final World world, final List<ITextComponent> tooltip,
            final ITooltipFlag flagIn) {
        super.appendHoverText(stack, world, (List) tooltip, flagIn);
        if ("architect_event".equals(getKey(stack))) {
            tooltip.add((ITextComponent) new StringTextComponent("Architect").withStyle(TextFormatting.AQUA));
        }
    }

    public static ItemStack getArchitectBoosterPack() {
        final ItemStack stack = new ItemStack((IItemProvider) ModItems.RELIC_BOOSTER_PACK);
        stack.getOrCreateTag().putString("eventKey", "architect_event");
        return stack;
    }

    @Nullable
    public static String getKey(final ItemStack stack) {
        if (!stack.hasTag()) {
            return null;
        }
        return stack.getOrCreateTag().getString("eventKey");
    }

    public static void successEffects(final World world, final Vector3d pos) {
        world.playSound((PlayerEntity) null, pos.x, pos.y, pos.z,
                ModSounds.BOOSTER_PACK_SUCCESS_SFX, SoundCategory.PLAYERS, 1.0f, 1.0f);
        ((ServerWorld) world).sendParticles((IParticleData) ParticleTypes.DRAGON_BREATH, pos.x,
                pos.y, pos.z, 500, 1.0, 1.0, 1.0, 0.5);
    }

    public static void failureEffects(final World world, final Vector3d pos) {
        world.playSound((PlayerEntity) null, pos.x, pos.y, pos.z,
                ModSounds.BOOSTER_PACK_FAIL_SFX, SoundCategory.PLAYERS, 1.0f, 1.0f);
        ((ServerWorld) world).sendParticles((IParticleData) ParticleTypes.SMOKE, pos.x,
                pos.y, pos.z, 500, 1.0, 1.0, 1.0, 0.5);
    }
}
