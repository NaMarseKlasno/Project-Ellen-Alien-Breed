package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.Ammo;
import sk.tuke.kpi.oop.game.items.Energy;


public class FirstSteps implements SceneListener
{
    private Ripley ripley;


    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        this.ripley = new Ripley();
        scene.addActor(ripley, 0,0);

        Input input = scene.getInput();

        MovableController Controller = new MovableController(ripley);
        input.registerListener(Controller);


        Energy energy = new Energy();
        scene.addActor(energy,-100, 50);
        new When<>(
            () -> ripley.intersects(energy),
            new Invoke<>(() -> energy.useWith(ripley))
        ).scheduleFor(ripley);


        Ammo ammo = new Ammo();
        scene.addActor(ammo,-200, 50);
        new When<>(
            () -> ripley.intersects(ammo),
            new Invoke<>(() -> ammo.useWith(ripley))
        ).scheduleFor(ripley);

    }

    @Override
    public void sceneUpdating(@NotNull Scene scene)
    {
        int windowHeight = scene.getGame().getWindowSetup().getHeight();
        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET;

        scene.getGame().getOverlay().drawText("Energy: " + ripley.getEnergy(), 120, yTextPos);
    }

}
