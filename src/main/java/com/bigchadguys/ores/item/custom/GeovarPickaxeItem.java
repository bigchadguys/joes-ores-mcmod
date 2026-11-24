package com.bigchadguys.ores.item.custom;

import com.bigchadguys.ores.config.MorphiteConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class GeovarPickaxeItem extends PickaxeItem {
    public static final TagKey<Block> SAVING_BLOCKS = TagKey.create(
            Registries.BLOCK,
            ResourceLocation.fromNamespaceAndPath(
                    "ores",
                    "geovar_durability_saving"
            )
    );

    public GeovarPickaxeItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean mineBlock(
            ItemStack stack,
            Level level,
            BlockState state,
            BlockPos pos,
            LivingEntity miner
    ) {
        if (!level.isClientSide
                && state.getDestroySpeed(level, pos) != 0.0F
                && state.is(SAVING_BLOCKS)
                && MorphiteConfig.SPEC.isLoaded()
                && level.random.nextDouble()
                < MorphiteConfig.GEOVAR_SAVING.get()) {
            return true;
        }

        return super.mineBlock(stack, level, state, pos, miner);
    }
}