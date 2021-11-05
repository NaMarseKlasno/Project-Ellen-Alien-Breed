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
    private boolean wait10Sec;

    public DefectiveLight() {
        super();
        this.isOn = false;
        this.wait10Sec = false;
    }

    private void random() {
        if (this.wait10Sec) return;
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
        disposable = new Loop<>(new Invoke<>(this::random)).scheduleFor(this);
    }

    @Override
    public boolean repair()
    {
        if (!this.isOn) return false;

        this.disposable.dispose();
        this.wait10Sec = true;

        new ActionSequence<> (
            new Wait   <> (10f),
            new Invoke <> (this::disposableloop),
            new Invoke <> (this::togglestatus)
        ).scheduleFor(this);

        this.wait10Sec = false;
        return this.isOn = true;
    }

    private void disposableloop() {
        disposable = new Loop<>(
            new Invoke<>(this::random)
        ).scheduleFor(this);
    }

    public boolean isPochynena() {
        return wait10Sec;
    }

    private void togglestatus() {
        this.isOn = !this.isOn;
    }

    @Override
    public boolean extinguish() {
        return false;
    }
}
