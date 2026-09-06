package net.jaeger.oldworldfantasy.client.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.api.client.render.model.ModModelsProvider;
import net.jaeger.oldworldfantasy.client.model.item.shield.ImperialShieldModel;
import net.minecraft.client.model.geom.ModelLayerLocation;

@Environment(EnvType.CLIENT)
public class ModModels extends ModModelsProvider {

    public static final ModModels INSTANCE = new ModModels(OldWorldFantasy.MOD_ID);

    public static final ModelLayerLocation IMPERIAL_SHIELD = INSTANCE.addShieldModel("imperial_shield", ImperialShieldModel::createBodyLayer, ImperialShieldModel::new);


    protected ModModels(String modId) {
        super(modId);
    }
}
