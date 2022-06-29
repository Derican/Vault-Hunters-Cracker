
package iskallia.vault.config;

import java.util.Optional;
import com.google.common.collect.Lists;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.MathHelper;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.gson.annotations.Expose;
import java.util.List;
import java.util.Map;

public class FinalRaidConfig extends Config {
    @Expose
    private final Map<String, List<RaidConfig.Level>> mobPools;
    @Expose
    private final List<RaidConfig.WaveSetup> raidWaves;

    public FinalRaidConfig() {
        this.mobPools = new HashMap<String, List<RaidConfig.Level>>();
        this.raidWaves = new ArrayList<RaidConfig.WaveSetup>();
    }

    @Nullable
    public RaidConfig.MobPool getPool(final String pool, final int level) {
        final List<RaidConfig.Level> mobLevelPool = this.mobPools.get(pool);
        if (mobLevelPool == null) {
            return null;
        }
        return this.getForLevel(mobLevelPool, level).orElse(RaidConfig.DEFAULT).mobPool;
    }

    public RaidConfig.WaveSetup getWaveSetup(int index) {
        index = MathHelper.clamp(index, 0, this.raidWaves.size() - 1);
        return this.raidWaves.get(index);
    }

    @Override
    public String getName() {
        return "final_raid";
    }

    @Override
    protected void reset() {
        this.mobPools.clear();
        this.raidWaves.clear();
        this.mobPools.put("ranged", Lists.newArrayList((Object[]) new RaidConfig.Level[] {
                new RaidConfig.Level(0, new RaidConfig.MobPool().add((EntityType<?>) EntityType.SKELETON, 1)),
                new RaidConfig.Level(75, new RaidConfig.MobPool().add((EntityType<?>) EntityType.SKELETON, 1)
                        .add((EntityType<?>) EntityType.STRAY, 1)) }));
        this.mobPools.put("melee", Lists.newArrayList((Object[]) new RaidConfig.Level[] {
                new RaidConfig.Level(0, new RaidConfig.MobPool().add((EntityType<?>) EntityType.ZOMBIE, 1)),
                new RaidConfig.Level(50, new RaidConfig.MobPool().add((EntityType<?>) EntityType.ZOMBIE, 2)
                        .add((EntityType<?>) EntityType.VINDICATOR, 1)) }));
        final RaidConfig.WaveSetup waveSetup = new RaidConfig.WaveSetup()
                .addWave(new RaidConfig.CompoundWave(new RaidConfig.ConfiguredWave[] {
                        new RaidConfig.ConfiguredWave(2, 3, "ranged"), new RaidConfig.ConfiguredWave(2, 3, "melee") }))
                .addWave(new RaidConfig.CompoundWave(new RaidConfig.ConfiguredWave[] {
                        new RaidConfig.ConfiguredWave(4, 5, "ranged"), new RaidConfig.ConfiguredWave(4, 6, "melee") }))
                .addWave(new RaidConfig.CompoundWave(new RaidConfig.ConfiguredWave[] {
                        new RaidConfig.ConfiguredWave(6, 7, "ranged"), new RaidConfig.ConfiguredWave(5, 8, "melee") }));
        this.raidWaves.add(waveSetup);
        this.raidWaves.add(waveSetup);
        this.raidWaves.add(waveSetup);
    }

    private Optional<RaidConfig.Level> getForLevel(final List<RaidConfig.Level> levels, final int level) {
        int i = 0;
        while (i < levels.size()) {
            if (level < levels.get(i).level) {
                if (i == 0) {
                    break;
                }
                return Optional.of(levels.get(i - 1));
            } else {
                if (i == levels.size() - 1) {
                    return Optional.of(levels.get(i));
                }
                ++i;
            }
        }
        return Optional.empty();
    }
}
