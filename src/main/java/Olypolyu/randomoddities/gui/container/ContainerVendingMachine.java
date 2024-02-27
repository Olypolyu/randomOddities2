package Olypolyu.randomoddities.gui.container;

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
        this.tile = tile;

        this.addSlot(new Slot(tile, 0, 56, 17));
        this.addSlot(new Slot(tile, 1, 56, 53));
        this.addSlot(new Slot(tile, 2, 116, 35));

        for(int row = 0; row < 3; row++)
        {
            for(int column = 0; column < 9; column++)
            {
                addSlot(new Slot(inventoryPlayer, column + row * 9 + 9, 8 + column * 18, 112 + row * 18));
            }

        }
        for(int crosshairRow = 0; crosshairRow < 9; crosshairRow++)
        {
            addSlot(new Slot(inventoryPlayer, crosshairRow, 8 + crosshairRow * 18, 170));
        }
    }

    @Override
    public List<Integer> getMoveSlots(InventoryAction inventoryAction, Slot slot, int i, EntityPlayer entityPlayer) {
        return null;
    }

    @Override
    public List<Integer> getTargetSlots(InventoryAction inventoryAction, Slot slot, int i, EntityPlayer entityPlayer) {
        return null;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer entityPlayer) {
        return tile.canInteractWith(entityPlayer);
    }
}
