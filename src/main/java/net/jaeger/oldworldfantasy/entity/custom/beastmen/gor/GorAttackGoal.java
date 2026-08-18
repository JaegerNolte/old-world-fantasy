package net.jaeger.oldworldfantasy.entity.custom.beastmen.gor;

import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class GorAttackGoal extends MeleeAttackGoal {

    private final Gor gor;
    private int raiseArmTicks;


    public GorAttackGoal(Gor pGor, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pGor, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        this.gor = pGor;
    }

    @Override
    public void start() {
        super.start();
        this.raiseArmTicks = 0;
    }

    @Override
    public void stop() {
        super.stop();
        this.gor.setAggressive(false);
    }

    @Override
    public void tick() {
        super.tick();
        this.raiseArmTicks++;
        if (this.raiseArmTicks >= 5 && this.getTicksUntilNextAttack() < this.getAttackInterval() / 2) {
            this.gor.setAggressive(true);
        } else {
            this.gor.setAggressive(false);
        }
    }
}
