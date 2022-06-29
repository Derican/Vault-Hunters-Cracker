// 
// Decompiled by Procyon v0.6.0
// 

package iskallia.vault.init;

import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import iskallia.vault.entity.renderer.EffectCloudRenderer;
import net.minecraft.client.renderer.entity.TippedArrowRenderer;
import iskallia.vault.entity.renderer.EyestalkRenderer;
import iskallia.vault.entity.renderer.EyesoreRenderer;
import iskallia.vault.entity.renderer.AggressiveCowBossRenderer;
import iskallia.vault.entity.renderer.BoogiemanRenderer;
import iskallia.vault.entity.renderer.BlueBlazeRenderer;
import iskallia.vault.entity.renderer.RobotRenderer;
import iskallia.vault.entity.renderer.MonsterEyeRenderer;
import iskallia.vault.entity.renderer.EtchingVendorRenderer;
import net.minecraft.client.renderer.entity.CowRenderer;
import iskallia.vault.entity.renderer.TreasureGoblinRenderer;
import iskallia.vault.entity.renderer.EternalRenderer;
import iskallia.vault.entity.renderer.VaultGuardianRenderer;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import iskallia.vault.entity.renderer.FighterRenderer;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import java.util.ArrayList;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import java.util.function.Supplier;
import iskallia.vault.Vault;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraftforge.event.RegistryEvent;
import iskallia.vault.entity.EyesoreFireballEntity;
import iskallia.vault.entity.FloatingItemEntity;
import iskallia.vault.entity.VaultSandEntity;
import iskallia.vault.entity.EffectCloudEntity;
import iskallia.vault.entity.DrillArrowEntity;
import iskallia.vault.entity.EyestalkEntity;
import iskallia.vault.entity.EyesoreEntity;
import iskallia.vault.entity.AggressiveCowBossEntity;
import iskallia.vault.entity.BoogiemanEntity;
import iskallia.vault.entity.BlueBlazeEntity;
import iskallia.vault.entity.RobotEntity;
import iskallia.vault.entity.MonsterEyeEntity;
import iskallia.vault.entity.EtchingVendorEntity;
import iskallia.vault.entity.AggressiveCowEntity;
import iskallia.vault.entity.TreasureGoblinEntity;
import iskallia.vault.entity.EternalEntity;
import iskallia.vault.entity.VaultGuardianEntity;
import iskallia.vault.entity.ArenaBossEntity;
import iskallia.vault.entity.FighterEntity;
import iskallia.vault.entity.VaultFighterEntity;
import net.minecraft.entity.EntityType;
import java.util.List;

public class ModEntities
{
    public static List<EntityType<VaultFighterEntity>> VAULT_FIGHTER_TYPES;
    public static EntityType<FighterEntity> FIGHTER;
    public static EntityType<ArenaBossEntity> ARENA_BOSS;
    public static EntityType<VaultGuardianEntity> VAULT_GUARDIAN;
    public static EntityType<EternalEntity> ETERNAL;
    public static EntityType<TreasureGoblinEntity> TREASURE_GOBLIN;
    public static EntityType<AggressiveCowEntity> AGGRESSIVE_COW;
    public static EntityType<EtchingVendorEntity> ETCHING_VENDOR;
    public static EntityType<MonsterEyeEntity> MONSTER_EYE;
    public static EntityType<RobotEntity> ROBOT;
    public static EntityType<BlueBlazeEntity> BLUE_BLAZE;
    public static EntityType<BoogiemanEntity> BOOGIEMAN;
    public static EntityType<AggressiveCowBossEntity> AGGRESSIVE_COW_BOSS;
    public static EntityType<EyesoreEntity> EYESORE;
    public static EntityType<EyestalkEntity> EYESTALK;
    public static EntityType<DrillArrowEntity> DRILL_ARROW;
    public static EntityType<EffectCloudEntity> EFFECT_CLOUD;
    public static EntityType<VaultSandEntity> VAULT_SAND;
    public static EntityType<FloatingItemEntity> FLOATING_ITEM;
    public static EntityType<EyesoreFireballEntity> EYESORE_FIREBALL;
    
