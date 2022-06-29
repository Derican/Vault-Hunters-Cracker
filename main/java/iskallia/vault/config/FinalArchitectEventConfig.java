
package iskallia.vault.config;

import java.util.Arrays;
import javax.annotation.Nullable;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.Collection;
import java.util.stream.Stream;
import iskallia.vault.world.vault.logic.objective.architect.modifier.VoteModifier;
import iskallia.vault.util.data.WeightedList;
import iskallia.vault.world.vault.logic.objective.architect.modifier.FinalVaultTimeModifier;
import iskallia.vault.world.vault.logic.objective.architect.modifier.FinalVaultModifierModifier;
import iskallia.vault.world.vault.logic.objective.architect.modifier.FinalMobHealthModifier;
import com.google.gson.annotations.Expose;
import iskallia.vault.world.vault.logic.objective.architect.modifier.FinalKnowledgeModifier;
import java.util.List;

public class FinalArchitectEventConfig extends Config {
    @Expose
    private List<FinalKnowledgeModifier> KNOWLEDGE_MODIFIERS;
    @Expose
    private List<FinalMobHealthModifier> MOB_HEALTH_MODIFIERS;
    @Expose
    private List<FinalVaultModifierModifier> VAULT_MODIFIER_MODIFIERS;
    @Expose
    private List<FinalVaultTimeModifier> VAULT_TIME_MODIFIERS;
    @Expose
    private int bossKillsNeeded;
    @Expose
    private int totalKnowledgeNeeded;
    @Expose
    private WeightedList<ModifierPair> pairs;

    @Override
    public String getName() {
        return "final_architect_event";
    }

    public List<VoteModifier> getAll() {
        return Stream
                .of((List[]) new List[] { this.KNOWLEDGE_MODIFIERS, this.MOB_HEALTH_MODIFIERS,
                        this.VAULT_MODIFIER_MODIFIERS, this.VAULT_TIME_MODIFIERS })
                .flatMap((Function<? super List, ? extends Stream<?>>) Collection::stream)
                .collect((Collector<? super Object, ?, List<VoteModifier>>) Collectors.toList());
    }

    @Nullable
    public VoteModifier getModifier(final String modifierName) {
        if (modifierName == null) {
            return null;
        }
        return this.getAll().stream().filter(modifier -> modifierName.equalsIgnoreCase(modifier.getName())).findFirst()
                .orElse(null);
    }

    public ModifierPair getRandomPair() {
        return this.pairs.getRandom(FinalArchitectEventConfig.rand);
    }

    public int getBossKillsNeeded() {
        return this.bossKillsNeeded;
    }

    public int getTotalKnowledgeNeeded() {
        return this.totalKnowledgeNeeded;
    }

    @Override
    protected void reset() {
        this.KNOWLEDGE_MODIFIERS = Arrays.asList(new FinalKnowledgeModifier("Knowledge1", "+3 Knowledge", 3),
                new FinalKnowledgeModifier("Knowledge2", "+5 Knowledge", 5));
        this.MOB_HEALTH_MODIFIERS = Arrays.asList(new FinalMobHealthModifier("MobHealth1", "+10% Mob Health", 0.1f),
                new FinalMobHealthModifier("MobHealth2", "+20% Mob Health", 0.2f));
        this.VAULT_MODIFIER_MODIFIERS = Arrays
                .asList(new FinalVaultModifierModifier("AddCrowded", "Add Crowded", "Crowded"));
        this.VAULT_TIME_MODIFIERS = Arrays.asList(new FinalVaultTimeModifier("AddMinute", "Adds 1 Minute", 60),
                new FinalVaultTimeModifier("RemoveMinute", "Removes 1 Minute", -60));
        this.bossKillsNeeded = 10;
        this.totalKnowledgeNeeded = 20;
        (this.pairs = new WeightedList<ModifierPair>()).add(new ModifierPair("Knowledge1", "AddCrowded"), 5);
        this.pairs.add(new ModifierPair("Knowledge2", "RemoveMinute"), 10);
    }

    public static class ModifierPair {
        @Expose
        private String positive;
        @Expose
        private String negative;

        public ModifierPair(final String positive, final String negative) {
            this.positive = positive;
            this.negative = negative;
        }

        public String getPositive() {
            return this.positive;
        }

        public String getNegative() {
            return this.negative;
        }
    }
}
