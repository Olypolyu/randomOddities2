package Olypolyu.randomoddities.items;

import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;

public class ItemModelWindLamp extends ItemModelStandard {
    protected IconCoordinate emptyWindBottle = TextureRegistry.getTexture("randomoddities:item/emptyWindBottle");
    protected IconCoordinate windBottle = TextureRegistry.getTexture("randomoddities:item/windBottle");

    public ItemModelWindLamp(Item item, String namespace) {
        super(item, namespace);
    }

    public IconCoordinate getIcon(Entity entity, ItemStack itemStack) {
        if (itemStack.getMetadata() == itemStack.getMaxDamage()) {
            return emptyWindBottle;
        }
        return windBottle;
    }
}
