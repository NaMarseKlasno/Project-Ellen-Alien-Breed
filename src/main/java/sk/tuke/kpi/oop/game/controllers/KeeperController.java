package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.actions.Drop;
import sk.tuke.kpi.oop.game.actions.Shift;
import sk.tuke.kpi.oop.game.actions.Take;

public class KeeperController implements KeyboardListener {

    private Keeper KEEPER;

    public KeeperController(Keeper keeper) {
        this.KEEPER = keeper;
    }

    @Override
    public void keyPressed(Input.@NotNull Key key)
    {
        switch (key)
        {
            case ENTER:
                new Take<>(KEEPER).scheduleFor(KEEPER);
                break;
            case BACKSPACE:
                new Drop<>(KEEPER).scheduleFor(KEEPER);
                break;
            case S:
                new Shift<>().scheduleFor(KEEPER);
                break;
            default:
                break;
        }
    }
}
