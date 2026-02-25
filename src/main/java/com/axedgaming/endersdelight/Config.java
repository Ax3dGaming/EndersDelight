package com.axedgaming.endersdelight;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    // =============================
    // ENDER PHASING
    // =============================
    public static final ModConfigSpec.BooleanValue DOES_PHASING_TELEPORT_PLAYER;
    public static final ModConfigSpec.BooleanValue DOES_PHASING_TELEPORT_PLAYER_WHEN_DAMAGE_TAKEN_BY_WATER;
    public static final ModConfigSpec.BooleanValue DOES_WATER_HURT_PLAYER_WITH_PHASING_EFFECT;

    public static final ModConfigSpec.IntValue HORIZONTAL_PHASING_TELEPORT_RANGE;
    public static final ModConfigSpec.IntValue VERTICAL_PHASING_TELEPORT_RANGE;
    public static final ModConfigSpec.IntValue COOLDOWN_BETWEEN_PHASING_TELEPORTS;

    // =============================
    // VOID PEPPER
    // =============================
    public static final ModConfigSpec.IntValue VOID_PEPPER_SPREAD_RADIUS;
    public static final ModConfigSpec.IntValue VOID_PEPPER_SPREAD_ATTEMPTS;

    /** Chance to spread on tick (0..1). Default = 2/3 */
    public static final ModConfigSpec.DoubleValue VOID_PEPPER_SPREAD_CHANCE;

    /** Chance to become PEPPER on tick (0..1). Default = 1/3 */
    public static final ModConfigSpec.DoubleValue VOID_PEPPER_GROW_PEPPER_CHANCE;

    public static final ModConfigSpec SPEC;

    static {
        // -------- Ender Phasing
        BUILDER.push("ender_phasing");

        DOES_PHASING_TELEPORT_PLAYER = BUILDER
                .comment("If true, Ender Phasing can teleport the player when damage is taken.")
                .define("doesPhasingTeleportPlayer", true);

        DOES_PHASING_TELEPORT_PLAYER_WHEN_DAMAGE_TAKEN_BY_WATER = BUILDER
                .comment("If true, Ender Phasing can teleport even when the damage source is water/rain/bubbles effects.")
                .define("doesPhasingTeleportPlayerWhenDamageTakenByWater", false);

        DOES_WATER_HURT_PLAYER_WITH_PHASING_EFFECT = BUILDER
                .comment("If true, being in water/bubbles while Ender Phasing is active deals damage.")
                .define("doesWaterHurtPlayerWithPhasingEffect", true);

        HORIZONTAL_PHASING_TELEPORT_RANGE = BUILDER
                .comment("Horizontal teleport range (blocks).")
                .defineInRange("horizontalPhasingTeleportRange", 5, 1, 64);

        VERTICAL_PHASING_TELEPORT_RANGE = BUILDER
                .comment("Vertical teleport range (blocks).")
                .defineInRange("verticalPhasingTeleportRange", 3, 1, 32);

        COOLDOWN_BETWEEN_PHASING_TELEPORTS = BUILDER
                .comment("Cooldown between phasing teleports (ticks). 20 ticks = 1 second.")
                .defineInRange("cooldownBetweenPhasingTeleports", 10, 0, 200);

        BUILDER.pop();

        // -------- Void Pepper
        BUILDER.push("void_pepper");

        VOID_PEPPER_SPREAD_RADIUS = BUILDER
                .comment("Max Manhattan radius for void pepper spreading attempts.")
                .defineInRange("spreadRadius", 7, 1, 32);

        VOID_PEPPER_SPREAD_ATTEMPTS = BUILDER
                .comment("How many random positions are tried per spread tick.")
                .defineInRange("spreadAttempts", 15, 1, 200);

        VOID_PEPPER_SPREAD_CHANCE = BUILDER
                .comment("Chance (0..1) to attempt spreading on a tick while SPREADING is true.")
                .defineInRange("spreadChance", 2.0D / 3.0D, 0.0D, 1.0D);

        VOID_PEPPER_GROW_PEPPER_CHANCE = BUILDER
                .comment("Chance (0..1) to become PEPPER on a tick while SPREADING is true.")
                .defineInRange("pepperGrowChance", 1.0D / 3.0D, 0.0D, 1.0D);

        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}