package net.jaeger.oldworldfantasy.entity.client.gor;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.custom.gor.GorEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;


public class GorModel<T extends GorEntity> extends HierarchicalModel<T> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(OldWorldFantasyMod.MOD_ID, "gor"), "main");
	private final ModelPart body;
	private final ModelPart head;


	public GorModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = this.body.getChild("head");
	}

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 34.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(56, 23).addBox(-3.0F, -4.6268F, -2.2074F, 6.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(29, 64).addBox(-1.998F, -1.9846F, -7.2831F, 3.999F, 3.0F, 2.995F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -43.3732F, -3.7926F));

        PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(20, 72).addBox(0.0F, -3.0F, 2.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -5.6268F, -3.2074F, -0.1272F, 0.2262F, 0.9873F));

        PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(70, 68).addBox(0.0F, -3.0F, 2.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.5225F, -7.3715F, -2.8242F, -0.0063F, 0.2589F, 1.4809F));

        PartDefinition cube_r3 = head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(68, 51).addBox(-4.0F, -5.0F, 3.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 2.3732F, -2.2074F, 0.0873F, -0.4363F, 0.0F));

        PartDefinition cube_r4 = head.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(68, 39).addBox(-1.999F, -6.0F, 4.0F, 3.998F, 1.0F, 3.999F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 9.0154F, -8.2831F, 0.3491F, 0.0F, 0.0F));

        PartDefinition cube_r5 = head.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(44, 63).addBox(-1.9965F, -2.499F, -1.4995F, 3.995F, 4.999F, 2.999F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0005F, -1.2771F, -4.4551F, 0.7854F, 0.0F, 0.0F));

        PartDefinition cube_r6 = head.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(60, 0).addBox(-1.998F, -3.0F, -1.5005F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.412F, -3.1632F, 0.1745F, 0.0F, 0.0F));

        PartDefinition cube_r7 = head.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(56, 39).addBox(1.0F, -5.0F, 3.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 2.3732F, -2.2074F, 0.0873F, 0.4363F, 0.0F));

        PartDefinition cube_r8 = head.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(40, 71).addBox(-2.0F, -3.0F, 2.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.5225F, -7.3715F, -2.8242F, -0.0063F, -0.2589F, -1.4809F));

        PartDefinition cube_r9 = head.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(12, 72).addBox(-2.0F, -3.0F, 2.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -5.6268F, -3.2074F, -0.1272F, -0.2262F, -0.9873F));

        PartDefinition cube_r10 = head.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(68, 18).addBox(-2.997F, -4.0F, 2.0F, 5.995F, 3.0F, 1.999F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.8373F, 2.2567F, 1.0472F, 0.0F, 0.0F));

        PartDefinition cube_r11 = head.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(56, 32).addBox(-3.001F, -4.0F, 0.0F, 6.0F, 3.0F, 3.999F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 1.0F, 0.3491F, 0.0F, 0.0F));

        PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offsetAndRotation(-7.0F, -40.0F, 0.0F, -0.1512F, 0.1813F, 0.3027F));

        PartDefinition cube_r12 = left_arm.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(52, 10).addBox(2.0F, -4.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.4012F, 9.8992F, 0.1931F, -0.2618F, 0.0F, 0.0F));

        PartDefinition cube_r13 = left_arm.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(40, 29).addBox(2.0F, -4.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.4012F, 2.5387F, -0.4293F, 0.3491F, 0.0F, 0.0F));

        PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offsetAndRotation(7.0F, -40.0F, 0.0F, -0.1512F, -0.1813F, -0.3027F));

        PartDefinition cube_r14 = right_arm.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(52, 42).addBox(-6.0F, -4.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.4012F, 9.8992F, 0.1931F, -0.2618F, 0.0F, 0.0F));

        PartDefinition cube_r15 = right_arm.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(0, 42).addBox(-6.0F, -4.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.4012F, 2.5387F, -0.4293F, 0.3491F, 0.0F, 0.0F));

        PartDefinition upper_body = body.addOrReplaceChild("upper_body", CubeListBuilder.create().texOffs(26, 16).addBox(-2.999F, 2.2306F, -2.9595F, 5.999F, 5.999F, 6.999F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -39.0F, 0.0F));

        PartDefinition cube_r16 = upper_body.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(34, 42).addBox(-3.0F, -6.0F, -2.0F, 4.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 7.0F, 0.0F, 0.0F, 0.0F, 0.4363F));

        PartDefinition cube_r17 = upper_body.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -7.0F, -3.0F, 12.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, -1.0F, 0.3491F, 0.0F, 0.0F));

        PartDefinition cube_r18 = upper_body.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(16, 42).addBox(-1.0F, -6.0F, -2.0F, 4.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 7.0F, 0.0F, 0.0F, 0.0F, -0.4363F));

        PartDefinition lower_body = body.addOrReplaceChild("lower_body", CubeListBuilder.create().texOffs(40, 0).addBox(-2.0F, 1.0F, -2.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(-1, 55).addBox(-2.999F, -1.0F, -4.0F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(40, 10).addBox(-3.0F, 5.0F, -3.0F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(68, 44).addBox(-3.0F, 7.0F, 4.0F, 6.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(55, 54).addBox(-3.004F, 0.999F, 3.0F, 6.005F, 6.001F, 2.001F, new CubeDeformation(0.0F))
                .texOffs(48, 71).addBox(-2.0F, 0.0F, -5.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -32.0F, 0.0F));

        PartDefinition left_leg = body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 53).addBox(-3.2412F, 17.0F, -1.6319F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(28, 70).addBox(-2.2422F, 15.8299F, 0.2112F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -30.0F, -2.0F, 0.0F, 0.3491F, 0.0F));

        PartDefinition cube_r19 = left_leg.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(16, 61).addBox(-1.9949F, -1.0F, -2.0F, 2.995F, 8.0F, 2.999F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2422F, 9.5861F, 3.9548F, -0.2618F, 0.0F, 0.0F));

        PartDefinition cube_r20 = left_leg.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(58, 63).addBox(-2.0F, -1.0F, -2.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2412F, 6.0503F, 0.936F, 0.7854F, 0.0F, 0.0F));

        PartDefinition cube_r21 = left_leg.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(0, 29).addBox(-3.0F, -1.0F, -1.0F, 5.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2412F, 0.0F, 2.3681F, -0.5236F, 0.0F, 0.0F));

        PartDefinition right_leg = body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(36, 55).addBox(-1.7588F, 17.0F, -1.6319F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(70, 63).addBox(-0.7578F, 15.8299F, 0.2112F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -30.0F, -2.0F, 0.0F, -0.3491F, 0.0F));

        PartDefinition cube_r22 = right_leg.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(0, 63).addBox(-0.9962F, -0.999F, -2.0F, 2.995F, 7.999F, 2.999F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2422F, 9.5861F, 3.9548F, -0.2618F, 0.0F, 0.0F));

        PartDefinition cube_r23 = right_leg.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(68, 9).addBox(-1.0F, -1.0F, -2.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2412F, 6.0503F, 0.936F, 0.7854F, 0.0F, 0.0F));

        PartDefinition cube_r24 = right_leg.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(20, 29).addBox(-2.0F, -1.0F, -1.0F, 5.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2412F, 0.0F, 2.3681F, -0.5236F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

	@Override
	public void setupAnim(GorEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(GorAnimations.ANIM_GOR_WALKING, limbSwing, limbSwingAmount, 4.0f, 1.0f);
        this.animate(entity.idleAnimationState, GorAnimations.ANIM_GOR_IDLE, ageInTicks, 1.0f);
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