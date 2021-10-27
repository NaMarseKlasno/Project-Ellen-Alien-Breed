package sk.tuke.kpi.oop.game;

//import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;


public class Reactor extends AbstractActor
{
    private int temperature;
    private int damage;

    private Animation normalAnimation;
    private Animation destroyAnimation;
    private Animation overheatedAnimation;
    private Animation offAnimation;

    private boolean running;

    public Reactor () {
        this.temperature = 0;
        this.damage = 0;
        running = true;

        this.normalAnimation = new Animation("sprites/reactor_on.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        this.destroyAnimation = new Animation("sprites/reactor.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        this.overheatedAnimation = new Animation("sprites/reactor_hot.png", 80, 80, 0.05f, Animation.PlayMode.LOOP_PINGPONG);
        this.offAnimation = new Animation("sprites/reactor_broken.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);

        setAnimation(normalAnimation);
    }
    public int getTemperature () {
        return this.temperature;
    }
    public int getDamage () {
        return this.damage;
    }

    public void increaseTemperature (int increment) {
        if (increment < 1) return;

        this.temperature += increment;
        if (this.temperature > 2000) {
            this.damage = ((this.temperature - 2000) * 100) / 4000;

        }
        updateAnimation();
    }
    public void decreaseTemperature (int decrement) {
        this.temperature -= decrement;
        updateAnimation();
    }

    public void updateAnimation() {
        if (!this.running && this.damage != 100) {
            super.setAnimation(offAnimation);
        }
        else {
            if (getTemperature() < 4000) {
                setAnimation(normalAnimation);
            }
            if (getTemperature() > 4000 && getTemperature() < 6000) {
                setAnimation(overheatedAnimation);
            }
            if (getDamage() == 100) {
                running = false;
                setAnimation(destroyAnimation);
            }
        }
    }
}
