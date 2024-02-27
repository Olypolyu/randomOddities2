package Olypolyu.randomoddities.blocks;

import Olypolyu.randomoddities.RandomOddities;
import net.minecraft.client.sound.block.BlockSound;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockStone;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import turniplabs.halplibe.helper.BlockBuilder;
import useless.dragonfly.helper.ModelHelper;
import useless.dragonfly.model.block.BlockModelDragonFly;

import static Olypolyu.randomoddities.RandomOddities.MOD_ID;

public class RandomOdditiesBlocks {
	private static int randomOdditiesIds = 1400;

	// decoration blocks
	public static Block flintBlock = new BlockBuilder(MOD_ID)
		.setBlockSound(new BlockSound("step.stone", "step.stone", 1.0f, 1.0f))
		.setHardness(0.5f)
		.setResistance(0.8f)
		.setTextures("flint_block.png")
		.build(new BlockStone("flint_block", randomOdditiesIds++));

	public static Block bubbleColumn = new BlockBuilder(MOD_ID)
		.setBlockSound(new BlockSound("step.stone", "step.stone", 1.0f, 1.0f))
		.setHardness(0.5f)
		.setResistance(0.8f)
		.setSideTextures("bubble_generator/sides.png")
		.setTopTexture("bubble_generator/top.png")
		.setBottomTexture("trampoline/bottom.png")
		.build(new BlockBubbleGenerator("bubble_generator", randomOdditiesIds++, Material.metal));

	public static Block paintedGlass = new BlockBuilder(MOD_ID)
		.setBlockSound(new BlockSound("step.stone", "step.stone", 1.0f, 1.0f))
		.setHardness(0.3f)
		.setResistance(0.1f)
		.build(new BlockPaintedGlass("painted_glass", randomOdditiesIds++, Material.glass, true).withTags(BlockTags.MINEABLE_BY_PICKAXE));

	public static Block trampoline = new BlockBuilder(MOD_ID)
		.setBlockModel(new BlockModelDragonFly(ModelHelper.getOrCreateBlockModel(MOD_ID, "trampoline.json")))
		.setBlockSound(new BlockSound("step.stone", "step.stone", 1.0f, 1.0f))
		.setHardness(1.3F)
		.setResistance(1.0F)
		.build(new BlockTrampoline("trampoline", randomOdditiesIds++, Material.metal, 0,1.32F,0).withTags(BlockTags.MINEABLE_BY_PICKAXE));

	public static Block pillow  = new BlockBuilder(MOD_ID)
		.setTopBottomTexture("pillow/top.png")
		.setSideTextures("pillow/sides.png")
		.setBlockSound(new BlockSound("step.stone", "step.stone", 1.0f, 1.0f))
		.setHardness(1.0F)
		.setResistance(1.0F)
		.build(new BlockPillow("pillow", randomOdditiesIds++, Material.cloth).withTags(BlockTags.MINEABLE_BY_SHEARS));

	public static Block coinBlock = new BlockBuilder(MOD_ID)
		.setBlockModel(new BlockModelDragonFly(ModelHelper.getOrCreateBlockModel(MOD_ID, "coin_block.json")))
		.setHardness(9999.0F)
		.setResistance(9999.0F)
		.build(new BlockCoin("coin_Block", randomOdditiesIds++, Material.metal));

	public static Block vendingMachine = new BlockBuilder(MOD_ID)
		.setHardness(1.3F)
		.setResistance(2.0F)
		.build(new BlockVendingMachine("vending_machine", randomOdditiesIds++, Material.metal));

	// TODO: add rotation to coins, add animation to trampoline.

	public void initializeBlocks() {
		RandomOddities.info("RandomOddities has loaded blocks");
	}
}


