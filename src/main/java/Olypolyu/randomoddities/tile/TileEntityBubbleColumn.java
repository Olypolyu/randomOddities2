package Olypolyu.randomoddities.tile;

import Olypolyu.randomoddities.mixin.RandomOdditiesAirSupplyMixin;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.util.phys.AABB;

import java.util.List;
import java.util.Random;

public class TileEntityBubbleColumn extends TileEntity {
    private final Random random = new Random();
    private int columnLength;

    private void calculateColumnLength() {
        this.columnLength = 0;
        boolean reachedTop = false;

        while (!reachedTop) {
            Material blockMaterial = this.worldObj.getBlockMaterial( this.x, this.y + 1 + this.columnLength, this.z );
            if ( blockMaterial == Block.fluidWaterStill.blockMaterial || blockMaterial == Block.fluidWaterFlowing.blockMaterial )
                this.columnLength = this.columnLength + 1;

            else reachedTop = true;
            }
        }

	@Override
	public void tick() {
        calculateColumnLength();

        if (this.columnLength > 1) {
            // get entities within bounding box, then yeet.
            List<Entity> entitiesInBoundingBox = this.worldObj.getEntitiesWithinAABB(Entity.class, AABB.getBoundingBox(this.x, this.y, this.z, this.x + 1, this.y + 0.6 + this.columnLength, this.z + 1));

            for (int j = 0; j < entitiesInBoundingBox.size(); ++j) {
                Entity entity = entitiesInBoundingBox.get(j);

                // things related to players
                if ( entity instanceof EntityPlayer) {

                    // slowly regenerate air points
                    if ( entity.airSupply < ((RandomOdditiesAirSupplyMixin) entity).getMaxAir() - 2 )
                        entity.airSupply = entity.airSupply + 2;

                    // don't lift the player if they are sneaking
                    if (entity.isSneaking())
                        break;

                    }

                // yeet!
                if (entity.yd < 0.8) {
                    entity.yd = entity.yd + 0.25;

                    float Strength = (float) ((this.columnLength - entity.distanceTo(this.x, this.y, this.z) + 1) * 0.10);
                    if (Strength > 1) entity.yd = entity.yd + Strength;
                    }

                }

            int i;
            if (this.columnLength > 0)
                for (i = 0; i < random.nextInt(this.columnLength); i++)
                    this.worldObj.spawnParticle("bubble",
                            this.x + this.random.nextFloat(),
                            this.y + 1,
                            this.z + this.random.nextFloat(),
                            0,
                            this.columnLength,
                            0);
            }
        }
}
