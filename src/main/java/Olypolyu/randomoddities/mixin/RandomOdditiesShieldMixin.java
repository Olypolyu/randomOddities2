package Olypolyu.randomoddities.mixin;

import Olypolyu.randomoddities.RandomOdditiesCore;
import Olypolyu.randomoddities.items.ItemShield;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.monster.EntityMonster;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.util.phys.BoundingVolume;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(value = EntityPlayer.class, remap = false)

public abstract class RandomOdditiesShieldMixin extends EntityLiving {
	public RandomOdditiesShieldMixin(World world) {
		super(world);
	}

	@Shadow
	public InventoryPlayer inventory;

	@Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
	public void injectHurt(Entity attacker, int damage, DamageType type, CallbackInfoReturnable<Boolean> ci) {
		ItemStack stack = inventory.mainInventory[inventory.currentItem];
		EntityPlayer player = ((EntityPlayer) (Object) this);

		if (stack != null && attacker != null) {
			if (stack.getItem() instanceof ItemShield && stack.getData().getBoolean("active")) {
				ItemShield shield = (ItemShield) stack.getItem();

				final float range = 5;
				AABB volume = new AABB(player.x - (range/2), player.y - (range/2), player.z - (range/2), player.x + (range/2), player.y + (range/2), player.z + (range/2));
				List<Entity> enemies = player.world.getEntitiesWithinAABB(EntityMonster.class, volume);
				enemies.forEach(
					entity -> {
						if (entity != attacker) {
							shield.doCustomEffect(player.world, player, entity, damage, stack);
							entity.hurt(player, (int) ((float) damage - (damage * shield.damageMod)), type);
						}
					}
				);

				super.hurt(attacker, (int) ((float) damage - (damage * shield.protectionMod)), type);
				attacker.hurt(player, (int) ((float) damage - (damage * shield.damageMod)), type);
				stack.damageItem((int) ((float)(damage/20F) * 10), player);

				shield.doCustomEffect(player.world, player, attacker, damage, stack);
				ci.setReturnValue(true);
			}
		}
	}

}
