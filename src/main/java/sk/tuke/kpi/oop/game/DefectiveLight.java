package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.actions.Invoke;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;

import java.util.Objects;


public class DefectiveLight extends Light {
    private Disposable dis;
    private boolean isOn;

    private void Random()
    {
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
        dis = new Loop<>(new Invoke(this::Random)).scheduleOn(Objects.requireNonNull(this.getScene()));
    }


//    private void refreshLoop() {
//        loop = new Loop<>(new Invoke<>(this::toggleRandom)).scheduleFor(this);
//    }

}
