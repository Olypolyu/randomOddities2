package Olypolyu.randomoddities.items;

import Olypolyu.randomoddities.RandomOdditiesCore;
import Olypolyu.randomoddities.blocks.RandomOdditiesBlocks;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemPlaceable;
import net.minecraft.client.render.item.model.ItemModelStandard;
import turniplabs.halplibe.helper.ItemBuilder;
import turniplabs.halplibe.helper.ItemHelper;

import static Olypolyu.randomoddities.RandomOdditiesCore.MOD_ID;
import static Olypolyu.randomoddities.items.ItemPaintBrush.colours;

public class RandomOdditiesItems {
	private static int randomOdditiesIds = 17000;

	private static final int windLampCharges = 3;
	public static Item[] paintBrushes;
	public static Item windLamp;
	public static Item paintScrapper;
	public static Item coinStack;
	public static Item shield;

	public void initializeItems() {
	    paintBrushes = new Item[colours.length];
		ItemBuilder paintBrushBuilder = new ItemBuilder(MOD_ID)
            .setItemModel(item -> new ItemModelStandard(item, MOD_ID).setFull3D());
        for (int colour = 0; colour < colours.length; colour++) {
            paintBrushes[colour] = paintBrushBuilder
                .setIcon("randomoddities:item/paintBrush_" + colours[colour])
                .build(new ItemPaintBrush(String.format("paint_brush_%s", colours[colour]), randomOdditiesIds++, colour));
        }

    	windLamp = new ItemBuilder(MOD_ID)
    	    .setItemModel(item -> new ItemModelWindLamp(item, MOD_ID))
    	    .setIcon("randomoddities:item/windBottle")
    	    .build(new ItemWindLamp("wind_lamp", randomOdditiesIds++, windLampCharges));

    	paintScrapper = new ItemBuilder(MOD_ID)
    	    .setItemModel(item -> new ItemModelStandard(item, MOD_ID).setFull3D())
    	    .setIcon("randomoddities:item/paintBrush_scraper")
    	    .build(new ItemPaintScrapper("paint_scraper", randomOdditiesIds++));

    	coinStack = new ItemBuilder(MOD_ID)
    	    .setItemModel(item -> new ItemModelStandard(item, MOD_ID).setFull3D())
    	    .setIcon("randomoddities:item/coin_stack")
    	    .build(new ItemCoinStack("coin_stack", randomOdditiesIds++, 15, 5));

    	shield = new ItemBuilder(MOD_ID)
    	    .setItemModel(item -> new ItemModelStandard(item, MOD_ID).setFull3D())
    	    .setIcon("randomoddities:item/shield")
    	    .build(new ItemShield("shield", randomOdditiesIds++, 84, 0.55F, 0.75F, 1F));


		RandomOdditiesCore.info("RandomOddities has loaded items");
	}
}
