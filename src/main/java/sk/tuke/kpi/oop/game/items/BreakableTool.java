package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;

public abstract class BreakableTool <A extends Actor> extends AbstractActor implements Usable<A> {

    private int remainingUses;

    public BreakableTool(int remainingUses) {
        this.remainingUses = remainingUses;
    }

    //@Override
    public int getRemainingUses() {
        return remainingUses;
    }

    @Override
    public void useWith(A actor) {
        if (remainingUses == 0) return;

        --remainingUses;

        if (remainingUses == 0)
            getScene().removeActor(this);
    }

}
