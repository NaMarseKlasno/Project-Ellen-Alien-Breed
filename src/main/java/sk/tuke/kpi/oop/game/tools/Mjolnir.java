package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;


public class Mjolnir extends Hammer {

    private int number_of_uses;

    public Mjolnir() {
        super(4);
//        this.number_of_uses = 4;
//        setAnimation(new Animation("sprites/hammer.png"));
    }

//    //@Override
//    public void useWith(Reactor REACTOR) {
//        //super.useWith(REACTOR);
//        if (this.number_of_uses == 0)
//            return;
//
//        --number_of_uses;
//
//        if (this.number_of_uses == 0)
//            getScene().removeActor(this);
//        REACTOR.repair();
//    }

}
