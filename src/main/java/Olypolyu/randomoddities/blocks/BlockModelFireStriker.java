package Olypolyu.randomoddities.blocks;

import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.core.block.Block;
import net.minecraft.core.util.helper.Side;

public class BlockModelFireStriker<T extends Block> extends BlockModelStandard<T> {
    public BlockModelFireStriker(T block) {
        super(block);
    }

    @Override
    public void renderBlockOnInventory(Tessellator tessellator, int metadata, float brightness, float alpha, Integer lightmapCoordinate) {
        super.renderBlockOnInventory(tessellator, 3, brightness, alpha, lightmapCoordinate);
    }

	@Override
	public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int data) {
		// block's facing :
		IconCoordinate fireStriker = TextureRegistry.getTexture("randomoddities:block/fire_striker");
		switch (data) {
			//north
			default:
				if (side == Side.NORTH)
					 return fireStriker;
				else return TextureRegistry.getTexture("minecraft:block/piston_bottom");

			//south
			case 3:
				if (side == Side.SOUTH)
					return fireStriker;
				else return TextureRegistry.getTexture("minecraft:block/piston_bottom");

			//west
			case 4:
				if (side == Side.WEST)
					return fireStriker;
				else return TextureRegistry.getTexture("minecraft:block/piston_bottom");

			//east
			case 5:
				if (side == Side.EAST)
					return fireStriker;
				else return TextureRegistry.getTexture("minecraft:block/piston_bottom");
		}
    }
}
