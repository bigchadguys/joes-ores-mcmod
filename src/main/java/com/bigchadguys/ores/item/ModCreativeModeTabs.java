package com.bigchadguys.ores.item;

import com.bigchadguys.ores.JoesOres;
import com.bigchadguys.ores.block.ModBlocks;
import net.mcexpanded.fancytabsections.FancyTabSections;
import net.mcexpanded.fancytabsections.Section.SectionColored;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    // private static final int SECTION_BG = 0xFF344545;
    private static final int SECTION_BG = 0xFF6633CC;
    private static final int SECTION_TEXT = 0xFFEFEFEF;
    private static final boolean SECTION_TEXT_SHADOW = true;

    private static SectionColored section(String sectionId, String translationKey) {
        return new SectionColored(id(sectionId))
                .setTitle(Component.translatable(translationKey))
                .setBannerColor(SECTION_BG)
                .setTextColor(SECTION_TEXT)
                .setTextShadow(SECTION_TEXT_SHADOW);
    }

    private static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(JoesOres.MOD_ID, path);
    }

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, JoesOres.MOD_ID);

    public static final Supplier<CreativeModeTab> ORES = CREATIVE_MODE_TABS.register("ores_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.ores.ores_tab"))
                    .icon(() -> new ItemStack(ModItems.RAW_MORPHITE.get()))
                    .displayItems((parameters, output) ->
                    {
                        // Empty for Fancy Tab Sections
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
        addItems();
    }

    private static void addItems() {
        addArmor();
        addFunctionalBlocks();
        addOres();
        addRawMaterials();
        addIngots();
        addToolsWeapons();
    }

    private static void addArmor() {
        FancyTabSections.addSection(id("ores_tab"),
               section("armor", "creativetab.ores.ores_tab.armor")

                        .add(ModItems.COPPER_HELMET)
                        .add(ModItems.COBALT_HELMET)
                        .add(ModItems.MAGNITE_HELMET)
                        .add(ModItems.MYTHRIL_HELMET)
                        .add(ModItems.ORICHALCUM_HELMET)
                        .add(ModItems.ADAMANTITE_HELMET)
                        .add(ModItems.VIRIDIUM_HELMET)
                        .add(ModItems.LUNARIUM_HELMET)
                        .add(ModItems.SOLARIUM_HELMET)

                        .add(ModItems.COPPER_CHESTPLATE)
                        .add(ModItems.COBALT_CHESTPLATE)
                        .add(ModItems.MAGNITE_CHESTPLATE)
                        .add(ModItems.MYTHRIL_CHESTPLATE)
                        .add(ModItems.ORICHALCUM_CHESTPLATE)
                        .add(ModItems.ADAMANTITE_CHESTPLATE)
                        .add(ModItems.VIRIDIUM_CHESTPLATE)
                        .add(ModItems.LUNARIUM_CHESTPLATE)
                        .add(ModItems.SOLARIUM_CHESTPLATE)

                        .add(ModItems.COPPER_LEGGINGS)
                        .add(ModItems.COBALT_LEGGINGS)
                        .add(ModItems.MAGNITE_LEGGINGS)
                        .add(ModItems.MYTHRIL_LEGGINGS)
                        .add(ModItems.ORICHALCUM_LEGGINGS)
                        .add(ModItems.ADAMANTITE_LEGGINGS)
                        .add(ModItems.VIRIDIUM_LEGGINGS)
                        .add(ModItems.LUNARIUM_LEGGINGS)
                        .add(ModItems.SOLARIUM_LEGGINGS)

                        .add(ModItems.COPPER_BOOTS)
                        .add(ModItems.COBALT_BOOTS)
                        .add(ModItems.MAGNITE_BOOTS)
                        .add(ModItems.MYTHRIL_BOOTS)
                        .add(ModItems.ORICHALCUM_BOOTS)
                        .add(ModItems.ADAMANTITE_BOOTS)
                        .add(ModItems.VIRIDIUM_BOOTS)
                        .add(ModItems.LUNARIUM_BOOTS)
                        .add(ModItems.SOLARIUM_BOOTS)
        );
    }

    private static void addFunctionalBlocks() {
        FancyTabSections.addSection(id("ores_tab"),
                section("functional_blocks", "creativetab.ores.ores_tab.functional_blocks")

                        .add(ModBlocks.COBALT_FURNACE)
                        .add(ModBlocks.ADAMANTITE_FURNACE)
                        .add(ModBlocks.MORPHITE_SYNTHESIZER)
        );
    }

    private static void addOres() {
        FancyTabSections.addSection(id("ores_tab"),
                section("ores", "creativetab.ores.ores_tab.ores")

                        .add(ModBlocks.COBALT_ORE)
                        .add(ModBlocks.MAGNITE_ORE)
                        .add(ModBlocks.MYTHRIL_ORE)
                        .add(ModBlocks.ORICHALCUM_ORE)
                        .add(ModBlocks.ADAMANTITE_ORE)
                        .add(ModBlocks.CELESTIUM_ORE)
                        .add(ModBlocks.VIRIDIUM_ORE)
                        .add(ModBlocks.TECTONIC_ORE)
                        .add(ModBlocks.MORPHITE_ORE)

                        .add(ModBlocks.DEEPSLATE_COBALT_ORE)
                        .add(ModBlocks.DEEPSLATE_MAGNITE_ORE)
                        .add(ModBlocks.DEEPSLATE_MYTHRIL_ORE)
                        .add(ModBlocks.DEEPSLATE_ORICHALCUM_ORE)
                        .add(ModBlocks.DEEPSLATE_ADAMANTITE_ORE)
                        .add(ModBlocks.DEEPSLATE_CELESTIUM_ORE)
                        .add(ModBlocks.DEEPSLATE_TECTONIC_ORE)
                        .add(ModBlocks.DEEPSLATE_MORPHITE_ORE)
        );
    }

    private static void addRawMaterials() {
        FancyTabSections.addSection(id("ores_tab"),
                section("raw_materials", "creativetab.ores.ores_tab.raw_materials")

                        .add(ModItems.RAW_COBALT)
                        .add(ModItems.RAW_MAGNITE)
                        .add(ModItems.RAW_MYTHRIL)
                        .add(ModItems.RAW_ORICHALCUM)
                        .add(ModItems.RAW_ADAMANTITE)
                        .add(ModItems.RAW_VIRIDIUM)
                        .add(ModItems.RAW_CELESTIUM)
                        .add(ModItems.RAW_MORPHITE)
                        .add(ModItems.ECTOPLASM)
                        .add(ModItems.TECTONIC_SHARD)
                        .add(ModItems.QUICKSILVER)
                        .add(ModItems.PETRIFIED_BARK)
                        .add(ModItems.MUSH)
        );
    }

    private static void addIngots() {
        FancyTabSections.addSection(id("ores_tab"),
                section("ingots", "creativetab.ores.ores_tab.ingots")

                        .add(ModItems.ADAMANTITE_NUGGET)
                        .add(ModItems.VIRIDIUM_NUGGET)
                        .add(ModItems.LUNARIUM_NUGGET)
                        .add(ModItems.SOLARIUM_NUGGET)
                        .add(ModItems.MORPHITE_NUGGET)
                        .add(ModItems.NECRONIUM_NUGGET)
                        .add(ModItems.GEOVAR_NUGGET)
                        .add(ModItems.SWIFTITE_NUGGET)
                        .add(ModItems.PETRAFITE_NUGGET)
                        .add(ModItems.FLORITE_NUGGET)

                        .add(ModItems.COBALT_INGOT)
                        .add(ModItems.MAGNITE_INGOT)
                        .add(ModItems.MYTHRIL_INGOT)
                        .add(ModItems.ORICHALCUM_INGOT)
                        .add(ModItems.ADAMANTITE_INGOT)
                        .add(ModItems.VIRIDIUM_INGOT)
                        .add(ModItems.LUNARIUM_INGOT)
                        .add(ModItems.SOLARIUM_INGOT)
                        .add(ModItems.MORPHITE_INGOT)
                        .add(ModItems.NECRONIUM_INGOT)
                        .add(ModItems.GEOVAR_INGOT)
                        .add(ModItems.SWIFTITE_INGOT)
                        .add(ModItems.PETRAFITE_INGOT)
                        .add(ModItems.FLORITE_INGOT)

                        .add(ModBlocks.COBALT_BLOCK)
                        .add(ModBlocks.MAGNITE_BLOCK)
                        .add(ModBlocks.MYTHRIL_BLOCK)
                        .add(ModBlocks.ORICHALCUM_BLOCK)
                        .add(ModBlocks.ADAMANTITE_BLOCK)
                        .add(ModBlocks.VIRIDIUM_BLOCK)
                        .add(ModBlocks.LUNARIUM_BLOCK)
                        .add(ModBlocks.SOLARIUM_BLOCK)
                        .add(ModBlocks.MORPHITE_BLOCK)
                        .add(ModBlocks.NECRONIUM_BLOCK)
                        .add(ModBlocks.GEOVAR_BLOCK)
                        .add(ModBlocks.SWIFTITE_BLOCK)
                        .add(ModBlocks.PETRAFITE_BLOCK)
                        .add(ModBlocks.FLORITE_BLOCK)
        );
    }

    private static void addToolsWeapons() {
        FancyTabSections.addSection(id("ores_tab"),
                section("tools_weapons", "creativetab.ores.ores_tab.tools_weapons")

                        .add(ModItems.COPPER_SWORD)
                        .add(ModItems.COBALT_SWORD)
                        .add(ModItems.MAGNITE_SWORD)
                        .add(ModItems.MYTHRIL_SWORD)
                        .add(ModItems.ORICHALCUM_SWORD)
                        .add(ModItems.ADAMANTITE_SWORD)
                        .add(ModItems.VIRIDIUM_SWORD)
                        .add(ModItems.CELESTIUM_SWORD)
                        .add(ModItems.NECRONIUM_SWORD)

                        .add(ModItems.COPPER_PICKAXE)
                        .add(ModItems.COBALT_PICKAXE)
                        .add(ModItems.MAGNITE_PICKAXE)
                        .add(ModItems.MYTHRIL_PICKAXE)
                        .add(ModItems.ORICHALCUM_PICKAXE)
                        .add(ModItems.ADAMANTITE_PICKAXE)
                        .add(ModItems.VIRIDIUM_PICKAXE)
                        .add(ModItems.CELESTIUM_PICKAXE)
                        .add(ModItems.GEOVAR_PICKAXE)

                        .add(ModItems.COPPER_AXE)
                        .add(ModItems.COBALT_AXE)
                        .add(ModItems.MAGNITE_AXE)
                        .add(ModItems.MYTHRIL_AXE)
                        .add(ModItems.ORICHALCUM_AXE)
                        .add(ModItems.ADAMANTITE_AXE)
                        .add(ModItems.VIRIDIUM_AXE)
                        .add(ModItems.CELESTIUM_AXE)
                        .add(ModItems.PETRAFITE_AXE)

                        .add(ModItems.COPPER_SHOVEL)
                        .add(ModItems.COBALT_SHOVEL)
                        .add(ModItems.MAGNITE_SHOVEL)
                        .add(ModItems.MYTHRIL_SHOVEL)
                        .add(ModItems.ORICHALCUM_SHOVEL)
                        .add(ModItems.ADAMANTITE_SHOVEL)
                        .add(ModItems.VIRIDIUM_SHOVEL)
                        .add(ModItems.CELESTIUM_SHOVEL)
                        .add(ModItems.SWIFTITE_SHOVEL)

                        .add(ModItems.COPPER_HOE)
                        .add(ModItems.COBALT_HOE)
                        .add(ModItems.MAGNITE_HOE)
                        .add(ModItems.MYTHRIL_HOE)
                        .add(ModItems.ORICHALCUM_HOE)
                        .add(ModItems.ADAMANTITE_HOE)
                        .add(ModItems.VIRIDIUM_HOE)
                        .add(ModItems.CELESTIUM_HOE)
                        .add(ModItems.FLORITE_HOE)
        );
    }

}
