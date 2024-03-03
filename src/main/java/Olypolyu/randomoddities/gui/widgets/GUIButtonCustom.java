package Olypolyu.randomoddities.gui.widgets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.render.FontRenderer;
import org.lwjgl.opengl.GL11;

@SuppressWarnings("unused")
public class GUIButtonCustom extends GuiButton {

	public boolean pressed = false;
	private final String texture;
	private final int facingRight;
	private final int u;
	private final int v;


	public GUIButtonCustom(int id, String texture, boolean facingRight, int width, int height, int u, int v) {
		super(id, 0, 0, "");
		this.texture = texture;
		this.facingRight = (facingRight) ? 1 : 0;

		this.width = width;
		this.height = height;
		this.u = u;
		this.v = v;
	}

	public GUIButtonCustom(int id, String texture, String label, boolean facingRight, int width, int height, int u, int v) {
		super(id, 0, 0, label);
		this.texture = texture;
		this.facingRight = (facingRight) ? 1 : 0;

		this.width = width;
		this.height = height;
		this.u = u;
		this.v = v;
	}

	@Override
	protected int getButtonState(boolean hovered) {
		if (!enabled) return 2;
		if (this.pressed) return 3;
		if (hovered) return 0;
		return 1;
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		if (this.visible) {
			boolean mouseOver = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
			int state = this.getButtonState(mouseOver);

			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glBindTexture(3553, mc.renderEngine.getTexture(texture));
			this.drawTexturedModalRect(this.xPosition, this.yPosition, this.u + (this.width * facingRight), this.v + (this.height * state), this.width, this.height);

			this.mouseDragged(mc, mouseX, mouseY);

			FontRenderer fontrenderer = mc.fontRenderer;
			int textColor;

			switch (state) {
				case 0:
					textColor = 10526880;
					break;
				case 1:
					textColor = 14737632;
					break;
				default:
					textColor = 16777120;
			}

			this.drawStringCentered(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, textColor);
		}
	}
}
