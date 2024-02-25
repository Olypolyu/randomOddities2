package Olypolyu.randomoddities.blocks;

import Olypolyu.randomoddities.tile.TileEntityBubbleColumn;
import net.minecraft.core.block.BlockTileEntity;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;

public class BlockBubbleGenerator extends BlockTileEntity {
    public BlockBubbleGenerator(String key, int i, Material material) {
        super(key, i, material);
    }

	@Override
	protected TileEntity getNewBlockEntity() {
		return new TileEntityBubbleColumn();
	}
}

