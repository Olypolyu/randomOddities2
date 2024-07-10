package Olypolyu.randomoddities.gui.components;

import Olypolyu.randomoddities.interfaces.IRandomOdditiesCoinAmount;
import Olypolyu.randomoddities.items.ItemShield;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiHudDesigner;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.hud.ComponentAnchor;
import net.minecraft.client.gui.hud.Layout;
import net.minecraft.client.gui.hud.MovableHudComponent;
import net.minecraft.core.item.ItemStack;
import org.lwjgl.opengl.GL11;

import java.util.Arrays;
import java.util.Optional;

public class ComponentShield extends MovableHudComponent {

	private static final String texture = "/assets/randomoddities/textures/gui/glyphs.png";
	private static final int iconSize = 16;
	private static final int spacing = 1;


	private Minecraft mc = Minecraft.getMinecraft(Minecraft.class);
	private int xScreenSize;
	private int yScreenSize;
	private Gui gui;


	public ComponentShield(String key, Layout layout) {
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
		if (!(mc.currentScreen instanceof GuiHudDesigner) && !this.isVisible(mc)) {
			return 0;
		}

		return iconSize + spacing * 2;
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
		Optional<ItemStack> opStack = Arrays.stream(minecraft.thePlayer.inventory.mainInventory)
			.filter(stack -> stack != null && stack.getItem() instanceof ItemShield)
			.findFirst();
		return opStack.isPresent();
	}

	@Override
	public void render(Minecraft minecraft, GuiIngame gui, int xScreenSize, int yScreenSize, float f) {
		mc = minecraft;
		this.gui = gui;
		this.xScreenSize = xScreenSize;
		this.yScreenSize = yScreenSize;

		Optional<ItemStack> opStack = Arrays.stream(minecraft.thePlayer.inventory.mainInventory)
			.filter(stack -> stack != null && stack.getItem() instanceof ItemShield)
			.findFirst();

		if (opStack.isPresent()) {
			ItemStack stack = opStack.get();
			int durationMax = ((ItemShield) stack.getItem()).duration;
			int duration = stack.getData().getInteger("ticks");
			drawCounter((float) Math.max(duration, 1) / durationMax);
		}
	}

	@Override
	public void renderPreview(Minecraft minecraft, Gui gui, Layout layout, int xScreenSize, int yScreenSize) {
		mc = minecraft;
		this.gui = gui;
		this.xScreenSize = xScreenSize;
		this.yScreenSize = yScreenSize;

		drawCounter(0.5F);
	}

	protected void drawCounter(float amount){
		int barX = getLayout().getComponentX(mc, this, xScreenSize) + spacing;
		int barY = getLayout().getComponentY(mc, this, yScreenSize);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(mc.renderEngine.getTexture(texture));
		gui.drawTexturedModalRect(barX, barY, 16, 16, iconSize, iconSize);

		gui.drawTexturedModalRect(barX, barY, 0, 16, iconSize, (int) ((float)iconSize * amount));
	}

}
