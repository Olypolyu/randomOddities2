package Olypolyu.randomoddities.items;

import Olypolyu.randomoddities.mixin.RandomOdditiesAirSupplyMixin;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.sound.SoundCategory;
import net.minecraft.core.world.World;

import java.util.Random;

public class ItemWindLamp extends Item {
    private final Random random = new Random();

    public ItemWindLamp(String key, int i, int Charge) {
        super(key, i);
        this.maxStackSize = 1;
        this.setMaxDamage(Charge);
	}

    @Override
    public ItemStack onUseItem(ItemStack itemstack, World world, EntityPlayer entityplayer) {

        if (entityplayer.yd > 0.5F) return itemstack;

        if ( itemstack.getMetadata() >= this.getMaxDamage() ) return itemstack;
            else itemstack.damageItem(1,entityplayer);

		entityplayer.fallDistance = 0;
        entityplayer.airSupply = ((RandomOdditiesAirSupplyMixin) entityplayer).getMaxAir();
		entityplayer.yd = 0.5F;

		world.playSoundEffect(entityplayer, SoundCategory.ENTITY_SOUNDS, entityplayer.x, entityplayer.y, entityplayer.z, "random.fizz", 1.5F, (1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F) * 0.7F);
        for (int i = 0; i < 8 + this.random.nextInt(7); i++){

            // shows bubbles if the player is underwater.
            if ( entityplayer.isInWater() ) {
                world.spawnParticle("bubble",
					entityplayer.x,
					entityplayer.y - 1,
					entityplayer.z,
					(this.random.nextFloat() - 0.5F),
					(this.random.nextFloat() - 0.5F),
					(this.random.nextFloat() - 0.5F),
					0, 0
				);

			} else {
				// shows smoke if the player is not underwater.
				world.spawnParticle("largesmoke",
					entityplayer.x,
					entityplayer.y - 1,
					entityplayer.z,
					(this.random.nextFloat() - 0.5F),
					(this.random.nextFloat() - 0.8F),
					(this.random.nextFloat() - 0.5F),
					0, 0
				);

			}
		}
        return itemstack;
	}

}
