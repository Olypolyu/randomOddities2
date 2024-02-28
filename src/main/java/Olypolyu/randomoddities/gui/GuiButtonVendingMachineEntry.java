package Olypolyu.randomoddities.gui;

import Olypolyu.randomoddities.items.RandomOdditiesItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiRenderItem;
import net.minecraft.client.render.FontRenderer;
import net.minecraft.core.item.ItemStack;
import org.lwjgl.opengl.GL11;

import java.util.Random;

import static Olypolyu.randomoddities.gui.GUIVendingMachine.texture;

public class GuiButtonVendingMachineEntry extends GuiButton {
	private int price = 555555;
	private ItemStack itemStack = new ItemStack(RandomOdditiesItems.paintBrushes[new Random().nextInt(15)]);

	private int width = 80;
	private int height = 22;

	private final int u = 176;
	private final int v = 0;

	private final int vSelected = 22;
	public boolean selected = false;

	public GuiButtonVendingMachineEntry(int id, int xPosition, int yPosition) {
		super(id, xPosition, yPosition, "");
	}

	public void setPosition(int x, int y) {
		setX(x);
		setY(y);
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
			this.drawString(fontrenderer, "x"+this.price, this.xPosition + 33, this.yPosition + 7, textColor);

			GuiRenderItem itemRenderer = new GuiRenderItem(mc);
			itemRenderer.render(this.itemStack, this.xPosition + 3, this.yPosition + 3);
		}
	}
}
