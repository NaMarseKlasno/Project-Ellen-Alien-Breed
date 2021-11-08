package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.actions.ActionSequence;


public class TimeBomb extends AbstractActor {

//    private Animation timeBomb_off;
    private Animation timeBomb_on;
    private Animation timeBomb_exploded;

    private boolean status;

//    private float start_time;
//    private float sub_time_seconds;
    private float TIME;
    private Disposable disposable;


    public TimeBomb(float TIME)
    {
        this.status = false;
        this.TIME = TIME;

        this.timeBomb_on = new Animation("sprites/bomb_activated.png", 16, 16, 0.2f, Animation.PlayMode.LOOP_PINGPONG);
        this.timeBomb_exploded = new Animation("sprites/small_explosion.png", 16, 16, 0.2f, Animation.PlayMode.ONCE);

        setAnimation(new Animation("sprites/bomb.png"));
    }


    public void activate()
    {
        if (isActivated()) return;

        setAnimation(this.timeBomb_on);
        this.status = true;

        this.disposable = new ActionSequence<>(
            new Wait<>(this.TIME),
            new Invoke<>(this::explode)
            //new Wait<>(0.3f*8f),
            //new Invoke<>(this::remove)
        ).scheduleFor(this);
    }

    public boolean isActivated() {
        return this.status;
    }

    public void explode() {
        setAnimation(this.timeBomb_exploded);
        this.disposable.dispose();
        this.disposable =
            new When   <> (() -> this.getAnimation().getCurrentFrameIndex() >= this.getAnimation().getFrameCount()-1,
            new Invoke <> (() -> getScene().removeActor(this))
        ).scheduleFor(this);
        //this.status = true;
    }
    public void activateBomb() {
        setAnimation(this.timeBomb_on);
    }

    public void setTimeBombExploded() {
        setAnimation(timeBomb_exploded);
    }

    public float getTIME () {
        return this.TIME;
    }

    public void setTrueStatus() {
        this.status = true;
    }
}
