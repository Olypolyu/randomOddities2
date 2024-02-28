package Olypolyu.randomoddities.mixin;

import Olypolyu.randomoddities.RandomOddities;
import Olypolyu.randomoddities.interfaces.IRandomOdditiesCoinAmount;
import com.mojang.nbt.CompoundTag;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EntityPlayer.class, remap = false)
public class RandomOdditiesCoinMixin extends EntityLiving implements IRandomOdditiesCoinAmount {
	public RandomOdditiesCoinMixin(World world) {
		super(world);
	}

	@Shadow public String username;

	@Inject(method = "readAdditionalSaveData", at = @At("HEAD"))
	private void injectReadNBT(CompoundTag tag, CallbackInfo ci) {
		RandomOddities.theBank.put(this.username, tag.getInteger("randomOddities$coinAmount"));
	}

	@Inject(method = "addAdditionalSaveData", at = @At("HEAD"))
	private void injectWriteNBT(CompoundTag tag, CallbackInfo ci) {
		tag.putInt("randomOddities$coinAmount", RandomOddities.theBank.get(this.username));
	}

	@Unique
	public int randomOddities$getCoinAmount() {
		if (RandomOddities.theBank.get(this.username) == null) return 0;
		return RandomOddities.theBank.get(this.username);
	}

	@Unique
	public void randomOddities$setCoinAmount(int amount) {
		RandomOddities.theBank.put(this.username, amount);
	}

	@Override
	public void randomOddities$addCoinAmount(int amount) {
		RandomOddities.theBank.put(this.username, this.randomOddities$getCoinAmount() + amount);
	}

}
