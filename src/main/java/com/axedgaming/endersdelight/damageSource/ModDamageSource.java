package com.axedgaming.endersdelight.damageSource;

import net.minecraft.world.damagesource.DamageSource;

public class ModDamageSource {
    public static final DamageSource WATER = (new DamageSource("wasWatered")).bypassArmor();
}
