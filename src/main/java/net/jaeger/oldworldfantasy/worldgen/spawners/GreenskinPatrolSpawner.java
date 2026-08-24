package net.jaeger.oldworldfantasy.worldgen.spawners;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.config.spawndata.SpawnDataHelper;
import net.jaeger.oldworldfantasy.entity.ModEntities;
import net.jaeger.oldworldfantasy.entity.custom.greenskin.orc.Orc;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.PatrollingMonster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.CustomSpawner;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;

import static net.jaeger.oldworldfantasy.config.spawndata.SpawnData.GREENSKIN_WEIGHTS;

public class GreenskinPatrolSpawner implements CustomSpawner {
    private int NEXT_TICK = 0;
    private final int PATROL_COOLDOWN = 12000;
    private final EntityType<Orc> PATROL_LEADER = ModEntities.ORC.get();

    @Override
    public int tick(ServerLevel pLevel, boolean pSpawnEnemies, boolean pSpawnFriendlies) {
        if (!pSpawnEnemies) {
            return 0;
        } else if (!pLevel.getGameRules().getBoolean(GameRules.RULE_DO_PATROL_SPAWNING)) {
            return 0;
        } else {
            RandomSource randomsource = pLevel.random;
            this.NEXT_TICK--;
            if (this.NEXT_TICK > 0) {
                return 0;
            } else {

                this.NEXT_TICK = this.NEXT_TICK + PATROL_COOLDOWN + randomsource.nextInt(PATROL_COOLDOWN);
                long i = pLevel.getDayTime() / 24000L;

                if (i < 5L || !pLevel.isDay()) {
                    return 0;
                } else if (randomsource.nextInt(5) != 0) {

                    return 0;
                } else {

                    int j = pLevel.players().size();
                    if (j < 1) {

                        return 0;
                    } else {

                        Player player = pLevel.players().get(randomsource.nextInt(j));
                        OldWorldFantasyMod.LOG.info("Selected player at {}", player.blockPosition());

                        if (player.isSpectator()) {

                            if (player.isSpectator()) {
                                OldWorldFantasyMod.LOG.info("STOP: player is spectator");
                                return 0;
                            }

                            return 0;
                        } else if (pLevel.isCloseToVillage(player.blockPosition(), 2)) {

                            return 0;
                        } else {

                            int k = (24 + randomsource.nextInt(24)) * (randomsource.nextBoolean() ? -1 : 1);
                            int l = (24 + randomsource.nextInt(24)) * (randomsource.nextBoolean() ? -1 : 1);
                            BlockPos.MutableBlockPos blockpos$mutableblockpos = player.blockPosition().mutable().move(k, 0, l);
                            OldWorldFantasyMod.LOG.info("Potential patrol position: {}", blockpos$mutableblockpos);

                            int i1 = 10;
                            if (!pLevel.hasChunksAt(
                                    blockpos$mutableblockpos.getX() - 10,
                                    blockpos$mutableblockpos.getZ() - 10,
                                    blockpos$mutableblockpos.getX() + 10,
                                    blockpos$mutableblockpos.getZ() + 10
                            )) {
                                return 0;
                            } else {

                                Holder<Biome> holder = pLevel.getBiome(blockpos$mutableblockpos);
                                if (holder.is(BiomeTags.WITHOUT_PATROL_SPAWNS)) {
                                    return 0;
                                } else {
                                    int j1 = 0;
                                    int k1 = (int)Math.ceil((double)pLevel.getCurrentDifficultyAt(blockpos$mutableblockpos).getEffectiveDifficulty()) + 1;

                                    for (int l1 = 0; l1 < k1; l1++) {
                                        j1++;
                                        blockpos$mutableblockpos.setY(
                                                pLevel.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, blockpos$mutableblockpos).getY()
                                        );
                                        if (l1 == 0) {
                                            if (!this.spawnPatrolMember(pLevel, blockpos$mutableblockpos, randomsource, true)) {
                                                break;
                                            }
                                        } else {
                                            this.spawnPatrolMember(pLevel, blockpos$mutableblockpos, randomsource, false);
                                        }

                                        blockpos$mutableblockpos.setX(
                                                blockpos$mutableblockpos.getX() + randomsource.nextInt(5) - randomsource.nextInt(5)
                                        );
                                        blockpos$mutableblockpos.setZ(
                                                blockpos$mutableblockpos.getZ() + randomsource.nextInt(5) - randomsource.nextInt(5)
                                        );
                                    }

                                    return j1;
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    // HELPER METHODS
    private boolean spawnPatrolMember(ServerLevel pLevel, BlockPos pPos, RandomSource pRandom, boolean pLeader) {

        BlockState blockstate = pLevel.getBlockState(pPos);
        EntityType<? extends PatrollingMonster> entityType;

        if (pLeader) {
            entityType = PATROL_LEADER;
        } else {
            entityType = getRandomBeastman(pRandom);
        }

        if (!NaturalSpawner.isValidEmptySpawnBlock(pLevel, pPos, blockstate, blockstate.getFluidState(), entityType)) {
            return false;
        } else {

            PatrollingMonster patrollingMonster = entityType.create(pLevel);
            if (patrollingMonster != null) {
                if (pLeader) {
                    patrollingMonster.setPatrolLeader(true);
                    patrollingMonster.findPatrolTarget();
                }
                patrollingMonster.setPos((double)pPos.getX(), (double)pPos.getY(), (double)pPos.getZ());
                patrollingMonster.finalizeSpawn(pLevel, pLevel.getCurrentDifficultyAt(pPos), MobSpawnType.PATROL, null);
                pLevel.addFreshEntityWithPassengers(patrollingMonster);

                OldWorldFantasyMod.LOG.info("Spawned {}{} at {}", entityType, pLeader ? " [LEADER]" : "", pPos);
                return true;
            } else {
                return false;
            }
        }
    }


    private static EntityType<? extends PatrollingMonster> getRandomBeastman(RandomSource random) {
        int totalWeight = GREENSKIN_WEIGHTS.stream().mapToInt(SpawnDataHelper::weight).sum();
        int roll = random.nextInt(totalWeight);
        for (SpawnDataHelper data : GREENSKIN_WEIGHTS) {
            roll -= data.weight();
            if (roll < 0) {
                return data.entityType();
            }
        }
        return GREENSKIN_WEIGHTS.getFirst().entityType();
    }
}
