package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;


public class Cooler extends AbstractActor {
    private Animation cooler;
    //private Animation cooler_off;

    private boolean status;
//    private boolean status_power;
    private Reactor REACTOR;


    public Cooler(Reactor REACTOR) {
        this.status = false;
        this.REACTOR = REACTOR;

        this.cooler = new Animation("sprites/fan.png", 32, 32, 0.2f, Animation.PlayMode.LOOP_PINGPONG);
        //this.cooler_off= new Animation("sprites/fan.png",32, 32, 0);//Animation.PlayMode.LOOP);
        this.cooler.pause();
        setAnimation(cooler);
    }

    public void turnOn() {
        if (this.REACTOR == null) return;
        this.status = true;
        this.cooler.play();
    }

    public void turnOff() {
        if (this.REACTOR == null) return;
        this.status = false;
        this.cooler.pause();
    }

    public boolean ifOn() {
        return this.status;
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
}
