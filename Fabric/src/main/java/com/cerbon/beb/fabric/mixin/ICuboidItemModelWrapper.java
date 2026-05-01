package com.cerbon.beb.fabric.mixin;

import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.renderer.item.CuboidItemModelWrapper;
import net.minecraft.client.renderer.item.ModelRenderProperties;
import net.minecraft.client.resources.model.geometry.QuadCollection;
import org.joml.Matrix4fc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(CuboidItemModelWrapper.class)
public interface ICuboidItemModelWrapper {

    @Invoker("<init>")
    static CuboidItemModelWrapper invokeConstructor(final List<ItemTintSource> tints, final QuadCollection quads, final ModelRenderProperties properties, final Matrix4fc transformation) {
        throw new AssertionError("Mixin failed to invoke constructor");
    }
}
