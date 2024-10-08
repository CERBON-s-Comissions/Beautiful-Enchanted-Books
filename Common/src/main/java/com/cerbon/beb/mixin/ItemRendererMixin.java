package com.cerbon.beb.mixin;

import com.cerbon.beb.BeautifulEnchantedBooks;
import com.cerbon.beb.util.mixin.IModelManagerMixin;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Map;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {

    @Shadow
    private @Final ItemModelShaper itemModelShaper;

    @WrapOperation(method="getModel", at=@At( value="INVOKE", target="Lnet/minecraft/client/renderer/ItemModelShaper;getItemModel(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/client/resources/model/BakedModel;"))
    private BakedModel getModel(ItemModelShaper instance, ItemStack stack, Operation<BakedModel> original) {
        Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(stack);

        if (!stack.is(Items.ENCHANTED_BOOK) || enchants.isEmpty()) return original.call(instance, stack);

        final ModelManager modelManager = itemModelShaper.getModelManager();

        ResourceLocation enchantId = ResourceLocation.tryParse(enchants.entrySet().iterator().next().getKey().getDescriptionId().replace("enchantment.", "").replace(".", ":"));
        BakedModel model = ((IModelManagerMixin) modelManager).getModel(BeautifulEnchantedBooks.ofVariant(enchantId));
        return model != null ? model : original.call(instance, stack);
    }
}
