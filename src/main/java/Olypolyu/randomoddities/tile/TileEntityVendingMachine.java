package Olypolyu.randomoddities.tile;

import Olypolyu.randomoddities.util.DataVendingMachineEntry;
import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.ListTag;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.IInventory;

import java.util.List;

public class TileEntityVendingMachine extends TileEntity implements IInventory {

	public List<DataVendingMachineEntry> products;

	public static int pageSize = 4;
	private ItemStack[] inventory = new ItemStack[27];

	@Override
	public int getSizeInventory() {
		return this.inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return null;
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

	public int getPageAmount() {
		return (this.products.size() % pageSize == 0) ? this.products.size()/pageSize : this.products.size()/pageSize + 1;
	}

	public DataVendingMachineEntry[] getpage(int pageIndex) {
		DataVendingMachineEntry[] result = new DataVendingMachineEntry[pageSize];

		for (int page = 0; page < pageSize; page++) {
			result[page] = products.get(pageIndex*pageSize + page);
		}

		return result;
	}

	@Override
	public void sortInventory() {
	}

}
