package Olypolyu.randomoddities.blocks;

import Olypolyu.randomoddities.interfaces.IRandomOdditiesCoinAmount;
import Olypolyu.randomoddities.interfaces.IRandomOdditiesGUIs;
import Olypolyu.randomoddities.tile.TileEntityVendingMachine;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockTileEntity;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

public class BlockVendingMachine extends BlockTileEntity {
	public BlockVendingMachine(String key, int id, Material material) {
		super(key, id, material);
	}

	@Override
	protected TileEntity getNewBlockEntity() {
		return new TileEntityVendingMachine();
	}

	@Override
	public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
		if (!world.isClientSide) {
			TileEntityVendingMachine tile = (TileEntityVendingMachine) world.getBlockTileEntity(x, y, z);
			if (tile != null) {
				((IRandomOdditiesGUIs)player).randomOddities$DisplayGUIVendingMachine(tile);
			}
		}
		return true;
	}

}
