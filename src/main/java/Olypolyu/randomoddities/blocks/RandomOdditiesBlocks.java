package Olypolyu.randomoddities.blocks;

import Olypolyu.randomoddities.RandomOddities;
import net.minecraft.client.sound.block.BlockSound;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockStone;
import turniplabs.halplibe.helper.BlockBuilder;

import static Olypolyu.randomoddities.RandomOddities.MOD_ID;

public class RandomOdditiesBlocks {
	private static int randomOdditiesIds = 1400;

	// decoration blocks
	public static Block flintBlock = new BlockBuilder(MOD_ID)
		.setBlockSound(new BlockSound("step.stone", "step.stone", 1.0f, 1.0f))
		.setHardness(0.5f)
		.setResistance(0.8f)
		.setTextures("FlintBlock.png")
		.build(new BlockStone("flintBlock", randomOdditiesIds++));

	public void initializeBlocks() {
		//RandomOddities.LOGGER.info("RandomOddities has loaded blocks");
	}
}


