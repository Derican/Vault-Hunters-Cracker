
package iskallia.vault.skill;

import java.util.Collection;
import java.util.Arrays;
import iskallia.vault.skill.talent.TalentNode;
import iskallia.vault.skill.talent.ArchetypeTalentGroup;
import iskallia.vault.skill.talent.TalentTree;
import iskallia.vault.research.ResearchTree;
import iskallia.vault.research.type.Research;
import iskallia.vault.skill.talent.TalentGroup;
import java.util.Iterator;
import iskallia.vault.init.ModConfigs;
import java.util.LinkedList;
import iskallia.vault.skill.ability.AbilityGroup;
import java.util.List;
import java.util.HashMap;
import com.google.gson.annotations.Expose;
import java.util.Map;

public class SkillGates {
    @Expose
    private final Map<String, Entry> entries;

    public SkillGates() {
        this.entries = new HashMap<String, Entry>();
    }

    public void addEntry(final String skillName, final Entry entry) {
        this.entries.put(skillName, entry);
    }

    public List<AbilityGroup<?, ?>> getDependencyAbilities(final String abilityName) {
        final List<AbilityGroup<?, ?>> abilities = new LinkedList<AbilityGroup<?, ?>>();
        final Entry entry = this.entries.get(abilityName);
        if (entry == null) {
            return abilities;
        }
        entry.dependsOn.forEach(dependencyName -> {
            final AbilityGroup<?, ?> dependency = ModConfigs.ABILITIES.getAbilityGroupByName(dependencyName);
            abilities.add(dependency);
            return;
        });
        return abilities;
    }

    public List<AbilityGroup<?, ?>> getLockedByAbilities(final String abilityName) {
        final List<AbilityGroup<?, ?>> abilities = new LinkedList<AbilityGroup<?, ?>>();
        final Entry entry = this.entries.get(abilityName);
        if (entry == null) {
            return abilities;
        }
        entry.lockedBy.forEach(dependencyName -> {
            final AbilityGroup<?, ?> dependency = ModConfigs.ABILITIES.getAbilityGroupByName(dependencyName);
            abilities.add(dependency);
            return;
        });
        return abilities;
    }

    public List<AbilityGroup<?, ?>> getAbilitiesDependingOn(final String abilityName) {
        final List<AbilityGroup<?, ?>> abilities = new LinkedList<AbilityGroup<?, ?>>();
        final AbilityGroup<?, ?> ability = ModConfigs.ABILITIES.getAbilityGroupByName(abilityName);
        for (final AbilityGroup<?, ?> otherAbility : ModConfigs.ABILITIES.getAll()) {
            final List<AbilityGroup<?, ?>> dependencies = ModConfigs.SKILL_GATES.getGates()
                    .getDependencyAbilities(otherAbility.getParentName());
            if (dependencies.contains(ability)) {
                abilities.add(otherAbility);
            }
        }
        return abilities;
    }

    public List<TalentGroup<?>> getDependencyTalents(final String talentName) {
        final List<TalentGroup<?>> talents = new LinkedList<TalentGroup<?>>();
        final Entry entry = this.entries.get(talentName);
        if (entry == null) {
            return talents;
        }
        entry.dependsOn.forEach(dependencyName -> {
            final TalentGroup<?> dependency = ModConfigs.TALENTS.getByName(dependencyName);
            talents.add(dependency);
            return;
        });
        return talents;
    }

    public List<TalentGroup<?>> getLockedByTalents(final String talentName) {
        final List<TalentGroup<?>> talents = new LinkedList<TalentGroup<?>>();
        final Entry entry = this.entries.get(talentName);
        if (entry == null) {
            return talents;
        }
        entry.lockedBy.forEach(dependencyName -> {
            final TalentGroup<?> dependency = ModConfigs.TALENTS.getByName(dependencyName);
            talents.add(dependency);
            return;
        });
        return talents;
    }

    public List<TalentGroup<?>> getTalentsDependingOn(final String talentName) {
        final List<TalentGroup<?>> talents = new LinkedList<TalentGroup<?>>();
        final TalentGroup<?> talent = ModConfigs.TALENTS.getByName(talentName);
        for (final TalentGroup<?> otherTalent : ModConfigs.TALENTS.getAll()) {
            final List<TalentGroup<?>> dependencies = ModConfigs.SKILL_GATES.getGates()
                    .getDependencyTalents(otherTalent.getParentName());
            if (dependencies.contains(talent)) {
                talents.add(otherTalent);
            }
        }
        return talents;
    }

    public List<Research> getDependencyResearches(final String researchName) {
        final List<Research> researches = new LinkedList<Research>();
        final Entry entry = this.entries.get(researchName);
        if (entry == null) {
            return researches;
        }
        entry.dependsOn.forEach(dependencyName -> {
            final Research dependency = ModConfigs.RESEARCHES.getByName(dependencyName);
            researches.add(dependency);
            return;
        });
        return researches;
    }

    public List<Research> getLockedByResearches(final String researchName) {
        final List<Research> researches = new LinkedList<Research>();
        final Entry entry = this.entries.get(researchName);
        if (entry == null) {
            return researches;
        }
        entry.lockedBy.forEach(dependencyName -> {
            final Research dependency = ModConfigs.RESEARCHES.getByName(dependencyName);
            researches.add(dependency);
            return;
        });
        return researches;
    }

    public boolean isLocked(final String researchName, final ResearchTree researchTree) {
        final SkillGates gates = ModConfigs.SKILL_GATES.getGates();
        final List<String> researchesDone = researchTree.getResearchesDone();
        for (final Research dependencyResearch : gates.getDependencyResearches(researchName)) {
            if (!researchesDone.contains(dependencyResearch.getName())) {
                return true;
            }
        }
        for (final Research lockedByResearch : gates.getLockedByResearches(researchName)) {
            if (researchesDone.contains(lockedByResearch.getName())) {
                return true;
            }
        }
        return false;
    }

    public boolean isLocked(final TalentGroup<?> talent, final TalentTree talentTree) {
        final SkillGates gates = ModConfigs.SKILL_GATES.getGates();
        if (talent instanceof ArchetypeTalentGroup && talentTree.getLearnedNodes().stream()
                .filter(other -> !other.getGroup().getParentName().equals(talent.getParentName()))
                .anyMatch(t -> t.getGroup() instanceof ArchetypeTalentGroup)) {
            return true;
        }
        for (final TalentGroup<?> dependencyTalent : gates.getDependencyTalents(talent.getParentName())) {
            if (!talentTree.getNodeOf(dependencyTalent).isLearned()) {
                return true;
            }
        }
        for (final TalentGroup<?> lockedByTalent : gates.getLockedByTalents(talent.getParentName())) {
            if (talentTree.getNodeOf(lockedByTalent).isLearned()) {
                return true;
            }
        }
        return false;
    }

    public static class Entry {
        @Expose
        private List<String> dependsOn;
        @Expose
        private List<String> lockedBy;

        public Entry() {
            this.dependsOn = new LinkedList<String>();
            this.lockedBy = new LinkedList<String>();
        }

        public void setDependsOn(final String... skills) {
            this.dependsOn.addAll(Arrays.asList(skills));
        }

        public void setLockedBy(final String... skills) {
            this.lockedBy.addAll(Arrays.asList(skills));
        }
    }
}
