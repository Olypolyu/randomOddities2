package Olypolyu.randomoddities;

import Olypolyu.randomoddities.blocks.RandomOdditiesBlocks;
import Olypolyu.randomoddities.items.RandomOdditiesItems;

import Olypolyu.randomoddities.tile.TileEntityBubbleColumn;
import Olypolyu.randomoddities.tile.TileEntityVendingMachine;
import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.EntityHelper;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;
import turniplabs.halplibe.helper.RecipeBuilder;
import Olypolyu.randomoddities.blocks.BlockFishTrap;

import java.util.HashMap;


public class RandomOdditiesCore implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
    public static final String MOD_ID = "randomoddities";
    private static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static String concatenate(Object ...args){
		if (args.length > 1) {
			StringBuilder result = new StringBuilder();
			for (int arg = 0; arg < args.length; arg++) {
				result.append(args[arg]);
				if(arg != args.length - 1) result.append(", ");
			}
			return result.toString();
		}
		else return String.valueOf(args[0]);
	}

	public static void info(Object ...args) {
		LOGGER.info(concatenate(args));
	}
	public static void warn(Object ...args) {
		LOGGER.warn(concatenate(args));
	}
	public static void error(Object ...args) {
		LOGGER.error(concatenate(args));
	}

	public static HashMap<String, Integer> theBank = new HashMap<>();

    @Override
    public void onInitialize() {
        LOGGER.info("RandomOddities initialized.");
    }

	@Override
	public void beforeGameStart() {
		new RandomOdditiesAssets().initializeAssets();
		new RandomOdditiesItems().initializeItems();
		new RandomOdditiesBlocks().initializeBlocks();
	}

	@Override
	public void afterGameStart() {
		BlockFishTrap.fishingLoot.add(new WeightedRandomLootObject(new ItemStack(Block.tnt), 1, 3));
		BlockFishTrap.fishingLoot.add(new WeightedRandomLootObject(new ItemStack(Block.planksOakPainted, 1, 15), 1, 16));

		EntityHelper.createTileEntity(TileEntityBubbleColumn.class, "randomoddities$bubble_column");
		EntityHelper.createTileEntity(TileEntityVendingMachine.class, "randomoddities$vending_machine");
	}

	@Override
	public void onRecipesReady() {
	    new RandomOdditiesRecipes().initializeRecipes();
    }

    @Override
    public void initNamespaces() {
        RecipeBuilder.initNameSpace(MOD_ID);
    }
}
