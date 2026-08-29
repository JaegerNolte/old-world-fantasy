package net.jaeger.oldworldfantasy.worldgen.raids;

import com.google.common.collect.Maps;
import net.jaeger.oldworldfantasy.entity.mobs.ModRaider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.PoiTypeTags;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ModRaids extends SavedData {
    private static final String RAID_FILE_ID = "mod_raids";
    private final Map<Integer, ModRaid> raidMap = Maps.newHashMap();
    private final ServerLevel level;
    private int nextAvailableID;
    private int tick;

    public static Factory<ModRaids> factory(ServerLevel level) {
        return new Factory<>(
                () -> new ModRaids(level),
                (tag, provider) -> ModRaids.load(level, tag),
                DataFixTypes.SAVED_DATA_RAIDS
        );
    }

    public ModRaids(ServerLevel pLevel) {
        this.level = pLevel;
        this.nextAvailableID = 1;
        this.setDirty();
    }

    public ModRaid get(int pId) {
        return this.raidMap.get(pId);
    }

    public void tick() {
        this.tick++;
        Iterator<ModRaid> iterator = this.raidMap.values().iterator();

        while (iterator.hasNext()) {
            ModRaid raid = iterator.next();
            if (this.level.getGameRules().getBoolean(GameRules.RULE_DISABLE_RAIDS)) {
                raid.stop();
            }

            if (raid.isStopped()) {
                iterator.remove();
                this.setDirty();
            } else {
                raid.tick();
            }
        }

        if (this.tick % 200 == 0) {
            this.setDirty();
        }

//        OldWorldFantasyMod.LOG.debug("Level: {}, Active Mod Raids: {}", this.level.dimension().location(), this.raidMap.size());
    }

    public static boolean canJoinRaid(ModRaider pRaider, ModRaid pRaid) {
        return pRaider != null && pRaid != null && pRaid.getLevel() != null
                ? pRaider.isAlive() && pRaider.canJoinRaid() && pRaider.getNoActionTime() <= 2400 && pRaider.level().dimensionType() == pRaid.getLevel().dimensionType()
                : false;
    }

    @Nullable
    public ModRaid createOrExtendRaid(ServerPlayer pPlayer, BlockPos pPos) {
        if (pPlayer.isSpectator()) {
            return null;
        } else if (this.level.getGameRules().getBoolean(GameRules.RULE_DISABLE_RAIDS)) {
            return null;
        } else {
            DimensionType dimensiontype = pPlayer.level().dimensionType();
            if (!dimensiontype.hasRaids()) {
                return null;
            } else {
                List<PoiRecord> list = this.level
                        .getPoiManager()
                        .getInRange(p_219845_ -> p_219845_.is(PoiTypeTags.VILLAGE), pPos, 64, PoiManager.Occupancy.IS_OCCUPIED)
                        .toList();
                int i = 0;
                Vec3 vec3 = Vec3.ZERO;

                for (PoiRecord poirecord : list) {
                    BlockPos blockpos = poirecord.getPos();
                    vec3 = vec3.add((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ());
                    i++;
                }

                BlockPos blockpos1;
                if (i > 0) {
                    vec3 = vec3.scale(1.0 / (double)i);
                    blockpos1 = BlockPos.containing(vec3);
                } else {
                    blockpos1 = pPos;
                }

                ModRaid raid = this.getOrCreateRaid(pPlayer.serverLevel(), blockpos1);
                if (!raid.isStarted() && !this.raidMap.containsKey(raid.getId())) {
                    this.raidMap.put(raid.getId(), raid);
                }

                if (!raid.isStarted() || raid.getRaidOmenLevel() < raid.getMaxRaidOmenLevel()) {
                    raid.absorbRaidOmen(pPlayer);
                }

                this.setDirty();
                return raid;
            }
        }
    }

    private ModRaid getOrCreateRaid(ServerLevel pServerLevel, BlockPos pPos) {
        ModRaid raid = this.getRaidAt(pPos);
        return raid != null ? raid : new ModRaid(this.getUniqueId(), pServerLevel, pPos);
    }

    public static ModRaids get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(
                new Factory<>(() -> new ModRaids(level), (tag, provider) -> ModRaids.load(level, tag), DataFixTypes.SAVED_DATA_RAIDS),
                RAID_FILE_ID
        );
    }

    public static ModRaids load(ServerLevel pLevel, CompoundTag pTag) {
        ModRaids raids = new ModRaids(pLevel);
        raids.nextAvailableID = pTag.getInt("NextAvailableID");
        raids.tick = pTag.getInt("Tick");
        ListTag listtag = pTag.getList("Raids", 10);

        for (int i = 0; i < listtag.size(); i++) {
            CompoundTag compoundtag = listtag.getCompound(i);
            ModRaid raid = new ModRaid(pLevel, compoundtag);
            raids.raidMap.put(raid.getId(), raid);
        }

        return raids;
    }

    public CompoundTag save(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        pTag.putInt("NextAvailableID", this.nextAvailableID);
        pTag.putInt("Tick", this.tick);
        ListTag listtag = new ListTag();

        for (ModRaid raid : this.raidMap.values()) {
            CompoundTag compoundtag = new CompoundTag();
            raid.save(compoundtag);
            listtag.add(compoundtag);
        }

        pTag.put("Raids", listtag);
        return pTag;
    }

    public static String getFileId(Holder<DimensionType> pDimensionTypeHolder) {
        return pDimensionTypeHolder.is(BuiltinDimensionTypes.END) ? "raids_end" : "raids";
    }

    @Nullable
    public ModRaid getRaidAt(BlockPos pos) {
        return raidMap.values()
                .stream()
                .filter(raid -> raid.isInsideRaid(pos))
                .findFirst()
                .orElse(null);
    }

    private int getUniqueId() {
        return ++this.nextAvailableID;
    }

    @Nullable
    public ModRaid getNearbyRaid(BlockPos pPos, int pDistance) {
        ModRaid raid = null;
        double d0 = (double)pDistance;

        for (ModRaid raid1 : this.raidMap.values()) {
            double d1 = raid1.getCenter().distSqr(pPos);
            if (raid1.isActive() && d1 < d0) {
                raid = raid1;
                d0 = d1;
            }
        }

        return raid;
    }
}