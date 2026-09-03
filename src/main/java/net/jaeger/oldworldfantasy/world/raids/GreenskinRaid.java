package net.jaeger.oldworldfantasy.world.raids;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.effect.ModEffects;
import net.jaeger.oldworldfantasy.entity.ModEntities;
import net.jaeger.oldworldfantasy.entity.mobs.ModRaider;
import net.jaeger.oldworldfantasy.sound.ModSounds;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.IExtensibleEnum;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Stream;

public class GreenskinRaid extends ModRaid {

    private static final int SECTION_RADIUS_FOR_FINDING_NEW_VILLAGE_CENTER = 2;
    private static final int ATTEMPT_RAID_FARTHEST = 0;
    private static final int ATTEMPT_RAID_CLOSE = 1;
    private static final int ATTEMPT_RAID_INSIDE = 2;
    private static final int VILLAGE_SEARCH_RADIUS = 2; // set to 32
    private static final int RAID_TIMEOUT_TICKS = 48000;
    private static final int NUM_SPAWN_ATTEMPTS = 3;
    private static final String RAIDERS_REMAINING = "event.oldworldfantasy.raid.raiders_remaining";
    public static final int VILLAGE_RADIUS_BUFFER = 16;
    private static final int POST_RAID_TICK_LIMIT = 40;
    private static final int DEFAULT_PRE_RAID_TICKS = 300;
    public static final int MAX_NO_ACTION_TIME = 2400;
    public static final int MAX_CELEBRATION_TICKS = 600;
    private static final int OUTSIDE_RAID_BOUNDS_TIMEOUT = 30;
    public static final int TICKS_PER_DAY = 24000;
    public static final int DEFAULT_MAX_RAID_OMEN_LEVEL = 5;
    private static final int LOW_MOB_THRESHOLD = 2;
    private static final Component RAID_NAME_COMPONENT = Component.translatable("event.oldworldfantasy.raid.greenskin");
    private static final Component RAID_BAR_VICTORY_COMPONENT = Component.translatable("event.oldworldfantasy.raid.victory.full.greenskin");
    private static final Component RAID_BAR_DEFEAT_COMPONENT = Component.translatable("event.oldworldfantasy.raid.defeat.full.greenskin");
    private static final int HERO_OF_THE_VILLAGE_DURATION = 48000;
    public static final int VALID_RAID_RADIUS_SQR = 9216;
    public static final int RAID_REMOVAL_THRESHOLD_SQR = 12544;
    private final Map<Integer, ModRaider> groupToLeaderMap = Maps.newHashMap();
    private final Map<Integer, Set<ModRaider>> groupRaiderMap = Maps.newHashMap();
    private final Set<UUID> heroesOfTheVillage = Sets.newHashSet();
    private long ticksActive;
    private BlockPos center;
    private final ServerLevel level;
    private boolean started;
    private final int id;
    private float totalHealth;
    private int raidOmenLevel;
    private boolean active;
    private int groupsSpawned;
    private final ServerBossEvent raidEvent = new ServerBossEvent(RAID_NAME_COMPONENT, BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.NOTCHED_10);
    private int postRaidTicks;
    private int raidCooldownTicks;
    private final RandomSource random = RandomSource.create();
    private final int numGroups;
    private RaidStatus status;
    private int celebrationTicks;
    private Optional<BlockPos> waveSpawnPos = Optional.empty();
    private float radius = 3.00f;

    public GreenskinRaid(int pId, ServerLevel pLevel, BlockPos pCenter) {
        super(pId, pLevel, pCenter);
        this.id = pId;
        this.level = pLevel;
        this.active = true;
        this.raidCooldownTicks = 300;
        this.raidEvent.setProgress(0.0F);
        this.center = pCenter;
        this.numGroups = this.getNumGroups(pLevel.getDifficulty());
        this.status = RaidStatus.ONGOING;
    }

