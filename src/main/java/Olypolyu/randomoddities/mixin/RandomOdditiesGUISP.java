package Olypolyu.randomoddities.mixin;

import Olypolyu.randomoddities.gui.GUIVendingMachine;
import Olypolyu.randomoddities.interfaces.IRandomOdditiesGUIs;
import Olypolyu.randomoddities.tile.TileEntityVendingMachine;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.EntityPlayerSP;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = EntityPlayerSP.class, remap = false)
public abstract class RandomOdditiesGUISP extends EntityPlayer implements IRandomOdditiesGUIs {

	@Shadow
	protected Minecraft mc;

	public RandomOdditiesGUISP(World world) {
		super(world);
	}

	@Override
	public void randomOddities$DisplayGUIVendingMachine(TileEntityVendingMachine tile) {
		mc.displayGuiScreen(new GUIVendingMachine(inventory, tile));
	}


}
