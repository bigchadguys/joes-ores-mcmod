package com.bigchadguys.ores.item.armor;

import com.bigchadguys.ores.JoesOres;
import com.bigchadguys.ores.item.ModArmorMaterials;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.jetbrains.annotations.Nullable;

public class SolariumArmorItem extends AbstractArmorItem {
    private static final ResourceLocation TEXTURE_LOCATION = makeCustomTextureLocation(JoesOres.MOD_ID, "solarium");

    public SolariumArmorItem(Type pType) {
        super(ModArmorMaterials.SOLARIUM, pType, new Properties().rarity(Rarity.EPIC));
    }
    @Override
    public @Nullable ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean inner) {
        return TEXTURE_LOCATION;
    }
}
