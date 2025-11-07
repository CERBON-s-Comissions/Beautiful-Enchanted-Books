package com.cerbon.beb.mixin;

import com.cerbon.beb.platform.Services;
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
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemModelResolver.class)
public class ItemModelResolverMixin {

    @Inject(method = "appendItemLayers", at = @At(value = "INVOKE", target = "Ljava/util/function/Function;apply(Ljava/lang/Object;)Ljava/lang/Object;"), cancellable = true)
    private void appendItemLayers(ItemStackRenderState renderState, ItemStack stack, ItemDisplayContext displayContext, Level level, ItemOwner owner, int seed, CallbackInfo ci) {
        ItemEnchantments enchants = EnchantmentHelper.getEnchantmentsForCrafting(stack);

        if (!stack.is(Items.ENCHANTED_BOOK) || enchants.isEmpty()) return;

        String enchantId = enchants.entrySet().iterator().next().getKey().getRegisteredName();

        ItemModel itemModel = Services.PLATFORM.getItemModel(ResourceLocation.tryParse(enchantId), Minecraft.getInstance().getModelManager());

        if (itemModel != null && !(itemModel instanceof MissingItemModel)) {
            itemModel.update(renderState, stack, (ItemModelResolver) (Object) this, displayContext, level instanceof ClientLevel clientLevel ? clientLevel : null, owner, seed);
            ci.cancel();
        }
    }
}
