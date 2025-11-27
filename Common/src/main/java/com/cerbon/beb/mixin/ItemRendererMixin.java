package com.cerbon.beb.mixin;

import com.cerbon.beb.BeautifulEnchantedBooks;
import com.cerbon.beb.platform.Services;
import com.cerbon.beb.util.MiscUtils;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Map;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {
    // We need this map because stellarity has a different model id for the enchantments.
    @Unique
    private static final Map<String, String> STELLARITY_MODELS = Map.ofEntries(
            Map.entry("stellarity:void_locket/amethyst", "stellarity:amethyst"),
            Map.entry("stellarity:void_locket/copper", "stellarity:copper"),
            Map.entry("stellarity:void_locket/diamond", "stellarity:diamond"),
            Map.entry("stellarity:void_locket/emerald", "stellarity:emerald"),
            Map.entry("stellarity:void_locket/gold", "stellarity:gold"),
            Map.entry("stellarity:void_locket/iron", "stellarity:iron"),
            Map.entry("stellarity:void_locket/lapis", "stellarity:lapis"),
            Map.entry("stellarity:void_locket/netherite", "stellarity:netherite"),
            Map.entry("stellarity:void_locket/quartz", "stellarity:quartz")
    );

    @Shadow
    private @Final ItemModelShaper itemModelShaper;

    @WrapOperation(method="getModel", at=@At( value="INVOKE", target="Lnet/minecraft/client/renderer/ItemModelShaper;getItemModel(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/client/resources/model/BakedModel;"))
    private BakedModel getModel(ItemModelShaper instance, ItemStack stack, Operation<BakedModel> original) {
        ItemEnchantments enchants = EnchantmentHelper.getEnchantmentsForCrafting(stack);

        if (!stack.is(Items.ENCHANTED_BOOK) || enchants.isEmpty()) return original.call(instance, stack);

        final ModelManager modelManager = itemModelShaper.getModelManager();

        String enchantId = enchants.entrySet().iterator().next().getKey().getRegisteredName();
        //Minecraft.getInstance().player.displayClientMessage(Component.literal(enchantId), false);

        BakedModel model = Services.PLATFORM.getModel(BeautifulEnchantedBooks.ofVariantRl(
                ResourceLocation.tryParse(MiscUtils.isModLoaded("stellarity") && enchantId.startsWith("stellarity:void_locket/") ? STELLARITY_MODELS.getOrDefault(enchantId, "") : enchantId)),
                BeautifulEnchantedBooks.ofVariantMl(ResourceLocation.tryParse(MiscUtils.isModLoaded("stellarity") && enchantId.startsWith("stellarity:void_locket/") ? STELLARITY_MODELS.getOrDefault(enchantId, "") : enchantId)),
                modelManager
        );

        return model != null && model != modelManager.getMissingModel() ? model : original.call(instance, stack);
    }
}
