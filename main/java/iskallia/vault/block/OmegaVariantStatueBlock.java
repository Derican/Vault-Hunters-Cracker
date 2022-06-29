
package iskallia.vault.block;

import net.minecraft.network.PacketBuffer;
import net.minecraft.block.Block;
import net.minecraft.state.StateContainer;
import java.util.Iterator;
import java.util.List;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.network.NetworkHooks;
import iskallia.vault.container.OmegaStatueContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.nbt.INBT;
import iskallia.vault.init.ModConfigs;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.CompoundNBT;
import iskallia.vault.block.entity.LootStatueTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import javax.annotation.Nullable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.state.Property;
import net.minecraft.util.Direction;
import net.minecraft.block.BlockState;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import iskallia.vault.util.StatueType;

public class OmegaVariantStatueBlock extends LootStatueBlock {
    public OmegaVariantStatueBlock() {
        super(StatueType.OMEGA_VARIANT,
                AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE)
                        .strength(1.0f, 3600000.0f).noOcclusion());
        this.registerDefaultState((BlockState) ((BlockState) this.getStateDefinition().any())
                .setValue((Property) OmegaVariantStatueBlock.FACING, (Comparable) Direction.SOUTH));
    }

    @Override
    public void setPlacedBy(final World worldIn, final BlockPos pos, final BlockState state,
            @Nullable final LivingEntity placer, final ItemStack stack) {
        if (worldIn.isClientSide || !(placer instanceof PlayerEntity)) {
            return;
        }
        final PlayerEntity player = (PlayerEntity) placer;
        final TileEntity tileEntity = worldIn.getBlockEntity(pos);
        if (tileEntity instanceof LootStatueTileEntity) {
            final LootStatueTileEntity lootStatue = (LootStatueTileEntity) tileEntity;
            if (stack.hasTag()) {
                final CompoundNBT nbt = stack.getTag();
                final CompoundNBT blockEntityTag = nbt.getCompound("BlockEntityTag");
                this.setStatueTileData(lootStatue, blockEntityTag);
                if (nbt.contains("LootItem")) {
                    lootStatue.setLootItem(ItemStack.of(blockEntityTag.getCompound("LootItem")));
                }
                lootStatue.setChanged();
                lootStatue.sendUpdates();
                if (lootStatue.getLootItem() == null || lootStatue.getLootItem().isEmpty()) {
                    final CompoundNBT data = new CompoundNBT();
                    final ListNBT itemList = new ListNBT();
                    final List<ItemStack> options = ModConfigs.STATUE_LOOT.getOmegaOptions();
                    for (final ItemStack option : options) {
                        itemList.add((Object) option.serializeNBT());
                    }
                    data.put("Items", (INBT) itemList);
                    data.put("Position", (INBT) NBTUtil.writeBlockPos(pos));
                    NetworkHooks.openGui((ServerPlayerEntity) player,
                            (INamedContainerProvider) new INamedContainerProvider() {
                                public ITextComponent getDisplayName() {
                                    return (ITextComponent) new StringTextComponent("Omega Statue Options");
                                }

                                @Nullable
                                public Container createMenu(final int windowId, final PlayerInventory playerInventory,
                                        final PlayerEntity playerEntity) {
                                    return new OmegaStatueContainer(windowId, data);
                                }
                            }, buffer -> buffer.writeNbt(data));
                }
            }
        }
    }

    @Override
    protected void setStatueTileData(final LootStatueTileEntity lootStatue, final CompoundNBT blockEntityTag) {
        final StatueType statueType = StatueType.values()[blockEntityTag.getInt("StatueType")];
        final String playerNickname = blockEntityTag.getString("PlayerNickname");
        lootStatue.setStatueType(StatueType.OMEGA_VARIANT);
        lootStatue.setCurrentTick(blockEntityTag.getInt("CurrentTick"));
        lootStatue.getSkin().updateSkin(playerNickname);
        lootStatue.setLootItem(ItemStack.of(blockEntityTag.getCompound("LootItem")));
    }

    @Override
    protected void createBlockStateDefinition(final StateContainer.Builder<Block, BlockState> builder) {
        builder.add(new Property[] { (Property) OmegaVariantStatueBlock.FACING });
    }
}
