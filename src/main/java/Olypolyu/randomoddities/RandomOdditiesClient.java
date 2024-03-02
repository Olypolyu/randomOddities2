package Olypolyu.randomoddities;

import Olypolyu.randomoddities.gui.components.ComponentCoinCounter;
import net.minecraft.client.gui.hud.ComponentAnchor;
import net.minecraft.client.gui.hud.HudComponent;
import net.minecraft.client.gui.hud.HudComponents;
import net.minecraft.client.gui.hud.SnapLayout;
import turniplabs.halplibe.util.ClientStartEntrypoint;

public class RandomOdditiesClient implements ClientStartEntrypoint {
	public static HudComponent COIN_COUNTER = HudComponents.register(new ComponentCoinCounter("randomoddities.coin_counter", new SnapLayout(HudComponents.HOTBAR, ComponentAnchor.BOTTOM_RIGHT, ComponentAnchor.BOTTOM_LEFT)));

	@Override
	public void beforeClientStart() {
	}

	@Override
	public void afterClientStart() {

	}
}
