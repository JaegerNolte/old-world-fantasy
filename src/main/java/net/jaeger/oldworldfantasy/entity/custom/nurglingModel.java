// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


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

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, -22.0F, -4.0F, 4.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(34, 14).addBox(0.0F, -21.0F, -6.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(34, 18).addBox(0.0F, -21.0F, 2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition HornUpperL_r1 = head.addOrReplaceChild("HornUpperL_r1", CubeListBuilder.create().texOffs(28, 9).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -19.6428F, 4.766F, 0.6981F, 0.0F, 0.0F));

		PartDefinition hornUpperL_r2 = head.addOrReplaceChild("hornUpperL_r2", CubeListBuilder.create().texOffs(28, 29).addBox(-1.0F, -2.0F, -2.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -19.6428F, -6.766F, -0.6981F, 0.0F, 0.0F));

		PartDefinition lower = body.addOrReplaceChild("lower", CubeListBuilder.create().texOffs(28, 0).addBox(-1.0F, -9.0F, 0.0F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(14, 29).addBox(-1.0F, -9.0F, -5.0F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition upper = body.addOrReplaceChild("upper", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -17.0F, -5.0F, 6.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition armR = body.addOrReplaceChild("armR", CubeListBuilder.create().texOffs(20, 16).addBox(-1.0F, -17.0F, -8.0F, 4.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition armL = body.addOrReplaceChild("armL", CubeListBuilder.create().texOffs(0, 27).addBox(-1.0F, -17.0F, 3.0F, 4.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}