package com.cerbon.beb.fabric.platform;

import com.cerbon.beb.platform.services.IPlatformHelper;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public BakedModel getModel(ResourceLocation fabricVariantId, ModelResourceLocation neoForgeVariantId, ModelManager modelManager) {
        return modelManager.getModel(fabricVariantId);
    }
}
