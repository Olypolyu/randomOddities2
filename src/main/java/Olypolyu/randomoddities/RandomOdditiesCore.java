package Olypolyu.randomoddities;

import Olypolyu.randomoddities.blocks.RandomOdditiesBlocks;
import Olypolyu.randomoddities.items.RandomOdditiesItems;

import Olypolyu.randomoddities.tile.TileEntityBubbleColumn;
import Olypolyu.randomoddities.tile.TileEntityVendingMachine;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.EntityHelper;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;

import java.util.HashMap;


public class RandomOdditiesCore implements ModInitializer, GameStartEntrypoint {
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

		EntityHelper.Core.createTileEntity(TileEntityBubbleColumn.class, "randomoddities$bubble_column");
		EntityHelper.Core.createTileEntity(TileEntityVendingMachine.class, "randomoddities$vending_machine");
	}

	@Override
	public void afterGameStart() {
	}
}
