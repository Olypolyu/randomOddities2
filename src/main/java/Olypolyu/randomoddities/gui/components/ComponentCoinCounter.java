package Olypolyu.randomoddities.gui.components;

import Olypolyu.randomoddities.interfaces.IRandomOdditiesCoinAmount;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiHudDesigner;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.hud.ComponentAnchor;
import net.minecraft.client.gui.hud.Layout;
import net.minecraft.client.gui.hud.MovableHudComponent;
import org.lwjgl.opengl.GL11;

public class ComponentCoinCounter extends MovableHudComponent {

	private static final String texture = "/assets/randomoddities/gui/glyphs.png";
	private static final int iconSize = 16;
	private static final int spacing = 1;


	private Minecraft mc = Minecraft.getMinecraft(Minecraft.class);
	private int xScreenSize;
	private int yScreenSize;
	private Gui gui;


	public ComponentCoinCounter(String key, Layout layout) {
		super(key, 0, 0, layout);
	}

	@Override
	public int getYSize(Minecraft mc) {
		if (!(mc.currentScreen instanceof GuiHudDesigner) && !this.isVisible(mc)) {
			return 0;
		}
		return (iconSize);
	}

	@Override
	public int getXSize(Minecraft mc) {
		String text = "x" + ((IRandomOdditiesCoinAmount)mc.thePlayer).randomOddities$getCoinAmount();
		return iconSize + spacing + mc.fontRenderer.getStringWidth(text);
	}

	@Override
	public int getAnchorY(ComponentAnchor anchor) {
		return (int)(anchor.yPosition * getYSize(mc));
	}

	@Override
	public int getAnchorX(ComponentAnchor anchor) {
		return (int)(anchor.xPosition * getXSize(mc));
	}

	@Override
	public boolean isVisible(Minecraft minecraft) {
		return true;
	}

	@Override
	public void render(Minecraft minecraft, GuiIngame gui, int xScreenSize, int yScreenSize, float f) {
		mc = minecraft;
		this.gui = gui;
		this.xScreenSize = xScreenSize;
		this.yScreenSize = yScreenSize;
		drawCounter(((IRandomOdditiesCoinAmount)mc.thePlayer).randomOddities$getCoinAmount());
	}

	@Override
	public void renderPreview(Minecraft minecraft, Gui gui, Layout layout, int xScreenSize, int yScreenSize) {
		mc = minecraft;
		this.gui = gui;
		this.xScreenSize = xScreenSize;
		this.yScreenSize = yScreenSize;
		drawCounter(255);
	}

	protected void drawCounter(int amount){
		int barX = getLayout().getComponentX(mc, this, xScreenSize);
		int barY = getLayout().getComponentY(mc, this, yScreenSize);

		gui.drawString(this.mc.fontRenderer, "x" + amount, barX + iconSize + spacing, barY + (iconSize/4), 0xFFFFFFFF);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(mc.renderEngine.getTexture(texture));
		gui.drawTexturedModalRect(barX, barY, 0, 0, iconSize, iconSize);
	}

}
