// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.ContainerType;
import iskallia.vault.init.ModContainers;
import iskallia.vault.research.ResearchTree;
import iskallia.vault.skill.talent.TalentTree;
import iskallia.vault.skill.ability.AbilityTree;
import net.minecraft.inventory.container.Container;

public class SkillTreeContainer extends Container
{
    private final AbilityTree abilityTree;
    private final TalentTree talentTree;
    private final ResearchTree researchTree;
    
    public SkillTreeContainer(final int windowId, final AbilityTree abilityTree, final TalentTree talentTree, final ResearchTree researchTree) {
        super((ContainerType)ModContainers.SKILL_TREE_CONTAINER, windowId);
        this.abilityTree = abilityTree;
        this.talentTree = talentTree;
        this.researchTree = researchTree;
    }
    
    public boolean stillValid(final PlayerEntity player) {
        return true;
    }
    
    public AbilityTree getAbilityTree() {
        return this.abilityTree;
    }
    
    public TalentTree getTalentTree() {
        return this.talentTree;
    }
    
    public ResearchTree getResearchTree() {
        return this.researchTree;
    }
}
