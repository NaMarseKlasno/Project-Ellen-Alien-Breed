package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.gamelib.backends.lwjgl2.Lwjgl2Backend;
import sk.tuke.kpi.oop.game.scenarios.EscapeRoom;
//import sk.tuke.kpi.oop.game.scenarios.MissionImpossible;

public class Main
{
    public static void main(String[] args)
    {
        WindowSetup windowSetup = new WindowSetup("Project Ellen", 600, 400);

        Game game = new GameApplication(windowSetup, new Lwjgl2Backend());

        Scene scene = new World("World", "maps/escape-room.tmx", new EscapeRoom.Factory());
        EscapeRoom escapeRoom = new EscapeRoom();
        // MissionImpossible missionImpossible = new MissionImpossible();

        game.addScene(scene);
        scene.addListener(escapeRoom);

        game.start();

        game.getInput().onKeyPressed(Input.Key.ESCAPE, game::stop);
    }
}
