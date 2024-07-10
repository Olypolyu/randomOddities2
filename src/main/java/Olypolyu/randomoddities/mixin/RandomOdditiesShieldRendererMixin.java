package Olypolyu.randomoddities.mixin;

import Olypolyu.randomoddities.items.RandomOdditiesItems;
import Olypolyu.randomoddities.models.ModelSummoningCircle;
import net.minecraft.client.render.entity.LivingRenderer;
import net.minecraft.client.render.entity.PlayerRenderer;
import net.minecraft.client.render.model.Cube;
import net.minecraft.client.render.model.ModelBase;
import net.minecraft.client.render.model.ModelBiped;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.render.tessellator.Tessellator;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PlayerRenderer.class, remap = false)
public abstract class RandomOdditiesShieldRendererMixin extends LivingRenderer<EntityPlayer> {
	public RandomOdditiesShieldRendererMixin(ModelBase model, float shadowSize) {
		super(model, shadowSize);
	}

	@Shadow
	private ModelBiped modelBipedMain;

	@Inject(method = "renderSpecials", at = @At("HEAD"), cancellable = true)
	public void injectRenderer(EntityPlayer entity, float f, CallbackInfo ci) {
		ItemStack itemstack = entity.inventory.getCurrentItem();

		if (itemstack != null && itemstack.itemID == RandomOdditiesItems.shield.id) {
			if (itemstack.getData().getBoolean("active")) {
				GL11.glPushMatrix();
				modelBipedMain.bipedRightArm.postRender(0.0625F);

				final float scale = 0.625F;
				GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
				GL11.glScalef(scale, scale, scale);
				GL11.glRotatef(175, 0.0F, 0.0F, 1.0F);
				GL11.glRotatef(145F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(30F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(-30F, 0.0F, 1.0F, 0.0F);
				GL11.glTranslatef(0.25F, -0.90075F, 0.375F);

				ItemModelDispatcher.getInstance().getDispatch(itemstack).renderItem(Tessellator.instance, this.renderDispatcher.itemRenderer, entity, itemstack);
				GL11.glPopMatrix();

				int angle = itemstack.getData().getInteger("animation_data");
				Cube model = new Cube(0, 0, 3, 3);
				float y =  angle < 180 ? angle * 0.20F / 360 : (360 - angle) * 0.20F / 360;

				GL11.glColor4f(0.8156862745F, 0.5019607843F, 0.0980392157F, 0.75F);
				renderDispatcher.renderEngine.bindTexture(renderDispatcher.renderEngine.getTexture("/assets/randomoddities/textures/block/circle.png"));
				model.addBox(-1.5F, 1.25F + y, -1.5F, 3, 0, 3, 0, true);
				model.rotateAngleY = (angle+1 /16) * 16;
				model.render(1);

				ci.cancel();
			}

			else {
				GL11.glPushMatrix();
				modelBipedMain.bipedRightArm.postRender(0.0625F);

				final float scale = 0.625F;
				GL11.glTranslatef(-0.15F, 0.1875F, -0.1875F);
				GL11.glRotatef(30, 0.0F, 0.5F, 0.0F);
				GL11.glScalef(scale, scale, scale);

				ItemModelDispatcher.getInstance().getDispatch(itemstack).renderItem(Tessellator.instance, this.renderDispatcher.itemRenderer, entity, itemstack);
				GL11.glPopMatrix();
				ci.cancel();
			}
		}
	}

}
