package net.jaeger.oldworldfantasy.entity;


import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class ModEntityTags {

    public static final TagKey<EntityType<?>> BEASTMEN = create("beastmen");
    public static final TagKey<EntityType<?>> GREENSKIN = create("greenskin");
    public static final TagKey<EntityType<?>> EMPIRE = create("empire");
    public static final TagKey<EntityType<?>> TROLL = create("troll");
    public static final TagKey<EntityType<?>> GIANT = create("giant");
    public static final TagKey<EntityType<?>> GRIFFON = create("griffon");

    private static TagKey<EntityType<?>> create(String name) {
        return TagKey.create(Registries.ENTITY_TYPE, OldWorldFantasy.res(name));
    }

    private ModEntityTags() {}
}
