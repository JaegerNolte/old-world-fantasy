package net.jaeger.oldworldfantasy.entity.ai.goals;

import net.jaeger.oldworldfantasy.entity.mobs.monsters.giants.AbstractGiant;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class GiantAttackGoal extends MeleeAttackGoal {

    private final AbstractGiant giant;
    private final String rawAnimation;
    private int attackDelay = 20;
    private int ticksUntilNextAttack = 40;
    private boolean shouldCountToNextAttack = false;

    public GiantAttackGoal(AbstractGiant giant, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen, String rawAnimation) {
        super(giant, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        this.giant = giant;
        this.rawAnimation = rawAnimation;
    }

    @Override
    public void start() {
        super.start();
        attackDelay = 20;
        ticksUntilNextAttack = 0;
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity pTarget) {
        if (isEnemyWithinAttackDistance(pTarget)) {
            shouldCountToNextAttack = true;

            if (isTimeToStartAttackAnimation()) {
                this.giant.triggerAnim("attack", rawAnimation); // time animation
                giant.setAggressive(true);
            }

            if (isTimeToAttack()) {
                this.giant.getLookControl().setLookAt(pTarget.getX(), pTarget.getY(), pTarget.getZ());
                performAttack(pTarget);
            }
        } else {
            resetAttackCooldown();
            shouldCountToNextAttack = false;
            giant.setAggressive(false);
            giant.attackAnim = 0;
        }
    }

    private boolean isEnemyWithinAttackDistance(LivingEntity pTarget){
        return this.giant.distanceTo(pTarget) <= 3.5f;
    }

    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(attackDelay * 2);
    }

    protected boolean isTimeToStartAttackAnimation() {
        return this.getTicksUntilNextAttack() <= attackDelay;
    }

    protected boolean isTimeToAttack() {
        return this.getTicksUntilNextAttack() <= 20;
    }

    protected void performAttack(LivingEntity pTarget) {
        this.resetAttackCooldown();
        this.giant.swing(InteractionHand.MAIN_HAND);
        this.giant.doHurtTarget(pTarget);
    }

    @Override
    public void tick() {
        super.tick();
        if (shouldCountToNextAttack) {
            this.ticksUntilNextAttack = Math.max(this.getTicksUntilNextAttack() - 1, 0);
        }
    }

    @Override
    public void stop() {
        this.giant.setAggressive(false);
        super.stop();
    }
}
