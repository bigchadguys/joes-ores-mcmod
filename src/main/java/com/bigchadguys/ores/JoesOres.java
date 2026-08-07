package com.bigchadguys.ores;

import com.bigchadguys.ores.block.ModBlocks;
import com.bigchadguys.ores.block.entity.ModBlockEntities;
import com.bigchadguys.ores.item.ModArmorMaterials;
import com.bigchadguys.ores.item.ModCreativeModeTabs;
import com.bigchadguys.ores.item.ModItems;
import com.bigchadguys.ores.item.armor.client.ArmorClientExtension;
import com.bigchadguys.ores.item.armor.client.model.*;
import com.bigchadguys.ores.item.armor.client.provider.SimpleModelProvider;
import com.bigchadguys.ores.loot.AddItemModifier;
import com.bigchadguys.ores.recipe.ModRecipes;
import com.bigchadguys.ores.screen.ModMenuTypes;
import com.bigchadguys.ores.screen.custom.AdamantiteFurnaceScreen;
import com.bigchadguys.ores.screen.custom.CobaltFurnaceScreen;
import com.bigchadguys.ores.screen.custom.MorphiteSynthesizerScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

@Mod(JoesOres.MOD_ID)
public class JoesOres {
    public static final String MOD_ID = "ores";
    public JoesOres(IEventBus modEventBus) {
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModArmorMaterials.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);
        AddItemModifier.GLOBAL_LOOT_MODIFIER_SERIALIZERS.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
    }

    @EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void registerScreens(RegisterMenuScreensEvent event) {
            event.register(ModMenuTypes.MORPHITE_SYNTHESIZER_MENU.get(), MorphiteSynthesizerScreen::new);
            event.register(ModMenuTypes.COBALT_FURNACE_MENU.get(), CobaltFurnaceScreen::new);
            event.register(ModMenuTypes.ADAMANTITE_FURNACE_MENU.get(), AdamantiteFurnaceScreen::new);
        }

        @SubscribeEvent
        public static void registerClientExtensions(
                RegisterClientExtensionsEvent event
        ) {
            SimpleModelProvider solariumModelProvider =
                    new SimpleModelProvider(
                            SolariumArmorModel::createBodyLayer,
                            SolariumArmorModel::new
                    );
            ArmorClientExtension solariumExtension =
                    new ArmorClientExtension(solariumModelProvider);

            event.registerItem(
                    solariumExtension,
                    ModItems.SOLARIUM_HELMET.get(),
                    ModItems.SOLARIUM_CHESTPLATE.get(),
                    ModItems.SOLARIUM_LEGGINGS.get(),
                    ModItems.SOLARIUM_BOOTS.get()
            );

            SimpleModelProvider lunariumOuterProvider =
                    new SimpleModelProvider(
                            LunariumArmorOuterModel::createBodyLayer,
                            LunariumArmorOuterModel::new
                    );

            ArmorClientExtension lunariumExtension =
                    new ArmorClientExtension(lunariumOuterProvider);

            event.registerItem(
                    lunariumExtension,
                    ModItems.LUNARIUM_HELMET.get(),
                    ModItems.LUNARIUM_CHESTPLATE.get(),
                    ModItems.LUNARIUM_BOOTS.get()
            );

            SimpleModelProvider lunariumLeggingsProvider =
                    new SimpleModelProvider(
                            LunariumArmorLeggingsModel::createBodyLayer,
                            LunariumArmorLeggingsModel::new
                    );

            ArmorClientExtension lunariumLeggingsExtension =
                    new ArmorClientExtension(lunariumLeggingsProvider);

            event.registerItem(
                    lunariumLeggingsExtension,
                    ModItems.LUNARIUM_LEGGINGS.get()
            );



            SimpleModelProvider viridiumOuterProvider =
                    new SimpleModelProvider(
                            ViridiumArmorOuterModel::createBodyLayer,
                            ViridiumArmorOuterModel::new
                    );

            ArmorClientExtension viridiumExtension =
                    new ArmorClientExtension(viridiumOuterProvider);

            event.registerItem(
                    viridiumExtension,
                    ModItems.VIRIDIUM_HELMET.get(),
                    ModItems.VIRIDIUM_CHESTPLATE.get(),
                    ModItems.VIRIDIUM_BOOTS.get()
            );

            SimpleModelProvider viridiumLeggingsProvider =
                    new SimpleModelProvider(
                            ViridiumArmorLeggingsModel::createBodyLayer,
                            ViridiumArmorLeggingsModel::new
                    );

            ArmorClientExtension viridiumLeggingsExtension =
                    new ArmorClientExtension(viridiumLeggingsProvider);

            event.registerItem(
                    viridiumLeggingsExtension,
                    ModItems.VIRIDIUM_LEGGINGS.get()
            );
        }
    }
}