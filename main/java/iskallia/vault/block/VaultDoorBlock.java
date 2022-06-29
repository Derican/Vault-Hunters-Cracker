// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.block;

import java.util.ArrayList;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.Hand;
import net.minecraft.entity.player.PlayerEntity;
import iskallia.vault.init.ModBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.block.material.PushReaction;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.state.properties.DoorHingeSide;
import net.minecraft.state.Property;
import net.minecraft.util.Direction;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import java.util.List;
import net.minecraft.block.DoorBlock;

public class VaultDoorBlock extends DoorBlock
{
    public static final List<VaultDoorBlock> VAULT_DOORS;
    protected Item keyItem;
    
    public VaultDoorBlock(final Item keyItem) {
        super(AbstractBlock.Properties.of(Material.METAL, MaterialColor.DIAMOND).strength(-1.0f, 3600000.0f).sound(SoundType.METAL).noOcclusion());
        this.registerDefaultState((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.getStateDefinition().any()).setValue((Property)VaultDoorBlock.FACING, (Comparable)Direction.NORTH)).setValue((Property)VaultDoorBlock.OPEN, (Comparable)Boolean.FALSE)).setValue((Property)VaultDoorBlock.HINGE, (Comparable)DoorHingeSide.LEFT)).setValue((Property)VaultDoorBlock.POWERED, (Comparable)Boolean.FALSE)).setValue((Property)VaultDoorBlock.HALF, (Comparable)DoubleBlockHalf.LOWER));
        this.keyItem = keyItem;
        VaultDoorBlock.VAULT_DOORS.add(this);
    }
    
    public Item getKeyItem() {
        return this.keyItem;
    }
    
    public PushReaction getPistonPushReaction(final BlockState state) {
        return PushReaction.BLOCK;
    }
    
    public void neighborChanged(final BlockState state, final World worldIn, final BlockPos pos, final Block blockIn, final BlockPos fromPos, final boolean isMoving) {
    }
    
    public boolean hasTileEntity(final BlockState state) {
        return true;
    }
    
    public TileEntity createTileEntity(final BlockState state, final IBlockReader world) {
        return ModBlocks.VAULT_DOOR_TILE_ENTITY.create();
    }
    
    public ActionResultType use(final BlockState state, final World world, final BlockPos pos, final PlayerEntity player, final Hand hand, final BlockRayTraceResult hit) {
        final ItemStack heldStack = player.getItemInHand(hand);
        final Boolean isOpen = (Boolean)state.getValue((Property)VaultDoorBlock.OPEN);
        if (!isOpen && heldStack.getItem() == this.getKeyItem()) {
            heldStack.shrink(1);
            this.setOpen(world, state, pos, true);
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.SUCCESS;
    }
    
    static {
        VAULT_DOORS = new ArrayList<VaultDoorBlock>();
    }
}
