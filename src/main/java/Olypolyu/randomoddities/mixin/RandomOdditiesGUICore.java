package Olypolyu.randomoddities.mixin;

import Olypolyu.randomoddities.interfaces.IRandomOdditiesGUIs;
import Olypolyu.randomoddities.tile.TileEntityVendingMachine;
import net.minecraft.core.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = EntityPlayer.class, remap = false)
public class RandomOdditiesGUICore implements IRandomOdditiesGUIs {
	@Override
	public void randomOddities$DisplayGUIVendingMachine(TileEntityVendingMachine tile) {
	}
}
