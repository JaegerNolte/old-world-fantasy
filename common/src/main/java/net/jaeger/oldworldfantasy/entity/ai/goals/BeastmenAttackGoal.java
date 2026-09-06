package net.jaeger.oldworldfantasy.entity.ai.goals;

import net.jaeger.oldworldfantasy.entity.mobs.beastmen.AbstractBeastmen;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class BeastmenAttackGoal extends MeleeAttackGoal {

    private final AbstractBeastmen beastmen;
    private final String rawAnimation;
    private int attackDelay = 20;
    private int ticksUntilNextAttack = 40;
    private boolean shouldCountToNextAttack = false;

    public BeastmenAttackGoal(AbstractBeastmen beastmen, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen, String rawAnimation) {
        super(beastmen, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        this.beastmen = beastmen;
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
                this.beastmen.triggerAnim("attack", rawAnimation); // time animation
                beastmen.setAggressive(true);
            }

            if (isTimeToAttack()) {
                this.beastmen.getLookControl().setLookAt(pTarget.getX(), pTarget.getY(), pTarget.getZ());
                performAttack(pTarget);
            }
        } else {
            resetAttackCooldown();
            shouldCountToNextAttack = false;
            beastmen.setAggressive(false);
            beastmen.attackAnim = 0;
        }
    }

    private boolean isEnemyWithinAttackDistance(LivingEntity pTarget){
        return this.beastmen.distanceTo(pTarget) <= 2f; // Modify distance?
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
        this.beastmen.swing(InteractionHand.MAIN_HAND);
        this.beastmen.doHurtTarget(pTarget);
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
        this.beastmen.setAggressive(false);
        super.stop();
    }
}
