package com.gnomefolk.item;

import com.gnomefolk.Gnomefolk;
import com.gnomefolk.entity.ModEntities;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

public final class ModItems {
    public static final ResourceKey<Item> GNOME_SPAWN_EGG_KEY =
            ResourceKey.create(Registries.ITEM, Gnomefolk.id("gnome_spawn_egg"));

    public static final Item GNOME_SPAWN_EGG = Registry.register(
            BuiltInRegistries.ITEM,
            GNOME_SPAWN_EGG_KEY,
            new SpawnEggItem(new Item.Properties()
                    .setId(GNOME_SPAWN_EGG_KEY)
                    .spawnEgg(ModEntities.GNOME)));

    private ModItems() {}

    public static void init() {
        // Touching the class triggers static registration.
    }
}
