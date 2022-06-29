
package iskallia.vault.client.particles;

import net.minecraft.particles.IParticleData;
import net.minecraft.client.particle.Particle;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.renderer.Tessellator;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.ActiveRenderInfo;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.client.particle.SimpleAnimatedParticle;

@OnlyIn(Dist.CLIENT)
public class DepthFireworkParticle extends SimpleAnimatedParticle {
    private static final IParticleRenderType DEPTH_PARTICLE_SHEET_TRANSLUCENT;

    private DepthFireworkParticle(final ClientWorld world, final double x, final double y, final double z,
            final double motionX, final double motionY, final double motionZ, final IAnimatedSprite spriteWithAge) {
        super(world, x, y, z, spriteWithAge, 0.0f);
        this.xd = motionX;
        this.yd = motionY;
        this.zd = motionZ;
        this.quadSize *= 0.75f;
        this.lifetime = 48 + this.random.nextInt(12);
        this.setSpriteFromAge(spriteWithAge);
    }

    public void render(final IVertexBuilder buffer, final ActiveRenderInfo renderInfo,
            final float partialTicks) {
        if (this.age < this.lifetime / 3 || (this.age + this.lifetime) / 3 % 2 == 0) {
            super.render(buffer, renderInfo, partialTicks);
        }
    }

    public IParticleRenderType getRenderType() {
        return DepthFireworkParticle.DEPTH_PARTICLE_SHEET_TRANSLUCENT;
    }

    static {
        DEPTH_PARTICLE_SHEET_TRANSLUCENT = (IParticleRenderType) new IParticleRenderType() {
            public void begin(final BufferBuilder buf, final TextureManager mgr) {
                RenderSystem.disableDepthTest();
                DepthFireworkParticle$1.PARTICLE_SHEET_TRANSLUCENT.begin(buf, mgr);
            }

            public void end(final Tessellator tes) {
                DepthFireworkParticle$1.PARTICLE_SHEET_TRANSLUCENT.end(tes);
                RenderSystem.enableDepthTest();
            }
        };
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public Factory(final IAnimatedSprite spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle makeParticle(final BasicParticleType type, final ClientWorld world, final double x,
                final double y, final double z, final double xSpeed, final double ySpeed, final double zSpeed) {
            final DepthFireworkParticle particle = new DepthFireworkParticle(world, x, y, z, 0.0, 0.0, 0.0,
                    this.spriteSet, null);
            particle.setColor((float) xSpeed, (float) ySpeed, (float) zSpeed);
            return (Particle) particle;
        }
    }
}
