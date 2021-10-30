package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.actions.Invoke;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;


public class DefectiveLight extends Light {
    private boolean isOn;

    public DefectiveLight() {
        super();
    }

    private void Random() {
        this.isOn = false;
        int max = 20, min = 1, range = max - min + 1;

        int rand = (int)(Math.random() * range) + min;
        if (rand == 1) {
            toggle();
            this.isOn = !this.isOn;
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::Random)).scheduleFor(this);
    }

}
