package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
//import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.oop.game.actions.PerpetualReactorHeating;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
//import sk.tuke.kpi.oop.game.tools.FireExtinguisher;
//import sk.tuke.kpi.oop.game.tools.Hammer;
//import sk.tuke.kpi.oop.game.tools.Usable;

import java.util.HashSet;
import java.util.Set;


public class Reactor extends AbstractActor implements Switchable, Repairable {

    private float temperature;
    private float damage;

    private Animation normalAnimation;
    private Animation destroyAnimation;
    private Animation overheatedAnimation;
    private Animation offAnimation;
    private Animation reactor_extinguished;

    private boolean running;
    private Set<EnergyConsumer> devices;


    public Reactor () {
        this.temperature = 0.0f;
        this.damage = 0.0f;
        running = true;
        devices = new HashSet<>();

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
        } if (this.temperature > 6000) this.temperature = 6000;
        updateAnimation();
    }
    public void decreaseTemperature (int decrement) {
        if (!this.running || this.damage >= 100 || decrement < 0) return;

        if (damage >= 50) temperature -= Math.floor((float)decrement / 2);
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
            if (this.damage == 100 && this.temperature == 6000) {
                running = false;
                setAnimation(destroyAnimation);
            }
        }
        if (this.temperature == 2000 && this.damage == 100) {
            setAnimation(this.reactor_extinguished);
        }
    }

    public boolean repair() {
        if (this.damage == 100) return false;
//        if (molotok == null || getDamage() <= 0 || getDamage() == 100) return;
//        if (molotok.getRemainingUses() < 1) return;

       // molotok.useWith(this);
        if ((this.damage-50)>=0) {
            this.damage-=50;
            this.temperature = 4000 * (this.damage*0.01f) + 2000;
        } else if (this.temperature != 0.0f){
            this.damage = 0;
            this.temperature = 2000;
        }
        updateAnimation();

        return true;
    }

    public void turnOn() {
        this.running = true;
        updateAnimation();
    }

    public void turnOff() {
        this.running = false;
        updateAnimation();
    }

//    public boolean isRunning() {
//        return this.running;
//    }
    public boolean isOn() {
        return this.running;
    }

    public void addDevice(EnergyConsumer shtuchka) {
        this.devices.add(shtuchka);
//        if (this.running && this.damage < 100)
//            Light.light_On_Off(true);
        if (isOn() && this.damage<100) shtuchka.setPowered(true);
    }

    public void removeDevice(EnergyConsumer shtuchka) {
        shtuchka.setPowered(false);
        this.devices.remove(shtuchka);
    }


    /// ***** FIRE
    public boolean extinguish() {
        if (this.damage < 100 && this.temperature != 6000) return false;

        //horny_fire.useWith();
        if (temperature > 4000) temperature = 4000;

        setAnimation(new Animation("sprites/reactor_extinguished.png"));
        return true;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        scene.scheduleAction(new PerpetualReactorHeating(1), this);

        // v metode addedToScene triedy Reactor
       // new PerpetualReactorHeating(1).scheduleFor(this);
    }
}
