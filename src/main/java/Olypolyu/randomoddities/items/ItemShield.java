package Olypolyu.randomoddities.items;

import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

public class ItemShield extends Item {

	public final int duration = 100;
	public final float damageMod;
	public final float protectionMod;
	public final float knockBack;

	public ItemShield(String key, int id, int durability, float damageMod, float protectionMod, float knockBack) {
		super(key, id);
		this.maxStackSize = 1;
		this.damageMod = damageMod;
		this.protectionMod = protectionMod;
		this.knockBack = knockBack;
		this.setMaxDamage(durability);
	}

	public void doCustomEffect(World world, EntityPlayer player, Entity attacker, int damage, ItemStack itemStack) {
		double dx = player.x - attacker.x;
		double dy;
		double dz = player.z - attacker.z;
		double magnitude = Math.sqrt(dx * dx + dz * dz);

		dx /= magnitude;
		dz /= magnitude;
		attacker.push(-dx * this.knockBack, this.knockBack * 0.60F, -dz * this.knockBack);

		world.playSoundAtEntity(player, attacker, ("randomoddities.trampoline_bounce"), 1.0F, 0.9F + world.rand.nextFloat());
		final float width = 1.0f;
		for (int i = 0; i < 20; ++i) {
			dx = world.rand.nextGaussian() * 0.02;
			dy = world.rand.nextGaussian() * 0.02;
			dz = world.rand.nextGaussian() * 0.02;
			world.spawnParticle(
				"snowshovel",
				attacker.x + (double) (world.rand.nextFloat() * width * 2.0F) - width,
				attacker.y + (attacker.bbHeight / 2) + (double) (world.rand.nextFloat() * width),
				attacker.z + (double) (world.rand.nextFloat() * width * 2.0F) - width,
				dx, dy, dz, 0, 0
			);
		}
	}

	@Override
	public void inventoryTick(ItemStack itemstack, World world, Entity entity, int i, boolean flag) {
		if (itemstack.getData().getBoolean("active")) {
			int ticks = itemstack.getData().getInteger("ticks");
			ticks = Math.min(ticks, duration);

			int angle = itemstack.getData().getIntegerOrDefault("animation_data", 0) + 15;
			angle %= 360;

			entity.xd *= 0.95F;
			entity.zd *= 0.95;
			entity.speed = 0.02F;
			entity.setSneaking(true);

			((EntityPlayer) entity).inventory.setCurrentItem(itemstack, true);
			if (((EntityPlayer)entity).inventory.getCurrentItem() != itemstack) {
				itemstack.getData().putBoolean("active", false);
				return;
			}

			float mod = (float) (ticks * 0.125 / duration);
			world.spawnParticle("flame", entity.x + (Math.cos(Math.toRadians(angle))), entity.y - (angle*entity.bbHeight/360), entity.z + (Math.sin(Math.toRadians(angle))), Math.cos(Math.toRadians(angle)) * mod, 0, Math.sin(Math.toRadians(angle)) * mod, 0, 0);
			itemstack.getData().putInt("animation_data", angle);
			angle = (angle + 180) % 360;
			world.spawnParticle("flame", entity.x + (Math.cos(Math.toRadians(angle))), entity.y + (angle*entity.bbHeight/360) - entity.bbHeight, entity.z + (Math.sin(Math.toRadians(angle))), Math.cos(Math.toRadians(angle)) * mod, 0, Math.sin(Math.toRadians(angle)) * mod, 0, 0);

			world.spawnParticle("smoke", entity.x + world.rand.nextGaussian(), entity.y + world.rand.nextGaussian(), entity.z + world.rand.nextGaussian(), 0.0, 0.0, 0.0, 0, 0);

			if (ticks > 0) {
				itemstack.getData().putInt("ticks", ticks - 1);
			} else {
				itemstack.getData().putBoolean("active", false);
			}
		} else {
			itemstack.getData().putInt("animation_data", 0);

			int ticks = itemstack.getData().getInteger("ticks");
			itemstack.getData().putInt("ticks", ticks + 1);
		}
	}

	@Override
	public ItemStack onUseItem(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		if (itemstack.getData().getInteger("ticks") >= duration * 1.25) itemstack.getData().putBoolean("active", true);
		return itemstack;
	}
}
