package com.axedgaming.endersdelight.client;

import com.axedgaming.endersdelight.EndersDelight;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticleTypes {
    public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, EndersDelight.MODID);
    public static final RegistryObject<SimpleParticleType> END_FLAME = REGISTRY.register("end_flames", () -> new SimpleParticleType(false));
}

