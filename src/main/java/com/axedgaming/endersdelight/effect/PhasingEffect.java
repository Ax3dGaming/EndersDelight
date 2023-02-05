package com.axedgaming.endersdelight.effect;

import com.axedgaming.endersdelight.damageSource.ModDamageSource;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import java.util.Random;



public class PhasingEffect extends MobEffect {
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        double x = pLivingEntity.getX();
        double y = pLivingEntity.getY();
        double z = pLivingEntity.getZ();

        x = Math.floor(x) + Mth.nextInt(new Random(), -5, 5);
        y = Math.floor(y) + Mth.nextInt(new Random(), 0, 2);
        z = Math.floor(z) + Mth.nextInt(new Random(), -5, 5);

        if (!pLivingEntity.isInWaterOrBubble() && pLivingEntity.hurtTime >= 5 && !pLivingEntity.level.isRaining()) {
            if (pLivingEntity.level.isEmptyBlock(new BlockPos(x, y, z)) && pLivingEntity.level.isEmptyBlock(new BlockPos(x, y + 1, z))
                    && pLivingEntity.level.getBlockState(new BlockPos(x, y - 1, z)).canOcclude()) {
                pLivingEntity.teleportTo(x,y,z);
            }
        }
        if (pLivingEntity.level.isRaining()) {
            pLivingEntity.hurt(ModDamageSource.WATER, 1);
        }
        if (pLivingEntity.isInWaterOrBubble()) {
           pLivingEntity.hurt(ModDamageSource.WATER, 1);
        }
    }

    public PhasingEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return pDuration % 80 == 0;
    }
}