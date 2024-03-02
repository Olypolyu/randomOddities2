package Olypolyu.randomoddities;

import Olypolyu.randomoddities.items.RandomOdditiesItems;
import net.minecraft.core.block.Block;

import net.minecraft.core.data.registry.recipe.RecipeGroup;
import net.minecraft.core.data.registry.recipe.RecipeNamespace;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCrafting;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCraftingShapeless;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryRepairable;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import turniplabs.halplibe.helper.RecipeBuilder;

import java.util.ArrayList;
import java.util.List;

import static Olypolyu.randomoddities.RandomOdditiesCore.MOD_ID;
import static Olypolyu.randomoddities.items.ItemPaintBrush.colours;

public class RandomOdditiesRecipes {
	public static final RecipeNamespace RANDOM_ODDITIES = RecipeBuilder.getRecipeNamespace(MOD_ID);
	public static final RecipeGroup<RecipeEntryCrafting<?, ?>> WORKBENCH = new RecipeGroup<>(new RecipeSymbol(new ItemStack(Block.workbench)));

	public void initializeRecipes() {

		List<RecipeSymbol> recipePaintBrush = new ArrayList<>();
		recipePaintBrush.add(new RecipeSymbol(new ItemStack(Item.stick), 1));
		recipePaintBrush.add(new RecipeSymbol(new ItemStack(Item.cloth), 1));
		for (int colour = 0; colour < colours.length; colour++) {
			List<RecipeSymbol> copyOfRecipe = new ArrayList<>(recipePaintBrush);
			copyOfRecipe.add(new RecipeSymbol(new ItemStack(Item.dye, 1, colours.length - colour)));
			WORKBENCH.register(String.format("paint_brush_%s", colours[colour]), new RecipeEntryCraftingShapeless(copyOfRecipe, new ItemStack(RandomOdditiesItems.paintBrushes[colour])));
		}

		WORKBENCH.register("wind_lamp", new RecipeEntryRepairable(RandomOdditiesItems.windLamp, Item.featherChicken));

		//DataLoader.loadRecipes("/assets/randomoddities/recipes/workbench.json");

		RANDOM_ODDITIES.register("workbench", WORKBENCH);
	}
}
