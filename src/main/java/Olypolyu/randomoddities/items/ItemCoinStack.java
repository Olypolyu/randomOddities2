package Olypolyu.randomoddities.items;

import Olypolyu.randomoddities.interfaces.IRandomOdditiesCoinAmount;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

public class ItemCoinStack extends Item {
	private final int amount;

	public ItemCoinStack(int id, int amount) {
		super(id);
		this.amount = amount;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		((IRandomOdditiesCoinAmount) entityplayer).randomOddities$addCoinAmount(amount);
		return super.onItemRightClick(itemstack, world, entityplayer);
	}
}
