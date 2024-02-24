package Olypolyu.randomoddities.items;

import Olypolyu.randomoddities.RandomOddities;
import net.minecraft.core.item.Item;
import turniplabs.halplibe.helper.ItemHelper;

import static Olypolyu.randomoddities.RandomOddities.MOD_ID;

public class RandomOdditiesItems {
	private static int randomOdditiesIds = 17000;

	public static final String[] colours = {
		"white", "orange", "magenta", "lightBlue", "yellow", "lime", "pink", "gray", "lightGray", "cyan", "purple",
		"blue", "brown", "green", "red", "black"
	};
	public static final Item[] paintBrushes = new Item[colours.length];

	static {
		for (int colour = 0; colour < colours.length; colour ++) {
			RandomOddities.info(colours[colour]);
			RandomOddities.info(colour);

			paintBrushes[colour] = ItemHelper.createItem(MOD_ID, new ItemPaintBrush(randomOdditiesIds++, colour).setKey(String.format("paint_brush.%s", colours[colour])),
				String.format("paintBrush/%s.png", colours[colour]))
				.setMaxStackSize(1);
		}
	}

	public void initializeItems() {
		RandomOddities.info("RandomOddities has loaded items");
	}
}
