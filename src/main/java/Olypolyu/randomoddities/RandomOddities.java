package Olypolyu.randomoddities;

import Olypolyu.randomoddities.blocks.RandomOdditiesBlocks;
import Olypolyu.randomoddities.items.RandomOdditiesItems;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;


public class RandomOddities implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
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

    @Override
    public void onInitialize() {
        LOGGER.info("RandomOddities initialized.");
    }

	@Override
	public void beforeGameStart() {
		new RandomOdditiesItems().initializeItems();
		new RandomOdditiesBlocks().initializeBlocks();
	}

	@Override
	public void afterGameStart() {

	}

	@Override
	public void onRecipesReady() {
 		new RandomOdditiesRecipes().initializeRecipes();
	}
}