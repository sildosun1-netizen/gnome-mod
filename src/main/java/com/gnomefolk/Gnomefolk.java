package com.gnomefolk;

import com.gnomefolk.entity.GnomeEntity;
import com.gnomefolk.entity.ModEntities;
import com.gnomefolk.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.entity.event.v1.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.levelgen.Heightmap;

public class Gnomefolk implements ModInitializer {
    public static final String MOD_ID = "gnomefolk";

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    @Override
    public void onInitialize() {
        ModEntities.init();
        ModItems.init();

        FabricDefaultAttributeRegistry.register(ModEntities.GNOME, GnomeEntity.createAttributes());

        SpawnPlacements.register(
                ModEntities.GNOME,
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Mob::checkMobSpawnRules);

        // Rare: low weight, single gnomes. Tweak weight / group sizes to taste.
        BiomeModifications.addSpawn(
                BiomeSelectors.foundInOverworld(),
                MobCategory.CREATURE,
                ModEntities.GNOME,
                3, 1, 1);

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS)
                .register(entries -> entries.accept(ModItems.GNOME_SPAWN_EGG));
    }
}
