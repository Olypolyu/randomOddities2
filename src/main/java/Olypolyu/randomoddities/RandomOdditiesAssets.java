package Olypolyu.randomoddities;

import net.minecraft.client.entity.fx.EntityDiggingFX;
import net.minecraft.client.entity.fx.EntityFX;
import turniplabs.halplibe.helper.ParticleHelper;
import turniplabs.halplibe.helper.TextureHelper;

public class RandomOdditiesAssets {

	public static final int[] emptyWindLampTex = TextureHelper.getOrCreateItemTexture(RandomOddities.MOD_ID, "emptyWindBottle.png");
	public static final int[] windLampTex = TextureHelper.getOrCreateItemTexture(RandomOddities.MOD_ID, "windBottle.png");


	public static final String[] particleSizes = {"tiny", "small", "medium", "large"};
	public static final int[] paintParticleIndex = new int[particleSizes.length];

	public void initializeAssets() {
		RandomOddities.info("loaded assets.");

		for (int size = 0; size < particleSizes.length; size++) {
			paintParticleIndex[size] = TextureHelper.getOrCreateParticleTextureIndex(RandomOddities.MOD_ID, String.format("paint/splash_%s.png", particleSizes[size]));

			int finalSize = size;
			ParticleHelper.createParticle("paint_" + particleSizes[size],
				(world, x, y, z, motionX, motionY, motionZ) -> new EntityFX(world, x, y, z, motionX, motionY, motionZ) {
					@Override
					protected void init() {
						this.particleTextureIndex = paintParticleIndex[finalSize];
					}
				});
		}
	}

}
