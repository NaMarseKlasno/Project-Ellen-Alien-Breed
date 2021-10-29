package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;


public class FireExtinguisher extends AbstractActor
{
    private Animation FireExtinguisher;

    public FireExtinguisher ()
    {

        this.FireExtinguisher = new Animation("sprites/");
        setAnimation(FireExtinguisher);
    }
}
