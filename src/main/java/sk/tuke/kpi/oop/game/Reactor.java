package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;


class Reactor extends AbstractActor
{
    private int temperature;
    private double damage;
    private Animation normalAnimation;

    public Reactor () {
        this.temperature = 0;
        this.damage = 0;

        // create animation object
        this.normalAnimation = new Animation("sprites/reactor_on.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        // set actor's animation to just created Animation object
        setAnimation(this.normalAnimation);

    }
}
