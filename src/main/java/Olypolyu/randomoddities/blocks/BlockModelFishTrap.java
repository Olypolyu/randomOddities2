package Olypolyu.randomoddities.blocks;

import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.util.helper.Side;

public class BlockModelFishTrap<T extends Block> extends BlockModelStandard<T> {
    protected IconCoordinate[] fish_trap = new IconCoordinate[] {
        TextureRegistry.getTexture("randomoddities:block/fish_trap_empty"),
        TextureRegistry.getTexture("randomoddities:block/fish_trap_engaged"),
        TextureRegistry.getTexture("randomoddities:block/fish_trap_full")
    };

    public BlockModelFishTrap(T block) {
        super(block);
    }

	@Override
	public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int data) {
        if (data == 1) return fish_trap[1];
        if (data == 2) return fish_trap[2];
        return fish_trap[0];
    }
}
