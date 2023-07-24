package com.axedgaming.endersdelight.effect;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

@Mod.EventBusSubscriber
public class WhenPlayerIsAttacked {
    @SubscribeEvent
    public static void onEntityAttacked(LivingAttackEvent event) {
        if (event != null && event.getEntity() != null) {
            execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
        }
    }

    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        execute(null, world, x + 0.5, y + 0.5, z + 0.5, entity);
    }

    private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null)
            return;
        if ((entity instanceof LivingEntity _livEnt ? _livEnt.hasEffect(ModEffects.PHASING.get()) : false) && !world.getLevelData().isRaining() && !entity.isInWaterOrBubble()) {
            {
                Entity _ent = entity;
                _ent.teleportTo((x + Mth.nextInt(RandomSource.create(), -5, 5)), (y + Mth.nextInt(RandomSource.create(), -2, 2)), (z + Mth.nextInt(RandomSource.create(), -5, 5)));
                if (_ent instanceof ServerPlayer _serverPlayer)
                    _serverPlayer.connection.teleport((x + Mth.nextInt(RandomSource.create(), -5, 5)), (y + + Mth.nextInt(RandomSource.create(), -2, 2)), (z + Mth.nextInt(RandomSource.create(), -5, 5)), _ent.getYRot(), _ent.getXRot());
            }
        }
    }
}
