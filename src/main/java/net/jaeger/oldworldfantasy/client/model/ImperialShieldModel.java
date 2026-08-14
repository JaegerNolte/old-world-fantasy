package net.jaeger.oldworldfantasy.client.model;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class ImperialShieldModel extends ModBaseShieldModel {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(OldWorldFantasyMod.res("imperial_shield"), "main");

    public ImperialShieldModel(ModelPart root) {
        super(root);
    }

	public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition plate = partdefinition.addOrReplaceChild("plate", CubeListBuilder.create().texOffs(0, 15).addBox(-5.0F, -5.0F, 1.0F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 13).addBox(-6.0F, -6.0F, 1.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 11).addBox(-7.0F, -7.0F, 1.0F, 11.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-8.0F, -17.0F, 1.0F, 13.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 17).addBox(-3.0F, -4.0F, 1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 12.0F, -1.0F));

        PartDefinition handle = partdefinition.addOrReplaceChild("handle", CubeListBuilder.create().texOffs(16, 15).addBox(-3.0F, -12.0F, 2.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 12.0F, -1.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
	}
}