package Olypolyu.randomoddities.mixin;

import net.minecraft.core.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Entity.class)
public interface RandomOdditiesEntityMixin {

    @Accessor("fallDistance")
        float getFallDistance();

    @Accessor("fallDistance")
        void setFallDistance(float value);

    @Accessor("airMaxSupply")
        int getMaxAir();
}
