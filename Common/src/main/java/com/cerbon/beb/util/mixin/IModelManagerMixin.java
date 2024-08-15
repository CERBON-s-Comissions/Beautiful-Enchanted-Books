package com.cerbon.beb.util.mixin;

import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;

public interface IModelManagerMixin {
    BakedModel getModel(ResourceLocation modelLocation);
}
