package Olypolyu.randomoddities.mixin;

import net.minecraft.core.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Entity.class)
public interface RandomOdditiesAirSupplyMixin {

    @Accessor("airMaxSupply")
        int getMaxAir();

}
