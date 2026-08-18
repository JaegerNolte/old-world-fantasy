package net.jaeger.oldworldfantasy.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class ModEntityTypeTags implements EntityTypeTags {

    TagKey<EntityType<?>> RAIDERS = create("raiders");
    TagKey<EntityType<?>> BEASTMEN = create("beastmen");
    TagKey<EntityType<?>> BEASTMEN_FRIENDS = create("beastmen_friends");

    private static TagKey<EntityType<?>> create(String pName) {
        return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.withDefaultNamespace(pName));
    }

    public static TagKey<EntityType<?>> create(String namepsace, String path) {
        return create(ResourceLocation.fromNamespaceAndPath(namepsace, path));
    }

    public static TagKey<EntityType<?>> create(ResourceLocation name) {
        return TagKey.create(Registries.ENTITY_TYPE, name);
    }
}
