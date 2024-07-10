package Olypolyu.randomoddities.particle;

import net.minecraft.client.entity.fx.EntityFX;
import net.minecraft.core.world.World;
import net.minecraft.client.render.stitcher.TextureRegistry;

public class EntityPaintFX extends EntityFX {
	public EntityPaintFX(int size, float red, float blue, float green, World world, double x, double y, double z, double motionX, double motionY, double motionZ) {
		super(world, x, y, z, motionX, motionY, motionZ);
		this.particleTexture = TextureRegistry.getTexture("randomoddities:particle/paint_splash_" + size);

		this.particleRed = red;
		this.particleBlue = blue;
		this.particleGreen = green;

		this.particleGravity = 0.32F;
	}
}
