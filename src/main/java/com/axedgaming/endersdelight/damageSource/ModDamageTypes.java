package com.axedgaming.endersdelight.damageSource;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.Level;

public class ModDamageTypes {
    public static final ResourceKey<DamageType> WAS_WATERED =
            ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("endersdelight", "was_watered"));

    public static DamageSource getWateredDamage(Level level) {
        Holder<DamageType> type = level.registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(WAS_WATERED);
        return new DamageSource(type);
    }
}