    public GreenskinRaid(ServerLevel pLevel, CompoundTag pCompound) {
        super(pLevel, pCompound);
        this.level = pLevel;
        this.id = pCompound.getInt("Id");
        this.started = pCompound.getBoolean("Started");
        this.active = pCompound.getBoolean("Active");
        this.ticksActive = pCompound.getLong("TicksActive");
        this.raidOmenLevel = pCompound.getInt("BadOmenLevel");
        this.groupsSpawned = pCompound.getInt("GroupsSpawned");
        this.raidCooldownTicks = pCompound.getInt("PreRaidTicks");
        this.postRaidTicks = pCompound.getInt("PostRaidTicks");
        this.totalHealth = pCompound.getFloat("TotalHealth");
        this.center = new BlockPos(pCompound.getInt("CX"), pCompound.getInt("CY"), pCompound.getInt("CZ"));
        this.numGroups = pCompound.getInt("NumGroups");
        this.status = RaidStatus.getByName(pCompound.getString("Status"));
        this.heroesOfTheVillage.clear();
        if (pCompound.contains("HeroesOfTheVillage", 9)) {
            for (Tag tag : pCompound.getList("HeroesOfTheVillage", 11)) {
                this.heroesOfTheVillage.add(NbtUtils.loadUUID(tag));
            }
        }
    }

    @Override
    public String getRaidType() {
        return "greenskins";
    }

