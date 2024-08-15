package com.cerbon.beb.mixin;

import com.cerbon.beb.util.mixin.IModelManagerMixin;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(ModelManager.class)
public abstract class ModelManagerMixin implements IModelManagerMixin {

    @Shadow private Map<ResourceLocation, BakedModel> bakedRegistry;

    @Override
    public BakedModel getModel(ResourceLocation modelLocation) {
        return bakedRegistry.get(modelLocation);
    }
}
