package Olypolyu.randomoddities;

import net.minecraft.client.entity.fx.EntityFX;
import turniplabs.halplibe.helper.ParticleHelper;
import turniplabs.halplibe.helper.SoundHelper;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.client.render.stitcher.IconCoordinate;

import static Olypolyu.randomoddities.RandomOdditiesCore.MOD_ID;
import static Olypolyu.randomoddities.items.ItemPaintBrush.colours;

public class RandomOdditiesAssets {
	public void initializeAssets() {
		/*for (int size = 0; size < 4; size++) {
			int finalSize = size;
			ParticleHelper.createParticle("paint_" + size,
				(world, x, y, z, motionX, motionY, motionZ, data) -> new EntityFX(world, x, y, z, motionX, motionY, motionZ) {
					@Override
					protected void init() {
						this.particleTexture = TextureRegistry.getTexture("randomoddities:paint/splash_" + finalSize);
					}
				}
			);
		}
		for (int colour = 0; colour < colours.length; colour++) {
			glassPaintedIndex[colour] = TextureRegistry.getTexture(String.format("glass/%s.png", colours[colour]));
		}*/

		ParticleHelper.createParticle("coin", (world, x, y, z, motionX, motionY, motionZ, data) -> new EntityFX(world, x, y, z, motionX, motionY, motionZ) {
				@Override
				protected void init() {
				    this.particleTexture = TextureRegistry.getTexture("randomoddities:particle/coin");
				}
			});

		SoundHelper.addSound(MOD_ID, "trampoline_bounce.ogg");
		SoundHelper.addSound(MOD_ID, "coin_chime.ogg");

		SoundHelper.addSound(MOD_ID, "metal_pipe_zero.ogg");
		SoundHelper.addSound(MOD_ID, "metal_pipe_one.ogg");
		SoundHelper.addSound(MOD_ID, "metal_pipe_two.ogg");

	}

}
