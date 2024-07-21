package Olypolyu.randomoddities.gui.components;

import Olypolyu.randomoddities.RandomOdditiesClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.guidebook.mobs.MobInfoRegistry;
import net.minecraft.client.gui.hud.ComponentAnchor;
import net.minecraft.client.gui.hud.Layout;
import net.minecraft.client.gui.hud.MovableHudComponent;
import net.minecraft.client.option.BooleanOption;
import net.minecraft.client.render.EntityRenderDispatcher;
import net.minecraft.client.render.Lighting;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.core.entity.EntityDispatcher;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.lang.I18n;
import org.lwjgl.opengl.GL11;

public class ComponentHealthMeter extends MovableHudComponent {

	private static final String texture = "/assets/randomoddities/textures/gui/health_meter.png";
	private Minecraft mc = Minecraft.getMinecraft(Minecraft.class);
	private int xScreenSize;
	private int yScreenSize;
	private Gui gui;
	private EntityLiving entity = null;
	private long timestamp = 0;
	private final int persistFor = 2;

	public ComponentHealthMeter(String key, Layout layout) {
		super(key, 100, 48, layout);
	}

	@Override
	public boolean isVisible(Minecraft minecraft) {
	    if (!mc.gameSettings.immersiveMode.drawHotbar()) return false;
		BooleanOption healthMeter = (BooleanOption) RandomOdditiesClient.getOption("health_meter");
		if ( healthMeter != null) {
			if (!healthMeter.value) return false;
		}

		if (this.entity != null && (System.currentTimeMillis()/1000L) - timestamp < persistFor) return true;
        return (minecraft.objectMouseOver != null && minecraft.objectMouseOver.entity instanceof EntityLiving);
    }

	@Override
	public int getXSize(Minecraft mc) {
		if (entity == null) return 0;
		return Math.max(70 + mc.fontRenderer.getStringWidth(getNameFromEntity(entity)), 60 + mc.fontRenderer.getStringWidth(entity.getMaxHealth() + " x/" + entity.getHealth()));
	}

	@Override
	public int getYSize(Minecraft mc) {
		if (entity == null) return 0;
		return super.getYSize(mc);
	}

	@Override
	public int getAnchorX(ComponentAnchor anchor) {
		return (int)(anchor.xPosition * (float)this.getXSize(mc));
	}

	@Override
	public void render(Minecraft minecraft, GuiIngame gui, int xScreenSize, int yScreenSize, float f) {
		mc = minecraft;
		this.gui = gui;
		this.xScreenSize = xScreenSize;
		this.yScreenSize = yScreenSize;

		if (minecraft.objectMouseOver != null && minecraft.objectMouseOver.entity instanceof EntityLiving) {
			this.entity = (EntityLiving) minecraft.objectMouseOver.entity;
			this.timestamp = System.currentTimeMillis()/1000L;
		}

		drawMeter(entity);
	}

	@Override
	public void renderPreview(Minecraft minecraft, Gui gui, Layout layout, int xScreenSize, int yScreenSize) {
		this.mc = minecraft;
		this.gui = gui;
		this.xScreenSize = xScreenSize;
		this.yScreenSize = yScreenSize;
		this.entity = minecraft.thePlayer;
		drawMeter(entity);
	}

	public void drawMeter(EntityLiving entity){
		if (entity == null) return;

		int barX = getLayout().getComponentX(mc, this, xScreenSize);
		int barY = getLayout().getComponentY(mc, this, yScreenSize);

		// draw mob
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(mc.renderEngine.getTexture(texture));
		gui.drawTexturedModalRect(barX, barY, 0, 0, 48, 48);
		drawPaperDoll(entity, barX, barY - 2, 30F);
		barX = barX + 50;

		// draw name section:
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(mc.renderEngine.getTexture(texture));
		String entityName = getNameFromEntity(entity);
		final int tagWidth = 16;
		final int tagHeight = 21;
		int color = 0xFFFFFFFF;

		int width = mc.fontRenderer.getStringWidth(entityName);
		int widthClone = width;
		while ((widthClone = widthClone - 16) > 0) {
			gui.drawTexturedModalRect(barX + widthClone, barY, 64, 0, tagWidth, tagHeight);
		}

		gui.drawTexturedModalRect(barX, barY, 48, 0, tagWidth, tagHeight);
		gui.drawTexturedModalRect(barX + width - (tagWidth/4) + 2, barY, 80, 0, tagWidth, tagHeight);
		gui.drawString(mc.fontRenderer, entityName, (int) (barX + (tagWidth/2.5)), barY + 6, color);


		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(mc.renderEngine.getTexture(texture));
		gui.drawTexturedModalRect(barX, barY + tagHeight + 2, 96, 0, 9, 9);

		String health = " x" + entity.getHealth() + "/" + entity.getMaxHealth();
		gui.drawString(mc.fontRenderer, health, (int) (barX + (tagWidth/2.5)), barY + tagHeight + 2	, color);
	}

	private static float prevYRot = -1000;
	private static float desiredYRot = 0;
	public void drawPaperDoll(EntityLiving entity, int x, int y, float scale) {
		if (prevYRot == -1000){
			prevYRot = entity.yRot;
		}
		GL11.glColor4d(1,1,1,1);

		final float lookRange = 30;
		desiredYRot += entity.yRot - prevYRot;
		desiredYRot = Math.max(desiredYRot, -lookRange);
		desiredYRot = Math.min(desiredYRot, lookRange);

		GL11.glEnable(32826);
		GL11.glEnable(2903);
		GL11.glEnable(2929);
		GL11.glPushMatrix();
		GL11.glTranslatef(x + 24, y + 45, 50.0f);
		scale = scale/Math.max(Math.max(entity.bbHeight, entity.bbWidth), 1.0F);
        GL11.glScalef(-scale, scale, scale);
		GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);

		float oYawOff = entity.renderYawOffset;
		float oYRot = entity.yRot;
		float oXRot = entity.xRot;

		Lighting.enableLight();
		entity.renderYawOffset = -15;
		entity.yRot = desiredYRot;

		entity.entityBrightness = 1.0f;
		GL11.glTranslatef(0.0f, entity.heightOffset, 0.0f);
		EntityRenderDispatcher.instance.viewLerpYaw = 180.0f;
		EntityRenderDispatcher.instance.renderEntityWithPosYaw(Tessellator.instance, entity, 0.0, 0.0, 0.0, 0.0f, 1.0f);
		entity.entityBrightness = 0.0f;

		entity.renderYawOffset = oYawOff;
		entity.yRot = oYRot;
		entity.xRot = oXRot;
		GL11.glPopMatrix();
		Lighting.disable();
		GL11.glDisable(32826);
		prevYRot = entity.yRot;
	}

	public static String getNameFromEntity(EntityLiving entity){
		I18n translator = I18n.getInstance();
		if (entity.hadNicknameSet) {
			return entity.nickname;
		}
		if (entity instanceof EntityPlayer) {
			return ((EntityPlayer) entity).username;
		}
		if (MobInfoRegistry.getMobInfo(entity.getClass()) != null){
			return translator.translateKey(MobInfoRegistry.getMobInfo(entity.getClass()).getNameTranslationKey());
		}
		return String.valueOf(EntityDispatcher.getEntityString(entity));
	}

}
