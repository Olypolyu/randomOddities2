package Olypolyu.randomoddities.gui;

import Olypolyu.randomoddities.interfaces.IRandomOdditiesCoinAmount;
import Olypolyu.randomoddities.util.DataVendingMachineEntry;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.player.inventory.slot.Slot;

public class SlotPurchase extends Slot {
	private final DataVendingMachineEntry entry;

	public SlotPurchase(InventoryPlayer inventoryPlayer, DataVendingMachineEntry entry, int id, int x, int y) {
		super(inventoryPlayer, id, x, y);
		this.entry = entry;
	}

	public ItemStack getStack() {
		if (this.entry == null) return null;
		return this.entry.getProduct();
	}

	@Override
	public void onPickupFromSlot(ItemStack itemstack) {
		((IRandomOdditiesCoinAmount)((InventoryPlayer)this.inventory).player).randomOddities$addCoinAmount(this.entry.getPrice() * -1);
		super.onPickupFromSlot(itemstack);
	}
}
