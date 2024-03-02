package Olypolyu.randomoddities;

import net.minecraft.client.entity.fx.EntityFX;
import turniplabs.halplibe.helper.ParticleHelper;
import turniplabs.halplibe.helper.SoundHelper;
import turniplabs.halplibe.helper.TextureHelper;

import static Olypolyu.randomoddities.RandomOdditiesCore.MOD_ID;
import static Olypolyu.randomoddities.items.ItemPaintBrush.colours;

public class RandomOdditiesAssets {

	// textures
	public static final int[] emptyWindLampTex = TextureHelper.getOrCreateItemTexture(MOD_ID, "emptyWindBottle.png");
	public static final int[] windLampTex = TextureHelper.getOrCreateItemTexture(MOD_ID, "windBottle.png");

	public static final String[] particleSizes = {"tiny", "small", "medium", "large"};
	public static final int[] paintParticleIndex = new int[particleSizes.length];
	public static final int[] glassPaintedIndex = new int[colours.length];

	public void initializeAssets() {
		RandomOdditiesCore.info("loaded assets.");

		for (int size = 0; size < particleSizes.length; size++) {
			paintParticleIndex[size] = TextureHelper.getOrCreateParticleTextureIndex(MOD_ID, String.format("paint/splash_%s.png", particleSizes[size]));

			int finalSize = size;
			ParticleHelper.createParticle("paint_" + particleSizes[size],
				(world, x, y, z, motionX, motionY, motionZ) -> new EntityFX(world, x, y, z, motionX, motionY, motionZ) {
					@Override
					protected void init() {
						this.particleTextureIndex = paintParticleIndex[finalSize];
					}
				});
		}

		for (int colour = 0; colour < colours.length; colour++) {
			glassPaintedIndex[colour] = TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, String.format("glass/%s.png", colours[colour]));
		}

		SoundHelper.Client.addSound(MOD_ID, "trampoline_bounce.ogg");
		SoundHelper.Client.addSound(MOD_ID, "coin_chime.ogg");

	}

}
