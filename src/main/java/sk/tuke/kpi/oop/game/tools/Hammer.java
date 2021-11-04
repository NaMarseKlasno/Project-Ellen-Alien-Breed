package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;


public class Hammer extends BreakableTool<Reactor> {

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
    public void useWith(Reactor REACTOR){
        if (REACTOR == null || super.getRemainingUses() < 1 || REACTOR.getDamage() == 0) return;

        super.useWith(REACTOR);
        REACTOR.repair();
    }
}

