package com.cerbon.beb.platform.services;

import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.resources.ResourceLocation;

public interface IPlatformHelper {

    /**
     * Gets the name of the current platform
     *
     * @return The name of the current platform.
     */
    String getPlatformName();

    boolean isModLoaded(String modId);

    ItemModel getItemModel(ResourceLocation enchantId, ModelManager modelManager);
}
