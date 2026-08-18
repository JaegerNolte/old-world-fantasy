package net.jaeger.oldworldfantasy.client.model.item.shield;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;

public class ModShieldModel extends Model {
    private final ModelPart root;
    private final ModelPart[] plate;
    private final ModelPart handle;

    public ModShieldModel(ModelPart root)
    {
        super(RenderType::entitySolid);
        this.root = root;
        this.plate = new ModelPart[]{root.getChild("plate")};
        this.handle = root.getChild("handle");
    }

    public ModelPart[] plate() {
        return this.plate;
    }

    public ModelPart handle() {
        return this.handle;
    }

    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, int pColor) {
        this.root.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pColor);
    }
}
