package Olypolyu.randomoddities.items;

import Olypolyu.randomoddities.RandomOddities;
import net.minecraft.core.item.Item;
import turniplabs.halplibe.helper.ItemHelper;

import static Olypolyu.randomoddities.RandomOddities.MOD_ID;
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

	public void initializeItems() {
		RandomOddities.info("RandomOddities has loaded items");
	}
}
