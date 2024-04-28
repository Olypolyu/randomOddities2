package Olypolyu.randomoddities.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.World;

import java.util.Random;

public class BlockTrampoline extends Block {

	private final float launchX;
	private final float launchZ;
	private float launchY;
	private final Random random = new Random();

	public BlockTrampoline(String key, int id, Material material, float launchX, float launchY, float launchZ) {
		super(key, id, material);
		this.launchX = launchX;
		this.launchY = launchY;
		this.launchZ = launchZ;
		this.setBlockBounds(0F, 0F, 0F, 1.0F, 0.5F, 1.0F);
	}

	@Override
	public boolean isSolidRender() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return canBlockStay(world, x, y, z);
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
        return world.isBlockNormalCube(x, y - 1, z);
    }

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int blockId) {
		if (!canBlockStay(world, x, y, z)) {
			world.setBlock(x,y,z, 0);
			world.dropItem(x, y, z, new ItemStack(RandomOdditiesBlocks.trampoline));
		}

		super.onNeighborBlockChange(world, x, y, z, blockId);
	}

	public void jump(Entity entity) {
		World world = entity.world;

		entity.xd = compute(entity.xd, launchX);
		entity.yd = compute(entity.yd, launchY);
		entity.zd = compute(entity.zd, launchZ);
		world.playSoundAtEntity(
			null, entity,("randomoddities.trampoline_bounce"),
			1.0F + (0.16F * compute(entity.yd, launchY)),
			9.0F + random.nextFloat()
		);

		float width = 1.0f;
		for (int i = 0; i < 20; ++i) {
			double dx = random.nextGaussian() * 0.02;
			double dy = random.nextGaussian() * 0.02;
			double dz = random.nextGaussian() * 0.02;
			world.spawnParticle(
				"snowshovel",
				entity.x + (double) (random.nextFloat() * width * 2.0F) - (double) width,
				entity.y - entity.bbHeight + (double) (random.nextFloat() * width),
				entity.z + (double) (random.nextFloat() * width * 2.0F) - (double) width,
				dx, dy, dz
			);
		}
	}

	protected float compute(double motion, float launch){
		if (launch != 0 && motion < launch * 0.75F) {
			return (float) (Math.abs(motion) * 0.50F) + launch;
		}

		return (float) motion;
	}

	@Override
	public AABB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return AABB.getBoundingBoxFromPool(minX, y, minZ, maxX, y, maxZ);
	}

	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
		this.jump(entity);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (entity.yd < 0.0) {
			entity.yd *= 0.6;
			entity.fallDistance = 0.0F;
		}

		if (entity.y > (double) y && entity instanceof EntityLiving) {
			this.jump(entity);
		}
	}

}
