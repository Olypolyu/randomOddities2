package Olypolyu.randomoddities.items;

import Olypolyu.randomoddities.RandomOddities;
import Olypolyu.randomoddities.blocks.RandomOdditiesBlocks;
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
	public ItemPaintBrush(int id, int color) {
		super(id);
		this.setMaxDamage(64);
		this.maxStackSize = 1;
		this.bFull3D = true;
		this.colour = color;
	}

    private final int colour;

	public static final String[] colours = {
		"white", "orange", "magenta", "lightBlue", "yellow", "lime", "pink", "gray", "silver", "cyan", "purple",
		"blue", "brown", "green", "red", "black"
	};

	// int from hex. #41CD34 -> 4312372.
	public static final int[] colourValues = {
		15790320, 15435844, 12801229, 6719955, 14602026, 4312372, 14188952, 4408131, 9738647, 2651799, 8073150,
		2437522, 5320730, 3887386, 11743532, 1973019
	};

	public static final HashMap<Integer, IRandomOdditiesPainter> painterMap = new HashMap<>();

	static {

		// if you are a modder and reading this,
		// you can make your own mod's items work with the brush by just adding the results to the map.
		// it requires a block ID and a IRandomOdditiesPainter.

		// already painted non-complex
		Arrays.stream(new int[] {
			RandomOdditiesBlocks.paintedGlass.id,
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
		painterMap.put(Block.glass.id, new RandomOdditiesPainter(false, true, RandomOdditiesBlocks.paintedGlass.id));
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
