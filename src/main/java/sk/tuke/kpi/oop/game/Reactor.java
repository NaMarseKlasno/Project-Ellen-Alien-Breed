package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;


class Reactor extends AbstractActor
{
    private int temperature;
    private double damage;

    private Animation normalAnimation;
    private Animation destroyAnimation;
    private Animation overheatedAnimation;
    private Animation offAnimation;

    private boolean running;

    public Reactor () {
        this.temperature = 0;
        this.damage = 0.0D;
        this.running = true;

        // create animation object
        this.normalAnimation = new Animation("sprites/reactor_on.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        this.destroyAnimation = new Animation("sprites/reactor.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        this.overheatedAnimation = new Animation("sprites/reactor_hot.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        this.offAnimation = new Animation("sprites/reactor_broken.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);


        // set actor's animation to just created Animation object
        setAnimation(this.normalAnimation);
    }


    public void updateAnimation() {
        if (!this.running && this.damage != 100) {
            super.setAnimation(offAnimation);
        } else {
            if (this.temperature < 4000) {
                setAnimation(normalAnimation);
            }
            if (this.temperature > 4000 && this.temperature < 6000) {
                setAnimation(overheatedAnimation);
            }
            if (this.damage == 100) {
                running = false;
                setAnimation(destroyAnimation);
            }
        }
    }

    public void increaseTemperature (int increment) {
        this.temperature += increment;
    }
    public void decreaseTemperature (int decrement) {
        this.temperature -= decrement;
    }


    public int getTemperature () {
        return this.temperature;
    }
    public double getDamage () {
        return this.damage;
    }


}
