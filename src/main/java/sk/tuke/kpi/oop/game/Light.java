package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;


public class Light extends AbstractActor implements Switchable, EnergyConsumer
{
    private Animation light_on;
    private Animation light_off;

    private boolean status;
    private boolean status_power;
    private boolean isOn;


    public Light()
    {
        this.status = false;
        this.status_power = false;

        this.light_on = new Animation("sprites/light_on.png");
        this.light_off = new Animation("sprites/light_off.png");
        setAnimation(light_off);
    }


    public void toggle()
    {
        if (this.status) {
            if (status_power) setAnimation(light_off);
            this.status = false;
        } else {
            if (status_power) setAnimation(light_on);
            this.status = true;
        }
    }

    public void light_On_Off(boolean bool) {
        this.status_power = bool;
    }

    @Override
    public void turnOn() {
        this.isOn = true;
        this.status = !isOn();
        toggle();
    }

    @Override
    public void turnOff() {
        this.isOn = false;
        this.status = !isOn();
        toggle();
    }

    @Override
    public boolean isOn() {
        return this.isOn;
    }

    @Override
    public void setPowered(boolean tmp) {
        this.status_power = tmp;
        this.status = isOn();
        if (this.status_power) {
            if (this.status) setAnimation(light_on);
        }
        else {
            this.status = false;
            setAnimation(light_off);
        }
    }
}
