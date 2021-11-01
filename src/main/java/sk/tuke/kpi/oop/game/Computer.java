package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;


public class Computer extends AbstractActor implements EnergyConsumer
{
    private boolean status_power;

    public Computer()
    {
        setAnimation(new Animation(
            "sprites/computer.png",
            80, 48, 0.2f,
            Animation.PlayMode.LOOP_PINGPONG
        ));
    }


    public int add(int x, int y) {
        if (!status_power) return 0;
        return x + y;
    }
    public float add(float x, float y) {
        if (!status_power) return 0;
        return x + y;
    }
    public int sub(int x, int y) {
        if (!status_power) return 0;
        return x - y;
    }
    public float sub(float x, float y) {
        if (!status_power) return 0;
        return x - y;
    }

    @Override
    public void setPowered(boolean tmp) {
        this.status_power = tmp;
        if (this.status_power) this.getAnimation().play();
        else this.getAnimation().pause();
    }
}
