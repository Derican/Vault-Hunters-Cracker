// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.world.data.generated;

import java.util.ArrayList;
import net.minecraft.nbt.INBT;
import net.minecraft.util.IItemProvider;
import iskallia.vault.init.ModItems;
import iskallia.vault.world.vault.logic.objective.ScavengerHuntObjective;
import iskallia.vault.world.vault.VaultRaid;
import iskallia.vault.world.vault.logic.objective.SummonAndKillBossObjective;
import iskallia.vault.item.crystal.CrystalData;
import java.util.Collection;
import iskallia.vault.util.MiscUtils;
import java.util.Random;
import net.minecraft.util.math.MathHelper;
import net.minecraft.item.ItemStack;
import java.util.List;

public class ChallengeCrystalArchive
{
    private static final List<ItemStack> generatedCrystals;
    
    public static ItemStack get(final int index) {
        if (ChallengeCrystalArchive.generatedCrystals.isEmpty()) {
            initialize();
        }
        return ChallengeCrystalArchive.generatedCrystals.get(MathHelper.clamp(index, 0, ChallengeCrystalArchive.generatedCrystals.size() - 1)).copy();
    }
    
    public static ItemStack getRandom() {
        final ItemStack stack = getRandom(new Random());
        return (stack == null) ? null : stack.copy();
    }
    
    public static ItemStack getRandom(final Random rand) {
        if (ChallengeCrystalArchive.generatedCrystals.isEmpty()) {
            initialize();
        }
        return MiscUtils.getRandomEntry(ChallengeCrystalArchive.generatedCrystals, rand);
    }
    
