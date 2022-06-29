// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.dump;

import com.google.gson.GsonBuilder;
import java.io.IOException;
import com.google.gson.JsonElement;
import java.io.FileWriter;
import java.io.File;
import com.google.gson.JsonObject;
import com.google.gson.Gson;

public abstract class JsonDump
{
    private static final Gson GSON;
    
    public abstract String fileName();
    
    public abstract JsonObject dumpToJSON();
    
    public void dumpToFile(final String parentDir) throws IOException {
        final File configFile = new File(parentDir + File.separator + this.fileName());
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            configFile.createNewFile();
        }
        final JsonObject jsonObject = this.dumpToJSON();
        final FileWriter writer = new FileWriter(configFile);
        JsonDump.GSON.toJson((JsonElement)jsonObject, (Appendable)writer);
        writer.close();
    }
    
    static {
        GSON = new GsonBuilder().setPrettyPrinting().create();
    }
}
