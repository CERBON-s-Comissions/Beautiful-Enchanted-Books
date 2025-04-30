package com.cerbon.beb.neoforge;

import com.cerbon.beb.util.BEBConstants;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.model.standalone.StandaloneModelKey;

import java.util.HashMap;
import java.util.Map;

@Mod(BEBConstants.MOD_ID)
public class BeautifulEnchantedBooksNeo {

    public static final Map<ResourceLocation, StandaloneModelKey<ItemModel>> REGISTERED_MODELS = new HashMap<>();

    public BeautifulEnchantedBooksNeo() {}
}