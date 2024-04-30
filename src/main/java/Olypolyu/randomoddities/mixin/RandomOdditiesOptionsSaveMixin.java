package Olypolyu.randomoddities.mixin;

import Olypolyu.randomoddities.RandomOdditiesClient;
import net.minecraft.client.option.GameSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GameSettings.class, remap = false)
public class RandomOdditiesOptionsSaveMixin {

	@Inject(method = "saveOptions", at = @At("HEAD"))
	public void injectSaveOptions(CallbackInfo callbackInfo) {
		RandomOdditiesClient.saveOptions();
	}

}
