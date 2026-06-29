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
    public static final ResourceLocation REQUIRED_TOOL =
            ResourceLocation.fromNamespaceAndPath(JoesOres.MOD_ID, "required_tool");

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        HarvestToolProvider.registerHandler(JoresToolHandler.INSTANCE);
    }
}
