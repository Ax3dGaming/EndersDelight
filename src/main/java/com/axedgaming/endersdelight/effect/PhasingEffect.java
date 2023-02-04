package com.axedgaming.endersdelight.effect;

import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import java.util.Random;

public class PhasingEffect extends MobEffect {


    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity.hurtTime >= 5) {
            if (!pLivingEntity.isInWaterOrBubble()) {
                if (!pLivingEntity.level.isClientSide()) {
                    Double x = pLivingEntity.getX();
                    Double y = pLivingEntity.getY();
                    Double z = pLivingEntity.getZ();

                    x = Math.floor(x) + Mth.nextInt(new Random(), -5, 5);
                    y = Math.floor(y) + Mth.nextInt(new Random(), -2, 2);
                    z = Math.floor(z) + Mth.nextInt(new Random(), -5, 5);

                    pLivingEntity.teleportTo(x, y, z);
                }
            }
        }
        if (pLivingEntity.isInWaterOrBubble()) {
            pLivingEntity.hurt(DamageSource.GENERIC, 1);
        }

        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    public PhasingEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }



    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}