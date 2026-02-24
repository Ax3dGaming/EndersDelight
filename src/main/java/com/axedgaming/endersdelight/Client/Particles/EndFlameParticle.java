package com.axedgaming.endersdelight.Client.Particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.TextureSheetParticle;

public class EndFlameParticle extends TextureSheetParticle {
    protected EndFlameParticle(ClientLevel level, double x, double y, double z,
                               double xd, double yd, double zd) {
        super(level, x, y, z, xd, yd, zd);

        this.xd = xd;
        this.yd = yd;
        this.zd = zd;

        this.lifetime = 20;
        this.gravity = 0.0F;
        this.setSize(0.2F, 0.2F);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }
}
