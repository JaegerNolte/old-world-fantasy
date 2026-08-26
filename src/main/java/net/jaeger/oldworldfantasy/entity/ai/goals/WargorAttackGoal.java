package net.jaeger.oldworldfantasy.entity.ai.goals;

import net.jaeger.oldworldfantasy.entity.mobs.beastmen.wargor.Wargor;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class WargorAttackGoal extends MeleeAttackGoal {

    private final Wargor gor;
    private final String rawAnimation;
    private int attackDelay = 20;
    private int ticksUntilNextAttack = 40;
    private boolean shouldCountToNextAttack = false;

    public WargorAttackGoal(Wargor pWargor, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen, String rawAnimation) {
        super(pWargor, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        this.gor = pWargor;
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
                this.gor.triggerAnim("attack", rawAnimation); // time animation
                gor.setAggressive(true);
            }

            if (isTimeToAttack()) {
                this.gor.getLookControl().setLookAt(pTarget.getX(), pTarget.getY(), pTarget.getZ());
                performAttack(pTarget);
            }
        } else {
            resetAttackCooldown();
            shouldCountToNextAttack = false;
            gor.setAggressive(false);
            gor.attackAnim = 0;
        }
    }

    private boolean isEnemyWithinAttackDistance(LivingEntity pTarget){
        return this.gor.distanceTo(pTarget) <= 2f; // Modify distance?
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
        this.gor.swing(InteractionHand.MAIN_HAND);
        this.gor.doHurtTarget(pTarget);
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
        this.gor.setAggressive(false);
        super.stop();
    }
}
