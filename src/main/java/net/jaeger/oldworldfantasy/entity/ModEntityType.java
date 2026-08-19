package net.jaeger.oldworldfantasy.entity;

import com.google.common.collect.ImmutableSet;
import net.jaeger.oldworldfantasy.entity.custom.beastmen.ungor.Ungor;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.flag.FeatureFlagSet;

public class ModEntityType extends EntityType {

    public ModEntityType(EntityFactory pFactory, MobCategory pCategory, boolean pSerialize, boolean pSummon, boolean pFireImmune, boolean pCanSpawnFarFromPlayer, ImmutableSet pImmuneTo, EntityDimensions pDimensions, float pSpawnDimensionsScale, int pClientTrackingRange, int pUpdateInterval, FeatureFlagSet pRequiredFeatures) {
        super(pFactory, pCategory, pSerialize, pSummon, pFireImmune, pCanSpawnFarFromPlayer, pImmuneTo, pDimensions, pSpawnDimensionsScale, pClientTrackingRange, pUpdateInterval, pRequiredFeatures);
    }

    private static <T extends Entity> EntityType<T> register(String pKey, EntityType.Builder<T> pBuilder) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, pKey, pBuilder.build(pKey));
    }

    public static final EntityType<Ungor> UNGOR = register(
            "ungor",
            EntityType.Builder.of(Ungor::new, MobCategory.MONSTER).canSpawnFarFromPlayer().sized(0.6F, 1.95F).passengerAttachments(2.0F).ridingOffset(-0.6F).clientTrackingRange(8)
    );
}
