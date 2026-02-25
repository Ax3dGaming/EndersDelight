package com.axedgaming.endersdelight.Common.Registries;

import com.axedgaming.endersdelight.EndersDelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;

public class EdDamageSource {

    public static final ResourceKey<DamageType> WATERED =
            ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(EndersDelight.MOD_ID, "watered"));

    /**
     * Creates a DamageSource from a DamageType key.
     * Includes a safe fallback to generic() if the key is missing (prevents hard crash).
     */
    public static DamageSource getSimpleDamageSource(Level level, ResourceKey<DamageType> damageType) {
        var regOpt = level.registryAccess().registry(Registries.DAMAGE_TYPE);
        if (regOpt.isEmpty()) {
            return level.damageSources().generic();
        }
        var holderOpt = regOpt.get().getHolder(damageType);
        return holderOpt.map(DamageSource::new).orElseGet(() -> level.damageSources().generic());
    }
}