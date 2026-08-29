package net.jaeger.oldworldfantasy.entity.ai.goals;

import com.google.common.collect.Sets;
import net.jaeger.oldworldfantasy.worldgen.raids.ModRaid;
import net.jaeger.oldworldfantasy.entity.mobs.ModRaider;
import net.jaeger.oldworldfantasy.worldgen.raids.ModRaids;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class PathfindToRaidGoal <T extends ModRaider> extends Goal {
    private static final int RECRUITMENT_SEARCH_TICK_DELAY = 20;
    private static final float SPEED_MODIFIER = 1.0F;
    private final T mob;
    private int recruitmentTick;

    public PathfindToRaidGoal(T pMob) {
        this.mob = pMob;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        return this.mob.getTarget() == null
                && !this.mob.hasControllingPassenger()
                && this.mob.hasActiveRaid()
                && !this.mob.getCurrentRaid().isOver()
                && !((ServerLevel)this.mob.level()).isVillage(this.mob.blockPosition());
    }

    @Override
    public boolean canContinueToUse() {
        return this.mob.hasActiveRaid()
                && !this.mob.getCurrentRaid().isOver()
                && this.mob.level() instanceof ServerLevel
                && !((ServerLevel)this.mob.level()).isVillage(this.mob.blockPosition());
    }

    @Override
    public void tick() {
        if (this.mob.hasActiveRaid()) {
            ModRaid raid = this.mob.getCurrentRaid();
            if (this.mob.tickCount > this.recruitmentTick) {
                this.recruitmentTick = this.mob.tickCount + 20;
                this.recruitNearby(raid);
            }

            if (!this.mob.isPathFinding()) {
                Vec3 vec3 = DefaultRandomPos.getPosTowards(this.mob, 15, 4, Vec3.atBottomCenterOf(raid.getCenter()), (float) (Math.PI / 2));
                if (vec3 != null) {
                    this.mob.getNavigation().moveTo(vec3.x, vec3.y, vec3.z, 1.0);
                }
            }
        }
    }

    private void recruitNearby(ModRaid pRaid) {
        if (pRaid.isActive()) {
            Set<ModRaider> set = Sets.newHashSet();
            List<ModRaider> list = this.mob
                    .level()
                    .getEntitiesOfClass(ModRaider.class, this.mob.getBoundingBox().inflate(16.0), p_25712_ -> !p_25712_.hasActiveRaid() && ModRaids.canJoinRaid(p_25712_, pRaid));
            set.addAll(list);

            for (ModRaider raider : set) {
                pRaid.joinRaid(pRaid.getGroupsSpawned(), raider, null, true);
            }
        }
    }
}