    @Override
    public boolean absorbRaidOmen(ServerPlayer pPlayer) {
        MobEffectInstance mobeffectinstance = pPlayer.getEffect(ModEffects.GREENSKIN_OMEN.getHolder().get());
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
    public void stop() {
        this.active = false;
        this.raidEvent.removeAllPlayers();
        this.status = RaidStatus.STOPPED;
    }

    @Override
    public void tick() {
        if (!this.isStopped()) {
            if (this.status == RaidStatus.ONGOING) {
                boolean flag = this.active;
                this.active = this.level.hasChunkAt(this.center);
                if (this.level.getDifficulty() == Difficulty.PEACEFUL) {
                    OldWorldFantasyMod.LOG.info("Raid stopped difficulty is set to: {}", this.level.getDifficulty());
                    this.stop();
                    return;
                }

                if (flag != this.active) {
                    this.raidEvent.setVisible(this.active);
                }

                if (!this.active) {
                    return;
                }

                if (!this.level.isVillage(this.center)) {
                    this.moveRaidCenterToNearbyVillageSection();
                }

                if (!this.level.isVillage(this.center)) {
                    if (this.groupsSpawned > 0) {
                        this.status = RaidStatus.LOSS;
                    } else {
                        this.stop();
                    }
                }

                this.ticksActive++;
                if (this.ticksActive >= 48000L) {
                    this.stop();
                    return;
                }

                int i = this.getTotalRaidersAlive();
                if (i == 0 && this.hasMoreWaves()) {
                    if (this.raidCooldownTicks <= 0) {
                        if (this.raidCooldownTicks == 0 && this.groupsSpawned > 0) {
                            this.raidCooldownTicks = 300;
                            this.raidEvent.setName(RAID_NAME_COMPONENT);
                            return;
                        }
                    } else {
                        boolean flag1 = this.waveSpawnPos.isPresent();
                        boolean flag2 = !flag1 && this.raidCooldownTicks % 5 == 0;
                        if (flag1 && !this.level.isPositionEntityTicking(this.waveSpawnPos.get())) {
                            flag2 = true;
                        }

                        if (flag2) {
                            int j = 0;
                            if (this.raidCooldownTicks < 100) {
                                j = 1;
                            } else if (this.raidCooldownTicks < 40) {
                                j = 2;
                            }

                            this.waveSpawnPos = this.getValidSpawnPos(j);
                        }

                        if (this.raidCooldownTicks == 300 || this.raidCooldownTicks % 20 == 0) {
                            this.updatePlayers();
                        }

                        this.raidCooldownTicks--;
                        this.raidEvent.setProgress(Mth.clamp((float)(300 - this.raidCooldownTicks) / 300.0F, 0.0F, 1.0F));
                    }
                }

                if (this.ticksActive % 20L == 0L) {
                    this.updatePlayers();
                    this.updateRaiders();
                    if (i > 0) {
                        if (i <= 2) {
                            this.trackRemainingRaiders();
                            this.raidEvent
                                    .setName(RAID_NAME_COMPONENT.copy().append(" - ").append(Component.translatable("event.oldworldfantasy.raid.raiders_remaining", i)));
                        } else {
                            this.raidEvent.setName(RAID_NAME_COMPONENT);
                        }
                    } else {
                        this.raidEvent.setName(RAID_NAME_COMPONENT);
                    }
                }

                boolean flag3 = false;
                int k = 0;
                while (this.shouldSpawnGroup()) {
                    BlockPos blockpos = this.waveSpawnPos.isPresent() ? this.waveSpawnPos.get() : this.findRandomSpawnPos(k, 20);
                    if (blockpos != null) {
                        this.started = true;
                        this.spawnGroup(blockpos);
                        if (!flag3) {
                            this.playSound(blockpos);
                            flag3 = true;
                        }
                    } else {
                        k++;
                        OldWorldFantasyMod.LOG.info("Could not find spawn position. Attempt: {}", k);
                    }
                    if (k > 3) {
                        OldWorldFantasyMod.LOG.info("Stopping raid because no valid spawn position was found");
                        this.stop();
                        break;
                    }
                }

                if (this.isStarted() && !this.hasMoreWaves() && i == 0) {
                    if (this.postRaidTicks < 40) {
                        this.postRaidTicks++;
                    } else {
                        this.status = RaidStatus.VICTORY;

                        for (UUID uuid : this.heroesOfTheVillage) {
                            Entity entity = this.level.getEntity(uuid);
                            if (entity instanceof LivingEntity) {
                                LivingEntity livingentity = (LivingEntity)entity;
                                if (!entity.isSpectator()) {
                                    livingentity.addEffect(new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 48000, this.raidOmenLevel - 1, false, false, true));
                                    if (livingentity instanceof ServerPlayer serverplayer) {
                                        serverplayer.awardStat(Stats.RAID_WIN);
                                        CriteriaTriggers.RAID_WIN.trigger(serverplayer);
                                    }
                                }
                            }
                        }
                    }
                }

                this.setDirty();
            } else if (this.isOver()) {
                this.celebrationTicks++;
                if (this.celebrationTicks >= 600) {
                    this.stop();
                    return;
                }

                if (this.celebrationTicks % 20 == 0) {
                    this.updatePlayers();
                    this.raidEvent.setVisible(true);
                    if (this.isVictory()) {
                        this.raidEvent.setProgress(0.0F);
                        this.raidEvent.setName(RAID_BAR_VICTORY_COMPONENT);
                    } else {
                        this.raidEvent.setName(RAID_BAR_DEFEAT_COMPONENT);
                    }
                }
            }
        }
    }

    @Override
    public void moveRaidCenterToNearbyVillageSection() {
        Stream<SectionPos> stream = SectionPos.cube(SectionPos.of(this.center), VILLAGE_SEARCH_RADIUS);
        stream.filter(this.level::isVillage)
                .map(SectionPos::center)
                .min(Comparator.comparingDouble(p_37766_ -> p_37766_.distSqr(this.center)))
                .ifPresent(this::setCenter);
    }

    @Override
    public Optional<BlockPos> getValidSpawnPos(int pOffsetMultiplier) {
        for (int i = 0; i < 3; i++) {
            BlockPos blockpos = this.findRandomSpawnPos(pOffsetMultiplier, 1);
            if (blockpos != null) {
                return Optional.of(blockpos);
            }
        }

        return Optional.empty();
    }

