package Olypolyu.randomoddities.tile;

import Olypolyu.randomoddities.items.RandomOdditiesItems;
import Olypolyu.randomoddities.util.DataVendingMachineEntry;
import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.IntTag;
import com.mojang.nbt.ListTag;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.IInventory;

import java.util.ArrayList;
import java.util.Random;

public class TileEntityVendingMachine extends TileEntity implements IInventory {

	public static final int pageSize = 4;
	private int currentPage = 0;

	private int selected = 0;
	private ArrayList<DataVendingMachineEntry> products = new ArrayList<>();
	private ItemStack[] inventory = new ItemStack[28];

	{
		for (int brush = 0; brush < 15; brush++) {
			this.addProductEntry(new ItemStack(RandomOdditiesItems.paintBrushes[brush]), new Random().nextInt(10)+5);
		}

		this.products.remove(2);
	}

	public void addProductEntry(ItemStack stack, int price){
		this.products.add(new DataVendingMachineEntry(stack, price));
	}

	public DataVendingMachineEntry[] getProducts() {
		return this.products.toArray(new DataVendingMachineEntry[0]);
	}

	public void setSelected(int selected) {
		this.selected = selected;
	}

	public int getSelected() {
		return selected;
	}

	public int getPageAmount() {
		if (this.products == null) return 0;
		return this.products.size() / pageSize;
	}

	public DataVendingMachineEntry getItemStackInPage(int index) {
		return getPage(currentPage)[index];
	}

	public DataVendingMachineEntry[] getPage(int pageIndex) {
		DataVendingMachineEntry[] result = new DataVendingMachineEntry[pageSize];

		for (int page = 0; page < pageSize; page++) {
			try { result[page] = products.get(pageIndex*pageSize + page); }
			catch(Exception exception) { result[page] = null; }
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

	@Override
	public int getSizeInventory() {
		return 27;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		if (i == 27) {
			if (this.products.get(this.selected) == null) return null;
			else return this.products.get(this.selected).getProduct();
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

	private ListTag NBTProductEntryBuilder(DataVendingMachineEntry productData) {
		ListTag product = new ListTag();
		product.addTag(new IntTag().setName("price").setValue(productData.getPrice()));
		product.addTag(productData.getProduct().writeToNBT(new CompoundTag()).setName("itemStack"));
		return product;
	}

	@Override
	public void writeToNBT(CompoundTag nbttagcompound) {

		ListTag NBTListInventory = new ListTag();
		for(int i = 0; i < this.inventory.length; ++i) {
			if (this.inventory[i] != null) {
				CompoundTag NBTTagSlot = new CompoundTag();
				NBTTagSlot.putByte("Slot", (byte)i);
				this.inventory[i].writeToNBT(NBTTagSlot);
				NBTListInventory.addTag(NBTTagSlot);
			}
		}

		ListTag NBTListProduct = new ListTag();
		int index = 0;
		for (;index < this.products.size(); index++) {
			NBTListProduct.addTag(this.NBTProductEntryBuilder(this.products.get(index)).setName(String.valueOf(index)));
		}
		NBTListProduct.addTag(new IntTag(index).setName("size"));

		super.writeToNBT(nbttagcompound);
	}

	@Override
	public void readFromNBT(CompoundTag nbttagcompound) {
		super.readFromNBT(nbttagcompound);

		ListTag NBTListInventory = nbttagcompound.getList("Items");
		this.inventory = new ItemStack[this.getSizeInventory()];

		for(int i = 0; i < NBTListInventory.tagCount(); ++i) {
			CompoundTag NBTTagSlot = (CompoundTag)NBTListInventory.tagAt(i);
			byte byte0 = NBTTagSlot.getByte("Slot");
			if (byte0 >= 0 && byte0 < this.inventory.length) {
				this.inventory[byte0] = ItemStack.readItemStackFromNbt(NBTTagSlot);
			}
		}
	}

	@Override
	public void sortInventory() {
	}

}
