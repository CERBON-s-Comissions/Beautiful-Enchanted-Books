package com.cerbon.beb.fabric;

import com.cerbon.beb.BeautifulEnchantedBooks;
import com.cerbon.beb.util.BEBConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.PreparableModelLoadingPlugin;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class BeautifulEnchantedBooksFabric implements ClientModInitializer, PreparableModelLoadingPlugin<Set<ResourceLocation>>, PreparableModelLoadingPlugin.DataLoader<Set<ResourceLocation>> {

    @Override
    public void onInitializeClient() {
        PreparableModelLoadingPlugin.register(this, this);
    }

    @Override
    public void onInitializeModelLoader(Set<ResourceLocation> enchantIds, ModelLoadingPlugin.Context pluginContext) {
        BEBConstants.LOGGER.info("Found {} enchanted-book CITs", enchantIds.size());

        for (ResourceLocation id : enchantIds) {
            ResourceLocation model = id.withPrefix(BeautifulEnchantedBooks.MODEL_PREFIX + "/");
            BeautifulEnchantedBooks.registerModelFabric(id, model);
            pluginContext.addModels(model);
        }
    }

    @Override
    public CompletableFuture<Set<ResourceLocation>> load(ResourceManager manager, Executor executor) {
        return CompletableFuture.supplyAsync(()-> BeautifulEnchantedBooks.findCITs(manager), executor);
    }
}