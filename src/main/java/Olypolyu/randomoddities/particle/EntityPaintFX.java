package Olypolyu.randomoddities.particle;

import Olypolyu.randomoddities.RandomOdditiesAssets;
import net.minecraft.client.entity.fx.EntityFX;
import net.minecraft.core.world.World;

public class EntityPaintFX extends EntityFX {
	public EntityPaintFX(int size, float red, float blue, float green, World world, double x, double y, double z, double motionX, double motionY, double motionZ) {
		super(world, x, y, z, motionX, motionY, motionZ);
		this.particleTextureIndex = RandomOdditiesAssets.paintParticleIndex[size];

		this.particleRed = red;
		this.particleBlue = blue;
		this.particleGreen = green;

		this.particleGravity = 0.32F;
	}
}
