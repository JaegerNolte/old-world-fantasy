package net.jaeger.oldworldfantasy.entity.client;// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class nurglingModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "nurglingmodel"), "main");
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart lower;
	private final ModelPart upper;
	private final ModelPart armR;
	private final ModelPart armL;

	public nurglingModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = this.body.getChild("head");
		this.lower = this.body.getChild("lower");
		this.upper = this.body.getChild("upper");
		this.armR = this.body.getChild("armR");
		this.armL = this.body.getChild("armL");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(-6, -4).addBox(-1.0F, -22.0F, -4.0F, 4.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition lower = body.addOrReplaceChild("lower", CubeListBuilder.create().texOffs(-2, -1).addBox(-1.0F, -9.0F, 0.0F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(-2, -1).addBox(-1.0F, -9.0F, -5.0F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition upper = body.addOrReplaceChild("upper", CubeListBuilder.create().texOffs(-7, -6).addBox(-2.0F, -17.0F, -5.0F, 6.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition armR = body.addOrReplaceChild("armR", CubeListBuilder.create().texOffs(-2, -1).addBox(-1.0F, -17.0F, -8.0F, 4.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition armL = body.addOrReplaceChild("armL", CubeListBuilder.create().texOffs(-1, -1).addBox(-1.0F, -17.0F, 3.0F, 4.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}