package com.cerbon.beb;

import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;

import java.util.HashSet;
import java.util.Set;

public class BeautifulEnchantedBooks {

	public static final String MODEL_PREFIX = "item/enchanted_book";

	public static Set<Identifier> findCITs(ResourceManager manager) {
		Set<Identifier> variantIds = new HashSet<>();

		String folder = "models/" + MODEL_PREFIX;

		for (Identifier resourceLocation : manager.listResources(folder, rl -> rl.getPath().endsWith(".json")).keySet()) {
			String path = resourceLocation.getPath();
			path = path.substring(folder.length()+1, path.length()-".json".length());
			variantIds.add(Identifier.tryBuild(resourceLocation.getNamespace(), path));
		}
		return variantIds;
	}
}
