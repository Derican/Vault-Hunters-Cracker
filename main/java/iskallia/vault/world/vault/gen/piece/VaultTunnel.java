
package iskallia.vault.world.vault.gen.piece;

import iskallia.vault.Vault;
import iskallia.vault.world.vault.VaultRaid;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.ResourceLocation;

public class VaultTunnel extends VaultPiece {
    public static final ResourceLocation ID;

    public VaultTunnel() {
        super(VaultTunnel.ID);
    }

    public VaultTunnel(final ResourceLocation template, final MutableBoundingBox boundingBox, final Rotation rotation) {
        super(VaultTunnel.ID, template, boundingBox, rotation);
    }

    @Override
    public void tick(final ServerWorld world, final VaultRaid vault) {
    }

    static {
        ID = Vault.id("tunnel");
    }
}
