package Olypolyu.randomoddities.blocks;

import Olypolyu.randomoddities.items.ItemPaintBrush;
import Olypolyu.randomoddities.items.RandomOdditiesItems;
import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BlockFishTrap extends Block {

    protected int radius = 1;

	public static ArrayList<WeightedRandomLootObject> fishingLoot = new ArrayList<>();

    public BlockFishTrap(String key, int i, Material material) {
        super(key, i, material);

		fishingLoot.add(new WeightedRandomLootObject(new ItemStack(Item.foodFishRaw), 4, 2));
		fishingLoot.add(new WeightedRandomLootObject(new ItemStack(Item.armorBootsLeather), 1));
		fishingLoot.add(new WeightedRandomLootObject(new ItemStack(RandomOdditiesItems.paintBrushes[Arrays.asList(ItemPaintBrush.colours).indexOf("brown")]), 1));
		fishingLoot.add(new WeightedRandomLootObject(new ItemStack(Item.string), 3, 10));
		fishingLoot.add(new WeightedRandomLootObject(new ItemStack(Item.dye, 1, 3), 1, 2));
        this.setTicking(true);
    }

    private boolean isInWater(World world, int i, int j, int k){
    int x;
    int z;
    int water = 0;

        for ( z = k - radius; z <= k + radius; z++) {
            for ( x = i - radius; x <= i + radius; x++  ) {
                if (world.getBlockMaterial(x, j, z) == Material.water)
                    water = water + 1;
                }
            }

        return water >= 6;
    }

    public void checkForWater(World world, int i, int j, int k) {
        if (!isInWater(world, i, j, k)) {
            world.setBlockWithNotify(i, j, k, 0);
            world.dropItem(i, j, k, new ItemStack(this));
            }
        }

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return isInWater(world, x, y, z);
	}

	@Override
	public void updateTick(World world, int i, int j, int k, Random random) {
		if (isInWater(world, i, j, k)) {
			if (random.nextInt(100) == 0 && world.getBlockMetadata(i, j, k) == 1) {
				world.setBlockMetadataWithNotify(i, j, k, 2);
			}
		} else checkForWater(world, i, j, k);
	}

	@Override
    public void onNeighborBlockChange(World world, int i, int j, int k, int l) {
        checkForWater(world, i, j, k);
    }

    @Override
    public boolean onBlockRightClicked(World world, int i, int j, int k, EntityPlayer entityplayer, Side side, double xp, double yp) {
        ItemStack currentItem = entityplayer.getCurrentEquippedItem();
        if (currentItem != null && world.getBlockMetadata(i, j, k) == 0) {
            if (currentItem.getItem() == Item.string) {
                entityplayer.inventory.getCurrentItem().consumeItem(entityplayer);
                world.setBlockMetadataWithNotify(i, j, k, 1);
                //this.getBlockTextureFromSideAndMetadata(Side.NORTH,0);
                return true;
            }
        }

        if (world.getBlockMetadata(i, j, k) == 2) {
            world.setBlockMetadataWithNotify(i, j, k, 0);
			ItemStack loot = fishingLoot.get(world.rand.nextInt(fishingLoot.size())).getItemStack();
            world.dropItem(i, j + 1, k, loot);
            return true;
        }
        return false;
    }

}
