package com.cerbon.beb.neoforge.platform;

import com.cerbon.beb.platform.services.IPlatformHelper;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModList;

public class NeoPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "NeoForge";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public BakedModel getModel(ResourceLocation fabricVariantId, ModelResourceLocation neoForgeVariantId, ModelManager modelManager) {
        return modelManager.getModel(neoForgeVariantId);
    }
}
