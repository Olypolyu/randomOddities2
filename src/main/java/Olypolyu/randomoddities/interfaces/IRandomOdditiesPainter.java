package Olypolyu.randomoddities.interfaces;

import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

public interface IRandomOdditiesPainter {
	boolean apply(int colour, World world, int x, int y, int z, ItemStack paintBrushItem, EntityPlayer player);
}
