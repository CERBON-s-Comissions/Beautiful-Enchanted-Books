package com.cerbon.beb.neoforge.platform;

import com.cerbon.beb.neoforge.BeautifulEnchantedBooksNeo;
import com.cerbon.beb.platform.services.IPlatformHelper;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.resources.ResourceLocation;

public class NeoPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "NeoForge";
    }

    @Override
    public ItemModel getItemModel(ResourceLocation enchantId, ModelManager modelManager) {
        return modelManager.getStandaloneModel(BeautifulEnchantedBooksNeo.REGISTERED_MODELS.get(enchantId));
    }
}
