package Olypolyu.randomoddities;

import com.google.common.collect.ImmutableMap;
import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class RandomOdditiesMixinPlugin implements IMixinConfigPlugin {

	private static final Supplier<Boolean> TRUE = () -> true;

	private static final String AETHER_STRING = "aether";
	private static final String BONUS_BLOCKS_STRING = "bonusblocks";

	private static final Map<String, Supplier<Boolean>> CONDITIONS = ImmutableMap.<String, Supplier<Boolean>> builder()
		.put("Olypolyu.randomoddities.mixin.RandomOdditiesTrampolineMixin",		() -> !FabricLoader.getInstance().isModLoaded(AETHER_STRING))
		.put("Olypolyu.randomoddities.mixin.RandomOdditiesTrampolineMixin",		() -> !FabricLoader.getInstance().isModLoaded(BONUS_BLOCKS_STRING))
		.build();

	@Override
	public void onLoad(String mixinPackage) {
		RandomOdditiesCore.info(AETHER_STRING + " found, managing mixins.");
	}

	@Override
	public String getRefMapperConfig() {
		return null;
	}

	@Override
	public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
		return CONDITIONS.getOrDefault(mixinClassName, TRUE).get();
	}

	@Override
	public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
	}

	@Override
	public List<String> getMixins() {
		return null;
	}

	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
	}

	@Override
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
	}

}
