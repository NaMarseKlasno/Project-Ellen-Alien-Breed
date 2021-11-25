package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;
import sk.tuke.kpi.oop.game.Repairable;

public class Hammer extends BreakableTool<Repairable> implements Collectible {
    //private Animation hammer;

    public Hammer(int i) {
        super(i);

        //this.hammer = new Animation("sprites/hammer.png");
        setAnimation(new Animation("sprites/hammer.png"));
    }

    public Hammer() {
        this(1);
    }

    @Override
    public void useWith(Repairable REACTOR) {
        if (REACTOR == null || super.getRemainingUses() < 1) return;

        super.useWith(REACTOR);
        REACTOR.repair();
    }

    @Override
    public Class<Repairable> getUsingActorClass() {
        return Repairable.class;
    }
}

