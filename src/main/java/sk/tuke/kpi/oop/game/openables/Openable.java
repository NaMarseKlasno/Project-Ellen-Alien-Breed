package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;

public interface Openable extends Actor
{
    void open();
    void close();
    boolean isOpen();
}
