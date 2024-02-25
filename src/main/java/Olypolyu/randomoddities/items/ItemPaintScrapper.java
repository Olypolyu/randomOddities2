package Olypolyu.randomoddities.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.fx.EntityDiggingFX;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

public class ItemPaintScrapper extends Item {

    public ItemPaintScrapper(int i) {
        super(i);
        this.maxStackSize = 1;
        this.bFull3D = true;
        this.setMaxDamage(24);
    }

	// TODO: use a hashmap like the paintbrush does.

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, Side l, double xPlaced, double yPlaced) {
        int block = world.getBlockId(i, j, k);
        int meta = world.getBlockMetadata(i, j, k);

        boolean flag = false;

        switch (block) {

            case 51: // planks
            case 81: // fences
                world.setBlockWithNotify(i, j, k, block - 1);
                flag = true;
                break;

            case 171: // wooden stairs
                world.setBlockAndMetadataWithNotify(i, j, k, 160, meta % 16);
                flag = true;
                break;

            case 154: // wooden slab
                world.setBlockAndMetadataWithNotify(i, j, k, 140, meta % 16);
                flag = true;
                break;

            case 681: // chests
            case 91: // fence gates
                world.setBlockRaw(i, j, k, block - 1);
                world.setBlockMetadataWithNotify(i, j, k, meta % 16);
                flag = true;
                break;

        }

        if (flag) {
            itemstack.damageItem(1, entityplayer);

			Minecraft mc = Minecraft.getMinecraft(Minecraft.class);
			for (int particle = 0; particle < 24; particle++) {
				mc.effectRenderer.addEffect(
					new EntityDiggingFX(
						world,
						i + Block.getBlock(block).maxX + Block.getBlock(block).minX - world.rand.nextDouble(),
						j + Block.getBlock(block).maxY + Block.getBlock(block).minY - world.rand.nextDouble(),
						k + Block.getBlock(block).maxZ + Block.getBlock(block).minZ - world.rand.nextDouble(),
						0,
						0,
						0,
						Block.getBlock(block),
						0,
						0
					)
				);
			}
            //world.playSoundEffect((i + 0.5F), (j + 0.5F), (k + 0.5F), Block.getBlock(block).stepSound.func_1145_d(), ( Block.getBlock(block).stepSound.getVolume() + 1.0F) / 2.0F, Block.getBlock(block).stepSound.getPitch() * 0.8F);
            return true;
        }

        return false;
    }

}
