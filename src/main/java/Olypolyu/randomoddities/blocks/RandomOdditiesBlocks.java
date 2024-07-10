package Olypolyu.randomoddities.blocks;

import Olypolyu.randomoddities.RandomOdditiesCore;
import Olypolyu.randomoddities.items.ItemPaintBrush;
import Olypolyu.randomoddities.util.RandomOdditiesPainter;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockStone;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.sound.BlockSound;
import turniplabs.halplibe.helper.BlockBuilder;
import org.useless.dragonfly.helper.ModelHelper;
import org.useless.dragonfly.model.block.DFBlockModelBuilder;

import static Olypolyu.randomoddities.RandomOdditiesCore.MOD_ID;

public class RandomOdditiesBlocks {
	private static int randomOdditiesIds = 1400;

	public static Block flintBlock;
	public static Block bubbleColumn;
	public static Block paintedGlass;
	public static Block trampoline;
	public static Block pillow ;
	public static Block coinBlock;
	public static Block vendingMachine;
	public static Block fireStriker;
	public static Block fishTrap;
	public static Block coconut;

	public void initializeBlocks() {
		RandomOdditiesCore.info("RandomOddities has loaded blocks");

    	// decoration blocks
    	flintBlock = new BlockBuilder(MOD_ID)
    		.setBlockSound(new BlockSound("step.stone", "step.stone", 1.0f, 1.0f))
    		.setHardness(0.5f)
    		.setResistance(0.8f)
    		.setTextures("randomoddities:block/flint_block")
    		.build(new Block("flint_block", randomOdditiesIds++, Material.stone).withTags(BlockTags.MINEABLE_BY_PICKAXE));

    	bubbleColumn = new BlockBuilder(MOD_ID)
    		.setBlockSound(new BlockSound("step.stone", "step.stone", 1.0f, 1.0f))
    		.setHardness(0.5f)
    		.setResistance(0.8f)
    	    .setBlockModel(block -> new BlockModelStandard(block)
    	        .withTextures("randomoddities:block/bubble_generator_top", "randomoddities:block/trampoline_bottom", "randomoddities:block/bubble_generator_sides")
            )
    		.build(new BlockBubbleGenerator("bubble_generator", randomOdditiesIds++, Material.metal).withTags(BlockTags.MINEABLE_BY_PICKAXE));

    	paintedGlass = new BlockBuilder(MOD_ID)
    		.setBlockSound(new BlockSound("step.stone", "step.stone", 1.0f, 1.0f))
    		.setHardness(0.3f)
    		.setResistance(0.1f)
    		.setBlockModel(block -> new BlockModelPaintedGlass(block))
    		.build(new BlockPaintedGlass("painted_glass", randomOdditiesIds++, Material.glass).withTags(BlockTags.MINEABLE_BY_PICKAXE));
		ItemPaintBrush.painterMap.put(RandomOdditiesBlocks.paintedGlass.id, new RandomOdditiesPainter(false, false, RandomOdditiesBlocks.paintedGlass.id));
		ItemPaintBrush.painterMap.put(Block.glass.id, new RandomOdditiesPainter(false, true, RandomOdditiesBlocks.paintedGlass.id));


    	trampoline = new BlockBuilder(MOD_ID)
    	    .setBlockModel(block -> new DFBlockModelBuilder(MOD_ID)
    	        .setBlockModel("trampoline.json")
    	        .build(block))
    		.setBlockSound(new BlockSound("step.stone", "step.stone", 1.0f, 1.0f))
    		.setHardness(1.3F)
    		.setResistance(1.0F)
    		.build(new BlockTrampoline("trampoline", randomOdditiesIds++, Material.metal, 0,1.32F,0).withTags(BlockTags.MINEABLE_BY_PICKAXE));

    	pillow  = new BlockBuilder(MOD_ID)
    	    .setBlockModel(block -> new BlockModelStandard(block)
    	        .withTextures("randomoddities:block/pillow_top", "randomoddities:block/pillow_sides")
       		)
    		.setBlockSound(new BlockSound("step.stone", "step.stone", 1.0f, 1.0f))
    		.setHardness(1.0F)
    		.setResistance(1.0F)
    		.build(new BlockPillow("pillow", randomOdditiesIds++, Material.cloth).withTags(BlockTags.MINEABLE_BY_SHEARS));

    	coinBlock = new BlockBuilder(MOD_ID)
    	    .setBlockModel(block -> new DFBlockModelBuilder(MOD_ID)
    	        .setBlockModel("coin_block.json")
    	        .build(block)
	        )
    		.setHardness(9999.0F)
    		.setResistance(9999.0F)
    		.build(new BlockCoin("coin_Block", randomOdditiesIds++, Material.metal, 15, 5));

    	vendingMachine = new BlockBuilder(MOD_ID)
    	    .setBlockModel(block -> new BlockModelStandard(block)
    	        .withTextures(
    	            "randomoddities:block/vending_machine_top", "randomoddities:block/vending_machine_bottom", "randomoddities:block/vending_machine_front",
    	            "randomoddities:block/vending_machine_sides", "randomoddities:block/vending_machine_sides", "randomoddities:block/vending_machine_sides"
                )
            )
    		.setHardness(1.3F)
    		.setResistance(2.0F)
    		.build(new BlockVendingMachine("vending_machine", randomOdditiesIds++, Material.metal).withTags(BlockTags.MINEABLE_BY_PICKAXE, BlockTags.NOT_IN_CREATIVE_MENU));

    	fireStriker = new BlockBuilder(MOD_ID)
    		.setBlockSound(new BlockSound("step.stone", "step.stone", 1.0f, 1.0f))
    		.setHardness(1.0F)
    		.setResistance(1.0F)
    		.setBlockModel(block -> new BlockModelFireStriker(block))
    		.build(new BlockFireStriker("fire_striker", randomOdditiesIds++, Material.stone).withTags(BlockTags.MINEABLE_BY_PICKAXE));

    	fishTrap = new BlockBuilder(MOD_ID)
    		.setBlockSound(new BlockSound("step.stone", "step.stone", 1.0f, 1.0f))
    		.setHardness(1.0F)
    		.setResistance(1.0F)
    		.setBlockModel(block -> new BlockModelFishTrap(block))
    		.build(new BlockFishTrap("fish_trap", randomOdditiesIds++, Material.plant).withTags(BlockTags.MINEABLE_BY_PICKAXE));

    	// TODO: add rotation to coins, add animation to trampoline.
    	coconut = new BlockBuilder(MOD_ID)
    		.setBlockSound(new BlockSound("step.stone", "step.stone", 1.0f, 1.0f))
    		.setHardness(1.0F)
    		.setResistance(1.0F)
    		.build(new BlockCoconut("coconut", randomOdditiesIds++, Material.plant).withTags(BlockTags.MINEABLE_BY_PICKAXE));
	}
}


