package Olypolyu.randomoddities;

import Olypolyu.randomoddities.blocks.RandomOdditiesBlocks;
import Olypolyu.randomoddities.gui.components.ComponentCoinCounter;
import Olypolyu.randomoddities.gui.components.ComponentHealthMeeter;
import Olypolyu.randomoddities.gui.components.ComponentShield;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.hud.*;
import net.minecraft.client.gui.options.components.BooleanOptionComponent;
import net.minecraft.client.gui.options.components.FloatOptionComponent;
import net.minecraft.client.gui.options.components.ShortcutComponent;
import net.minecraft.client.gui.options.data.OptionsPage;
import net.minecraft.client.gui.options.data.OptionsPages;
import net.minecraft.client.option.BooleanOption;
import net.minecraft.client.option.FloatOption;
import net.minecraft.client.option.GameSettings;
import net.minecraft.client.option.Option;
import turniplabs.halplibe.util.ClientStartEntrypoint;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

public class RandomOdditiesClient implements ClientStartEntrypoint {
	public static HudComponent COIN_COUNTER;
	public static HudComponent SHIELD_GLYPH;
	public static HudComponent HEALTH_MEETER;

	private static File optionsFile;
	private static final HashMap<String, Option> RandomOdditiesOptions = new HashMap<>();
	public static Option getOption(String key) {return RandomOdditiesOptions.get("randomoddities."+key);}
	private static void putOption(Option option) {RandomOdditiesOptions.put(option.name, option);}

	@Override
	public void beforeClientStart() {
		optionsFile = new File("./random_oddities_options.txt");
	}

	@Override
	public void afterClientStart() {
    	COIN_COUNTER = HudComponents.register(new ComponentCoinCounter("randomoddities.coin_counter", new SnapLayout(HudComponents.HOTBAR, ComponentAnchor.BOTTOM_RIGHT, ComponentAnchor.BOTTOM_LEFT)));
    	SHIELD_GLYPH = HudComponents.register(new ComponentShield("randomoddities.shield_glyph", new SnapLayout(COIN_COUNTER, ComponentAnchor.BOTTOM_RIGHT, ComponentAnchor.BOTTOM_LEFT)));
    	HEALTH_MEETER = HudComponents.register(new ComponentHealthMeeter("randomoddities.health_meeter", new AbsoluteLayout(0.5f, 0.0f, ComponentAnchor.TOP_CENTER)));

		GameSettings gameSettings = Minecraft.getMinecraft(Minecraft.class).gameSettings;
		OptionsPage optionsPage = new OptionsPage("options.randomoddities.title", RandomOdditiesBlocks.trampoline.getDefaultStack());

		// declare options
		putOption(new BooleanOption(gameSettings, "randomoddities.health_meeter", true));

		// insert options
		optionsPage.withComponent(new BooleanOptionComponent((BooleanOption) getOption("health_meeter")));

		OptionsPages.register(optionsPage);
		loadOptions();
	}

	public static void saveOptions() {
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(optionsFile));
			for (String key : RandomOdditiesOptions.keySet()) {
				writer.println(key + ":" + RandomOdditiesOptions.get(key).getValueString());
			}
			writer.close();
		}
		catch (Exception ignored){}
	}

	public static void loadOptions() {
		try {
			if (optionsFile.exists()) {
				BufferedReader reader = new BufferedReader(new FileReader(optionsFile));
				String line;
				while ((line = reader.readLine()) != null) {
					String[] keyValuePair = line.split(":");
					RandomOdditiesOptions.get(keyValuePair[0]).parse(keyValuePair[1]);
				}
			}
		} catch (Exception ignored) {}
	}
}
