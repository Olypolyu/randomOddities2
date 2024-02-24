package Olypolyu.randomoddities;

import Olypolyu.randomoddities.items.RandomOdditiesItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.data.DataLoader;
import net.minecraft.core.data.registry.recipe.RecipeGroup;
import net.minecraft.core.data.registry.recipe.RecipeNamespace;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCrafting;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCraftingShapeless;
import net.minecraft.core.item.ItemStack;
import turniplabs.halplibe.helper.RecipeHelper;

public class RandomOdditiesRecipes {
	public static final RecipeNamespace RANDOM_ODDITIES = new RecipeNamespace();
	public static final RecipeGroup<RecipeEntryCrafting<?, ?>> WORKBENCH = new RecipeGroup<>(new RecipeSymbol(new ItemStack(Block.workbench)));

	public void initializeRecipes() {
		RANDOM_ODDITIES.register("workbench", WORKBENCH);
		//WORKBENCH.register("randomoddities:workbench/paint_brush", new RecipeEntryCraftingShapeless(, new ItemStack(RandomOdditiesItems.PaintBrush).getData().putInt("colour", 3)));

		//DataLoader.loadRecipes("/assets/randomoddities/recipes/workbench.json");
	}
}
