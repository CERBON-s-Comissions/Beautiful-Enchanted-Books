package com.cerbon.beb.neoforge.event;

import com.cerbon.beb.BeautifulEnchantedBooks;
import com.cerbon.beb.neoforge.BeautifulEnchantedBooksNeo;
import com.cerbon.beb.util.BEBConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.TextureSlots;
import net.minecraft.client.renderer.item.BlockModelWrapper;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.ModelRenderProperties;
import net.minecraft.client.resources.model.BlockModelRotation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.model.standalone.SimpleUnbakedStandaloneModel;
import net.neoforged.neoforge.client.model.standalone.StandaloneModelKey;

import java.util.List;
import java.util.Set;

@EventBusSubscriber(modid = BEBConstants.MOD_ID, value = Dist.CLIENT)
public class BEBClientEventsNeo {

    @SubscribeEvent
    public static void onRegisterModel(ModelEvent.RegisterStandalone event) {
        Set<ResourceLocation> enchantIds = BeautifulEnchantedBooks.findCITs(Minecraft.getInstance().getResourceManager());

        BEBConstants.LOGGER.info("Found {} enchanted-book CITs", enchantIds.size());

        for (ResourceLocation id : enchantIds) {
            ResourceLocation model = id.withPrefix(BeautifulEnchantedBooks.MODEL_PREFIX + "/");

            StandaloneModelKey<ItemModel> key = new StandaloneModelKey<>(model::toString);
            BeautifulEnchantedBooksNeo.REGISTERED_MODELS.putIfAbsent(id, key);

            event.register(key, new SimpleUnbakedStandaloneModel<>(model, (resolvedModel, modelBaker) -> {
                TextureSlots textureSlots = resolvedModel.getTopTextureSlots();
                List<BakedQuad> list = resolvedModel.bakeTopGeometry(textureSlots, modelBaker, BlockModelRotation.X0_Y0).getAll();
                ModelRenderProperties modelRenderProperties = ModelRenderProperties.fromResolvedModel(modelBaker, resolvedModel, textureSlots);
                return new BlockModelWrapper(List.of(), list, modelRenderProperties);
            }));
        }
    }
}
