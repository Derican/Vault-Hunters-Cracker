
package iskallia.vault.block.entity;

import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.util.text.ITextComponent;
import iskallia.vault.world.vault.VaultRaid;
import iskallia.vault.world.vault.logic.objective.TreasureHuntObjective;
import net.minecraft.inventory.container.IContainerListener;
import iskallia.vault.world.vault.logic.objective.ScavengerHuntObjective;
import iskallia.vault.world.data.VaultRaidData;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.inventory.IInventory;
import iskallia.vault.container.ScavengerChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.client.particle.SimpleAnimatedParticle;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.client.Minecraft;
import iskallia.vault.init.ModBlocks;
import net.minecraft.util.NonNullList;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import java.util.Random;
import net.minecraft.tileentity.ChestTileEntity;

public class ScavengerChestTileEntity extends ChestTileEntity {
    private static final Random rand;

    protected ScavengerChestTileEntity(final TileEntityType<?> typeIn) {
        super((TileEntityType) typeIn);
        this.setItems(NonNullList.withSize(45, (Object) ItemStack.EMPTY));
    }

    public ScavengerChestTileEntity() {
        this(ModBlocks.SCAVENGER_CHEST_TILE_ENTITY);
    }

    public void tick() {
        super.tick();
        if (this.level.isClientSide()) {
            this.playEffects();
        }
    }

    @OnlyIn(Dist.CLIENT)
    private void playEffects() {
        final ParticleManager mgr = Minecraft.getInstance().particleEngine;
        final BlockPos pos = this.getBlockPos();
        final Vector3d rPos = new Vector3d(
                pos.getX() + 0.5
                        + (ScavengerChestTileEntity.rand.nextFloat() - ScavengerChestTileEntity.rand.nextFloat())
                                * (ScavengerChestTileEntity.rand.nextFloat() * 3.0f),
                pos.getY() + 0.5
                        + (ScavengerChestTileEntity.rand.nextFloat() - ScavengerChestTileEntity.rand.nextFloat())
                                * (ScavengerChestTileEntity.rand.nextFloat() * 7.0f),
                pos.getZ() + 0.5
                        + (ScavengerChestTileEntity.rand.nextFloat() - ScavengerChestTileEntity.rand.nextFloat())
                                * (ScavengerChestTileEntity.rand.nextFloat() * 3.0f));
        final SimpleAnimatedParticle p = (SimpleAnimatedParticle) mgr.createParticle(
                (IParticleData) ParticleTypes.FIREWORK, rPos.x, rPos.y,
                rPos.z, 0.0, 0.0, 0.0);
        if (p != null) {
            p.baseGravity = 0.0f;
            p.setColor(2347008);
        }
    }

    public int getContainerSize() {
        return 45;
    }

    protected Container createMenu(final int id, final PlayerInventory playerInventory) {
        Container ct = (Container) new ScavengerChestContainer(id, playerInventory, (IInventory) this,
                (IInventory) this);
        if (this.level instanceof ServerWorld) {
            final ServerWorld sWorld = (ServerWorld) this.level;
            final VaultRaid vault = VaultRaidData.get(sWorld).getAt(sWorld, this.getBlockPos());
            if (vault != null) {
                ct = vault.getActiveObjective(ScavengerHuntObjective.class).map(objective -> {
                    final Container linkedCt = (Container) new ScavengerChestContainer(id, playerInventory,
                            (IInventory) this, objective.getScavengerChestInventory());
                    linkedCt.addSlotListener((IContainerListener) objective.getChestWatcher());
                    return linkedCt;
                }).orElse(ct);
                ct = vault.getActiveObjective(TreasureHuntObjective.class).map(objective -> {
                    final Container linkedCt2 = (Container) new ScavengerChestContainer(id, playerInventory,
                            (IInventory) this, objective.getScavengerChestInventory());
                    linkedCt2.addSlotListener((IContainerListener) objective.getChestWatcher());
                    return linkedCt2;
                }).orElse(ct);
            }
        }
        return ct;
    }

    public ITextComponent getDisplayName() {
        return (ITextComponent) new TranslationTextComponent(ModBlocks.SCAVENGER_CHEST.getDescriptionId());
    }

    static {
        rand = new Random();
    }
}
