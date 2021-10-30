package sk.tuke.kpi.oop.game;

//import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.tools.FireExtinguisher;
import sk.tuke.kpi.oop.game.tools.Hammer;


public class Reactor extends AbstractActor
{
    private float temperature;
    private float damage;

    private Animation normalAnimation;
    private Animation destroyAnimation;
    private Animation overheatedAnimation;
    private Animation offAnimation;

    private Animation reactor_extinguished;

    private boolean running;


    public Reactor () {
        this.temperature = 0.0f;
        this.damage = 0.0f;
        running = true;

        this.normalAnimation = new Animation("sprites/reactor_on.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        this.offAnimation = new Animation("sprites/reactor.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        this.overheatedAnimation = new Animation("sprites/reactor_hot.png", 80, 80, 0.05f, Animation.PlayMode.LOOP_PINGPONG);
        this.destroyAnimation = new Animation("sprites/reactor_broken.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        this.reactor_extinguished = new Animation("sprites/reactor_extinguished.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);

        setAnimation(normalAnimation);
    }


    public int getTemperature () {
        return (int)this.temperature;
    }
    public int getDamage () {
        return (int)this.damage;
    }

    public void increaseTemperature (int increment) {
        if (increment < 1 || !this.running) return;

        this.temperature += increment;
        if (this.temperature > 2000 && this.damage < 100) {
            this.damage = ((this.temperature - 2000) * 100) / 4000;
            if (this.damage>100) this.damage = 100;
        }
        updateAnimation();
    }
    public void decreaseTemperature (int decrement) {
        if (!this.running || this.damage >= 100 || decrement < 0) return;

        if (damage >= 50) temperature -= Math.floor(decrement / 2);
        else temperature -= decrement;

        updateAnimation();
    }

    public void updateAnimation() {
        if (!this.running && this.damage != 100) {
            super.setAnimation(offAnimation);
        }
        if (this.running) {
            if (getTemperature() <= 4000) {
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
        if (this.temperature == 2000 && this.damage == 100) {
            setAnimation(this.reactor_extinguished);
        }
    }

    public void repairWith(Hammer molotok) {
        if (molotok == null || getDamage() <= 0 || getDamage() == 100) return;
        if (molotok.getRemainingUses() < 1) return;

        molotok.use();
        if ((this.damage-50)>=0) {
            this.damage-=50;
            this.temperature = 4000 * (this.damage*0.01f) + 2000;
        } else if (this.temperature != 0.0f){
            this.damage = 0;
            this.temperature = 2000;
        }
        updateAnimation();
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
        if (this.running && this.damage < 100)
            Light.light_On_Off(true);
    }

    public void removeLight(Light Light) {
        Light.light_On_Off(false);
    }


    /// ***** FIRE
    public void extinguishWith(FireExtinguisher horny_fire) {
        if (horny_fire.getRemainingUses() == 0) {
            return;
        }
        horny_fire.use();
        if (temperature > 4000) {
            temperature = 4000;
        }
        setAnimation(new Animation("sprites/reactor_extinguished.png"));

    }



}
