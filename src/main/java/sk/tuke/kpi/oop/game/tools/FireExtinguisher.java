package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;


public class FireExtinguisher extends BreakableTool {

    private Animation extinguisher;

    public FireExtinguisher() {
        super(1);

        this.extinguisher = new Animation("sprites/extinguisher.png");
        setAnimation(this.extinguisher);
    }

    //@Override
    public void useWith(Reactor REACTOR) {
        super.useWith(REACTOR);
        REACTOR.extinguish();
    }

}

