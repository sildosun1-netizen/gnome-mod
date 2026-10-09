package com.gnomefolk.client;

import com.gnomefolk.Gnomefolk;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;

/**
 * Blocky gnome: boots, blue tunic, stacked tilted red hat (no visible head),
 * stepped white beard with a cream nose, blue sleeves with cream hands.
 * Total height is ~16.5 model units, i.e. about one block.
 * Texture: 64x64 (see assets/gnomefolk/textures/entity/gnome.png).
 */
public class GnomeModel extends EntityModel<LivingEntityRenderState> {
    public static final ModelLayerLocation LAYER = new ModelLayerLocation(Gnomefolk.id("gnome"), "main");

    private final ModelPart leftArm;
    private final ModelPart rightArm;
    private final ModelPart leftBoot;
    private final ModelPart rightBoot;

    public GnomeModel(ModelPart root) {
        super(root);
        this.leftArm = root.getChild("left_arm");
        this.rightArm = root.getChild("right_arm");
        this.leftBoot = root.getChild("left_boot");
        this.rightBoot = root.getChild("right_boot");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        PartPose base = PartPose.offset(0.0F, 24.0F, 0.0F); // feet level

        // Blue tunic (y up: 1..5)
        root.addOrReplaceChild("body",
                CubeListBuilder.create().texOffs(0, 0).addBox(-3, -5, -3, 6, 4, 6), base);

        // Hat stack: each piece is a child of the one below, so tilts accumulate and stay connected.
        PartDefinition band = root.addOrReplaceChild("hat_band",
                CubeListBuilder.create().texOffs(0, 10).addBox(-3, -8, -3, 6, 3, 6), base);
        PartDefinition mid = band.addOrReplaceChild("hat_mid",
                CubeListBuilder.create().texOffs(0, 19).addBox(-3, -4, -3, 6, 4, 6),
                PartPose.offsetAndRotation(-0.5F, -8.0F, 0.0F, 0.0F, 0.0F, 0.10F));
        PartDefinition top = mid.addOrReplaceChild("hat_top",
                CubeListBuilder.create().texOffs(0, 29).addBox(-2, -3, -2, 4, 3, 4),
                PartPose.offsetAndRotation(-0.5F, -4.0F, 0.0F, 0.0F, 0.0F, 0.14F));
        top.addOrReplaceChild("hat_tip",
                CubeListBuilder.create().texOffs(0, 36).addBox(-1, -3, -1, 2, 3, 2),
                PartPose.offsetAndRotation(-0.5F, -3.0F, 0.0F, 0.0F, 0.0F, 0.16F));

        // Stepped white beard (front = -Z) and nose
        root.addOrReplaceChild("beard_1",
                CubeListBuilder.create().texOffs(0, 41).addBox(-3, -7, -4, 6, 2, 1), base);
        root.addOrReplaceChild("beard_2",
                CubeListBuilder.create().texOffs(0, 44).addBox(-2, -5, -4, 4, 2, 1), base);
        root.addOrReplaceChild("beard_3",
                CubeListBuilder.create().texOffs(0, 47).addBox(-1, -3, -4, 2, 1, 1), base);
        root.addOrReplaceChild("nose",
                CubeListBuilder.create().texOffs(0, 49).addBox(-1, -7, -5, 2, 2, 1), base);

        // Arms: blue sleeve + cream hand, angled outwards
        PartDefinition rightArm = root.addOrReplaceChild("right_arm",
                CubeListBuilder.create().texOffs(30, 0).addBox(-1, 0, -1, 2, 3, 2),
                PartPose.offsetAndRotation(-4.0F, 19.0F, 0.0F, 0.0F, 0.0F, 0.45F));
        rightArm.addOrReplaceChild("right_hand",
                CubeListBuilder.create().texOffs(30, 5).addBox(-1, 0, -1, 2, 1, 2),
                PartPose.offset(0.0F, 3.0F, 0.0F));
        PartDefinition leftArm = root.addOrReplaceChild("left_arm",
                CubeListBuilder.create().texOffs(30, 0).addBox(-1, 0, -1, 2, 3, 2),
                PartPose.offsetAndRotation(4.0F, 19.0F, 0.0F, 0.0F, 0.0F, -0.45F));
        leftArm.addOrReplaceChild("left_hand",
                CubeListBuilder.create().texOffs(30, 5).addBox(-1, 0, -1, 2, 1, 2),
                PartPose.offset(0.0F, 3.0F, 0.0F));

        // Boots
        root.addOrReplaceChild("right_boot",
                CubeListBuilder.create().texOffs(30, 8).addBox(-4, -1, -2, 4, 1, 4), base);
        root.addOrReplaceChild("left_boot",
                CubeListBuilder.create().texOffs(30, 8).addBox(0, -1, -2, 4, 1, 4), base);

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);
        float swing = Mth.cos(state.walkAnimationPos * 0.6662F) * 1.4F * state.walkAnimationSpeed;
        this.rightArm.xRot = swing;
        this.leftArm.xRot = -swing;
        this.rightBoot.z = swing * 1.5F;
        this.leftBoot.z = -swing * 1.5F;
    }
}
