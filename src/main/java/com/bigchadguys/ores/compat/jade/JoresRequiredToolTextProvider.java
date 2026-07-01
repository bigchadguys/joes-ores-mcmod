package com.bigchadguys.ores.compat.jade;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum JoresRequiredToolTextProvider implements IBlockComponentProvider {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        JoresHarvestTier.fromState(accessor.getBlockState()).ifPresent(tier -> {
            ItemStack tool = tier.getToolStack();

            tooltip.add(coloredRequiredTool(tool));
        });
    }

    private static Component coloredRequiredTool(ItemStack tool) {
        return Component.translatable("tooltip.ores.requires_tool")
                .withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xC76DFF)))
                .append(tool.getHoverName().copy()
                        .withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFFD166))));
    }

    @Override
    public ResourceLocation getUid() {
        return JoresJadePlugin.JORES_REQUIRED_TOOL_TEXT;
    }
}
