package Olypolyu.randomoddities.blocks;

import Olypolyu.randomoddities.interfaces.IRandomOdditiesGUIs;
import Olypolyu.randomoddities.tile.TileEntityVendingMachine;
import net.minecraft.core.block.BlockTileEntityRotatable;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.world.World;
import net.minecraft.core.util.helper.Side;

public class BlockVendingMachine extends BlockTileEntityRotatable {
	public BlockVendingMachine(String key, int id, Material material) {
		super(key, id, material);
	}

	@Override
	protected TileEntity getNewBlockEntity() {
		return new TileEntityVendingMachine();
	}

	@Override
	public boolean onBlockRightClicked(World world, int x, int y, int z, EntityPlayer player, Side side, double xp, double yp) {
		if (!world.isClientSide) {
			TileEntityVendingMachine tile = (TileEntityVendingMachine) world.getBlockTileEntity(x, y, z);
			if (tile != null) {
				((IRandomOdditiesGUIs)player).randomOddities$DisplayGUIVendingMachine(tile);
			}
		}
		return true;
	}

}