    private static void initialize() {
        final CrystalData grail = baseData();
        grail.setType(CrystalData.Type.CLASSIC);
        grail.setSelectedObjective(VaultRaid.SUMMON_AND_KILL_BOSS.get().getId());
        grail.setTargetObjectiveCount(6);
        grail.addModifier("Treasure");
        grail.addModifier("Treasure");
        grail.addModifier("Treasure");
        grail.addModifier("Safe Zone");
        grail.addModifier("Locked");
        grail.addModifier("Rush");
        grail.addModifier("Rush");
        ChallengeCrystalArchive.generatedCrystals.add(make(grail));
        final CrystalData gambler = baseData();
        gambler.setType(CrystalData.Type.CLASSIC);
        gambler.setSelectedObjective(VaultRaid.SCAVENGER_HUNT.get().getId());
        gambler.setTargetObjectiveCount(6);
        gambler.addGuaranteedRoom("digsite", 10);
        gambler.addModifier("Super Lucky");
        gambler.addModifier("Super Lucky");
        gambler.addModifier("Rotten");
        ChallengeCrystalArchive.generatedCrystals.add(make(gambler));
        final CrystalData speed = baseData();
        speed.setType(CrystalData.Type.CLASSIC);
        speed.setSelectedObjective(VaultRaid.SUMMON_AND_KILL_BOSS.get().getId());
        speed.setTargetObjectiveCount(10);
        speed.addModifier("Odyssey");
        speed.addModifier("Odyssey");
        speed.addModifier("Rush");
        speed.addModifier("Rush");
        speed.addModifier("Locked");
        speed.addModifier("Rotten");
        ChallengeCrystalArchive.generatedCrystals.add(make(speed));
        final CrystalData miner = baseData();
        miner.setType(CrystalData.Type.CLASSIC);
        miner.setSelectedObjective(VaultRaid.SUMMON_AND_KILL_BOSS.get().getId());
        miner.setTargetObjectiveCount(6);
        miner.addGuaranteedRoom("mineshaft", 10);
        miner.addModifier("Copious");
        miner.addModifier("Copious");
        miner.addModifier("Copious");
        miner.addModifier("Rush");
        miner.addModifier("Rush");
        miner.addModifier("Locked");
        ChallengeCrystalArchive.generatedCrystals.add(make(miner));
        final CrystalData impossible = baseData();
        impossible.setType(CrystalData.Type.CLASSIC);
        impossible.setSelectedObjective(VaultRaid.SUMMON_AND_KILL_BOSS.get().getId());
        impossible.setTargetObjectiveCount(10);
        impossible.addModifier("Impossible");
        impossible.addModifier("Impossible");
        impossible.addModifier("Frenzy");
        impossible.addModifier("Odyssey");
        impossible.addModifier("Odyssey");
        impossible.addModifier("Locked");
        ChallengeCrystalArchive.generatedCrystals.add(make(impossible));
        final CrystalData trap = baseData();
        trap.setType(CrystalData.Type.CLASSIC);
        trap.setSelectedObjective(VaultRaid.SUMMON_AND_KILL_BOSS.get().getId());
        trap.setTargetObjectiveCount(1);
        trap.addModifier("Treasure");
        trap.addModifier("Treasure");
        trap.addModifier("Treasure");
        trap.addModifier("Treasure");
        trap.addModifier("Trapped");
        trap.addModifier("Trapped");
        trap.addModifier("Trapped");
        trap.addModifier("Super Lucky");
        trap.addModifier("Super Lucky");
        trap.addModifier("Super Lucky");
        ChallengeCrystalArchive.generatedCrystals.add(make(trap));
        final CrystalData dream = new CrystalData();
        dream.setChallenge(true);
        dream.setModifiable(false);
        dream.setCanTriggerInfluences(false);
        dream.setCanGenerateTreasureRooms(false);
        dream.setType(CrystalData.Type.CLASSIC);
        dream.setSelectedObjective(VaultRaid.SUMMON_AND_KILL_BOSS.get().getId());
        dream.setTargetObjectiveCount(10);
        dream.addModifier("Prismatic");
        dream.addModifier("Prismatic");
        dream.addModifier("Prismatic");
        dream.addModifier("Crowded");
        dream.addModifier("Safe Zone");
        dream.addModifier("Rotten");
        dream.addModifier("Locked");
        ChallengeCrystalArchive.generatedCrystals.add(make(dream));
        final CrystalData soul = baseData();
        soul.setType(CrystalData.Type.CLASSIC);
        soul.setSelectedObjective(VaultRaid.SUMMON_AND_KILL_BOSS.get().getId());
        soul.setTargetObjectiveCount(10);
        soul.addModifier("Impossible");
        soul.addModifier("Impossible");
        soul.addModifier("Impossible");
        soul.addModifier("Locked");
        soul.addModifier("Destructive");
        soul.addModifier("Raging");
        ChallengeCrystalArchive.generatedCrystals.add(make(soul));
        final CrystalData dejavu = baseData();
        dejavu.setType(CrystalData.Type.CLASSIC);
        dejavu.setSelectedObjective(VaultRaid.SUMMON_AND_KILL_BOSS.get().getId());
        dejavu.setTargetObjectiveCount(10);
        dejavu.addGuaranteedRoom("dungeons", 500);
        dejavu.addModifier("Exploration");
        dejavu.addModifier("Copious");
        ChallengeCrystalArchive.generatedCrystals.add(make(dejavu));
        final CrystalData village = baseData();
        village.setType(CrystalData.Type.CLASSIC);
        village.setSelectedObjective(VaultRaid.SUMMON_AND_KILL_BOSS.get().getId());
        village.setTargetObjectiveCount(5);
        village.addGuaranteedRoom("village", 500);
        village.addModifier("Rush");
        village.addModifier("Rotten");
        village.addModifier("Lucky");
        ChallengeCrystalArchive.generatedCrystals.add(make(village));
        final CrystalData puzzle = baseData();
        puzzle.setType(CrystalData.Type.CLASSIC);
        puzzle.setSelectedObjective(VaultRaid.SUMMON_AND_KILL_BOSS.get().getId());
        puzzle.setTargetObjectiveCount(5);
        puzzle.addGuaranteedRoom("puzzle_cube", 500);
        puzzle.addModifier("Rush");
        puzzle.addModifier("Rotten");
        puzzle.addModifier("Trapped");
        puzzle.addModifier("Trapped");
        ChallengeCrystalArchive.generatedCrystals.add(make(puzzle));
        final CrystalData frenzied = baseData();
        frenzied.setType(CrystalData.Type.CLASSIC);
        frenzied.setSelectedObjective(VaultRaid.SUMMON_AND_KILL_BOSS.get().getId());
        frenzied.setTargetObjectiveCount(5);
        frenzied.addGuaranteedRoom("digsite", 500);
        frenzied.addModifier("Frenzy");
        frenzied.addModifier("Impossible");
        frenzied.addModifier("Impossible");
        frenzied.addModifier("Safe Zone");
        frenzied.addModifier("Super Lucky");
        frenzied.addModifier("Locked");
        ChallengeCrystalArchive.generatedCrystals.add(make(frenzied));
    }
    
    private static ItemStack make(final CrystalData data) {
        final ItemStack crystal = new ItemStack((IItemProvider)ModItems.VAULT_CRYSTAL);
        crystal.getOrCreateTag().put("CrystalData", (INBT)data.serializeNBT());
        return crystal;
    }
    
    private static CrystalData baseData() {
        final CrystalData data = new CrystalData();
        data.setModifiable(false);
        data.setCanTriggerInfluences(false);
        data.setCanGenerateTreasureRooms(false);
        data.setPreventsRandomModifiers(true);
        data.setChallenge(true);
        return data;
    }
    
    static {
        generatedCrystals = new ArrayList<ItemStack>();
    }
}
