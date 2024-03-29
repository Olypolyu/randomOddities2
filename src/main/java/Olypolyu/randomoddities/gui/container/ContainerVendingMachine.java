package Olypolyu.randomoddities.gui.container;

import Olypolyu.randomoddities.gui.widgets.SlotPurchase;
import Olypolyu.randomoddities.tile.TileEntityVendingMachine;
import net.minecraft.core.InventoryAction;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.player.inventory.Container;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.player.inventory.slot.Slot;

import java.util.List;

public class ContainerVendingMachine extends Container {

    private final TileEntityVendingMachine tile;

    public ContainerVendingMachine(InventoryPlayer inventoryPlayer, TileEntityVendingMachine tile){
		for(int crosshairRow = 0; crosshairRow < 9; crosshairRow++) {
			addSlot(new Slot(inventoryPlayer, crosshairRow, 8 + crosshairRow * 18, 170));
		}
        this.tile = tile;
		addSlot(new SlotPurchase(inventoryPlayer,tile,27 , 123, 22));
		for(int row = 0; row < 3; row++) {
			for(int column = 0; column < 9; column++) {
				addSlot(new Slot(inventoryPlayer,  9 + column + (row * 9), 8 + column * 18, 112 + row * 18));
			}
		}
    }


	@Override
	public List<Integer> getMoveSlots(InventoryAction inventoryAction, Slot slot, int i, EntityPlayer entityPlayer) {
		return null;
	}

	@Override
	public List<Integer> getTargetSlots(InventoryAction inventoryAction, Slot slot, int target, EntityPlayer entityPlayer) {
		return null;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer entityPlayer) {
		return tile.canInteractWith(entityPlayer);
	}
}
