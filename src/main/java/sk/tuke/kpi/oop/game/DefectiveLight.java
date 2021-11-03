package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.actions.Invoke;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;


public class DefectiveLight extends Light implements Repairable {

    private boolean isOn;
    private Disposable disposable;

    public DefectiveLight() {
        super();
        this.isOn = false;
    }

    private void Random() {
        //this.isOn = false;
        int max = 20, min = 1, range = max - min + 1;

        int rand = (int)(Math.random() * range) + min;
        if (rand == 1) {
            toggle();
            //this.isOn = !this.isOn;
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        disposable = new Loop<>(new Invoke<>(this::Random)).scheduleFor(this);
    }

    @Override
    public boolean repair()
    {
        if (!this.isOn) return false;

        this.disposable.dispose();
        this.isOn = true;

        new ActionSequence<> (
            new Invoke <> (this::disposable_loop),
            new Invoke <> (this::toggle_status)
        ).scheduleFor(this);

        return true;
    }

    private void disposable_loop() {
        disposable = new Loop<>(new Invoke<>(this::Random)).scheduleFor(this);
    }

    private void toggle_status() {
        this.isOn = !this.isOn;
    }

    @Override
    public boolean extinguish() {
        return false;
    }
}
