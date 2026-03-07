package com.bigchadguys.ores.integration.emi;

import com.bigchadguys.ores.recipe.MorphiteSynthesizerRecipe;
import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.world.item.crafting.RecipeHolder;

public class MorphiteSynthesizerEmiRecipe extends BasicEmiRecipe {

    public MorphiteSynthesizerEmiRecipe(RecipeHolder<MorphiteSynthesizerRecipe> holder) {
        super(
                MorphiteSynthesizerEmiPlugin.CATEGORY,
                holder.id(),
                110, 40
        );

        MorphiteSynthesizerRecipe recipe = holder.value();

        this.inputs.add(EmiIngredient.of(recipe.catalystItem()));
        this.inputs.add(EmiIngredient.of(recipe.morphiteItem()));
        this.inputs.add(EmiIngredient.of(recipe.upgradeItem()));

        this.outputs.add(EmiStack.of(recipe.output()));
    }

    @Override
    public void addWidgets(WidgetHolder w) {

        w.addSlot(inputs.get(0), 0, 12);
        w.addSlot(inputs.get(1), 22, 12);
        w.addSlot(inputs.get(2), 44, 12);

        w.addTexture(dev.emi.emi.api.render.EmiTexture.FULL_ARROW, 66, 14);

        w.addSlot(outputs.getFirst(), 90, 12).recipeContext(this);
    }
}
