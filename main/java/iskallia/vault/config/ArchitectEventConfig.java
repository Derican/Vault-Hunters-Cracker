
package iskallia.vault.config;

import java.util.Arrays;
import iskallia.vault.init.ModBlocks;
import javax.annotation.Nullable;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.Collection;
import java.util.stream.Stream;
import iskallia.vault.world.vault.logic.objective.architect.modifier.VoteModifier;
import iskallia.vault.util.data.WeightedList;
import iskallia.vault.config.entry.RangeEntry;
import iskallia.vault.world.vault.logic.objective.architect.modifier.BossExitModifier;
import iskallia.vault.world.vault.logic.objective.architect.modifier.RandomVoteModifier;
import iskallia.vault.world.vault.logic.objective.architect.modifier.PieceSelectionModifier;
import iskallia.vault.world.vault.logic.objective.architect.modifier.MobSpawnModifier;
import iskallia.vault.world.vault.logic.objective.architect.modifier.TimeModifyModifier;
import iskallia.vault.world.vault.logic.objective.architect.modifier.FloatingItemPlacementModifier;
import iskallia.vault.world.vault.logic.objective.architect.modifier.BlockPlacementModifier;
import java.util.List;
import com.google.gson.annotations.Expose;

public class ArchitectEventConfig extends Config {
    public static final String EVENT_KEY = "architect_event";
    @Expose
    private boolean enabled;
    @Expose
    private List<BlockPlacementModifier> BLOCK_PLACEMENT;
    @Expose
    private List<FloatingItemPlacementModifier> FLOATING_ITEM_PLACEMENT;
    @Expose
    private List<TimeModifyModifier> TIME_MODIFIER;
    @Expose
    private List<MobSpawnModifier> ADDITIONAL_MOB_SPAWNS;
    @Expose
    private List<PieceSelectionModifier> ROOM_SELECTION;
    @Expose
    private List<RandomVoteModifier> RANDOM;
    @Expose
    private BossExitModifier BOSS;
    @Expose
    private RangeEntry requiredPolls;
    @Expose
    private WeightedList<String> rolls;

    public List<VoteModifier> getAll() {
        final List<VoteModifier> modifiers = Stream
                .of((List[]) new List[] { this.BLOCK_PLACEMENT, this.FLOATING_ITEM_PLACEMENT, this.TIME_MODIFIER,
                        this.ADDITIONAL_MOB_SPAWNS, this.ROOM_SELECTION, this.RANDOM })
                .flatMap((Function<? super List, ? extends Stream<?>>) Collection::stream)
                .collect((Collector<? super Object, ?, List<VoteModifier>>) Collectors.toList());
        modifiers.add(this.BOSS);
        return modifiers;
    }

    @Nullable
    public VoteModifier getModifier(final String modifierName) {
        if (modifierName == null) {
            return null;
        }
        return this.getAll().stream().filter(modifier -> modifierName.equalsIgnoreCase(modifier.getName())).findFirst()
                .orElse(null);
    }

    public VoteModifier getBossModifier() {
        return this.BOSS;
    }

    @Nullable
    public VoteModifier generateRandomModifier() {
        return this.getModifier(this.rolls.getRandom(ArchitectEventConfig.rand));
    }

    public int getRandomTotalRequiredPolls() {
        return this.requiredPolls.getRandom();
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public String getName() {
        return "architect_event";
    }

    @Override
    protected void reset() {
        this.BLOCK_PLACEMENT = Arrays.asList(
                new BlockPlacementModifier("Treasure", "+Gilded Chests", 30, ModBlocks.VAULT_BONUS_CHEST, 6000));
        this.FLOATING_ITEM_PLACEMENT = Arrays.asList(
                new FloatingItemPlacementModifier("Gems", "+Gems", 15, 4500,
                        FloatingItemPlacementModifier.defaultGemList()),
                new FloatingItemPlacementModifier("Prismatic", "+Catalysts", 15, 12000,
                        FloatingItemPlacementModifier.defaultPrismaticList()),
                new FloatingItemPlacementModifier("VaultGear", "+Vault Gear", 15, 16000,
                        FloatingItemPlacementModifier.defaultVaultGearList()));
        this.TIME_MODIFIER = Arrays.asList(new TimeModifyModifier("MoreTime", "+3 Minutes", 0, 3600),
                new TimeModifyModifier("LessTime", "-3 Minutes", 0, -3600));
        this.ADDITIONAL_MOB_SPAWNS = Arrays.asList(new MobSpawnModifier("Crowded", "+Monsters", -10, 4000),
                new MobSpawnModifier("Chaotic", "+++Monsters", -20, 1800));
        this.ROOM_SELECTION = Arrays.asList(new PieceSelectionModifier("OmegaRooms", "+% Omega Room", 60, 0.5f,
                Arrays.asList("the_vault:vault/enigma/rooms/mineshaft/", "the_vault:vault/enigma/rooms/x_spot/",
                        "the_vault:vault/enigma/rooms/digsite/")));
        this.RANDOM = Arrays.asList(new RandomVoteModifier("Random", "? Random ?", -15));
        this.BOSS = new BossExitModifier("BossExit", "Summon Boss", 0, 0.1f);
        this.requiredPolls = new RangeEntry(12, 20);
        this.enabled = false;
        (this.rolls = new WeightedList<String>()).add("None", 20);
        this.rolls.add("Random", 5);
        this.rolls.add("Treasure", 2);
        this.rolls.add("Gems", 2);
        this.rolls.add("Prismatic", 2);
        this.rolls.add("VaultGear", 2);
        this.rolls.add("MoreTime", 2);
        this.rolls.add("LessTime", 2);
        this.rolls.add("Crowded", 2);
        this.rolls.add("Chaotic", 2);
        this.rolls.add("OmegaRooms", 2);
    }
}
