package net.jaeger.oldworldfantasy.client;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.api.client.model.ModModelsProvider;
import net.jaeger.oldworldfantasy.client.model.ImperialShieldModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModModels extends ModModelsProvider {

    public static final ModModels INSTANCE = new ModModels(OldWorldFantasyMod.MOD_ID);

    public static final ModelLayerLocation IMPERIAL_SHIELD = INSTANCE.addModel("imperial_shield", ImperialShieldModel::createBodyLayer);


    protected ModModels(String modId) {
        super(modId);
    }
}
