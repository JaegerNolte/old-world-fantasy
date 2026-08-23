package net.jaeger.oldworldfantasy.entity.ai.goals;

import net.jaeger.oldworldfantasy.entity.custom.greenskin.goblin.Goblin;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class GoblinAttackGoal extends MeleeAttackGoal {

    private final Goblin goblin;
    private final String rawAnimation;
    private int attackDelay = 20;
    private int ticksUntilNextAttack = 40;
    private boolean shouldCountToNextAttack = false;

    public GoblinAttackGoal(Goblin goblin, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen, String rawAnimation) {
        super(goblin, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        this.goblin = goblin;
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
                this.goblin.triggerAnim("attack", rawAnimation); // time animation
                goblin.setAggressive(true);
            }

            if (isTimeToAttack()) {
                this.goblin.getLookControl().setLookAt(pTarget.getX(), pTarget.getY(), pTarget.getZ());
                performAttack(pTarget);
            }
        } else {
            resetAttackCooldown();
            shouldCountToNextAttack = false;
            goblin.setAggressive(false);
            goblin.attackAnim = 0;
        }
    }

    private boolean isEnemyWithinAttackDistance(LivingEntity pTarget){
        return this.goblin.distanceTo(pTarget) <= 2f; // Modify distance?
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
        this.goblin.swing(InteractionHand.MAIN_HAND);
        this.goblin.doHurtTarget(pTarget);
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
        this.goblin.setAggressive(false);
        super.stop();
    }
}
