package com.cerbon.beb.neoforge.platform;

import com.cerbon.beb.platform.services.IPlatformHelper;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;

public class NeoPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "NeoForge";
    }

    @Override
    public BakedModel getModel(ResourceLocation fabricVariantId, ModelResourceLocation neoForgeVariantId, ModelManager modelManager) {
        return modelManager.getModel(neoForgeVariantId);
    }
}
