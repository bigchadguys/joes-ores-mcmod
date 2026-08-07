package com.bigchadguys.ores.item.custom;

import com.bigchadguys.ores.JoesOres;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class ViridiumArmorItem extends ArmorItem {
    private static final ResourceLocation LAYER_1 =
            ResourceLocation.fromNamespaceAndPath(
                    JoesOres.MOD_ID,
                    "textures/models/armor/custom/viridium_layer_1.png"
            );
    private static final ResourceLocation LAYER_2 =
            ResourceLocation.fromNamespaceAndPath(
                    JoesOres.MOD_ID,
                    "textures/models/armor/custom/viridium_layer_2.png"
            );

    public ViridiumArmorItem(Holder<ArmorMaterial> material, ArmorItem.Type type, Item.Properties properties) {
        super(material, type, properties);
    }

    @Override
    public @Nullable ResourceLocation getArmorTexture(
            ItemStack stack,
            Entity entity,
            EquipmentSlot slot,
            ArmorMaterial.Layer layer,
            boolean inner
    ) {
        return inner ? LAYER_2 : LAYER_1;
    }
}
