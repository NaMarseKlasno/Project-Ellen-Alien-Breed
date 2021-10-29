package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.Actor;

public interface Usable<R extends Actor>
{
    void useWith(R actor);
}
