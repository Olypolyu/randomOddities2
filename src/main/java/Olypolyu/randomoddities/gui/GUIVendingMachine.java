package Olypolyu.randomoddities.gui;

import Olypolyu.randomoddities.gui.container.ContainerVendingMachine;
import Olypolyu.randomoddities.interfaces.IRandomOdditiesCoinAmount;
import Olypolyu.randomoddities.tile.TileEntityVendingMachine;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiContainer;
import net.minecraft.core.lang.I18n;
import net.minecraft.core.player.inventory.InventoryPlayer;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;

public class GUIVendingMachine extends GuiContainer {

	protected static final String texture = "/assets/randomoddities/gui/vending_machine_GUI.png";
	private final TileEntityVendingMachine tile;

	private int id = 723;

	public int xSize = 176;
	public int ySize = 222;

	protected GuiButton lastPageButton;
	protected GuiButton nextPageButton;

	protected GuiButtonVendingMachineEntry[] entries = new GuiButtonVendingMachineEntry[4];

	HashMap<String, Boolean> buttons = new HashMap<>();

	public GUIVendingMachine(InventoryPlayer inventoryPlayer, TileEntityVendingMachine tile) {
		super(new ContainerVendingMachine(inventoryPlayer, tile));
		this.tile = tile;
	}

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

		for (int index = 0; index < entries.length; index++) {
			entries[index].setPosition(X + 8,Y + 18 + (22 * index));
		}

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(mc.renderEngine.getTexture(texture));
		this.drawTexturedModalRect(X, Y, 0, 0, xSize, ySize);
		fontRenderer.drawStringWithShadow("x" + wallet.randomOddities$getCoinAmount(), X + 106, Y + 20, 0xFFFFFFFF);
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
		if (button instanceof GuiButtonVendingMachineEntry)
			for (GuiButtonVendingMachineEntry entry : entries) {
				entry.selected = button.id == entry.id;
			}

		if (button instanceof GUIButtonCustom) {
			((GUIButtonCustom) button).pressed = false;
		}

		super.buttonReleased(button);
	}

	@Override
	protected void drawGuiContainerForegroundLayer() {
		super.drawGuiContainerForegroundLayer();
		fontRenderer.drawString(I18n.getInstance().translateKey("tile.randomoddities.gui.vending_machine.title"),6,-22, 0xFF404040);
	}
}
