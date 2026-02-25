package com.axedgaming.endersdelight.Common.Effects;

import com.axedgaming.endersdelight.Common.Registries.EdEffects;
import com.axedgaming.endersdelight.Config;
import com.axedgaming.endersdelight.Common.Registries.EdDamageSource;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber
public final class EnderPhasingHandler {

    private EnderPhasingHandler() {}

    private static final String NBT_LAST_PHASE_TICK = "endersdelight_last_phase_tick";
    private static final int MAX_ATTEMPTS = 24;

    @SubscribeEvent
    public static void onIncomingDamage(LivingIncomingDamageEvent event) {
        LivingEntity entity = event.getEntity();
        if (!(entity.level() instanceof ServerLevel level)) return;

        // Must have the effect
        if (!entity.hasEffect(EdEffects.PHASING)) return;

        // Global toggle
        if (!Config.DOES_PHASING_TELEPORT_PLAYER.get()) return;

        // Cooldown
        int cooldown = Config.COOLDOWN_BETWEEN_PHASING_TELEPORTS.get();
        long now = level.getGameTime();
        long last = entity.getPersistentData().getLong(NBT_LAST_PHASE_TICK);
        if (now - last < cooldown) return;

        // Water-related damage handling (prevents "launching out of water" by default)
        if (isWaterRelatedDamage(event, entity) && !Config.DOES_PHASING_TELEPORT_PLAYER_WHEN_DAMAGE_TAKEN_BY_WATER.get()) {
            return;
        }

        int rangeXZ = Config.HORIZONTAL_PHASING_TELEPORT_RANGE.get();
        int rangeY = Config.VERTICAL_PHASING_TELEPORT_RANGE.get();

        if (tryRandomTeleport(level, entity, rangeXZ, rangeY)) {
            entity.getPersistentData().putLong(NBT_LAST_PHASE_TICK, now);
        }
    }

    private static boolean isWaterRelatedDamage(LivingIncomingDamageEvent event, LivingEntity entity) {
        // If the entity is currently in water/rain/bubbles, treat it as water-related
        if (entity.isInWaterRainOrBubble()) return true;

        // Try to identify the damage type key (vanilla drown OR our custom watered)
        ResourceKey<DamageType> key = event.getSource()
                .typeHolder()
                .unwrapKey()
                .orElse(null);

        if (key == null) return false;

        return key.equals(DamageTypes.DROWN) || key.equals(EdDamageSource.WATERED);
    }

    private static boolean tryRandomTeleport(ServerLevel level, LivingEntity entity, int rangeXZ, int rangeY) {
        final double ox = entity.getX();
        final double oy = entity.getY();
        final double oz = entity.getZ();

        for (int i = 0; i < MAX_ATTEMPTS; i++) {
            int dx = Mth.nextInt(level.random, -rangeXZ, rangeXZ);
            int dy = Mth.nextInt(level.random, -rangeY, rangeY);
            int dz = Mth.nextInt(level.random, -rangeXZ, rangeXZ);

            BlockPos feet = BlockPos.containing(ox + dx, oy + dy, oz + dz);
            if (!isSafeTeleportSpot(level, feet)) continue;

            double tx = feet.getX() + 0.5D;
            double ty = feet.getY();
            double tz = feet.getZ() + 0.5D;

            float yaw = entity.getYRot();
            float pitch = entity.getXRot();

            if (entity instanceof ServerPlayer player) {
                player.teleportTo(level, tx, ty, tz, yaw, pitch);
            } else {
                entity.teleportTo(tx, ty, tz);
            }
            return true;
        }

        return false;
    }

    private static boolean isSafeTeleportSpot(ServerLevel level, BlockPos feetPos) {
        BlockPos belowPos = feetPos.below();
        BlockState below = level.getBlockState(belowPos);

        // Must stand on a full solid block
        if (!below.isCollisionShapeFullBlock(level, belowPos)) return false;

        BlockState feet = level.getBlockState(feetPos);
        BlockState head = level.getBlockState(feetPos.above());

        // Avoid suffocation
        if (feet.isCollisionShapeFullBlock(level, feetPos)) return false;
        if (head.isCollisionShapeFullBlock(level, feetPos.above())) return false;

        return true;
    }
}