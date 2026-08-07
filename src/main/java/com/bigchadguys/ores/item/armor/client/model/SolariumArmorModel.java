package com.bigchadguys.ores.item.armor.client.model;

// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class SolariumArmorModel extends ArmorModel {

	/*
	 * The source texture is 96x80, but the model was originally authored
	 * against an effective UV canvas of 64x53.3333.
	 *
	 * Will edit model files later to better fit 96 x 80
	 *
	 * 96 * 2/3 = 64
	 * 80 * 2/3 = 53.3333
	 */
	private static final float UV_SCALE = 2.0F / 3.0F;

	private static final int TEXTURE_WIDTH = 96;
	private static final int TEXTURE_HEIGHT = 80;

	public SolariumArmorModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition root = meshDefinition.getRoot();

		PartDefinition head = root.addOrReplaceChild(
				"head",
				CubeListBuilder.create()
						.texOffs(0, 0)
						.addBox(
								-4.0F, -8.5F, -4.0F,
								8.0F, 8.0F, 8.0F,
								new CubeDeformation(0.75F),
								UV_SCALE, UV_SCALE
						)
						.texOffs(32, 0)
						.addBox(
								-4.0F, -8.5F, -4.0F,
								8.0F, 8.0F, 8.0F,
								new CubeDeformation(1.0F),
								UV_SCALE, UV_SCALE
						)
						.texOffs(0, 32)
						.addBox(
								-4.0F, -9.5F, -2.0F,
								8.0F, 8.0F, 8.0F,
								new CubeDeformation(1.25F),
								UV_SCALE, UV_SCALE
						),
				PartPose.offset(0.0F, 0.0F, 0.0F)
		);

		PartDefinition hat = root.addOrReplaceChild(
				"hat",
				CubeListBuilder.create(),
				PartPose.offset(0.0F, 24.0F, 0.0F)
		);

		PartDefinition body = root.addOrReplaceChild(
				"body",
				CubeListBuilder.create()
						.texOffs(16, 16)
						.addBox(
								-4.0F, 0.0F, -2.0F,
								8.0F, 12.0F, 4.0F,
								new CubeDeformation(0.75F),
								UV_SCALE, UV_SCALE
						),
				PartPose.offset(0.0F, 0.0F, 0.0F)
		);

		PartDefinition rightArm = root.addOrReplaceChild(
				"right_arm",
				CubeListBuilder.create()
						.texOffs(40, 16)
						.addBox(
								-3.0F, -2.0F, -2.0F,
								4.0F, 12.0F, 4.0F,
								new CubeDeformation(0.75F),
								UV_SCALE, UV_SCALE
						),
				PartPose.offset(-5.0F, 2.0F, 0.0F)
		);

		PartDefinition leftArm = root.addOrReplaceChild(
				"left_arm",
				CubeListBuilder.create()
						.texOffs(40, 16)
						.mirror()
						.addBox(
								-1.0F, -2.0F, -2.0F,
								4.0F, 12.0F, 4.0F,
								new CubeDeformation(0.75F),
								UV_SCALE, UV_SCALE
						)
						.mirror(false),
				PartPose.offset(5.0F, 2.0F, 0.0F)
		);

		PartDefinition rightLeg = root.addOrReplaceChild(
				"right_leg",
				CubeListBuilder.create(),
				PartPose.offset(-1.9F, 12.0F, 0.0F)
		);

		PartDefinition leftLeg = root.addOrReplaceChild(
				"left_leg",
				CubeListBuilder.create(),
				PartPose.offset(1.9F, 12.0F, 0.0F)
		);

		PartDefinition rightBoot = root.addOrReplaceChild(
				"right_boot",
				CubeListBuilder.create()
						.texOffs(0, 16)
						.addBox(
								-2.0F, 0.0F, -2.0F,
								4.0F, 12.0F, 4.0F,
								new CubeDeformation(0.75F),
								UV_SCALE, UV_SCALE
						),
				PartPose.offset(-1.9F, 12.0F, 0.0F)
		);

		PartDefinition leftBoot = root.addOrReplaceChild(
				"left_boot",
				CubeListBuilder.create()
						.texOffs(0, 16)
						.mirror()
						.addBox(
								-2.0F, 0.0F, -2.0F,
								4.0F, 12.0F, 4.0F,
								new CubeDeformation(0.75F),
								UV_SCALE, UV_SCALE
						)
						.mirror(false),
				PartPose.offset(1.9F, 12.0F, 0.0F)
		);

		return LayerDefinition.create(
				meshDefinition,
				TEXTURE_WIDTH,
				TEXTURE_HEIGHT
		);
	}
}