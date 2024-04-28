package Olypolyu.randomoddities.util;

import Olypolyu.randomoddities.interfaces.IRandomOdditiesPainter;
import Olypolyu.randomoddities.particle.EntityPaintFX;
import static Olypolyu.randomoddities.RandomOdditiesAssets.particleSizes;
import static Olypolyu.randomoddities.items.ItemPaintBrush.colourValues;

import net.minecraft.client.Minecraft;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Color;
import net.minecraft.core.world.World;

public class RandomOdditiesPainter implements IRandomOdditiesPainter {
	private final boolean isComplex;
	private final int result;
	private final boolean isBase;

	public RandomOdditiesPainter(Boolean isComplex, boolean isBase, int result) {
		this.isComplex = isComplex;
		this.result = result;
		this.isBase = isBase;
	}

	public boolean apply(int colour, World world, int x, int y, int z, ItemStack itemstack, EntityPlayer entityplayer) {
		int blockData = world.getBlockMetadata(x, y, z);

		if (isComplex && !isBase && (world.getBlockMetadata(x, y, z) >> 4) == colour) return false;
		if (!isComplex && !isBase  && world.getBlockMetadata(x, y, z) == colour) return false;

		// using raw here so chests don't drop their contents on the floor, might be a bad idea, dunno.
		world.setBlockRawWithNotify(x, y, z, this.result);

		if(isComplex) {
			world.setBlockMetadataWithNotify(x, y, z,(blockData % 16) + (colour * 16));
		} else {
			world.setBlockMetadataWithNotify(x, y, z, colour);
			itemstack.damageItem(1, entityplayer);
		}

		Minecraft mc = Minecraft.getMinecraft(Minecraft.class);
		Block block = Block.getBlock(result);

		Color particleColour = new Color();
		particleColour.setARGB(colourValues[colour]);

		for (int particle = 0; particle < 24; particle++) {
			mc.effectRenderer.addEffect(
				new EntityPaintFX(world.rand.nextInt(particleSizes.length),
					particleColour.getRed() / 255F,
					particleColour.getBlue() / 255F,
					particleColour.getGreen() / 255F,
					world,
					x + block.maxX + block.minX - world.rand.nextDouble(),
					y + block.maxY + block.minY - world.rand.nextDouble(),
					z + block.maxZ + block.minZ - world.rand.nextDouble(),
					0,
					0,
					0)
			);
		}

		world.playSoundAtEntity(entityplayer, entityplayer, "mob.slime", 0.3F, ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);

		return true;
	}
}
