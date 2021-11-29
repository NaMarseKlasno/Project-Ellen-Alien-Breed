package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.actions.Drop;
import sk.tuke.kpi.oop.game.actions.Shift;
import sk.tuke.kpi.oop.game.actions.Take;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.items.Usable;
import java.util.Objects;


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
                new Take<>().scheduleFor(KEEPER);
                break;
            case BACKSPACE:
                new Drop<>().scheduleFor(KEEPER);
                break;
            case S:
                new Shift<>().scheduleFor(KEEPER);
                break;
            case U:
                Usable<?> USE = Objects.requireNonNull(this.KEEPER.getScene()).getActors().stream().filter(this.KEEPER::intersects).filter(Usable.class::isInstance).map(Usable.class::cast).findFirst().orElse(null);
                if (USE == null) return;
                new Use<>(USE).scheduleForIntersectingWith(this.KEEPER);
                break;
            case B:
                if (Usable.class.isInstance(this.KEEPER.getBackpack().peek()))
                    new Use<>((Usable<?>)this.KEEPER.getBackpack().peek()).scheduleForIntersectingWith(this.KEEPER);
                break;
            default:
                break;
        }
    }
}
