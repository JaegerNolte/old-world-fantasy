package net.jaeger.oldworldfantasy.api.client.model;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
public abstract class ModModelsProvider {

    public final String MOD_ID;

    public final Map<ModelLayerLocation, Supplier<LayerDefinition>> layers = new HashMap<>();

    protected ModModelsProvider(String modId) {
        this.MOD_ID = modId;
    }

    public ModelLayerLocation addModel(String name, Supplier<LayerDefinition> definition) {
        ModelLayerLocation location = this.createLocation(name);
        layers.put(location, definition);
        return location;
    }

    public ModelLayerLocation createLocation(String name) {
        return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MOD_ID, name), "main");
    }
}
