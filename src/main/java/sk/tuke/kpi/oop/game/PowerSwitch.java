package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;


public class PowerSwitch extends AbstractActor {

    //private Animation powerSwitch;
    private Switchable SWITCH;


    public PowerSwitch(Switchable switchable) {
        this.SWITCH = switchable;

        //this.powerSwitch = new Animation("sprites/switch.png");
        setAnimation(new Animation("sprites/switch.png"));
    }

    public void toggle(Reactor reactor) {
        if (reactor.getDamage() < 6000)
        {
            if (reactor.isOn()) reactor.turnOff();
            else reactor.turnOn();
        }
    }

    public Switchable getDevice() {
        return this.SWITCH;
    }

    public void switchOn() {
        if(this.SWITCH != null) this.SWITCH.turnOn();
    }

    public void switchOff() {
        if(this.SWITCH != null) this.SWITCH.turnOff();
    }

}
