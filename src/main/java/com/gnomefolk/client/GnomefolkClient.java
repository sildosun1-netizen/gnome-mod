package com.gnomefolk.client;

import com.gnomefolk.entity.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class GnomefolkClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModelLayerRegistry.registerModelLayer(GnomeModel.LAYER, GnomeModel::createBodyLayer);
        EntityRenderers.register(ModEntities.GNOME, GnomeRenderer::new);
    }
}
