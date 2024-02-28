package Olypolyu.randomoddities.util;

import net.minecraft.core.item.ItemStack;

public class DataVendingMachineEntry {

	private final ItemStack product;
	private final int price;

	public DataVendingMachineEntry(ItemStack product, int price) {
		this.product = product;
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	public ItemStack getProduct() {
		return product;
	}
}
