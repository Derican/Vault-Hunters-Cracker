// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.config;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.IFormattableTextComponent;
import com.google.gson.annotations.Expose;
import com.google.gson.JsonElement;
import java.util.HashMap;

public class SkillDescriptionsConfig extends Config
{
    @Expose
    private HashMap<String, JsonElement> descriptions;
    
    @Override
    public String getName() {
        return "skill_descriptions";
    }
    
    public IFormattableTextComponent getDescriptionFor(final String skillName) {
        final JsonElement element = this.descriptions.get(skillName);
        if (element == null) {
            return ITextComponent.Serializer.fromJsonLenient("[{text:'No description for ', color:'#192022'},{text: '" + skillName + "', color: '#fcf5c5'},{text: ', yet', color: '#192022'}]");
        }
        return ITextComponent.Serializer.fromJson(element);
    }
    
    @Override
    protected void reset() {
        this.descriptions = new HashMap<String, JsonElement>();
    }
}
