package net.jaeger.oldworldfantasy.entity.client.ungor;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.custom.beastmen.ungor.Ungor;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;


public class UngorModel<T extends Ungor> extends HierarchicalModel<T> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(OldWorldFantasyMod.MOD_ID, "ungor"), "main");
	private final ModelPart body;
	private final ModelPart head;


	public UngorModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = this.body.getChild("head");
	}

    public static LayerDefinition createBodyLayer() {
            MeshDefinition meshdefinition = new MeshDefinition();
            PartDefinition partdefinition = meshdefinition.getRoot();

            PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

            PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(-2.0F, -32.0F, 0.0F));

            PartDefinition right_horn_r1 = head.addOrReplaceChild("right_horn_r1", CubeListBuilder.create().texOffs(10, 62).addBox(-2.0F, -2.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -6.0F, -4.0F, -2.7115F, 0.609F, -3.0158F));

            PartDefinition left_horn_r1 = head.addOrReplaceChild("left_horn_r1", CubeListBuilder.create().texOffs(10, 66).addBox(-2.0F, -2.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -6.0F, 4.0F, 0.4301F, 0.609F, 0.1258F));

            PartDefinition head_r1 = head.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(0, 20).addBox(-4.0F, -7.0F, -5.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, 0.0F, 1.5708F, -0.1309F));

            PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create(), PartPose.offset(0.0F, -28.0F, 0.0F));

            PartDefinition chest_r1 = torso.addOrReplaceChild("chest_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -8.5F, -5.5F, 7.0F, 9.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 3.0F, 0.0F, 0.0F, 0.0F, -0.1309F));

            PartDefinition lower_body = body.addOrReplaceChild("lower_body", CubeListBuilder.create().texOffs(32, 20).addBox(-3.0F, -3.0F, -4.0F, 6.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                    .texOffs(60, 60).addBox(-4.0F, 7.0F, -3.0F, 0.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
                    .texOffs(56, 0).addBox(3.0F, 6.0F, -3.0F, 0.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
                    .texOffs(52, 36).addBox(-4.0F, 1.0F, -3.0F, 1.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                    .texOffs(52, 48).addBox(3.0F, 1.0F, -3.0F, 1.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                    .texOffs(0, 62).addBox(-5.0F, 2.0F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -22.0F, 0.0F));

            PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(-1.0F, -31.0F, 7.0F));

            PartDefinition lower_arm_r1 = left_arm.addOrReplaceChild("lower_arm_r1", CubeListBuilder.create().texOffs(0, 49).addBox(-1.5F, -2.2F, -1.0F, 4.0F, 9.0F, 3.8F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 8.0F, 1.0F, 0.121F, -0.05F, 0.3897F));

            PartDefinition upper_arm_r1 = left_arm.addOrReplaceChild("upper_arm_r1", CubeListBuilder.create().texOffs(20, 36).addBox(-1.0F, -2.0F, -1.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 1.0F, -1.0F, 0.3054F, 0.0F, 0.0F));

            PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, -31.0F, -7.0F, -3.1416F, 0.0F, 3.1416F));

            PartDefinition lower_arm_r2 = right_arm.addOrReplaceChild("lower_arm_r2", CubeListBuilder.create().texOffs(16, 49).addBox(-2.35F, -2.2F, -1.0F, 4.0F, 9.0F, 3.8F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 8.0F, 1.0F, 0.121F, 0.05F, -0.3897F));

            PartDefinition upper_arm_r2 = right_arm.addOrReplaceChild("upper_arm_r2", CubeListBuilder.create().texOffs(36, 36).addBox(-3.0F, -2.0F, -1.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 1.0F, -1.0F, 0.3054F, 0.0F, 0.0F));

            PartDefinition left_leg = body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(36, 13).addBox(-6.0F, 16.0F, -3.0F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -19.0F, 5.0F));

            PartDefinition ankle_r1 = left_leg.addOrReplaceChild("ankle_r1", CubeListBuilder.create().texOffs(60, 14).addBox(0.0F, -6.25F, -0.5F, 4.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 15.0F, -2.0F, 0.0F, 0.0F, 0.5236F));

            PartDefinition lower_leg_r1 = left_leg.addOrReplaceChild("lower_leg_r1", CubeListBuilder.create().texOffs(60, 25).addBox(0.0F, -5.5F, -0.5F, 4.0F, 6.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 13.0F, -2.0F, 0.0F, 0.0F, -1.0472F));

            PartDefinition upper_leg_r1 = left_leg.addOrReplaceChild("upper_leg_r1", CubeListBuilder.create().texOffs(36, 0).addBox(-2.0F, -4.2679F, -1.0F, 6.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 3.0F, -2.0F, 0.0F, 0.0F, 0.5236F));

            PartDefinition right_leg = body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(32, 49).addBox(-1.0F, 16.0F, -2.0F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -19.0F, -4.0F, -3.1416F, 0.0F, 3.1416F));

            PartDefinition ankle_r2 = right_leg.addOrReplaceChild("ankle_r2", CubeListBuilder.create().texOffs(32, 56).addBox(-4.0F, -6.25F, -0.5F, 4.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 15.0F, -1.0F, 0.0F, 0.0F, -0.5236F));

            PartDefinition lower_leg_r2 = right_leg.addOrReplaceChild("lower_leg_r2", CubeListBuilder.create().texOffs(46, 60).addBox(-4.0F, -5.5F, -0.5F, 4.0F, 6.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 13.0F, -1.0F, 0.0F, 0.0F, 1.0472F));

            PartDefinition upper_leg_r2 = right_leg.addOrReplaceChild("upper_leg_r2", CubeListBuilder.create().texOffs(0, 36).addBox(-4.0F, -4.2679F, -1.0F, 6.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 3.0F, -1.0F, 0.0F, 0.0F, -0.5236F));

            return LayerDefinition.create(meshdefinition, 128, 128);
    }

	@Override
	public void setupAnim(Ungor entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(UngorAnimations.ANIM_UNGOR_WALKING, limbSwing, limbSwingAmount, 4.0f, 1.0f);
        this.animate(entity.idleAnimationState, UngorAnimations.ANIM_UNGOR_IDLE, ageInTicks, 1.0f);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}

    private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -25.0f, 25.0f);
        pHeadPitch = Mth.clamp(pHeadPitch, -2.5f, 2.5f);

		this.head.xRot = 0.0f;
        this.head.zRot = pHeadPitch * ((float)Math.PI / 180f);
        this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180f);
    }

    @Override
    public ModelPart root() {
        return body;
    }
}