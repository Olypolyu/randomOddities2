package Olypolyu.randomoddities.items;

import Olypolyu.randomoddities.RandomOdditiesCore;
import Olypolyu.randomoddities.blocks.RandomOdditiesBlocks;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemPlaceable;
import net.minecraft.core.item.block.ItemBlock;
import turniplabs.halplibe.helper.ItemHelper;

import static Olypolyu.randomoddities.RandomOdditiesCore.MOD_ID;
import static Olypolyu.randomoddities.items.ItemPaintBrush.colours;

public class RandomOdditiesItems {
	private static int randomOdditiesIds = 17000;

	public static final Item[] paintBrushes = new Item[colours.length];

	static {
		for (int colour = 0; colour < colours.length; colour ++) {
			paintBrushes[colour] = ItemHelper.createItem(MOD_ID, new ItemPaintBrush(randomOdditiesIds++, colour).setKey(String.format("paint_brush.%s", colours[colour])),
				String.format("paintBrush/%s.png", colours[colour]));
		}
	}

	private static final int windLampCharges = 3;
	public static final Item windLamp = ItemHelper.createItem(MOD_ID, new ItemWindLamp(randomOdditiesIds++, windLampCharges).setKey("wind_lamp"));

	public static final Item paintScrapper = ItemHelper.createItem(MOD_ID, new ItemPaintScrapper(randomOdditiesIds++).setKey("paint_brush.scrapper"), "paintBrush/scrapper.png");

	public static final Item coinStack = ItemHelper.createItem(MOD_ID, new ItemCoinStack(randomOdditiesIds++, 15, 5).setKey("coin_stack"), "coin_stack.png");
	public static final Item itemPumpkinPie = ItemHelper.createItem(MOD_ID, new ItemPlaceable("pumpkin_pie", randomOdditiesIds++, RandomOdditiesBlocks.pumpkinPie).setIconCoord(13, 3));

	public void initializeItems() {
		RandomOdditiesCore.info("RandomOddities has loaded items");
	}
}
