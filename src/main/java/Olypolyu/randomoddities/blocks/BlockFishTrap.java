package Olypolyu.randomoddities.blocks;

import Olypolyu.randomoddities.RandomOdditiesAssets;
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

	public static final ArrayList<WeightedRandomLootObject> FishingLoot = new ArrayList<>();
	static {
		FishingLoot.add(new WeightedRandomLootObject(new ItemStack(Item.foodFishRaw), 4, 2));
		FishingLoot.add(new WeightedRandomLootObject(new ItemStack(Item.armorBootsLeather), 1));
		FishingLoot.add(new WeightedRandomLootObject(new ItemStack(Block.tnt), 1, 3));
		FishingLoot.add(new WeightedRandomLootObject(new ItemStack(RandomOdditiesItems.paintBrushes[Arrays.asList(ItemPaintBrush.colours).indexOf("brown")]), 1));
		FishingLoot.add(new WeightedRandomLootObject(new ItemStack(Item.string), 3, 10));
		FishingLoot.add(new WeightedRandomLootObject(new ItemStack(Item.dye, 1, 3), 1, 2));
		FishingLoot.add(new WeightedRandomLootObject(new ItemStack(Block.planksOakPainted, 1, 15), 1, 16));
	}

    public BlockFishTrap(String key, int i, Material material) {
        super(key, i, material);
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
	public int getBlockTextureFromSideAndMetadata(Side side, int data) {
        if (data == 1) return RandomOdditiesAssets.fishTrapEngagedTex;
        if (data == 2) return RandomOdditiesAssets.fishTrapFullTex;
        return RandomOdditiesAssets.fishTrapTex;
    }

    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer) {
        ItemStack currentItem = entityplayer.getCurrentEquippedItem();

        if ( currentItem != null && world.getBlockMetadata(i, j, k) == 0) {
            if (currentItem.getItem() == Item.string) {
                entityplayer.inventory.getCurrentItem().consumeItem(entityplayer);
                world.setBlockMetadataWithNotify(i, j, k, 1);
                this.getBlockTextureFromSideAndMetadata(Side.NORTH,0);
                return true;
                }
            }

        if ( world.getBlockMetadata(i, j, k) == 2) {
                world.setBlockMetadataWithNotify(i, j, k, 0);
				ItemStack loot = FishingLoot.get(world.rand.nextInt(FishingLoot.size())).getItemStack();
                world.dropItem(i, j + 1, k, loot);
                return true;
            }

        return false;
    }

}
