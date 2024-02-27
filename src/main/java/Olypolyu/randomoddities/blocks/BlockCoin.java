package Olypolyu.randomoddities.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.World;

public class BlockCoin extends Block {

	public BlockCoin(String key, int id, Material material) {
		super(key, id, material);
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
		}
	}
}
