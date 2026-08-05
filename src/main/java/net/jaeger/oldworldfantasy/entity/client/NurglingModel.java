package net.jaeger.oldworldfantasy.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.custom.NurglingEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;


public class NurglingModel<T extends NurglingEntity> extends HierarchicalModel<T> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(OldWorldFantasyMod.MOD_ID, "nurgling"), "main");
	private final ModelPart body;
	private final ModelPart head;


	public NurglingModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = this.body.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 27.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, -22.0F, -4.0F, 4.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(34, 14).addBox(0.0F, -21.0F, -6.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(34, 18).addBox(0.0F, -21.0F, 2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition HornUpperL_r1 = head.addOrReplaceChild("HornUpperL_r1", CubeListBuilder.create().texOffs(28, 9).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -19.6428F, 4.766F, 0.6981F, 0.0F, 0.0F));

		PartDefinition hornUpperL_r2 = head.addOrReplaceChild("hornUpperL_r2", CubeListBuilder.create().texOffs(28, 29).addBox(-1.0F, -2.0F, -2.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -19.6428F, -6.766F, -0.6981F, 0.0F, 0.0F));

		PartDefinition upper = body.addOrReplaceChild("upper", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -17.0F, -5.0F, 6.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition legL = body.addOrReplaceChild("legL", CubeListBuilder.create().texOffs(28, 0).addBox(-1.0F, -9.0F, 0.0F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition legR = body.addOrReplaceChild("legR", CubeListBuilder.create().texOffs(14, 29).addBox(-1.0F, -9.0F, -5.0F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition armR = body.addOrReplaceChild("armR", CubeListBuilder.create().texOffs(20, 16).addBox(-1.0F, -17.0F, -8.0F, 4.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition armL = body.addOrReplaceChild("armL", CubeListBuilder.create().texOffs(0, 27).addBox(-1.0F, -17.0F, 3.0F, 4.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(NurglingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(NurglingAnimations.ANIM_NURGLING_WALKING, limbSwing, limbSwingAmount, 4f, 2.5f);
        this.animate(entity.idleAnimationState, NurglingAnimations.ANIM_NURGLING_IDLE, ageInTicks, 1.5f);
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