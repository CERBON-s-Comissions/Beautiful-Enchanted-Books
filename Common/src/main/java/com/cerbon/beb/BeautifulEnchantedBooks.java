package com.cerbon.beb;

import com.cerbon.beb.util.MiscUtils;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//https://github.com/Estecka/mc-Enchants-CIT
public class BeautifulEnchantedBooks {

	/**
	 * Maps Enchantment id to the corresponding Model Id
	 */
	private static Map<ResourceLocation, ModelResourceLocation> REGISTERED_MODEL_IDS_ML;
	private static Map<ResourceLocation, ResourceLocation> REGISTERED_MODEL_IDS_RL;

	public static final String MODEL_PREFIX = "item/enchanted_book";

	public static void registerModel(ResourceLocation enchantId, ModelResourceLocation model) {
		if (MiscUtils.getPlatformName().equals("Fabric")) {
			if (REGISTERED_MODEL_IDS_RL == null)
				REGISTERED_MODEL_IDS_RL = new HashMap<>();

			REGISTERED_MODEL_IDS_RL.put(enchantId, model.id());
		}
		else {
			if (REGISTERED_MODEL_IDS_ML == null)
				REGISTERED_MODEL_IDS_ML = new HashMap<>();

			REGISTERED_MODEL_IDS_ML.put(enchantId, model);
		}
	}

	public static ModelResourceLocation ofVariantMl(ResourceLocation variantId) {
		return REGISTERED_MODEL_IDS_ML.get(variantId);
	}

	public static ResourceLocation ofVariantRl(ResourceLocation variantId) {
		return REGISTERED_MODEL_IDS_RL.get(variantId);
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
