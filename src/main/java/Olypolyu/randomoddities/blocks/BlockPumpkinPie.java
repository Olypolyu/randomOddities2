package Olypolyu.randomoddities.blocks;

import net.minecraft.core.block.BlockCake;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.World;

import static Olypolyu.randomoddities.RandomOdditiesAssets.pieEatenTex;

public class BlockPumpkinPie extends BlockCake {

    public BlockPumpkinPie(String key, int i) {
            super(key, i);
            this.setTicking(true);
    }

    public void setBlockBoundsBasedOnState(World world, int i, int j, int k) {
        int slice = world.getBlockMetadata(i, j, k);
        float f = 0.0625F;
        float f1 = (1 + slice * 4) / 16.0F;
        this.setBlockBounds(f1, 0.0F, f, 1.0F - f, 0.3125F ,1.0F - f);
    }

    public void setBlockBoundsForItemRender() {
        float f = 0.0625F;
        this.setBlockBounds(f, 0.0F, f, 1.0F - f, 0.3125F, 1.0F - f);
    }

    public AABB getSelectedBoundingBoxFromPool(World world, int i, int j, int k) {
        int slice = world.getBlockMetadata(i, j, k);
        float f = 0.0625F;
        float f1 = (1 + slice * 4) / 16.0F;
        float f2 = 0.3125F;
        return AABB.getBoundingBoxFromPool((float)i + f1, j, (float)k + f, (float)(i + 1) - f, (float)j + f2, (float)(k + 1) - f);
    }

    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer) {
        this.eatSlice(world, i, j, k, entityplayer);
        return true;
    }

    private void eatSlice(World world, int i, int j, int k, EntityPlayer entityplayer) {
        if (entityplayer.getHealth() < 20) {
            entityplayer.heal(5);
            int l = world.getBlockMetadata(i, j, k) + 1;
            if (l >= 4) {
                world.setBlockWithNotify(i, j, k, 0);
            } else {
                world.setBlockMetadataWithNotify(i, j, k, l);
                world.markBlockNeedsUpdate(i, j, k);
            }
        }

    }

    public int getBlockTextureFromSideAndMetadata(int i, int j) {
        return j > 0 && i == 4 ? pieEatenTex : this.atlasIndices[i];
    }

}