    @Override
    public boolean hasMoreWaves() {
        return this.hasBonusWave() ? !this.hasSpawnedBonusWave() : !this.isFinalWave();
    }

    @Override
    public boolean isFinalWave() {
        return this.getGroupsSpawned() == this.numGroups;
    }

    @Override
    public boolean hasBonusWave() {
        return this.raidOmenLevel > 1;
    }

    @Override
    public boolean hasSpawnedBonusWave() {
        return this.getGroupsSpawned() > this.numGroups;
    }

    @Override
    public boolean shouldSpawnBonusGroup() {
        return this.isFinalWave() && this.getTotalRaidersAlive() == 0 && this.hasBonusWave();
    }

    @Override
    protected void updateRaiders() {
        Iterator<Set<ModRaider>> iterator = this.groupRaiderMap.values().iterator();
        Set<ModRaider> set = Sets.newHashSet();

        while (iterator.hasNext()) {
            Set<ModRaider> set1 = iterator.next();

            for (ModRaider raider : set1) {
                BlockPos blockpos = raider.blockPosition();
                if (raider.isRemoved() || raider.level().dimension() != this.level.dimension() || this.center.distSqr(blockpos) >= 12544.0) {
                    set.add(raider);
                } else if (raider.tickCount > 600) {
                    if (this.level.getEntity(raider.getUUID()) == null) {
                        set.add(raider);
                    }

                    if (!this.level.isVillage(blockpos) && raider.getNoActionTime() > 2400) {
                        raider.setTicksOutsideRaid(raider.getTicksOutsideRaid() + 1);
                    }

                    if (raider.getTicksOutsideRaid() >= 30) {
                        set.add(raider);
                    }
                }
            }
        }

        for (ModRaider raider1 : set) {
            this.removeFromRaid(raider1, true);
        }
    }

