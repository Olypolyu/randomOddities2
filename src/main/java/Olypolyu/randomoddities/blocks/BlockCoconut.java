package Olypolyu.randomoddities.blocks;

import net.minecraft.core.HitResult;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.util.phys.Vec3d;
import net.minecraft.core.world.World;

import java.util.List;
import java.util.Random;

public class BlockCoconut extends Block {

	static final int range = 8;
	static final int inverseProbability = 100;


	public BlockCoconut(String key, int id, Material material) {
		super(key, id, material);
		this.setTicking(true);
	}

	@Override
	public int tickRate() {
		return 1;
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		updateTick(world, x, y, z, world.rand);
	}

	@Override
	public void onBlockPlaced(World world, int x, int y, int z, Side side, EntityLiving entity, double sideHeight) {
		super.onBlockPlaced(world, x, y, z, side, entity, sideHeight);
		world.setBlockMetadataWithNotify(x, y, z, 1);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		super.updateTick(world, x, y, z, rand);

		if (world.getBlockMetadata(x, y, z) == 0) {
			world.scheduleBlockUpdate(x, y, z, this.id, tickRate());

			Vec3d oldPosition = Vec3d.createVector(x, y - 1, z);
			Vec3d newPosition = Vec3d.createVector(x, y - 1 - range, z);
			HitResult hit = world.checkBlockCollisionBetweenPoints(oldPosition, newPosition, true, false);

			AABB box = new AABB(x - 0.5, y - range, z - 0.5, x + 0.5, y + 0.5, z + 0.5);
			List<Entity> entitiesUnder = world.getEntitiesWithinAABB(EntityPlayer.class, box);

			int effectiveHitY = hit != null ? hit.y : 0;
			if (!entitiesUnder.isEmpty()) {
				if (((hit == null) || (entitiesUnder.get(0).y > effectiveHitY)) && rand.nextInt(inverseProbability) == 0) {
					world.setBlock(x, y, z, Block.sand.id);
				}
			}
		}
	}
}
