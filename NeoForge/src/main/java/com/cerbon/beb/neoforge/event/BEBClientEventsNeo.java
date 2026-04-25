package com.cerbon.beb.neoforge.event;

import com.cerbon.beb.BeautifulEnchantedBooks;
import com.cerbon.beb.neoforge.BeautifulEnchantedBooksNeo;
import com.cerbon.beb.util.BEBConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.dispatch.BlockModelRotation;
import net.minecraft.client.renderer.item.CuboidItemModelWrapper;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.ModelRenderProperties;
import net.minecraft.client.resources.model.geometry.QuadCollection;
import net.minecraft.client.resources.model.sprite.TextureSlots;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.model.standalone.SimpleUnbakedStandaloneModel;
import net.neoforged.neoforge.client.model.standalone.StandaloneModelKey;
import org.joml.Matrix4f;

import java.util.List;
import java.util.Set;

@EventBusSubscriber(modid = BEBConstants.MOD_ID, value = Dist.CLIENT)
public class BEBClientEventsNeo {

    @SubscribeEvent
    public static void onRegisterModel(ModelEvent.RegisterStandalone event) {
        Set<Identifier> enchantIds = BeautifulEnchantedBooks.findCITs(Minecraft.getInstance().getResourceManager());

        BEBConstants.LOGGER.info("Found {} enchanted-book CITs", enchantIds.size());

        for (Identifier id : enchantIds) {
            Identifier model = id.withPrefix(BeautifulEnchantedBooks.MODEL_PREFIX + "/");

            StandaloneModelKey<ItemModel> key = new StandaloneModelKey<>(model::toString);
            BeautifulEnchantedBooksNeo.REGISTERED_MODELS.putIfAbsent(id, key);

            event.register(key, new SimpleUnbakedStandaloneModel<>(model, (resolvedModel, modelBaker, debugName) -> {
                TextureSlots textureSlots = resolvedModel.getTopTextureSlots();
                QuadCollection list = resolvedModel.bakeTopGeometry(textureSlots, modelBaker, BlockModelRotation.IDENTITY);
                ModelRenderProperties modelRenderProperties = ModelRenderProperties.fromResolvedModel(modelBaker, resolvedModel, textureSlots);
                return new CuboidItemModelWrapper(List.of(), list, modelRenderProperties, new Matrix4f());
            }));
        }
    }
}