    @Override
    public void playSound(BlockPos pPos) {
        float f = 13.0F;
        int i = 64;
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

        for (GreenskinRaid.RaiderType raid$raidertype : GreenskinRaid.RaiderType.VALUES) {
            int j = this.getDefaultNumSpawns(raid$raidertype, i, flag1) + this.getPotentialBonusSpawns(raid$raidertype, this.random, i, difficultyinstance, flag1);
            int k = 0;

            for (int l = 0; l < j; l++) {
                ModRaider raider = raid$raidertype.entityType.create(this.level);
                if (raider == null) {
                    break;
                }

                if (!flag && raider.canBeLeader()) {
                    raider.setPatrolLeader(true);
                    this.setLeader(i, raider);
                    flag = true;
                }

                this.joinRaid(i, raider, pPos, false);
                if (raid$raidertype.entityType == ModEntities.GOBLIN.get()) {
                    ModRaider raider1 = null;
                    if (i == this.getNumGroups(Difficulty.NORMAL)) {
                        raider1 = ModEntities.ORC.get().create(this.level);
                    } else if (i >= this.getNumGroups(Difficulty.HARD)) {
                        if (k == 0) {
                            raider1 = ModEntities.BIGUNS.get().create(this.level);
                        } else {
                            raider1 = ModEntities.ORCWARBOSS.get().create(this.level);
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
    public void joinRaid(int pWave, ModRaider pRaider, @Nullable BlockPos pPos, boolean pIsRecruited) {
        boolean flag = this.addWaveMob(pWave, pRaider);
        if (flag) {
            pRaider.setCurrentRaid(this);
            pRaider.setWave(pWave);
            pRaider.setCanJoinRaid(true);
            pRaider.setTicksOutsideRaid(0);
            if (!pIsRecruited && pPos != null) {
                pRaider.setPos((double)pPos.getX() + 0.5, (double)pPos.getY() + 1.0, (double)pPos.getZ() + 0.5);
                pRaider.finalizeSpawn(this.level, this.level.getCurrentDifficultyAt(pPos), MobSpawnType.EVENT, null);
                pRaider.applyRaidBuffs(this.level, pWave, false);
                pRaider.setOnGround(true);
                this.level.addFreshEntityWithPassengers(pRaider);
            }
        }
    }

    @Override
    public void updateBossbar() {
        this.raidEvent.setProgress(Mth.clamp(this.getHealthOfLivingRaiders() / this.totalHealth, 0.0F, 1.0F));
    }

    @Override
    public float getHealthOfLivingRaiders() {
        float f = 0.0F;

        for (Set<ModRaider> set : this.groupRaiderMap.values()) {
            for (ModRaider raider : set) {
                f += raider.getHealth();
            }
        }

        return f;
    }

    @Override
    public boolean shouldSpawnGroup() {
        return this.raidCooldownTicks == 0 && (this.groupsSpawned < this.numGroups || this.shouldSpawnBonusGroup()) && this.getTotalRaidersAlive() == 0;
    }

    @Override
    public int getTotalRaidersAlive() {
        return this.groupRaiderMap.values().stream().mapToInt(Set::size).sum();
    }

    @Override
    public void removeFromRaid(ModRaider pRaider, boolean pWanderedOutOfRaid) {
        Set<ModRaider> set = this.groupRaiderMap.get(pRaider.getWave());
        if (set != null) {
            boolean flag = set.remove(pRaider);
            if (flag) {
                if (pWanderedOutOfRaid) {
                    this.totalHealth = this.totalHealth - pRaider.getHealth();
                }

                pRaider.setCurrentRaid(null);
                this.updateBossbar();
                this.setDirty();
            }
        }
    }

    @Override
    public boolean isInsideRaid(BlockPos pos) {
        return this.center.distSqr(pos) <= this.radius * this.radius;
    }

    @Override
    public void setDirty() {
        this.level.getRaids().setDirty();
    }

    @Override
    @Nullable
    public ModRaider getLeader(int pWave) {
        return this.groupToLeaderMap.get(pWave);
    }

    @Override
    @Nullable
    public BlockPos findRandomSpawnPos(int pOffsetMultiplier, int pMaxTry) {
        int i = pOffsetMultiplier == 0 ? 2 : 2 - pOffsetMultiplier;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        SpawnPlacementType spawnplacementtype = SpawnPlacements.getPlacementType(EntityType.RAVAGER);

        for (int i1 = 0; i1 < pMaxTry; i1++) {
            float f = this.level.random.nextFloat() * (float) (Math.PI * 2);
            int j = this.center.getX() + Mth.floor(Mth.cos(f) * 32.0F * (float)i) + this.level.random.nextInt(5);
            int l = this.center.getZ() + Mth.floor(Mth.sin(f) * 32.0F * (float)i) + this.level.random.nextInt(5);
            int k = this.level.getHeight(Heightmap.Types.WORLD_SURFACE, j, l);
            blockpos$mutableblockpos.set(j, k, l);
            if (!this.level.isVillage(blockpos$mutableblockpos) || pOffsetMultiplier >= 2) {
                int j1 = 10;
                if (this.level.hasChunksAt(
                        blockpos$mutableblockpos.getX() - 10,
                        blockpos$mutableblockpos.getZ() - 10,
                        blockpos$mutableblockpos.getX() + 10,
                        blockpos$mutableblockpos.getZ() + 10
                )
                        && this.level.isPositionEntityTicking(blockpos$mutableblockpos)
                        && (
                        spawnplacementtype.isSpawnPositionOk(this.level, blockpos$mutableblockpos, EntityType.RAVAGER)
                                || this.level.getBlockState(blockpos$mutableblockpos.below()).is(Blocks.SNOW)
                                && this.level.getBlockState(blockpos$mutableblockpos).isAir()
                )) {
                    return blockpos$mutableblockpos;
                }
            }
        }

        return null;
    }

    @Override
    public boolean addWaveMob(int pWave, ModRaider pRaider) {
        return this.addWaveMob(pWave, pRaider, true);
    }

    @Override
    public boolean addWaveMob(int pWave, ModRaider pRaider, boolean pIsRecruited) {
        this.groupRaiderMap.computeIfAbsent(pWave, p_37746_ -> Sets.newHashSet());
        Set<ModRaider> set = this.groupRaiderMap.get(pWave);
        ModRaider raider = null;

        for (ModRaider raider1 : set) {
            if (raider1.getUUID().equals(pRaider.getUUID())) {
                raider = raider1;
                break;
            }
        }

        if (raider != null) {
            set.remove(raider);
            set.add(pRaider);
        }

        set.add(pRaider);
        if (pIsRecruited) {
            this.totalHealth = this.totalHealth + pRaider.getHealth();
        }

        this.updateBossbar();
        this.setDirty();
        return true;
    }

    @Override
    public void setLeader(int pWave, ModRaider pRaider) {
        this.groupToLeaderMap.put(pWave, pRaider);
    }

    @Override
    public void removeLeader(int pWave) {
        this.groupToLeaderMap.remove(pWave);
    }

    @Override
    public BlockPos getCenter() {
        return this.center;
    }

    @Override
    public void setCenter(BlockPos pos) {
        this.center = pos;
    }

    @Override
    public int getId() {
        return this.id;
    }

    private int getDefaultNumSpawns(GreenskinRaid.RaiderType pRaiderType, int pWave, boolean pShouldSpawnBonusGroup) {
        return pShouldSpawnBonusGroup ? pRaiderType.spawnsPerWaveBeforeBonus[this.numGroups] : pRaiderType.spawnsPerWaveBeforeBonus[pWave];
    }

    private int getPotentialBonusSpawns(GreenskinRaid.RaiderType pRaiderType, RandomSource pRandom, int pWave, DifficultyInstance pDifficulty, boolean pShouldSpawnBonusGroup) {
        Difficulty difficulty = pDifficulty.getDifficulty();
        boolean flag = difficulty == Difficulty.EASY;
        boolean flag1 = difficulty == Difficulty.NORMAL;
        int i;
        switch (pRaiderType) {
            case GOBLIN:
            case ORC:
                if (flag) {
                    i = pRandom.nextInt(2);
                } else if (flag1) {
                    i = 1;
                } else {
                    i = 2;
                }
                break;
            case BIGUNS:
            default:
                return 0;
            case WARBOSS:
                if (flag || pWave <= 2 || pWave == 4) {
                    return 0;
                }

                i = 1;
                break;
        }

        return i > 0 ? pRandom.nextInt(i + 1) : 0;
    }

    @Override
    public boolean isActive() {
        return this.active;
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
    public int getNumGroups(Difficulty pDifficulty) {
        switch (pDifficulty) {
            case EASY:
                return 3;
            case NORMAL:
                return 5;
            case HARD:
                return 7;
            default:
                return 0;
        }
    }

    @Override
    public void addHeroOfTheVillage(Entity pPlayer) {
        this.heroesOfTheVillage.add(pPlayer.getUUID());
    }

    @Override
    public void trackRemainingRaiders() {
        for (ModRaider raider : this.getAllRaiders()) {
            if (raider.isAlive()) {
                raider.addEffect(new MobEffectInstance(MobEffects.GLOWING, 12000, 0, false, false));
            }
        }
    }

    enum RaiderType implements IExtensibleEnum {
        GOBLIN(ModEntities.GOBLIN.get(), new int[]{1, 0, 2, 0, 1, 2, 2, 3}),
        ORC(ModEntities.ORC.get(), new int[]{0, 1, 0, 0, 0, 1, 1, 2}),
        BIGUNS(ModEntities.BIGUNS.get(), new int[]{0, 0, 0, 0, 2, 2, 2, 2}),
        WARBOSS(ModEntities.ORCWARBOSS.get(), new int[]{0, 1, 0, 1, 0, 1, 0, 2});

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
