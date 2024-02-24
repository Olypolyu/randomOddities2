package Olypolyu.randomoddities.util;

import Olypolyu.randomoddities.interfaces.IRandomOdditiesPainter;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

public class RandomOdditiesPainter implements IRandomOdditiesPainter {
	private final boolean isComplex;
	private final int result;
	private final boolean isBase;

	public RandomOdditiesPainter(Boolean isComplex, boolean isBase, int result) {
		this.isComplex = isComplex;
		this.result = result;
		this.isBase = isBase;
	}

	public boolean apply(int colour, World world, int x, int y, int z, ItemStack itemstack, EntityPlayer entityplayer) {
		int blockData = world.getBlockMetadata(x, y, z);

		 if (isComplex && (world.getBlockMetadata(x, y, z) >> 4) == colour) return false;
		 else if (!isBase && world.getBlockMetadata(x, y, z) == colour) return false;

		// using raw here so chests don't drop their contents on the floor, might be a bad idea, dunno.
		world.setBlockRawWithNotify(x, y, z, this.result);

		// all blocks have their color variants spaced on 16 by 16.
		// I just figure out the difference between the current block and a block of "id 0" on the desired color and set to that.
		if(isComplex) {
			world.setBlockMetadataWithNotify(x, y, z,(blockData % 16) + (colour * 16));
		} else {
			world.setBlockMetadataWithNotify(x, y, z, colour);
			itemstack.damageItem(1, entityplayer);
		}

		return true;
	}
}
