package com.cerbon.beb.mixin;

import com.cerbon.beb.platform.Services;
import com.cerbon.beb.util.MiscUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.item.MissingItemModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(ItemModelResolver.class)
public class ItemModelResolverMixin {
    // We need this map because stellarity has a different model id for the enchantments.
    @Unique
    private static final Map<String, String> STELLARITY_MODELS = Map.ofEntries(
            Map.entry("stellarity:_technical/void_locket/amethyst", "stellarity:amethyst"),
            Map.entry("stellarity:_technical/void_locket/copper", "stellarity:copper"),
            Map.entry("stellarity:_technical/void_locket/diamond", "stellarity:diamond"),
            Map.entry("stellarity:_technical/void_locket/emerald", "stellarity:emerald"),
            Map.entry("stellarity:_technical/void_locket/gold", "stellarity:gold"),
            Map.entry("stellarity:_technical/void_locket/iron", "stellarity:iron"),
            Map.entry("stellarity:_technical/void_locket/lapis", "stellarity:lapis"),
            Map.entry("stellarity:_technical/void_locket/netherite", "stellarity:netherite"),
            Map.entry("stellarity:_technical/void_locket/quartz", "stellarity:quartz"),
            Map.entry("stellarity:_technical/soul_harvest", "stellarity:soul_harvest"),
            Map.entry("stellarity:_technical/mighty_wind", "stellarity:mighty_wind"),
            Map.entry("stellarity:_technical/infernal_infusion", "stellarity:infernal_infusion"),
            Map.entry("stellarity:_technical/draconic", "stellarity:draconic"),
            Map.entry("stellarity:_technical/daybroken", "stellarity:daybroken"),
            Map.entry("stellarity:_technical/prismatic_pearl_return", "stellarity:prismatic_pearl_return")
    );

    @Inject(method = "appendItemLayers", at = @At(value = "INVOKE", target = "Ljava/util/function/Function;apply(Ljava/lang/Object;)Ljava/lang/Object;"), cancellable = true)
    private void appendItemLayers(ItemStackRenderState renderState, ItemStack stack, ItemDisplayContext displayContext, Level level, ItemOwner owner, int seed, CallbackInfo ci) {
        ItemEnchantments enchants = EnchantmentHelper.getEnchantmentsForCrafting(stack);

        if (!stack.is(Items.ENCHANTED_BOOK) || enchants.isEmpty()) return;

        String enchantId = enchants.entrySet().iterator().next().getKey().getRegisteredName();
        //Minecraft.getInstance().player.displayClientMessage(Component.literal(enchantId.toString()), false);
        ItemModel itemModel = Services.PLATFORM.getItemModel(ResourceLocation.tryParse(MiscUtils.isModLoaded("stellarity") && enchantId.startsWith("stellarity:_technical/") ? STELLARITY_MODELS.getOrDefault(enchantId, "") : enchantId), Minecraft.getInstance().getModelManager());

        if (itemModel != null && !(itemModel instanceof MissingItemModel)) {
            itemModel.update(renderState, stack, (ItemModelResolver) (Object) this, displayContext, level instanceof ClientLevel clientLevel ? clientLevel : null, owner, seed);
            ci.cancel();
        }
    }
}
