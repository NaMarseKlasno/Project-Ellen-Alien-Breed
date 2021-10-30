package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;


public class Light extends AbstractActor
{
    private Animation light_on;
    private Animation light_off;

    private boolean status;
    private boolean status_power;

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
        if (this.status)  {
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






}
