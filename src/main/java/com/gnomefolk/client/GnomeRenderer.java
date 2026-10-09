package com.gnomefolk.client;

import com.gnomefolk.Gnomefolk;
import com.gnomefolk.entity.GnomeEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class GnomeRenderer extends MobRenderer<GnomeEntity, LivingEntityRenderState, GnomeModel> {
    private static final Identifier TEXTURE = Gnomefolk.id("textures/entity/gnome.png");

    public GnomeRenderer(EntityRendererProvider.Context context) {
        super(context, new GnomeModel(context.bakeLayer(GnomeModel.LAYER)), 0.3F);
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    @Override
    public Identifier getTextureLocation(LivingEntityRenderState state) {
        return TEXTURE;
    }
}
