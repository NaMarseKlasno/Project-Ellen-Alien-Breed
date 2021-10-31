package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.actions.Invoke;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;


public class SmartCooler extends Cooler {

    private Reactor REACTOR;

    public SmartCooler(Reactor REACTOR) {
        super(REACTOR);

        this.REACTOR = REACTOR;
    }


    private void smartCooler() {
        if (this.REACTOR == null) return;

        if (this.REACTOR.getTemperature() >= 2500) turnOn();
        if (this.REACTOR.getTemperature() <= 1500) turnOff();
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::smartCooler)).scheduleFor(this);
    }
}

