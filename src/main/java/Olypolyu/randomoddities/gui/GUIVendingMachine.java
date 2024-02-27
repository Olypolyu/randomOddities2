package Olypolyu.randomoddities.gui;

import Olypolyu.randomoddities.gui.container.ContainerVendingMachine;
import Olypolyu.randomoddities.interfaces.IRandomOdditiesCoinAmount;
import Olypolyu.randomoddities.tile.TileEntityVendingMachine;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiContainer;
import net.minecraft.core.lang.I18n;
import net.minecraft.core.player.inventory.InventoryPlayer;
import org.lwjgl.opengl.GL11;

public class GUIVendingMachine extends GuiContainer {

	protected static final String texture = "/assets/randomoddities/gui/vending_machine_GUI.png";
	private final TileEntityVendingMachine tile;

	public int xSize = 176;
	public int ySize = 222;

	protected GuiButton lastPageButton;
	protected GuiButton nextPageButton;

	public GUIVendingMachine(InventoryPlayer inventoryPlayer, TileEntityVendingMachine tile) {
		super(new ContainerVendingMachine(inventoryPlayer, tile));
		this.tile = tile;
	}

	@Override
	public void init() {
		super.init();

		this.controlList.add(this.lastPageButton = new GuiButton(500, 0, 0, 16, 12, ""));
		this.controlList.add(this.nextPageButton = new GuiButton(501, 0, 0, 16, 12, ""));

		lastPageButton.visible = false;
		nextPageButton.visible = false;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f) {
		IRandomOdditiesCoinAmount wallet = (IRandomOdditiesCoinAmount) mc.thePlayer;
		int X = (width - xSize) / 2;
		int Y = (height - ySize) / 2;

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(mc.renderEngine.getTexture(texture));
		drawTexturedModalRect(X, Y, 0, 0, xSize, ySize);

		fontRenderer.drawString("x" + wallet.randomOddities$getCoinAmount(), X + 95, Y + 20, 0xFFFFFFFF);
		fontRenderer.drawString(I18n.getInstance().translateKey("tile.randomoddities.gui.vending_machine.title"), X + 6, Y + 6, 0xFF404040);

		lastPageButton.xPosition = X + 11; lastPageButton.yPosition = Y + 109;
		nextPageButton.xPosition = X + 60; nextPageButton.yPosition = Y + 109;

		drawTexturedModalRect(X + 11, Y + 109, 175, 18, 15, 12);

	}

	@Override
	protected void buttonPressed(GuiButton button) {
		System.out.print(button);
		super.buttonPressed(button);
	}

	@Override
	protected void drawGuiContainerForegroundLayer() {
	}

}
