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

    private int temperature;
    private int damage;

    private Animation normalAnimation;
    private Animation destroyAnimation;
    private Animation overheatedAnimation;
    private Animation offAnimation;
    private Animation reactor_extinguished;

    private boolean running;
    private Set<EnergyConsumer> devices;


    public Reactor () {
        this.temperature = 0;
        this.damage = 0;
        running = false;
        devices = new HashSet<>();

        this.normalAnimation = new Animation("sprites/reactor_on.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        this.offAnimation = new Animation("sprites/reactor.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        this.overheatedAnimation = new Animation("sprites/reactor_hot.png", 80, 80, 0.05f, Animation.PlayMode.LOOP_PINGPONG);
        this.destroyAnimation = new Animation("sprites/reactor_broken.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        this.reactor_extinguished = new Animation("sprites/reactor_extinguished.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);

        setAnimation(offAnimation);
    }


    public int getTemperature () {
        return this.temperature;
    }
    public int getDamage () {
        return this.damage;
    }

    public void increaseTemperature(int increment)
    {
        if (!this.running || increment < 0 || this.temperature >= 6000) return;

        if (!checktemp1(increment)) temperature += 2 * increment;

        if (temperature < 6000 && temperature > 2000)
            damage = (int)Math.floor((temperature * 0.025) - 50);
        else if (temperature >= 6000) {
            damage = 100;
            this.running = false;
        }
        updateAnimation();
    }
    private boolean checktemp1(int increment) {
        if (damage < 33) {
            temperature += increment;
            return true;
        }
        else if (damage <= 66) {
            temperature += (int)Math.ceil(1.5 * increment);
            return true;
        } return false;
    }

    public void decreaseTemperature (int decrement)
    {
        if (!this.running || this.temperature < 0 || decrement < 0 || this.damage == 100) return;

        if (this.damage > 49) this.temperature -= (int)Math.ceil(decrement * 0.5);
        else this.temperature -= decrement;

        this.updateAnimation();
    }

    public void updateAnimation() {
        if (!this.running && this.damage != 100) {
            super.setAnimation(offAnimation);
        }
        //if (this.running) {
        checktemperature();
        //}
        if (this.temperature == 2000 && this.damage == 100) {
            setAnimation(this.reactor_extinguished);
        }
    }

    private void checktemperature()
    {
        check1();
//        if (getTemperature() <= 4000 && this.running) {
//            setAnimation(normalAnimation);
//        }
        check2();
//        else if (getTemperature() > 4000 && getTemperature() < 6000 && this.running) {
//            setAnimation(overheatedAnimation);
//        }
        check3();
//        else if (this.damage == 100 && this.temperature >= 6000 && this.running) {
//            running = false;
//            setAnimation(destroyAnimation);
//            for (EnergyConsumer shtuchka : devices) {
//                shtuchka.setPowered(false);
//            }
//        }
    }

    private void check1() {
        if (getTemperature() > 4000 || !this.running) return;
        setAnimation(normalAnimation);
    }
    private void check2() {
        if (getTemperature() <= 4000 || getTemperature() >= 6000 || !this.running) return;
        setAnimation(overheatedAnimation);
    }
    private void check3() {
        if (this.damage != 100 || this.temperature < 6000) return;
        this.running = false;
        setAnimation(destroyAnimation);
        for (EnergyConsumer shtuchka : devices) {
            shtuchka.setPowered(false);
        }
    }

    public boolean repair() {
        if (this.getDamage() == 0) return false;
//        if (molotok == null || getDamage() <= 0 || getDamage() == 100) return;
//        if (molotok.getRemainingUses() < 1) return;

       // molotok.useWith(this);
        if ((this.damage-50f) >= 0) {
            this.damage-=50f;
            this.temperature = (int)(4000 * (this.damage*0.01) + 2000);
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
        for (EnergyConsumer shtuchka : devices) {
            shtuchka.setPowered(true);
        }
    }

    public void turnOff() {
        this.running = false;
        updateAnimation();
        for (EnergyConsumer shtuchka : devices) {
            shtuchka.setPowered(false);
        }
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
    public boolean extinguish()
    {
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
