package sk.tuke.kpi.oop.game.openables;

import sk.tuke.kpi.gamelib.Actor;

public class LockedDoor extends Door
{
    private boolean STATUS;

    public LockedDoor(String name,Orientation orientation) {
        super(name, orientation);
        this.STATUS = true;
    }


    public void lock() {
        this.STATUS = true;
        close();
    }

    public void unlock() {
        this.STATUS = false;
        open();
    }

    public boolean isLocked() {
        return this.STATUS;
    }

    @Override
    public void useWith(Actor actor) {
        if (this.STATUS) return;
        super.useWith(actor);
    }
}
