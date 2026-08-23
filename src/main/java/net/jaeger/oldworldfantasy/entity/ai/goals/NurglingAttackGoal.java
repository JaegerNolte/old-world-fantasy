package net.jaeger.oldworldfantasy.entity.ai.goals;

import net.jaeger.oldworldfantasy.entity.custom.nurgling.NurglingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class NurglingAttackGoal extends MeleeAttackGoal {

    private final NurglingEntity nurgling;
    private int raiseArmTicks;


    public NurglingAttackGoal(NurglingEntity pNurgling, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pNurgling, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        this.nurgling = pNurgling;
    }

    @Override
    public void start() {
        super.start();
        this.raiseArmTicks = 0;
    }

    @Override
    public void stop() {
        super.stop();
        this.nurgling.setAggressive(false);
    }

    @Override
    public void tick() {
        super.tick();
        this.raiseArmTicks++;
        if (this.raiseArmTicks >= 5 && this.getTicksUntilNextAttack() < this.getAttackInterval() / 2) {
            this.nurgling.setAggressive(true);
        } else {
            this.nurgling.setAggressive(false);
        }
    }
}
