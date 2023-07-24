package com.axedgaming.endersdelight.effect;

import com.axedgaming.endersdelight.registry.ModDamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;


public class PhasingEffect extends MobEffect {
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity.level().isRaining() || (pLivingEntity.isInWaterOrBubble())){
            pLivingEntity.hurt(ModDamageTypes.getSimpleDamageSource(pLivingEntity.level(), ModDamageTypes.WATERED), 1.0F);
        }
    }
    public PhasingEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return pDuration > 0;
    }
}