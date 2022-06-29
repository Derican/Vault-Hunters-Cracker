package iskallia.vault.mixin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.template.Template;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;





@Mixin({Template.Palette.class})
public class MixinTemplatePalette
{
  @Shadow
  @Final
  private Map<Block, List<Template.BlockInfo>> cache;
  @Shadow
  @Final
  private List<Template.BlockInfo> blocks;
  
  @Overwrite
  public List<Template.BlockInfo> blocks(Block block) {
    return this.cache.computeIfAbsent(block, filterBlock -> {
          if (block == Blocks.JIGSAW) {
            List<Template.BlockInfo> prioritizedJigsawPieces = new ArrayList<>();
            List<Template.BlockInfo> jigsawBlocks = (List<Template.BlockInfo>)this.blocks.stream().filter(()).filter(()).collect(Collectors.toList());
            prioritizedJigsawPieces.addAll(jigsawBlocks);
            return prioritizedJigsawPieces;
          } 
          return (List)this.blocks.stream().filter(()).collect(Collectors.toList());
        });
  }
}