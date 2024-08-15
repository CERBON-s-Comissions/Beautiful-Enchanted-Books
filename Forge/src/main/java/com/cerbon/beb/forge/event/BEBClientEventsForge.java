package com.cerbon.beb.forge.event;

import com.cerbon.beb.BeautifulEnchantedBooks;
import com.cerbon.beb.util.BEBConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

@Mod.EventBusSubscriber(modid = BEBConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BEBClientEventsForge {

    @SubscribeEvent
    public static void onRegisterModel(ModelEvent.RegisterAdditional event) {
        Set<ResourceLocation> enchantIds = BeautifulEnchantedBooks.findCITs(Minecraft.getInstance().getResourceManager());

        BEBConstants.LOGGER.info("Found {} enchanted-book CITs", enchantIds.size());

        for (ResourceLocation id : enchantIds) {
            ResourceLocation model = id.withPrefix(BeautifulEnchantedBooks.MODEL_PREFIX + "/");
            BeautifulEnchantedBooks.registerModel(id, model);
            event.register(model);
        }
    }
}
