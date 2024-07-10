package Olypolyu.randomoddities.mixin;

import net.minecraft.core.entity.Entity;
import net.minecraft.core.util.helper.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Entity.class, remap = false)
public class RandomOdditiesTrampolineMixin {
    @Shadow
    public double y;
    @Unique
    public double randomoddities$prevY = y;
    @Unique
    public double randomoddities$deltaY;

    @Inject(method = "baseTick()V", at = @At(value ="HEAD"))
    private void tick(CallbackInfo ci){
        randomoddities$deltaY = y - randomoddities$prevY;
        randomoddities$prevY = y;
    }

    /*@Redirect(
        method = "move",
        at = @At(value = "INVOKE", target = "floor_double", ordinal = 5)
    )
    public int extendBlockRange(double d) {
        return MathHelper.floor_double(d + randomoddities$deltaY);
    }*/
}
