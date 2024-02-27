package Olypolyu.randomoddities.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.world.World;

public class BlockPillow extends Block {
	public BlockPillow(String key, int id, Material material) {
		super(key, id, material);
		this.setBlockBounds(0F, 0F, 0F, 1.0F, 0.5F, 1.0F);
	}

	@Override
	public boolean isSolidRender() {
		return false;
	}

	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
		this.onEntityCollidedWithBlock(world, x, y, z, entity);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (entity.yd < 0.0) {
			entity.yd *= 0.50F;
			entity.fallDistance = 0.0F;
		}
	}
}
