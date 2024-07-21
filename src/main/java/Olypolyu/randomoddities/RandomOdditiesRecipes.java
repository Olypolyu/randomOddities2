package Olypolyu.randomoddities;

import Olypolyu.randomoddities.blocks.RandomOdditiesBlocks;
import Olypolyu.randomoddities.items.RandomOdditiesItems;
import net.minecraft.core.block.Block;

import net.minecraft.core.data.registry.recipe.RecipeGroup;
import net.minecraft.core.data.registry.recipe.RecipeNamespace;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCrafting;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCraftingShaped;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCraftingShapeless;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryRepairableStackable;
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

		for (int colour = 0; colour < 15; colour++) {
			List<RecipeSymbol> copyOfRecipe = new ArrayList<>(recipePaintBrush);
			RandomOdditiesCore.info(colour, 15 - colour);
			copyOfRecipe.add(new RecipeSymbol(new ItemStack(Item.dye, 1, 15 - colour)));
			WORKBENCH.register(String.format("paint_brush_%s", colours[colour]), new RecipeEntryCraftingShapeless(copyOfRecipe, new ItemStack(RandomOdditiesItems.paintBrushes[colour])));
		}
		WORKBENCH.register("wind_lamp", new RecipeEntryRepairableStackable(RandomOdditiesItems.windLamp, new ItemStack(Item.featherChicken)));

		List<RecipeSymbol> flintRecipe = new ArrayList<>();
		flintRecipe.add(new RecipeSymbol(new ItemStack(RandomOdditiesBlocks.flintBlock), 1));
		WORKBENCH.register("flint", new RecipeEntryCraftingShapeless(flintRecipe, new ItemStack(Item.flint, 4)));

		RecipeSymbol[] flintBlockRecipe = new RecipeSymbol[] {
			new RecipeSymbol(new ItemStack(Item.flint)), new RecipeSymbol(new ItemStack(Item.flint)),
			new RecipeSymbol(new ItemStack(Item.flint)), new RecipeSymbol(new ItemStack(Item.flint))
		};
		WORKBENCH.register("flint_block", new RecipeEntryCraftingShaped(2, 2, flintBlockRecipe, new ItemStack(RandomOdditiesBlocks.flintBlock)));

		RecipeSymbol[] trampolineRecipe = {
			new RecipeSymbol(new ItemStack(Item.dye, 1, 4)), new RecipeSymbol(new ItemStack(Item.featherChicken)), new RecipeSymbol(new ItemStack(Item.dye, 1, 4)),
			new RecipeSymbol(new ItemStack(Item.ingotIron)), new RecipeSymbol(new ItemStack(Item.featherChicken)), new RecipeSymbol(new ItemStack(Item.ingotIron))
		};
		WORKBENCH.register("trampoline", new RecipeEntryCraftingShaped(3, 2, trampolineRecipe, new ItemStack(RandomOdditiesBlocks.trampoline)));

		RecipeSymbol[] pillowRecipe = {
			new RecipeSymbol(new ItemStack(Block.wool)), new RecipeSymbol(new ItemStack(Block.wool)), new RecipeSymbol(new ItemStack(Block.wool)),
			new RecipeSymbol(new ItemStack(Item.featherChicken)), new RecipeSymbol(new ItemStack(Item.featherChicken)), new RecipeSymbol(new ItemStack(Item.featherChicken))
		};
		WORKBENCH.register("pillow", new RecipeEntryCraftingShaped(3, 2, pillowRecipe, new ItemStack(RandomOdditiesBlocks.pillow)));

		RecipeSymbol[] bubbleGeneratorRecipe = {
			new RecipeSymbol(new ItemStack(Item.dye, 1, 4)),new RecipeSymbol(new ItemStack(Item.featherChicken)), new RecipeSymbol(new ItemStack(Item.dye, 1, 4)),
			new RecipeSymbol(new ItemStack(Item.ingotIron)), new RecipeSymbol(new ItemStack(Block.mesh)), new RecipeSymbol(new ItemStack(Item.ingotIron))
		};
		WORKBENCH.register("bubble_generator", new RecipeEntryCraftingShaped(3, 2, bubbleGeneratorRecipe, new ItemStack(RandomOdditiesBlocks.bubbleColumn)));

		RecipeSymbol[] fishTrapRecipe = {
			null, new RecipeSymbol(new ItemStack(Block.algae)), null,
			new RecipeSymbol(new ItemStack(Block.algae)), new RecipeSymbol(new ItemStack(Block.mesh)), new RecipeSymbol(new ItemStack(Block.algae)),
			null, new RecipeSymbol(new ItemStack(Block.algae)), null
		};
		WORKBENCH.register("fish_trap", new RecipeEntryCraftingShaped(3, 3, fishTrapRecipe, new ItemStack(RandomOdditiesBlocks.fishTrap)));

		RecipeSymbol[] fireStriker = {
			new RecipeSymbol(new ItemStack(Block.cobbleStone)), new RecipeSymbol(new ItemStack(Block.cobbleStone)), new RecipeSymbol(new ItemStack(Block.cobbleStone)),
			new RecipeSymbol(new ItemStack(Block.cobbleStone)), new RecipeSymbol(new ItemStack(Item.toolFirestriker)), new RecipeSymbol(new ItemStack(Block.cobbleStone)),
			new RecipeSymbol(new ItemStack(Block.cobbleStone)), new RecipeSymbol(new ItemStack(Block.cobbleStone)), new RecipeSymbol(new ItemStack(Block.cobbleStone))
		};
		WORKBENCH.register("fire_striker", new RecipeEntryCraftingShaped(3, 3, fireStriker, new ItemStack(RandomOdditiesBlocks.fireStriker)));

		RecipeSymbol[] paintScrapper = {
			new RecipeSymbol(new ItemStack(Item.ingotIron)), new RecipeSymbol(new ItemStack(Item.stick)), new RecipeSymbol(new ItemStack(Item.stick))
		};
		WORKBENCH.register("paint_scraper", new RecipeEntryCraftingShaped(1, 3, paintScrapper, new ItemStack(RandomOdditiesItems.paintScrapper)));

		RANDOM_ODDITIES.register("workbench", WORKBENCH);
	}
}
