package com.gnomefolk.entity;

import com.gnomefolk.Gnomefolk;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public final class ModEntities {
    public static final ResourceKey<EntityType<?>> GNOME_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Gnomefolk.id("gnome"));

    public static final EntityType<GnomeEntity> GNOME = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            GNOME_KEY,
            EntityType.Builder.of(GnomeEntity::new, MobCategory.CREATURE)
                    .sized(0.5F, 1.0F)
                    .clientTrackingRange(8)
                    .build(GNOME_KEY));

    private ModEntities() {}

    public static void init() {
        // Touching the class triggers static registration.
    }
}
