package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;


public class FireExtinguisher extends BreakableTool<Reactor> implements Collectible {
    //private Animation extinguisher;

    public FireExtinguisher() {
        super(1);

        //this.extinguisher = new Animation("sprites/extinguisher.png");
        setAnimation(new Animation("sprites/extinguisher.png"));
    }

    @Override
    public void useWith(Reactor REACTOR) {
        if (REACTOR == null || super.getRemainingUses() < 1 || REACTOR.getDamage() != 100) return;

        super.useWith(REACTOR);
        REACTOR.extinguish();
    }

}

