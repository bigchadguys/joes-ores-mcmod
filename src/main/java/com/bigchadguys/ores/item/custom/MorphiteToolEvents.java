package com.bigchadguys.ores.item.custom;

import com.bigchadguys.ores.JoesOres;
import com.bigchadguys.ores.config.MorphiteConfig;
import com.bigchadguys.ores.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import net.neoforged.neoforge.event.level.BlockDropsEvent;

@EventBusSubscriber(modid = JoesOres.MOD_ID)
public final class MorphiteToolEvents {

    @SubscribeEvent
    public static void attributes(ItemAttributeModifierEvent event) {
        if (!event.getItemStack().is(ModItems.SWIFTITE_SHOVEL.get())) {
            return;
        }

        double bonus = MorphiteConfig.SPEC.isLoaded()
                ? MorphiteConfig.SWIFTITE_SPEED.get()
                : 0.10;

        if (bonus <= 0) {
            return;
        }

        event.addModifier(
                Attributes.MOVEMENT_SPEED,
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath(
                                "ores",
                                "swiftite_speed"
                        ),
                        bonus,
                        AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ),
                EquipmentSlotGroup.MAINHAND
        );
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void leaves(BlockDropsEvent event) {
        ItemStack tool = event.getTool();

        if (!tool.is(ModItems.PETRAFITE_AXE.get())
                || !event.getState().is(BlockTags.LEAVES)
                || !MorphiteConfig.SPEC.isLoaded()) {
            return;
        }

        int minimum = MorphiteConfig.LEAF_FORTUNE.get();

        if (minimum == 0) {
            return;
        }

        var enchantments = event.getLevel()
                .registryAccess()
                .lookupOrThrow(Registries.ENCHANTMENT);

        var fortune = enchantments.getOrThrow(Enchantments.FORTUNE);
        var silk = enchantments.getOrThrow(Enchantments.SILK_TOUCH);

        if (EnchantmentHelper.getItemEnchantmentLevel(silk, tool) > 0
                || EnchantmentHelper.getItemEnchantmentLevel(fortune, tool)
                >= minimum) {
            return;
        }

        // Enchant a copy, leaving the player's real axe untouched.
        ItemStack fortuneTool = tool.copy();
        fortuneTool.enchant(fortune, minimum);

        // Evaluate this leaf block's own loot table with the copied tool.
        var drops = Block.getDrops(
                event.getState(),
                event.getLevel(),
                event.getPos(),
                event.getBlockEntity(),
                event.getBreaker(),
                fortuneTool
        );

        // Replace the original drops rather than adding a second batch.
        event.getDrops().clear();

        for (ItemStack drop : drops) {
            ItemEntity entity = new ItemEntity(
                    event.getLevel(),
                    event.getPos().getX() + 0.5,
                    event.getPos().getY() + 0.5,
                    event.getPos().getZ() + 0.5,
                    drop
            );

            entity.setDefaultPickUpDelay();
            event.getDrops().add(entity);
        }
    }

    private MorphiteToolEvents() {}
}