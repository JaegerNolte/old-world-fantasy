package net.jaeger.oldworldfantasy.entity.custom.ungor;

import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class UngorAttackGoal extends MeleeAttackGoal {

    private final UngorEntity ungor;
    private int raiseArmTicks;


    public UngorAttackGoal(UngorEntity pUngor, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pUngor, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        this.ungor = pUngor;
    }

    @Override
    public void start() {
        super.start();
        this.raiseArmTicks = 0;
    }

    @Override
    public void stop() {
        super.stop();
        this.ungor.setAggressive(false);
    }

    @Override
    public void tick() {
        super.tick();
        this.raiseArmTicks++;
        if (this.raiseArmTicks >= 5 && this.getTicksUntilNextAttack() < this.getAttackInterval() / 2) {
            this.ungor.setAggressive(true);
        } else {
            this.ungor.setAggressive(false);
        }
    }
}
