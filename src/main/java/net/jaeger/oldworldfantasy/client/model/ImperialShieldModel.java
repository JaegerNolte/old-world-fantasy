package net.jaeger.oldworldfantasy.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;

public class ImperialShieldModel extends Model {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(OldWorldFantasyMod.res("imperial_shield"), "main");
	private final ModelPart plate;
	private final ModelPart handle;

	public ImperialShieldModel(ModelPart root) {
        super(RenderType::entitySolid);
		this.plate = root.getChild("plate");
		this.handle = root.getChild("handle");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition plate = partdefinition.addOrReplaceChild("plate", CubeListBuilder.create().texOffs(0, 15).addBox(-5.0F, -5.0F, 1.0F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 13).addBox(-6.0F, -6.0F, 1.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 11).addBox(-7.0F, -7.0F, 1.0F, 11.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-8.0F, -17.0F, 1.0F, 13.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 17).addBox(-3.0F, -4.0F, 1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 24.0F, -1.0F));

		PartDefinition handle = partdefinition.addOrReplaceChild("handle", CubeListBuilder.create().texOffs(16, 15).addBox(-3.0F, -12.0F, 2.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 24.0F, -1.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, int pColor) {
        plate.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pColor);
        handle.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pColor);
    }
}