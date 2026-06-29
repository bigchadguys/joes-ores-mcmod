package com.bigchadguys.ores.compat.jade;

import com.bigchadguys.ores.block.ModBlockTags;
import com.bigchadguys.ores.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import snownee.jade.addon.harvest.ToolHandler;

import java.util.List;

public enum JoresToolHandler implements ToolHandler {
    INSTANCE;

    private static final List<ItemStack> TOOLS = List.of(
            ModItems.MYTHRIL_PICKAXE.get().getDefaultInstance(),
            ModItems.ORICHALCUM_PICKAXE.get().getDefaultInstance(),
            ModItems.ADAMANTITE_PICKAXE.get().getDefaultInstance(),
            ModItems.CELESTIUM_PICKAXE.get().getDefaultInstance()
    );

    @Override
    public ItemStack test(BlockState state, Level level, BlockPos pos){
        if (state.is(ModBlockTags.NEEDS_CELESTIUM_TOOL)) {
            return ModItems.CELESTIUM_PICKAXE.get().getDefaultInstance();
        }

        if (state.is(ModBlockTags.NEEDS_ADAMANTITE_TOOL)) {
            return ModItems.ADAMANTITE_PICKAXE.get().getDefaultInstance();
        }

        if (state.is(ModBlockTags.NEEDS_ORICHALCUM_TOOL)) {
            return ModItems.ORICHALCUM_PICKAXE.get().getDefaultInstance();
        }

        if (state.is(ModBlockTags.NEEDS_MYTHRIL_TOOL)) {
            return ModItems.MYTHRIL_PICKAXE.get().getDefaultInstance();
        }

        return ItemStack.EMPTY;
    }

    @Override
    public List<ItemStack> getTools() {
        return TOOLS;
    }

    @Override
    public ResourceLocation getUid() {
        return JoresJadePlugin.REQUIRED_TOOL;
    }
}
