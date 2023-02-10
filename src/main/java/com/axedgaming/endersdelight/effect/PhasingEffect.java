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

        if (pLivingEntity.level.isRaining()) {
            pLivingEntity.hurt(ModDamageSource.WATER, 1);
        }
        if (pLivingEntity.isInWaterOrBubble()) {
            pLivingEntity.hurt(ModDamageSource.WATER, 1);
        }

        double x = pLivingEntity.getX();
        double y = pLivingEntity.getY();
        double z = pLivingEntity.getZ();

        for (int i = 0; i < (int) (1000); i++) {
            x = Math.floor(x) + Mth.nextInt(new Random(), -5, 5);
            y = Math.floor(y) + Mth.nextInt(new Random(), -2, 2);
            z = Math.floor(z) + Mth.nextInt(new Random(), -5, 5);
            if (pLivingEntity.level.isEmptyBlock(new BlockPos(x, y, z))) {
                if (pLivingEntity.level.getBlockState(new BlockPos(x, y - 1, z)).canOcclude()) {
                    if (pLivingEntity.hurtTime <= 10) {pLivingEntity.teleportTo(x,y,z);}}}break;}



    }

    public PhasingEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return pDuration % 80 == 0;
    }
}