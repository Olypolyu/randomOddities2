package Olypolyu.randomoddities.tile;

import Olypolyu.randomoddities.items.RandomOdditiesItems;
import Olypolyu.randomoddities.util.DataVendingMachineEntry;
import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.ListTag;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.IInventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TileEntityVendingMachine extends TileEntity implements IInventory {

	public static final int pageSize = 4;
	private int currentPage = 0;

	private DataVendingMachineEntry selected = null;
	public List<DataVendingMachineEntry> products = new ArrayList<>();
	private ItemStack[] inventory = new ItemStack[28];

	private DataVendingMachineEntry productsGet(int index) {
		try {
			return products.get(index);
		}
		catch(Exception exception) { return null; }
	}

	public void setSelected(DataVendingMachineEntry selected) {
		this.selected = selected;
	}

	public DataVendingMachineEntry getSelected() {
		return selected;
	}

	public int getPageAmount() {
		if (this.products == null || this.products.isEmpty()) return 0;
		return this.products.size() / pageSize;
	}

	public DataVendingMachineEntry getItemStackInPage(int index) {
		return getPage(currentPage)[index];
	}

	public DataVendingMachineEntry[] getPage(int pageIndex) {
		DataVendingMachineEntry[] result = new DataVendingMachineEntry[pageSize];

		for (int page = 0; page < pageSize; page++) {
			result[page] = productsGet(pageIndex*pageSize + page);
		}

		return result;
	}

	public boolean hasPreviousPage() {
		return currentPage != 0;
	}

	public boolean hasNextPage() {
		return currentPage < getPageAmount();
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void nextPage() {
		currentPage++;
	}

	public void previousPage() {
		if (currentPage == 0) return;
		currentPage--;
	}

	{
		for (int brush = 0; brush < 15; brush++) {
			this.products.add(new DataVendingMachineEntry(new ItemStack(RandomOdditiesItems.paintBrushes[brush]), new Random().nextInt(10)+5));
		}
		this.products.remove(2);
	}


	@Override
	public int getSizeInventory() {
		return 27;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		if (i == 27) {
			if (selected == null) return null;
			else return selected.getProduct();
		}

		return inventory[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (this.inventory[i] != null) {
			ItemStack itemstack1;
			if (this.inventory[i].stackSize <= j) {
				itemstack1 = this.inventory[i];
				this.inventory[i] = null;
				return itemstack1;
			} else {
				itemstack1 = this.inventory[i].splitStack(j);
				if (this.inventory[i].stackSize <= 0) {
					this.inventory[i] = null;
				}

				return itemstack1;
			}
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		this.inventory[i] = itemstack;
		if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
			itemstack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInvName() {
		return "Enchanter";
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

	@Override
	public void writeToNBT(CompoundTag nbttagcompound) {

		ListTag nbttaglist = new ListTag();
		for(int i = 0; i < this.inventory.length; ++i) {
			if (this.inventory[i] != null) {
				CompoundTag nbttagcompound1 = new CompoundTag();
				nbttagcompound1.putByte("Slot", (byte)i);
				this.inventory[i].writeToNBT(nbttagcompound1);
				nbttaglist.addTag(nbttagcompound1);
			}
		}
		super.writeToNBT(nbttagcompound);
	}

	@Override
	public void readFromNBT(CompoundTag nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		ListTag nbttaglist = nbttagcompound.getList("Items");
		this.inventory = new ItemStack[this.getSizeInventory()];

		for(int i = 0; i < nbttaglist.tagCount(); ++i) {
			CompoundTag nbttagcompound1 = (CompoundTag)nbttaglist.tagAt(i);
			byte byte0 = nbttagcompound1.getByte("Slot");
			if (byte0 >= 0 && byte0 < this.inventory.length) {
				this.inventory[byte0] = ItemStack.readItemStackFromNbt(nbttagcompound1);
			}
		}
	}

	@Override
	public void sortInventory() {
	}

}
