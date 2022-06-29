// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.world.vault.time;

import net.minecraft.nbt.INBT;
import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.List;
import iskallia.vault.world.vault.time.extension.TimeExtension;
import iskallia.vault.nbt.VListNBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public class VaultTimer implements INBTSerializable<CompoundNBT>
{
    public int startTime;
    public int totalTime;
    public int runTime;
    protected VListNBT<TimeExtension, CompoundNBT> extensions;
    protected List<BiConsumer<VaultTimer, TimeExtension>> extensionAddedListeners;
    protected List<BiConsumer<VaultTimer, TimeExtension>> extensionAppliedListeners;
    
    public VaultTimer() {
        this.extensions = VListNBT.of(TimeExtension::fromNBT);
        this.extensionAddedListeners = new ArrayList<BiConsumer<VaultTimer, TimeExtension>>();
        this.extensionAppliedListeners = new ArrayList<BiConsumer<VaultTimer, TimeExtension>>();
    }
    
    public int getStartTime() {
        return this.startTime;
    }
    
    public int getTotalTime() {
        return this.totalTime;
    }
    
    public int getRunTime() {
        return this.runTime;
    }
    
    public int getTimeLeft() {
        return this.totalTime - this.runTime;
    }
    
    public VaultTimer addTime(final TimeExtension extension, final int delay) {
        extension.setExecutionTime(this.runTime + delay);
        this.extensions.add(extension);
        this.extensionAddedListeners.forEach(listener -> listener.accept(this, extension));
        return this;
    }
    
    public VaultTimer onExtensionAdded(final BiConsumer<VaultTimer, TimeExtension> listener) {
        this.extensionAddedListeners.add(listener);
        return this;
    }
    
    public VaultTimer onExtensionApplied(final BiConsumer<VaultTimer, TimeExtension> listener) {
        this.extensionAppliedListeners.add(listener);
        return this;
    }
    
    public VaultTimer start(final int startTime) {
        this.runTime = 0;
        this.startTime = startTime;
        this.totalTime = this.startTime;
        return this;
    }
    
    public void tick() {
        this.extensions.forEach(extension -> {
            if (extension.getExecutionTime() == this.runTime) {
                extension.apply(this);
                this.extensionAppliedListeners.forEach(listener -> listener.accept(this, extension));
            }
            return;
        });
        ++this.runTime;
    }
    
    public CompoundNBT serializeNBT() {
        final CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("StartTime", this.startTime);
        nbt.putInt("TotalTime", this.totalTime);
        nbt.putInt("RunTime", this.runTime);
        nbt.put("TimeExtensions", (INBT)this.extensions.serializeNBT());
        return nbt;
    }
    
    public void deserializeNBT(final CompoundNBT nbt) {
        this.startTime = nbt.getInt("StartTime");
        this.totalTime = nbt.getInt("TotalTime");
        this.runTime = nbt.getInt("RunTime");
        this.extensions.deserializeNBT(nbt.getList("TimeExtensions", 10));
    }
}
