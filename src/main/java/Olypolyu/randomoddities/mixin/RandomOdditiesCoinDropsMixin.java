package Olypolyu.randomoddities.mixin;

import Olypolyu.randomoddities.interfaces.IRandomOdditiesCoinAmount;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(value = EntityLiving.class, remap = false)
public abstract class RandomOdditiesCoinDropsMixin extends Entity {
	public RandomOdditiesCoinDropsMixin(World world) {
		super(world);
	}

	@Unique List<IRandomOdditiesCoinAmount> randomOddities$attackedBy = new ArrayList<>();

	@Inject(method = "hurt", at = @At("TAIL"))
	private void injectHurt(Entity attacker, int damage, DamageType type, CallbackInfoReturnable<Boolean> ci) {
		if (!randomOddities$attackedBy.contains(attacker) &&  attacker instanceof IRandomOdditiesCoinAmount){
			randomOddities$attackedBy.add((IRandomOdditiesCoinAmount) attacker);
		}

		if (((EntityLiving)(Object)this).getHealth() < 0) {
			int coinAmount = ((int) ((EntityLiving) (Object) this).getMaxHealth() / 50) * 8 / Math.max(randomOddities$attackedBy.size(), 1);
			coinAmount = (int) (coinAmount * (0.5F + world.rand.nextFloat()));

			for (IRandomOdditiesCoinAmount wallet : randomOddities$attackedBy) {
				wallet.randomOddities$addCoinAmount(coinAmount);

				if (wallet instanceof Entity) {
					Entity walletEntity = (Entity) wallet;
					if (coinAmount > 0)
						world.playSoundAtEntity(walletEntity, walletEntity, "randomoddities.coin_chime", 0.65F, 1.0F);

					for (int coin = 0; coin < coinAmount; coin++) {
						int motionMod = world.rand.nextBoolean() ? 1 : -1;
						double rad = Math.toRadians((double) (360 / coinAmount) * coin);
						double radius = 1.25;
						world.spawnParticle(
							"coin",
							walletEntity.x + (Math.cos(rad) * radius),
							walletEntity.y - 1,
							walletEntity.z + (Math.sin(rad) * radius),
							world.rand.nextDouble() / 4 * motionMod,
							world.rand.nextDouble() / 4 * motionMod,
							world.rand.nextDouble() / 4 * motionMod
						);
					}
				}
			}
		}
	}
}
