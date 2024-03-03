package Olypolyu.randomoddities.gui.widgets;

import Olypolyu.randomoddities.interfaces.IRandomOdditiesCoinAmount;
import Olypolyu.randomoddities.tile.TileEntityVendingMachine;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.player.inventory.slot.Slot;

public class SlotPurchase extends Slot {
	private final TileEntityVendingMachine tile;

	public SlotPurchase(InventoryPlayer inventoryPlayer, TileEntityVendingMachine tile, int id, int x, int y) {
		super(inventoryPlayer, id, x, y);
		this.tile = tile;
	}

	public ItemStack getStack() {
		if (this.tile.getProducts()[this.tile.getSelected()] == null) return null;
		return this.tile.getProducts()[this.tile.getSelected()].getProduct();
	}

	@Override
	public void onPickupFromSlot(ItemStack itemstack) {
		if (!((IRandomOdditiesCoinAmount)((InventoryPlayer)this.inventory).player).randomOddities$subtractCoinAmount(this.tile.getProducts()[this.tile.getSelected()].getPrice())) {
			return;
		}
		super.onPickupFromSlot(itemstack);
	}

	@Override
	public boolean canPutStackInSlot(ItemStack itemstack) {
		return false;
	}
}
