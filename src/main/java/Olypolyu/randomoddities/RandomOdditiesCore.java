package Olypolyu.randomoddities;

import Olypolyu.randomoddities.blocks.RandomOdditiesBlocks;
import Olypolyu.randomoddities.items.RandomOdditiesItems;

import Olypolyu.randomoddities.tile.TileEntityBubbleColumn;
import Olypolyu.randomoddities.tile.TileEntityVendingMachine;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.EntityHelper;
import turniplabs.halplibe.util.ClientStartEntrypoint;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;

import java.util.HashMap;


public class RandomOdditiesCore implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
    public static final String MOD_ID = "randomoddities";
    private static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static void info(Object arg) {
		LOGGER.info(String.valueOf(arg));
	}

	public static void warn(Object arg) {
		LOGGER.warn(String.valueOf(arg));
	}

	public static void error(Object arg) {
		LOGGER.error(String.valueOf(arg));
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

		EntityHelper.Core.createTileEntity(TileEntityBubbleColumn.class, "randomoddities$bubble_column");
		EntityHelper.Core.createTileEntity(TileEntityVendingMachine.class, "randomoddities$vending_machine");
	}

	@Override
	public void afterGameStart() {
	}

	@Override
	public void onRecipesReady() {
 		new RandomOdditiesRecipes().initializeRecipes();
	}
}
