package Olypolyu.randomoddities.gui.widgets;

import Olypolyu.randomoddities.util.DataVendingMachineEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiRenderItem;
import net.minecraft.client.render.FontRenderer;
import org.lwjgl.opengl.GL11;

public class GuiButtonVendingMachineEntry extends GuiButton {

	private final int vSelected = 22;
	public boolean selected = false;

	protected final int u = 176;
	protected final int v = 0;

	protected DataVendingMachineEntry entry = null;
	protected static final String texture = "/assets/randomoddities/gui/vending_machine_GUI.png";

	public GuiButtonVendingMachineEntry(int id, int xPosition, int yPosition) {
		super(id, xPosition, yPosition, "");
		this.width = 80;
		this.height = 22;
	}

	public void setPosition(int x, int y) {
		setX(x);
		setY(y);
	}

	public void setEntry(DataVendingMachineEntry entry) {
		this.entry = entry;
	}

	public DataVendingMachineEntry getEntry() {
		return entry;
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		if (this.visible) {
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glBindTexture(3553, mc.renderEngine.getTexture(texture));
			int v = selected ? this.vSelected : this.v;
			this.drawTexturedModalRect(this.xPosition, this.yPosition, this.u, v, this.width, this.height);

			FontRenderer fontrenderer = mc.fontRenderer;
			int textColor = this.selected ? 16777120 : 0xFFFFFFFF;
			this.drawString(fontrenderer, "x"+ this.entry.getPrice(), this.xPosition + 33, this.yPosition + 7, textColor);
			GuiRenderItem itemRenderer = new GuiRenderItem(mc);
			itemRenderer.render(this.entry.getProduct(), this.xPosition + 3, this.yPosition + 3);
		}
	}
}
