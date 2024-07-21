package Olypolyu.randomoddities.blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockRotatable;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityTNT;
import net.minecraft.core.entity.monster.EntityCreeper;
import net.minecraft.core.sound.SoundCategory;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.World;

import java.util.List;

public class BlockFireStriker extends BlockRotatable {
    public BlockFireStriker(String key, int i, Material material) {
        super(key, i, material);
    }
    private boolean lastState = false;
    public void onNeighborBlockChange(World world, int i, int j, int k, int l) {
        boolean power = world.isBlockGettingPowered(i, j, k) || world.isBlockIndirectlyGettingPowered(i, j, k);
        if (lastState != power) {
            lastState = power;
            this.onPowered(world, i, j, k, power);
        }
    }

    public void onPowered(World world, int i, int j, int k, boolean powered) {
        int side = world.getBlockMetadata(i, j, k);

        switch (side) {
            // north
			default:
                k--;
                break;

            // south
            case 3:
                k++;
                break;

            // west
            case 4:
                i--;
                break;

			// east
			case 5:
				i++;
				break;
		}

        if (world.getBlockId(i, j, k) == Block.brazierActive.id && !powered) {
            world.setBlockWithNotify(i, j, k, Block.brazierInactive.id);
            visualEffects(world, i, j, k);
            return;
        }
        if (!powered) return;

        if (world.getBlockId(i, j, k) == Block.tnt.id) {
            world.setBlockWithNotify(i, j, k, 0);
            EntityTNT tnt = new EntityTNT(world, i + 0.5F, j + 0.5F, k + 0.5F);
            world.entityJoinedWorld(tnt);
			world.playSoundAtEntity(null, tnt, "random.fuse", 1.0F, 1.0F);
            return;
        } else if (world.getBlockId(i, j, k) == Block.brazierInactive.id) {
            world.setBlockWithNotify(i, j, k, Block.brazierActive.id);
            visualEffects(world, i, j, k);
            return;
        }

        List<Entity> entitiesInBoundingBox = world.getEntitiesWithinAABB(Entity.class, AABB.getBoundingBox(i, j, k, i + 1, j + 1, k + 1));
        for (Entity entity : entitiesInBoundingBox) {
            if (entity instanceof EntityCreeper) {
                world.createExplosion(entity, entity.x, entity.y, entity.z, 3.0F);
                entity.removed = true;
                return;
            }
        }

        if (world.getBlockId(i, j, k) == 0) {
      		world.setBlockWithNotify(i, j, k, Block.fire.id);
  		}
		visualEffects(world, i, j, k);
	}

    private void visualEffects( World world, int i, int j, int k) {
		world.playSoundEffect(null, SoundCategory.WORLD_SOUNDS, i, j, k, "fire.ignite", 1.0F, 1.0F);

        // summon particles
        for ( int x = 0; x < 5; x++) {
			int motionMod = world.rand.nextBoolean() ? 1 : -1;
            world.spawnParticle("smoke", i + 0.5, j + 0.5, k + 0.5, world.rand.nextDouble() / 4 * motionMod, world.rand.nextDouble() / 4 * motionMod, world.rand.nextDouble() / 4 * motionMod, 0, 0);
        }
    }
}
