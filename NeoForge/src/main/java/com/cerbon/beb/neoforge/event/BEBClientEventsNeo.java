package com.cerbon.beb.neoforge.event;

import com.cerbon.beb.BeautifulEnchantedBooks;
import com.cerbon.beb.util.BEBConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ModelEvent;

import java.util.Set;

@EventBusSubscriber(modid = BEBConstants.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BEBClientEventsNeo {

    @SubscribeEvent
    public static void onRegisterModel(ModelEvent.RegisterAdditional event) {
        Set<ResourceLocation> enchantIds = BeautifulEnchantedBooks.findCITs(Minecraft.getInstance().getResourceManager());

        BEBConstants.LOGGER.info("Found {} enchanted-book CITs", enchantIds.size());

        for (ResourceLocation id : enchantIds) {
            ResourceLocation model = id.withPrefix(BeautifulEnchantedBooks.MODEL_PREFIX + "/");
            BeautifulEnchantedBooks.registerModel(id, model);
            event.register(ModelResourceLocation.standalone(model));
        }
    }
}
