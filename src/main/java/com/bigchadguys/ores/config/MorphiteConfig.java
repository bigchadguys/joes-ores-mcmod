package com.bigchadguys.ores.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class MorphiteConfig {
    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.DoubleValue PETRAFITE_SAVING;
    public static final ModConfigSpec.DoubleValue GEOVAR_SAVING;
    public static final ModConfigSpec.IntValue LEAF_FORTUNE;
    public static final ModConfigSpec.DoubleValue SWIFTITE_SPEED;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        builder.push("morphite_tools");

        PETRAFITE_SAVING = builder
                .comment(
                        "Chance to skip mining durability on blocks in",
                        "ores:petrafite_durability_saving.",
                        "Rolls independently of Unbreaking."
                )
                .defineInRange(
                        "petrafite_durability_saving_chance",
                        0.33,
                        0.0,
                        1.0
                );

        GEOVAR_SAVING = builder
                .comment(
                        "Chance to skip mining durability on blocks in",
                        "ores:geovar_durability_saving.",
                        "Rolls independently of Unbreaking."
                )
                .defineInRange(
                        "geovar_durability_saving_chance",
                        0.33,
                        0.0,
                        1.0
                );

        LEAF_FORTUNE = builder
                .comment(
                        "Minimum Fortune level for Petrafite leaf drops.",
                        "Higher Fortune takes precedence.",
                        "Silk Touch takes precedence.",
                        "Set to 0 to disable."
                )
                .defineInRange(
                        "petrafite_leaf_fortune",
                        1,
                        0,
                        3
                );

        SWIFTITE_SPEED = builder
                .comment(
                        "Main-hand movement speed bonus.",
                        "0.15 means +15%.",
                        "Re-equip the shovel after changing this value."
                )
                .defineInRange(
                        "swiftite_speed_bonus",
                        0.15,
                        0.0,
                        1.0
                );

        builder.pop();

        SPEC = builder.build();
    }

    private MorphiteConfig() {}
}