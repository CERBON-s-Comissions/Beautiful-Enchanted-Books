package com.cerbon.beb.fabric.platform;

import com.cerbon.beb.fabric.BeautifulEnchantedBooksFabric;
import com.cerbon.beb.platform.services.IPlatformHelper;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.resources.ResourceLocation;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public ItemModel getItemModel(ResourceLocation enchantId, ModelManager modelManager) {
        return modelManager.getModel(BeautifulEnchantedBooksFabric.REGISTERED_MODELS.get(enchantId));
    }
}
