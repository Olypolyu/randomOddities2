package Olypolyu.randomoddities.mixin;

import net.minecraft.core.block.Block;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.tool.ItemToolPickaxe;
import net.minecraft.core.sound.SoundCategory;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Item.class, remap = false)
public abstract class RandomOdditiesPickaxeMixin {

	// This is cursed. As for the reason I have to do this, onItemUse() is not overridden and is inherited. I cannot use @Inject there.
	// So, if I want to this I have to either to use @Override and break every other mod or... this.
	@SuppressWarnings({"ConstantValue", "DataFlowIssue"})
	@Inject(method = "onItemUse", at = @At("HEAD"), cancellable = true)
	public void injectOnItemUSe(ItemStack itemstack, EntityPlayer entityplayer, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
		if (((Item)(Object)this) instanceof ItemToolPickaxe) {
			int torchAt;

			boolean hasTorches = false;
			for (torchAt = 0; torchAt < entityplayer.inventory.getSizeInventory(); torchAt++) {
				if (entityplayer.inventory.getStackInSlot(torchAt) != null && entityplayer.inventory.getStackInSlot(torchAt).itemID == Block.torchCoal.id) {
					hasTorches = true;
					break;
				}
			}

			if (!hasTorches) return;

			switch (side) {
				case NONE:
					return;

				case BOTTOM:
					--blockY;
					break;

				case TOP:
					++blockY;
					break;

				case NORTH:
					--blockZ;
					break;

				case SOUTH:
					++blockZ;
					break;

				case WEST:
					--blockX;
					break;

				case EAST:
					++blockX;
					break;
			}

			if (world.canBlockBePlacedAt(Block.torchCoal.id, blockX, blockY, blockZ, false, side)) {
				entityplayer.inventory.getStackInSlot(torchAt).consumeItem(entityplayer);
				if (entityplayer.inventory.getStackInSlot(torchAt).stackSize < 1) {
					entityplayer.inventory.setInventorySlotContents(torchAt, null);
				}

				world.setBlockWithNotify(blockX, blockY, blockZ, Block.torchCoal.id);
				Block.torchCoal.onBlockPlaced(world, blockX, blockY, blockZ, side, entityplayer, yPlaced);
				world.playSoundEffect(entityplayer, SoundCategory.WORLD_SOUNDS, (blockX + 0.5F), (blockY + 0.5F), (blockZ + 0.5F), "step.wood", 1.0F, 1.0F);
				callbackInfoReturnable.setReturnValue(true);
			}
		}
	}

}
