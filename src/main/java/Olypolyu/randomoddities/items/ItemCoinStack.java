package Olypolyu.randomoddities.items;

import Olypolyu.randomoddities.interfaces.IRandomOdditiesCoinAmount;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

public class ItemCoinStack extends Item {

	private final int amountMax;
	private final int amountMin;

	public ItemCoinStack(String key, int id, int amountMax, int amountMin) {
		super(key, id);
		this.amountMax = amountMax;
		this.amountMin = amountMin;
	}

	@Override
	public ItemStack onUseItem(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		itemstack.consumeItem(entityplayer);
		if (itemstack.stackSize < 1) itemstack = null;

		int coinAmount = world.rand.nextInt(this.amountMax - this.amountMin) + this.amountMin;
		((IRandomOdditiesCoinAmount) entityplayer).randomOddities$addCoinAmount(coinAmount);
		world.playSoundAtEntity(entityplayer, entityplayer, "randomoddities.coin_chime",0.65F,1.0F);

		for ( int coin = 0; coin < coinAmount; coin++) {
			int motionMod = world.rand.nextBoolean() ? 1 : -1;
			double rad = Math.toRadians((double) (360/coinAmount) * coin);
			double radius = 1.25;
			world.spawnParticle(
				"coin",
				entityplayer.x + (Math.cos(rad) * radius),
				entityplayer.y - 1,
				entityplayer.z + (Math.sin(rad) * radius),
				world.rand.nextDouble() / 4 * motionMod,
				world.rand.nextDouble() / 4 * motionMod,
				world.rand.nextDouble() / 4 * motionMod,
				0, 0
			);
		}

		return super.onUseItem(itemstack, world, entityplayer);
	}
}
