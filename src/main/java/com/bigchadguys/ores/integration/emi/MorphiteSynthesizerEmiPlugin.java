package com.bigchadguys.ores.integration.emi;

import com.bigchadguys.ores.JoesOres;
import com.bigchadguys.ores.block.ModBlocks;
import com.bigchadguys.ores.recipe.ModRecipes;
import com.bigchadguys.ores.recipe.MorphiteSynthesizerRecipe;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;

@EmiEntrypoint
public class MorphiteSynthesizerEmiPlugin implements EmiPlugin {

    public static final ResourceLocation CATEGORY_ID =
            ResourceLocation.fromNamespaceAndPath(JoesOres.MOD_ID, "morphite_synthesizing");

    public static final EmiStack WORKSTATION =
            EmiStack.of(ModBlocks.MORPHITE_SYNTHESIZER.get());

    public static final EmiRecipeCategory CATEGORY =
            new EmiRecipeCategory(CATEGORY_ID, WORKSTATION, EmiTexture.FULL_ARROW);

    @Override
    public void register(EmiRegistry registry) {

        registry.addCategory(CATEGORY);
        registry.addWorkstation(CATEGORY, WORKSTATION);

        for (RecipeHolder<MorphiteSynthesizerRecipe> holder :
                registry.getRecipeManager().getAllRecipesFor(ModRecipes.MORPHITE_SYNTHESIZER_TYPE.get())) {
            registry.addRecipe(new MorphiteSynthesizerEmiRecipe(holder));
        }
    }
}
