package com.bigchadguys.ores.datagen;

import com.bigchadguys.ores.JoesOres;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class ModItemTags {
    private static TagKey<Item> modTag(String path) {
        return TagKey.create(
                Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath(JoesOres.MOD_ID, path)
        );
    }

    private static TagKey<Item> commonTag(String path) {
        return TagKey.create(
                Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath("c", path)
        );
    }

    public static final TagKey<Item> CELESTIUM_INGOT = modTag("celestium_ingot");

    public static final TagKey<Item> MAGNITE_TOOLS = modTag("magnite_tools");
    public static final TagKey<Item> COBALT_TOOLS = modTag("cobalt_tools");
    public static final TagKey<Item> MYTHRIL_TOOLS = modTag("mythril_tools");
    public static final TagKey<Item> ORICHALCUM_TOOLS = modTag("orichalcum_tools");
    public static final TagKey<Item> ADAMANTITE_TOOLS = modTag("adamantite_tools");
    public static final TagKey<Item> CELESTIUM_TOOLS = modTag("celestium_tools");
    public static final TagKey<Item> VIRIDIUM_TOOLS = modTag("viridium_tools");

    public static final TagKey<Item> COBALT_INGOTS = commonTag("ingots/cobalt");
    public static final TagKey<Item> COBALT_NUGGETS = commonTag("nuggets/cobalt");
    public static final TagKey<Item> COBALT_STORAGE_BLOCKS = commonTag("storage_blocks/cobalt");

    public static final TagKey<Item> MYTHRIL_INGOTS = commonTag("ingots/mythril");
    public static final TagKey<Item> MYTHRIL_NUGGETS = commonTag("nuggets/mythril");
    public static final TagKey<Item> MYTHRIL_STORAGE_BLOCKS = commonTag("storage_blocks/mythril");
}