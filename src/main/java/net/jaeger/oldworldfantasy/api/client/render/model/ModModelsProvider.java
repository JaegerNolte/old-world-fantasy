package net.jaeger.oldworldfantasy.api.client.render.model;

import net.jaeger.oldworldfantasy.client.model.item.shield.ModBaseShieldModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
public abstract class ModModelsProvider {

    public final String MOD_ID;
    public final Map<ModelLayerLocation, Supplier<LayerDefinition>> layers = new HashMap<>();
    public final Map<ModelLayerLocation, Function<ModelPart, ? extends ModBaseShieldModel>> modelFactories = new HashMap<>();

    protected ModModelsProvider(String modId) {
        this.MOD_ID = modId;
    }

    public ModelLayerLocation addShieldModel(String name, Supplier<LayerDefinition> definition, Function<ModelPart, ? extends ModBaseShieldModel> modelFactory) {
        ModelLayerLocation location = createLocation(name);

        layers.put(location, definition);
        modelFactories.put(location, modelFactory);
        return location;
    }

    public ModelLayerLocation createLocation(String name) {
        return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MOD_ID, name), "main");
    }

    public ModelLayerLocation getModel(Item item) {
        ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(item);
        if (!MOD_ID.equals(itemId.getNamespace())) {
            return null;
        }
        ModelLayerLocation location = createLocation(itemId.getPath());
        return modelFactories.containsKey(location) ? location : null;
    }

    public Function<ModelPart, ? extends ModBaseShieldModel> getModelFactory( ModelLayerLocation location) {
        return modelFactories.get(location);
    }
}
