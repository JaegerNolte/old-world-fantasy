package net.jaeger.oldworldfantasy.client.model;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.api.client.render.model.ModModelsProvider;
import net.jaeger.oldworldfantasy.client.model.item.ImperialShieldModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModModels extends ModModelsProvider {

    public static final ModModels INSTANCE = new ModModels(OldWorldFantasyMod.MOD_ID);

    public static final ModelLayerLocation IMPERIAL_SHIELD = INSTANCE.addShieldModel("imperial_shield", ImperialShieldModel::createBodyLayer, ImperialShieldModel::new);


    protected ModModels(String modId) {
        super(modId);
    }
}
