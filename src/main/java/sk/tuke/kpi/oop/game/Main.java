package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.gamelib.backends.lwjgl2.Lwjgl2Backend;
import sk.tuke.kpi.oop.game.scenarios.FirstSteps;

public class Main {

    public static void main(String[] args)
    {
        WindowSetup windowSetup = new WindowSetup("Project Ellen", 800, 600);
        Game game = new GameApplication(windowSetup, new Lwjgl2Backend());

        Scene scene = new World("world");
        game.addScene(scene);

        FirstSteps scenar = new FirstSteps();
        scene.addListener(scenar);


        game.getInput().onKeyPressed(Input.Key.ESCAPE, game::stop);
        game.start();
    }
}
