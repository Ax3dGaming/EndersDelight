package com.axedgaming.endersdelight.Common.Registries;

import com.axedgaming.endersdelight.Common.Effects.EnderPhasingEffect;
import com.axedgaming.endersdelight.EndersDelight;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EdEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, EndersDelight.MOD_ID);

    public static final Holder<MobEffect> PHASING = EFFECTS.register("phasing", EnderPhasingEffect::new);
}
