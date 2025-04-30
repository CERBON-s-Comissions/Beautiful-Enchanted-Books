package com.cerbon.beb;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;

import java.util.HashSet;
import java.util.Set;

public class BeautifulEnchantedBooks {

	public static final String MODEL_PREFIX = "item/enchanted_book";

	public static Set<ResourceLocation> findCITs(ResourceManager manager) {
		Set<ResourceLocation> variantIds = new HashSet<>();

		String folder = "models/" + MODEL_PREFIX;

		for (ResourceLocation resourceLocation : manager.listResources(folder, rl -> rl.getPath().endsWith(".json")).keySet()) {
			String path = resourceLocation.getPath();
			path = path.substring(folder.length()+1, path.length()-".json".length());
			variantIds.add(ResourceLocation.tryBuild(resourceLocation.getNamespace(), path));
		}
		return variantIds;
	}
}
