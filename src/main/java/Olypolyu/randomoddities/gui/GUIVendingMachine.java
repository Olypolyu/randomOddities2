package Olypolyu.randomoddities.gui;

import Olypolyu.randomoddities.gui.container.ContainerVendingMachine;
import Olypolyu.randomoddities.gui.widgets.GUIButtonCustom;
import Olypolyu.randomoddities.gui.widgets.GuiButtonVendingMachineEntry;
import Olypolyu.randomoddities.interfaces.IRandomOdditiesCoinAmount;
import Olypolyu.randomoddities.tile.TileEntityVendingMachine;
import Olypolyu.randomoddities.util.DataVendingMachineEntry;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiContainer;
import net.minecraft.core.lang.I18n;
import net.minecraft.core.player.inventory.InventoryPlayer;
import org.lwjgl.opengl.GL11;

import java.util.Arrays;

public class GUIVendingMachine extends GuiContainer {

	public GUIVendingMachine(InventoryPlayer inventoryPlayer, TileEntityVendingMachine tile) {
		super(new ContainerVendingMachine(inventoryPlayer, tile));
		this.tile = tile;
    }

	private final TileEntityVendingMachine tile;

	protected static final String texture = "/assets/randomoddities/gui/vending_machine_GUI.png";
	public int xSize = 176;
	public int ySize = 222;

	protected int id = 723;
	protected GuiButton lastPageButton;
	protected GuiButton nextPageButton;

	protected GuiButtonVendingMachineEntry[] entries = new GuiButtonVendingMachineEntry[4];

	@Override
	public void init() {
		super.init();
		this.controlList.add(this.lastPageButton = new GUIButtonCustom(id++, texture, false, 15, 12, 176, 44));
		this.controlList.add(this.nextPageButton = new GUIButtonCustom(id++, texture, true, 15, 12, 176, 44));
		for (int index = 0; index < entries.length; index++) {
			this.controlList.add(entries[index] = new GuiButtonVendingMachineEntry(id++, 0, 0));
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f) {
		IRandomOdditiesCoinAmount wallet = (IRandomOdditiesCoinAmount) mc.thePlayer;
		int X = (width - xSize) / 2;
		int Y = (height - ySize) / 2;

		lastPageButton.xPosition = X + 11; lastPageButton.yPosition = Y + 109;
		nextPageButton.xPosition = X + 70; nextPageButton.yPosition = Y + 109;

		lastPageButton.enabled = tile.hasPreviousPage();
		nextPageButton.enabled = tile.hasNextPage();

		for (int index = 0; index < entries.length; index++) {
			entries[index].setPosition(X + 8,Y + 18 + (22 * index));

			DataVendingMachineEntry entry = this.tile.getItemStackInPage(index);
			if (entry == null) {
				entries[index].visible = false;
				entries[index].enabled = false;
			}

			else {
				entries[index].visible = true;
				entries[index].enabled = true;
				entries[index].setEntry(entry);

				entries[index].selected = Arrays.asList(tile.getProducts()).indexOf(entry) == tile.getSelected();
			}
		}

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(mc.renderEngine.getTexture(texture));
		this.drawTexturedModalRect(X, Y, 0, 0, xSize, ySize);
		fontRenderer.drawStringWithShadow("x" + wallet.randomOddities$getCoinAmount(), X + 106, Y + 20, 0xFFFFFFFF);

		fontRenderer.drawString(String.valueOf(tile.getCurrentPage()), X + 47, Y + 113, 0x090c25);
		fontRenderer.drawString(String.valueOf(tile.getCurrentPage()), X + 46, Y + 112, 0xFFFFFFFF);

	}

	@Override
	protected void buttonPressed(GuiButton button) {
		if (button instanceof GUIButtonCustom) {
			((GUIButtonCustom) button).pressed = true;
		}

		super.buttonPressed(button);
	}

	@Override
	protected void buttonReleased(GuiButton button) {

		if (button instanceof GuiButtonVendingMachineEntry) {
			tile.setSelected(Arrays.asList(tile.getProducts()).indexOf(((GuiButtonVendingMachineEntry)button).getEntry()));
		}

		if (button instanceof GUIButtonCustom) {
			((GUIButtonCustom) button).pressed = false;
		}

		if (button.id == nextPageButton.id) tile.nextPage();
		if (button.id == lastPageButton.id) tile.previousPage();
		super.buttonReleased(button);
	}

	@Override
	protected void drawGuiContainerForegroundLayer() {
		super.drawGuiContainerForegroundLayer();
		fontRenderer.drawString(I18n.getInstance().translateKey(I18n.getInstance().translateKey("tile.randomoddities.vending_machine.name")),6,-22, 0xFF404040);
	}
}
