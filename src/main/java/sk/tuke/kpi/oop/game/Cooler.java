package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;


public class Cooler extends AbstractActor implements Switchable
{
    private Animation cooler_anim;
    //private Animation cooler_off;
    private boolean status;
//    private boolean status_power;
    private Reactor REACTOR;

    public Cooler(Reactor R) {
        this.status = false;
        this.REACTOR = R;

        this.cooler_anim = new Animation("sprites/fan.png", 32, 32, 0.2f, Animation.PlayMode.LOOP_PINGPONG);
        //this.cooler_off= new Animation("sprites/fan.png",32, 32, 0);//Animation.PlayMode.LOOP);
        this.cooler_anim.pause();
        setAnimation(cooler_anim);
    }


    private void coolReactor() {
        if (this.REACTOR == null) return;
        if (this.status) this.REACTOR.decreaseTemperature(1);
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::coolReactor)).scheduleFor(this);
    }

    @Override
    public void turnOn() {
        if (this.REACTOR == null) return;
        this.status = true;
        this.cooler_anim.play();
    }

    @Override
    public void turnOff() {
        if (this.REACTOR == null) return;
        this.status = false;
        this.cooler_anim.pause();
    }

    @Override
    public boolean isOn() {
        return this.status;
    }
}
