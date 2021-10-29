package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;


public abstract class BreakableTool<R extends Actor> extends AbstractActor implements Usable<R> {
    private int remainingUses;


    public BreakableTool(int remainingUses) {
        this.remainingUses = remainingUses;
    }


    public int getRemainingUses() {
        return this.remainingUses;
    }

    @Override
    public void useWith(R r) {
        if (r == null) return;

        if (remainingUses > 0) {
            this.remainingUses-=1;
        } else {
            this.getScene().removeActor(this);
            this.removedFromScene(getScene());
        }
    }
}


