package Olypolyu.randomoddities.blocks;

import Olypolyu.randomoddities.particle.EntityPaintFX;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockGlass;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Color;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

import static Olypolyu.randomoddities.items.ItemPaintBrush.colours;

public class BlockModelPaintedGlass<T extends Block> extends BlockModelStandard<T> {
    protected IconCoordinate[] textures = new IconCoordinate[colours.length];

    public BlockModelPaintedGlass(T block) {
        super(block);
        for (int colour = 0; colour < colours.length; colour++) {
            textures[colour] = TextureRegistry.getTexture("randomoddities:block/glass_" + colours[colour]);
        }
    }

	@Override
	public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int data) {
	    return textures[data % colours.length];
    }
}
