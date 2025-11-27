package com.cerbon.beb.platform.services;

import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;

public interface IPlatformHelper {

    /**
     * Gets the name of the current platform
     *
     * @return The name of the current platform.
     */
    String getPlatformName();

    boolean isModLoaded(String modId);

    BakedModel getModel(ResourceLocation fabricVariantId, ModelResourceLocation neoForgeVariantId, ModelManager modelManager);
}
