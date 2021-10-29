package sk.tuke.kpi.oop.game;

//import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.tools.Hammer;


public class Reactor extends AbstractActor
{
    private float temperature;
    private float damage;

    private Animation normalAnimation;
    private Animation destroyAnimation;
    private Animation overheatedAnimation;
    private Animation offAnimation;

    private boolean running;


    public Reactor () {
        this.temperature = 0.0f;
        this.damage = 0.0f;
        running = true;

        this.normalAnimation = new Animation("sprites/reactor_on.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        this.offAnimation = new Animation("sprites/reactor.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        this.overheatedAnimation = new Animation("sprites/reactor_hot.png", 80, 80, 0.05f, Animation.PlayMode.LOOP_PINGPONG);
        this.destroyAnimation = new Animation("sprites/reactor_broken.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);

        setAnimation(normalAnimation);
    }


    public float getTemperature () {
        return this.temperature;
    }
    public float getDamage () {
        return this.damage;
    }

    public void increaseTemperature (int increment) {
        if (increment < 1) return;

        this.temperature += increment;
        if (this.temperature > 2000 && this.damage < 100) {
            this.damage = ((this.temperature - 2000) * 100) / 4000;
            if (this.damage>100) this.damage = 100;
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
        if (this.running) {
            if (getTemperature() < 4000) {
                setAnimation(normalAnimation);
            }
            if (getTemperature() > 4000 && getTemperature() < 6000) {
                setAnimation(overheatedAnimation);
            }
            if (this.damage == 100) {
                running = false;
                setAnimation(destroyAnimation);
            }
        }
    }

    public void repairWith(Hammer molotok) {
        if (molotok == null || getDamage() <= 0 || getDamage() == 100) return;

        if ((this.damage-50)>=0) {
            this.damage-=50;
            this.temperature = 4000 * (this.damage*0.01f) + 2000;
        } else {
            this.damage = 0;
            this.temperature = 2000;
        }
    }

    public void turnOn() {
        this.running = true;
        updateAnimation();
    }

    public void turnOff() {
        this.running = false;
        updateAnimation();
    }

    public boolean isRunning() {
        return this.running;
    }
    public boolean isOn() {
        return this.isRunning();
    }


    public void addLight(Light Light) {
        if (this.running) Light.light_On_Off(true);
    }


}
