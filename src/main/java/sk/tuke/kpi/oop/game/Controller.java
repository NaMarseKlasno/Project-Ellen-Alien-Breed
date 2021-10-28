package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;


public class Controller extends AbstractActor {

    private Animation controller;

    public Controller() {

        this.controller = new Animation("sprites/switch.png");
        setAnimation(controller);
    }

    public void toggle(Reactor reactor) {
        if (reactor.getDamage() < 6000)
        {
            if (reactor.isRunning()) reactor.turnOff();
            else reactor.turnOn();
        }
    }
}
