package com.bigchadguys.ores.block.entity.custom;

import com.bigchadguys.ores.block.ModBlocks;
import com.bigchadguys.ores.block.entity.ModBlockEntities;
import com.bigchadguys.ores.item.ModItems;
import com.bigchadguys.ores.screen.ModMenuTypes;
import com.bigchadguys.ores.screen.custom.AdamantiteFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.NotNull;

public class AdamantiteFurnaceBlockEntity extends AbstractFurnaceBlockEntity {

    private final RecipeManager.CachedCheck<SingleRecipeInput, SmeltingRecipe> recipeCheck =
            RecipeManager.createCheck(RecipeType.SMELTING);

    private boolean celestiumIsDaytime;
    private boolean hasCelestiumMode;

    private boolean inputRecipeDirty = true;
    private boolean cachedHasVanillaRecipe = false;
    private int cachedCookTime = AbstractFurnaceBlockEntity.BURN_TIME_STANDARD;

    public AdamantiteFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ADAMANTITE_FURNACE_BLOCK_ENTITY.get(), pos, state, RecipeType.SMELTING);
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return Component.translatable("container.joesores.adamantite_furnace");
    }

    @Override
    protected @NotNull AbstractContainerMenu createMenu(int windowId, @NotNull Inventory playerInventory) {
        return new AdamantiteFurnaceMenu(
                ModMenuTypes.ADAMANTITE_FURNACE_MENU.get(),
                windowId,
                playerInventory,
                this,
                this,
                this.dataAccess
        );
    }

    @Override
    public void setItem(int slot, @NotNull ItemStack stack) {
        super.setItem(slot, stack);

        if (slot == 0) {
            this.inputRecipeDirty = true;
            this.hasCelestiumMode = false;
            this.celestiumIsDaytime = false;
            this.dataAccess.set(DATA_COOKING_PROGRESS, 0);

            Level level = this.getLevel();
            if (level != null) {
                refreshRecipeCache(level);

                int totalTime = isCelestiumInput(stack)
                        ? 66
                        : Math.max(1, this.cachedCookTime / 3);

                if (this.dataAccess.get(DATA_COOKING_TOTAL_TIME) != totalTime) {
                    this.dataAccess.set(DATA_COOKING_TOTAL_TIME, totalTime);
                }
            }

            setChanged();
        }
    }

    private static boolean isCelestiumInput(ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }

        Item item = stack.getItem();
        return item == ModItems.RAW_CELESTIUM.get()
                || item == ModBlocks.CELESTIUM_ORE.get().asItem()
                || item == ModBlocks.DEEPSLATE_CELESTIUM_ORE.get().asItem();
    }

    private void refreshRecipeCache(Level world) {
        if (world == null) {
            return;
        }

        ItemStack input = this.getItem(0);
        if (input.isEmpty()) {
            this.cachedHasVanillaRecipe = false;
            this.cachedCookTime = AbstractFurnaceBlockEntity.BURN_TIME_STANDARD;
            this.inputRecipeDirty = false;
            return;
        }

        var recipe = this.recipeCheck.getRecipeFor(new SingleRecipeInput(input), world);
        this.cachedHasVanillaRecipe = recipe.isPresent();
        this.cachedCookTime = recipe.map(holder -> holder.value().getCookingTime())
                .orElse(AbstractFurnaceBlockEntity.BURN_TIME_STANDARD);
        this.inputRecipeDirty = false;
    }

    public static boolean isFuel(ItemStack stack) {
        return AbstractFurnaceBlockEntity.isFuel(stack);
    }

    public boolean isLit() {
        return dataAccess.get(DATA_LIT_TIME) > 0;
    }

    public static void serverTick(Level world, BlockPos pos, BlockState state, AdamantiteFurnaceBlockEntity furnace) {
        ItemStack inputStack = furnace.items.get(0);
        ItemStack fuelStack = furnace.items.get(1);
        ItemStack outputStack = furnace.items.get(2);

        boolean wasLit = furnace.isLit();
        boolean dirty = false;

        if (!furnace.isLit()
                && inputStack.isEmpty()
                && fuelStack.isEmpty()
                && furnace.dataAccess.get(DATA_COOKING_PROGRESS) == 0) {
            return;
        }

        boolean isCelestiumInput = isCelestiumInput(inputStack);

        if (furnace.inputRecipeDirty) {
            furnace.refreshRecipeCache(world);
        }

        boolean hasVanillaRecipe = furnace.cachedHasVanillaRecipe;

        if (!furnace.isLit()
                && !fuelStack.isEmpty()
                && AbstractFurnaceBlockEntity.isFuel(fuelStack)
                && !inputStack.isEmpty()
                && (isCelestiumInput || hasVanillaRecipe)) {

            int burnTime = 1600;
            furnace.dataAccess.set(DATA_LIT_TIME, burnTime);
            furnace.dataAccess.set(DATA_LIT_DURATION, burnTime);
            fuelStack.shrink(1);
            dirty = true;
        }

        if (furnace.isLit()) {
            furnace.dataAccess.set(DATA_LIT_TIME, furnace.dataAccess.get(DATA_LIT_TIME) - 1);
        }

        if (isCelestiumInput) {
            if (furnace.isLit()) {
                int prog = furnace.dataAccess.get(DATA_COOKING_PROGRESS);

                if (!furnace.hasCelestiumMode && prog == 0) {
                    furnace.celestiumIsDaytime = (world.getDayTime() % 24000L) < 12000L;
                    furnace.hasCelestiumMode = true;
                }

                Item outItem = furnace.celestiumIsDaytime
                        ? ModItems.SOLARIUM_INGOT.get()
                        : ModItems.LUNARIUM_INGOT.get();

                if (outputStack.isEmpty() || outputStack.getItem() == outItem) {
                    prog += 1;
                    furnace.dataAccess.set(DATA_COOKING_PROGRESS, prog);

                    int total = 66;
                    if (furnace.dataAccess.get(DATA_COOKING_TOTAL_TIME) != total) {
                        furnace.dataAccess.set(DATA_COOKING_TOTAL_TIME, total);
                    }

                    if (prog >= total) {
                        inputStack.shrink(1);

                        if (outputStack.isEmpty()) {
                            furnace.items.set(2, new ItemStack(outItem, 1));
                        } else {
                            outputStack.grow(1);
                        }

                        furnace.dataAccess.set(DATA_COOKING_PROGRESS, 0);
                        furnace.hasCelestiumMode = false;
                        furnace.celestiumIsDaytime = false;
                        furnace.inputRecipeDirty = true; // input count changed
                        dirty = true;
                    }
                }
            } else {
                int prog = furnace.dataAccess.get(DATA_COOKING_PROGRESS);
                if (prog > 0) {
                    furnace.dataAccess.set(DATA_COOKING_PROGRESS, Math.max(prog - 2, 0));
                    dirty = true;
                }
            }
        } else {
            // Use vanilla furnace logic for non-celestium items
            AbstractFurnaceBlockEntity.serverTick(world, pos, state, furnace);

            int expectedTotal = Math.max(1, furnace.cachedCookTime / 3);
            if (furnace.dataAccess.get(DATA_COOKING_TOTAL_TIME) != expectedTotal) {
                furnace.dataAccess.set(DATA_COOKING_TOTAL_TIME, expectedTotal);
            }
        }

        boolean nowLit = furnace.isLit();
        if (wasLit != nowLit) {
            dirty = true;
            world.setBlock(pos, state.setValue(BlockStateProperties.LIT, nowLit), 3);
        }

        if (dirty) {
            furnace.setChanged();
        }
    }
}