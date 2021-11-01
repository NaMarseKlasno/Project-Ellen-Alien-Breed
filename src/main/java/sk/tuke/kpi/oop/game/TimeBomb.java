package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.actions.ActionSequence;


public class TimeBomb extends AbstractActor {

    private Animation timeBomb_off;
    private Animation timeBomb_on;
    private Animation timeBomb_exploded;

    private boolean status;

    private float start_time;
    private float sub_time_seconds;
    private float TIME;


    public TimeBomb(float TIME)
    {
        this.status = false;
        this.TIME = TIME;

        this.timeBomb_off = new Animation("sprites/bomb.png");
        this.timeBomb_on = new Animation("sprites/bomb_activated.png", 16, 16, 0.2f);
        this.timeBomb_exploded = new Animation("sprites/small_explosion.png", 16, 16, 0.3f);

        setAnimation(this.timeBomb_off);
    }


    public void activate()
    {
        setAnimation(this.timeBomb_on);

        new ActionSequence<>(
            new Wait<>(this.TIME),
            new Invoke<>(this::explode),
            new Wait<>(2),
            new Invoke<>(this::remove)
        ).scheduleFor(this);
    }

    public boolean isActivated() {
        return this.status;
    }

    private void remove() {
        getScene().removeActor(this);
    }

    public void explode() {
        setAnimation(this.timeBomb_exploded);
        this.status = true;
    }

}