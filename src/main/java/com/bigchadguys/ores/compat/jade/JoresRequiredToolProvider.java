package com.bigchadguys.ores.compat.jade;

import com.bigchadguys.ores.block.ModBlockTags;
import com.bigchadguys.ores.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IElement;
import snownee.jade.api.ui.IElementHelper;

public enum JoresRequiredToolProvider implements IBlockComponentProvider {
    INSTANCE;

    @Override
    public @Nullable IElement getIcon(BlockAccessor accessor, IPluginConfig config, IElement currentIcon) {
        ItemStack tool = getRequiredTool(accessor);

        if (tool.isEmpty()){
            return currentIcon;
        }

        return IElementHelper.get().item(tool);
    }

    @Override
    public void appendTooltip(ITooltip iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {

    }

    private ItemStack getRequiredTool(BlockAccessor blockAccessor) {
        var state = blockAccessor.getBlockState();

        if (state.is(ModBlockTags.NEEDS_CELESTIUM_TOOL)) {
            return new ItemStack(ModItems.CELESTIUM_PICKAXE.get());
        }

        if (state.is(ModBlockTags.NEEDS_ADAMANTITE_TOOL)) {
            return new ItemStack(ModItems.ADAMANTITE_PICKAXE.get());
        }

        if (state.is(ModBlockTags.NEEDS_ORICHALCUM_TOOL)) {
            return new ItemStack(ModItems.ORICHALCUM_PICKAXE.get());
        }

        if (state.is(ModBlockTags.NEEDS_MYTHRIL_TOOL)) {
            return new ItemStack(ModItems.MYTHRIL_PICKAXE.get());
        }

        return ItemStack.EMPTY;
    }

    @Override
    public ResourceLocation getUid() {
        return JoresJadePlugin.REQUIRED_TOOL;
    }
}
