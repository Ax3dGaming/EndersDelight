package com.axedgaming.endersdelight.Client.Particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;

public class EndFlameParticleProvider implements ParticleProvider<SimpleParticleType> {

    private final SpriteSet sprites;

    public EndFlameParticleProvider(SpriteSet sprites) {
        this.sprites = sprites;
    }

    @Override
    public Particle createParticle(SimpleParticleType type, ClientLevel level,
                                   double x, double y, double z,
                                   double xd, double yd, double zd) {

        EndFlameParticle particle = new EndFlameParticle(level, x, y, z, xd, yd, zd);
        particle.pickSprite(sprites);
        return particle;
    }
}
