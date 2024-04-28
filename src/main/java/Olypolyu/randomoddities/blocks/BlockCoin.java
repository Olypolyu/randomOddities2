package Olypolyu.randomoddities.blocks;

import Olypolyu.randomoddities.interfaces.IRandomOdditiesCoinAmount;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.World;

public class BlockCoin extends Block {

	private final int amountMax;
	private final int amountMin;

	public BlockCoin(String key, int id, Material material, int amountMax, int amountMin) {
		super(key, id, material);
		this.amountMax = amountMax;
		this.amountMin = amountMin;
		this.immovable = true;
	}

	@Override
	public boolean isSolidRender() {
		return false;
	}

	@Override
	public AABB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return AABB.getBoundingBoxFromPool(0, 0, 0, 0, 0, 0);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (entity instanceof EntityPlayer) {
			world.setBlockWithNotify(x, y, z, 0);

			int coinAmount = world.rand.nextInt(this.amountMax - this.amountMin) + this.amountMin;
			((IRandomOdditiesCoinAmount) entity).randomOddities$addCoinAmount(world.rand.nextInt(this.amountMax - this.amountMin) + this.amountMin);
			world.playSoundAtEntity(entity, entity,"randomoddities.coin_chime",0.65F,1.0F);

			for ( int coin = 0; coin < coinAmount; coin++) {
				int motionMod = world.rand.nextBoolean() ? 1 : -1;
				double degrees = Math.toRadians((double) (360/coinAmount) * coin);
				double radius = 1.25;
				world.spawnParticle(
					"coin",
					x + 0.5 + (Math.cos(degrees) * radius),
					y + 0.5,
					z + 0.5 + (Math.sin(degrees) * radius),
					world.rand.nextDouble() / 4 * motionMod,
					world.rand.nextDouble() / 4 * motionMod,
					world.rand.nextDouble() / 4 * motionMod
				);
			}
		}

        super.onEntityCollidedWithBlock(world, x, y, z, entity);
    }

}
