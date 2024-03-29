package Olypolyu.randomoddities.tile;

import Olypolyu.randomoddities.RandomOdditiesCore;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.util.helper.Side;

public class TileEntityPipeBase extends TileEntity {

	public Integer suctionForce = 0;
	public TileEntityTransporter transporter = null;

	public void updateTargets() {
		int suctionForce = 0;
		TileEntityTransporter transporter = null;

		for (Side side : Side.values()) {
			if (side == Side.NONE) continue;
			int offsetX = side.getOffsetX() + this.x;
			int offsetY = side.getOffsetY() + this.y;
			int offsetZ = side.getOffsetZ() + this.z;

			RandomOdditiesCore.info(worldObj.getBlock(offsetX, offsetY, offsetZ));
			TileEntity tile = worldObj.getBlockTileEntity(offsetX, offsetY, offsetZ);
			if (tile instanceof TileEntityPipeBase) {
				TileEntityPipeBase pipeTile = (TileEntityPipeBase) tile;
				if (pipeTile.suctionForce > suctionForce) {
					pipeTile.updateTargets();
					suctionForce = pipeTile.suctionForce;
					transporter = pipeTile.transporter;
				}
			}

			if (worldObj.getBlock(offsetX, offsetY, offsetZ) == Block.blockDiamond) {
				suctionForce = 7;
				RandomOdditiesCore.info(suctionForce);
			}
		}

		this.suctionForce = suctionForce - 1;
		this.transporter = transporter;
	}

}
