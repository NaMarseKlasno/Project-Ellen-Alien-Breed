package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.actions.Move;
import sk.tuke.kpi.oop.game.controllers.MovableController;
//import sk.tuke.kpi.gamelib.actions.ActionSequence;


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

    }
}
