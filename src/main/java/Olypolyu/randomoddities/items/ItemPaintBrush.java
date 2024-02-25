package Olypolyu.randomoddities.items;

import Olypolyu.randomoddities.util.RandomOdditiesPainter;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.animal.EntitySheep;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

import Olypolyu.randomoddities.interfaces.IRandomOdditiesPainter;

import java.util.Arrays;
import java.util.HashMap;

public class ItemPaintBrush extends Item {
    private final int colour;
	public static final HashMap<Integer, IRandomOdditiesPainter> painterMap = new HashMap<>();

    public ItemPaintBrush(int id, int color) {
        super(id);
		this.setMaxDamage(64);
		this.maxStackSize = 1;
		this.bFull3D = true;
		this.colour = color;
	}

	static {

		// if you are a modder and reading this,
		// you can make your own mod's items work with the brush by just adding the results to the map.
		// it requires a block ID and a IRandomOdditiesPainter.

		// already painted non-complex
		Arrays.stream(new int[] {
			Block.fencePlanksOakPainted.id,
			Block.planksOakPainted.id,
			Block.lampActive.id,
			Block.lampIdle.id,
			Block.wool.id,
		}).forEach(id -> painterMap.put(id, new RandomOdditiesPainter(false, false, id)));

		// already painted complex
		Arrays.stream(new int[] {
			Block.fencegatePlanksOakPainted.id,
			Block.stairsPlanksOakPainted.id,
			Block.chestPlanksOakPainted.id,
			Block.slabPlanksOakPainted.id,
		}).forEach(id -> painterMap.put(id, new RandomOdditiesPainter(true,false, id)));

		painterMap.put(Block.planksOak.id, new RandomOdditiesPainter(false, true, Block.planksOakPainted.id));
		painterMap.put(Block.fencePlanksOak.id, new RandomOdditiesPainter(false,true, Block.fencePlanksOakPainted.id));
		painterMap.put(Block.chestPlanksOak.id, new RandomOdditiesPainter(true,true, Block.chestPlanksOakPainted.id));
		painterMap.put(Block.fencegatePlanksOak.id, new RandomOdditiesPainter(true,true, Block.fencegatePlanksOakPainted.id));
		painterMap.put(Block.stairsPlanksOak.id, new RandomOdditiesPainter(true,true, Block.stairsPlanksOakPainted.id));
		painterMap.put(Block.slabPlanksOak.id, new RandomOdditiesPainter(true,true, Block.slabPlanksOakPainted.id));

		/*

		all of this is from random oddities 1. ignore.

		// base blocks
        switch (block) {

			 case 190: // Glass
                if (this.color == 0) return false; // glass is already white.
                //paint(RandomOddities.paintedGlass.blockID, false, world, i, j, k, itemstack, entityplayer);
                return true;
            }

        // already painted blocks.
        switch (block) {

            case 713: // painted glass
                // set to normal glass instead of white painted glass.
                if(this.color == 0) {
                    world.setBlockWithNotify(i, j, k, 190);
                    return true;
                }

                // turns out it only updates properly if you set block and metadata.
                world.setBlockAndMetadataWithNotify(i, j, k, RandomOdditiesBlocks.paintedGlass.blockID, this.color);
                itemstack.damageItem(1, entityplayer);
                return true;
                }
		 */
	}

    public boolean useItemOnEntity(ItemStack itemstack, EntityLiving entityliving, EntityPlayer entityplayer) {
        if (entityliving instanceof EntitySheep) {
            EntitySheep entitysheep = (EntitySheep) entityliving;

            if (entitysheep.getFleeceColor() != this.colour) {
                itemstack.damageItem(1, entityplayer);
                entitysheep.setFleeceColor(this.colour);

                return true;
			}

            }
        return false;
    }

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
        int block = world.getBlockId(blockX, blockY, blockZ);
		IRandomOdditiesPainter painter = painterMap.get(block);

		if (painter != null) {
			return painter.apply(this.colour, world, blockX, blockY, blockZ, itemstack, entityplayer);
		}

		return false;
	}
}
