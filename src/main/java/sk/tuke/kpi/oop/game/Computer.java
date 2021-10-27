package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;


public class Computer extends AbstractActor
{
    public Computer()
    {
        setAnimation(new Animation(
            "sprites/computer.png",
            80, 48, 0.2f,
            Animation.PlayMode.LOOP_PINGPONG
        ));
    }

    public int add(int x, int y) {
        return x + y;
    }

    public float add(float x, float y) {
        return x + y;
    }




}
