package com.axedgaming.endersdelight.Common.Registries;

import com.axedgaming.endersdelight.EndersDelight;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EdParticles {
    public static final DeferredRegister<ParticleType<?>> ED_PARTICLES = DeferredRegister.create(Registries.PARTICLE_TYPE, EndersDelight.MOD_ID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> END_FLAME = ED_PARTICLES.register("end_flame",
            () -> new SimpleParticleType(true));
}
