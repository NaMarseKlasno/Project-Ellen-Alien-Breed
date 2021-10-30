package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.framework.AbstractActor;


public class BreakableTool extends AbstractActor {

    private int remainingUses;

    public BreakableTool(int remainingUses) {
        this.remainingUses = remainingUses;
    }

    public int getRemainingUses() {
        return remainingUses;
    }

    public void use() {
        if (remainingUses == 0) return;

        --remainingUses;

        if (remainingUses == 0)
            getScene().removeActor(this);
    }

}
