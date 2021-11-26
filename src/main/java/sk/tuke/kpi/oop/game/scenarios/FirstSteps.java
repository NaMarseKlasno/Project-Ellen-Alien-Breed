package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.*;



public class FirstSteps implements SceneListener
{
    private Ripley ripley;

    @Override
    public void sceneInitialized(@NotNull Scene scene)
    {

        ripley = new Ripley();
        scene.addActor(ripley, 0, 0);

        MovableController movableController = new MovableController(ripley);
        scene.getInput().registerListener(movableController);



        Energy ENERGY = new Energy();
        scene.addActor(ENERGY,-100, 50);
        new When<>(
            () -> ripley.intersects(ENERGY),
            new Invoke<>(() -> ENERGY.useWith(ripley))
        ).scheduleFor(ripley);


        Ammo AMMO = new Ammo();
        scene.addActor(AMMO,-200, 50);

        new When<>(
            () -> ripley.intersects(AMMO),
            new Invoke<>(() -> AMMO.useWith(ripley))
        ).scheduleFor(ripley);

        Hammer HAMMER = new Hammer();
        FireExtinguisher FIREEXTINGUISHER = new FireExtinguisher();
        Wrench WRENCH = new Wrench();
        scene.addActor(HAMMER, 100, -50);
        scene.addActor(FIREEXTINGUISHER, 120, 40);
        scene.addActor(WRENCH, -150, 200);
        ripley.getBackpack().add(HAMMER);
        ripley.getBackpack().add(WRENCH);
        ripley.getBackpack().add(FIREEXTINGUISHER);

        scene.getGame().pushActorContainer(ripley.getBackpack());
        ripley.getBackpack().shift();

        KeeperController keeperController = new KeeperController(ripley);
        scene.getInput().registerListener(keeperController);
    }


    @Override
    public void sceneUpdating(@NotNull Scene scene)
    {
        int windowHeight = scene.getGame().getWindowSetup().getHeight();
        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET;

        scene.getGame().getOverlay().drawText("Energy: " + ripley.getHealth().getValue(), 120, yTextPos);
    }

}
