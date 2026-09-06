package net.jaeger.oldworldfantasy.client.model.item.shield;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class ImperialShieldModel extends ModShieldModel {

    public ImperialShieldModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition plate = partdefinition.addOrReplaceChild("plate", CubeListBuilder.create().texOffs(0, 5).addBox(-7.0F, -25.0F, -2.0F, 13.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 22).addBox(1.0F, -26.0F, -2.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 17).addBox(-7.0F, -27.0F, -2.0F, 7.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 20).addBox(-6.0F, -28.0F, -2.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(24, 8).addBox(-5.0F, -29.0F, -2.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(24, 10).addBox(2.0F, -27.0F, -2.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-7.0F, -19.0F, -2.0F, 13.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 8).addBox(-6.0F, -23.0F, -2.0F, 11.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 24).addBox(3.0F, -28.0F, -2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 13).addBox(-6.0F, -15.0F, -2.0F, 11.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(22, 24).addBox(3.0F, -29.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 15).addBox(-5.0F, -14.0F, -2.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition handle = partdefinition.addOrReplaceChild("handle", CubeListBuilder.create().texOffs(0, 13).addBox(-1.0F, -23.0F, -1.0F, 2.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}