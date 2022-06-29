
package iskallia.vault.world.vault.gen.layout;

import java.util.HashMap;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.CompoundNBT;
import javax.annotation.Nullable;
import java.util.function.Supplier;
import net.minecraft.util.ResourceLocation;
import java.util.Map;

public class VaultRoomLayoutRegistry {
    private static final Map<ResourceLocation, Supplier<VaultRoomLayoutGenerator>> layoutRegistry;

    public static void init() {
        VaultRoomLayoutRegistry.layoutRegistry.put(SingularVaultRoomLayout.ID,
                (Supplier<VaultRoomLayoutGenerator>) SingularVaultRoomLayout::new);
        VaultRoomLayoutRegistry.layoutRegistry.put(LineRoomLayout.ID,
                (Supplier<VaultRoomLayoutGenerator>) LineRoomLayout::new);
        VaultRoomLayoutRegistry.layoutRegistry.put(DiamondRoomLayout.ID,
                (Supplier<VaultRoomLayoutGenerator>) DiamondRoomLayout::new);
        VaultRoomLayoutRegistry.layoutRegistry.put(SquareRoomLayout.ID,
                (Supplier<VaultRoomLayoutGenerator>) SquareRoomLayout::new);
        VaultRoomLayoutRegistry.layoutRegistry.put(CircleRoomLayout.ID,
                (Supplier<VaultRoomLayoutGenerator>) CircleRoomLayout::new);
        VaultRoomLayoutRegistry.layoutRegistry.put(TriangleRoomLayout.ID,
                (Supplier<VaultRoomLayoutGenerator>) TriangleRoomLayout::new);
        VaultRoomLayoutRegistry.layoutRegistry.put(SpiralRoomLayout.ID,
                (Supplier<VaultRoomLayoutGenerator>) SpiralRoomLayout::new);
        VaultRoomLayoutRegistry.layoutRegistry.put(DebugVaultLayout.ID,
                (Supplier<VaultRoomLayoutGenerator>) DebugVaultLayout::new);
        VaultRoomLayoutRegistry.layoutRegistry.put(DenseDiamondRoomLayout.ID,
                (Supplier<VaultRoomLayoutGenerator>) DenseDiamondRoomLayout::new);
        VaultRoomLayoutRegistry.layoutRegistry.put(DenseSquareRoomLayout.ID,
                (Supplier<VaultRoomLayoutGenerator>) DenseSquareRoomLayout::new);
        VaultRoomLayoutRegistry.layoutRegistry.put(EmptyVaultLayout.ID,
                (Supplier<VaultRoomLayoutGenerator>) EmptyVaultLayout::new);
    }

    @Nullable
    public static VaultRoomLayoutGenerator getLayoutGenerator(final ResourceLocation id) {
        return VaultRoomLayoutRegistry.layoutRegistry.containsKey(id)
                ? VaultRoomLayoutRegistry.layoutRegistry.get(id).get()
                : null;
    }

    @Nullable
    public static VaultRoomLayoutGenerator deserialize(final CompoundNBT tag) {
        if (!tag.contains("Id", 8)) {
            return null;
        }
        final VaultRoomLayoutGenerator layout = getLayoutGenerator(new ResourceLocation(tag.getString("Id")));
        if (layout == null) {
            return null;
        }
        layout.deserialize(tag.getCompound("Data"));
        layout.generateLayout();
        return layout;
    }

    public static CompoundNBT serialize(final VaultRoomLayoutGenerator roomLayout) {
        final CompoundNBT layoutTag = new CompoundNBT();
        layoutTag.putString("Id", roomLayout.getId().toString());
        layoutTag.put("Data", (INBT) roomLayout.serialize());
        return layoutTag;
    }

    static {
        layoutRegistry = new HashMap<ResourceLocation, Supplier<VaultRoomLayoutGenerator>>();
    }
}
