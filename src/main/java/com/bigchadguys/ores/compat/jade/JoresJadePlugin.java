package com.bigchadguys.ores.compat.jade;

import com.bigchadguys.ores.JoesOres;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import snownee.jade.addon.harvest.HarvestToolProvider;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class JoresJadePlugin implements IWailaPlugin {
    public static final ResourceLocation JORES_HARVEST_TOOLS =
            ResourceLocation.fromNamespaceAndPath(JoesOres.MOD_ID, "harvest_tool");

    public static final ResourceLocation JORES_REQUIRED_TOOL_TEXT =
            ResourceLocation.fromNamespaceAndPath(JoesOres.MOD_ID, "required_tool_text");

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        HarvestToolProvider.registerHandler(JoresToolHandler.INSTANCE);

        registration.addConfig(JORES_REQUIRED_TOOL_TEXT, true);
        registration.registerBlockComponent(JoresRequiredToolTextProvider.INSTANCE, Block.class);
    }
}
