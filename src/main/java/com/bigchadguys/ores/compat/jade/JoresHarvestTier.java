package com.bigchadguys.ores.compat.jade;

import com.bigchadguys.ores.block.ModBlockTags;
import com.bigchadguys.ores.item.ModItems;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;
import java.util.function.Supplier;

public enum JoresHarvestTier {
    MYTHRIL(
            ModBlockTags.NEEDS_MYTHRIL_TOOL,
            ModItems.MYTHRIL_PICKAXE
    ),
    ORICHALCUM(
            ModBlockTags.NEEDS_ORICHALCUM_TOOL,
            ModItems.ORICHALCUM_PICKAXE
    ),
    ADAMANTITE(
            ModBlockTags.NEEDS_ADAMANTITE_TOOL,
            ModItems.ADAMANTITE_PICKAXE
    ),
    CELESTIUM(
            ModBlockTags.NEEDS_CELESTIUM_TOOL,
            ModItems.CELESTIUM_PICKAXE
    );

    private final TagKey<Block> requiredBlockTag;
    private final Supplier<? extends Item> pickaxe;

    JoresHarvestTier(TagKey<Block> requiredBlockTag, Supplier<? extends Item> pickaxe) {
        this.requiredBlockTag = requiredBlockTag;
        this.pickaxe = pickaxe;
    }

    public boolean matches(BlockState state) {
        return state.is(requiredBlockTag);
    }

    public ItemStack getToolStack() {
        return pickaxe.get().getDefaultInstance();
    }

    public static Optional<JoresHarvestTier> fromState(BlockState state) {
        for (JoresHarvestTier tier : values()) {
            if (tier.matches(state)) {
                return Optional.of(tier);
            }
        }
        return Optional.empty();
    }
}