    public static void register(final RegistryEvent.Register<EntityType<?>> event) {
        for (int i = 0; i < 10; ++i) {
            ModEntities.VAULT_FIGHTER_TYPES.add(registerVaultFighter(i, event));
        }
        ModEntities.FIGHTER = registerLiving("fighter", (EntityType.Builder<FighterEntity>)EntityType.Builder.of(FighterEntity::new, EntityClassification.MONSTER).sized(0.6f, 1.95f), ZombieEntity::createAttributes, event);
        ModEntities.ARENA_BOSS = registerLiving("arena_boss", (EntityType.Builder<ArenaBossEntity>)EntityType.Builder.of(ArenaBossEntity::new, EntityClassification.MONSTER).sized(0.6f, 1.95f), ArenaBossEntity::getAttributes, event);
        ModEntities.VAULT_GUARDIAN = registerLiving("vault_guardian", (EntityType.Builder<VaultGuardianEntity>)EntityType.Builder.of(VaultGuardianEntity::new, EntityClassification.MONSTER).sized(1.3f, 2.95f), ZombieEntity::createAttributes, event);
        ModEntities.ETERNAL = registerLiving("eternal", (EntityType.Builder<EternalEntity>)EntityType.Builder.of(EternalEntity::new, EntityClassification.CREATURE).sized(0.6f, 1.95f), ZombieEntity::createAttributes, event);
        ModEntities.TREASURE_GOBLIN = registerLiving("treasure_goblin", (EntityType.Builder<TreasureGoblinEntity>)EntityType.Builder.of(TreasureGoblinEntity::new, EntityClassification.CREATURE).sized(0.5f, 1.5f), ZombieEntity::createAttributes, event);
        ModEntities.AGGRESSIVE_COW = registerLiving("aggressive_cow", (EntityType.Builder<AggressiveCowEntity>)EntityType.Builder.of(AggressiveCowEntity::new, EntityClassification.MONSTER).sized(0.9f, 1.4f).clientTrackingRange(8), AggressiveCowEntity::getAttributes, event);
        ModEntities.ETCHING_VENDOR = registerLiving("etching_vendor", (EntityType.Builder<EtchingVendorEntity>)EntityType.Builder.of(EtchingVendorEntity::new, EntityClassification.MISC), ZombieEntity::createAttributes, event);
        ModEntities.MONSTER_EYE = registerLiving("monster_eye", (EntityType.Builder<MonsterEyeEntity>)EntityType.Builder.of(MonsterEyeEntity::new, EntityClassification.MONSTER).sized(4.08f, 4.08f), ZombieEntity::createAttributes, event);
        ModEntities.ROBOT = registerLiving("robot", (EntityType.Builder<RobotEntity>)EntityType.Builder.of(RobotEntity::new, EntityClassification.MONSTER).sized(2.8f, 5.4f), ZombieEntity::createAttributes, event);
        ModEntities.BLUE_BLAZE = registerLiving("blue_blaze", (EntityType.Builder<BlueBlazeEntity>)EntityType.Builder.of(BlueBlazeEntity::new, EntityClassification.MONSTER).sized(1.2f, 3.6f), ZombieEntity::createAttributes, event);
        ModEntities.BOOGIEMAN = registerLiving("boogieman", (EntityType.Builder<BoogiemanEntity>)EntityType.Builder.of(BoogiemanEntity::new, EntityClassification.MONSTER).sized(1.2f, 3.9f), ZombieEntity::createAttributes, event);
        ModEntities.AGGRESSIVE_COW_BOSS = registerLiving("aggressive_cow_boss", (EntityType.Builder<AggressiveCowBossEntity>)EntityType.Builder.of(AggressiveCowBossEntity::new, EntityClassification.MONSTER).sized(2.6999998f, 4.2f), AggressiveCowEntity::getAttributes, event);
        ModEntities.EYESORE = registerLiving("eyesore", (EntityType.Builder<EyesoreEntity>)EntityType.Builder.of(EyesoreEntity::new, EntityClassification.MONSTER).sized(9.78f, 9.78f), EyesoreEntity::getAttributes, event);
        ModEntities.EYESTALK = registerLiving("eyestalk", (EntityType.Builder<EyestalkEntity>)EntityType.Builder.of(EyestalkEntity::new, EntityClassification.MONSTER).sized(2.2f, 2.6f), EyestalkEntity::getAttributes, event);
        ModEntities.DRILL_ARROW = register("drill_arrow", (EntityType.Builder<DrillArrowEntity>)EntityType.Builder.of(DrillArrowEntity::new, EntityClassification.MISC), event);
        ModEntities.EFFECT_CLOUD = register("effect_cloud", (EntityType.Builder<EffectCloudEntity>)EntityType.Builder.of(EffectCloudEntity::new, EntityClassification.MISC), event);
        ModEntities.VAULT_SAND = register("vault_sand", (EntityType.Builder<VaultSandEntity>)EntityType.Builder.of(VaultSandEntity::new, EntityClassification.MISC), event);
        ModEntities.FLOATING_ITEM = register("floating_item", (EntityType.Builder<FloatingItemEntity>)EntityType.Builder.of(FloatingItemEntity::new, EntityClassification.MISC), event);
        ModEntities.EYESORE_FIREBALL = register("eyesore_fireball", (EntityType.Builder<EyesoreFireballEntity>)EntityType.Builder.of(EyesoreFireballEntity::new, EntityClassification.MISC), event);
    }
    
