package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;


public class FireExtinguisher extends BreakableTool<Reactor> {

    private Animation extinguisher;

    public FireExtinguisher() {
        super(1);

        this.extinguisher = new Animation("sprites/extinguisher.png");
        setAnimation(this.extinguisher);
    }

    @Override
    public void useWith(Reactor REACTOR) {
        if (REACTOR == null || super.getRemainingUses() < 1) return;

        super.useWith(REACTOR);
        REACTOR.extinguish();
    }

}

