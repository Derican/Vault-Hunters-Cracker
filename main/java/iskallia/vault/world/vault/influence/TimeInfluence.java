
package iskallia.vault.world.vault.influence;

import iskallia.vault.Vault;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.CompoundNBT;
import iskallia.vault.world.vault.time.extension.TimeExtension;
import iskallia.vault.world.vault.time.extension.ModifierExtension;
import java.util.Random;
import net.minecraft.world.server.ServerWorld;
import iskallia.vault.world.vault.player.VaultPlayer;
import iskallia.vault.world.vault.VaultRaid;
import net.minecraft.util.ResourceLocation;

public class TimeInfluence extends VaultInfluence {
    public static final ResourceLocation ID;
    private int timeChange;

    TimeInfluence() {
        super(TimeInfluence.ID);
    }

    public TimeInfluence(final int timeChange) {
        this();
        this.timeChange = timeChange;
    }

    @Override
    public void apply(final VaultRaid vault, final VaultPlayer player, final ServerWorld world, final Random random) {
        player.getTimer().addTime(new ModifierExtension(this.timeChange), 0);
    }

    @Override
    public void remove(final VaultRaid vault, final VaultPlayer player, final ServerWorld world, final Random random) {
        player.getTimer().addTime(new ModifierExtension(-this.timeChange), 0);
    }

    @Override
    public CompoundNBT serializeNBT() {
        final CompoundNBT tag = super.serializeNBT();
        tag.putInt("timeChange", this.timeChange);
        return tag;
    }

    @Override
    public void deserializeNBT(final CompoundNBT tag) {
        super.deserializeNBT(tag);
        this.timeChange = tag.getInt("timeChange");
    }

    static {
        ID = Vault.id("time");
    }
}
