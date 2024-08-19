package com.cerbon.beb;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//https://github.com/Estecka/mc-Enchants-CIT
public class BeautifulEnchantedBooks {

	/**
	 * Maps Enchantment id to the corresponding Model Id
	 */
	private static final @NotNull Map<ResourceLocation, ModelResourceLocation> REGISTERED_MODEL_IDS = new HashMap<>();
	private static final @NotNull Map<ResourceLocation, ResourceLocation> REGISTERED_MODEL_IDS_FABRIC = new HashMap<>();
	public static final String MODEL_PREFIX = "item/enchanted_book";

	public static void registerModel(ResourceLocation enchantId, ModelResourceLocation model) {
		REGISTERED_MODEL_IDS.put(enchantId, model);
	}

	public static ModelResourceLocation ofVariant(ResourceLocation variantId) {
		return REGISTERED_MODEL_IDS.get(variantId);
	}

	public static void registerModelFabric(ResourceLocation enchantId, ResourceLocation model) {
		REGISTERED_MODEL_IDS_FABRIC.put(enchantId, model);
	}

	public static ResourceLocation ofVariantFabric(ResourceLocation variantId) {
		return REGISTERED_MODEL_IDS_FABRIC.get(variantId);
	}

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
