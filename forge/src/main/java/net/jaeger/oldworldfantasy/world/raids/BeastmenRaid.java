package net.jaeger.oldworldfantasy.world.raids;

import net.jaeger.oldworldfantasy.effect.ModEffects;
import net.jaeger.oldworldfantasy.entity.ModEntities;
import net.jaeger.oldworldfantasy.entity.mobs.ModRaider;
import net.jaeger.oldworldfantasy.sound.ModSounds;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.IExtensibleEnum;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class BeastmenRaid extends ModRaid {

    private static final Component RAID_NAME_COMPONENT = Component.translatable("event.oldworldfantasy.raid.beastmen");
    private static final Component RAID_BAR_VICTORY_COMPONENT = Component.translatable("event.oldworldfantasy.raid.victory.full.beastmen");
    private static final Component RAID_BAR_DEFEAT_COMPONENT = Component.translatable("event.oldworldfantasy.raid.defeat.full.beastmen");

    public BeastmenRaid(int pId, ServerLevel pLevel, BlockPos pCenter) {
        super(pId, pLevel, pCenter);
    }

    public BeastmenRaid(ServerLevel pLevel, CompoundTag pCompound) {
        super(pLevel, pCompound);
    }

    @Override
    public String getRaidType() {
        return RaidTypes.BEASTMEN.getType();
    }

    @Override
    protected Component getRaidNameComponent() {
        return RAID_NAME_COMPONENT;
    }

    @Override
    protected Component getRaidBarVictoryComponent() {
        return RAID_BAR_VICTORY_COMPONENT;
    }

    @Override
    protected Component getRaidBarDefeatComponent() {
        return RAID_BAR_DEFEAT_COMPONENT;
    }

    @Override
    public boolean absorbRaidOmen(ServerPlayer pPlayer) {
        MobEffectInstance mobeffectinstance = pPlayer.getEffect(ModEffects.BEASTMEN_OMEN.getHolder().get());
        if (mobeffectinstance == null) {
            return false;
        } else {
            this.raidOmenLevel = this.raidOmenLevel + mobeffectinstance.getAmplifier() + 1;
            this.raidOmenLevel = Mth.clamp(this.raidOmenLevel, 0, this.getMaxRaidOmenLevel());
            if (!this.hasFirstWaveSpawned()) {
                pPlayer.awardStat(Stats.RAID_TRIGGER);
                CriteriaTriggers.RAID_OMEN.trigger(pPlayer);
            }

            return true;
        }
    }

    @Override
    public void playSound(BlockPos pPos) {
        Collection<ServerPlayer> collection = this.raidEvent.getPlayers();
        long j = this.random.nextLong();

        for (ServerPlayer serverplayer : this.level.players()) {
            Vec3 vec3 = serverplayer.position();
            Vec3 vec31 = Vec3.atCenterOf(pPos);
            double d0 = Math.sqrt(
                    (vec31.x - vec3.x) * (vec31.x - vec3.x) + (vec31.z - vec3.z) * (vec31.z - vec3.z)
            );
            double d1 = vec3.x + 13.0 / d0 * (vec31.x - vec3.x);
            double d2 = vec3.z + 13.0 / d0 * (vec31.z - vec3.z);
            if (d0 <= 64.0 || collection.contains(serverplayer)) {
                serverplayer.connection
                        .send(new ClientboundSoundPacket(ModSounds.CHAOS_HORN.getHolder().get(), SoundSource.NEUTRAL, d1, serverplayer.getY(), d2, 74.0F, 0.8F, j));
            }
        }
    }

    @Override
    public void spawnGroup(BlockPos pPos) {
        boolean flag = false;
        int i = this.groupsSpawned + 1;
        this.totalHealth = 0.0F;
        DifficultyInstance difficultyinstance = this.level.getCurrentDifficultyAt(pPos);
        boolean flag1 = this.shouldSpawnBonusGroup();

        for (RaiderType raidertype : RaiderType.VALUES) {
            int j = this.getDefaultNumSpawns(raidertype, i, flag1) + this.getPotentialBonusSpawns(raidertype, this.random, i, difficultyinstance, flag1);
            int k = 0;

            for (int l = 0; l < j; l++) {
                ModRaider raider = raidertype.entityType.create(this.level);
                if (raider == null) {
                    break;
                }

                if (!flag && raider.canBeLeader()) {
                    raider.setPatrolLeader(true);
                    this.setLeader(i, raider);
                    flag = true;
                }

                this.joinRaid(i, raider, pPos, false);
                if (raidertype.entityType == ModEntities.UNGOR.get()) {
                    ModRaider raider1 = null;
                    if (i == this.getNumGroups(Difficulty.NORMAL)) {
                        raider1 = ModEntities.GOR.get().create(this.level);
                    } else if (i >= this.getNumGroups(Difficulty.HARD)) {
                        if (k == 0) {
                            raider1 = ModEntities.BESTIGOR.get().create(this.level);
                        } else {
                            raider1 = ModEntities.WARGOR.get().create(this.level);
                        }
                    }
                    k++;
                    if (raider1 != null) {
                        this.joinRaid(i, raider1, pPos, false);
                    }
                }
            }
        }

        this.waveSpawnPos = Optional.empty();
        this.groupsSpawned++;
        this.updateBossbar();
        this.setDirty();
    }

    @Override
    public int getGroupsSpawned() {
        return this.groupsSpawned;
    }

    private int getDefaultNumSpawns(BeastmenRaid.RaiderType pRaiderType, int pWave, boolean pShouldSpawnBonusGroup) {
        return pShouldSpawnBonusGroup ? pRaiderType.spawnsPerWaveBeforeBonus[this.numGroups] : pRaiderType.spawnsPerWaveBeforeBonus[pWave];
    }

    private int getPotentialBonusSpawns(BeastmenRaid.RaiderType pRaiderType, RandomSource pRandom, int pWave, DifficultyInstance pDifficulty, boolean pShouldSpawnBonusGroup) {
        Difficulty difficulty = pDifficulty.getDifficulty();
        boolean flag = difficulty == Difficulty.EASY;
        boolean flag1 = difficulty == Difficulty.NORMAL;
        int i;
        switch (pRaiderType) {
            case UNGOR:
            case GOR:
                if (flag) {
                    i = pRandom.nextInt(2);
                } else if (flag1) {
                    i = 1;
                } else {
                    i = 2;
                }
                break;
            case BESTIGOR:
            default:
                return 0;
            case WARGOR:
                if (flag || pWave <= 2 || pWave == 4) {
                    return 0;
                }

                i = 1;
                break;
        }

        return i > 0 ? pRandom.nextInt(i + 1) : 0;
    }

    @Override
    public CompoundTag save(CompoundTag pCompound) {
        pCompound.putInt("Id", this.id);
        pCompound.putBoolean("Started", this.started);
        pCompound.putBoolean("Active", this.active);
        pCompound.putLong("TicksActive", this.ticksActive);
        pCompound.putInt("BadOmenLevel", this.raidOmenLevel);
        pCompound.putInt("GroupsSpawned", this.groupsSpawned);
        pCompound.putInt("PreRaidTicks", this.raidCooldownTicks);
        pCompound.putInt("PostRaidTicks", this.postRaidTicks);
        pCompound.putFloat("TotalHealth", this.totalHealth);
        pCompound.putInt("NumGroups", this.numGroups);
        pCompound.putString("Status", this.status.getName());
        pCompound.putString("RaidType", this.getRaidType());
        pCompound.putInt("CX", this.center.getX());
        pCompound.putInt("CY", this.center.getY());
        pCompound.putInt("CZ", this.center.getZ());
        ListTag listtag = new ListTag();

        for (UUID uuid : this.heroesOfTheVillage) {
            listtag.add(NbtUtils.createUUID(uuid));
        }

        pCompound.put("HeroesOfTheVillage", listtag);
        return pCompound;
    }

    @Override
    public void addHeroOfTheVillage(Entity pPlayer) {
        this.heroesOfTheVillage.add(pPlayer.getUUID());
    }

    enum RaiderType implements IExtensibleEnum {
        UNGOR(ModEntities.UNGOR.get(), new int[]{1, 0, 2, 0, 1, 2, 2, 3}),
        GOR(ModEntities.GOR.get(), new int[]{0, 1, 0, 0, 0, 1, 1, 2}),
        BESTIGOR(ModEntities.BESTIGOR.get(), new int[]{0, 0, 0, 0, 2, 2, 2, 2}),
        WARGOR(ModEntities.WARGOR.get(), new int[]{0, 1, 0, 1, 0, 1, 0, 2});

        static RaiderType[] VALUES = values();
        final EntityType<? extends ModRaider> entityType;
        final int[] spawnsPerWaveBeforeBonus;

        RaiderType(final EntityType<? extends ModRaider> pEntityType, final int[] pSpawnsPerWaveBeforeBonus) {
            this.entityType = pEntityType;
            this.spawnsPerWaveBeforeBonus = pSpawnsPerWaveBeforeBonus;
        }

        public static RaiderType create(String name, EntityType<? extends ModRaider> typeIn, int[] waveCountsIn) {
            throw new IllegalStateException("Enum not extended");
        }

        @Override
        @Deprecated
        public void init() {
            VALUES = values();
        }
    }
}
