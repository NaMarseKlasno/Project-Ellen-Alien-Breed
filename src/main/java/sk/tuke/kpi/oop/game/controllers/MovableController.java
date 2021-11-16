package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MovableController implements KeyboardListener {

    Movable PLAYER;
    private List<Direction> ARRAY;
    private Move<Movable> lastMove;

    private Map<Input.Key, Direction> keyDirectionMap = Map.ofEntries(
        Map.entry(Input.Key.UP, Direction.NORTH),
        Map.entry(Input.Key.DOWN, Direction.SOUTH),
        Map.entry(Input.Key.LEFT, Direction.WEST),
        Map.entry(Input.Key.RIGHT, Direction.EAST)
    );

    public MovableController(Movable player) {
        this.PLAYER = player;
        this.ARRAY = new ArrayList<>();
    }

    @Override
    public void keyPressed(@NotNull Input.Key key) {
        if (keyDirectionMap.containsKey(key)) {
            this.ARRAY.add(keyDirectionMap.get(key));
            move();
        }
    }

    @Override
    public void keyReleased(@NotNull Input.Key key) {
        if (keyDirectionMap.containsKey(key)) {
            this.ARRAY.remove(keyDirectionMap.get(key));
            move();
        }
    }

    private void move()
    {
        Direction direction = Direction.NONE;

        for (Direction direction1:ARRAY) direction = direction.combine(direction1);
        if (ARRAY.isEmpty() || lastMove!=null) lastMove.stop();

        if (direction != Direction.NONE)
        {
            lastMove = new Move<>(direction,5);
            lastMove.setActor(PLAYER);
            lastMove.scheduleFor(PLAYER);
        }
    }

}
