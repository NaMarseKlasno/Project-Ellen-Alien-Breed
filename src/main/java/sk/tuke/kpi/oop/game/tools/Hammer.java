package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;


public class Hammer extends BreakableTool {

    private Animation hammer;

    public Hammer() {
        super(1);

        this.hammer = new Animation("sprites/hammer.png");
        setAnimation(this.hammer);
    }

    //@Override
    public void useWith(Reactor REACTOR){
        super.useWith(REACTOR);
        REACTOR.repair();
    }
}

