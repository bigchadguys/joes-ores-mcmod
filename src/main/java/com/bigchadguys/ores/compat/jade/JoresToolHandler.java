package com.bigchadguys.ores.compat.jade;

import com.bigchadguys.ores.block.ModBlockTags;
import com.bigchadguys.ores.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import snownee.jade.addon.harvest.ToolHandler;

import java.util.Arrays;
import java.util.List;

public enum JoresToolHandler implements ToolHandler {
    INSTANCE;

    private static final List<ItemStack> TOOLS = Arrays.stream(JoresHarvestTier.values())
            .map(JoresHarvestTier::getToolStack)
            .toList();

    @Override
    public ItemStack test(BlockState state, Level level, BlockPos pos){
        return JoresHarvestTier.fromState(state)
                .map(JoresHarvestTier::getToolStack)
                .orElse(ItemStack.EMPTY);
    }

    @Override
    public List<ItemStack> getTools() {
        return TOOLS;
    }

    @Override
    public ResourceLocation getUid() {
        return JoresJadePlugin.JORES_HARVEST_TOOLS;
    }
}
