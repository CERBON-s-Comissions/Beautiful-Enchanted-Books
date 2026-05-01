package com.cerbon.beb.fabric;

import com.cerbon.beb.BeautifulEnchantedBooks;
import com.cerbon.beb.fabric.mixin.ICuboidItemModelWrapper;
import com.cerbon.beb.util.BEBConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ExtraModelKey;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.PreparableModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.SimpleUnbakedExtraModel;
import net.minecraft.client.renderer.block.dispatch.BlockModelRotation;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.ModelRenderProperties;
import net.minecraft.client.resources.model.geometry.QuadCollection;
import net.minecraft.client.resources.model.sprite.TextureSlots;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import org.joml.Matrix4f;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class BeautifulEnchantedBooksFabric implements ClientModInitializer, PreparableModelLoadingPlugin<Set<Identifier>>, PreparableModelLoadingPlugin.DataLoader<Set<Identifier>> {

    public static final Map<Identifier, ExtraModelKey<ItemModel>> REGISTERED_MODELS = new HashMap<>();

    @Override
    public void onInitializeClient() {
        PreparableModelLoadingPlugin.register(this, this);
    }

    @Override
    public void initialize(Set<Identifier> enchantIds, ModelLoadingPlugin.Context pluginContext) {
        BEBConstants.LOGGER.info("Found {} enchanted-book CITs", enchantIds.size());

        for (Identifier id : enchantIds) {
            Identifier model = id.withPrefix(BeautifulEnchantedBooks.MODEL_PREFIX + "/");

            ExtraModelKey<ItemModel> key = ExtraModelKey.create(model::toString);
            REGISTERED_MODELS.putIfAbsent(id, key);

            pluginContext.addModel(key, new SimpleUnbakedExtraModel<>(model, (resolvedModel, modelBaker) -> {
                TextureSlots textureSlots = resolvedModel.getTopTextureSlots();
                QuadCollection list = resolvedModel.bakeTopGeometry(textureSlots, modelBaker, BlockModelRotation.IDENTITY);
                ModelRenderProperties modelRenderProperties = ModelRenderProperties.fromResolvedModel(modelBaker, resolvedModel, textureSlots);
                return ICuboidItemModelWrapper.invokeConstructor(List.of(), list, modelRenderProperties, new Matrix4f());
            }));
        }
    }

    @Override
    public CompletableFuture<Set<Identifier>> load(PreparableReloadListener.SharedState resourceReloaderStore, Executor executor) {
        return CompletableFuture.supplyAsync(()-> BeautifulEnchantedBooks.findCITs(resourceReloaderStore.resourceManager()), executor);
    }
}