package com.axedgaming.endersdelight.Common.Effects;

import com.axedgaming.endersdelight.Config;
import com.axedgaming.endersdelight.Common.Registries.EdDamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class EnderPhasingEffect extends MobEffect {

    public EnderPhasingEffect() {
        super(MobEffectCategory.BENEFICIAL, 3124687);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (Config.DOES_WATER_HURT_PLAYER_WITH_PHASING_EFFECT.get()) {
            if (entity.level().isRaining() || entity.isInWaterOrBubble()) {
                entity.hurt(
                        EdDamageSource.getSimpleDamageSource(entity.level(), EdDamageSource.WATERED),
                        1.0F
                );
            }
        }

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}