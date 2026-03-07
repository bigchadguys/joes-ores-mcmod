package com.bigchadguys.ores.datagen.custom;

import com.bigchadguys.ores.JoesOres;
import com.bigchadguys.ores.recipe.MorphiteSynthesizerRecipe;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class MorphiteSynthesizerRecipeBuilder {

    private final Ingredient upgrade;
    private final ItemStack result;

    public MorphiteSynthesizerRecipeBuilder(Ingredient upgrade, ItemStack result) {
        this.upgrade = upgrade;
        this.result = result;
    }

    public static MorphiteSynthesizerRecipeBuilder create(Ingredient upgrade, ItemStack result) {
        return new MorphiteSynthesizerRecipeBuilder(upgrade, result);
    }

    public void save(RecipeOutput out, String id) {
        out.accept(
                ResourceLocation.fromNamespaceAndPath(
                        JoesOres.MOD_ID,
                        "morphite_synthesizing/" + id
                ),
                new MorphiteSynthesizerRecipe(
                        Ingredient.EMPTY,
                        Ingredient.EMPTY,
                        upgrade,
                        result
                ),
                null
        );
    }

}
