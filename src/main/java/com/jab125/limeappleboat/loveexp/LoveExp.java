package com.jab125.limeappleboat.loveexp;

import com.jab125.limeappleboat.loveexp.api.LoveExpApi;
import com.jab125.limeappleboat.loveexp.api.LoveExpApiRegistry;
import com.jab125.limeappleboat.loveexp.util.LoveExpFormat;
import com.jab125.limeappleboat.loveexp.util.ConfigLoader;
import com.jab125.limeappleboat.loveexp.util.Util;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoveExp implements ModInitializer {
	public static final String MODID = "loveexp";
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger(MODID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Hello Fabric world!");;
		FabricLoader.getInstance().getEntrypointContainers("loveexp", LoveExpApi.class).forEach(entrypoint -> {
			entrypoint.getEntrypoint().register();
		});
		Util.REGISTERED_MOBS.put("minecraft:warden", new LoveExpFormat(90,0));
		LoveExpApiRegistry.freeze();
		ConfigLoader.loadConfigs(FabricLoader.getInstance().getConfigDir().toFile());
		ConfigLoader.loadMobs(FabricLoader.getInstance().getConfigDir().toFile());
	}
}