    private static EntityType<VaultFighterEntity> registerVaultFighter(final int count, final RegistryEvent.Register<EntityType<?>> event) {
        return registerLiving((count > 0) ? ("vault_fighter_" + count) : "vault_fighter", (EntityType.Builder<VaultFighterEntity>)EntityType.Builder.of(VaultFighterEntity::new, EntityClassification.MONSTER).sized(0.6f, 1.95f), ZombieEntity::createAttributes, event);
    }
    
    private static <T extends Entity> EntityType<T> register(final String name, final EntityType.Builder<T> builder, final RegistryEvent.Register<EntityType<?>> event) {
        final EntityType<T> entityType = (EntityType<T>)builder.build(Vault.sId(name));
        event.getRegistry().register(entityType.setRegistryName(Vault.id(name)));
        return entityType;
    }
    
    private static <T extends LivingEntity> EntityType<T> registerLiving(final String name, final EntityType.Builder<T> builder, final Supplier<AttributeModifierMap.MutableAttribute> attributes, final RegistryEvent.Register<EntityType<?>> event) {
        final EntityType<T> entityType = register(name, builder, event);
        if (attributes != null) {
            GlobalEntityTypeAttributes.put((EntityType)entityType, attributes.get().build());
        }
        return entityType;
    }
    
    static {
        ModEntities.VAULT_FIGHTER_TYPES = new ArrayList<EntityType<VaultFighterEntity>>();
    }
    
    public static class Renderers
    {
        public static void register(final FMLClientSetupEvent event) {
            ModEntities.VAULT_FIGHTER_TYPES.forEach(type -> RenderingRegistry.registerEntityRenderingHandler(type, FighterRenderer::new));
            RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.FIGHTER, FighterRenderer::new);
            RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.ARENA_BOSS, FighterRenderer::new);
            RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.VAULT_GUARDIAN, VaultGuardianRenderer::new);
            RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.ETERNAL, EternalRenderer::new);
            RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.TREASURE_GOBLIN, TreasureGoblinRenderer::new);
            RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.AGGRESSIVE_COW, CowRenderer::new);
            RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.ETCHING_VENDOR, EtchingVendorRenderer::new);
            RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.MONSTER_EYE, MonsterEyeRenderer::new);
            RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.ROBOT, RobotRenderer::new);
            RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.BLUE_BLAZE, BlueBlazeRenderer::new);
            RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.BOOGIEMAN, BoogiemanRenderer::new);
            RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.AGGRESSIVE_COW_BOSS, AggressiveCowBossRenderer::new);
            RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.EYESORE, EyesoreRenderer::new);
            RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.EYESTALK, EyestalkRenderer::new);
            RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.DRILL_ARROW, TippedArrowRenderer::new);
            RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.EFFECT_CLOUD, EffectCloudRenderer::new);
            RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.VAULT_SAND, rm -> new ItemRenderer(rm, Minecraft.getInstance().getItemRenderer()));
            RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.FLOATING_ITEM, rm -> new ItemRenderer(rm, Minecraft.getInstance().getItemRenderer()));
            RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.EYESORE_FIREBALL, rm -> new SpriteRenderer(rm, Minecraft.getInstance().getItemRenderer(), 5.0f, true));
        }
    }
}
